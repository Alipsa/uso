# manifestclasspath

The `manifestclasspath` task converts a classpath Path into a property suitable for use in a JAR manifest's `Class-Path` attribute. It computes relative URLs from the JAR file to each classpath entry.

## Usage Example

```groovy
target('updateManifestClassPath') {
  manifestclasspath(
    property: 'jar.classpath',
    jarfile: 'build/acme.jar',
    maxParentLevels: 2
  ) {
    classpath(refid: 'classpath')
  }
}
```

This creates a property `jar.classpath` containing a space-separated list of JAR and directory paths relative to `build/acme.jar`'s parent directory, up to two levels up.

## Attributes

|         Attribute | Description                                                                                         | Required |
|------------------:|-----------------------------------------------------------------------------------------------------|----------|
|        `property` | Name of the property to set (must not already exist)                                                | Yes      |
|         `jarfile` | Path to the JAR file whose manifest will use the `Class-Path` entries (file need not exist)         | Yes      |
| `maxParentLevels` | Maximum number of parent directory levels (`..`) allowed when computing relative paths (default: 2) | No       |

## Nested Elements

- `classpath` — Defines the classpath Path whose elements will be converted. Can reference an existing Path via `refid` or define inline.

## Notes

- The resulting property value may exceed the 72-character per line limit in manifests, but the `manifest` task will wrap lines correctly per the JAR specification.
- If a path entry cannot be found or would require traversing above the allowed parent levels, it is ignored.
- Useful for automatically generating Class-Path entries in a JAR manifest to avoid long command-line classpaths.

## Reference

- [Ant Manual: manifestclasspath Task](https://ant.apache.org/manual/Tasks/manifestclasspath.html)
