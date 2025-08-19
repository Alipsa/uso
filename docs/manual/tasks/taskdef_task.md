# taskdef

The `taskdef` task adds a task definition to the current project, such that the newly defined task can be used like a built-in task. It is a specialized form of `<typedef>` where tasks are defined rather than data types; by default, it adapts classes to Ant tasks using `org.apache.tools.ant.TaskAdapter` and `org.apache.tools.ant.Task`.

## Usage Examples

```groovy
// Define a single task by name and implementation class
target('defineMyJavadoc') {
  taskdef(
    name: 'myjavadoc',
    classname: 'com.mydomain.JavadocTask'
  )
}
```

```groovy
// Load multiple task definitions from a properties file
target('loadBatchTasks') {
  taskdef(
    file: 'build/tasks.properties'
  )
}
```

```groovy
// Define tasks from an Antlib resource with a custom namespace
target('defineAntContrib') {
  path(id: 'antcontrib') {
    pathelement(location: 'lib/ant-contrib.jar')
  }
  taskdef(
    resource: 'net/sf/antcontrib/antlib.xml',
    classpathref: 'antcontrib',
    uri: 'antlib:net.sf.antcontrib'
  )
}
```

## Attributes

| Attribute      | Description                                                                                                                                          | Required                                      |
|----------------|------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------|
| `name`         | Name to assign to the new task (as used in the build file).                                                                                          | Yes, unless `file` or `resource` is specified |
| `classname`    | Fully qualified Java class name that implements the task (must extend `org.apache.tools.ant.Task`).                                                  | Yes, unless `file` or `resource` is specified |
| `classpath`    | Classpath for loading the task implementation; can be specified inline or via nested `<classpath>` element.                                          | No                                            |
| `classpathref` | Reference to an existing `<path>` element defining a classpath for locating the task class.                                                          | No                                            |
| `loaderref`    | Identifier for the `ClassLoader` to use when loading the task class; allows multiple taskdefs to share the same loader and classpath.                | No                                            |
| `file`         | Path to a properties file containing `name=classname` pairs to load multiple task definitions at once.                                               | No                                            |
| `resource`     | Classpath resource path (e.g., `mypkg/antlib.xml`) to load multiple task definitions, typically in Antlib XML format.                                | No                                            |
| `format`       | Format of the `file` or `resource`: `properties` or `xml`. Defaults to `properties` unless the file/resource name ends with `.xml`.                  | No                                            |
| `uri`          | XML namespace URI to associate with tasks defined via Antlib (`resource`); allows tasks to be used with a namespace prefix in the build.             | No                                            |
| `onerror`      | Action when a definition fails: `fail`, `report`, `ignore`, or `failall`. Defaults to `fail` (`report` for `resource` errors if `failall` not used). | No                                            |
| `adapter`      | Adapter class to wrap the defined class to another type; defaults to `org.apache.tools.ant.TaskAdapter`.                                             | No                                            |
| `adaptto`      | Target interface/class the defined class must implement; wrapper used if not. Defaults to `org.apache.tools.ant.Task`.                               | No                                            |

## Nested Elements

- `<classpath>` — Defines a classpath inline for loading the task implementation, equivalent to the `classpath` attribute.

## Notes

- When defining multiple tasks that share code, prefer using `resource` with an Antlib XML descriptor to avoid separate class loaders per `taskdef`.
- Use `loaderref` to ensure tasks defined by multiple `taskdef` or `typedef` invocations share the same class loader.
- To call tasks from a namespace, supply both `resource` and `uri`, and declare the namespace in the `<project>` element if needed.
- The `taskdef` task must be invoked within a target or project context—Groovy’s Uso builder wraps it as a method call.

## Reference

- [Ant Manual: taskdef Task](https://ant.apache.org/manual/Tasks/taskdef.html)
