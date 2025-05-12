## CCUpdate Task (Groovy DSL)

### Description

The `CCUpdate` task is part of Ant's ClearCase integration. It is used to perform a `cleartool update` command, which updates a ClearCase snapshot view by bringing in new versions of elements from the VOB (Versioned Object Base) according to the view's configuration specification (config spec). It can also handle hijacked files (files modified locally without being checked out).

In Groovy AntBuilder, this task would be invoked as `ant.ccupdate(...)`.

### Parameters

Common Ant attributes for the `CCUpdate` task and their Groovy AntBuilder DSL mapping:

*   `viewpath`: The path to the ClearCase snapshot view file or directory that the command will operate on. If omitted, it often defaults to the current directory if it's within a snapshot view.
    *   Groovy: `viewpath: "c:/views/my_snapshot_view/"` or `viewpath: "/path/to/my_snapshot_view/project_module"`
*   `graphical`: (Boolean) If `true`, displays a graphical dialog during the update process (if supported by `cleartool`). Defaults to `false`.
    *   Groovy: `graphical: true`
*   `log`: Specifies a path to a log file where ClearCase will write update details.
    *   Groovy: `log: "/path/to/logs/cc_update.log"`
*   `overwrite`: (Boolean) If `true`, specifies that hijacked files (modified without checkout) should be overwritten with the version from the VOB. Defaults to `false`. Mutually exclusive with `rename`.
    *   Groovy: `overwrite: true`
*   `rename`: (Boolean) If `true`, specifies that hijacked files should be renamed with a `.keep` extension before being replaced by the version from the VOB. Defaults to `false`. Mutually exclusive with `overwrite`.
    *   Groovy: `rename: true`
*   `currenttime`: (Boolean) If `true`, sets the modification time of updated files to the current time. Mutually exclusive with `preservetime`. Defaults to `false`.
    *   Groovy: `currenttime: true`
*   `preservetime`: (Boolean) If `true`, attempts to preserve the modification time of files from the VOB. Mutually exclusive with `currenttime`. Defaults to `false` (behavior might depend on ClearCase version/config).
    *   Groovy: `preservetime: true`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool update` command returns an error.
    *   Groovy: `failonerr: false`

### Examples

1.  **Update a snapshot view, logging output and overwriting hijacked files:**

    *   **Ant XML:**
        ```xml
        <ccupdate viewpath="c:/views/mysnapshotview"
                  log="c:/logs/update.txt"
                  overwrite="true"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccupdate(
            viewpath: "c:/views/mysnapshotview",
            log: "c:/logs/update.txt",
            overwrite: true
        )
        ```

2.  **Update a specific directory within a view, renaming hijacked files and preserving VOB time:**

    *   **Ant XML:**
        ```xml
        <ccupdate viewpath="/home/user/cc_views/project_snap/src"
                  rename="true"
                  preservetime="true"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccupdate(
            viewpath: "/home/user/cc_views/project_snap/src",
            rename: true,
            preservetime: true
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **Snapshot Views Only**: The `update` command is primarily for ClearCase snapshot views. Dynamic views are updated differently (usually by changes to the config spec and view access).
*   **Hijacked Files**: Be cautious with `overwrite` and `rename` options. `overwrite: true` will discard local changes to hijacked files. `rename: true` preserves them but might clutter the workspace if not managed.
*   **Conflicts**: The update process can lead to conflicts if local changes conflict with changes from the VOB. These typically need manual resolution.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
