# subant

The `subant` task calls a given target for all defined sub-builds. It iterates over multiple build files or directories, invoking Ant on each in turn, and optionally captures output and property inheritance.

## Usage Examples

```groovy
target('multiModuleBuild') {
  subant(target: 'compile') {
    fileset(dir: 'modules', includes: '**/build.xml')
  }
}

target('buildAlternateWithCommon') {
  subant(
    target: 'compile',
    genericantfile: 'common/build.xml'
  ) {
    dirset(dir: 'modules', includes: 'mod*/build.xml')
  }
}

target('batchClean') {
  subant(failonerror: false) {
    fileset(dir: '.', includes: '**/build.xml', excludes: 'build.xml')
    target(name: 'clean')
    target(name: 'build')
  }
}
```

## Attributes

|        Attribute | Description                                                                                            | Required |
|-----------------:|--------------------------------------------------------------------------------------------------------|----------|
|         `target` | Comma-separated list of targets to execute (defaults to each build file’s default target)              | No       |
|        `antfile` | Name of the build file to invoke (default: `build.xml`; ignored if `genericantfile` is set)            | No       |
| `genericantfile` | Path to a build file used for all base directories; overrides `antfile`                                | No       |
|      `buildpath` | Resource collection (`<path>` or other) to locate sub-projects; added to Ant’s implicit build path     | No       |
|   `buildpathref` | Reference to an existing `<path>` element defining the build path                                      | No       |
|     `inheritall` | Whether to inherit all properties from the parent project (`true`/`false`; default: `false`)           | No       |
|    `inheritrefs` | Whether to inherit references from the parent project (`true`/`false`; default: `false`)               | No       |
|         `output` | File to capture sub-build log output for each invocation                                               | No       |
|    `failonerror` | Whether the build stops on sub-build failure (`true`/`false`; default: `true`)                         | No       |
|        `verbose` | Enable verbose logging of entering/exiting each sub-build directory (`true`/`false`; default: `false`) | No       |

## Nested Elements

- **Resource collections**: `<fileset>`, `<dirset>`, `<filelist>` to specify build file locations.
- `<property>` / `<propertyset>`: Define properties to set in each sub-build.
- `<buildpath>` / `<buildpathelement>`: Configure the implicit Ant build path for locating tasks.
- `<target>`: Specify multiple targets with nested `<target name="..."/>` elements (Ant 1.7+).

## Notes

- If `genericantfile` is specified, `antfile` is ignored.
- `inheritall` defaults to `false` (unlike `<ant>` which defaults to `true` for property inheritance).
- Nested `<target>` elements override the `target` attribute and allow ordering of multiple targets.
- This task is part of Ant core as of version 1.6.

## Reference

- [Ant Manual: subant Task](https://ant.apache.org/manual/Tasks/subant.html)
