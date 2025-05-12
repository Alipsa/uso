## CCMkbl Task (Groovy DSL)

### Description

The `CCMkbl` task is part of Ant's ClearCase integration. It is used to perform a `cleartool mkbl` (make baseline) command. This command creates a new baseline in a UCM (Unified Change Management) project VOB. Baselines capture a specific configuration of components and are fundamental to UCM workflows for tracking and managing versions of software.

In Groovy AntBuilder, this task would be invoked as `ant.ccmkbl(...)`.

### Parameters

Common Ant attributes for the `CCMkbl` task and their Groovy AntBuilder DSL mapping:

*   `blname`: The name for the new baseline (e.g., "MY_PROJECT_BUILD_25"). (Required)
    *   Groovy: `blname: "MYAPP_RELEASE_2_1_0_BL"`
*   `component`: The UCM component for which the baseline is being created. (Required if not using `viewpath` to infer component from a stream attached to the view).
    *   Groovy: `component: "MyApplication_Component@/vobs/my_project_pvob"`
*   `stream`: The UCM stream on which the baseline will be created. (Required if not using `viewpath` to infer stream).
    *   Groovy: `stream: "MyApplication_Integration_Stream@/vobs/my_project_pvob"`
*   `viewpath`: Path to a ClearCase view. This can be used to infer the stream and component if the view is attached to a UCM stream. If `component` and `stream` are provided, `viewpath` might be used for context or could be optional.
    *   Groovy: `viewpath: "/path/to/my_ucm_view/"`
*   `comment`: A string comment to associate with the baseline creation.
    *   Groovy: `comment: "Baseline for Release 2.1.0, includes all features for sprint 5."`
*   `commentfile`: The path to a file containing the comment. Use either `comment` or `commentfile`.
    *   Groovy: `commentfile: "/path/to/baseline_comment.txt"`
*   `identical`: (Boolean) If `true`, allows the baseline to be created even if it is identical to the previous baseline on the stream for that component. Defaults to `false`.
    *   Groovy: `identical: true`
*   `full`: (Boolean) If `true`, creates a full baseline. If `false`, creates an incremental baseline. The default behavior might depend on ClearCase UCM policies or `cleartool` defaults.
    *   Groovy: `full: true`
*   `nlabel`: (Boolean) If `true`, suppresses the automatic creation of a label on the versions selected by the baseline. Defaults to `false` (meaning a label is typically created).
    *   Groovy: `nlabel: true`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool mkbl` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Create a full baseline for a component on a specific stream:**

    *   **Ant XML:**
        ```xml
        <ccmkbl blname="PROJECT_X_ITERATION_3_BL"
                component="ProjectX_Core@/vobs/projx_pvob"
                stream="ProjectX_Dev_Stream@/vobs/projx_pvob"
                full="true"
                comment="Baseline for end of Iteration 3."/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmkbl(
            blname: "PROJECT_X_ITERATION_3_BL",
            component: "ProjectX_Core@/vobs/projx_pvob",
            stream: "ProjectX_Dev_Stream@/vobs/projx_pvob",
            full: true,
            comment: "Baseline for end of Iteration 3."
        )
        ```

2.  **Create an incremental baseline using view context, allowing identical:**

    *   **Ant XML:**
        ```xml
        <ccmkbl blname="MY_COMPONENT_DAILY_BL"
                viewpath="c:/views/my_ucm_snapshot_view"
                identical="true"
                commentfile="c:/build/comments/daily_bl_comment.txt"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccmkbl(
            blname: "MY_COMPONENT_DAILY_BL",
            viewpath: "c:/views/my_ucm_snapshot_view",
            identical: true,
            commentfile: "c:/build/comments/daily_bl_comment.txt"
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **UCM Context**: This task is specific to ClearCase UCM. The VOBs involved (`pvob` for project VOB, and component VOBs) must be UCM-enabled.
*   **Stream and Component**: Correctly identifying the target stream and component is crucial. These are typically UCM objects ending with `@/vobs/your_pvob_name`.
*   **Permissions**: The user executing the Ant script must have the necessary ClearCase UCM permissions to create baselines on the specified stream and component.
*   **Baseline Naming Conventions**: Follow established baseline naming conventions for your project.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
