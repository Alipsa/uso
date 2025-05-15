## CCMkattr Task (Groovy DSL)

### Description

The `CCMkattr` task is part of Ant's ClearCase integration. It is used to perform a `cleartool mkattr` (make attribute) command. This command creates and attaches an attribute instance (a name-value pair) to a version of an element, a branch, or other ClearCase objects. Attributes are used to store metadata.

In Groovy AntBuilder, this task would be invoked as `ant.ccmkattr(...)`.

### Parameters

Common Ant attributes for the `CCMkattr` task and their Groovy AntBuilder DSL mapping:

*   `typename`: The name of the attribute type. This attribute type must already exist in the VOB (e.g., created using `cleartool mkattype`). (Required)
    *   Groovy: `typename: "ReviewStatus"`
*   `typevalue`: The value to assign to the attribute instance. The data type of this value should match the data type defined for the attribute type. (Required)
    *   Groovy: `typevalue: "Approved"` (for a string attribute) or `typevalue: 10` (for an integer attribute)
*   `viewpath`: The path to the ClearCase view file or directory to which the attribute will be attached. This usually points to the specific version of the element you want to attribute (often the version selected in the view).
    *   Groovy: `viewpath: "/path/to/view/my_module/src/file.java"`
*   `version`: Specifies the version of the element to which the attribute will be attached. If omitted, it typically applies to the version selected by the view's config spec.
    *   Groovy: `version: "/main/my_branch/LATEST"`
*   `comment`: A string comment to associate with the creation of the attribute instance.
    *   Groovy: `comment: "Setting review status to Approved after successful review."`
*   `commentfile`: The path to a file containing the comment. Use either `comment` or `commentfile`.
    *   Groovy: `commentfile: "/path/to/attr_comment.txt"`
*   `recurse`: (Boolean) If `true` and `viewpath` is a directory, recursively attaches the attribute to versions of elements within that directory selected by the view. Defaults to `false`.
    *   Groovy: `recurse: true`
*   `replace`: (Boolean) If `true`, replaces an existing attribute instance of the same type on the same version/object. Defaults to `false`.
    *   Groovy: `replace: true`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool mkattr` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Attach a string attribute to the version of a file selected in the view:**

    *   **Ant XML:**
        ```xml
        <ccmkattr typename="CodeCoverage"
                  typevalue="85%"
                  viewpath="/vobs/projectA/src/module.c"
                  comment="Updated code coverage metric after tests."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmkattr(
            typename: "CodeCoverage",
            typevalue: "85%",
            viewpath: "/vobs/projectA/src/module.c",
            comment: "Updated code coverage metric after tests."
        )
        ```

2.  **Recursively attach an integer attribute to a directory and its contents, replacing existing attributes of this type:**

    *   **Ant XML:**
        ```xml
        <ccmkattr typename="SecurityLevel"
                  typevalue="3" <!-- Assuming SecurityLevel is an integer attribute type -->
                  viewpath="c:/views/my_view/secure_docs"
                  recurse="true"
                  replace="true"
                  commentfile="c:/docs/security_attr_comment.txt"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmkattr(
            typename: "SecurityLevel",
            typevalue: 3,
            viewpath: "c:/views/my_view/secure_docs",
            recurse: true,
            replace: true,
            commentfile: "c:/docs/security_attr_comment.txt"
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **Attribute Type Must Exist**: The attribute type specified by `typename` must have been previously created in the VOB (e.g., using `cleartool mkattype`).
*   **Value Data Type**: The `typevalue` must match the data type defined for the attribute type (e.g., string, integer, date, etc.).
*   **View Context and Version Selection**: The `viewpath` and `version` attributes determine which specific version of an element gets the attribute.
*   **Permissions**: The user executing the Ant script must have ClearCase permissions to create attribute instances in the VOB.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
