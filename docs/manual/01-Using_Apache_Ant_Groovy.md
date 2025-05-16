## Using Apache Ant with Groovy DSL

This section explains how to use Apache Ant with Groovy DSL. Instead of XML build files, you can now leverage the power and conciseness of Groovy scripts.

For example, a simple task execution would look like this in your Groovy script:

```groovy
import groovy.ant.AntBuilder
def ant = new AntBuilder()
// Example: Echoing a message
ant.echo("Hello World!")
```

However, if you have multiple, consecutive tasks, you can use the `with` method to group them together:
```groovy
// build.groovy

new AntBuilder().with {

  // Example: Echoing a message
  echo("Compiling the project...")

  // Example: Compiling Java source files
  javac(srcdir: 'src', destdir: 'build/classes') {
    compilerarg value: "-Xlint"
  }

  // Example: Creating a JAR file, if we are not using a closure, paranthesis is not needed
  echo message: "Creating the JAR..."
  jar destfile: 'build/myproject.jar', basedir: 'build/classes'

  echo "Build finished."
}
```

To run this, you would typically use Groovy to execute the script.

The Uso ProjectBuilder, which is an extension of AntBuilder, can be used in exactly the same way. In an Uso build, the ProjectBuilder instance is injected into the session with the name project so hence, an Uso build script typically begins with
```groovy 
project.with {
  // target definitions and top level tasks here
}
```
The difference is that the Uso ProjectBuilder behaves much more like a traditional Ant build i.e. it supports the definition of targets that can be chained together using `depends: 'someOtherTarget'` leading to a two-phase execution:
0. The ProjectBuilder instance is created and additional Ant tasks and types are registered
1. The project is injected into the script session and the build script is evaluated (compiled)
2. The targets that was given as parameters to the Uso script are executed in the order they were provided, or if no target(s) were specified, the default target is executed.

This does not change any of syntax or semantics of the AntBuilder DSL, but it does allow you to use the same build script in both AntBuilder and Uso. The only difference is that in Uso, you can define targets and use them to chain tasks together whereas in AntBuilder tasks are executed immediately.

### General Principles

- Ant tasks become method calls on the `ant` builder instance. E.g., `ant.echo()` or 
  ```groovy
  ant.with{ 
    echo()
    copy()
  }
  ```
- Attributes of Ant tasks become named arguments in Groovy (e.g., `message:"Hello"` for `<echo message="Hello"/>`).
- Nested elements in Ant tasks are represented as closures in Groovy, where the nested task also becomes a method call.
- Text content within an Ant task (like for `<echo>`) can often be passed as the first argument to the Groovy method if no other attributes are specified, or as a named `message` argument.

## Common Task Mappings

### Echo Task
*Ant XML:*
```xml
<echo message="Hello, World!"/>
<echo>Another message</echo>
```
*Groovy AntBuilder:*
```groovy
ant.with {
  echo message: "Hello, World!"
  echo "Another message"
  // Or for more complex messages or levels:
  echo message: "A warning message", level: "warning"
}
```

### Mkdir Task
*Ant XML:*
```xml
<mkdir dir="build/classes"/>
```
*Groovy AntBuilder:*
```groovy
ant.mkdir dir: "build/classes"
```

### Copy Task
#### Simple File Copy:
*Ant XML:*
```xml
<copy file="source.txt" tofile="destination.txt"/>
```
*Groovy AntBuilder:*
```groovy
ant.copy file: "source.txt", tofile: "destination.txt"
```
#### Copying a Directory:
*Ant XML:*
```xml
<copy todir="backup">
  <fileset dir="src"/>
</copy>
```
*Groovy AntBuilder:*
```groovy
ant.copy(todir: "backup") {
  fileset dir: "src"
}
```
#### Copying with filtering:
*Ant XML:*
```xml
<copy todir="../backup/dir-example">
  <fileset dir="src_dir">
    <include name="**/*.java"/>
    <exclude name="**/*Test*"/>
  </fileset>
</copy>
```
*Groovy AntBuilder:*
```groovy
ant.copy(todir: '../backup/dir-example') {
  fileset(dir: 'src_dir') {
    include name: '**/*.java'
    exclude name: '**/*Test*'
  }
}
```

### Delete Task
#### Deleting a File:
*Ant XML:*
```xml 
<delete file="temp.txt"/>
```
*Groovy AntBuilder:*
```groovy
ant.delete file: "temp.txt"
```
#### Deleting a Directory:
*Ant XML:*
```xml
<delete dir="build_temp"/>
```
*Groovy AntBuilder:*
```groovy
ant.delete dir: "build_temp"
```
#### Deleting with Fileset:
*Ant XML:*
```xml
<delete>
  <fileset dir="." includes="**/*.bak"/>
</delete>
```
*Groovy AntBuilder:*
```groovy
ant.delete {
  fileset dir: ".", includes: "**/*.bak"
}
```

### Zip Task
*Ant XML:*
```xml
<zip destfile="archive.zip" basedir="build"/>
```
*Groovy AntBuilder:*
```groovy
ant.zip destfile: "archive.zip", basedir: "build"
```
#### Zip with Fileset:
*Ant XML:*
```xml
<zip destfile="myarchive.zip">
  <fileset dir="src" includes="**/*.java"/>
  <fileset dir="resources" includes="**/*.properties"/>
</zip>
```
*Groovy AntBuilder:*
```groovy
ant.zip(destfile: "myarchive.zip") {
  fileset dir: "src", includes: "**/*.java"
  fileset dir: "resources", includes: "**/*.properties"
}
```

### Javac Task
*Ant XML:*
```xml
<javac srcdir="src" destdir="build/classes" classpath="lib/somelib.jar" debug="on"/>
```
*Groovy AntBuilder:*
```groovy
ant.javac srcdir: "src", 
          destdir: "build/classes", 
          classpath: "lib/somelib.jar", 
          debug: "on"
// For more complex classpath or nested elements:
ant.javac(srcdir: 'src', destdir: 'build/classes') {
  classpath {
    pathelement location: 'lib/somelib.jar'
    pathelement path: System.getProperty('java.class.path')
  }
  include name: 'com/example/**/*.java'
  exclude name: 'com/example/test/**'
}
```

### Java Task
*Ant XML:*
```xml
<java classname="com.example.Main" classpath="build/classes">
    <arg value="-h"/>
    <jvmarg value="-Xmx512m"/>
    <sysproperty key="my.prop" value="myValue"/>
</java>
```
*Groovy AntBuilder:*
```groovy
ant.java(classname: "com.example.Main", classpath: "build/classes", fork: true) {
  arg value: "-h"
  jvmarg value: "-Xmx512m"
  sysproperty key: "my.prop", value: "myValue"
}
```

This list is not exhaustive but covers many common use cases. The general pattern of converting tags to methods and attributes to named parameters, with nested tags becoming closures, applies broadly.
### Navigation

*   [Next Task: ANTLR Task](ANTLR_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Tasks-Overview.md)
