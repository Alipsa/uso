## CCCheckin Task (Groovy DSL)

### Description

The `CCCheckin` task is part of Ant's ClearCase integration and is used to perform a `cleartool checkin` command. This action checks in a previously checked-out file or directory into the ClearCase versioned object base (VOB).

In Groovy AntBuilder, this task would be invoked as `ant.cccheckin(...)`.

### Parameters

Common Ant attributes for the `CCCheckin` task and their Groovy AntBuilder DSL mapping:

*   `viewpath`: The path to the ClearCase view file or directory that the command will operate on. This is often the file or directory being checked in.
    *   Groovy: `viewpath: "/path/to/view/my_module/src/file.java"` or `viewpath: "c:/views/my_view/my_component/src/file.c"`
*   `comment`: A string comment to associate with the check-in.
    *   Groovy: `comment: "Fixed bug #123: Null pointer exception."`
*   `commentfile`: The path to a file containing the comment for the check-in. Use either `comment` or `commentfile`, not both.
    *   Groovy: `commentfile: "/path/to/checkin_comment.txt"`
*   `nowarn`: (Boolean) If `true`, suppresses warning messages from `cleartool`. Defaults to `false`.
    *   Groovy: `nowarn: true`
*   `preservetime`: (Boolean) If `true`, preserves the modification time of the file from the VOB. Defaults to `false`.
    *   Groovy: `preservetime: true`
*   `keepcopy`: (Boolean) If `true`, keeps a copy of the file with a `.keep` extension after check-in. Defaults to `false`.
    *   Groovy: `keepcopy: true`
*   `identical`: (Boolean) If `true`, allows the file to be checked in even if it is identical to the predecessor version. Defaults to `false`.
    *   Groovy: `identical: true`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool checkin` command returns an error.
    *   Groovy: `failonerr: false` (to allow the build to continue on error)

### Examples

1.  **Check in a file with a direct comment:**

    *   **Ant XML:**
        ```xml
        <cccheckin viewpath="/vobs/projectA/src/foo.c"
                   comment="Implemented new feature X."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.cccheckin(
            viewpath: "/vobs/projectA/src/foo.c",
            comment: "Implemented new feature X."
        )
        ```

2.  **Check in a file using a comment file, preserving time, and allowing identical check-in:**

    *   **Ant XML:**
        ```xml
        <cccheckin viewpath="c:/views/my_view/src/bar.java"
                   commentfile="c:/comments/bar_java_v2.txt"
                   preservetime="true"
                   identical="true"
                   nowarn="true"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.cccheckin(
            viewpath: "c:/views/my_view/src/bar.java",
            commentfile: "c:/comments/bar_java_v2.txt",
            preservetime: true,
            identical: true,
            nowarn: true
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **View Context**: The `viewpath` should correctly point to an element within an active ClearCase view.
*   **Checked-out State**: The element must typically be checked out in the current view before it can be checked in.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
