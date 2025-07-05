# Ant Task: Include

## Original Ant Task Description

The `include` task in Ant (available since Ant 1.8.0) is used to incorporate another Ant build file into the current project. Unlike the `import` task, `include` is designed to prevent accidental overriding of targets. When a file is included, its targets are renamed by prefixing them with the included project's name (or a name specified via the `as` attribute). This makes the included build file's targets distinct and helps in creating modular, self-contained build components.

When using Groovy with AntBuilder, the `include` task provides a way to structure build scripts by referencing external Ant files, ensuring that target names from the included file do not clash with those in the main or other included files.

## Groovy AntBuilder DSL Equivalent

**Note:** The `include` task in Ant is primarily a build file structuring mechanism. In Groovy, this is typically handled by Groovy's own import mechanisms for classes or by using `evaluate` for scripts. However, if you are working with Ant XML files within a Groovy script, you can use the `ant.include()` method to mimic the behavior of the Ant `include` task. This method is part of the `AntBuilder`'s internal handling of Ant tasks and might not be directly exposed in the same way as other tasks. A more common approach in a pure Groovy build script would be to organize reusable logic into separate Groovy scripts or classes and then use standard Groovy mechanisms to incorporate them.

If you need to directly invoke the Ant `include` task (for example, when programmatically generating an Ant XML file or interacting with an existing Ant build process that uses `include`), you would represent it as follows:

```groovy
// Example of how the <include> task might be represented in a Groovy script
// This assumes you are building an Ant XML structure, not directly executing it.

// main_build.groovy
new AntBuilder().project(name: 'main_project', default: 'fullBuild') {
    target(name: 'fullBuild', depends: 'common.compile, common.test') {
        echo(message: 'Main build finished.')
    }
    // This is a conceptual representation. In practice, you'd likely use
    // Ant's <import> task or Groovy's own script/class loading mechanisms
    // for more complex scenarios.
    // For simple inclusion of tasks, you might define them in a separate file
    // and use Groovy's 'evaluate' or class loading.

    // If you are generating an Ant XML file using Groovy, you would do something like:
    // StringWriter writer = new StringWriter()
    // AntBuilder ant = new AntBuilder(new PrintWriter(writer))
    // ant.project(name: 'main_project', default: 'fullBuild') {
    //     ant.include(file: 'common.xml', as: 'common')
    //     ant.target(name: 'fullBuild', depends: 'common.compile, common.test') {
    //         echo(message: 'Main build finished.')
    //     }
    // }
    // println writer.toString()
}
```

For direct execution within a Groovy script that's acting as the main build file, you'd typically use Groovy's own mechanisms for code organization (like classes, methods, or script includes) rather than relying on Ant's `include` task directly. If you need to interact with an existing Ant build that uses `<include>`, you would typically execute that Ant build file as a whole using `ant.ant(...)` and let Ant handle the include directives internally.

## Parameters

The `include` task in Ant has the following attributes:

| Attribute  | Description                                                                                                                                    | Required                                                                                                   |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| `file`     | The name of the file to include. The path is resolved relative to the directory of the current build file.                                     | Yes, unless a nested resource collection is used.                                                          |
| `as`       | The prefix to use for the targets in the included file. If not specified, the `name` attribute of the included file's `<project>` tag is used. | No. If not specified and the included file's `<project>` tag has no `name` attribute, the build will fail. |
| `optional` | Specifies whether the include is optional. If `true` and the file is not found, the build will not fail. Defaults to `false`.                  | No                                                                                                         |

### Parameters as nested elements

The `include` task can also take a single resource collection as a child element to specify the file to be included. This allows for more flexible ways to locate the build file, such as using a `fileset` with a base directory or a `url` resource.

## Examples

### Example 1: Basic include with `as` attribute

**main_build.xml:**
```xml
<project name="main_project" default="main.target">
    <include file="common.xml" as="common"/>
    <target name="main.target" depends="common.compile">
        <echo>Main target in main_build.xml</echo>
    </target>
</project>
```

**common.xml:**
```xml
<project name="common_project">
    <target name="compile">
        <echo>Compile target in common.xml</echo>
    </target>
</project>
```
In this example, the `compile` target from `common.xml` will be accessible as `common.compile` in `main_build.xml`.

### Example 2: Using a fileset to specify the included file
```xml
<include>
    <fileset file="common.xml"/>
</include>
```
This is equivalent to ` <include file="common.xml"/>`.

### Example 3: Including a file from a JAR
```xml
<project name="main_project">
    <include as="common">
        <javaresource name="com/example/common.xml">
            <classpath location="path/to/your.jar"/>
        </javaresource>
    </include>
    <target name="main.target" depends="common.compile"/>
</project>
```
This example includes `common.xml` located within `com/example/` package inside `your.jar`.

## Differences between `<include>` and `<import>`

It's important to understand the distinction between the `<include>` and `<import>` tasks in Ant:

*   **Target Overriding:** `<import>` allows targets in the importing file to override targets with the same name in the imported file. `<include>` does not allow this; all included targets are prefixed, preventing name collisions and overriding.
*   **Prefixing:** `<include>` always prefixes the targets from the included file. `<import>` only prefixes targets if they would otherwise conflict with targets in the importing file or other imported files.
*   **Multiple Imports/Includes:** The same file can be included multiple times using different prefixes. An Ant file can only be imported once; subsequent import attempts for the same file are ignored.
*   **Use Case:** `<include>` is generally used for building composite projects where components are clearly separated by prefixes. `<import>` is more suited for extending or customizing existing build logic, allowing for more seamless integration and overriding of targets.

In essence, if you want to ensure that included targets remain distinct and cannot be accidentally overridden, use `<include>`. If you want to allow overriding or a more integrated approach, use `<import>`.
