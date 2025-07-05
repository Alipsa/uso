# loadproperties

The `loadproperties` task loads a `.properties` file and assigns its keys and values to Ant project properties. It supports filtering and can load from a file, resource, or nested resource structure.

## Usage Example

```groovy
target('loadProps') {
  loadproperties(srcFile: 'config/settings.properties')
}
```

Loads `settings.properties` and assigns its keys to Ant properties.

### With prefix

```groovy
target('loadPrefixedProps') {
  loadproperties(srcFile: 'config/settings.properties', prefix: 'app')
}
```

This would load `version=1.0` as `app.version=1.0`.

### With filterchain

```groovy
target('loadFilteredProps') {
  loadproperties(srcFile: 'config/settings.properties') {
    filterchain {
      linecontains {
        contains(value: 'database')
      }
    }
  }
}
```

Only lines containing the word "database" will be loaded.

## Attributes

| Attribute      | Description                                                  | Required                                         |
|----------------|--------------------------------------------------------------|--------------------------------------------------|
| `srcFile`      | File to load from                                            | One of `srcFile`, `resource`, or nested resource |
| `resource`     | Resource to load from (e.g., from classpath)                 | One of `srcFile`, `resource`, or nested resource |
| `encoding`     | Encoding of the file or resource                             | No                                               |
| `classpath`    | Classpath for resource loading                               | No                                               |
| `classpathref` | Reference to a path for classpath                            | No                                               |
| `prefix`       | Prefix to apply to all property names                        | No                                               |
| `prefixValues` | Whether to prefix values in addition to keys (default: true) | No                                               |

## Notes

- This task is more powerful than `property(file: '...')` because it fails on missing files and supports filterchains.
- Use `prefix` to avoid property name clashes.
- When loading from classpath, make sure resources are correctly included.

## Reference

- [Ant Manual: loadproperties Task](https://ant.apache.org/manual/Tasks/loadproperties.html)
