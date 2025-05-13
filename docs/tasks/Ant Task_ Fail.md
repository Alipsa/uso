# Ant Task: Fail

## Original Ant Task Description

The `fail` task in Apache Ant is designed to intentionally halt the current build process. This is typically used when a certain condition is met (or not met) that makes further execution of the build pointless or erroneous. It achieves this by throwing a `BuildException`.

One can specify a message to be displayed when the build fails. This message can be provided either through the `message` attribute or as character data nested within the `<fail>` element itself.

## Groovy AntBuilder DSL Equivalent

In Groovy using `AntBuilder`, the `fail` task can be invoked similarly to its XML counterpart. You can pass attributes as named arguments to the `fail()` method.

```groovy
ant = new AntBuilder()

// Simple fail with a message
ant.fail(message: "A critical error occurred, build cannot continue.")

// Conditional fail based on a property
if (!project.properties.containsKey("required.property")) {
    ant.fail(message: "Required property 'required.property' is not set.")
}

// Conditional fail using a nested condition (conceptual)
// Note: Direct equivalent for nested <condition> might require more complex Groovy logic
// or might not be directly supported by AntBuilder's fail().
// Typically, the condition would be evaluated in Groovy before calling fail().
boolean someCondition = false // Example condition
if (someCondition) {
    ant.fail(message: "Condition met, failing build.")
}
```

## Parameter Mapping

The parameters available for the `fail` task in Ant XML can be mapped to arguments when calling it via `AntBuilder` in Groovy.

| Ant Attribute | Groovy Parameter | Description                                                                                                | Required |
|---------------|------------------|------------------------------------------------------------------------------------------------------------|----------|
| `message`     | `message`        | A message providing further information on why the build exited.                                           | No       |
| `if`          | `if`             | Only fail if a property of the given name exists in the current project.                                   | No       |
| `unless`      | `unless`         | Only fail if a property of the given name doesn't exist in the current project.                            | No       |
| `status`      | `status`         | Exit using the specified status code; assuming the generated Exception is not caught, the JVM will exit with this status. (Since Apache Ant 1.6.2) | No       |

### Nested Elements

*   **`<condition>`**: As an alternative to `if`/`unless` attributes, a nested `<condition>` element can be used to specify the condition for failure. This element should contain exactly one core or custom condition. (Since Ant 1.6.2)

    In Groovy, this conditional logic would typically be handled by Groovy's own conditional statements before deciding to call `ant.fail()`.

## Code Examples

### Example 1: Simple Fail with Message

**Ant XML:**
```xml
<fail message="This build has failed due to a specific reason."/>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().fail(message: "This build has failed due to a specific reason.")
```

### Example 2: Conditional Fail if a Property is Set

**Ant XML:**
```xml
<property name="critical.error" value="true"/>
<fail message="Critical error detected." if="critical.error"/>
```

**Groovy AntBuilder DSL:**
```groovy
def ant = new AntBuilder()
def criticalError = true // Simulating a property or condition

if (criticalError) {
    ant.fail(message: "Critical error detected.")
} else {
    println "No critical error, proceeding."
}
```

### Example 3: Conditional Fail if a Property is NOT Set (unless)

**Ant XML:**
```xml
<fail message="Required property 'db.password' not found." unless="db.password"/>
```

**Groovy AntBuilder DSL:**
```groovy
def ant = new AntBuilder()
def dbPasswordExists = project.properties.containsKey("db.password")

if (!dbPasswordExists) {
    ant.fail(message: "Required property 'db.password' not found.")
} else {
    println "db.password found, proceeding."
}
```

### Example 4: Fail with a specific status code

**Ant XML:**
```xml
<fail message="Exiting with status 1." status="1"/>
```

**Groovy AntBuilder DSL:**
```groovy
// Note: The 'status' attribute directly influences the JVM exit code.
// In a typical script execution, you might not directly observe this
// unless the script is the main entry point of a larger process.
new AntBuilder().fail(message: "Exiting with status 1.", status: "1")
```

### Example 5: Fail based on a nested condition (conceptual Groovy equivalent)

**Ant XML (Illustrative of the concept):**
```xml
<condition property="is.windows">
  <os family="windows" />
</condition>
<fail message="This script only runs on Linux." unless="is.windows"/>
```

**Groovy AntBuilder DSL (Conceptual - condition checked in Groovy):**
```groovy
def ant = new AntBuilder()
def osName = System.getProperty("os.name").toLowerCase()

if (!osName.contains("linux")) { // Example: Check if not Linux
    ant.fail(message: "This script is intended for Linux environments only.")
} else {
    println "Running on Linux."
}
```

## Notes

*   The `fail` task is a straightforward way to terminate a build when specific criteria are met, preventing further potentially incorrect or damaging operations.
*   When using `if` or `unless` attributes, ensure the specified properties are correctly set or unset within the Ant project's context.
*   In Groovy, standard conditional logic (`if/else`) can be used to determine whether to call `ant.fail()`, providing more flexibility than the XML attributes for complex conditions.
*   The `status` attribute can be useful for scripting scenarios where the exit code of the Ant build is checked by an external process.

