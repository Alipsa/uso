# pathconvert

The `pathconvert` task converts a path or fileset into a String property, with customizable separators and formatting. It is useful for converting Ant `Path` or resource collections into properties for use in other tasks or tools.

## Usage Examples

### Convert a Path reference
```groovy
target('convertPath') {
  pathconvert(property: 'classpath.string', pathref: 'compilePath', pathsep: ':')
}
```

### Convert a fileset
```groovy
target('filesetToProperty') {
  pathconvert(property: 'resources', pathsep: ',', dirsep: '/', prefix: 'res-', suffix: '.xml') {
    fileset(dir: 'src/main/resources', includes: '**/*.xml')
  }
}
```

## Attributes

| Attribute   | Description                                                                                | Required |
|------------:|--------------------------------------------------------------------------------------------|----------|
| `property`  | Name of the property to set with the converted path                                       | Yes      |
| `pathref`   | Reference to an existing Ant `Path` or classpath                                           | One of `pathref`, `fileset`, or nested resource |
| `pathsep`   | String used to separate path elements (default: system path separator)                     | No       |
| `dirsep`    | Directory separator to normalize paths (default: system file separator)                    | No       |
| `prefix`    | Prefix added to each path element in the output                                           | No       |
| `suffix`    | Suffix added to each path element in the output                                           | No       |
| `targetos`  | If specified (`windows`, `unix`), uses that OS’s separators regardless of current platform | No       |

## Nested Elements

- `fileset` — Include files via patterns instead of a `pathref`.
- `dirset` — Include directories via patterns.
- `path` — Define an inline Ant Path for conversion.
- `mapper` — Apply a filename mapper to modify each entry.

## Notes

- If both `pathref` and nested elements are provided, nested elements take precedence.
- Use `pathsep` to control the delimiter for different tools (e.g., `;` on Windows).
- `dirsep` helps normalize paths when generating properties consumed on different platforms.
- Setting `prefix`/`suffix` is useful for templating entries (e.g., adding `file://`).

## Reference

- [Ant Manual: pathconvert Task](https://ant.apache.org/manual/Tasks/pathconvert.html)
