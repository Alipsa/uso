# nice

The `nice` task sets the scheduling priority (nice value) of the current process when launching a sub-process. It only works on Unix-like systems that support the `nice` command.

## Usage Example

```groovy
target('runWithLowerPriority') {
  nice(nice: 10) {
    exec(executable: 'make', args: 'all')
  }
}
```

This runs `make all` with a niceness of 10, lowering its scheduling priority.

## Attributes

| Attribute | Description                                     | Required |
|----------:|-------------------------------------------------|----------|
| `nice`    | Numeric niceness value (`-20` to `19`, default: `0`) | Yes      |
| `executable` | The command to run at the specified niceness        | No*     |
| `arguments`  | Arguments for the executable (`args` attribute or nested `<arg>`) | No* |

:star: Note: Typically used with `<exec>` nested inside the `nice` closure rather than its own attributes.

## Nested Elements

- `exec` — Execute a command within the nice wrapper.
- `java` — Launch a Java application with adjusted niceness.
- Any system command invocation supported by Ant.

## Notes

- Requires the `nice` utility on the system.
- Running with a negative nice value (higher priority) may require superuser privileges.
- If no nested task is provided, `nice` has no effect on the current JVM.

## Reference

- [Ant Manual: nice Task](https://ant.apache.org/manual/Tasks/nice.html)
