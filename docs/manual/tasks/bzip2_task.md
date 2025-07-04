## BZip2 Task (Groovy DSL)

### Description

The `bzip2` task is used to compress a file using the BZip2 algorithm. The output file (typically with a `.bz2` extension) is generated. This task is often used to compress archive files like TAR files.

In Groovy AntBuilder, the `bzip2` task is invoked as `ant.bzip2(...)`. The source file to be compressed and the destination file for the compressed output are specified as named parameters.

### Parameters

Common Ant attributes for the `bzip2` task and their Groovy AntBuilder DSL mapping:

*   `src`: The source file to be compressed. This is required if no nested resource is provided.
    *   Groovy: `src: 'path/to/your/sourcefile.tar'`
*   `destfile`: The name of the destination file where the bzip2 compressed output will be saved. This is required.
    *   Groovy: `destfile: 'path/to/your/archive.tar.bz2'`
*   `zipfile`: A deprecated alias for `destfile`. It's recommended to use `destfile`.

### Nested Elements

*   **Single Element Resource Collection**: _Since Ant 1.7_, you can specify the source file as a nested resource collection (e.g., `<file file="source.txt"/>` or `<url url="http://example.com/data"/>`).
    ```groovy
    // Example with a nested <file> resource
    ant.bzip2(destfile: 'output.bz2') {
        file(file: 'input.txt') // Specifies the source file
    }
    ```

### Examples

1.  **Compressing a TAR file:**

    *   **Ant XML:**
        ```xml
        <bzip2 src="myarchive.tar" destfile="myarchive.tar.bz2"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.bzip2(src: 'myarchive.tar', destfile: 'myarchive.tar.bz2')
        ```

2.  **Compressing a file specified by a property:**

    *   **Ant XML:**
        ```xml
        <property name="input.file" value="logs/today.log"/>
        <property name="output.file" value="logs/today.log.bz2"/>
        <bzip2 src="${input.file}" destfile="${output.file}"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.property(name: 'input.file', value: 'logs/today.log')
        ant.property(name: 'output.file', value: 'logs/today.log.bz2')
        ant.bzip2(src: ant.project.getProperty('input.file'), destfile: ant.project.getProperty('output.file'))
        ```

3.  **Compressing a resource from a URL (conceptual, using nested resource):**

    *   **Ant XML:**
        ```xml
        <bzip2 destfile="downloaded_data.bz2">
            <url url="https://www.example.com/datafile.txt"/>
        </bzip2>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.bzip2(destfile: 'downloaded_data.bz2') {
            url(url: 'https://www.example.com/datafile.txt')
        }
        ```

### Important Considerations

*   The `bzip2` task will typically only create the `destfile` if it doesn't exist or if the `src` file is newer than the `destfile`. This behavior can be important for incremental builds.
*   Ensure the source file exists and is readable, and that the destination path is writable.
*   The BZip2 compression algorithm is generally more effective than GZip for many types of files but can be slower.

### Navigation

*   [Previous Task: BUnzip2 Task](BUnzip2_Task_Groovy.md)
*   [Next Task: Basename Task](Basename_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
