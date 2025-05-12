## Attrib Task (Groovy DSL)

### Description

The `attrib` task is used in Ant to change the attributes of a file or all files within specified directories. According to the Apache Ant documentation, this task primarily has an effect on Windows systems. For platform-independent attribute manipulation, the `setpermissions` task is suggested as an alternative.

When using Groovy with AntBuilder, you can interact with the `attrib` task by calling `ant.attrib(...)`. The attributes of the task are passed as named parameters, and any nested resource collections (like filesets) are defined within a closure.

### Parameters

The main parameters for the `attrib` task, when translated to Groovy AntBuilder, are:

*   `file`: (String) Specifies the path to the file or directory whose attributes are to be changed. This is used when operating on a single file/directory. Example: `file: 'myFile.txt'`
*   `readonly`: (Boolean) Sets or clears the read-only attribute. `true` to set, `false` to clear.
*   `archive`: (Boolean) Sets or clears the archive attribute. `true` to set, `false` to clear.
*   `system`: (Boolean) Sets or clears the system attribute. `true` to set, `false` to clear.
*   `hidden`: (Boolean) Sets or clears the hidden attribute. `true` to set, `false` to clear.
*   `type`: (String) Applicable when using resource collections. Can be "file", "dir", or "both" to specify what types of items in the collection should be affected. Defaults to "file".
*   `verbose`: (Boolean) If `true`, provides more detailed output during execution.
*   `os`: (String) Specifies a list of operating systems on which the command can be executed.
*   `osfamily`: (String) Specifies the OS family (e.g., 'windows'). Defaults to 'windows' as per documentation.

At least one of the permission attributes (`readonly`, `archive`, `system`, `hidden`) must be specified when not using nested resource collections for broader application.

### Nested Elements

When you need to apply attributes to multiple files or directories, you can use nested resource collections:

*   **fileset**: Defines a set of files.
*   **dirset**: Defines a set of directories.
*   **filelist**: Defines a list of specific files.

These are specified within a closure in the Groovy call.

### Examples

1.  **Make a single file read-only and hidden:**

    *   **Ant XML:**
        ```xml
        <attrib file="mydirectory/myfile.txt" readonly="true" hidden="true"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.attrib(file: 'mydirectory/myfile.txt', readonly: true, hidden: true)
        ```

2.  **Make all `.xml` files in a directory and its subdirectories writable:**

    *   **Ant XML:**
        ```xml
        <attrib readonly="false">
            <fileset dir="src/main/resources" includes="**/*.xml"/>
        </attrib>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.attrib(readonly: false) {
            fileset(dir: 'src/main/resources', includes: '**/*.xml')
        }
        ```

3.  **Apply attributes to files specified in a FileList, on Windows OS only:**

    *   **Ant XML:**
        ```xml
        <attrib readonly="true" osfamily="windows">
            <filelist dir="config" files="settings.conf,user.prefs"/>
        </attrib>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.attrib(readonly: true, osfamily: 'windows') {
            filelist(dir: 'config', files: 'settings.conf,user.prefs')
        }
        ```

### Important Considerations

*   The `attrib` task's primary effectiveness is on Windows systems for attribute modification.
*   For cross-platform permission changes, consider using other tasks or methods if `attrib` does not meet the requirements on non-Windows systems.
*   Always ensure that the paths and file/directory names are correctly specified.

### Navigation

*   [Previous Task: Apply/ExecOn Task](ApplyExecOn_Task_Groovy.md)
*   [Next Task: Augment Task](Augment_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
