# Ant Task: Jarlib-display

## Original Ant Task Description

Displays the "Optional Package" and "Package Specification" information contained within the specified JARs. This task is useful for inspecting the extension information embedded in JAR files according to the Java Optional Package specification.

Note that this task works with extensions as defined by the "Optional Package" specification. For more information about optional packages, see the document _Optional Package Versioning_ in the documentation bundle for your Java Standard Edition package, or the online [Extension and ExtensionSet documentation](https://ant.apache.org/manual/Types/extension.html).

## Parameters

| Attribute | Description                                          | Required                                      |
|-----------|------------------------------------------------------|-----------------------------------------------|
| `file`    | The JAR file to display extension information about. | Yes, unless a nested `<fileset>` is specified |

### Parameters specified as nested elements

#### `fileset`
A standard Ant [FileSet](https://ant.apache.org/manual/Types/fileset.html) used to specify a collection of JAR files for which to display extension information.

## Groovy AntBuilder DSL Equivalent

The `jarlib-display` Ant task can be used in Groovy scripts with `AntBuilder`. The Ant attributes and nested elements map directly to Groovy method parameters and closures.

### Key Parameters (and their Groovy equivalents):

| Ant Attribute | Groovy Parameter Type | Description                                      | Required                                  |
|---------------|-----------------------|--------------------------------------------------|-------------------------------------------|
| `file`        | `String`              | The JAR file to display extension information about. | Yes, unless a nested `fileset` is specified |

### Nested Elements in Groovy:

*   **`fileset`**: Corresponds to the `<fileset>` Ant element.
    *   `dir`: The directory to scan for files.
    *   `includes` / `include`: Patterns for files to include.
    *   `excludes` / `exclude`: Patterns for files to exclude.

## Example Ant XML Usage

### Example 1: Display Extension info for a single file

```xml
<jarlib-display file="myfile.jar"/>
```
*Description: This example displays the Optional Package and Package Specification information for `myfile.jar`.*

### Example 2: Display Extension info for a fileset

```xml
<jarlib-display>
  <fileset dir="lib">
    <include name="*.jar"/>
  </fileset>
</jarlib-display>
```
*Description: This example displays the extension information for all JAR files found in the `lib` directory.*

## Example Groovy DSL Usage

### Example 1: Display Extension info for a single file (Groovy)

```groovy
def ant = new AntBuilder()
ant.jarlibDisplay(file: "myfile.jar")

// Output will be sent to Ant's log
```
*Description: This Groovy example uses AntBuilder to display extension information for `myfile.jar`.*

### Example 2: Display Extension info for a fileset (Groovy)

```groovy
def ant = new AntBuilder()
ant.jarlibDisplay {
    fileset(dir: "lib") {
        include(name: "*.jar")
    }
}

// Output for all JARs in 'lib' will be sent to Ant's log
```
*Description: This Groovy example displays extension information for all JAR files within the `lib` directory.*

## Important Considerations

*   The primary output of this task is to Ant's logging system.
*   Ensure the JAR files exist and are accessible.
*   This task is helpful for verifying that JARs correctly declare their extension information as per the Java Optional Package mechanism.

