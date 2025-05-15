## Deltree Task (Groovy DSL)

### _Deprecated_

**This task has been deprecated since Ant 1.2. Users should use the [Delete Task](Delete_Task_Groovy.md) instead.**

### Description

The `deltree` task was used to delete a directory along with all its files and subdirectories. Its functionality is now fully covered and superseded by the more versatile `delete` task.

### Original Parameters

*   `dir`: The directory to delete.
    *   Required: Yes

### Groovy AntBuilder Equivalent (using the recommended `delete` task)

Since `deltree` is deprecated, you should use `ant.delete` with the `dir` attribute to achieve the same outcome:

```groovy
// Example of how you would have used deltree, and how to do it now:

ant = new AntBuilder()

// Deprecated deltree usage (conceptual - don't use)
// ant.deltree(dir: "path/to/your_directory")

// Recommended modern equivalent using the delete task:
ant.delete(dir: "path/to/your_directory")
```

This will delete the specified directory and all its contents recursively.

### Examples (from original `deltree` documentation, translated to modern `delete`)

1.  **Delete the directory `dist`, including its files and subdirectories.**

    *   **Original Ant XML (`deltree`):**
        ```xml
        <deltree dir="dist"/>
        ```
    *   **Recommended Groovy AntBuilder (using `delete`):**
        ```groovy
        ant.delete(dir: "dist")
        ```

2.  **Delete the directory specified by an Ant property `${dist}`.**

    *   **Original Ant XML (`deltree`):**
        ```xml
        <deltree dir="${dist}"/>
        ```
    *   **Recommended Groovy AntBuilder (using `delete`):**
        ```groovy
        // Assuming 'distDir' is a Groovy variable holding the path, or an Ant property
        // If it's an Ant property set earlier:
        // ant.property(name: 'dist', location: 'target/distribution')
        ant.delete(dir: ant.project.getProperty("dist"))

        // Or, if 'dist' is a Groovy variable:
        // def distPath = 'target/distribution'
        // ant.delete(dir: distPath)
        ```

### Navigation

*   [Previous Task: Delete Task](Delete_Task_Groovy.md)
*   [Next Task: Depend Task](Depend_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)

