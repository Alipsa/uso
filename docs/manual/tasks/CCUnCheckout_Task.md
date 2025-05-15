## CCUnCheckout Task (Groovy DSL)

### Description

The `CCUnCheckout` task is part of Ant's ClearCase integration. It performs a `cleartool uncheckout` command, which cancels a previous checkout operation. This reverts the file or directory to its state before the checkout, discarding any changes made since the checkout. If the checkout was reserved, the reservation is also removed.

In Groovy AntBuilder, this task would be invoked as `ant.ccuncheckout(...)`.

### Parameters

Common Ant attributes for the `CCUnCheckout` task and their Groovy AntBuilder DSL mapping:

*   `viewpath`: The path to the ClearCase view file or directory that the command will operate on. This is the element to be un-checked-out.
    *   Groovy: `viewpath: "/path/to/view/my_module/src/file.java"` or `viewpath: "c:/views/my_view/src/component.h"`
*   `keepcopy`: (Boolean) If `true`, specifies that a copy of the modified file should be kept with a `.keep` extension (e.g., `file.java.keep`). If `false` (the default), the modified file is deleted or reverted without a backup copy.
    *   Groovy: `keepcopy: true`
*   `failonerr`: (Boolean) If `true` (the default), the Ant build will fail if the `cleartool uncheckout` command returns an error.
    *   Groovy: `failonerr: false` (to allow the build to continue even if uncheckout fails, though this is generally not recommended unless specifically handled).

### Examples

1.  **Un-checkout a file, discarding changes:**

    *   **Ant XML:**
        ```xml
        <ccuncheckout viewpath="/vobs/projectA/src/foo.c"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccuncheckout(viewpath: "/vobs/projectA/src/foo.c")
        ```

2.  **Un-checkout a file, but keep a copy of the local modifications:**

    *   **Ant XML:**
        ```xml
        <ccuncheckout viewpath="c:/views/my_view/src/bar.java" keepcopy="true"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ccuncheckout(
            viewpath: "c:/views/my_view/src/bar.java",
            keepcopy: true
        )
        ```

### Important Considerations

*   **`cleartool` Dependency**: The `cleartool` executable must be in the system PATH.
*   **View Context**: The `viewpath` must point to an element within an active ClearCase view.
*   **Checked-out State**: The element must currently be checked out in the view for the uncheckout operation to be meaningful.
*   **Data Loss**: If `keepcopy` is `false` (the default), any local modifications made to the file since it was checked out will be lost. Ensure this is the intended behavior.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
