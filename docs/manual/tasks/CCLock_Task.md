## CCLock Task (Groovy DSL)

### Description

The `CCLock` task is part of Ant's ClearCase integration. It is used to perform a `cleartool lock` command. This command locks an object in a ClearCase VOB (Versioned Object Base), such as an element, branch type, label type, or other type objects. Locking an object can prevent certain operations on it, depending on the type of lock and ClearCase policies.

In Groovy AntBuilder, this task would be invoked as `ant.cclock(...)`.

### Parameters

Common Ant attributes for the `CCLock` task and their Groovy AntBuilder DSL mapping:

*   `objselect`: The object to be locked. This can be an element path (e.g., `file.txt@@/main/mybranch`), a branch type (e.g., `brtype:my_branch_type@/vobs/my_vob`), a label type (e.g., `lbtype:MY_LABEL@/vobs/my_vob`), or other type selectors.
    *   Groovy: `objselect: "lbtype:RELEASE_1_0@/vobs/project_vob"` or `objselect: "my_file.c@@"` (locks the version in view)
*   `pname`: An alternative to `objselect` for specifying an element by its path in the view.
    *   Groovy: `pname: "/view/my_view/src/component.h"`
*   `comment`: A string comment to associate with the lock operation.
    *   Groovy: `comment: "Locking label type to prevent further modifications."`
*   `commentfile`: The path to a file containing the comment. Use either `comment` or `commentfile`.
    *   Groovy: `commentfile: "/path/to/lock_comment.txt"`
*   `replace`: (Boolean) If `true`, replaces an existing lock on the object held by the same user. Defaults to `false`.
    *   Groovy: `replace: true`
*   `nusers`: A comma-separated list of additional users who are authorized to unlock the object. By default, only the user who locked the object (or a ClearCase administrator) can unlock it.
    *   Groovy: `nusers: "user1,user2"`
*   `obsolete`: (Boolean) If `true`, marks the object as obsolete. This is a specific type of lock that typically prevents new versions from being created on a branch or new instances of a type from being created. Defaults to `false`.
    *   Groovy: `obsolete: true` (e.g., for locking a branch type as obsolete)
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool lock` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Lock a label type to prevent its use or modification:**

    *   **Ant XML:**
        ```xml
        <cclock objselect="lbtype:V1.0_FINAL@/vobs/my_project"
                comment="Label type V1.0_FINAL is now locked and archived."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.cclock(
            objselect: "lbtype:V1.0_FINAL@/vobs/my_project",
            comment: "Label type V1.0_FINAL is now locked and archived."
        )
        ```

2.  **Lock an element (version in view) and allow specific users to unlock it:**

    *   **Ant XML:**
        ```xml
        <cclock pname="/views/integration_view/src/critical_module.c"
                nusers="admin_user,lead_developer"
                comment="Module locked pending security review."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.cclock(
            pname: "/views/integration_view/src/critical_module.c",
            nusers: "admin_user,lead_developer",
            comment: "Module locked pending security review."
        )
        ```

3.  **Mark a branch type as obsolete:**

    *   **Ant XML:**
        ```xml
        <cclock objselect="brtype:old_feature_branch@/vobs/my_project" obsolete="true"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.cclock(objselect: "brtype:old_feature_branch@/vobs/my_project", obsolete: true)
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **Object Selector Syntax**: The `objselect` syntax can be complex and specific to ClearCase. Refer to `cleartool man lock` for details on how to specify different types of objects.
*   **Permissions**: The user executing the Ant script must have the necessary ClearCase permissions to lock the specified object.
*   **Impact of Locks**: Understand the implications of locking objects in ClearCase, as it can prevent other users or processes from performing necessary operations.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
