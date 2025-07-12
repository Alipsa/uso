# unxz

The `unxz` task expands a file compressed with the XZ algorithm. It extends Ant’s core unpack functionality to handle XZ-compressed archives, typically used to decompress `.tar.xz` files into `.tar` before further extraction.

## Usage Examples

```groovy
// Decompress a single .tar.xz file to .tar
target('expandSingle') {
  unxz(src: 'dist/archive.tar.xz', dest: 'dist/archive.tar')
}

// Decompress using default target filename (remove .xz suffix)
target('expandDefault') {
  unxz(src: 'dist/archive.tar.xz')
}

// Batch decompress all .xz files into a directory
target('batchExpand') {
  unxz(dest: 'output') {
    fileset(dir: 'dist', includes: '**/*.xz')
  }
}
```

## Attributes

| Attribute | Description                                                                                                                         | Required    |
|-----------|-------------------------------------------------------------------------------------------------------------------------------------|-------------|
| `src`     | Path to the `.xz` file to decompress; required if no nested resource collections are provided                                       | Conditional |
| `dest`    | Destination file or directory; if a file, use it directly; if a directory, results are placed there (defaults to src without `.xz`) | No          |

## Nested Elements

- **Resource Collections**: `<fileset>`, `<filelist>`, `<path>`, or other Ant resource collections to select multiple `.xz` files for batch decompression.

## Notes

- Introduced in Ant 1.10.1 as part of the optional XZ support, moved to core in later releases.
- When `dest` is omitted, the task removes the `.xz` extension from `src` to determine the output file.
- Works with non-file resources as long as they support stream input.

## Reference

- [Ant Manual: unxz Task](https://ant.apache.org/manual/Tasks/unxz.html)
