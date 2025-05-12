## Checksum Task (Groovy DSL)

### Description

The `checksum` task in Ant is used to generate checksums (e.g., MD5, SHA-1, SHA-512) for one or more files. It can also be used to verify if a file matches a known checksum. The generated checksum can be stored in a property or written to a file (often with an extension corresponding to the algorithm, like `.md5` or `.sha1`).

In Groovy AntBuilder, the `checksum` task is invoked as `ant.checksum(...)`. Its attributes and nested elements (like filesets) are configured within the Groovy script.

### Parameters

Common Ant attributes for the `checksum` task and their Groovy AntBuilder DSL mapping:

*   `file`: The specific file for which to generate a checksum. Required if no nested resource collection is used.
    *   Groovy: `file: "path/to/your/file.txt"`
*   `todir`: The directory where checksum files should be written. If not specified, checksum files are written in the same directory as the original files.
    *   Groovy: `todir: "build/checksums/"`
*   `algorithm`: The checksum algorithm to use (e.g., "MD5", "SHA-1", "SHA-256", "SHA-512"). Defaults to "MD5".
    *   Groovy: `algorithm: "SHA-256"`
*   `provider`: The JCE provider for the algorithm (rarely needed for standard algorithms).
    *   Groovy: `provider: "BC"` (Example for BouncyCastle)
*   `fileext`: The extension to use for the generated checksum file (e.g., ".sha256sum"). Defaults to `.` followed by the algorithm name (e.g., `.SHA-256`).
    *   Groovy: `fileext: ".sha256check"`
*   `property`: This attribute has a dual meaning:
    1.  If `verifyproperty` is NOT set: The name of the property to set with the generated checksum value (for a single file).
        *   Groovy: `property: "file.checksum.value"`
    2.  If `verifyproperty` IS set: The expected checksum value to compare against.
        *   Groovy: `property: "expected_checksum_string"`
*   `verifyproperty`: The name of the property to set to `true` or `false` based on checksum verification. If set, the generated checksum is compared against an existing checksum (from a file or the `property` attribute).
    *   Groovy: `verifyproperty: "checksum.match.status"`
*   `forceoverwrite`: (Boolean) Whether to overwrite existing checksum files. Defaults to `false` (no).
    *   Groovy: `forceoverwrite: true`
*   `pattern`: A `java.text.MessageFormat` pattern for the content of the checksum file. `{0}` is checksum, `{1}` is filename, `{2}` relative path to checksum file, `{3}` relative path to basedir, `{4}` absolute path.
    *   Groovy: `pattern: "{0}  {1}"` (MD5SUM-like format)
*   `format`: Predefined formats like "CHECKSUM", "MD5SUM", "SVF".
    *   Groovy: `format: "MD5SUM"`
*   `totalproperty`: If specified, sets a property with a checksum of all checksums and file paths processed.
    *   Groovy: `totalproperty: "allfiles.checksum.aggregate"`
*   `readbuffersize`: Buffer size for reading files. Defaults to 8192.
    *   Groovy: `readbuffersize: 16384`

### Nested Elements

*   **Resource Collections (e.g., fileset)**: Used to specify multiple files for which to generate checksums.
    ```groovy
    ant.checksum(todir: "checksums/") {
        fileset(dir: "dist/files") {
            include(name: "**/*.jar")
        }
    }
    ```

### Examples

1.  **Generate MD5 checksum for a single file, written to `file.MD5`:**

    *   **Ant XML:**
        ```xml
        <checksum file="important_data.txt"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.checksum(file: "important_data.txt")
        ```

2.  **Generate SHA-256 checksum and store it in a property:**

    *   **Ant XML:**
        ```xml
        <checksum file="archive.zip" algorithm="SHA-256" property="archive.sha256"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.checksum(file: "archive.zip", algorithm: "SHA-256", property: "archive.sha256")
        def sha256Value = ant.project.getProperty("archive.sha256")
        println "SHA-256 for archive.zip: $sha256Value"
        ```

3.  **Verify a file against a known MD5 checksum string:**

    *   **Ant XML:**
        ```xml
        <property name="expected.md5" value="d41d8cd98f00b204e9800998ecf8427e"/>
        <checksum file="empty.txt" property="${expected.md5}" verifyproperty="is.md5.correct"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.property(name: "expected.md5", value: "d41d8cd98f00b204e9800998ecf8427e")
        ant.checksum(file: "empty.txt", property: ant.project.getProperty("expected.md5"), verifyproperty: "is.md5.correct")
        def isCorrect = ant.project.getProperty("is.md5.correct")
        println "Is MD5 correct for empty.txt? $isCorrect"
        ```

4.  **Generate SHA-1 checksums for all JAR files in a directory, output to a `checksums` subdirectory:**

    *   **Ant XML:**
        ```xml
        <checksum algorithm="SHA-1" todir="checksums">
            <fileset dir="lib">
                <include name="**/*.jar"/>
            </fileset>
        </checksum>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.checksum(algorithm: "SHA-1", todir: "checksums") {
            fileset(dir: "lib") {
                include(name: "**/*.jar")
            }
        }
        ```

### Important Considerations

*   **Algorithm Choice**: Be mindful of the security implications of older algorithms like MD5 and SHA-1 if used for integrity verification in security-sensitive contexts. Prefer stronger algorithms like SHA-256 or SHA-512.
*   **JCE Providers**: For algorithms not built into the JDK, you might need to install a JCE provider (like BouncyCastle) and potentially specify it using the `provider` attribute.
*   **File Extensions**: The case of the algorithm name can affect the default file extension (e.g., `SHA-1` might produce `.SHA-1`, while `sha-1` might produce `.sha-1`). Use `fileext` for explicit control.

### Navigation

*   [Previous Task: Cab Task](Cab_Task_Groovy.md)
*   [Next Task: Chgrp Task](Chgrp_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
