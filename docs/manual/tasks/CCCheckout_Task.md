## CCCheckout Task (Groovy DSL)

### Description

The `CCCheckout` task is part of Ant's ClearCase integration and is used to perform a `cleartool checkout` command. This action makes an element (file or directory) in a ClearCase VOB writable in the current view, allowing modifications.

In Groovy AntBuilder, this task would be invoked as `ant.cccheckout(...)`.

### Parameters

Common Ant attributes for the `CCCheckout` task and their Groovy AntBuilder DSL mapping:

*   `viewpath`: The path to the ClearCase view file or directory that the command will operate on.
    *   Groovy: `viewpath: "/path/to/view/my_module/src/file.java"`
*   `reserved`: (Boolean) Specifies whether to check out the file as reserved (`true`) or unreserved (`false`). A reserved checkout prevents other users from checking out the same branch of the element. (Required)
    *   Groovy: `reserved: true`
*   `out`: Creates a writable file under a different filename. The original element remains as a read-only copy of the VOB version.
    *   Groovy: `out: "/path/to/view/my_module/src/file_copy.java"`
*   `nodata`: (Boolean) If `true`, checks out the file but does not create an editable file containing its data. Useful for directory checkouts or when data is not immediately needed. Defaults to `false`.
    *   Groovy: `nodata: true`
*   `branch`: Specifies a branch to check out the file to. If the branch doesn't exist, `cleartool` might attempt to create it depending on its configuration.
    *   Groovy: `branch: "my_feature_branch"`
*   `version`: Allows checkout of a version other than `/main/LATEST` (or whatever is selected by the view's config spec).
    *   Groovy: `version: "/main/my_branch/3"`
*   `nowarn`: (Boolean) If `true`, suppresses warning messages from `cleartool`. Defaults to `false`.
    *   Groovy: `nowarn: true`
*   `comment`: A string comment to associate with the checkout operation.
    *   Groovy: `comment: "Checking out for refactoring."`
*   `commentfile`: The path to a file containing the comment for the checkout. Use either `comment` or `commentfile`.
    *   Groovy: `commentfile: "/path/to/checkout_comment.txt"`
*   `notco`: (Boolean) Fail if it's already checked out to the current view. Set to `false` to ignore this and proceed (effectively a no-op if already checked out). Defaults to `true`.
    *   Groovy: `notco: false`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool checkout` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Perform a reserved checkout of a file with a comment:**

    *   **Ant XML:**
        ```xml
        <cccheckout viewpath="c:/views/viewdir/afile.txt"
                    reserved="true"
                    comment="Modifying for issue #456"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.cccheckout(
            viewpath: "c:/views/viewdir/afile.txt",
            reserved: true,
            comment: "Modifying for issue #456"
        )
        ```

2.  **Perform an unreserved checkout to a specific branch, suppressing warnings:**

    *   **Ant XML:**
        ```xml
        <cccheckout viewpath="/vobs/projectX/lib/utils.jar"
                    reserved="false"
                    branch="bugfix_branch"
                    nowarn="true"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.cccheckout(
            viewpath: "/vobs/projectX/lib/utils.jar",
            reserved: false,
            branch: "bugfix_branch",
            nowarn: true
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **View Context**: The `viewpath` must point to an element within an active ClearCase view.
*   **Element State**: The element should not already be checked out (unless `notco="false"` is used for reserved checkouts, or it's an unreserved checkout and you intend to have multiple unreserved checkouts if allowed by ClearCase policy).

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
