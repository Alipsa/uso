## Ant Tasks: Move (Groovy DSL)

The `move` task in Ant is used for moving files and directories from one location to another. It can move single files, entire directories, or collections of files defined by filesets. Groovy AntBuilder provides a clean syntax for this task.

### Moving a Single File

*   **Ant XML:**
    ```xml
    <move file="source.txt" tofile="destination.txt"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.move(file: "source.txt", tofile: "destination.txt")
    ```

### Moving a File to a Directory

*   **Ant XML:**
    ```xml
    <move file="image.png" todir="../images_archive"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.move(file: "image.png", todir: "../images_archive")
    ```

### Moving a Directory

To move an entire directory and its contents:

*   **Ant XML:**
    ```xml
    <move todir="../backup/old_src">
        <fileset dir="src_to_move"/>
    </move>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.move(todir: "../backup/old_src") {
        fileset(dir: "src_to_move")
    }
    ```
    *Note: If `todir` is an existing directory, the `src_to_move` directory will be moved inside it. If `todir` does not exist, `src_to_move` will be renamed to `old_src` (in this example).* 

### Moving Files using Filesets with Include/Exclude Patterns

Filesets allow for precise control over which files are moved.

*   **Ant XML:**
    ```xml
    <move todir="processed_files">
        <fileset dir="incoming_data">
            <include name="**/*.xml"/>
            <exclude name="**/temp_*"/>
        </fileset>
    </move>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.move(todir: "processed_files") {
        fileset(dir: "incoming_data") {
            include(name: "**/*.xml")
            exclude(name: "**/temp_*")
        }
    }
    ```

### Overwriting Files

By default, the `move` task will not overwrite existing files in the destination. You can change this behavior.

*   **Ant XML:**
    ```xml
    <move file="config.properties" tofile="old_config.properties" overwrite="true"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.move(file: "config.properties", tofile: "old_config.properties", overwrite: true)
    ```

### Using Mappers to Rename Files on Move

Similar to the `copy` task, `move` can use mappers to rename files during the operation.

*   **Ant XML:**
    ```xml
    <move todir="renamed_logs">
        <fileset dir="current_logs"/>
        <mapper type="glob" from="*.log" to="*.log.processed"/>
    </move>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.move(todir: "renamed_logs") {
        fileset(dir: "current_logs")
        mapper(type: "glob", from: "*.log", to: "*.log.processed")
    }
    ```

These examples cover the typical uses of the `move` task in Ant and their translations to Groovy AntBuilder DSL. The Groovy syntax simplifies the build script while retaining the functionality of the Ant task.

### Navigation

*   [Previous: Copy Task](09-Ant_Tasks_Copy_Groovy.md)
*   [Next: Exec Task](11-Ant_Tasks_Exec_Groovy.md)
*   [Table of Contents](00-Introduction_Groovy_Ant_Manual.md)
