# Ant Task: Filter

## Original Ant Task Description

The `filter` task in Apache Ant is used to set token filters for a project or to read multiple token filter definitions from a properties file. These filters are then applied by tasks that perform file copying operations, allowing for dynamic replacement of placeholder tokens within files.

The task typically uses `@` as the token separator. For example, if a file contains `@year@`, the `filter` task can replace this token with a specified value during a copy operation.

## Groovy AntBuilder DSL Equivalent

When using Groovy with `AntBuilder`, the `filter` task can be invoked to define token-value pairs or to specify a filter file. These filters will then be active for subsequent file operations within the same Ant execution context.

### Defining a single token-value filter:
```groovy
ant = new AntBuilder()
ant.filter(token: "myToken", value: "replacementValue")

// Example: If a file contains "@myToken@", it will be replaced by "replacementValue"
// when processed by a subsequent copy task with filtering enabled.
```

### Defining filters from a properties file:
```groovy
ant = new AntBuilder()
// Assuming 'myfilters.properties' contains lines like:
// token1=value1
// token2=value2
ant.filter(filtersfile: "myfilters.properties")

// Subsequent copy operations with filtering will use these definitions.
```

## Parameter Mapping

The Ant XML attributes for the `filter` task map to parameters in the Groovy `AntBuilder` method call.

| Ant Attribute | Groovy Parameter | Description                                                                                                | Required                                                |
|---------------|------------------|------------------------------------------------------------------------------------------------------------|---------------------------------------------------------|
| `token`       | `token`          | The token string (without separators) to be replaced.                                                      | Yes, if `value` is specified and `filtersfile` is not.  |
| `value`       | `value`          | The string that will replace the token.                                                                    | Yes, if `token` is specified and `filtersfile` is not.  |
| `filtersfile` | `filtersfile`    | The path to a properties file containing token-value pairs. Each line should be `token=value`.             | Yes, if `token` and `value` are not specified.          |

## Code Examples

### Example 1: Replacing a single token in a text file

**Ant XML:**
```xml
<project name="FilterExample1" default="run">
    <target name="prepare">
        <mkdir dir="source_files"/>
        <mkdir dir="destination_files"/>
        <echo file="source_files/template.txt">Hello @USERNAME@, welcome!</echo>
    </target>
    <target name="run" depends="prepare">
        <filter token="USERNAME" value="SpecificUser"/>
        <copy todir="destination_files" filtering="true">
            <fileset dir="source_files"/>
        </copy>
        <echo>Copied and filtered. Check destination_files/template.txt</echo>
    </target>
</project>
```

**Groovy AntBuilder DSL:**
```groovy
// Create dummy files for the example to run
new File("source_files").mkdir()
new File("destination_files").mkdir()
new File("source_files/template.txt").write("Hello @USERNAME@, welcome!")

new AntBuilder().sequential {
    filter(token: "USERNAME", value: "SpecificUserGroovy")
    copy(todir: "destination_files", filtering: "true") {
        fileset(dir: "source_files")
    }
    println "Groovy: Copied and filtered. Check destination_files/template.txt for USERNAME replaced by SpecificUserGroovy."
}
```

### Example 2: Using a filters file

**Create a file named `my.filters` with the following content:**
```properties
PRODUCT_NAME=AwesomeApp
VERSION=1.0.0
RELEASE_DATE=2023-10-26
```

**Ant XML:**
```xml
<project name="FilterExample2" default="run">
    <target name="prepare">
        <mkdir dir="source_files_props"/>
        <mkdir dir="destination_files_props"/>
        <echo file="source_files_props/product_info_template.txt">Product: @PRODUCT_NAME@
Version: @VERSION@
Released: @RELEASE_DATE@</echo>
    </target>
    <target name="run" depends="prepare">
        <filter filtersfile="my.filters"/>
        <copy todir="destination_files_props" filtering="true">
            <fileset dir="source_files_props"/>
        </copy>
        <echo>Copied and filtered using properties file. Check destination_files_props/product_info_template.txt</echo>
    </target>
</project>
```

**Groovy AntBuilder DSL:**
```groovy
// Ensure my.filters file exists from the XML example or create it:
new File("my.filters").write("PRODUCT_NAME=AwesomeAppGroovy\nVERSION=2.0.0\nRELEASE_DATE=2024-01-15")

// Create dummy files for the example to run
new File("source_files_props_groovy").mkdir()
new File("destination_files_props_groovy").mkdir()
new File("source_files_props_groovy/product_info_template.txt").write("Product: @PRODUCT_NAME@\nVersion: @VERSION@\nReleased: @RELEASE_DATE@")

new AntBuilder().sequential {
    filter(filtersfile: "my.filters")
    copy(todir: "destination_files_props_groovy", filtering: "true") {
        fileset(dir: "source_files_props_groovy")
    }
    println "Groovy: Copied and filtered using properties file. Check destination_files_props_groovy/product_info_template.txt"
}
```

## Notes

*   The `filter` task sets global filters for the Ant project. If you need to apply different filters to different copy operations, you might need to call `filter` multiple times with different values, or use `<filterset>` within the `<copy>` task for more localized filtering.
*   The `filtering="true"` attribute must be set on tasks like `<copy>` or `<move>` for the defined filters to be applied.
*   Tokens in the source files are typically enclosed in `@` symbols (e.g., `@TOKEN@`). The `filter` task itself does not require these separators in its `token` attribute.
*   If a token is defined both by a `filter` task and a `filterset` within a copy task, the `filterset`'s value will typically take precedence for that specific copy operation.

