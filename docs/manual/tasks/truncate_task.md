# truncate

The `truncate` task sets the length of one or more files, similar to the Unix `truncate` command, by either specifying an absolute length or adjusting the current size. It works on single files or any Ant resource collections (filesystem-based).

## Usage Examples

```groovy
// Truncate file 'foo' to zero length
target('truncateZero') {
  truncate(file: 'foo')
}

// Truncate file 'foo' to 1 kilobyte (1024 bytes)
target('truncateTo1K') {
  truncate(file: 'foo', length: '1K')
}

// Adjust file 'foo' upward by 1 kilobyte, then downward by 1 megabyte
target('adjustLength') {
  truncate(file: 'foo', adjust: '1K')
  truncate(file: 'foo', adjust: '-1M')
}

// Truncate multiple files in a directory to zero using a fileset
target('truncateAll') {
  truncate(length: '0') {
    fileset(dir: 'build/tmp', includes: '**/*.tmp')
  }
}
```

## Attributes

| Attribute | Description                                                                                                        | Required    |
|-----------|--------------------------------------------------------------------------------------------------------------------|-------------|
| `file`    | Name of the file to truncate. Required unless nested resource collections are used.                                | Conditional |
| `length`  | New file length in bytes. Supports suffixes: `K`, `M`, `G`, `T`, `P` (powers of 1024). Defaults to `0` if omitted. | No          |
| `adjust`  | Number of bytes to adjust the existing file length (positive or negative). Supports same suffixes as `length`.     | No          |
| `create`  | Whether to create the file if it does not exist (`true`/`false`). Default is `true`.                               | No          |
| `mkdirs`  | Whether to create parent directories if they do not exist (`true`/`false`). Default is `false`.                    | No          |

## Nested Elements

- **Resource collections**: Any Ant resource collection (e.g., `<fileset>`, `<path>`, `<filelist>`) to specify multiple files for truncation. Resources must be filesystem-based.

## Notes

- Introduced in Ant 1.7.1.
- A `length` of `"0"` is implied when neither `length` nor `adjust` is specified.
- Nested resource collections allow batch truncation across directories.
- Ensure resources refer to files on the local filesystem; non-filesystem resources are not supported.

## Reference

- [Ant Manual: truncate Task](https://ant.apache.org/manual/Tasks/truncate.html)
