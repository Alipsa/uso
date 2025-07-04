# Ant Task: Import

## Original Ant Task Description

The `import` Ant task is used to include the contents of another Ant build file into the current project. This allows for modularization of build processes, where common targets or properties can be defined in separate files and reused across multiple projects. When an Ant build file is imported, its targets, tasks, and properties become part of the importing project.

Key features and behaviors of the `import` task include:

*   **Target Overriding**: If a target in the importing (main) file has the same name as a target in an imported file, the target from the importing file takes precedence. The target from the imported file is still accessible using a prefixed name (e.g., `importedProjectName.targetName`).
*   **Special Properties**: Ant automatically defines properties for each imported file, allowing the imported file to reference its own location. For example, if `builddocs.xml` (with a project name `builddocs`) is imported, `ant.file.builddocs` will hold the path to `builddocs.xml`.
*   **Scope**: The `import` task must be a top-level task, meaning it cannot be used within a target.
*   **Uniqueness**: A file can only be imported once. Subsequent attempts to import the same file are ignored.
*   **Resolution**: File paths in the `import` task are resolved relative to the importing file, not the project's basedir.

## Groovy AntBuilder DSL Equivalent

When using Groovy's `AntBuilder`, you can achieve similar functionality to the `import` task. While there isn't a direct one-to-one `import` method in `AntBuilder` that replicates all behaviors (like automatic target prefixing for overriding), you can achieve the core goal of including and executing targets from other Ant files or structuring Groovy build scripts to share common logic.

### Approach 1: Using `ant.importBuild()`

Groovy's `AntBuilder` has an `importBuild` method that can be used to load targets from another Ant XML file. This is the closest equivalent to the `<import>` task.

```groovy
// main_build.groovy
ant = new AntBuilder()

// Import targets from common_targets.xml
// This makes targets from common_targets.xml available directly.
ant.importBuild("common_targets.xml")

// Now you can call targets defined in common_targets.xml
ant.commonTarget()

// If common_targets.xml has a target named 'compile', and main_build.groovy also defines 'compile',
// the one in main_build.groovy (if defined as a Groovy method/closure) would typically take precedence
// or you might need to manage namespaces or prefixes manually if AntBuilder doesn't do it automatically
// in the same way as <import>.
```

**common_targets.xml:**
```xml
<project name="common">
    <target name="commonTarget">
        <echo message="Executing commonTarget from common_targets.xml"/>
    </target>
    <target name="compile">
        <echo message="Executing compile from common_targets.xml"/>
    </target>
</project>
```

### Approach 2: Structuring Groovy Build Scripts

For pure Groovy-based builds (without relying on XML Ant files), you can achieve modularity by:

1.  **Defining common tasks in separate Groovy scripts or classes.**
2.  **Using Groovy's script execution or class loading mechanisms to include them.**

```groovy
// common_tasks.groovy

// Define a closure for a common task
def commonCompile = {
    echo(message: "Performing common compilation steps...")
    // Add javac, etc. tasks here
}

// Define another common task
def commonPackage = {
    echo(message: "Performing common packaging steps...")
    // Add jar, war, etc. tasks here
}

// Expose these tasks, e.g. by returning a map of closures
return [compile: commonCompile, packageIt: commonPackage]

// main_build.groovy
ant = new AntBuilder()

// Load the common tasks
def commonTasks = evaluate(new File('common_tasks.groovy'))

// Define a target that uses a common task
def myCompile = {
    commonTasks.compile()
    echo(message: "Performing project-specific compilation after common steps...")
}

// Define another target
def myPackage = {
    commonTasks.packageIt()
    echo(message: "Performing project-specific packaging after common steps...")
}

// Execute the targets (in a real build, these would be invoked by Ant or a build runner)
myCompile()
echo "-----"
myPackage()
```

### Simulating Target Overriding and Prefixed Access

If you need to simulate the target overriding and prefixed access behavior of Ant's `<import>` task more closely within a pure Groovy context, you would need to implement custom logic. This could involve:

*   Loading tasks from other scripts into specific namespaces (e.g., maps).
*   Checking for existing task definitions in the main script and deciding whether to use the local or imported version.
*   Providing access to the imported tasks using a specific naming convention (e.g., `importedModuleName.taskName`).

## Parameters

The `import` task in Ant has the following attributes:

| Attribute         | Description                                                                                                 | Required                                                                 |
|-------------------|-------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------|
| `file`            | The name of the file to import. The path is resolved relative to the directory of the current build file.     | Yes or a nested resource collection                                      |
| `optional`        | If true, do not stop the build if the file does not exist.                                                  | No; default is false                                                     |
| `as`              | Specifies the prefix to be used for the imported targets. If not specified, the project name of the imported file is used. | No; defaults to name attribute of the `project` tag of the imported file |
| `prefixSeparator` | Specifies the separator character to be used between the prefix and the target name.                        | No; defaults to .                                                        |

### Parameters specified as nested elements

The `import` task can also take a single resource collection as a child element to specify the file to be imported. This allows for more flexible ways to locate the build file, such as using a `fileset` with a base directory or a `url` resource.

## Examples

### Example 1: Basic Import

**Ant XML:**
```xml
<import file="../common-targets.xml"/>
```

**Groovy AntBuilder DSL:**
```groovy
ant.importBuild(file: '../common-targets.xml')
```

### Example 2: Import with `as` attribute for prefixing

**Ant XML:**
```xml
<import file="common-targets.xml" as="common"/>
```

**Groovy AntBuilder DSL:**
```groovy
// Assuming common-targets.xml defines a target 'doSomething'
// After import, you might access it as common.doSomething if AntBuilder handles prefixes.
// The 'as' attribute in <import> is specific to Ant's XML parsing and target naming.
// In Groovy, you would typically manage namespaces or prefixes in your script logic if needed.
```

### Example 3: Optional Import

**Ant XML:**
```xml
<import file="optional-features.xml" optional="true"/>
```

**Groovy AntBuilder DSL:**
```groovy
def optionalFile = new File('optional-features.groovy') // or .xml
if (optionalFile.exists()) {
    ant.importBuild('optional-features.groovy')
}
```

## Differences between `<import>` and `<include>`

It's important to understand the distinction between the `<import>` and `<include>` tasks in Ant:

*   **Target Overriding:** `<import>` allows targets in the importing file to override targets with the same name in the imported file. `<include>` does not allow this; all included targets are prefixed, preventing name collisions and overriding.
*   **Prefixing:** `<include>` always prefixes the targets from the included file. `<import>` only prefixes targets if they would otherwise conflict with targets in the importing file or other imported files.
*   **Multiple Imports/Includes:** The same file can be included multiple times using different prefixes. An Ant file can only be imported once; subsequent import attempts for the same file are ignored.
*   **Use Case:** `<include>` is generally used for building composite projects where components are clearly separated by prefixes. `<import>` is more suited for extending or customizing existing build logic, allowing for more seamless integration and overriding of targets.

In essence, if you want to ensure that included targets remain distinct and cannot be accidentally overridden, use `<include>`. If you want to allow overriding or a more integrated approach, use `<import>`.

