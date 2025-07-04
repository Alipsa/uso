# Uso Build System – User Manual

## Introduction to Uso

**Uso** is an **Ant and Groovy based build system** designed for JVM projects. It combines the simplicity of Ant’s target-driven build process with the power and flexibility of Groovy scripting. In practice, Uso behaves much like Apache Ant but lets you write your build logic in Groovy instead of XML. This gives build engineers and developers a familiar target/dependency model while leveraging Groovy’s syntax and capabilities for scripting.

**Key Benefits:**

- *Groovy Scripting*: Build files are written in Groovy, enabling you to use the full Groovy/Java API alongside traditional Ant tasks. This makes complex build logic easier to implement compared to Ant’s XML.
- *Declarative Targets*: Uso introduces a `project` closure where you declare targets and their dependencies in a straightforward manner (similar to Ant’s `<target>` but in Groovy code).
- *Maven Integration*: Maven Ant Tasks are included by default. You can declare **dependencies** (and even import BOMs) and publish artifacts to Maven repositories without additional setup.
- *No Custom DSL to Learn*: Uso extends Groovy’s built-in `AntBuilder`, so all standard Ant tasks (like `javac`/`groovyc`, `jar`, `junit`, etc.) are available using Groovy’s builder syntax. There’s no new language; if you know Ant or Gradle, the concepts will feel familiar.

**Who Should Use Uso**: This tool is ideal for build engineers or JVM developers (Groovy, Java, Kotlin, etc.) who want the **declarative structure of Ant** with the scripting ease of Groovy. If you have existing Ant builds or prefer explicit target order of execution, but want more flexibility and easier dependency management, Uso was created for you.

## Installation

Uso requires **Groovy** to be installed on your system and available on the PATH (we recommend installing via [SDKMAN!](https://sdkman.io) for ease). Uso itself can be installed on a per-project basis using a simple shell script:

```bash
curl -H 'Cache-Control: no-cache, no-store' -s   "https://raw.githubusercontent.com/Alipsa/uso/refs/heads/main/uso-core/src/main/script/installUso.sh" | bash
```

Running the above command in your project directory will download and set up the `uso` command for that project. This typically places an executable script (and necessary Uso JARs) into your project (similar to a Gradle wrapper). Ensure the script has execute permission. After installation, you should see an `uso` file in your project.

**Verifying Installation:** Open a new terminal in your project directory and run:

```bash
uso --help
```

If Uso is set up, this should print the Uso version (or help text). You can also run `uso` without arguments to execute the default target of your build.

> **Note:** The `uso` script uses the system Groovy to run. Thus, ensure your Groovy version is compatible (Uso is tested with Groovy 4.x and Java 17+).

## Getting Started

To define a build, create a `build.groovy` file in your project root. At minimum:

```groovy
project.with {
  target('hello') {
    echo "Hello from Uso!"
  }
  defaultTarget = 'hello'
}
```

Run it:

```bash
./uso
```

This will execute the `hello` target. Targets can depend on each other and use Ant or Groovy APIs directly.

## Dependency Management

Uso supports Maven-style dependency management out of the box:

```groovy
dependencies(id: 'compile') {
  dependency(coords: 'org.apache.commons:commons-lang3:3.12.0')
}

target('resolveDeps') {
  resolve(dependenciesRef: 'compile') {
    path(id: 'compilePath', refid: 'compile.classpath')
  }
}
```

This downloads dependencies and makes them available via a named classpath.

### Using Ivy with AntBuilder

Although Uso focuses on Maven-based dependency resolution, you can also use [Apache Ivy](https://ant.apache.org/ivy/) d as the dependency management framework. Ivy is a flexible dependency manager integrated into the Ant ecosystem.

#### 1. Add Ivy to Your Classpath

If not using `@Grab`, make sure Ivy is available to Ant:

```groovy
project.with {
  taskdef(name: 'ivyConfigure', classname: 'org.apache.ivy.ant.IvyConfigure',
          classpath: 'lib/ivy-2.5.1.jar')
  taskdef(name: 'ivyResolve', classname: 'org.apache.ivy.ant.IvyResolve',
          classpath: 'lib/ivy-2.5.1.jar')
  taskdef(name: 'ivyCachePath', classname: 'org.apache.ivy.ant.IvyCachePath',
          classpath: 'lib/ivy-2.5.1.jar')
}
```

#### 2. Provide an `ivy.xml` File

```xml
<ivy-module version="2.0">
  <info organisation="org.example" module="demo"/>
  <dependencies>
    <dependency org="junit" name="junit" rev="4.13.2"/>
  </dependencies>
</ivy-module>
```

#### 3. Resolve Dependencies and Use Classpath

```groovy
target('resolveIvy') {
  ivyConfigure()
  ivyResolve(file: 'ivy.xml')
  ivyCachePath(pathid: 'ivy.classpath')
}

target('compileWithIvy', depends: 'resolveIvy') {
  groovyc(srcdir: 'src', destdir: 'build/classes', classpathref: 'ivy.classpath')
}
```

Using Ivy this way gives you full Ant compatibility for projects that already use Ivy or where Maven isn't preferred.

## Compilation

Compile Groovy or Java source using:

```groovy
target('compile', depends: 'resolveDeps') {
  groovyc(srcdir: 'src/main/groovy', destdir: 'build/classes', classpathref: 'compilePath')
}
```

Set `targetBytecode: '21'` if compiling for Java 21.

## Testing

Uso supports JUnit 4 and 5. Example with JUnit 5:

```groovy
target('test', depends: 'compile') {
  junitlauncher(printSummary: true, haltOnFailure: true) {
    classpath {
      pathelement(location: 'build/classes')
      path(refid: 'compilePath')
    }
    testclasses {
      fileset(dir: 'build/classes')
    }
  }
}
```

## Packaging

Package compiled classes:

```groovy
target('jar', depends: 'compile') {
  jar(destfile: 'build/libs/app.jar') {
    fileset(dir: 'build/classes')
  }
}
```

## Using BOMs

For dependency version alignment:

```groovy
dependencyManagement(id: 'bom') {
  dependencies {
    dependency(groupId: 'org.junit', artifactId: 'junit-bom', version: '5.9.2', type: 'pom', scope: 'import')
  }
}
```

Declare dependencies without versions:

```groovy
dependencies(id: 'test') {
  dependency(groupId: 'org.junit.jupiter', artifactId: 'junit-jupiter')
}
```

Then generate and register a POM using `createPom` and `pom()` before resolving.

## Publishing Artifacts

Install to local Maven repo:

```groovy
target('publishLocal') {
  install(artifactsRef: 'compiledArtifacts') {
    pom(file: 'build/libs/pom.xml')
  }
}
```

Deploy to remote repo:

```groovy
target('deploy') {
  deploy(artifactsRef: 'compiledArtifacts') {
    remoteRepo(id: 'central', url: 'https://your.repo.url')
  }
}
```

## Layout Task

Configure project layout quickly:

```groovy
target('init') {
  layout(type: 'maven', language: 'groovy')
}
```

This sets up standard directories and variables like `srcDir`, `mainClassesDir`, etc.

## Troubleshooting

- Ensure `uso` script is executable (`chmod +x uso`).
- Use `echo` or `echoproperties()` for debugging.
- Duplicate targets will rerun dependencies unless structured with care.
- Ensure correct Java/Groovy version for compatibility.

---

Happy building with Uso! If you run into issues or have questions, you can seek help on the project’s GitHub page or community forums. Uso aims to be a simple yet powerful tool in your build automation arsenal, marrying the reliability of Ant with the expressive power of Groovy. **Use** it well!
