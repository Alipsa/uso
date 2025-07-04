# length

The `length` task computes the length of a string or the size (in bytes) of a file or group of files. It can be used to assign the result to a property or as a condition to compare against a specific value.

## Usage Examples

### Get length of a string

```groovy
target('stringLength') {
  length(string: 'hello', property: 'len.hello')
}
```

Sets `len.hello = 5`.

### Get size of a file

```groovy
target('fileLength') {
  length(file: 'README.md', property: 'len.readme')
}
```

Stores the number of bytes in the file into `len.readme`.

### Get total size of all `.java` files

```groovy
target('sumLength') {
  length(property: 'total.size') {
    fileset(dir: 'src', includes: '**/*.java')
  }
}
```

### Use as a condition

```groovy
target('checkReadme') {
  condition(property: 'readme.nonempty') {
    length(file: 'README.md', length: 0, when: 'gt')
  }
}
```

Sets the property if the file is larger than 0 bytes.

## Attributes

| Attribute   | Description                                                             | Required |
|------------:|-------------------------------------------------------------------------|----------|
| `string`     | String whose length will be computed                                   | One of `string`, `file`, or nested resource |
| `file`       | File whose size in bytes will be computed                              | One of `string`, `file`, or nested resource |
| `property`   | Name of the property to assign the computed length                     | No       |
| `length`     | Length to compare against (used in conditions)                         | No       |
| `when`       | Comparison operator (`equal`, `gt`, `lt`, `ge`, `le`, `ne`)            | No (used in conditions) |
| `mode`       | `all` (sum sizes) or `each` (report each resource)                     | No (default: `all`) |
| `trim`       | Whether to trim whitespace when computing string length                | No (default: `false`) |

## Notes

- In `each` mode, the task logs the path and size of each resource to the console.
- When no `property` is specified, the result is only logged.
- Can be used both in targets and in `condition` elements.

## Reference

- [Ant Manual: length Task](https://ant.apache.org/manual/Tasks/length.html)
