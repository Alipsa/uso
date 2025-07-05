# Java Ant Task

## Description

The `java` task executes a Java class. It can be used to run any Java application within an Ant build. The task offers a high degree of control over the execution environment, including classpath, system properties, JVM arguments, and whether to fork a new JVM process.

It can execute a class by its name or run an executable JAR file. When forking a new JVM, you can specify memory settings, the JVM to use, and other JVM-specific options. It also supports Java Modules (since Ant 1.9.7) via `module` and `modulepath` attributes.

## Parameters

| Attribute                 | Description                                                                                                | Required                                                                  |
|---------------------------|------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| `classname`               | The Java class to execute.                                                                                 | Yes, unless `jar` or `module` is specified.                               |
| `jar`                     | The JAR file to execute. Its manifest must specify a `Main-Class`.                                         | Yes, unless `classname` or `module` is specified.                         |
| `module`                  | The Java module to execute (e.g., `com.example.mymodule` or `com.example.mymodule/com.example.MainClass`). | Yes, unless `classname` or `jar` is specified. *Since Ant 1.9.7*          |
| `args`                    | A string of command-line arguments to pass to the Java class.                                              | No                                                                        |
| `classpath`               | The classpath to use for the execution.                                                                    | No                                                                        |
| `classpathref`            | Adds a referenced classpath to the classpath.                                                              | No                                                                        |
| `modulepath`              | The modulepath to use for the execution. *Since Ant 1.9.7*                                                 | No                                                                        |
| `modulepathref`           | Adds a referenced modulepath to the modulepath. *Since Ant 1.9.7*                                          | No                                                                        |
| `upgrademodulepath`       | The location of modules that replace upgradeable modules in the runtime image. *Since Ant 1.9.7*           | No                                                                        |
| `fork`                    | If `true`, run the Java class in a separate JVM.                                                           | No; defaults to `false`                                                   |
| `spawn`                   | If `true`, run the Java class in a separate JVM but do not wait for it to finish. Implies `fork="true"`.   | No; defaults to `false`. *Since Ant 1.6*                                  |
| `jvm`                     | The command used to invoke the Java Virtual Machine, e.g., `gij` or `d:/java/bin/java`.                    | No; defaults to the `java` command found in `PATH`.                       |
| `maxmemory`               | Corresponds to `java -Xmx` or `-mx`.                                                                       | No                                                                        |
| `jvmargs`                 | A string of command-line arguments to pass to the JVM.                                                     | No                                                                        |
| `dir`                     | The working directory for the new JVM; ignored if `fork` is `false`.                                       | No; defaults to the project's base directory.                             |
| `output`                  | Name of a file to which to redirect standard output.                                                       | No                                                                        |
| `input`                   | Name of a file from which to redirect standard input.                                                      | No                                                                        |
| `inputstring`             | A string to use as standard input.                                                                         | No                                                                        |
| `error`                   | Name of a file to which to redirect standard error.                                                        | No                                                                        |
| `outputproperty`          | The name of a property in which the standard output of the command should be stored.                       | No                                                                        |
| `errorproperty`           | The name of a property in which the standard error of the command should be stored.                        | No                                                                        |
| `failonerror`             | If `true`, stop the build if the command exits with a non-zero return code.                                | No; defaults to `false` if `fork` is `false`, `true` if `fork` is `true`. |
| `resultproperty`          | The name of a property in which the return code of the command should be stored.                           | No                                                                        |
| `timeout`                 | Stop the command if it doesn't finish within the specified time (in milliseconds).                         | No                                                                        |
| `append`                  | If `true`, append output to existing files; otherwise, overwrite.                                          | No; defaults to `false`.                                                  |
| `newenvironment`          | If `true`, do not propagate the old environment when `fork`ing.                                            | No; defaults to `false`.                                                  |
| `taskname`                | The name to use for the task in log messages.                                                              | No; defaults to "java".                                                   |
| `logError`                | If `true`, send output from `stderr` to Ant's logging system.                                              | No; defaults to `false`. *Since Ant 1.6.2*                                |
| `addsourcefileextensions` | If `true`, try to append <q>.java</q> and <q>.class</q> to the classname.                                  | No; defaults to `true`. *Since Ant 1.8.2*                                 |



## Nested Elements

### arg

Passes a command-line argument to the new Java class. These are specified as nested `<arg>` elements.

| Attribute | Description                                                                 | Required |
|-----------|-----------------------------------------------------------------------------|----------|
| `value`   | A single command-line argument; can contain space characters.               | Yes      |
| `file`    | The name of a file, which is passed as the argument to the new Java program.  | Yes      |
| `path`    | A string that will be treated as a `PATH` and passed as a single argument.  | Yes      |
| `line`    | A string of arguments that will be tokenized and passed as individual arguments. | Yes      |

*Note: Exactly one of these attributes is required.*

### classpath

The classpath to be used for the Java execution. This is a [Path-like Structure](../using.html#path) and can be set via a nested `<classpath>` element.

### modulepath

*Since Ant 1.9.7*

The modulepath to be used for the Java execution. This is a [Path-like Structure](../using.html#path) and can be set via a nested `<modulepath>` element.

### upgrademodulepath

*Since Ant 1.9.7*

The location of modules that replace upgradeable modules in the runtime image. This is a [Path-like Structure](../using.html#path) and can be set via a nested `<upgrademodulepath>` element.

### jvmarg

Passes an argument to the JVM. These are specified as nested `<jvmarg>` elements. This element is ignored when `fork` is `false`.

| Attribute | Description                                                                 | Required |
|-----------|-----------------------------------------------------------------------------|----------|
| `value`   | A single command-line argument to the JVM; can contain space characters.    | Yes      |
| `file`    | The name of a file, which is passed as the argument to the JVM.             | Yes      |
| `path`    | A string that will be treated as a `PATH` and passed as a single JVM argument. | Yes      |
| `line`    | A string of arguments that will be tokenized and passed as individual JVM arguments. | Yes      |

*Note: Exactly one of these attributes is required.*

### sysproperty

Specifies a system property to be made available to the JVM. These are specified as nested `<sysproperty>` elements. The attributes for this element are `key` (the name of the system property) and `value` (the value of the system property). If `fork` is `false`, the system property is set on the Ant JVM; otherwise, it is set on the forked JVM.

| Attribute | Description                               | Required |
|-----------|-------------------------------------------|----------|
| `key`     | The name of the system property.          | Yes      |
| `value`   | The value for the system property.        | Yes      |
| `file`    | The file from which to load properties.   | No       |
| `path`    | The path to use as the system property.   | No       |
| `environment` | The environment variable to use as the value. | No       |

### env

*Since Ant 1.1*

Adds an environment variable to the forked process. These are specified as nested `<env>` elements. This element is ignored when `fork` is `false`.

| Attribute | Description                               | Required |
|-----------|-------------------------------------------|----------|
| `key`     | The name of the environment variable.     | Yes      |
| `value`   | The value for the environment variable.   | No       |
| `path`    | The path to use as the environment variable.| No       |
| `file`    | The file to use as the environment variable.| No       |

### bootclasspath

*Since Ant 1.4*

The bootclasspath to use for the Java execution. This is a [Path-like Structure](../using.html#path) and can be set via a nested `<bootclasspath>` element. This element is ignored if `fork` is `false` or if the target JVM doesn't support it (e.g., Java 9+ without specific JVM args).

### permissions

*Since Ant 1.6*

If `fork` is `true`, you can specify the permissions for the code run inside the JVM via a nested `<permissions>` element. See the [Permissions in Ant](../permissions.html) document for more details.

### assertions (ea, da)

*Since Ant 1.6*

You can control Java 1.4 assertions with nested `<assertions>`, `<enableassertions>`/`<ea>`, and `<disableassertions>`/`<da>` elements. These elements are ignored if `fork` is `false` or if the target JVM is older than 1.4.

`<assertions>` can have the following attributes:

| Attribute | Description                                     | Required |
|-----------|-------------------------------------------------|----------|
| `enableSystemAssertions` | Enable/disable system assertions. | No; defaults to `false` |

Nested elements for `<assertions>`, `<enableassertions>`, and `<disableassertions>`:

*   `<package name="my.package"/>` (to enable/disable assertions for a package)
*   `<class name="my.Class"/>` (to enable/disable assertions for a class)



## Examples

### Run a class in this JVM with a new jar on the classpath

```xml
<java classname="test.Main">
  <arg value="-h"/>
  <classpath>
    <pathelement location="dist/test.jar"/>
    <pathelement path="${java.class.path}"/>
  </classpath>
</java>
```

### Run the JAR test.jar, forking, with max memory, and fail on error

```xml
<java jar="dist/test.jar"
      fork="true"
      failonerror="true"
      maxmemory="128m">
  <arg value="-h"/>
  <classpath>
    <pathelement location="dist/test.jar"/>
    <pathelement path="${java.class.path}"/>
  </classpath>
</java>
```

### Run a JAR relative to a specific directory

```xml
<java dir="${exec.dir}"
      jar="${exec.dir}/dist/test.jar"
      fork="true"
      failonerror="true"
      maxmemory="128m">
  <arg value="-h"/>
  <classpath>
    <pathelement location="dist/test.jar"/>
    <pathelement path="${java.class.path}"/>
  </classpath>
</java>
```

### Run a given class with the current classpath

```xml
<java classname="test.Main"/>
```

### Add system properties and JVM arguments

```xml
<java classname="test.Main"
      fork="yes" >
  <sysproperty key="DEBUG" value="true"/>
  <arg value="-h"/>
  <jvmarg value="-Xrunhprof:cpu=samples,file=log.txt,depth=3"/>
</java>
```

### Use a specific JVM implementation

```xml
<java classname="ShowJavaVersion" classpath="."
      jvm="path-to-java14-home/bin/java" fork="true"
      taskname="java1.4"/>
```

### Run a module

```xml
<java fork="true"
      failonerror="true"
      maxmemory="128m"
      module="TestModule"
      modulepath="lib:dist/test.jar"/>
```

### Run a class in a module

```xml
<java fork="true"
      failonerror="true"
      maxmemory="128m"
      module="TestModule"
      classname="Main">
  <modulepath>
    <pathelement location="lib"/>
    <pathelement location="dist/test.jar"/>
  </modulepath>
</java>
```



## Groovy AntBuilder DSL Equivalent

To use the `java` Ant task within a Groovy script via `AntBuilder`, you invoke it as a method on your `AntBuilder` instance. The XML attributes translate to named method arguments, and nested elements often translate to closures or method calls within a primary closure.

### Key Parameters (and their Groovy equivalents):

| Ant Attribute        | Groovy Parameter Type | Description                                                                                                |
|----------------------|-----------------------|------------------------------------------------------------------------------------------------------------|
| `classname`          | `String`              | The Java class to execute.                                                                                 |
| `jar`                | `String` or `File`    | The JAR file to execute.                                                                                   |
| `module`             | `String`              | The Java module to execute.                                                                                |
| `args`               | `String`              | A string of command-line arguments (can also be done with nested `arg` elements).                          |
| `classpath`          | `String` or `Path`    | The classpath to use.                                                                                      |
| `classpathref`       | `String`              | Reference to a predefined classpath.                                                                       |
| `modulepath`         | `String` or `Path`    | The modulepath to use.                                                                                     |
| `modulepathref`      | `String`              | Reference to a predefined modulepath.                                                                      |
| `upgrademodulepath`  | `String` or `Path`    | Location of modules that replace upgradeable modules.                                                      |
| `fork`               | `boolean`             | If `true`, run in a separate JVM.                                                                          |
| `spawn`              | `boolean`             | If `true`, run in a separate JVM and do not wait.                                                          |
| `jvm`                | `String`              | Command to invoke the JVM.                                                                                 |
| `maxmemory`          | `String`              | JVM max memory (e.g., "128m").                                                                             |
| `jvmargs`            | `String`              | JVM arguments string (can also be done with nested `jvmarg` elements).                                     |
| `dir`                | `String` or `File`    | Working directory for the forked JVM.                                                                      |
| `output`             | `String` or `File`    | File to redirect standard output.                                                                          |
| `input`              | `String` or `File`    | File for standard input.                                                                                   |
| `inputstring`        | `String`              | String for standard input.                                                                                 |
| `error`              | `String` or `File`    | File to redirect standard error.                                                                           |
| `outputproperty`     | `String`              | Property to store standard output.                                                                         |
| `errorproperty`      | `String`              | Property to store standard error.                                                                          |
| `failonerror`        | `boolean`             | Stop build on non-zero return code.                                                                        |
| `resultproperty`     | `String`              | Property to store return code.                                                                             |
| `timeout`            | `long`                | Timeout in milliseconds.                                                                                   |
| `append`             | `boolean`             | Append output to existing files.                                                                           |
| `newenvironment`     | `boolean`             | Do not propagate old environment when forking.                                                             |
| `taskname`           | `String`              | Name for the task in log messages.                                                                         |
| `logError`           | `boolean`             | Send stderr output to Ant's logging.                                                                       |
| `addsourcefileextensions` | `boolean`        | Try to append .java and .class to classname.                                                               |

### Nested Elements in Groovy:

*   **`arg`**: Corresponds to the `<arg>` Ant element. Can be a closure or a list of maps/strings.
    ```groovy
    // Example with a closure
    arg { value("-h") }
    // Or directly as a map
    arg(value: "-Xmx512m")
    // Multiple args
    arg(value: "-Dname=value")
    arg(line: "-verbose -debug")
    ```
*   **`classpath`**: Corresponds to the `<classpath>` Ant element. Typically a closure where you define path elements.
    ```groovy
    classpath {
        pathelement(location: "dist/test.jar")
        pathelement(path: System.getProperty("java.class.path"))
    }
    ```
*   **`modulepath`**: Corresponds to the `<modulepath>` Ant element. Similar to `classpath`.
    ```groovy
    modulepath {
        pathelement(location: "lib")
        pathelement(location: "dist/test.jar")
    }
    ```
*   **`upgrademodulepath`**: Corresponds to the `<upgrademodulepath>` Ant element. Similar to `classpath`.
*   **`jvmarg`**: Corresponds to the `<jvmarg>` Ant element. Similar to `arg`.
    ```groovy
    jvmarg(value: "-Xmx256m")
    jvmarg(line: "-Dprop1=val1 -Dprop2=val2")
    ```
*   **`sysproperty`**: Corresponds to the `<sysproperty>` Ant element. Can be a closure or a list of maps.
    ```groovy
    sysproperty(key: "DEBUG", value: "true")
    // or within a closure for multiple
    sysproperty {
        property(key: "my.prop", value: "my.value")
    }
    ```
*   **`env`**: Corresponds to the `<env>` Ant element. Similar to `sysproperty`.
    ```groovy
    env(key: "MY_VAR", value: "my_value")
    ```
*   **`bootclasspath`**: Corresponds to the `<bootclasspath>` Ant element. Similar to `classpath`.
*   **`permissions`**: Corresponds to the `<permissions>` Ant element. This would involve a closure defining specific permissions.
*   **`assertions` (ea, da)**: Corresponds to `<assertions>`, `<enableassertions>`, `<disableassertions>`. These can be closures with nested `package` or `class` elements.
    ```groovy
    assertions {
        enableSystemAssertions(true)
        ea { // enableassertions
            package(name: "com.example.foo")
            class(name: "com.example.Bar")
        }
        da { // disableassertions
            package(name: "com.example.baz")
        }
    }
    ```



### Example Groovy DSL Usage:

Here are some examples of how to use the `java` task in your Groovy AntBuilder scripts, corresponding to common Ant XML usage patterns.

**1. Basic Class Execution**

*Ant XML:*
```xml
<project name="JavaTaskExample1" default="run">
    <target name="run">
        <java classname="com.example.Main" classpath="libs/myclasses.jar"/>
    </target>
</project>
```

*Groovy DSL:*
```groovy
ant.java(classname: "com.example.Main") {
    classpath {
        pathelement(location: "libs/myclasses.jar")
    }
}
```

**2. Executing a Class with Arguments and Forking**

*Ant XML:*
```xml
<project name="JavaTaskExample2" default="run">
    <path id="run.cp">
        <pathelement location="libs/processor.jar"/>
        <pathelement path="${java.class.path}"/>
    </path>
    <target name="run">
        <java classname="com.example.Processor" fork="true">
            <arg value="input.txt"/>
            <arg line="--verbose -o output.txt"/>
            <classpath refid="run.cp"/>
        </java>
    </target>
</project>
```

*Groovy DSL:*
```groovy
ant.path(id: "run.cp") {
    pathelement(location: "libs/processor.jar")
    pathelement(path: System.getProperty("java.class.path"))
}

ant.java(classname: "com.example.Processor", fork: true) {
    arg(value: "input.txt")
    arg(line: "--verbose -o output.txt")
    classpath(refid: "run.cp")
}
```

**3. Setting System Properties, JVM Arguments, and Output Redirection**

*Ant XML:*
```xml
<project name="JavaTaskExample3" default="run">
    <target name="run">
        <java classname="com.example.Server" fork="true" maxmemory="512m" output="server.log" errorproperty="server.error">
            <jvmarg value="-Xms128m"/>
            <jvmarg value="-Djava.rmi.server.hostname=localhost"/>
            <sysproperty key="server.port" value="9090"/>
            <sysproperty key="config.file" path="conf/server.properties"/>
            <classpath>
                <pathelement location="dist/server.jar"/>
            </classpath>
        </java>
    </target>
</project>
```

*Groovy DSL:*
```groovy
ant.java(classname: "com.example.Server", 
         fork: true, 
         maxmemory: "512m", 
         output: "server.log", 
         errorproperty: "server.error") {
    jvmarg(value: "-Xms128m")
    jvmarg(value: "-Djava.rmi.server.hostname=localhost")
    sysproperty(key: "server.port", value: "9090")
    sysproperty(key: "config.file", path: "conf/server.properties")
    classpath {
        pathelement(location: "dist/server.jar")
    }
}
```

**4. Running a JAR file**

*Ant XML:*
```xml
<project name="JavaTaskExample4" default="run">
    <target name="run">
        <java jar="dist/MyApplication.jar" fork="true">
            <arg value="--mode=production"/>
        </java>
    </target>
</project>
```

*Groovy DSL:*
```groovy
ant.java(jar: "dist/MyApplication.jar", fork: true) {
    arg(value: "--mode=production")
}
```

These examples illustrate the direct translation of common `java` task configurations from XML to Groovy DSL, showcasing the conciseness and readability of the Groovy syntax while maintaining the full power of the Ant task.

