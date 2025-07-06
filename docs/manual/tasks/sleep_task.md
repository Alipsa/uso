# sleep

The `sleep` task pauses the build for a specified duration. It is useful for waiting on external resources or introducing delays in the build process.

## Usage Examples

```groovy
project.with {
  // Pause for 5 seconds
  target('pauseBuild') {
    sleep(seconds: 5)
  }

  // Pause for half a second
  target('shortPause') {
    sleep(milliseconds: 500)
  }

  // Pause for 2.5 seconds using both attributes
  target('mixedPause') {
    sleep(seconds: 2, milliseconds: 500)
  }
}

  // Sleep for one hour minus 59 minutes and 58 seconds (i.e. total 2 seconds)
  target('weirdPause') {
    sleep(hours: "1", minutes: "-59", seconds: "-58")
  }
```

## Attributes

|      Attribute | Description                     | Required                                      |
|---------------:|---------------------------------|-----------------------------------------------|
|        `hours` | Number of hours to sleep        | One of `hours`, `seconds`, or `milliseconds`  |
|      `seconds` | Number of seconds to sleep      | One of `hours`, `seconds`, or `milliseconds`  |
| `milliseconds` | Number of milliseconds to sleep | One of `hours`, `seconds`, or `milliseconds`  |

## Notes

- If both `seconds` and `milliseconds` are provided, the total sleep time is the sum of both.
- On some platforms, actual sleep time may be slightly longer due to system scheduling.
- Use sparingly to avoid unnecessarily delaying automated builds.

## Reference

- [Ant Manual: sleep Task](https://ant.apache.org/manual/Tasks/sleep.html)