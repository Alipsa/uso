## Echo Task (Groovy DSL)

### Description

The `echo` task in Ant is used to display messages to the Ant loggers and listeners (which typically means `System.out`) or to write messages to a file. It's a fundamental task for providing feedback during a build, logging information, or even generating simple file content.

In Groovy AntBuilder, this task is invoked as `ant.echo(...)`.

### Parameters

*   `message`: The message string to be echoed. If not specified, and if the `<echo>` tag in XML has content, that content is used. In Groovy, you typically provide it directly.
    *   Groovy: `message: "Your message here"` or `ant.echo("Your message here")` for simple messages.
*   `file`: The path to a file where the message should be written. If specified, the `level` attribute is ignored.
    *   Groovy: `file: "path/to/output.txt"`
*   `output`: (Since Ant 1.8) A Resource to write the message to. This is more flexible than `file`.
    *   Groovy: `output: ant.project.resources.fileResource(file: "path/to/output.txt")` (example for a file resource)
*   `append`: (Boolean) If `true` and `file` or `output` (to a file) is specified, the message will be appended to the existing file. If `false` (default), the file will be overwritten.
    *   Groovy: `append: true`
*   `level`: Controls the logging level at which the message is reported when not writing to a file. Valid values (in decreasing order of severity/visibility): `error`, `warning`, `info`, `verbose`, `debug`. The default is `warning`.
    *   Groovy: `level: "info"`
*   `encoding`: (Since Ant 1.7) The character encoding to use when writing to a file. Defaults to the JVM's default character encoding.
    *   Groovy: `encoding: "UTF-8"`
*   `force`: (Since Ant 1.8.2) If `true`, allows overwriting read-only destination files. Defaults to `false`.
    *   Groovy: `force: true`

### Text Content

In Ant XML, you can place the message directly between the `<echo>` and `</echo>` tags. In Groovy AntBuilder, if you want to echo a multi-line string or a string that's not passed via the `message` attribute, you can pass it as the first argument to a closure if the method supports it, or more commonly, just use the `message` attribute with a Groovy multi-line string.

```groovy
// Using message attribute with a multi-line string
ant.echo(message: """This is a longer message
stretching over
two lines.""")

// Ant XML allows <echo>Some text</echo>. 
// The direct Groovy equivalent for simple text is just ant.echo('Some text') or ant.echo(message: 'Some text')
// For more complex structures or if a task specifically supports nested text via addText(), 
// it would be ant.echo { "Some text" } but echo primarily uses the message attribute.
```

### Examples

1.  **Basic message:**

    *   **Ant XML:**
        ```xml
        <echo message="Hello, world"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.echo(message: "Hello, world")
        // Or more simply for just a message:
        ant.echo("Hello, world")
        ```

2.  **Using Ant properties (ensure single quotes for Ant expansion):**

    *   **Ant XML:**
        ```xml
        <property name="greeting" value="Hello from Ant"/>
        <echo message="${greeting}"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.property(name: "greeting", value: "Hello from Groovy AntBuilder")
        ant.echo(message: 'Message: ${greeting}') // Single quotes for Ant to expand
        ```

3.  **Echoing to a file:**

    *   **Ant XML:**
        ```xml
        <echo file="output.txt" message="This goes to a file."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.echo(file: "output.txt", message: "This goes to a file via Groovy.")
        ```

4.  **Appending to a file:**

    *   **Ant XML:**
        ```xml
        <echo file="output.txt" message="This is the first line." append="false"/>
        <echo file="output.txt" message="This is the second line." append="true"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.echo(file: "output.txt", message: "This is the first line from Groovy.", append: false)
        ant.echo(file: "output.txt", message: "This is the second line from Groovy.", append: true)
        ```

5.  **Setting the logging level:**

    *   **Ant XML:**
        ```xml
        <echo message="This is an informational message." level="info"/>
        <echo message="This is a debug message." level="debug"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.echo(message: "This is an informational message from Groovy.", level: "info")
        ant.echo(message: "This is a debug message from Groovy.", level: "debug")
        ```
        (Visibility depends on how Ant is run, e.g., with `-verbose` or `-debug` flags).

6.  **Generating a shell script (escaping `$` for shell variables):**

    *   **Ant XML:**
        ```xml
        <echo file="runner.sh" append="false">#\!/bin/bash
        echo "Project: ${project.name}"
        echo "Arguments: $$@"
        </echo>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        // Note: Ant properties like project.name are expanded if in single-quoted strings.
        // Shell variables like $@ need to be $$@ in Ant XML to escape Ant's own $ expansion.
        // In Groovy, if you use single quotes for the message, Ant handles ${project.name}.
        // For literal '$' for the shell, you'd ensure Groovy doesn't try to GString expand it if it were in double quotes.
        // With single quotes, '$' is literal unless it's part of '${...}' for Ant.
        
        def scriptContent = """#!/bin/bash
echo "Project: 
${project.name}"
echo "Arguments: \$\$@"
""" // Using Groovy GString here, so \$ is needed for literal $ for shell.
        // If passing to Ant for its expansion, and you want Ant to see literal $ for shell, it's tricky.
        // Best to use single quotes for the 'message' attribute if Ant needs to expand properties.

        ant.property(name: 'project.name', value: 'MyGroovyProject')
        ant.echo(file: "runner.sh", append: false, message: '#!/bin/bash\necho "Project: ${project.name}"\necho "Arguments: $$@"')
        // In the above, Ant expands ${project.name}. The $$@ becomes $@ for the shell script.
        // The \n are literal newlines for the file.
        ```

### Important Considerations

*   **Property Expansion**: When echoing messages that contain Ant properties (e.g., `${my.property}`), ensure the `message` string is passed to AntBuilder in single quotes if you want Ant to perform the expansion (e.g., `message: '${my.property}'`). If you use double quotes (GString in Groovy), Groovy will try to interpolate it first, which might not be what you intend if `my.property` is an Ant property and not a Groovy variable.
*   **Line Separators**: Use `ant.project.getProperty("line.separator")` to get the platform-specific line separator if you need to embed it explicitly in messages.
*   **Logging Levels**: The `level` attribute is crucial for controlling message visibility based on Ant's runtime verbosity settings (e.g., `-q`, `-verbose`, `-debug`).

### Navigation

*   [Previous Task: Ear Task](Ear_Task_Groovy.md)
*   [Next Section: Appendix A](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)

