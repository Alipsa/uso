# uso

The `uso` task invokes another Uso build script (Groovy) as a sub-build within the current project. It allows multi-module or composite builds by calling external `.groovy` build files with specified targets and conditional execution.

Note: this task is ONLY available when using the uso build system.

## Usage Example

```groovy
project.with {
  target('buildAll') {
    // Invoke common module
    uso(file: 'common/build.groovy', target: 'publishLocal')

    // Conditionally invoke subA if groupId is unset
    uso(file: 'subA/build.groovy', target: 'publishLocal', unless: 'groupId')

    // Always invoke subB
    uso(file: 'subB/build.groovy', target: 'publishLocal')
  }
}
```

## Attributes

|    Attribute | Description                                                                              | Required |
|-------------:|------------------------------------------------------------------------------------------|----------|
|       `file` | Path to the external Uso build script (Groovy file) to invoke                            | Yes      |
|     `target` | Name of the target(s) in the invoked script to execute (defaults to its `defaultTarget`) | No       |
|     `unless` | Only invoke if the given property is **not** set                                         | No       |
|        `dir` | Working directory for the sub-build; defaults to the directory of `file`                 | No       |
## Notes

- The invoked script runs in its own Ant project context, but inherits system properties and the parent project's properties.
- The `unless` attribute mirrors Ant’s own conditional, controlling execution when a property is unset.
- Useful for orchestrating multi-module builds or reusing common build logic across projects.

## Reference

- [ProjectBuilder.uso Source](https://github.com/Alipsa/uso/blob/main/uso-core/src/main/groovy/se/alipsa/uso/core/ProjectBuilder.groovy)
- [Multi-module Example](https://github.com/Alipsa/uso/blob/main/examples/multimodule/build.groovy)
