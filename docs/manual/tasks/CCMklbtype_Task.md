## CCMklbtype Task (Groovy DSL)

### Description

The `CCMklbtype` task is part of Ant's ClearCase integration. It is used to perform a `cleartool mklbtype` (make label type) command. This command creates a new label type in a ClearCase VOB (Versioned Object Base). Label types are definitions for labels that can then be applied to versions of elements.

In Groovy AntBuilder, this task would be invoked as `ant.ccmklbtype(...)`.

### Parameters

Common Ant attributes for the `CCMklbtype` task and their Groovy AntBuilder DSL mapping:

*   `typename`: The name of the label type to create (e.g., "RELEASE_1_0", "BUILD_PASSED"). (Required)
    *   Groovy: `typename: "MY_PROJECT_RELEASE_V2"`
*   `vob`: The name of the VOB (Versioned Object Base) where the label type will be created. If not specified, ClearCase might infer it from the current working directory or view context, but it's often better to be explicit.
    *   Groovy: `vob: "/vobs/my_project_vob"` (Unix) or `vob: "\\my_server\ccstg_vobs\my_project_vob"` (Windows, example)
*   `replace`: (Boolean) If `true`, replaces an existing label type definition of the same name. Defaults to `false`.
    *   Groovy: `replace: true`
*   `global`: (Boolean) If `true`, creates a label type that is global to the VOB or to VOBs that use this VOB (in a multi-site environment). Mutually exclusive with `ordinary`. Defaults to `false` (meaning ordinary by default if neither is specified, or if `ordinary` is `true`).
    *   Groovy: `global: true`
*   `ordinary`: (Boolean) If `true`, creates a label type that can be used only in the current VOB. Mutually exclusive with `global`. This is often the default behavior if neither `global` nor `ordinary` is explicitly set to `true`.
    *   Groovy: `ordinary: true`
*   `pbranch`: (Boolean) If `true`, allows the label type to be used once per branch in a given element's version tree. Defaults to `false`.
    *   Groovy: `pbranch: true`
*   `shared`: (Boolean) Sets the way mastership is checked by ClearCase. Consult ClearCase documentation for details on shared vs. unshared label types and mastership. Defaults to `false` (unshared).
    *   Groovy: `shared: true`
*   `comment`: A string comment to associate with the creation of the label type.
    *   Groovy: `comment: "Label type for all official production releases."`
*   `commentfile`: The path to a file containing the comment. Use either `comment` or `commentfile`.
    *   Groovy: `commentfile: "/path/to/labeltype_comment.txt"`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool mklbtype` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Create an ordinary label type in a specific VOB:**

    *   **Ant XML:**
        ```xml
        <ccmklbtype typename="PROJECT_BUILD_LABEL"
                    vob="/vobs/main_project"
                    comment="Standard build label for project deliverables"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmklbtype(
            typename: "PROJECT_BUILD_LABEL",
            vob: "/vobs/main_project",
            comment: "Standard build label for project deliverables"
        )
        ```

2.  **Create a global label type, replacing if it exists:**

    *   **Ant XML:**
        ```xml
        <ccmklbtype typename="GLOBAL_MILESTONE"
                    global="true"
                    replace="true"
                    commentfile="comments/global_milestone_desc.txt"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmklbtype(
            typename: "GLOBAL_MILESTONE",
            global: true,
            replace: true,
            commentfile: "comments/global_milestone_desc.txt"
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **Permissions**: The user executing the Ant script must have the necessary ClearCase permissions to create label types in the specified VOB.
*   **VOB Context**: Ensure the `vob` attribute correctly identifies the target VOB.
*   **Global vs. Ordinary**: Understand the implications of global vs. ordinary label types in your ClearCase environment, especially in multi-site setups.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
