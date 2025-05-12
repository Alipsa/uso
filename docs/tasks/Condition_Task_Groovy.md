## Condition Task (Groovy DSL)

### Description

The `condition` task in Ant is a powerful tool for setting a property based on whether one or more nested conditions evaluate to true. It generalizes the functionality of tasks like `available` and `uptodate` by providing a flexible framework for conditional logic within Ant builds.

If the overall nested condition is true, the specified property is set (by default to the string "true"). If it's false, the property remains unset, unless an `else` value is provided.

In Groovy AntBuilder, the `condition` task is invoked as `ant.condition(...)`, with the actual conditions specified as nested method calls within a closure.

### Parameters

Common Ant attributes for the `condition` task and their Groovy AntBuilder DSL mapping:

*   `property`: The name of the property to set if the condition is true. (Required)
    *   Groovy: `property: "my.conditional.property"`
*   `value`: The value to set the property to if the condition is true. Defaults to "true".
    *   Groovy: `value: "ConditionMet"`
*   `else`: The value to set the property to if the condition evaluates to false. If not specified, the property remains unset if the condition is false.
    *   Groovy: `elsevalue: "ConditionNotMet"` (Note: Ant XML uses `else`, but AntBuilder often uses `elsevalue` for this attribute in the `condition` task, or it might be directly `else` depending on the Ant version / AntBuilder specifics. It's best to check with `ant.project.taskdef` for exact attribute names if unsure, but `elsevalue` is a common pattern for attributes named `else` in XML to avoid keyword clashes in some programmatic contexts. For direct AntBuilder usage, `else` is often the correct attribute name as per the Ant task definition.) Let's assume `else` based on the Ant manual for the task itself.
    *   Groovy: `else: "ConditionNotMet"`

### Nested Conditions

The `condition` task requires exactly one nested condition. This can be a single condition (like `<os family="unix"/>`) or a compound condition (like `<and>...</and>`). Ant provides a wide array of built-in conditions. Many of these translate to method calls within the `ant.condition` closure in Groovy.

Common nested conditions include:

*   **`os`**: Checks operating system properties.
    *   Groovy: `os(family: "unix")`, `os(name: "Windows 10", arch: "amd64")`
*   **`available`**: Checks if a class or resource is available.
    *   Groovy: `available(classname: "javax.mail.Transport")`, `available(file: "/opt/myapp/config.xml", type: "file")`
*   **`uptodate`**: Checks if a target file is up-to-date with respect to source file(s).
    *   Groovy: `uptodate(targetfile: "output.jar") { srcfiles(dir: "src", includes: "**/*.java") }`
*   **`equals`**: Compares two values.
    *   Groovy: `equals(arg1: "
${some.property}", arg2: "expectedValue")`
*   **`isset`**: Checks if a property has been set.
    *   Groovy: `isset(property: "my.flag")`
*   **`istrue`**: Checks if a property evaluates to true.
    *   Groovy: `istrue(value: "
${boolean.property}")`
*   **`isfalse`**: Checks if a property evaluates to false.
    *   Groovy: `isfalse(value: "
${boolean.property}")`
*   **Logical Conditions**: `and`, `or`, `not` to combine other conditions.
    *   Groovy:
        ```groovy
        and {
            available(classname: "com.example.Main")
            os(family: "windows")
        }
        ```
        ```groovy
        or {
            isset(property: "override.flag")
            equals(arg1: "
${env.MODE}", arg2: "test")
        }
        ```
        ```groovy
        not {
            os(family: "mac")
        }
        ```
*   **Filesystem Conditions**: `filesmatch`, `resourcecontains`, `resourceexists`, etc.
*   **Network Conditions**: `http`, `socket`.
*   **Other**: `checksum`, `isreference`, `typefound`, `scriptcondition`, `resourcesmatch`, `isfileselected`.

A comprehensive list of standard conditions can be found in the Apache Ant Manual under "Conditions". Most of these can be used as corresponding method calls within the `ant.condition` closure.

### Examples

1.  **Set `java.present` if a Java class is available:**

    *   **Ant XML:**
        ```xml
        <condition property="java.present">
            <available classname="java.lang.String"/>
        </condition>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.condition(property: "java.present") {
            available(classname: "java.lang.String")
        }
        // ant.echo(message: "Java present: 
${ant.project.getProperty("java.present")}")
        ```

2.  **Set `env.type` to "production" if `mode` property is "prod", otherwise to "development":**

    *   **Ant XML:**
        ```xml
        <condition property="env.type" value="production" else="development">
            <equals arg1="
${mode}" arg2="prod"/>
        </condition>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.condition(property: "env.type", value: "production", else: "development") {
            equals(arg1: ant.project.getProperty("mode"), arg2: "prod")
        }
        // ant.project.setProperty("mode", "prod") // for testing
        // ant.echo(message: "Environment type: 
${ant.project.getProperty("env.type")}")
        ```

3.  **Set `can.deploy` if JavaMail and JAF are available:**

    *   **Ant XML:**
        ```xml
        <condition property="can.deploy">
            <and>
                <available classname="javax.activation.DataHandler"/>
                <available classname="javax.mail.Transport"/>
            </and>
        </condition>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.condition(property: "can.deploy") {
            and {
                available(classname: "javax.activation.DataHandler")
                available(classname: "javax.mail.Transport")
            }
        }
        // ant.echo(message: "Can deploy: 
${ant.project.getProperty("can.deploy")}")
        ```

### Alternative in Groovy

While `ant.condition` provides a direct translation, often in Groovy, you might use native Groovy `if/else` constructs to achieve similar logic, especially if the goal isn't strictly to set an Ant property but to control script flow:

```groovy
// Example: Direct Groovy conditional logic
if (org.apache.tools.ant.taskdefs.condition.Os.isFamily("unix")) {
    ant.echo(message: "Running on a Unix-like system.")
    ant.project.setProperty("isUnix", "true")
} else {
    ant.echo(message: "Not running on a Unix-like system.")
    ant.project.setProperty("isUnix", "false")
}
```
However, if you need to set an Ant property that other Ant tasks (potentially defined in XML and imported, or other parts of a complex build) will rely on, using `ant.condition` ensures the property is set within the Ant project context correctly.

### Navigation

*   [Previous Task: Concat Task](Concat_Task_Groovy.md)
*   [Next Task: Copy Task](Copy_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
