# presetdef

The `presetdef` task defines a new custom task or data type by pre-configuring attributes and nested elements of an existing task/type. It allows you to create a shorthand for frequently-used tasks with common settings.

## Usage Example

```groovy
target('defineZipPreset') {
  presetdef(name: 'zipTextFiles') {
    // Preset a zipfileset for text files in build directory
    mapper(type: 'glob', from: '**/*.txt', to: '**/*.zip')
    zipfileset(dir: 'build', includes: '**/*.txt')
  }
}

target('packageText') {
  // Use the new preset task
  zipTextFiles(destfile: 'build/text-files.zip')
}
```

## Attributes

| Attribute  | Description                                                                  | Required |
|-----------:|------------------------------------------------------------------------------|----------|
|     `name` | Name of the new custom task or type to define                                | Yes      |

## Nested Elements

- `<mapper>` — Pre-configure a filename mapper (e.g., `type="glob"`) for the preset.
- Any nested element of the original task/type (e.g., `<zipfileset>`, `<fileset>`, etc.) — these become implicit in the custom task invocation.
- `<attribute>` — Preset specific attributes of the original task/type.

## Notes

- The new preset task can be invoked with only the attributes/nested elements you did *not* preset.
- You can define multiple nested elements to fully customize the preset.
- Useful for simplifying build scripts by grouping common configurations.

## Reference

- [Ant Manual: presetdef Task](https://ant.apache.org/manual/Tasks/presetdef.html)
