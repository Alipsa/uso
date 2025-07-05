# parallel

The `parallel` task runs a set of nested tasks concurrently (in parallel). It can improve build performance by executing independent tasks simultaneously. Parallelism is limited by the `threadCount` attribute or the default thread pool size.

## Usage Example

```groovy
target('buildAll') {
  parallel(threadCount: 4, timeout: 600000) {
    exec(executable: 'compile.sh')
    junitlauncher(printSummary: true) {
      classpath {
        path(refid: 'testPath')
      }
      testclasses {
        fileset(dir: 'build/test-classes')
      }
    }
    copy(todir: 'build/resources') {
      fileset(dir: 'src/main/resources')
    }
  }
}
```

This runs the `compile.sh`, tests, and resource copy in parallel using up to 4 threads and a 10-minute timeout.

## Attributes

|     Attribute | Description                                                                        | Required             |
|--------------:|------------------------------------------------------------------------------------|----------------------|
| `threadCount` | Maximum number of threads to use (default: number of available processors)         | No                   |
|     `timeout` | Maximum time in milliseconds to wait for all threads to finish (default: infinite) | No                   |
| `failonerror` | Whether to stop all threads on first error (`true`) or continue (`false`)          | No (default: `true`) |

## Notes

- Exceptions in any nested task are collected; if `failonerror` is `true`, the first failure stops execution.
- Use `timeout` to prevent hanging builds.
- Suitable for independent tasks that have no shared mutable state.
- Logging from parallel tasks may interleave in the console output.

## Reference

- [Ant Manual: parallel Task](https://ant.apache.org/manual/Tasks/parallel.html)
