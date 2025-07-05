# replace

The `replace` task performs literal string replacements in files or resource collections. It can replace single strings or token pairs, supports nested resource collections, and allows preserving backups.

## Usage Examples

### Replace a literal string in a single file
```groovy
target('updateVersion') {
  replace(
    file: 'src/main/java/com/example/App.java',
    token: '@VERSION@',
    value: '1.2.3'
  )
}
```

### Replace across multiple files
```groovy
target('batchReplace') {
  replace(token: 'foo', value: 'bar') {
    fileset(dir: 'src', includes: '**/*.txt')
    fileset(dir: 'config', includes: '*.cfg')
  }
}
```

### Create backups of original files
```groovy
target('replaceWithBackup') {
  replace(
    file: 'README.md',
    token: 'TODO',
    value: 'Done',
    backupSuffix: '.bak'
  )
}
```

## Attributes

| Attribute       | Description                                                                                         | Required                                                          |
|----------------:|-----------------------------------------------------------------------------------------------------|-------------------------------------------------------------------|
| `file`          | Path to a single file to process                                                                    | One of `file` or nested resource                                  |
| `token`         | The string to search for                                                                           | One of `token` or `<replacefilter>` nested element                |
| `value`         | Replacement string                                                                                  | Yes (unless using nested `<replacefilter>` with its own `replace` rule) |
| `backupPrefix`  | Prefix for backup files (default: none)                                                             | No                                                                |
| `backupSuffix`  | Suffix for backup files (default: none)                                                             | No                                                                |
| `encoding`      | Character encoding to use when reading/writing files (default: platform encoding)                  | No                                                                |

## Nested Elements

- `<replacefilter>` — Define a custom replace rule with attributes `token` and `value`.
- `<filterchain>` — Apply Ant-style filters (e.g., `<tokenfilter>`) before or after replacement.
- Resource collections (`<fileset>`, `<path>`, etc.) to specify multiple files.

## Notes

- If both `file` and nested resources are provided, nested resources take precedence.
- Without backup settings, original files are overwritten.
- Use `encoding` to handle files with specific character sets.
- For advanced scenarios, use `<replacetask>` parameters via nested elements.

## Reference

- [Ant Manual: replace Task](https://ant.apache.org/manual/Tasks/replace.html)
