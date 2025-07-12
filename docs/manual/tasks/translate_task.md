# translate

The `translate` task identifies keys in files delimited by special tokens and replaces them with values from a specified resource bundle. It preserves line endings and supports custom file encodings and overwrite behavior.

## Usage Examples

```groovy
// Translate JSP files into Japanese using a SJIS-encoded resource bundle
target('translateJSPs') {
  translate(
    todir: 'build/i18n/ja',
    starttoken: '#',
    endtoken: '#',
    bundle: 'resource/BaseResource',
    bundlelanguage: 'ja',
    forceoverwrite: true,
    srcencoding: 'ISO8859_1',
    destencoding: 'SJIS',
    bundleencoding: 'SJIS'
  ) {
    fileset(dir: 'src/main/webapp', includes: '**/*.jsp')
  }
}

// Generic translation using default locale and UTF-8 encodings
target('translateAll') {
  translate(
    todir: 'build/i18n',
    starttoken: '${',
    endtoken: '}',
    bundle: 'MyResources',
    forceoverwrite: false
  ) {
    fileset(dir: 'src', includes: '**/*.txt')
  }
}
```

## Attributes

| Attribute        | Description                                                                                | Required |
|------------------|--------------------------------------------------------------------------------------------|----------|
| `todir`          | Destination directory where translated files are written                                   | Yes      |
| `starttoken`     | String that prefixes keys in the source files (e.g., `#` or `${`)                          | Yes      |
| `endtoken`       | String that suffixes keys in the source files (e.g., `#` or `}`)                           | Yes      |
| `bundle`         | Base name (family name) of the resource bundle                                             | Yes      |
| `bundlelanguage` | Locale language code for the bundle (e.g., `ja`, `en`; defaults to the JVM default locale) | No       |
| `bundlecountry`  | Locale country code for the bundle (e.g., `US`, `JP`; defaults to the JVM default locale)  | No       |
| `bundlevariant`  | Locale variant for the bundle; defaults based on `bundlelanguage` and `bundlecountry`      | No       |
| `srcencoding`    | Character encoding of source files (defaults to the platform encoding)                     | No       |
| `destencoding`   | Character encoding for translated files (defaults to `srcencoding`)                        | No       |
| `bundleencoding` | Character encoding of the resource bundle property files (defaults to `srcencoding`)       | No       |
| `forceoverwrite` | Overwrite existing destination files even if they are newer (defaults to `false`)          | No       |

## Nested Elements

- `<fileset>` — Resource collection to specify which source files to translate.

## Notes

- File line endings are preserved in the translated output.
- The resource bundle lookup follows standard locale fallback (language, country, variant).
- Bundles correspond to property files (e.g., `baseName_language_country.properties`).
- Introduced in Ant 1.6; supports custom encodings and overwrite control.
- Use `${starttoken}` and `${endtoken}` values that do not occur in normal text.

## Reference

- [Ant Manual: translate Task](https://ant.apache.org/manual/Tasks/translate.html)
