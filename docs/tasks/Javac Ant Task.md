# Javac Ant Task

## Description

Compiles a Java source tree. The source and destination directory will be recursively scanned for Java source files to compile. Only `.java` files that have no corresponding `.class` file or where the `.class` file is older than the `.java` file will be compiled.

Ant uses only the names of the source and class files to find the classes that need a rebuild. It will not scan the source and therefore will have no knowledge about nested classes, classes that are named differently from the source file, and so on. See the `<depend>` task for dependency checking based on other than just existence/modification times.

When the source files are part of a package, the directory structure of the source tree should follow the package hierarchy.

It is possible to refine the set of files that are being compiled using `includes`, `includesfile`, `excludes`, and `excludesfile` attributes, or by using wildcard patterns. It is also possible to use different compilers, specified by the `build.compiler` property or the `compiler` attribute.

## Parameters

| Attribute          | Description                                                                                                                               | Required                                                                 |
|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------|
| `srcdir`           | Location of the java files.                                                                                                               | Yes, unless nested `<src>` elements or `modulesourcepath` are present.   |
| `destdir`          | Location to store the class files.                                                                                                        | No                                                                       |
| `includes`         | Comma- or space-separated list of patterns of files that must be included.                                                                | No; defaults to all `.java` files.                                       |
| `includesfile`     | Name of a file. Each line of this file is taken to be an include pattern.                                                                 | No                                                                       |
| `excludes`         | Comma- or space-separated list of patterns of files that must be excluded.                                                                | No; defaults to default excludes or none if `defaultexcludes` is `no`.   |
| `excludesfile`     | Name of a file. Each line of this file is taken to be an exclude pattern.                                                                 | No                                                                       |
| `defaultexcludes`  | Indicates whether default excludes should be used or not (`yes` or `no`).                                                                   | No; defaults to `yes`.                                                   |
| `classpath`        | The classpath to use.                                                                                                                     | No                                                                       |
| `sourcepath`       | The sourcepath to use. To suppress the `sourcepath` switch, use `sourcepath=""`.                                                          | No; defaults to `srcdir` unless nested `<src>` elements are specified.   |
| `bootclasspath`    | Location of bootstrap class files.                                                                                                        | No                                                                       |
| `classpathref`     | The classpath to use, given as a reference to a path defined elsewhere.                                                                   | No                                                                       |
| `sourcepathref`    | The sourcepath to use, given as a reference to a path defined elsewhere.                                                                  | No                                                                       |
| `bootclasspathref` | The bootstrapclasspath to use, given as a reference to a path defined elsewhere.                                                          | No                                                                       |
| `extdirs`          | Location of installed extensions.                                                                                                         | No                                                                       |
| `encoding`         | Encoding of source files.                                                                                                                 | No                                                                       |
| `nowarn`           | Indicates whether the `-nowarn` switch should be passed to the compiler.                                                                  | No; defaults to `off`.                                                   |
| `debug`            | Indicates whether source should be compiled with debug information.                                                                       | No; defaults to `off`.                                                   |
| `debuglevel`       | Keyword list to be appended to the `-g` command-line switch (e.g., `lines,vars,source`).                                                  | No; ignored when `debug` is `false`.                                     |
| `optimize`         | Indicates whether source should be compiled with optimization. (Ignored by Sun javac since JDK 1.3).                                        | No; defaults to `off`.                                                   |
| `deprecation`      | Indicates whether source should be compiled with deprecation information.                                                                 | No; defaults to `off`.                                                   |
| `verbose`          | Asks the compiler for verbose output.                                                                                                     | No; defaults to `off`.                                                   |
| `depend`           | Enables dependency-tracking for compilers that support it.                                                                                | No; defaults to `off`.                                                   |
| `target`           | Generate class files for a specific Java release.                                                                                         | No                                                                       |
| `release`          | Compiles against the API of a specified Java platform version. *Since Ant 1.9.8*                                                          | No                                                                       |
| `source`           | Provide source compatibility with a specific Java release.                                                                                | No                                                                       |
| `fork`             | If `true`, run the compiler in a separate process.                                                                                        | No; defaults to `false`.                                                 |
| `executable`       | Complete path to the compiler executable.                                                                                                 | No; defaults to the compiler of the Java version Ant is running on.        |
| `memoryinitialsize`| Initial memory for the forked compiler.                                                                                                   | No                                                                       |
| `memorymaximumsize`| Maximum memory for the forked compiler.                                                                                                   | No                                                                       |
| `failonerror`      | If `true`, stop the build if compilation errors occur.                                                                                    | No; defaults to `true`.                                                  |
| `errorproperty`    | Name of a property to set if compilation fails.                                                                                           | No                                                                       |
| `compiler`         | The compiler to use (e.g., `modern`, `jikes`).                                                                                            | No; defaults to the JDK compiler Ant is running with.                    |
| `listfiles`        | If `true`, list the files being compiled.                                                                                                 | No; defaults to `false`.                                                 |
| `tempdir`          | Directory for temporary files used by some compilers. *Since Ant 1.8.0*                                                                   | No                                                                       |
| `updatedproperty`  | Name of a property to set if files were compiled. *Since Ant 1.8.0*                                                                       | No                                                                       |
| `includeantruntime`| Whether to include the Ant runtime libraries in the classpath.                                                                            | No; defaults to `true`.                                                  |
| `includejavaruntime`| Whether to include the default Java runtime libraries in the classpath.                                                                   | No; defaults to `false`.                                                 |
| `nativeheaderdir`  | Directory to store native header files (`.h` files). *Since Ant 1.9.7*                                                                    | No                                                                       |
| `modulepath`       | The module path. *Since Ant 1.9.7*                                                                                                        | No                                                                       |
| `modulepathref`    | Reference to a module path. *Since Ant 1.9.7*                                                                                             | No                                                                       |
| `modulesourcepath` | The module source path. *Since Ant 1.9.7*                                                                                                 | No                                                                       |
| `modulesourcepathref`| Reference to a module source path. *Since Ant 1.9.7*                                                                                      | No                                                                       |
| `upgrademodulepath`| The upgrade module path. *Since Ant 1.9.7*                                                                                                | No                                                                       |
| `upgrademodulepathref`| Reference to an upgrade module path. *Since Ant 1.9.7*                                                                                    | No                                                                       |
| `compilerarg`      | Allows to specify additional command line arguments for the compiler. *Since Ant 1.8.0*                                                   | No                                                                       |
| `compilerclasspath`| The classpath to use when loading the compiler implementation if a custom compiler is used. *Since Ant 1.8.0*                             | No                                                                       |
| `compilerclasspathref`| Reference to a compiler classpath. *Since Ant 1.8.0*                                                                                      | No                                                                       |



## Nested Elements

### src

Specifies a path-like structure for source files. The `srcdir` attribute is a shortcut for this.

### classpath

Specifies the classpath to use for compilation. This is a path-like structure.

### sourcepath

Specifies the sourcepath to use for compilation. This is a path-like structure.

### bootclasspath

Specifies the boot classpath to use for compilation. This is a path-like structure.

### extdirs

Specifies the locations of installed extensions. This is a path-like structure.

### compilerarg

Specifies additional command-line arguments for the compiler. This element has a `value`, `line`, `file`, or `path` attribute.

### compilerclasspath

Specifies the classpath to use when loading the compiler implementation if a custom compiler is used. This is a path-like structure.

### modulepath

*Since Ant 1.9.7*

Specifies the module path. This is a path-like structure.

### modulesourcepath

*Since Ant 1.9.7*

Specifies the module source path. This is a path-like structure.

### upgrademodulepath

*Since Ant 1.9.7*

Specifies the upgrade module path. This is a path-like structure.



## Groovy AntBuilder DSL Equivalent

To use the `javac` Ant task within a Groovy script via `AntBuilder`, you invoke it as a method on your `AntBuilder` instance. The XML attributes translate directly to named method arguments in Groovy, and nested elements often translate to closures or method calls within a primary closure.

### Key Parameters (and their Groovy equivalents):

Most attributes listed in the Parameters section above can be used as named arguments in the Groovy DSL. For example, `srcdir="src/main/java"` becomes `srcdir: "src/main/java"` in Groovy.

| Ant Attribute        | Groovy Parameter Type | Description                                                                                                                               |
|----------------------|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| `srcdir`             | `String` or `File`    | Location of the java files.                                                                                                               |
| `destdir`            | `String` or `File`    | Location to store the class files.                                                                                                        |
| `includes`           | `String`              | Comma- or space-separated list of patterns of files that must be included.                                                                |
| `excludes`           | `String`              | Comma- or space-separated list of patterns of files that must be excluded.                                                                |
| `classpath`          | `String` or `Path`    | The classpath to use.                                                                                                                     |
| `sourcepath`         | `String` or `Path`    | The sourcepath to use.                                                                                                                    |
| `bootclasspath`      | `String` or `Path`    | Location of bootstrap class files.                                                                                                        |
| `classpathref`       | `String`              | Reference to a predefined classpath.                                                                                                      |
| `sourcepathref`      | `String`              | Reference to a predefined sourcepath.                                                                                                     |
| `bootclasspathref`   | `String`              | Reference to a predefined bootclasspath.                                                                                                  |
| `extdirs`            | `String` or `Path`    | Location of installed extensions.                                                                                                         |
| `encoding`           | `String`              | Encoding of source files.                                                                                                                 |
| `nowarn`             | `boolean`             | If `true`, pass the `-nowarn` switch to the compiler.                                                                                     |
| `debug`              | `boolean`             | If `true`, compile with debug information.                                                                                                |
| `debuglevel`         | `String`              | Keyword list for the `-g` switch (e.g., `lines,vars,source`).                                                                             |
| `optimize`           | `boolean`             | If `true`, compile with optimization.                                                                                                     |
| `deprecation`        | `boolean`             | If `true`, compile with deprecation information.                                                                                          |
| `verbose`            | `boolean`             | If `true`, ask the compiler for verbose output.                                                                                           |
| `depend`             | `boolean`             | If `true`, enable dependency-tracking.                                                                                                    |
| `target`             | `String`              | Generate class files for a specific Java release (e.g., `1.8`, `9`).                                                                      |
| `release`            | `String`              | Compiles against the API of a specified Java platform version (e.g., `8`, `9`, `11`).                                                     |
| `source`             | `String`              | Provide source compatibility with a specific Java release (e.g., `1.8`, `9`).                                                             |
| `fork`               | `boolean`             | If `true`, run the compiler in a separate process.                                                                                        |
| `executable`         | `String` or `File`    | Complete path to the compiler executable.                                                                                                 |
| `memoryinitialsize`  | `String`              | Initial memory for the forked compiler (e.g., `64m`).                                                                                     |
| `memorymaximumsize`  | `String`              | Maximum memory for the forked compiler (e.g., `256m`).                                                                                    |
| `failonerror`        | `boolean`             | If `true`, stop the build if compilation errors occur.                                                                                    |
| `errorproperty`      | `String`              | Name of a property to set if compilation fails.                                                                                           |
| `compiler`           | `String`              | The compiler to use (e.g., `modern`, `jikes`, `extJavac`).                                                                                |
| `listfiles`          | `boolean`             | If `true`, list the files being compiled.                                                                                                 |
| `tempdir`            | `String` or `File`    | Directory for temporary files.                                                                                                            |
| `updatedproperty`    | `String`              | Name of a property to set if files were compiled.                                                                                         |
| `includeantruntime`  | `boolean`             | Whether to include the Ant runtime libraries in the classpath.                                                                            |
| `includejavaruntime` | `boolean`             | Whether to include the default Java runtime libraries in the classpath.                                                                   |
| `nativeheaderdir`    | `String` or `File`    | Directory to store native header files.                                                                                                   |
| `modulepath`         | `String` or `Path`    | The module path.                                                                                                                          |
| `modulepathref`      | `String`              | Reference to a module path.                                                                                                               |
| `modulesourcepath`   | `String` or `Path`    | The module source path.                                                                                                                   |
| `modulesourcepathref`| `String`              | Reference to a module source path.                                                                                                        |
| `upgrademodulepath`  | `String` or `Path`    | The upgrade module path.                                                                                                                  |
| `upgrademodulepathref`| `String`              | Reference to an upgrade module path.                                                                                                      |

### Nested Elements in Groovy DSL

Nested elements like `<src>`, `<classpath>`, `<compilerarg>`, etc., are typically handled within a closure passed to the `javac` method.

-   **`<src>`**: Can be represented by a `src` closure or by directly providing `srcdir`.
    ```groovy
    ant.javac(destdir: "build/classes") {
        src(path: "src/main/java")
        // or multiple src paths
        src(path: "src/generated/java")
    }
    ```
-   **`<classpath>`**: Use a `classpath` closure with `pathelement` or `fileset` calls.
    ```groovy
    ant.javac(destdir: "build/classes", srcdir: "src/main/java") {
        classpath {
            pathelement(location: "libs/some.jar")
            fileset(dir: "common_libs") {
                include(name: "**/*.jar")
            }
        }
    }
    ```
-   **`<compilerarg>`**: Use a `compilerarg` method call within the `javac` closure.
    ```groovy
    ant.javac(destdir: "build/classes", srcdir: "src/main/java") {
        compilerarg(value: "-Xlint:unchecked")
        compilerarg(line: "-nowarn -verbose") // for multiple args on one line
    }
    ```
-   **`<modulepath>`, `<modulesourcepath>`, `<upgrademodulepath>`**: Similar to `classpath`, these can be defined within closures.
    ```groovy
    ant.javac(destdir: "build/classes", release: "11") {
        modulesourcepath {
            pathelement(location: "src/module-a/java")
            pathelement(location: "src/module-b/java")
        }
        modulepath(refid: "project.module.path") // Using a reference
    }
    ```




## Examples

### Basic Compilation

Compile all `.java` files in `src` and place the `.class` files in `build/classes`.

*Ant XML:*
```xml
<javac srcdir="src"
       destdir="build/classes"/>
```

*Groovy DSL:*
```groovy
ant.javac(srcdir: "src",
          destdir: "build/classes")
```

### Compilation with Classpath and Debug Info

Compile files, including debug information, and specify a classpath.

*Ant XML:*
```xml
<path id="compile.classpath">
  <fileset dir="libs">
    <include name="**/*.jar"/>
  </fileset>
</path>

<javac srcdir="src/main/java"
       destdir="build/main/classes"
       classpathref="compile.classpath"
       debug="on"/>
```

*Groovy DSL:*
```groovy
ant.path(id: "compile.classpath") {
    fileset(dir: "libs") {
        include(name: "**/*.jar")
    }
}

ant.javac(srcdir: "src/main/java",
          destdir: "build/main/classes",
          classpathref: "compile.classpath",
          debug: true) // 'on' becomes true in Groovy
```

### Forking the Compiler and Setting Memory

Compile in a separate JVM process with specified initial and maximum memory.

*Ant XML:*
```xml
<javac srcdir="${src}"
       destdir="${build}"
       fork="true"
       memoryinitialsize="128m"
       memorymaximumsize="512m">
  <classpath>
    <pathelement location="${classpath}"/>
  </classpath>
</javac>
```

*Groovy DSL:*
```groovy
ant.javac(srcdir: project.properties["src"],
          destdir: project.properties["build"],
          fork: true,
          memoryinitialsize: "128m",
          memorymaximumsize: "512m") {
    classpath {
        pathelement(location: project.properties["classpath"])
    }
}
```

### Using a Specific Compiler and Target Version

Compile using the Jikes compiler for Java 1.8 compatibility.

*Ant XML:*
```xml
<javac srcdir="src"
       destdir="classes"
       compiler="jikes"
       source="1.8"
       target="1.8"/>
```

*Groovy DSL:*
```groovy
ant.javac(srcdir: "src",
          destdir: "classes",
          compiler: "jikes",
          source: "1.8",
          target: "1.8")
```

### Compiling with Modules (Java 9+)

Compile a modular project.

*Ant XML:*
```xml
<javac destdir="build/modules"
       release="11">
  <modulesourcepath>
    <pathelement location="src/main/java"/>
  </modulesourcepath>
  <modulepath>
    <pathelement location="libs/dependency.jar"/>
  </modulepath>
</javac>
```

*Groovy DSL:*
```groovy
ant.javac(destdir: "build/modules",
          release: "11") {
    modulesourcepath {
        pathelement(location: "src/main/java")
    }
    modulepath {
        pathelement(location: "libs/dependency.jar")
    }
}
```

### Using Compiler Arguments

Pass specific arguments to the compiler, like enabling all lint warnings.

*Ant XML:*
```xml
<javac srcdir="src"
       destdir="build/classes">
  <compilerarg value="-Xlint:all"/>
  <compilerarg line="-Xdoclint:none -Werror"/>
</javac>
```

*Groovy DSL:*
```groovy
ant.javac(srcdir: "src",
          destdir: "build/classes") {
    compilerarg(value: "-Xlint:all")
    compilerarg(line: "-Xdoclint:none -Werror")
}
```

