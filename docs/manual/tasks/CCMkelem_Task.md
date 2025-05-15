## CCMkelem Task (Groovy DSL)

### Description

The `CCMkelem` task is part of Ant's ClearCase integration. It is used to perform a `cleartool mkelem` (make element) command. This command creates a new versioned element (typically a file) in a ClearCase VOB from an existing view-private file. The file is added to source control and checked in.

In Groovy AntBuilder, this task would be invoked as `ant.ccmkelem(...)`.

### Parameters

Common Ant attributes for the `CCMkelem` task and their Groovy AntBuilder DSL mapping:

*   `viewpath`: The path to the view-private file that is to be converted into a ClearCase element. (Required)
    *   Groovy: `viewpath: "/path/to/view/my_module/src/new_file.java"` or `viewpath: "c:/views/my_view/docs/report.doc"`
*   `comment`: A string comment to associate with the creation and initial check-in of the element.
    *   Groovy: `comment: "Adding new utility class to the project."`
*   `commentfile`: The path to a file containing the comment. Use either `comment` or `commentfile`.
    *   Groovy: `commentfile: "/path/to/mkelem_comment.txt"`
*   `nocheckout`: (Boolean) If `true`, the element is created and checked in, but not checked out. By default (`false`), the element is created, checked in, and then checked out.
    *   Groovy: `nocheckout: true`
*   `master`: (Boolean) If `true`, attempts to master the branch type of the new element's main branch in the current replica (for multi-site environments). Defaults to `false`.
    *   Groovy: `master: true`
*   `eltype`: Specifies the element type for the new element (e.g., `text_file`, `compressed_file`). If omitted, ClearCase often determines a suitable type based on file characteristics or defaults.
    *   Groovy: `eltype: "xml"`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool mkelem` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Create a new element from a view-private file with a comment:**

    *   **Ant XML:**
        ```xml
        <ccmkelem viewpath="/vobs/projectA/src/new_feature.java"
                  comment="Initial check-in of new feature class."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmkelem(
            viewpath: "/vobs/projectA/src/new_feature.java",
            comment: "Initial check-in of new feature class."
        )
        ```

2.  **Create a new element, specifying an element type, and do not check it out:**

    *   **Ant XML:**
        ```xml
        <ccmkelem viewpath="c:/views/my_view/assets/logo.png"
                  eltype="binary_delta_file" <!-- Example element type -->
                  nocheckout="true"
                  commentfile="c:/assets/comments/logo_mkelem.txt"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmkelem(
            viewpath: "c:/views/my_view/assets/logo.png",
            eltype: "binary_delta_file",
            nocheckout: true,
            commentfile: "c:/assets/comments/logo_mkelem.txt"
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **View-Private File**: The `viewpath` must point to an existing file that is currently view-private (i.e., not yet under ClearCase control).
*   **Parent Directory**: The parent directory of the `viewpath` must already be a ClearCase versioned directory.
*   **Permissions**: The user executing the Ant script must have the necessary ClearCase permissions to create elements in the specified VOB and view.
*   **Element Type**: If `eltype` is specified, it must be a valid, pre-existing element type in the VOB.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
