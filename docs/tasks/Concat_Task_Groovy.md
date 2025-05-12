## Concat Task (Groovy DSL)

### Description

The `concat` task is used to concatenate one or more resources (files, text, etc.) into a single destination file or to Ant's logging system (the console). It's a versatile task for combining text-based content, and with the `binary` attribute, it can also handle binary files.

In Groovy AntBuilder, the `concat` task is invoked as `ant.concat(...)`. Resources to be concatenated are typically specified using nested resource collections within a closure, or by providing inline text.

### Parameters

Common Ant attributes for the `concat` task and their Groovy AntBuilder DSL mapping:

*   `destfile`: The path to the destination file where the concatenated output will be written. If not specified, output goes to the Ant console.
    *   Groovy: `destfile: "/path/to/output/combined.txt"`
*   `append`: (Boolean) If `true`, the output is appended to `destfile` if it already exists. If `false` (the default), `destfile` is overwritten.
    *   Groovy: `append: true`
*   `overwrite`: (Boolean) If `true` (the default), `destfile` will be written to even if it is newer than all source files. Set to `false` to only write if `destfile` is older.
    *   Groovy: `overwrite: false`
*   `forceReadOnly`: (Boolean) If `true`, overwrites read-only destination files. Defaults to `false`.
    *   Groovy: `forceReadOnly: true`
*   `encoding`: Specifies the character encoding for reading the input files. Defaults to the JVM's default character encoding.
    *   Groovy: `encoding: "UTF-8"`
*   `outputencoding`: Specifies the character encoding for writing the `destfile`. Defaults to the value of `encoding` if set, otherwise the JVM's default.
    *   Groovy: `outputencoding: "ISO-8859-1"`
*   `fixlastline`: (Boolean) If `true`, ensures that each concatenated file (not inline text) ends with a newline character. If a file doesn't, a newline is added. Defaults to `false`.
    *   Groovy: `fixlastline: true`
*   `eol`: Specifies the end-of-line character(s) to use when `fixlastline` is `true`. Valid values: `cr`, `lf`, `crlf`, `mac`, `unix`, `dos`. Defaults to the platform's EOL.
    *   Groovy: `eol: "lf"`
*   `binary`: (Boolean) If `true`, concatenates files byte-by-byte. This is necessary for binary files. If `true`, `destfile` must be set, and nested text, `encoding`, `outputencoding`, and `fixlastline` cannot be used. Defaults to `false`.
    *   Groovy: `binary: true`
*   `ignoreempty`: (Boolean) If `true` (the default), `destfile` is created even if the list of resources to concatenate is empty. If `false`, an empty resource list will not create/modify `destfile`.
    *   Groovy: `ignoreempty: false`
*   `filterbeforeconcat`: (Boolean) If `true`, applies the filterchain to each input *after* `fixlastline` but *before* concatenation. If `false` (default), the filterchain is applied once to the already concatenated inputs. Header and footer filtering is not affected.
    *   Groovy: `filterbeforeconcat: true`

### Nested Elements

*   **Resource Collections**: Any standard Ant resource collection (`fileset`, `filelist`, `path`, `resources`, etc.) can be nested to specify the sources to concatenate.
    ```groovy
    ant.concat(destfile: "all_logs.txt") {
        fileset(dir: "logs", includes: "*.log")
        filelist(dir: "archived_logs", files: "old_log_1.txt,old_log_2.txt")
    }
    ```
*   **Inline Text**: You can directly nest text content to be concatenated.
    ```groovy
    ant.concat(destfile: "greeting.txt", "Hello, ") // First part of text
    ant.concat(destfile: "greeting.txt", append: true, "World!") // Append more text
    // Or more commonly for multiline or combined with files:
    ant.concat(destfile: "story.txt") {
        text(value: "Once upon a time...\n") // Using nested <text> element for clarity
        fileset(file: "chapter1.txt")
        text(value: "\nThe End.")
    }
    ```
    Note: For simple string concatenation directly as arguments to `ant.concat()`, the first string is often treated as the content if `destfile` is also present. For more complex or structured inline text, using a nested `ant.text(value: "...")` or `<text>...</text>` in XML (which translates to `ant.text { "..." }` or `ant.text(value:"...")` in Groovy) is more robust.

*   **`filterchain`**: A standard Ant FilterChain can be nested to process the concatenated content.
    ```groovy
    ant.concat(destfile: "processed.txt") {
        fileset(dir: "input", includes: "*.txt")
        filterchain {
            replacetokens(begintoken: "@", endtoken: "@") {
                token(key: "VERSION", value: "1.2.3")
            }
        }
    }
    ```
*   **`header`** and **`footer`**: These elements allow prepending or appending fixed text to the concatenated stream. They have attributes like `file` (to source from a file), `filtering`, `trim`, `trimleading`.
    ```groovy
    ant.concat(destfile: "report.txt") {
        header(file: "report_header.txt")
        fileset(dir: "report_sections", includes: "section_*.txt")
        footer {
            // Inline footer text
            "--- End of Report ---"
        }
    }
    ```

### Examples

1.  **Concatenate multiple text files into one:**

    *   **Ant XML:**
        ```xml
        <concat destfile="all_chapters.txt">
            <filelist dir="chapters" files="chap1.txt,chap2.txt,chap3.txt"/>
        </concat>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.concat(destfile: "all_chapters.txt") {
            filelist(dir: "chapters", files: "chap1.txt,chap2.txt,chap3.txt")
        }
        ```

2.  **Append text to an existing file, ensuring a newline:**

    *   **Ant XML:**
        ```xml
        <concat destfile="notes.log" append="true" fixlastline="true">
            A new note added on ${TODAY}.
        </concat>
        ```
    *   **Groovy AntBuilder (for inline text, `fixlastline` applies to file inputs primarily; for inline, manage newlines directly):**
        ```groovy
        // Groovy handles string interpolation directly
        def newNote = "A new note added on ${ant.project.getProperty("TODAY")}.\n" 
        ant.concat(destfile: "notes.log", append: true, newNote)
        // If concatenating files and wanting fixlastline:
        ant.concat(destfile: "output.txt", fixlastline: true) {
            fileset(file: "input1.txt") 
        }
        ```

3.  **Concatenate binary files:**

    *   **Ant XML:**
        ```xml
        <concat destfile="archive.bin" binary="true">
            <fileset file="part1.bin"/>
            <fileset file="part2.bin"/>
        </concat>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.concat(destfile: "archive.bin", binary: true) {
            fileset(file: "part1.bin")
            fileset(file: "part2.bin")
        }
        ```

### Important Considerations

*   **Text vs. Binary**: Be mindful of the `binary` attribute. For text files, encodings can be important. For binary files, `binary="true"` is essential.
*   **Resource Order**: Resources are concatenated in the order they are specified or encountered by the resource collections.
*   **FilterChains**: When using filterchains, understand whether `filterbeforeconcat` is needed for your use case.

### Navigation

*   [Previous Task: Componentdef Task](ComponentDef_Task_Groovy.md)
*   [Next Task: Condition Task](Condition_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
