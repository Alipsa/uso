# uptodate

The `uptodate` task sets a property if a target file (or set of target files) is up-to-date with respect to a source file (or set of source files). It supports single-file checks via attributes, or multi-file checks via nested `<srcfiles>` or `<srcresources>` elements combined with a `<mapper>`.

By default, the value of the property is set to true if the timestamp of the source file(s) is not more recent than the timestamp of the corresponding target file(s). You can set the value to something other than the default by specifying the value attribute.

Normally, this task is used to set properties that are useful to avoid target execution depending on the relative age of the specified files.

## Usage Examples

```groovy
// Single source and target file comparison
target('checkSingle') {
  uptodate(
    property: 'isUpToDate',
    srcfile: '/usr/local/bin/testit',
    targetfile: "${buildDir}/.flagfile"
  )
}

// Multiple source files compared to a single target via merge mapper
target('checkXmlBuild') {
  uptodate(property: 'xmlBuild.notRequired') {
    srcfiles(dir: 'src/xml', includes: '**/*.dtd')
    mapper(type: 'merge', to: "${deployDir}/xmlClasses.jar")
  }
}

// Multiple resource collections compared via nested srcresources (Ant 1.7+)
target('checkResources') {
  uptodate(property: 'resourcesUpToDate') {
    srcresources {
      union {
        fileset(dir: 'src/resources', includes: '**/*')
        fileset(dir: 'common/resources', includes: '**/*')
      }
    }
    mapper(type: 'merge', to: 'build/resources.bundle')
  }
}
```

## Attributes

|    Attribute | Description                                                                                        | Required    |
|-------------:|----------------------------------------------------------------------------------------------------|-------------|
|   `property` | Name of the property to set if the check passes                                                    | Yes         |
|      `value` | Value to assign to the property (default: `"true"`)                                                | No          |
|    `srcfile` | Path to the single source file; required if no nested `<srcfiles>` or `<srcresources>` is provided | Conditional |
| `targetfile` | Path to the single target file; required if no nested `<mapper>` is provided                       | Conditional |

## Nested Elements

- `<srcfiles>` — A `<fileset>` of source files to compare. Only normal files are considered, directories are ignored.
- `<srcresources>` — A `<union>` of any resource collections (since Ant 1.7), allowing mixed resource types.
- `<mapper>` — A mapper (e.g., `merge`) defining how source files map to target files. If omitted when using `<srcfiles>`, a default merge mapper targets the `targetfile` attribute.

## Notes

- By default, when `<srcfiles>` is used without a `<mapper>`, a merge mapper is applied to the `targetfile` attribute.
- Useful for avoiding unnecessary rebuild targets by conditionally setting properties.
- Directories matched by `<srcfiles>` are ignored; use `<srcresources>` if you need directory logic.

## Reference

- [Ant Manual: uptodate Task](https://ant.apache.org/manual/Tasks/uptodate.html)
