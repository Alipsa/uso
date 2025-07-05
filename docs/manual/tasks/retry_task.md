# retry

The `retry` task executes a single nested task repeatedly until it succeeds (no failure) or the specified retry count is exceeded. If all attempts fail, a `BuildException` is thrown. Introduced in Ant 1.7.1.

## Usage Example

```groovy
target('tryCommand') {
  // Attempt 'make build' up to 3 times with 1 second between attempts
  retry(retrycount: 3, retrydelay: 1000) {
    exec(executable: 'make', args: 'build')
  }
}
```

## Attributes

|    Attribute | Description                                                          | Required |
|-------------:|----------------------------------------------------------------------|----------|
| `retrycount` | Number of times to attempt the nested task                           | Yes      |
| `retrydelay` | Milliseconds to wait between retry attempts (Ant 1.8.3+, default: 0) | No       |

## Nested Elements

- **Task**  
  A single Ant task to execute (e.g., `<exec>`, `<javac>`, etc.). Only one nested task is supported.

## Notes

- If `retrydelay` is specified, the task sleeps for that duration between failed attempts.
- The first execution counts as the first attempt; `retrycount="3"` allows up to three tries.
- Useful for operations that may fail transiently (e.g., network calls, external processes).

## Reference

- [Ant Manual: retry Task](https://ant.apache.org/manual/Tasks/retry.html)
