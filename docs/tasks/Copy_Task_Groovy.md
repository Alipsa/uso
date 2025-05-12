## Copy Task (Groovy DSL)

### Description

The `copy` task is fundamental for copying files and resource collections from a source to a destination. It can copy individual files, entire directory structures (via filesets), or other resource collections. By default, it only copies if the source is newer than the destination or if the destination doesn't exist, but this behavior can be controlled.

In Groovy AntBuilder, the `copy` task is invoked as `ant.copy(...)`. It supports various attributes and nested elements to control its behavior, such as filesets for specifying source files, mappers for filename transformations, and filtersets for token replacement.

### Parameters

Common Ant attributes for the `copy` task and their Groovy AntBuilder DSL mapping:

*   `file`: The single source file to copy.
    *   Groovy: `file: "path/to/source/file.txt"`
*   `tofile`: The destination file path when copying a single source file.
    *   Groovy: `tofile: "path/to/destination/copy.txt"`
*   `todir`: The destination directory. Required when copying multiple files (e.g., from a fileset) or when copying a single file to a directory (preserving its name).
    *   Groovy: `todir: "path/to/destination_directory"`
*   `preservelastmodified`: (Boolean) If `true`, the copied files will retain the last modified timestamp of their source files. Defaults to `false`.
    *   Groovy: `preservelastmodified: true`
*   `overwrite`: (Boolean) If `true`, existing destination files will be overwritten even if they are newer than the source files. Defaults to `false`.
    *   Groovy: `overwrite: true`
*   `force`: (Boolean) If `true`, overwrites read-only destination files. _Since Ant 1.8.2_. Defaults to `false`.
    *   Groovy: `force: true`
*   `flatten`: (Boolean) If `true`, the directory structure of the source files is ignored, and all files are copied directly into the `todir`. Defaults to `false`.
    *   Groovy: `flatten: true`
*   `includeEmptyDirs`: (Boolean) If `true` (the default), empty directories from the source fileset(s) are created in the destination.
    *   Groovy: `includeEmptyDirs: false`
*   `filtering`: (Boolean) If `true`, token filtering using global Ant project filters is applied. Defaults to `false`. Nested `<filterset>` elements are always applied regardless of this attribute.
    *   Groovy: `filtering: true`
*   `encoding`: The character encoding to assume when copying files with filtering. Defaults to the JVM default.
    *   Groovy: `encoding: "UTF-8"`
*   `outputencoding`: The character encoding to use when writing the destination files (if filtering is involved). Defaults to the `encoding` attribute or JVM default.
    *   Groovy: `outputencoding: "ISO-8859-1"`
*   `granularity`: (Long) Milliseconds leeway for timestamp comparisons. Useful if filesystems have different timestamp precision or clocks are out of sync. Defaults to 1 second (or 2 seconds on DOS-based systems).
    *   Groovy: `granularity: 0` (for exact match)
*   `failonerror`: (Boolean) If `true` (default), the build fails if an error occurs (e.g., source file not found). If `false`, logs a warning and continues.
    *   Groovy: `failonerror: false`
*   `verbose`: (Boolean) If `true`, logs the files being copied. Defaults to `false`.
    *   Groovy: `verbose: true`
*   `quiet`: (Boolean) If `true` and `failonerror` is `false`, suppresses warning messages for non-fatal errors. _Since Ant 1.8.3_. Defaults to `false`.
    *   Groovy: `quiet: true`
*   `enablemultiplemappings`: (Boolean) If `true` and a mapper is used that can produce multiple target filenames for a single source, all mappings are processed. Defaults to `false` (only the first mapping is used).
    *   Groovy: `enablemultiplemappings: true`

### Nested Elements

*   **Resource Collections (e.g., `fileset`, `filelist`, `path`, `resources`)**: Used to specify the source files/resources to copy. `todir` is required when using resource collections.
    ```groovy
    ant.copy(todir: "backup") {
        fileset(dir: "src") {
            include(name: "**/*.java")
            exclude(name: "**/*Test.java")
        }
    }
    ```
*   **`mapper`**: Defines filename transformations. Common mappers include `identity` (default), `flatten`, `glob`, `regexp`, `package`.
    ```groovy
    ant.copy(todir: "build/classes-renamed") {
        fileset(dir: "build/classes", includes: "**/*.class")
        mapper(type: "glob", from: "*.class", to: "*.clazz")
    }
    ```
*   **`filterset`**: Replaces tokens in files during the copy operation.
    ```groovy
    ant.copy(todir: "dist", filtering: true) {
        fileset(dir: "src_templates")
        filterset {
            filter(token: "VERSION", value: "1.0.0")
            filter(token: "BUILD_DATE", value: ant.project.getProperty("TODAY"))
        }
    }
    ```
*   **`filterchain`**: Provides more complex filtering capabilities by chaining multiple filter readers.
    ```groovy
    ant.copy(todir: "processed_docs") {
        fileset(dir: "raw_docs")
        filterchain {
            linecontainsregexp {
                regexp(pattern: "^ERROR:")
            }
            replacestring(from: "ERROR:", to: "Warning:")
        }
    }
    ```

### Examples

1.  **Copy a single file to a new name:**

    *   **Ant XML:**
        ```xml
        <copy file="source.txt" tofile="destination.txt"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.copy(file: "source.txt", tofile: "destination.txt")
        ```

2.  **Copy a directory structure to a new location:**

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

3.  **Copy Java files, flattening the structure, and preserving timestamps:**

    *   **Ant XML:**
        ```xml
        <copy todir="target/flatjava" flatten="true" preservelastmodified="true">
            <fileset dir="src/main/java" includes="**/*.java"/>
        </copy>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.copy(todir: "target/flatjava", flatten: true, preservelastmodified: true) {
            fileset(dir: "src/main/java", includes: "**/*.java")
        }
        ```

4.  **Copy files and replace tokens:**

    *   **Ant XML:**
        ```xml
        <filter token="version" value="2.1"/>
        <copy todir="dist/conf" filtering="true">
            <fileset dir="src/conf" includes="app.properties"/>
        </copy>
        ```
    *   **Groovy AntBuilder (assuming global filter was set or using nested filterset):**
        ```groovy
        // For global filter effect, it would be set on ant.project.getGlobalFilterSet()
        // More commonly with AntBuilder, use nested filterset for clarity:
        ant.copy(todir: "dist/conf") {
            fileset(dir: "src/conf", includes: "app.properties")
            filterset {
                filter(token: "version", value: "2.1")
            }
        }
        ```

### Important Considerations

*   **Binary Files and Filtering**: If filtering is active (either via the `filtering` attribute or nested `filterset`/`filterchain`), copying binary files can corrupt them. Ensure filtering is only applied to text files.
*   **Resource Collections and `todir`**: When using any resource collection (like `fileset`), the `todir` attribute is mandatory.
*   **Absolute Paths in Resources**: Some resources might return absolute paths. Using `flatten=
