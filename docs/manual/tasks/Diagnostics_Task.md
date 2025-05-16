## Diagnostics Task (Groovy DSL)

_Since Ant 1.7.0_

### Description

The `diagnostics` task runs Apache Ant's internal `-diagnostics` command from within a build script. This is primarily useful for debugging Ant's configuration, classpath, loaded libraries, and other environmental details, especially when running Ant embedded within an IDE or another application where direct command-line access to `ant -diagnostics` might be less convenient.

In Groovy AntBuilder, this task is invoked simply as `ant.diagnostics()`.

### Parameters

This task has no parameters.

### Examples

Print out the current Ant diagnostics dump.

*   **Ant XML:**
    ```xml
    <target name="run_diagnostics" description="Run Ant diagnostics">
        <diagnostics/>
    </target>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    // build.groovy
    ant = new AntBuilder()

    // Define a block to run diagnostics (equivalent to a target)
    ant.project.target(name: 'run_diagnostics') {
        ant.diagnostics()
    }

    // To execute this specific block if it were a default target or called:
    // ant.project.executeTarget('run_diagnostics') 
    // Or simply, if you just want to run it directly in a script:
    // ant.diagnostics()
    ```
    Running `ant.diagnostics()` will print a wealth of information to Ant's log output, including:
    *   Ant version
    *   JVM version and details
    *   OS details
    *   Classpath information (system, Ant home, user)
    *   Available tasks and types
    *   System properties
    *   And more.

### Important Considerations

*   **Output**: The output of the `diagnostics` task is sent to Ant's standard logging mechanism. Where this appears depends on how Ant is being run (e.g., console, IDE log window).
*   **Use Case**: Primarily for debugging and understanding the Ant runtime environment. It's not typically part of a production build's core logic.

### Navigation

*   [Previous Task: Dependset Task](Dependset_Task_Groovy.md)
*   [Next Task: Dirname Task](Dirname_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)

