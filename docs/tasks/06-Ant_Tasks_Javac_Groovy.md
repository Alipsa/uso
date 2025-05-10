## Ant Tasks: Javac (Groovy DSL)

The `javac` task in Ant is used to compile Java source code. Groovy AntBuilder provides a straightforward way to invoke this task, mapping its attributes and nested elements to Groovy syntax.

### Basic Compilation

*   **Ant XML:**
    ```xml
    <javac srcdir="src" destdir="build/classes"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.javac(srcdir: 'src', destdir: 'build/classes')
    ```

### Compilation with Classpath and Debug Options

*   **Ant XML:**
    ```xml
    <javac srcdir="src" destdir="build/classes" classpath="lib/somelib.jar" debug="on"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.javac(srcdir: 'src', 
              destdir: 'build/classes', 
              classpath: 'lib/somelib.jar', 
              debug: 'on')
    ```

### Using Nested Elements (e.g., Classpath, Include, Exclude)

Nested elements in Ant tasks are typically handled using closures in Groovy AntBuilder.

*   **Ant XML:**
    ```xml
    <javac srcdir="src" destdir="build/classes">
        <classpath>
            <pathelement location="lib/somelib.jar"/>
            <pathelement path="${java.class.path}"/>
        </classpath>
        <include name="com/example/**/*.java"/>
        <exclude name="com/example/test/**"/>
    </javac>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.javac(srcdir: 'src', destdir: 'build/classes') {
        classpath {
            pathelement(location: 'lib/somelib.jar')
            pathelement(path: System.getProperty('java.class.path'))
        }
        include(name: 'com/example/**/*.java')
        exclude(name: 'com/example/test/**')
    }
    ```

### Setting Compiler Arguments

*   **Ant XML:**
    ```xml
    <javac srcdir="src" destdir="build/classes">
        <compilerarg value="-Xlint:unchecked"/>
    </javac>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.javac(srcdir: 'src', destdir: 'build/classes') {
        compilerarg(value: '-Xlint:unchecked')
    }
    ```

This demonstrates how the `javac` task and its various options can be effectively translated into Groovy AntBuilder DSL, providing a more concise and readable way to define compilation steps.

### Navigation

*   [Previous: Property Task](05-Ant_Tasks_Property_Groovy.md)
*   [Next: Jar Task](07-Ant_Tasks_Jar_Groovy.md)
*   [Table of Contents](00-Introduction_Groovy_Ant_Manual.md)
