## CCMkdir Task (Groovy DSL)

### Description

The `CCMkdir` task is part of Ant's ClearCase integration. It is used to perform a `cleartool mkdir` (make directory) command. This command creates a new directory element in a ClearCase VOB (Versioned Object Base) and checks it in. This is analogous to a standard `mkdir` command but within the context of ClearCase version control.

In Groovy AntBuilder, this task would be invoked as `ant.ccmkdir(...)`.

### Parameters

Common Ant attributes for the `CCMkdir` task and their Groovy AntBuilder DSL mapping:

*   `viewpath`: The path to the ClearCase view where the directory will be created. (Required)
    *   Groovy: `viewpath: "/path/to/my_clearcase_view"`
*   `directoryname`: The name of the directory to be created. (Required)
    *   Groovy: `directoryname: "new_directory"`
*   `comment`: A string comment to associate with the creation of the directory.
    *   Groovy: `comment: "Creating a new directory for feature X components."`
*   `commentfile`: The path to a file containing the comment. Use either `comment` or `commentfile`.
    *   Groovy: `commentfile: "/path/to/mkdir_comment.txt"`
*   `nocheckout`: (Boolean) If `true`, creates the directory without checking it out. By default (`false`), the directory is created and then checked out.
    *   Groovy: `nocheckout: true`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool mkdir` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Create a new directory in a VOB with a comment:**

    *   **Ant XML:**
        ```xml
        <ccmkdir viewpath="/vobs/projectA/src"
                 directoryname="utils"
                 comment="Adding utility classes directory"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmkdir(
            viewpath: "/vobs/projectA/src",
            directoryname: "utils",
            comment: "Adding utility classes directory"
        )
        ```

2.  **Create a directory and don't check it out immediately:**

    *   **Ant XML:**
        ```xml
        <ccmkdir viewpath="c:/views/my_view/components"
                 directoryname="temp_files"
                 nocheckout="true"
                 comment="Temporary files directory, will be populated later"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmkdir(
            viewpath: "c:/views/my_view/components",
            directoryname: "temp_files",
            nocheckout: true,
            comment: "Temporary files directory, will be populated later"
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **View Context**: The `viewpath` must point to a valid location within an active ClearCase view where the user has write permissions.
*   **Directory Existence**: The task will likely fail if a directory with the same name already exists at that location.
*   **Permissions**: The user executing the Ant script must have the necessary ClearCase permissions to create directories in the specified VOB and view.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
