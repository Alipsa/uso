# propertyfile

The `propertyfile` task updates or creates a Java `.properties` file by setting keys programmatically. It supports setting, incrementing, appending, and deleting property entries.

## Usage Examples

### Create or update a properties file
```groovy
target('updateVersionFile') {
  propertyfile(
    file: 'build/version.properties'
  ) {
    entry(key: 'version', value: '1.0.0')
    entry(key: 'buildTimestamp', type: 'comment', value: 'Build generated on ${timestamp}')
  }
}
```

### Increment a numeric property
```groovy
target('incrementBuildNumber') {
  propertyfile(
    file: 'build/version.properties'
  ) {
    entry(key: 'buildCount', type: 'int', operation: 'increment', default: '0')
  }
}
```

### Append to an existing property
```groovy
target('appendClasspath') {
  propertyfile(
    file: 'build/config.properties'
  ) {
    entry(key: 'classpath', type: 'path', operation: 'append', value: 'lib/new.jar')
  }
}
```

### Delete a property entry
```groovy
target('cleanupProperties') {
  propertyfile(
    file: 'build/version.properties'
  ) {
    entry(key: 'obsoleteKey', operation: 'delete')
  }
}
```

## Attributes

| Attribute      | Description                                                       | Required |
|---------------:|-------------------------------------------------------------------|----------|
| `file`         | Path to the properties file to read/update                        | Yes      |
| `encoding`     | Character encoding of the file (default: platform encoding)       | No       |
| `comment`      | Header comment to add at the top of the file                      | No       |
| `commentchar`  | Character to use for comment lines (default: `#`)                 | No       |
| `keepformat`   | Preserve the existing file format and comments (`true`/`false`)   | No (default: `false`) |

## Nested Elements

- `<entry>` — Defines an operation on a property:
  - `key` — Property name (required)
  - `value` — Value to set or append (optional for `delete`)
  - `type` — Data type: `string`, `int`, `float`, `path`, `comment` (default: `string`)
  - `operation` — `set`, `increment`, `append`, `delete` (default: `set`)
  - `default` — Default value if the entry or file does not exist (for `increment`)

## Notes

- Supports comment entries by setting `type='comment'`.
- For `append`, multiple calls will accumulate values separated by the system path separator.
- The task writes changes back to the file atomically.
- Useful for CI pipelines to bump build numbers or update configuration files.

## Reference

- [Ant Manual: propertyfile Task](https://ant.apache.org/manual/Tasks/propertyfile.html)
