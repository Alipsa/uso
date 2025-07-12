# tar

The `tar` task creates a tar archive from a set of files, using an implicit fileset or nested resource collections. It supports control over long file names, compression methods, and file metadata (mode, user, group).

## Usage Examples

```groovy
// Create a simple tar archive of the htdocs/manual directory
target('makeManualTar') {
  tar(
    destfile: 'dist/manual.tar',
    basedir: 'htdocs/manual'
  )
}

// Create a tar with excludes and then compress separately
target('makeFilteredTar') {
  tar(
    destfile: "${dist}/manual.tar",
    basedir: 'htdocs/manual',
    excludes: 'mydocs/**, **/todo.html'
  )
  gzip(
    destfile: "${dist}/manual.tar.gz",
    src: "${dist}/manual.tar"
  )
}

// Use nested tarfileset to set fullpath and preserve leading slashes
target('makeDocsTar') {
  tar(destfile: "${basedir}/docs.tar") {
    tarfileset(
      dir: "${dir.src}/docs",
      fullpath: "/usr/doc/ant/README",
      preserveLeadingSlashes: true
    ) {
      include(name: "readme.txt")
    }
    tarfileset(
      dir: "${dir.src}/docs",
      prefix: "/usr/doc/ant",
      preserveLeadingSlashes: true
    ) {
      include(name: "*.html")
    }
  }
}

// Create a PAX tar with GNU extensions for long paths and mixed file modes
target('makePosixTar') {
  tar(
    destfile: "${dist.base}/${dist.name}-src.tar",
    longfile: "posix"
  ) {
    tarfileset(
      dir: "${dist.name}/..",
      filemode: "755",
      username: "ant",
      group: "ant"
    ) {
      include(name: "${dist.name}/bootstrap.sh")
      include(name: "${dist.name}/build.sh")
    }
    tarfileset(
      dir: "${dist.name}/..",
      username: "ant",
      group: "ant"
    ) {
      include(name: "${dist.name}/**")
      exclude(name: "${dist.name}/bootstrap.sh")
      exclude(name: "${dist.name}/build.sh")
    }
  }
}

// Repackage a ZIP as a gzip-compressed tar preserving permissions
target('zipToTarGz') {
  tar(
    destfile: "release.tar.gz",
    compression: "gzip"
  ) {
    zipfileset(src: "release.zip")
  }
}
```

## Attributes

| Attribute         | Description                                                                                               | Required |
|-------------------|-----------------------------------------------------------------------------------------------------------|----------|
| `destfile`        | Path to the tar file to create (or replace).                                                              | Yes      |
| `basedir`         | Base directory from which to include files (implicit fileset).                                            | No       |
| `longfile`        | Handling of long paths: `truncate`, `fail`, `warn`, `omit`, `gnu`, `posix` (default: `warn`).             | No       |
| `includes`        | Comma- or space-separated patterns of files to include (implicit with `basedir`).                         | No       |
| `includesfile`    | File containing include patterns, one per line.                                                           | No       |
| `excludes`        | Comma- or space-separated patterns of files to exclude.                                                   | No       |
| `excludesfile`    | File containing exclude patterns, one per line.                                                           | No       |
| `defaultexcludes` | Whether to apply default excludes (`yes` or `no`; default: `yes`).                                        | No       |
| `compression`     | Compression method: `none`, `gzip`, `bzip2`, `xz` (default: `none`).                                      | No       |
| `encoding`        | Character encoding for file names in the archive (since Ant 1.9.5; default: JVM encoding).                | No       |
| `overwrite`       | Overwrite existing tar file if present, even if newer (default: `false`).                                 | No       |
| `filemode`        | Default file mode (octal) applied to all files in the implicit fileset (overridden by nested tarfileset). | No       |
| `dirmode`         | Default directory mode (octal) for directories in the implicit fileset (overridden by nested tarfileset). | No       |
| `username`        | Default user name for file entries (overridden by nested tarfileset).                                     | No       |
| `group`           | Default group name for file entries (overridden by nested tarfileset).                                    | No       |

## Nested Elements

- `<tarfileset>` — Extended fileset for tar archives, supporting attributes:
  - `dir`, `prefix`, `fullpath`, `filemode`, `dirmode`, `username`, `group`, `preserveLeadingSlashes`.
- Any Ant resource collection (e.g., `<fileset>`, `<zipfileset>`) to include resources.

## Notes

- The tar task replaces the archive if it already exists; it does not append.
- Use `longfile="posix"` or `longfile="gnu"` for support of long file names.
- Compression is applied as part of tar for gzip, bzip2, and xz methods.
- Permissions and ownership can be controlled globally via attributes or per-entry via `<tarfileset>`.

## Reference

- [Ant Manual: tar Task](https://ant.apache.org/manual/Tasks/tar.html)
