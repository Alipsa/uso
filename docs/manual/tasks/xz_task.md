# xz

The `xz` task compresses a resource using the XZ algorithm. It produces an `.xz` compressed file only if the destination does not exist or if the source is newer. XZ compression support was added in Ant 1.10.1 and requires external libraries (such as the [XZ for Java](https://tukaani.org/xz/) library) on the classpath.

## Usage Examples

```groovy
// Compress a TAR file into XZ format
target('compressTar') {
  xz(src: 'test.tar', destfile: 'test.tar.xz')
}

// Compress data from a URL on the fly
target('compressRemote') {
  xz(destfile: 'archive.tar.xz') {
    url(url: 'https://example.org/archive.tar')
  }
}

// Batch compress all TAR files in a directory
target('batchCompress') {
  fileset(dir: 'dist', includes: '**/*.tar')
  xz(destfile: 'all.tar.xz') {
    fileset(dir: 'dist', includes: '**/*.tar')
  }
}
```

## Attributes

| Attribute  | Description                                                                            | Required                                  |
|------------|----------------------------------------------------------------------------------------|-------------------------------------------|
| `src`      | File to compress. Required unless nested resource collections are provided.            | Conditional                               |
| `destfile` | Destination file to create (e.g., `archive.tar.xz`). Required if `zipfile` is omitted. | One of `destfile` or deprecated `zipfile` |
| `zipfile`  | **Deprecated** alias for `destfile`.                                                   | No                                        |

## Nested Elements

- **Resource Collections**: Any Ant resource collection (e.g., `<fileset>`, `<url>`, `<path>`) to define one or more source resources.

## Notes

- Requires the XZ for Java library (or similar) on the classpath to function.  
- The task will not overwrite existing compressed files unless the source is newer.

## Reference

- [Ant Manual: GZip/BZip2/XZ Tasks](https://ant.apache.org/manual/Tasks/pack.html)
