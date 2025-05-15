## CCMklabel Task (Groovy DSL)

### Description

The `CCMklabel` task is part of Ant's ClearCase integration. It is used to perform a `cleartool mklabel` (make label) command. This command attaches a label of a pre-existing label type to a specific version of an element (file or directory) in a ClearCase VOB.

In Groovy AntBuilder, this task would be invoked as `ant.ccmklabel(...)`.

### Parameters

Common Ant attributes for the `CCMklabel` task and their Groovy AntBuilder DSL mapping:

*   `typename`: The name of the label type to apply. This label type must already exist in the VOB (e.g., created using `ccmklbtype`). (Required)
    *   Groovy: `typename: "RELEASE_CANDIDATE_1"`
*   `viewpath`: The path to the ClearCase view file or directory to which the label will be applied. This usually points to the specific version of the element you want to label (often the version selected in the view).
    *   Groovy: `viewpath: "/path/to/view/my_module/src/file.java"`
*   `version`: Specifies the version of the element to label. If omitted, it typically labels the version selected by the view's config spec.
    *   Groovy: `version: "/main/my_branch/LATEST"` or `version: "/main/integration_branch/7"`
*   `comment`: A string comment to associate with the application of the label.
    *   Groovy: `comment: "Labeling version for QA testing."`
*   `commentfile`: The path to a file containing the comment. Use either `comment` or `commentfile`.
    *   Groovy: `commentfile: "/path/to/label_comment.txt"`
*   `recurse`: (Boolean) If `true` and `viewpath` is a directory, recursively applies the label to versions of elements within that directory selected by the view. Defaults to `false`.
    *   Groovy: `recurse: true`
*   `replace`: (Boolean) If `true`, replaces an existing label of the same type on the same version. Defaults to `false`.
    *   Groovy: `replace: true`
*   `nmaster`: (Boolean) If `true`, indicates that the VOB containing the label type is not the master of the label type. Used in replicated VOB environments. Defaults to `false`.
    *   Groovy: `nmaster: true`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool mklabel` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Apply an existing label type to the version of a file selected in the view:**

    *   **Ant XML:**
        ```xml
        <ccmklabel typename="APPROVED_FOR_RELEASE"
                   viewpath="/vobs/projectA/docs/manual.doc"
                   comment="Document approved by review board."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmklabel(
            typename: "APPROVED_FOR_RELEASE",
            viewpath: "/vobs/projectA/docs/manual.doc",
            comment: "Document approved by review board."
        )
        ```

2.  **Recursively apply a label type to a directory and its contents, replacing existing labels of this type:**

    *   **Ant XML:**
        ```xml
        <ccmklabel typename="BUILD_2_5_0"
                   viewpath="c:/views/my_view/my_product_module"
                   recurse="true"
                   replace="true"
                   commentfile="c:/builds/comments/build_2_5_0.txt"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmklabel(
            typename: "BUILD_2_5_0",
            viewpath: "c:/views/my_view/my_product_module",
            recurse: true,
            replace: true,
            commentfile: "c:/builds/comments/build_2_5_0.txt"
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **Label Type Must Exist**: The label type specified by `typename` must have been previously created in the VOB (e.g., using `ccmklbtype`).
*   **View Context and Version Selection**: The `viewpath` and `version` attributes determine which specific version of an element gets labeled. If `version` is omitted, the version selected by the current view's config spec is typically used.
*   **Permissions**: The user executing the Ant script must have ClearCase permissions to apply labels in the VOB.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
