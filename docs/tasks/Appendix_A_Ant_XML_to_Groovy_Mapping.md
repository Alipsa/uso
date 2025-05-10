## Appendix A: Ant XML to Groovy AntBuilder DSL Mapping

This document provides a mapping from common Apache Ant XML task syntax to the equivalent Groovy AntBuilder DSL.

### General Principles

- Ant tasks become method calls on the `ant` builder instance (e.g., `ant.echo()`, `ant.copy()`).
- Attributes of Ant tasks become named arguments in Groovy (e.g., `message:"Hello"` for `<echo message="Hello"/>`).
- Nested elements in Ant tasks are often represented as closures in Groovy, where the nested task also becomes a method call.
- Text content within an Ant task (like for `<echo>`) can often be passed as the first argument to the Groovy method if no other attributes are specified, or as a named `message` argument.

### Common Task Mappings

1.  **Echo Task**
    *   **Ant XML:**
        ```xml
        <echo message="Hello, World!"/>
        <echo>Another message</echo>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.echo(message: "Hello, World!")
        ant.echo("Another message")
        // Or for more complex messages or levels:
        ant.echo(message: "A warning message", level: "warning")
        ```

2.  **Mkdir Task**
    *   **Ant XML:**
        ```xml
        <mkdir dir="build/classes"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.mkdir(dir: "build/classes")
        ```

3.  **Copy Task**
    *   **Simple File Copy:**
        *   **Ant XML:**
            ```xml
            <copy file="source.txt" tofile="destination.txt"/>
            ```
        *   **Groovy AntBuilder:**
            ```groovy
            ant.copy(file: "source.txt", tofile: "destination.txt")
            ```
    *   **Copying a Directory:**
        *   **Ant XML:**
            ```xml
            <copy todir="backup">
                <fileset dir="src"/>
            </copy>
            ```
        *   **Groovy AntBuilder:**
            ```groovy
            ant.copy(todir: "backup") {
                fileset(dir: "src")
            }
            ```
    *   **Copying with filtering:**
        *   **Ant XML:**
            ```xml
            <copy todir="../backup/dir-example">
                <fileset dir="src_dir">
                    <include name="**/*.java"/>
                    <exclude name="**/*Test*"/>
                </fileset>
            </copy>
            ```
        *   **Groovy AntBuilder:**
            ```groovy
            ant.copy(todir: '../backup/dir-example') {
                fileset(dir: 'src_dir') {
                    include(name: '**/*.java')
                    exclude(name: '**/*Test*')
                }
            }
            ```

4.  **Delete Task**
    *   **Deleting a File:**
        *   **Ant XML:**
            ```xml
            <delete file="temp.txt"/>
            ```
        *   **Groovy AntBuilder:**
            ```groovy
            ant.delete(file: "temp.txt")
            ```
    *   **Deleting a Directory:**
        *   **Ant XML:**
            ```xml
            <delete dir="build_temp"/>
            ```
        *   **Groovy AntBuilder:**
            ```groovy
            ant.delete(dir: "build_temp")
            ```
    *   **Deleting with Fileset:**
        *   **Ant XML:**
            ```xml
            <delete>
                <fileset dir="." includes="**/*.bak"/>
            </delete>
            ```
        *   **Groovy AntBuilder:**
            ```groovy
            ant.delete {
                fileset(dir: ".", includes: "**/*.bak")
            }
            ```

5.  **Zip Task**
    *   **Ant XML:**
        ```xml
        <zip destfile="archive.zip" basedir="build"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.zip(destfile: "archive.zip", basedir: "build")
        ```
    *   **Zip with Fileset:**
        *   **Ant XML:**
            ```xml
            <zip destfile="myarchive.zip">
                <fileset dir="src" includes="**/*.java"/>
                <fileset dir="resources" includes="**/*.properties"/>
            </zip>
            ```
        *   **Groovy AntBuilder:**
            ```groovy
            ant.zip(destfile: "myarchive.zip") {
                fileset(dir: "src", includes: "**/*.java")
                fileset(dir: "resources", includes: "**/*.properties")
            }
            ```

6.  **Javac Task**
    *   **Ant XML:**
        ```xml
        <javac srcdir="src" destdir="build/classes" classpath="lib/somelib.jar" debug="on"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.javac(srcdir: "src", 
                  destdir: "build/classes", 
                  classpath: "lib/somelib.jar", 
                  debug: "on")
        // For more complex classpath or nested elements:
        ant.javac(srcdir: 'src', destdir: 'build/classes') {
            classpath {
                pathelement(location: 'lib/somelib.jar')
                pathelement(path: System.getProperty('java.class.path'))
            }
            include(name: 'com/example/**/*.java')
            exclude(name: 'com/example/test/**')
        }
        ```

7.  **Java Task**
    *   **Ant XML:**
        ```xml
        <java classname="com.example.Main" classpath="build/classes">
            <arg value="-h"/>
            <jvmarg value="-Xmx512m"/>
            <sysproperty key="my.prop" value="myValue"/>
        </java>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.java(classname: "com.example.Main", classpath: "build/classes", fork: true) {
            arg(value: "-h")
            jvmarg(value: "-Xmx512m")
            sysproperty(key: "my.prop", value: "myValue")
        }
        ```

This list is not exhaustive but covers many common use cases. The general pattern of converting tags to methods and attributes to named parameters, with nested tags becoming closures, applies broadly.

### Navigation

*   [Previous: Fileset Data Type](12-Ant_DataTypes_Fileset_Groovy.md)
*   [Table of Contents](00-Introduction_Groovy_Ant_Manual.md)
