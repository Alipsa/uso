# touch

The `touch` task updates the modification time of files or resources and can create them if they do not exist. It works on single files via the `file` attribute or on arbitrary resource collections, with options to set explicit timestamps, relative permissions, and directory creation.

## Usage Examples

```groovy
target('touchCurrent') {
  // Create 'myfile' if needed and set its timestamp to the current time
  touch(file: 'myfile')
}

target('touchDate') {
  // Set modification time to Jun 28 2000 2:02 pm
  touch(file: 'myfile', datetime: '06/28/2000 2:02 pm')
}

target('touchAllSources') {
  // Set all files under src_dir (and directories) to Oct 09 1974 4:30 pm
  touch(datetime: '09/10/1974 4:30 pm') {
    fileset(dir: 'src_dir')
  }
}

target('touchWithMapper') {
  // Create 'bar' based on 'foo' timestamp using a mapper
  touch(file: 'foo') {
    mapper(type: 'glob', from: 'foo', to: 'bar')
  }
}
```

## Attributes

| Attribute  | Description                                                                                                                                | Required    |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------|-------------|
| `file`     | Single file name to touch; required if no nested resource collections are specified.                                                       | Conditional |
| `millis`   | Epoch time in milliseconds to set as the modification time. Ignored if `datetime` is provided; defaults to current time when both omitted. | No          |
| `datetime` | Human-readable date/time string (e.g., `MM/dd/yyyy hh:mm a`) to set as the modification time; special value `"now"` uses the current time. | No          |
| `pattern`  | `SimpleDateFormat`–compatible pattern used to parse `datetime`; defaults to US locale pattern.                                             | No          |
| `mkdirs`   | Create parent directories when creating new files (`true`/`false`, default: `false`).                                                      | No          |
| `verbose`  | Log creation of new files to the build output (`true`/`false`, default: `true`).                                                           | No          |

## Nested Elements

- **Resource Collections**: Any Ant resource collection (e.g., `<fileset>`, `<filelist>`, `<path>`) to specify multiple resources.
- `<mapper>`: Map source files to target names before touching (`type`, `from`, `to`), introduced in Ant 1.6.3.

## Notes

- By default, if no `millis` or `datetime` is specified, the current system time is used.
- Directories matched by nested resource collections are also “touched” for backward compatibility; use selectors to filter if needed.
- Use `mkdirs="true"` to ensure parent directories exist when creating new files.
- Timestamp granularity depends on the underlying filesystem and OS.

## Reference

- [Ant Manual: touch Task](https://ant.apache.org/manual/Tasks/touch.html)
