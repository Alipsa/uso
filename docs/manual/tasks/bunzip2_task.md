## BUnzip2 Task (Groovy DSL)

### Description

The `bunzip2` task is used to decompress a file that has been compressed using the bzip2 algorithm. This task is typically used when dealing with `.bz2` files.

In Groovy AntBuilder, the `bunzip2` task can be invoked by calling `ant.bunzip2(...)`. The necessary attributes, such as the source file and destination, are passed as named parameters within the closure.

### Parameters

Common Ant attributes for the `bunzip2` task and their Groovy AntBuilder DSL mapping:

*   `src`: The source file to be decompressed (e.g., `archive.bz2`). This is a required parameter.
    *   Groovy: `src: 'path/to/your/archive.bz2'`
*   `dest`: The destination file or directory where the decompressed content should be placed. If `dest` is a directory, the decompressed file will be placed in that directory with its original name (minus the .bz2 extension if that's how it was named). If `dest` is a file, the decompressed content will be written to that file.
    *   Groovy: `dest: 'path/to/your/output_location/'` or `dest: 'path/to/your/output_file.ext'`
*   `overwrite`: (Boolean, Optional) Whether to overwrite the destination file if it already exists. Defaults to `true`. If set to `false` and the destination exists, the task might fail or skip decompression for that file.
    *   Groovy: `overwrite: false` (Example: do not overwrite)

### Nested Elements

The `bunzip2` task typically does not involve complex nested elements for its basic operation. It primarily works with source and destination attributes.

However, like many Ant tasks, it can be part of a larger structure involving filesets or mappers if you are processing multiple bzip2 files. For a single file decompression, these are usually not needed directly within the `bunzip2` call itself in Groovy.

### Examples

1.  **Decompressing a specific .bz2 file to a specific location:**

    ```groovy
    bunzip2(src: 'archive.bz2', dest: 'output_directory/')
    // This will create output_directory/archive (if archive.bz2 contained 'archive')
    ```

2.  **Decompressing a .bz2 file and renaming the output:**

    ```groovy
    bunzip2(src: 'data.file.bz2', dest: 'extracted_data.txt')
    // This will create extracted_data.txt with the contents of data.file.bz2
    ```

3.  **Decompressing with overwrite disabled (conceptual, assuming a pre-existing file at dest):**

    ```groovy
    // Assuming 'unpacked_file' already exists
    bunzip2(src: 'latest_update.bz2', dest: 'unpacked_file', overwrite: false)
    // If overwrite is false and 'unpacked_file' exists, the task might skip or error
    // depending on Ant's default behavior for this specific scenario (usually skip/fail).
    ```

### Important Considerations

*   Ensure that the bzip2 program is available in the system's PATH if Ant's native implementation is not used or if there are specific version requirements handled by an external tool (though `bunzip2` is a core Ant task and typically uses Java libraries).
*   The source file must exist and be a valid bzip2 compressed file.
*   The destination path must be writable.
*   If `dest` is a directory, it must exist, or you might need to create it first using a task like `mkdir`.

### Navigation

*   [Previous Task: Available Task](Available_Task_Groovy.md)
*   [Next Task: BZip2 Task](BZip2_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
