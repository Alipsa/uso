# record

The `record` task adds or configures a recorder listener to capture Ant build output into a log file. You can start, stop, and adjust the recorder’s settings dynamically.

## Usage Examples

```groovy
target('compileWithLogging') {
  // Start recording build output for the compile target
  record(name: 'compile.log', action: 'start')

  javac(srcdir: 'src', destdir: 'build/classes')

  // Stop recording after compilation
  record(name: 'compile.log', action: 'stop')
}
```

```groovy
target('setupMultipleRecorders') {
  // Default start recorder at INFO level
  record(name: 'records-simple.log')

  // Recorder at verbose level
  record(name: 'ISO.log', loglevel: 'verbose')
}
```

## Attributes

| Attribute   | Description                                                                                                                         | Required |
|------------:|-------------------------------------------------------------------------------------------------------------------------------------|----------|
| `name`      | Unique identifier and filename for the recorder (the log file path).                                                               | Yes      |
| `action`    | Controls the recorder state: `start` or `stop`. Defaults to `start` on first use, and no change on subsequent calls if omitted.    | No       |
| `append`    | Whether to append to an existing file (`true`) or overwrite it (`false`). Only applies the first time the recorder is created.      | No       |
| `emacsmode` | If `true`, removes task banners (mimics Ant’s `-emacs` switch).                                                                    | No       |
| `loglevel`  | Logging level for the recorder: `error`, `warn`, `info`, `verbose`, or `debug`. Defaults to no change on subsequent calls.          | No       |

## Notes

- Multiple recorders (with different `name` values) can coexist.
- The recorder flushes on `buildFinished`, `targetFinished`, and `taskFinished` events, and closes on `buildFinished`.
- You can change the recorder’s `loglevel` mid-build by calling `record` again with the same `name`.
- Future enhancements may include listeners, include/exclude configurations for tasks/targets.

## Reference

- [Ant Manual: record Task](https://ant.apache.org/manual/Tasks/recorder.html)
