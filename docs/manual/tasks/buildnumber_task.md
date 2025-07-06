## BuildNumber Task (Groovy DSL)

### Description

The `buildnumber` task in Ant is designed to manage and track build numbers. It typically reads a build number from a specified file (defaulting to `build.number` in the current directory), sets an Ant property (defaulting to `build.number`) to this value (or 0 if the file doesn't exist or is empty), increments the number, and then writes the new number back to the file.

When using Groovy with AntBuilder, you can utilize the `buildnumber` task by calling `ant.buildnumber(...)`. The primary parameter for this task is the `file` attribute, which specifies the file to read from and write to.

### Parameters

*   `file`: The path to the file used for storing and retrieving the build number. If not specified, it defaults to a file named `build.number` in the current working directory.
    *   Groovy: `file: 'my_build_tracker.txt'`

### How it Works Internally (and with Groovy)

1.  **Read Phase**: The task attempts to open and read a number from the file specified by the `file` attribute (or `build.number` by default).
2.  **Property Setting**: 
    *   If a number is successfully read, the Ant property `build.number` is set to this value.
    *   If the file doesn't exist, is empty, or doesn't contain a valid number, `build.number` is set to `0`.
3.  **Increment and Write Phase**: The numeric value held in `build.number` (or the one just read/initialized) is incremented by 1. This new value is then written back to the specified file, overwriting its previous content.

### Examples

1.  **Default Behavior (using `build.number` file):**

    ```groovy
    // Ensure build.number file exists or will be created
    // For the first run, if build.number doesn't exist or is empty, ${build.number} will be 0, then 1 after increment.
    // On subsequent runs, it will read the last number, increment, and update.

    buildnumber()
    echo(message: 'Current Build Number: ${build.number}')
    ```

2.  **Using a Custom File:**

    ```groovy
    // Create the file if it doesn't exist for the first run
    // new File('custom_build_count.txt').text = '0'

    buildnumber(file: "custom_build_count.txt")
    def customBuild = project.getProperty("build.number") // Still uses 'build.number' property
    println "Current Custom Build Number: $customBuild"

    // If you run it again:
    // ant.buildnumber(file: "custom_build_count.txt")
    // customBuild = ant.project.getProperty("build.number")
    // println "Next Custom Build Number: $customBuild"
    ```

### Important Considerations

*   **Property Name**: The `buildnumber` task, by default, sets the Ant property named `build.number`. If you need to use a different property name for the build number within your Groovy script or other Ant tasks, you would typically use the `propertyfile` task for more advanced property management, as `buildnumber` itself doesn't offer an attribute to change the *name* of the property it sets.
*   **File Handling**: Ensure the specified file is writable by the Ant process. If the file doesn't exist, Ant will attempt to create it.
*   **Concurrency**: If multiple Ant processes might run concurrently in the same directory and use the same build number file, this could lead to race conditions where the build number is not updated correctly. This task is generally intended for single-instance build processes.

### Navigation

*   [Previous Task: Bindtargets Task](Bindtargets_Task_Groovy.md)
*   [Next Task: Cab Task](Cab_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
