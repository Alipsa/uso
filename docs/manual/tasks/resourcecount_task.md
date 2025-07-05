# resourcecount

The `resourcecount` task displays or sets a property to the size of a nested resource collection. It can also be used as a `condition` to compare the number of resources against a given count. Introduced in Ant 1.7.

## Usage Examples

### Store count in a property

```groovy
target('countFiles') {
  resourcecount(property: 'count.foo') {
    filelist(dir: '.', files: 'foo,bar')
  }
}
```

This sets `count.foo = 2`.

### Use as a condition

```groovy
target('checkFileLines') {
  property(name: 'file', value: project.properties['ant.file'])
  condition(property: 'tooManyLines') {
    resourcecount(count: 100, when: 'gt') {
      concat {
        filterchain {
          tokenfilter {
            linetokenizer()
          }
        }
        fileset(file: '${file}')
      }
    }
  }
}
```

This sets `tooManyLines` if the build file has more than 100 lines.

## Attributes

|  Attribute | Description                                                                                     | Required                                             |
|-----------:|-------------------------------------------------------------------------------------------------|------------------------------------------------------|
| `property` | Name of the property to set; if omitted, the count is logged instead of stored                  | No                                                   |
|    `refid` | Reference to an existing resource collection                                                    | Yes, unless a nested resource collection is supplied |
|    `count` | Comparison count for use in a condition                                                         | Yes, when used as a condition; ignored otherwise     |
|     `when` | Comparison type: `equal`/`eq`, `greater`/`gt`, `less`/`lt`, `ge`, `ne`, `le` (default: `equal`) | No                                                   |

## Nested Elements

- **Resource collection**  
  Specify the collection via nested elements such as `<fileset>`, `<filelist>`, `<path>`, `<zipfileset>`, etc.

## Notes

- Without `count` and `when`, the task simply logs or sets the raw count.
- Commonly used to assert non-zero resource existence or file presence.
- Pair with `condition` to fail builds on unexpected counts.

## Reference

- [Ant Manual: resourcecount Task](https://ant.apache.org/manual/Tasks/resourcecount.html)
