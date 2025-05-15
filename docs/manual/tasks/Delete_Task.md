## Delete Task (Groovy DSL)

### Description

The `delete` task in Ant is used to delete a single file, an empty directory, or a set of files and directories specified by one or more nested resource collections (like filesets). By default, it does not follow symbolic links.

In Groovy AntBuilder, the `delete` task is invoked as `ant.delete(...)`. It supports various attributes to control its behavior, such as deleting specific files, directories, or files matching certain patterns.

### Parameters

Common Ant attributes for the `delete` task and their Groovy AntBuilder DSL mapping:

*   `file`: The specific file to delete. If the specified file is a directory, it must be empty for this attribute to delete it. For non-empty directories, use a nested fileset or dirset.
    *   Groovy: `file: "path/to/file_to_delete.txt"`
*   `dir`: The specific directory to delete. If this directory is not empty, the task will delete its entire contents (files and subdirectories) and then the directory itself. This is a key difference from using the `file` attribute on a directory.
    *   Groovy: `dir: "path/to/directory_to_delete_recursively"`
*   `verbose`: (Boolean) If `true`, prints a message for each file or directory deleted. Defaults to `false`.
    *   Groovy: `verbose: true`
*   `quiet`: (Boolean) If `true`, suppresses the "Unable to delete file" warning message if a file or directory cannot be deleted or does not exist. Setting `quiet="true"` implies `failonerror="false"`. Defaults to `false`.
    *   Groovy: `quiet: true`
*   `failonerror`: (Boolean) If `true` (the default), the build stops if a file or directory cannot be deleted. If `false`, errors are logged (unless `quiet` is also `true`), but the build continues.
    *   Groovy: `failonerror: false`
*   `includeEmptyDirs`: (Boolean) When using nested filesets, if `true`, empty directories matched by the fileset will also be deleted. Defaults to `false` for filesets (meaning empty dirs are not deleted by a fileset by default, though the `delete` task itself might remove them if they become empty after their contents are deleted).
    *   Groovy: `includeEmptyDirs: true` (when using a fileset)
*   `deleteonexit`: (Boolean) If `true`, and Ant fails to delete a file, it will ask the JVM to attempt deletion when the JVM exits. Defaults to `false`.
    *   Groovy: `deleteonexit: true`
*   `removeNotFollowedSymlinks`: (Boolean) If `true`, symbolic links that were not followed (due to `followSymlinks` being false on a fileset, or exceeding max link depth) will themselves be removed. Defaults to `false`.
    *   Groovy: `removeNotFollowedSymlinks: true`
*   `performGCOnFailedDelete`: (Boolean) If Ant fails to delete, setting this to `true` (default on Windows, `false` elsewhere unless NFS detected) will trigger a garbage collection before retrying. Can help with file locking issues.
    *   Groovy: `performGCOnFailedDelete: true`
*   `defaultexcludes`: (Boolean) When using nested filesets, this controls whether Ant's default excludes (like SCM directories `.svn`, `.git`, etc.) are applied. Defaults to `true`.
    *   Groovy: (within a fileset) `defaultexcludes: false`

### Nested Elements

*   **Resource Collections (e.g., `fileset`, `dirset`, `filelist`, `path`)**: Used to specify multiple files or directories to delete.
    ```groovy
    ant.delete {
        fileset(dir: "build/temp", includes: "**/*.tmp")
        dirset(dir: "old_logs", includes: "**/*", defaultexcludes: "false") // Delete all in old_logs, even .svn
    }
    ```

### Examples

1.  **Delete a single file:**

    *   **Ant XML:**
        ```xml
        <delete file="dist/myapp.jar"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.delete(file: "dist/myapp.jar")
        ```

2.  **Delete an empty directory (using `file` attribute):**

    *   **Ant XML:**
        ```xml
        <delete file="emptydir"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.delete(file: "emptydir")
        ```

3.  **Delete a directory and all its contents (using `dir` attribute):**

    *   **Ant XML:**
        ```xml
        <delete dir="build/output"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.delete(dir: "build/output")
        ```

4.  **Delete all `.tmp` files in a directory and its subdirectories:**

    *   **Ant XML:**
        ```xml
        <delete verbose="true">
            <fileset dir="temp_files" includes="**/*.tmp"/>
        </delete>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.delete(verbose: true) {
            fileset(dir: "temp_files", includes: "**/*.tmp")
        }
        ```

5.  **Delete all files and directories under `build`, including `build` itself, ensuring empty directories are also removed by the fileset processing:**

    *   **Ant XML:**
        ```xml
        <delete includeEmptyDirs="true">
            <fileset dir="build" defaultexcludes="false"/> <!-- defaultexcludes=false to ensure .git etc. are also targeted if present -->
        </delete>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        // Note: To delete the 'build' directory itself after its contents,
        // a separate ant.delete(dir: "build") might be more straightforward
        // after clearing its contents with a fileset.
        // The example below deletes contents *within* build based on fileset.
        // If 'build' itself needs to go, and it might be non-empty initially:
        ant.delete(dir: "build") // This is the most direct way to delete a dir and its contents.

        // If you wanted to use fileset to clear contents first, then delete the dir:
        ant.delete(includeEmptyDirs: true) {
            fileset(dir: "build", includes: "**/*", defaultexcludes: "false")
        }
        ant.delete(dir: "build", quiet: true) // Attempt to delete the now potentially empty 'build' dir
        ```

### Important Considerations

*   **`dir` vs. `fileset`**: `<delete dir="some_dir"/>` is powerful and will attempt to remove the directory and everything in it. `<delete><fileset dir="some_dir" includes="**/*"/></delete>` will delete files and potentially empty directories within `some_dir` based on `includeEmptyDirs`, but might not remove `some_dir` itself if it wasn't empty due to files not matched by the fileset (e.g. if `defaultexcludes` was true and `.git` was present).
*   **Permissions**: The user running Ant must have appropriate filesystem permissions to delete the targeted files and directories.
*   **Symbolic Links**: By default, `delete` does not follow symbolic links when processing filesets. The links themselves might be deleted if matched by a pattern.
*   **`defaultexcludes`**: Be aware of default excludes when using filesets if you intend to delete files like `.svn` or `.git` directories. You may need `defaultexcludes="false"` on the fileset.

### Navigation

*   [Previous Task: CvsChangeLog Task](CvsChangeLog_Task_Groovy.md)
*   [Next Task: Deltree Task](Deltree_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
