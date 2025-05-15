## CCRmtype Task (Groovy DSL)

### Description

The `CCRmtype` task is part of Ant's ClearCase integration. It is used to perform a `cleartool rmtype` (remove type) command. This command deletes a type object (such as a label type, attribute type, branch type, or element type) from a ClearCase VOB (Versioned Object Base). This operation is usually permanent and should be used with caution.

In Groovy AntBuilder, this task would be invoked as `ant.ccrmtype(...)`.

### Parameters

Common Ant attributes for the `CCRmtype` task and their Groovy AntBuilder DSL mapping:

*   `typekind`: The kind of type to remove. Common values include `lbtype` (label type), `attype` (attribute type), `brtype` (branch type), `eltype` (element type). (Required)
    *   Groovy: `typekind: "lbtype"`
*   `typename`: The name of the type instance to remove (e.g., the name of the label type). (Required)
    *   Groovy: `typename: "OBSOLETE_BUILD_LABEL"`
*   `vob`: The name of the VOB where the type exists. If not specified, ClearCase might infer it, but explicit specification is safer.
    *   Groovy: `vob: "/vobs/my_project_vob"`
*   `ignore`: (Boolean) If `true`, ignores pre-trigger errors during type removal. Use with caution. Defaults to `false`.
    *   Groovy: `ignore: true`
*   `rmall`: (Boolean) If `true`, attempts to remove all instances of the type (e.g., all labels of a given label type) before removing the type itself. This can be a long and impactful operation. Use with extreme caution. Defaults to `false`.
    *   Groovy: `rmall: true`
*   `comment`: A string comment to associate with the removal of the type.
    *   Groovy: `comment: "Removing obsolete label type as per cleanup policy."`
*   `commentfile`: The path to a file containing the comment. Use either `comment` or `commentfile`.
    *   Groovy: `commentfile: "/path/to/rmtype_comment.txt"`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool rmtype` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Remove an obsolete label type from a VOB:**

    *   **Ant XML:**
        ```xml
        <ccrmtype typekind="lbtype"
                  typename="OLD_RELEASE_LABEL"
                  vob="/vobs/legacy_project"
                  comment="Removing unused legacy label type."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccrmtype(
            typekind: "lbtype",
            typename: "OLD_RELEASE_LABEL",
            vob: "/vobs/legacy_project",
            comment: "Removing unused legacy label type."
        )
        ```

2.  **Remove an attribute type, ignoring pre-trigger errors (use with caution):**

    *   **Ant XML:**
        ```xml
        <ccrmtype typekind="attype"
                  typename="DEPRECATED_STATUS_ATTR"
                  vob="/vobs/main_vob"
                  ignore="true"
                  commentfile="comments/rm_attr_type.txt"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccrmtype(
            typekind: "attype",
            typename: "DEPRECATED_STATUS_ATTR",
            vob: "/vobs/main_vob",
            ignore: true,
            commentfile: "comments/rm_attr_type.txt"
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **Irreversible Operation**: Removing types, especially with `rmall`, can be an irreversible operation and can lead to loss of historical metadata or prevent access to certain versions if not done carefully. Always ensure you understand the impact in your ClearCase environment.
*   **Permissions**: The user executing the Ant script must have the necessary ClearCase administrative permissions to remove types in the specified VOB.
*   **Type Instances**: Before removing a type, ensure it is not in use or that you intend to remove all its instances (if using `rmall`). Removing a type that is still referenced can cause errors or inconsistencies.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
