## Ant Tasks: Jar (Groovy DSL)

The `jar` task in Ant is used to create JAR (Java Archive) files. This task is essential for packaging Java applications and libraries. Groovy AntBuilder provides a clean and intuitive way to use the `jar` task.

### Basic Jar Creation

*   **Ant XML:**
    ```xml
    <jar destfile="build/myproject.jar" basedir="build/classes"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.jar(destfile: "build/myproject.jar", basedir: "build/classes")
    ```

### Jarring Specific Files using Filesets

Often, you need more control over what goes into the JAR file. This is typically done using nested `fileset` elements.

*   **Ant XML:**
    ```xml
    <jar destfile="dist/myapp.jar">
        <fileset dir="build/classes" includes="com/example/**" excludes="**/*Test.class"/>
        <fileset dir="resources" includes="**/*.properties"/>
        <manifest>
            <attribute name="Main-Class" value="com.example.Main"/>
        </manifest>
    </jar>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.jar(destfile: "dist/myapp.jar") {
        fileset(dir: "build/classes") {
            include(name: "com/example/**")
            exclude(name: "**/*Test.class")
        }
        fileset(dir: "resources", includes: "**/*.properties")
        manifest {
            attribute(name: "Main-Class", value: "com.example.Main")
        }
    }
    ```

### Adding a Manifest

As seen in the example above, the `manifest` task can be nested within the `jar` task to specify manifest attributes like the Main-Class.

*   **Ant XML (Manifest from file):**
    ```xml
    <jar destfile="final.jar" manifest="src/manifest.mf">
        <fileset dir="classes"/>
    </jar>
    ```
*   **Groovy AntBuilder (Manifest from file):**
    ```groovy
    ant.jar(destfile: "final.jar", manifest: "src/manifest.mf") {
        fileset(dir: "classes")
    }
    ```

### Updating an Existing Jar

*   **Ant XML:**
    ```xml
    <jar destfile="existing.jar" update="true">
        <fileset dir="newfiles"/>
    </jar>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.jar(destfile: "existing.jar", update: true) {
        fileset(dir: "newfiles")
    }
    ```

These examples illustrate how the `jar` task, along with its common attributes and nested elements like `fileset` and `manifest`, can be translated into the Groovy AntBuilder DSL. The Groovy syntax often results in more readable and concise build scripts.

### Navigation

*   [Previous: Javac Task](06-Ant_Tasks_Javac_Groovy.md)
*   [Next: Delete Task](08-Ant_Tasks_Delete_Groovy.md)
*   [Table of Contents](00-Introduction_Groovy_Ant_Manual.md)
