# zip

The `zip` task creates or updates ZIP archives by packaging files from specified resources. It supports pattern-based inclusion/exclusion, nested resource collections, filename transformations via mappers, and control over compression and archive metadata.

## Usage Examples

```groovy
// Create a ZIP archive of compiled classes
target('packageZip') {
  zip(
    destfile: 'dist/app.zip',
    basedir: 'build/classes'
  )
}

// Update an existing ZIP with new web resources
target('updateZip') {
  zip(
    destfile: 'dist/app.zip',
    update: true
  ) {
    fileset(dir: 'src/main/webapp', includes: '**/*.html')
  }
}

// Include only properties files and transform .properties to .cfg
target('configZip') {
  zip(destfile: 'dist/config.zip') {
    fileset(dir: 'config') {
      include(name: '**/*.properties')
    }
    mapper(type: 'glob', from: '*.properties', to: '*.cfg')
  }
}

// Batch create ZIPs for each module directory
target('batchZip') {
  fileset(dir: 'modules', includes: '*/build/classes')
  zip(destfile: 'dist/modules.zip') {
    fileset(dir: 'modules', includes: '*/build/classes')
  }
}
```

## Attributes

| Attribute              | Description                                                                                                     | Required                   |
|------------------------|-----------------------------------------------------------------------------------------------------------------|----------------------------|
| `destfile`             | Path to the ZIP file to create or update.                                                                       | Yes                        |
| `zipfile`              | **Deprecated** alias for `destfile`.                                                                            | No                         |
| `basedir`              | Base directory for implicit fileset. If specified, all files under this directory are included unless filtered. | No                         |
| `compress`             | Whether to compress entries (`true                                                                              | false`, default: `true`).  | No       |
| `update`               | Update existing archive instead of overwriting (`true                                                           | false`, default: `false`). | No       |
| `encoding`             | Character encoding for entry names in the archive (default: `UTF8`).                                            | No                         |
| `level`                | Compression level (0–9) for ZIP entries; higher values yield better compression but slower speed.               | No                         |
| `includes`             | Comma‑ or space‑separated patterns to include (when using `basedir`).                                           | No                         |
| `includesfile`         | File containing include patterns, one per line.                                                                 | No                         |
| `excludes`             | Comma‑ or space‑separated patterns to exclude.                                                                  | No                         |
| `excludesfile`         | File containing exclude patterns, one per line.                                                                 | No                         |
| `defaultexcludes`      | Whether to apply Ant’s default excludes (`yes                                                                   | no`, default: `yes`).      | No       |
| `filesonly`            | Store only file entries and omit directory entries (`true                                                       | false`, default: `false`). | No       |
| `duplicate`            | Behavior on duplicate entries: `add`, `preserve`, `fail` (default: `add`).                                      | No                         |
| `whenempty`            | Action when no entries match: `fail`, `skip`, `create` (default: `create`).                                     | No                         |
| `zip64Mode`            | Zip64 extension mode: `never`, `always`, `as-needed` (default: `never`).                                        | No                         |
| `roundup`              | Round file modification times up to even 2-second multiples (`true                                              | false`, default: `true`).  | No       |
| `preserve0permissions` | Preserve original Unix permissions of files on update when mode is zero (`true                                  | false`, default: `false`). | No       |

## Nested Elements

- `<fileset>` — Select file system entries to include.
- `<zipfileset>` — Specialized fileset for ZIP entries, supports `prefix`, `filemode`, and other metadata.
- `<patternset>` — Include/exclude patterns for entries.
- `<mapper>` — Transform entry names (e.g., extension changes).
- `<zipgroupfileset>` — Combine multiple archives into one ZIP.

## Notes

- When `update="true"`, existing entries not matched by nested elements remain unchanged.
- Use `<zipfileset>` to customize file paths, permissions, or entry-specific options.
- Zip64 support (`zip64Mode`) is necessary for archives with large files or many entries.
- Compression level can significantly impact CPU usage; choose appropriately for build environments.

## Reference

- [Ant Manual: zip Task](https://ant.apache.org/manual/Tasks/zip.html)
