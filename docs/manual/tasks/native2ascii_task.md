# native2ascii

The `native2ascii` task converts files containing non-ASCII characters into ASCII text with Unicode escapes (e.g., `\u00E9` for `é`). It is commonly used to prepare Java `.properties` files, which by default only support ISO-8859-1 encoding.

## Usage Examples

### Convert a single file
```groovy
target('convertProperties') {
  native2ascii(
    src: 'src/main/resources/messages_fr.properties',
    dest: 'build/messages_fr.properties'
  )
}
```

### Batch conversion using a fileset
```groovy
target('convertAllProperties') {
  native2ascii(destdir: 'build/resources') {
    fileset(dir: 'src/main/resources', includes: '**/*.properties')
  }
}
```

## Attributes

| Attribute     | Description                                                                  | Required                                                       |
|--------------:|------------------------------------------------------------------------------|----------------------------------------------------------------|
| `src`         | Source file to convert                                                       | One of `src` or nested `<fileset>` (when using `destdir`)      |
| `dest`        | Destination file for the converted output                                    | Required if `src` is used                                       |
| `destdir`     | Directory to place converted files                                           | Required if nested `<fileset>` is used                          |
| `encoding`    | Character encoding of input files (default: platform default)                | No                                                             |
| `propertyfile`| Alias for `src` (supported for backward compatibility)                       | No                                                             |

## Nested Elements

- `fileset` — Defines a set of input files to be converted when using `destdir`.

## Notes

- The task outputs Unicode escapes for any character outside ISO-8859-1.
- Useful for internationalization of Java `.properties` files.
- Ensure you reference the converted files in your classpath or resource loading.

## Reference

- [Ant Manual: native2ascii Task](https://ant.apache.org/manual/Tasks/native2ascii.html)
