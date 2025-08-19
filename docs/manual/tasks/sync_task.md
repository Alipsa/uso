# sync

Synchronize a target directory from the files defined in one or more resource collections. Any file in the target directory that is not matched by at least one of the nested resource collections will be removed. (Since Ant 1.6)

## Usage Examples

```groovy
target('mirrorSite') {
  sync(todir: 'site') {
    fileset(dir: 'generated-site')
  }
}

target('mirrorWithCVS') {
  sync(todir: 'site') {
    fileset(dir: 'generated-site')
    preserveInTarget(preserveemptydirs: true) {
      include(name: '**/CVS/**')
    }
  }
}
```

## Attributes

| Attribute          | Description                                                                                                                         | Required |
|--------------------|-------------------------------------------------------------------------------------------------------------------------------------|----------|
| `todir`            | The target directory to synchronize with the resource collections                                                                   | Yes      |
| `overwrite`        | Overwrite existing files even if the destination files are newer (default: `false`)                                                 | No       |
| `includeEmptyDirs` | Copy empty directories included in the resource collections (also controls default `preserveEmptyDirs` for `<preserveInTarget>`)    | No       |
| `failonerror`      | If `false`, log a warning instead of stopping the build when a nested fileset points to a non-existent directory (default: `true`)  | No       |
| `verbose`          | Log each file as it is copied (default: `false`)                                                                                    | No       |
| `granularity`      | Number of milliseconds to use as leeway when comparing timestamps before deciding files are out of date (default: `0`; DOS: `2000`) | No       |

## Nested Elements

- **Resource Collections**: Any Ant resource collection (e.g., `<fileset>`, `<path>`) to select source files.
- `<preserveInTarget>`: Defines files or directories that should be kept in the target even if not present in source.
  - **Attribute**:  
    - `preserveEmptyDirs` (default: inherits `includeEmptyDirs`): whether to preserve empty directories matched by patterns.

## Notes

- Files in the target directory not matched by any source are deleted.
- `<preserveInTarget>` was introduced in Ant 1.7.0 to protect specific files or directories.
- Use `overwrite: true` to always replace files regardless of timestamps.
- `granularity` helps when syncing across systems with clock skew.

## Reference

- [Ant Manual: sync Task](https://ant.apache.org/manual/Tasks/sync.html)
