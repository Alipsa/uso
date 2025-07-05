# move

The `move` task relocates files or directories. It supports moving single files, multiple files via resource collections (filesets, paths), and optional flattening. Existing files in the destination are overwritten by default.

## Usage Examples

### Move a single file

```groovy
target('moveReadme') {
  move(
    file: 'README.tmp',
    tofile: 'README.md'
  )
}
```

### Move all `.class` files to a directory

```groovy
target('moveClasses') {
  move(
    todir: 'build/classes',
    preservelastmodified: true
  ) {
    fileset(dir: 'src', includes: '**/*.class')
  }
}
```

### Flatten directory structure

```groovy
target('flattenMove') {
  move(
    todir: 'deploy/lib',
    flatten: true
  ) {
    fileset(dir: 'lib', includes: '**/*.jar')
  }
}
```

## Attributes

|              Attribute | Description                                                                                          | Required                             |
|-----------------------:|------------------------------------------------------------------------------------------------------|--------------------------------------|
|                 `file` | Single file to move (use `tofile`)                                                                   | One of `file` or resource collection |
|               `tofile` | Destination file path                                                                                | Required if `file` used              |
|                `todir` | Destination directory for moved files                                                                | Required if resource collection used |
|              `flatten` | If `true`, places all files directly into `todir`, ignoring directory structure (`false` by default) | No                                   |
| `preservelastmodified` | Preserve last modified timestamp on moved files (`false` by default)                                 | No                                   |
|            `overwrite` | Overwrite existing files in the destination (`true` by default)                                      | No                                   |
|              `verbose` | Log detailed file-by-file operations (`false` by default)                                            | No                                   |
|          `failonerror` | Fail the build if a move operation fails (`true` by default)                                         | No                                   |
|            `filtering` | Apply filterchain to file content during the move (uses nested `<filterchain>`)                      | No                                   |

## Nested Elements

- `fileset` — file selections by directory, include/exclude patterns.
- `path` / `zipfileset` — additional resource collections.
- `filterchain` — apply text transformations (e.g., `<tokenfilter>`) as files are moved.
- `mapper` — use Ant mappers to rename files during the move operation.

## Notes

- When moving directories, all contents are moved recursively.
- The `flatten` option is useful for collecting artifacts into a single directory.
- Using `mapper` allows renaming or reorganizing files on the fly.
- If `overwrite` is `false`, existing files in the destination will prevent the move and fail the build.

## Reference

- [Ant Manual: move Task](https://ant.apache.org/manual/Tasks/move.html)
