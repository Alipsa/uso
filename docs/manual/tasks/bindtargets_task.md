## Bindtargets Task (Groovy DSL)

### Description

The `bindtargets` task is used in Ant to make a target the extension of a defined extension point. This means it establishes a dependency relationship where the specified targets become prerequisites for the extension point. This is particularly useful in complex build scenarios where different parts of a build process are modularized and might be contributed from different build files or projects.

In Groovy AntBuilder, the `bindtargets` task is invoked using `ant.bindtargets(...)`. The key parameters specify the target(s) to be bound and the extension point they should be bound to.

### Parameters

Common Ant attributes for the `bindtargets` task and their Groovy AntBuilder DSL mapping:

*   `targets`: A comma-separated list of target names that need to be executed before the extension point. (Required)
    *   Groovy: `targets: "compile,test"`
*   `extensionPoint`: The name of the target that serves as the extension point. (Required)
    *   Groovy: `extensionPoint: "deploy"`
*   `onMissingExtensionPoint`: Defines the behavior if the specified extension point is not found. Options are typically "fail" (default), "warn", or "ignore".
    *   Groovy: `onMissingExtensionPoint: "warn"` (Example)

### Examples

Let's say you have a build process where `compile` and `test` are targets that must complete before a `deploy` target (acting as an extension point) can run.

1.  **Basic Usage:**

    ```groovy
    bindtargets(targets: "compile,test", extensionPoint: "deploy")
    ```
    This ensures that `compile` and `test` are executed as dependencies if `deploy` is invoked.

2.  **Handling Missing Extension Point (Example):**

    ```groovy
    bindtargets(targets: "preprocess-data", extensionPoint: "data-analysis", onMissingExtensionPoint: "warn")
    ```
    In this case, if the `data-analysis` extension point isn't found, a warning will be issued instead of failing the build.

### Important Considerations

*   The `bindtargets` task is typically used at the top level of a build script (not within another target) to define global build dependencies and workflows.
*   Ensure that the target names specified in `targets` and `extensionPoint` accurately reflect existing targets in your Ant build configuration.
*   The order of execution will respect these bindings, meaning the `targets` will run before the `extensionPoint` if the `extensionPoint` target is invoked.

### Navigation

*   [Previous Task: Basename Task](Basename_Task_Groovy.md)
*   [Next Task: BuildNumber Task](BuildNumber_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
