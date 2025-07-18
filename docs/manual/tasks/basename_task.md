## Basename Task (Groovy DSL)

### Description

The `basename` task is used to determine the last path element (the "basename") of a specified file or directory path. It can also optionally remove a specified suffix from this basename. The result is stored in a property.

In Groovy AntBuilder, the `basename` task is invoked as `ant.basename(...)`. Its attributes are passed as named parameters.

### Parameters

Common Ant attributes for the `basename` task and their Groovy AntBuilder DSL mapping:

*   `file`: The path (string) from which to extract the basename. This can be an absolute path, a relative path, or just a filename. (Required) Groovy: `file: 
    '/usr/local/lib/myjar.jar'` or `file: ant.project.getProperty("some.file.path.property")`
*   `property`: The name of the property (string) that will be set to the extracted basename. (Required) Groovy: `property: "extracted.filename"`
*   `suffix`: An optional suffix (string) to remove from the end of the basename. If the basename ends with this suffix, it will be removed. The suffix can be specified with or without a leading dot (e.g., ".jar" or "jar"). Groovy: `suffix: ".zip"`

### Examples

1.  **Get the basename of a file path stored in a property:**

    ```groovy
    property(name: "full.path.to.file", value: "/opt/app/archive.tar.gz")
    basename(property: "archive.name", file: ant.project.getProperty("full.path.to.file"))
    // To verify or use:
    // echo(message: "Archive name is: ${ant.project.getProperty('archive.name')}")
    ```

2.  **Get the basename and remove a specific suffix:**

    ```groovy
    basename(property: "script.name", file: "/usr/local/bin/start_server.sh", suffix: ".sh")
    echo(message: "Script name is: ${project.getProperty('script.name')}")
    // script.name will be "start_server"
    ```

3.  **Get the basename of a directory path (last directory element):**

    ```groovy
    property(environment: "env")
    basename(property: "temp.dir.name", file: ant.project.getProperty("env.TEMP"))
    echo(message: "Temp directory name is: ${ant.project.getProperty('temp.dir.name')}")
    // Assuming TEMP is /var/folders/xyz/T/, temp.dir.name will be "T"
    ```

4.  **Using a literal path directly:**

    ```groovy
    basename(property: "config.file.basename", file: "project/settings/config.xml", suffix: "ml")
    // config.file.basename will be "config.x"
    basename(property: "config.file.basename.correct", file: "project/settings/config.xml", suffix: "xml")
    // config.file.basename.correct will be "config"
    echo(message: "Config basename (suffix 'xml'): ${project.getProperty('config.file.basename')}")
    echo(message: "Config basename (suffix '.xml'): ${project.getProperty('config.file.basename.correct')}")
    ```

### Important Considerations

*   The `file` attribute can be a literal string path or an Ant property reference (e.g., `ant.project.getProperty("my.path.prop")` in Groovy, or `${my.path.prop}` if AntBuilder passes it directly to an Ant task that does expansion, though for `basename`'s `file` attribute, direct value or `getProperty` is safer).
*   If the `file` attribute points to a directory, the basename will be the name of the last directory in the path.
*   If the `suffix` is not found at the end of the basename, the basename remains unchanged by the suffix removal logic.

### Navigation

*   [Previous Task: BZip2 Task](BZip2_Task_Groovy.md)
*   [Next Task: Bindtargets Task](Bindtargets_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
