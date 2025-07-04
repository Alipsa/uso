# loadfile

The `loadfile` task reads the content of a file and assigns it to a property. This is useful for reading text content like version strings, license files, or configuration snippets into your build process.

## Usage Example

```groovy
target('readVersion') {
  loadfile(property: 'version', srcFile: 'version.txt')
}
```

This sets the property `version` to the contents of the file `version.txt`.

## Attributes

| Attribute     | Description                                                    | Required |
|---------------|----------------------------------------------------------------|----------|
| `property`     | The name of the property to assign the file contents to        | Yes      |
| `srcFile`      | The file to read from                                          | Yes      |
| `encoding`     | The encoding used to read the file                             | No       |
| `failonerror`  | Whether to fail the build if the file cannot be read           | No (default: true) |

## Notes

- This task is ideal for reading values like a version number or license header into a build property.
- If the file does not exist and `failonerror` is false, no property will be set.
- If no encoding is specified, the platform default will be used.

## Reference

- [Ant Manual: loadfile Task](https://ant.apache.org/manual/Tasks/loadfile.html)
