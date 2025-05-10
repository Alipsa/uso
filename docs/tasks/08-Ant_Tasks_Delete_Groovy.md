## Ant Tasks: Delete (Groovy DSL)

The `delete` task in Ant is used to remove files or directories. This is a common operation in build scripts for cleaning up temporary files, build artifacts, or old distributions. Groovy AntBuilder provides a simple way to perform these operations.

### Deleting a Single File

*   **Ant XML:**
    ```xml
    <delete file="path/to/your/file.tmp"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.delete(file: "path/to/your/file.tmp")
    ```

### Deleting an Empty Directory

*   **Ant XML:**
    ```xml
    <delete dir="path/to/empty_dir"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.delete(dir: "path/to/empty_dir")
    ```
    *Note: By default, `delete dir` will only remove the directory if it is empty. To delete a directory and its contents, see below.* 

### Deleting a Directory and its Contents (Recursive Delete)

*   **Ant XML:**
    ```xml
    <delete dir="path/to/dir_with_contents" includeEmptyDirs="true"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    // To delete a directory and all its contents:
    ant.delete(dir: "path/to/dir_with_contents", includeEmptyDirs: true)
    ```

### Deleting Files using a Fileset

For more complex deletion patterns, such as deleting all files with a certain extension or in a specific directory structure, you use a nested `fileset`.

*   **Ant XML:**
    ```xml
    <delete>
        <fileset dir="build/temp" includes="**/*.tmp"/>
        <fileset dir="logs" includes="*.log" defaultexcludes="no"/>
    </delete>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.delete {
        fileset(dir: "build/temp", includes: "**/*.tmp")
        fileset(dir: "logs", includes: "*.log", defaultexcludes: false) // Note: defaultexcludes="no" becomes defaultexcludes: false
    }
    ```

### Controlling Behavior on Failure

*   **Ant XML:**
    ```xml
    <delete file="non_existent_file.txt" quiet="true"/>
    <delete file="another_file.txt" failonerror="false"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    // Suppress error message if file doesn't exist
    ant.delete(file: "non_existent_file.txt", quiet: true)

    // Continue build even if deletion fails
    ant.delete(file: "another_file.txt", failonerror: false)
    ```

These examples cover the primary uses of the `delete` task in Ant and their corresponding Groovy AntBuilder DSL syntax. The Groovy approach maintains clarity while offering the full power of Ant's deletion capabilities.

### Navigation

*   [Previous: Jar Task](07-Ant_Tasks_Jar_Groovy.md)
*   [Next: Copy Task](09-Ant_Tasks_Copy_Groovy.md)
*   [Table of Contents](00-Introduction_Groovy_Ant_Manual.md)
