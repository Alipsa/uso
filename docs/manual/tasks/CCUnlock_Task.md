## CCUnlock Task (Groovy DSL)

### Description

The `CCUnlock` task is part of Ant's ClearCase integration. It is used to perform a `cleartool unlock` command. This command removes a lock from an object in a ClearCase VOB (Versioned Object Base) that was previously locked (e.g., using `cclock`).

In Groovy AntBuilder, this task would be invoked as `ant.ccunlock(...)`.

### Parameters

Common Ant attributes for the `CCUnlock` task and their Groovy AntBuilder DSL mapping:

*   `objselect`: The object to be unlocked. This can be an element path (e.g., `file.txt@@/main/mybranch`), a branch type (e.g., `brtype:my_branch_type@/vobs/my_vob`), a label type (e.g., `lbtype:MY_LABEL@/vobs/my_vob`), or other type selectors.
    *   Groovy: `objselect: "lbtype:RELEASE_1_0@/vobs/project_vob"` or `objselect: "my_file.c@@"` (unlocks the version in view if locked)
*   `pname`: An alternative to `objselect` for specifying an element by its path in the view.
    *   Groovy: `pname: "/view/my_view/src/component.h"`
*   `comment`: A string comment to associate with the unlock operation.
    *   Groovy: `comment: "Unlocking label type after review."`
*   `commentfile`: The path to a file containing the comment. Use either `comment` or `commentfile`.
    *   Groovy: `commentfile: "/path/to/unlock_comment.txt"`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool unlock` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Unlock a previously locked label type:**

    *   **Ant XML:**
        ```xml
        <ccunlock objselect="lbtype:V1.0_FINAL@/vobs/my_project"
                  comment="Unlocking label type V1.0_FINAL for updates."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccunlock(
            objselect: "lbtype:V1.0_FINAL@/vobs/my_project",
            comment: "Unlocking label type V1.0_FINAL for updates."
        )
        ```

2.  **Unlock an element (specified by pname) that was previously locked:**

    *   **Ant XML:**
        ```xml
        <ccunlock pname="/views/integration_view/src/critical_module.c"
                  comment="Security review complete, unlocking module."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccunlock(
            pname: "/views/integration_view/src/critical_module.c",
            comment: "Security review complete, unlocking module."
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **Object Selector Syntax**: The `objselect` syntax can be complex. Refer to `cleartool man unlock` for details.
*   **Permissions**: The user executing the Ant script must have the necessary ClearCase permissions to unlock the specified object. Typically, this means being the user who locked the object, one of the users specified in `nusers` during lock, or a ClearCase administrator.
*   **Object Must Be Locked**: The specified object must actually be locked for the unlock operation to succeed.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
