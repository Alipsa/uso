# sound

The `sound` task registers hooks that play sound files when the build finishes, choosing between success or failure sounds. You specify nested `<success>` and `<fail>` elements with file or directory sources, optional loops, and durations.

## Usage Examples

```groovy
target('notifyBuild') {
  sound {
    success(source: "${user.home}/sounds/bell.wav")
    fail(source: "${user.home}/sounds/ohno.wav", loops: 2)
  }
}

target('randomNotify') {
  sound {
    success(source: "//intranet/sounds/success")
    fail(source: "//intranet/sounds/failure")
  }
}
```

## Nested Elements

| Element     | Description                          | Required |
|-------------|--------------------------------------|----------|
| `<success>` | Sound to play if the build succeeds  | Yes      |
| `<fail>`    | Sound to play if the build fails     | Yes      |

### Attributes on `<success>` and `<fail>`

| Attribute  | Description                                                                                         | Required |
|------------|-----------------------------------------------------------------------------------------------------|----------|
| `source`   | Path to a sound file or directory. If a directory, a random file within is chosen.                  | Yes      |
| `loops`    | Number of additional times to play the sound (default: `0`).                                         | No       |
| `duration` | Duration in milliseconds to play the sound (default: until sound ends).                              | No       |

## Notes

- Place the `sound` task in a target that always executes or at the root level so the hooks are registered before the build ends.
- Requires the Java Sound API (`javax.sound`). On older Java versions (pre-1.3), the Java Media Framework may be needed on the classpath.
- When sourcing a directory, ensure it contains only valid sound files, as selection is not filtered by extension.

## Reference

- [Ant Manual: sound Task](https://ant.apache.org/manual/Tasks/sound.html)
