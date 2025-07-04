# Ant Task: FixCRLF

## Original Ant Task Description

The `FixCRLF` Ant task is used to adjust text files to local operating system line ending conventions. It can also handle tab characters and End-Of-File (EOF) markers. This task is crucial for maintaining consistent line endings across different development environments (e.g., Windows, Linux, macOS) and for preparing files for specific platforms or tools.

It operates on a set of files, which can be specified using attributes like `srcdir`, `file`, `includes`, `excludes`, etc., forming an implicit fileset. The task only overwrites files if the processed content differs from the original, preventing unnecessary rebuilds.

Key functionalities include:
*   **EOL Conversion**: Converts line endings to `CRLF` (DOS/Windows), `LF` (Unix/macOS), or `CR` (classic Mac), or leaves them `asis`.
*   **Tab Handling**: Can convert tabs to spaces (`remove`), spaces to tabs (`add`), or leave them `asis`. `tablength` specifies the tab interval.
*   **EOF Character Handling**: Can `add`, `remove`, or leave `asis` DOS EOF (Ctrl-Z) characters.
*   **Encoding**: Supports specifying input `encoding` and `outputencoding` for files.
*   **Java File Specifics**: Has a `javafiles` attribute to correctly handle tabs within string/character literals in Java source files.
*   **Preserving Timestamps**: Can preserve the last modified timestamp of source files.

_Since Ant 1.7_, `FixCRLF` can also be used as a filter within a filterchain.

## Groovy AntBuilder DSL Equivalent

The Groovy `AntBuilder` provides a direct and idiomatic way to use the `FixCRLF` task. Attributes from the Ant XML task are passed as named parameters to the `fixcrlf` method. Nested fileset elements like `<include>` and `<exclude>` can be used within a closure if operating on a directory.

```groovy
ant = new AntBuilder()

// Example: Convert all .txt files in a directory to Unix line endings
ant.fixcrlf(srcdir: "path/to/textfiles", includes: "**/*.txt", eol: "lf")

// Example: Process a single file, remove tabs, preserve timestamp
ant.fixcrlf(file: "path/to/singlefile.conf", tab: "remove", preservelastmodified: true)
```

## Parameter Mapping

The Ant XML attributes for the `filter` task map to parameters in the Groovy `AntBuilder` method call.

| Ant Attribute          | Groovy Parameter       | Description                                                                                                                               | Required (as Task)                |
|------------------------|------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------|
| `srcDir`               | `srcdir`               | Source directory for files to be fixed.                                                                                                   | Yes (or `file`)                   |
| `file`                 | `file`                 | A single file to fix. (Since Ant 1.7)                                                                                                     | Yes (or `srcdir`)                 |
| `destDir`              | `destdir`              | Destination directory for corrected files.                                                                                                | No; defaults to `srcdir` (in-place) |
| `includes`             | `includes`             | Comma- or space-separated list of include patterns.                                                                                       | No; defaults to `**` (all files)  |
| `includesfile`         | `includesfile`         | File containing include patterns, one per line.                                                                                           | No                                |
| `excludes`             | `excludes`             | Comma- or space-separated list of exclude patterns.                                                                                       | No                                |
| `excludesfile`         | `excludesfile`         | File containing exclude patterns, one per line.                                                                                           | No                                |
| `defaultexcludes`      | `defaultexcludes`      | Whether to use default Ant excludes (`yes`/`no`).                                                                                         | No; defaults to `yes`             |
| `encoding`             | `encoding`             | Encoding of the source files.                                                                                                             | No; defaults to JVM default       |
| `outputencoding`       | `outputencoding`       | Encoding for writing the output files. (Since Ant 1.7)                                                                                    | No; defaults to `encoding` value  |
| `preservelastmodified` | `preservelastmodified` | Whether to preserve the last modified date of source files (`true`/`false`). (Since Ant 1.6.3)                                            | No; defaults to `false`           |
| `eol`                  | `eol`                  | End-of-line handling: `asis`, `cr`, `lf`, `crlf`, `mac`, `unix`, `dos`.                                                                   | No; defaults to platform-specific |
| `cr`                   | `cr`                   | *Deprecated*. Use `eol`. Handles CR characters: `asis`, `add`, `remove`.                                                                  | No                                |
| `javafiles`            | `javafiles`            | Whether files are Java source files (`true`/`false`), for tab handling in literals.                                                       | No; defaults to `false`           |
| `tab`                  | `tab`                  | Tab handling: `add`, `asis`, `remove`.                                                                                                    | No; defaults to `asis`            |
| `tablength`            | `tablength`            | Tab character interval (2-80).                                                                                                            | No; defaults to 8                 |
| `eof`                  | `eof`                  | DOS EOF (Ctrl-Z) handling: `add`, `asis`, `remove`.                                                                                       | No; defaults to platform-specific |
| `fixlast`              | `fixlast`              | Whether to add a missing EOL to the last line (`true`/`false`). Ignored if `eof` is `asis`. (Since Ant 1.6.1)                             | No; defaults to `true`            |

### Nested Elements (when `srcdir` is used)

*   **`<include>`**: Specifies files to include.
*   **`<exclude>`**: Specifies files to exclude.
*   **`<patternset>`**: Allows for more complex include/exclude patterns.

```groovy
ant.fixcrlf(srcdir: "source_directory") {
    include(name: "**/*.java")
    exclude(name: "**/legacy/**")
}
```

## Code Examples

### Example 1: Convert Shell Scripts to LF EOLs

**Ant XML:**
```xml
<fixcrlf srcdir="src/scripts"
         includes="**/*.sh"
         eol="lf"
         eof="remove"/>
```

**Groovy AntBuilder DSL:**
```groovy
// Ensure dummy files/dirs exist for the example to run
new File("src/scripts").mkdirs()
new File("src/scripts/myscript.sh").write("echo \"Hello World\"\r\nexit 0\r\n")

new AntBuilder().sequential {
    fixcrlf(srcdir: "src/scripts", includes: "**/*.sh", eol: "lf", eof: "remove")
    println "Shell scripts in src/scripts processed to LF line endings."
}
```

### Example 2: Convert Batch Files to CRLF EOLs, Output to Different Directory

**Ant XML:**
```xml
<fixcrlf srcdir="src/batch_files"
         destdir="processed/batch_files"
         includes="**/*.bat"
         eol="crlf"/>
```

**Groovy AntBuilder DSL:**
```groovy
// Ensure dummy files/dirs exist for the example
new File("src/batch_files").mkdirs()
new File("processed/batch_files").mkdirs() // AntBuilder won't create destDir automatically for fixcrlf
new File("src/batch_files/mybatch.bat").write("echo Hello World\n@pause\n")

new AntBuilder().sequential {
    fixcrlf(srcdir: "src/batch_files", 
            destdir: "processed/batch_files", 
            includes: "**/*.bat", 
            eol: "crlf")
    println "Batch files from src/batch_files processed to processed/batch_files with CRLF endings."
}
```

### Example 3: Process Java Files: Remove Tabs, Set EOL to LF

**Ant XML:**
```xml
<fixcrlf srcdir="src/main/java"
         includes="**/*.java"
         tab="remove"
         tablength="4"
         eol="lf"
         javafiles="true"/>
```

**Groovy AntBuilder DSL:**
```groovy
// Ensure dummy files/dirs exist for the example
new File("src/main/java/com/example").mkdirs()
new File("src/main/java/com/example/MyClass.java").write("public class MyClass {\n\tpublic void myMethod() {\n\t\tSystem.out.println(\"Hello\tWorld\");\n\t}\n}")

new AntBuilder().sequential {
    fixcrlf(srcdir: "src/main/java", 
            includes: "**/*.java", 
            tab: "remove", 
            tablength: 4,  // Integer preferred in Groovy
            eol: "lf", 
            javafiles: true)
    println "Java files in src/main/java processed: tabs removed, LF line endings."
}
```

### Example 4: Process a Single Configuration File with Specific Encodings

**Ant XML:**
```xml
<fixcrlf file="config/app.properties"
         encoding="ISO-8859-1"
         outputencoding="UTF-8"
         eol="asis"/>
```

**Groovy AntBuilder DSL:**
```groovy
// Ensure dummy file exists for the example
new File("config").mkdirs()
new File("config/app.properties").write("key=value\r\n# Old comment with special chars: Ã©Ã Ã¼", "ISO-8859-1")

new AntBuilder().sequential {
    fixcrlf(file: "config/app.properties", 
            encoding: "ISO-8859-1", 
            outputencoding: "UTF-8", 
            eol: "asis")
    println "Single file config/app.properties processed with encoding conversion."
}
```

### Example 5: Preserve Last Modified Timestamp

**Ant XML:**
```xml
<fixcrlf srcdir="documents"
         includes="**/*.txt"
         eol="unix"
         preservelastmodified="true"/>
```

**Groovy AntBuilder DSL:**
```groovy
// Ensure dummy files/dirs exist for the example
new File("documents").mkdirs()
new File("documents/report.txt").write("Line 1\r\nLine 2\r\n")
long originalTimestamp = new File("documents/report.txt").lastModified()

new AntBuilder().sequential {
    fixcrlf(srcdir: "documents", 
            includes: "**/*.txt", 
            eol: "unix", 
            preservelastmodified: true)
    println "Text files in documents processed, timestamps preserved."
    // Verification (conceptual)
    // long newTimestamp = new File("documents/report.txt").lastModified()
    // assert originalTimestamp == newTimestamp
}
```

## Notes

*   When `destdir` is not specified, files are modified in-place in the `srcdir`.
*   The `fixcrlf` task is powerful for ensuring cross-platform compatibility of text files.
*   Pay attention to the `javafiles="true"` attribute when processing Java source code to avoid unintentionally altering string or character literals containing tabs.
*   The `eol` attribute is preferred over the deprecated `cr` attribute for specifying line ending conversions.
*   For complex file selections, nested `<patternset>` elements can be used within the `fixcrlf` task when `srcdir` is specified.


