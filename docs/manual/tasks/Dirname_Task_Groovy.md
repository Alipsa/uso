## Dirname Task (Groovy DSL)

### Description

The `dirname` task in Ant is used to determine the directory path of a specified file or directory string. It sets a specified property to the value of the input path up to, but not including, the last path element (the filename or last directory name).

If the input `file` attribute is a full path to a file (e.g., `/path/to/some/file.txt`), the property will be set to `/path/to/some`. If it's just a filename (e.g., `file.txt`), the property will be set to the project's basedir (i.e., `.`).

This is different from the Unix `dirname` command, which typically strips any non-directory suffix. Ant's `dirname` provides the full parent directory path.

In Groovy AntBuilder, this task is invoked as `ant.dirname(...)` and is useful for manipulating file paths and constructing other paths dynamically within a build script.

### Parameters

*   `file`: (Required) The file path string from which to determine the directory name.
    *   Groovy: `file: "path/to/your/file.txt"` or `file: ant.project.getProperty("some.file.prop")`
*   `property`: (Required) The name of the Ant property that will be set to the determined directory path.
    *   Groovy: `property: "name.of.property.to.set"`

### Examples

1.  **Set `antfile.dir` to the directory path of the current Ant build file (`${ant.file}`).**

    *   **Ant XML:**
        ```xml
        <dirname property="antfile.dir" file="${ant.file}"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.dirname(property: "antfile.dir", file: ant.project.getProperty("ant.file"))
        // After this, ant.project.getProperty("antfile.dir") will hold the directory.
        // For example, if ant.file is /opt/project/build.xml, antfile.dir will be /opt/project
        ant.echo(message: "Ant file directory: ${ant.project.getProperty("antfile.dir")}")
        ```

2.  **Set `foo.dirname` to the project's basedir if `foo.txt` is a simple filename.**

    *   **Ant XML:**
        ```xml
        <dirname property="foo.dirname" file="foo.txt"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.dirname(property: "foo.dirname", file: "foo.txt")
        // foo.dirname will be set to the project's base directory (e.g., ".")
        ant.echo(message: "Dirname for foo.txt: ${ant.project.getProperty("foo.dirname")}")
        ```

3.  **Using a full path:**

    *   **Groovy AntBuilder:**
        ```groovy
        def myFilePath = "/usr/local/share/doc/manual.pdf"
        ant.dirname(property: "manual.doc.dir", file: myFilePath)
        // manual.doc.dir will be "/usr/local/share/doc"
        ant.echo(message: "Manual document directory: ${ant.project.getProperty("manual.doc.dir")}")
        ```

### Important Considerations

*   **Property Setting**: This task *sets an Ant property*. To use the value within your Groovy script after the task has run, you retrieve it using `ant.project.getProperty("your.property.name")`.
*   **Relative vs. Absolute Paths**: The behavior with relative paths for the `file` attribute will be resolved against the project's basedir to determine the absolute path before extracting the directory name.

### Navigation

*   [Previous Task: Diagnostics Task](Diagnostics_Task_Groovy.md)
*   [Next Task: Ear Task](Ear_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)

