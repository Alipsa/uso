## AntVersion Task (Groovy DSL)

_Since Ant 1.7.0_

### Description

The `antversion` task is used to store the Apache Ant version in a property or to check for a specific Ant version when used as a condition.

In Groovy AntBuilder, the `antversion` task is invoked as `ant.antversion(...)`. Its attributes are passed as named parameters.

### Parameters

When used as a task to set a property:

*   `property`: The name of the property to set with the Ant version. (Required when used as a task). Groovy: `property: "current.ant.version"`
*   `atleast`: If specified, the property is only set if the current Ant version is at least this version (e.g., "1.9.0"). Groovy: `atleast: "1.9.0"`
*   `exactly`: If specified, the property is only set if the current Ant version is exactly this version. Groovy: `exactly: "1.10.1"`

When used as a condition (nested within `<condition>` or similar):

*   `atleast`: Checks if the current Ant version is at least this version. (Exactly one of `atleast` or `exactly` is required for condition use). Groovy: `atleast: "1.8"`
*   `exactly`: Checks if the current Ant version is exactly this version. Groovy: `exactly: "1.10.1"`

### Nested Elements

The `antversion` task itself does not have nested elements when used directly.

### Examples

1.  **Store the current Ant version in a property:**
    ```groovy
    ant.antversion(property: "current.ant.version")
    // To use it:
    ant.echo(message: 'Ant version is ${current.ant.version}') 
    // Or fetch in Groovy:
    // def version = ant.project.getProperty("current.ant.version")
    // ant.echo(message: "Ant version (Groovy): $version")
    ```

2.  **Store Ant version only if it meets a minimum requirement:**

    ```groovy
    ant.antversion(property: "ant.is.1.8plus", atleast: "1.8")
    // Property "ant.is.1.8plus" will be set to the Ant version if current version is >= 1.8
    ```

3.  **Set a property if Ant version is exact:**

    ```groovy
    ant.antversion(property: "ant.is.exactly.1.10.1", exactly: "1.10.1")
    // Property "ant.is.exactly.1.10.1" will be set to the Ant version if current version is 1.10.1
    ```

4.  **Using `antversion` as a condition:**

    *   **Groovy AntBuilder (Conceptual - conditions are often handled differently in Groovy scripts):
        ```groovy
        // Direct equivalent of <condition> with <antversion> is less common in pure Groovy.
        // Typically, you might fetch the version and do a Groovy comparison, or use it within an Ant <condition> task if needed.

        // Example using AntBuilder for the <condition> task itself:
        ant.condition(property: "is.ant.modern") {
            antversion(atleast: "1.9.0")
        }

        // Then, to use the property:
        // This requires the property to be set first. Ant's <fail> task can have a nested <condition>.
        ant.fail(message: "Ant version is too old. Please upgrade to Ant 1.9.0 or later.") {
            condition {
                not {
                    isset(property: "is.ant.modern")
                }
            }
        }
        ```
        *Alternatively, in a more Groovy-idiomatic way, you might check programmatically if not using Ant's `condition` task directly:* 
        ```groovy
        // Fetch version first
        ant.antversion(property: 'temp.ant.version')
        def antVersionString = ant.project.getProperty('temp.ant.version')
        // Basic string comparison (for more robust comparison, parse versions)
        if (antVersionString < '1.9.0') { // This is a simplified string comparison
            // ant.fail(message: "Ant version is too old. Please upgrade to Ant 1.9.0 or later.")
            println "Warning: Ant version might be too old."
        }
        ```

### Navigation

*   [Previous Task: AntStructure Task](AntStructure_Task_Groovy.md)
*   [Next Task: Ant Task](Ant_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
