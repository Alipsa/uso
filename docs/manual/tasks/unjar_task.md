# Unjar/Untar/Unwar/Unzip

The `unjar`, `untar`, `unwar`, and`unzip` tasks extracts files from a ZIP, JAR, or WAR archive. It supports nested resource collections to specify multiple archives, pattern-based inclusion/exclusion, filename transformations via mappers, and control over file overwriting and encoding.

## Usage Examples

```groovy
// Basic unzip of a single archive
target('unzipArchive') {
  unzip(
    src: 'dist/app.zip',
    dest: 'build/app'
  )
}

// Unzip only XML and properties files, renaming .xml to .config
target('unzipFiltered') {
  unzip(src: 'dist/app.zip', dest: 'build/app') {
    patternset {
      include(name: '**/*.xml')
      include(name: '**/*.properties')
    }
    mapper(type: 'glob', from: '*.xml', to: '*.config')
  }
}

// Unzip all ZIPs in a directory into a common output directory
target('batchUnzip') {
  unzip(dest: 'build/all') {
    fileset(dir: 'dist', includes: '**/*.zip')
  }
}
```

## Attributes

| Attribute                   | Description                                                                                                                       | Required                                    |
|-----------------------------|-----------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------|
| `src`                       | Path to the ZIP archive to extract.                                                                                               | Yes (unless resource collections are used)  |
| `dest`                      | Directory where extracted files are placed.                                                                                       | Yes                                         |
| `encoding`                  | Character encoding used for filenames in the ZIP (defaults to `UTF8`).                                                            | No                                          |
| `overwrite`                 | Overwrite existing files even if they are newer (`true                                                                            | false`; default: `true`).                   | No       |
| `failOnEmptyArchive`        | Treat an empty archive as an error (`true                                                                                         | false`; default: `false`, since Ant 1.8.0). | No       |
| `stripAbsolutePathSpec`     | Remove leading `/` or `\` from entry names before extraction (default: `true`, since Ant 1.10.4).                                 | No                                          |
| `scanForUnicodeExtraFields` | Use Unicode extra fields for entry names if present, ignoring `encoding` (default: `true`).                                       | No                                          |
| `allowFilesToEscapeDest`    | Allow extracted files to be outside the `dest` directory; only if `stripAbsolutePathSpec="false"` and entries start with a slash. | No                                          |

## Nested Elements

- `<patternset>` — Include/exclude patterns to select entries within the archive.
- **Resource Collections** — `<fileset>`, `<filelist>`, `<path>`, or `<files>` to specify one or more archive files to process.
- `<mapper>` — Define filename transformations for extracted files (default is identity).

## Notes

- File permissions and ownership are not preserved.
- Timestamp handling follows the InfoZIP/zlib algorithm; archives from Windows may yield different timestamps.
- When using nested resource collections, `src` is not required; each matching resource is processed.
- For additional filtering or copying features (e.g., filtering file contents), consider using `<copy>` with `<zipfileset>`.
- Resource collections may be used to select archived files to perform unarchival upon. Only file system based resource collections are supported by Unjar/Unwar/Unzip, this includes fileset, filelist, path, and files. Untar supports arbitrary resource collections.
- The untar task recognizes the long pathname entries used by GNU tar.
- different ZIP tools handle timestamps differently when it comes to applying timezone offset calculations of files. Some ZIP libraries will store the timestamps as they've been read from the filesystem while others will modify the timestamps both when reading and writing the files to make all timestamps use the same timezone. A ZIP archive created by one library may extract files with "wrong timestamps" when extracted by another library. Ant's ZIP classes use the same algorithm as the InfoZIP tools and zlib (timestamps get adjusted), Windows' "compressed folders" function and WinZIP don't change the timestamps. This means that using the unzip task on files created by Windows' compressed folders function may create files with timestamps that are "wrong", the same is true if you use Windows' functions to extract an Ant generated ZIP archive.

## Reference

- [Ant Manual: unzip Task](https://ant.apache.org/manual/Tasks/unzip.html)
