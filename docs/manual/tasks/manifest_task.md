# manifest

The `manifest` task is used to create or modify the manifest file of a JAR. It supports adding attributes and sections, and can be used both within the `jar` task or standalone to generate a manifest file.

## Usage Example

### Inside `jar` task

```groovy
target('jarWithManifest') {
  jar(destfile: 'build/myapp.jar') {
    manifest() {
      attribute(name: 'Main-Class', value: 'com.example.Main')
      attribute(name: 'Implementation-Version', value: '1.0.0')
      section(name: 'com.example') {
        attribute(name: 'Specification-Version', value: '1.0')
      }
    }
    fileset(dir: 'build/classes')
  }
}
```

### Standalone manifest generation

```groovy
target('generateManifestFile') {
  manifest(file: 'build/manifest.tmp') {
    attribute(name: 'Created-By', value: 'Uso Build')
  }
}
```

## Attributes

| Attribute | Description                                                              | Required |
|----------:|--------------------------------------------------------------------------|----------|
| `file`    | Path to write the manifest to (if outside a `jar` task); defaults to `META-INF/MANIFEST.MF` inside a JAR | No       |
| `mode`    | How to handle existing manifests: `update`, `merge`, or `skip`           | No (default: `update`) |

## Nested Elements

- `attribute` — Adds a name/value pair to the main manifest or to a section.
- `section` — Defines a named section containing nested `attribute` elements.
- `main` — Alias for the main manifest section.
- `classpath` — Adds Class-Path entries; nested elements specify individual paths.

## Notes

- Within a `jar` task, `manifest` customizes the JAR’s manifest rather than generating one standalone.
- `mode="merge"` merges with an existing manifest file; `update` overwrites or adds entries; `skip` preserves the existing manifest entirely.
- Class-Path values are space-separated if specified via nested `classpath` or attributes.

## Reference

- [Ant Manual: manifest Task](https://ant.apache.org/manual/Tasks/manifest.html)
