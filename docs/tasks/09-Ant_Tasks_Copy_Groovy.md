## Ant Tasks: Copy (Groovy DSL)

The `copy` task in Ant is used for copying files and directories. It's a versatile task with many options for controlling what gets copied and where. Groovy AntBuilder provides a clean syntax for this task.

### Copying a Single File

*   **Ant XML:**
    ```xml
    <copy file="source.txt" tofile="destination.txt"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.copy(file: "source.txt", tofile: "destination.txt")
    ```

### Copying a File to a Directory

*   **Ant XML:**
    ```xml
    <copy file="source.txt" todir="../backup"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.copy(file: "source.txt", todir: "../backup")
    ```

### Copying a Directory

To copy an entire directory and its contents:

*   **Ant XML:**
    ```xml
    <copy todir="../backup/src_backup">
        <fileset dir="src"/>
    </copy>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.copy(todir: "../backup/src_backup") {
        fileset(dir: "src")
    }
    ```

### Copying Files using Filesets with Include/Exclude Patterns

Filesets provide powerful control over which files are copied.

*   **Ant XML:**
    ```xml
    <copy todir="build/dist">
        <fileset dir="src">
            <include name="**/*.java"/>
            <exclude name="**/*Test*"/>
        </fileset>
        <fileset dir="resources" includes="**/*.properties"/>
    </copy>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.copy(todir: "build/dist") {
        fileset(dir: "src") {
            include(name: "**/*.java")
            exclude(name: "**/*Test*")
        }
        fileset(dir: "resources", includes: "**/*.properties")
    }
    ```

### Navigation

*   [Previous: Delete Task](08-Ant_Tasks_Delete_Groovy.md)
*   [Next: Move Task](10-Ant_Tasks_Move_Groovy.md)
*   [Table of Contents](00-Introduction_Groovy_Ant_Manual.md)
