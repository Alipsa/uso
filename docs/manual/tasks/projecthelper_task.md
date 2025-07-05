# projecthelper

The `projecthelper` task uses an Ant `ProjectHelper` implementation to parse and configure a target Ant project from an external build file. It allows you to import or include other Ant build files at runtime.

## Usage Example

```groovy
target('importSubproject') {
  // Using the default XML ProjectHelper
  projecthelper(
    buildfile: 'subproject/build.xml'
  )
}
```

```groovy
target('importWithCustomHelper') {
  projecthelper(
    buildfile: 'build.gant',
    helperclassname: 'org.codehaus.gant.GantProjectHelper'
  )
}
```

## Attributes

| Attribute         | Description                                                                                       | Required |
|------------------:|---------------------------------------------------------------------------------------------------|----------|
| `buildfile`       | Path to the external build file to parse                                                          | Yes      |
| `helperid`        | Registered ProjectHelper ID (e.g., `xml`, `antlib`) to use                                        | No (default: `xml`) |
| `helperclassname` | Fully qualified class name of a custom `ProjectHelper` implementation                             | No       |
| `project`         | Reference to the Ant project to configure (defaults to the current project)                       | No       |

## Notes

- By default, the task registers and uses the standard XML `ProjectHelper` to parse `build.xml` files.
- Use `helperid` to select an alternative helper registered via `project.addProjectHelper()`.
- Use `helperclassname` to specify a completely custom parser (e.g., for Gant scripts).
- The task replaces the existing project references—be cautious when importing multiple builds.
- Useful for monorepo builds or large projects split into multiple build files.

## Reference

- [Ant Manual: projecthelper Task](https://ant.apache.org/manual/Tasks/projecthelper.html)
