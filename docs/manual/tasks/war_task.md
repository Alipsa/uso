# war

The `war` task extends the `jar` task to create a Web Application Archive (WAR) with the standard layout for Java web applications: `WEB-INF/classes`, `WEB-INF/lib`, and web application files at the archive root.

(The War task is a shortcut for specifying the particular layout of a WAR file. The same thing can be accomplished by using the prefix and fullpath attributes of zipfilesets in a Zip or Jar task.)

The extended zipfileset element from the zip task (with attributes prefix, fullpath, and src) is available in the War task. The task is also resource-enabled and will add nested resources and resource collections to the archive.

## Usage Examples

```groovy
target('packageWar') {
  war(
    destfile: 'dist/myapp.war',
    webxml: 'src/main/webapp/WEB-INF/web.xml'
  ) {
    fileset(dir: 'src/main/webapp')
    lib(dir: 'thirdparty/libs') {
      exclude(name: 'jdbc1.jar')
    }
    classes(dir: 'build/classes')
    zipfileset(dir: 'src/graphics/images', prefix: 'images')
  }
}

target('updateWar') {
  war(destfile: 'dist/myapp.war', update: true) {
    fileset(dir: 'patches', includes: '**/*.jsp')
  }
}
```

## Attributes

| Attribute                  | Description                                                                                                            | Required                          |
|----------------------------|------------------------------------------------------------------------------------------------------------------------|-----------------------------------|
| `destfile`                 | Path to the WAR file to create.                                                                                        | Yes                               |
| `warfile`                  | **Deprecated** alias for `destfile`.                                                                                   | No                                |
| `webxml`                   | Path to the servlet descriptor (`WEB-INF/web.xml`).                                                                    | Yes, unless `needxmlfile="false"` |
| `needxmlfile`              | Whether a `web.xml` file is required (`true`/`false`; default: `true`).                                                | No                                |
| `basedir`                  | Base directory for implicit fileset entries.                                                                           | No                                |
| `compress`                 | Whether to compress entries (`true`/`false`; default: `true`).                                                         | No                                |
| `keepcompression`          | Preserve compression settings from existing archives when updating (`true`/`false`; default: `false`).                 | No                                |
| `encoding`                 | Character encoding for file names in the archive (default: `UTF8`).                                                    | No                                |
| `filesonly`                | Store only file entries and omit directory entries (`true`/`false`; default: `false`).                                 | No                                |
| `includes`                 | Patterns of files to include (comma- or space-separated).                                                              | No                                |
| `includesfile`             | File containing include patterns, one per line.                                                                        | No                                |
| `excludes`                 | Patterns of files to exclude (comma- or space-separated).                                                              | No                                |
| `excludesfile`             | File containing exclude patterns, one per line.                                                                        | No                                |
| `defaultexcludes`          | Whether to apply Ant’s default excludes (`yes`/`no`; default: `yes`).                                                  | No                                |
| `manifest`                 | Path to a manifest file to include (`META-INF/MANIFEST.MF`).                                                           | No                                |
| `filesetmanifest`          | Behavior when a manifest is found in a nested ZIP/JAR resource: `skip`, `merge`, `mergewithoutmain` (default: `skip`). | No                                |
| `whenmanifestonly`         | Action when no files match (e.g., `fail`, `skip`, `create`; default: `create`).                                        | No                                |
| `update`                   | Whether to update an existing WAR instead of overwriting (`true`/`false`; default: `false`).                           | No                                |
| `duplicate`                | Behavior when duplicate entries are found: `add`, `preserve`, `fail` (default: `add`).                                 | No                                |
| `roundup`                  | Round file modification times up to even 2-second multiples (`true`/`false`; default: `true`).                         | No                                |
| `level`                    | Compression level for ZIP entries (0–9; default: container default).                                                   | No                                |
| `preserve0permissions`     | Preserve original Unix permissions of 0 entries when updating (`true`/`false`; default: `false`).                      | No                                |
| `useLanguageEncodingFlag`  | Set language encoding flag if encoding is UTF-8 (`true`/`false`; default: `true`).                                     | No                                |
| `createUnicodeExtraFields` | Controls Unicode extra fields: `never`, `always`, `not-encodeable` (default: `never`).                                 | No                                |
| `fallbacktoUTF8`           | Fallback to UTF-8 encoding if file name cannot be encoded (`true`/`false`; default: `false`).                          | No                                |
| `mergeClassPathAttributes` | Merge `Class-Path` attributes in merged manifests (`true`/`false`; default: `false`).                                  | No                                |
| `flattenAttributes`        | Flatten duplicate manifest attributes into one (`true`/`false`; default: `false`).                                     | No                                |
| `zip64Mode`                | Zip64 extension handling: `never`, `always`, `as-needed` (default: `never`).                                           | No                                |
| `modificationtime`         | Set all entry timestamps to the given ISO 8601 timestamp or milliseconds-since-epoch (since Ant 1.10.2).               | No                                |

## Nested Elements

- **`<lib>`**: A fileset whose contents are placed in `WEB-INF/lib`.  
- **`<classes>`**: A fileset whose contents are placed in `WEB-INF/classes`.  
- **`<webinf>`**: A fileset whose contents are placed in `WEB-INF` (excluding `web.xml`).  
- **`<metainf>`**: A fileset whose contents are placed in `META-INF` (excluding `MANIFEST.MF`).  
- **`<zipfileset>`**: A specialized fileset with `prefix`, `fullpath`, and `src` attributes for custom layout.  
- Any Ant resource collection (`<fileset>`, `<path>`, `<filelist>`, etc.) to specify additional entries.

## Notes

- Multiple nested elements preserve correct WAR directory structure without manual prefix settings.  
- When `update="true"`, existing entries not matched by nested elements remain unchanged.
- the Zip format allows multiple files of the same fully-qualified name to exist within a single archive. This has been documented as causing various problems for unsuspecting users. If you wish to avoid this behavior you must set the duplicate attribute to a value other than its default (i.e. `add`, e.g. `fail`).

## Reference

- [Ant Manual: war Task](https://ant.apache.org/manual/Tasks/war.html)
