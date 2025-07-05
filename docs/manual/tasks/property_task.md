# property

The `property` task sets one or more Ant project properties. It loads values from attributes, files, resources, or nested elements and supports optional overrides and filtering.

## Usage Examples

### Set a simple property
```groovy
target('defineVersion') {
  property(name: 'version', value: '1.0.0')
}
```

### Load properties from a file
```groovy
target('loadProps') {
  property(file: 'config/settings.properties')
}
```

### Set a property only if undefined
```groovy
target('defaultEncoding') {
  property(name: 'encoding', value: 'UTF-8', override: false)
}
```

### Use nested elements
```groovy
target('loadFilteredProps') {
  property(prefix: 'app') {
    fileset(dir: 'src/main/resources', includes: '*.properties')
  }
}
```

## Attributes

|  Attribute | Description                                                                       | Required                            |
|-----------:|-----------------------------------------------------------------------------------|-------------------------------------|
|     `name` | Name of the property to set                                                       | Yes (unless using nested resource)  |
|    `value` | Value to assign to the property                                                   | Yes (unless using file or resource) |
|     `file` | Path to a properties file to load into properties                                 | No                                  |
| `resource` | Classpath resource to load properties from                                        | No                                  |
|      `url` | URL to load properties from                                                       | No                                  |
| `override` | Whether to overwrite an existing property (`true`/`false`, default: `true`)       | No                                  |
|   `prefix` | Prefix to prepend to property names when loading from resource or nested elements | No                                  |

## Nested Elements

- `<fileset>` — Load properties from multiple files matching include/exclude patterns.
- `<filterchain>` — Apply text filters (e.g., `<tokenfilter>`) before parsing properties.
- Nested `<property>` — Define multiple name/value pairs inline.

## Notes

- If both `value` and `file/resource` are specified, `file/resource` takes precedence.
- Useful for initializing defaults and loading external configurations.
- Use `override="false"` to preserve user-defined properties in command-line or environment.

## Reference

- [Ant Manual: property Task](https://ant.apache.org/manual/Tasks/property.html)
