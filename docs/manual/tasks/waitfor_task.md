# waitfor

The `waitfor` task pauses the build until a specified condition becomes true or a timeout is reached. It supports a variety of nested condition elements (e.g., file, socket, HTTP) and configurable polling intervals, properties, and failure behaviors. 
This task is often used in conjunction with the parallel task to synchronize a set of processes.

The conditions to wait for are defined in nested elements, if multiple conditions are specified, then the task will wait until all conditions are true.

If both maxwait and maxwaitunit are not specified, default maxwait is 3 minutes (180000 milliseconds).

If the timeoutproperty attribute has been set, a property of that name will be created if the condition didn't come true within the specified time.

## Usage Examples

```groovy
// Wait up to 30 seconds for a file to exist
target('waitForConfig') {
  waitfor(
    maxwait: 30,
    maxwaitunit: 'second'
  ) {
    available(file: 'config/app.properties')
  }
}

// Wait for a TCP port to become available, checking every 5 seconds
target('waitForService') {
  waitfor(
    maxwait: 2,
    maxwaitunit: 'minute',
    checkevery: 5,
    checkeveryunit: 'second',
    verbose: true
  ) {
    socket(server: 'localhost', port: 8080)
  }
}

// Wait for an HTTP URL to return a 200 response
target('waitForHealth') {
  waitfor(
    maxwait: 1,
    maxwaitunit: 'minute',
    property: 'service.up',
    abortonfailure: false
  ) {
    http(url: 'http://localhost:8080/health', statuscode: 200)
  }
  if (project.properties['service.up']) {
    echo('Service is healthy!')
  } else {
    echo('Service did not start in time.')
  }
}
```

## Attributes

| Attribute        | Description                                                                                         | Required                    |
|------------------|-----------------------------------------------------------------------------------------------------|-----------------------------|
| `maxwait`        | Amount of time to wait before giving up (default: `0`, meaning no wait).                            | Yes                         |
| `maxwaitunit`    | Time unit for `maxwait`: `millisecond`, `second`, `minute`, `hour`, `day`, `week`, `month`, `year`. | No (default: `millisecond`) |
| `checkevery`     | Polling interval between condition checks (default: `0`, meaning check only once).                  | No                          |
| `checkeveryunit` | Time unit for `checkevery` (same options as `maxwaitunit`).                                         | No                          |
| `verbose`        | Log each condition check (`true`/`false`, default: `false`).                                        | No                          |
| `property`       | Name of a property to set to `true` when the condition is met (no failure on timeout).              | No                          |
| `abortonfailure` | Fail the build if the timeout is reached (`true`/`false`, default: `true`).                         | No                          |

## Nested Elements

The `waitfor` task uses nested condition elements. Common ones include:

- `<available>` — Wait for a file or resource (`file`, `classpath`, `resource` attributes).
- `<socket>` — Wait for a TCP socket (`server`, `port`).
- `<http>` — Wait for an HTTP URL (`url`, optional `statuscode`).
- `<resourceexists>` — Wait for a resource on the classpath (`name`, `classpath`).
- `<uptodate>` — Wait until a target is up-to-date relative to sources.
- `<and>`, `<or>`, `<not>` — Combine other condition elements.
- Any Ant `<condition>` element supported by Ant core.

## Notes

- If `property` is set, build continues after timeout without failing; property indicates success.
- Default time units are in milliseconds; specify units for human-friendly durations.
- Use `verbose: true` to debug polling loops.
- The task is part of Ant core as of version 1.6.

## Reference

- [Ant Manual: waitfor Task](https://ant.apache.org/manual/Tasks/waitfor.html)
