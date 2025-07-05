# makeurl

The `makeurl` task converts one or more file paths into file-based URLs and assigns them to a property. This is useful for constructing RMI codebases, JNLP codebase lists, or any scenario where file URLs are needed.

## Usage Examples

```groovy
// Convert a single directory to a URL
target('setRepoUrl') {
  makeurl(
    file: "${System.getProperty('user.home')}/.m2/repository",
    property: 'm2.repository.url'
  )
}
```

```groovy
// Convert multiple JARs in a directory into a space-separated URL list
target('setCodebase') {
  makeurl(property: 'codebase', separator: ' ') {
    fileset(dir: 'lib', includes: '*.jar')
  }
}
```

## Attributes

|   Attribute | Description                                                              | Required |
|------------:|--------------------------------------------------------------------------|----------|
|      `file` | Path to a file or directory to convert to a URL (e.g., `/path/to/dir`)   | No       |
|  `property` | Name of the property to set with the resulting URL(s)                    | Yes      |
| `separator` | Separator string between URLs (default: space)                           | No       |
|  `validate` | Whether to check that each file exists (`true`/`false`, default: `true`) | No       |

## Nested Elements

- `fileset` — FileSet of files whose paths are converted to URLs.
- `path` — Ant `Path` whose elements are converted to URLs.

## Notes

- If neither `file` nor nested elements are provided, the task will fail.
- Nested elements and the `file` attribute can be mixed; all provided paths are included.
- With `validate="true"`, missing files will cause the build to fail.

## Reference

- [Ant Manual: makeurl Task](https://ant.apache.org/manual/Tasks/makeurl.html)
