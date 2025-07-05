# rexec

The `rexec` task executes a command on a remote host via the Rexec (remote execution) protocol. It sends commands to a remote daemon running the Rexx protocol, capturing output and handling authentication.

## Usage Examples

### Execute a command on a remote host

```groovy
target('runRemote') {
  rexec(
    host: 'remote.example.com',
    username: 'user',
    password: 'secret',
    command: 'ls -l /var/log'
  )
}
```

### Capture output into a property

```groovy
target('getRemoteDate') {
  rexec(
    host: '192.168.1.100',
    username: 'admin',
    password: 'pass',
    command: 'date',
    outputproperty: 'remote.date'
  )
  echo(message: "Remote date: ${project.properties['remote.date']}")
}
```

### Specify custom port and timeout

```groovy
target('customRexec') {
  rexec(
    host: 'remote.example.com',
    port: 512,
    username: 'deploy',
    password: 'deploy123',
    command: 'uptime',
    timeout: 5000
  )
}
```

## Attributes

|        Attribute | Description                                                                                        | Required                          |
|-----------------:|----------------------------------------------------------------------------------------------------|-----------------------------------|
|           `host` | Hostname or IP address of the remote machine                                                       | Yes                               |
|           `port` | TCP port to connect to (default: 512)                                                              | No                                |
|       `username` | Username for authentication                                                                        | Yes                               |
|       `password` | Password for authentication                                                                        | Yes                               |
|        `command` | Command string to execute remotely                                                                 | Yes                               |
| `outputproperty` | Name of the property to store the command output                                                   | No                                |
|       `loglevel` | Logging level for output: `verbose`, `info`, `warn`, `error` (default: `info`)                     | No                                |
|          `trust` | Whether to trust the remote host's key without verification (`true`/`false`)                       | No (for SSH-like implementations) |
|        `timeout` | Timeout in milliseconds for the remote command execution                                           | No                                |
|    `failonerror` | Whether to fail the build if the remote command returns a non-zero exit status (`true` by default) | No                                |

## Nested Elements

- `<arg>` — Add command arguments one per nested `<arg value="..."/>`.
- `<redirector>` — Configure input/output redirection (e.g., `input`, `output`, `error` files).
- `<env>` — Set environment variables for the remote execution.

## Notes

- The Rexec protocol is insecure (transmits credentials in plaintext). Consider using `<sshexec>` for encrypted communication.
- Ensure the remote host is running the Rexec daemon and is configured to accept connections.
- Use `timeout` to prevent hanging if the remote server is unresponsive.

## Reference

- [Ant Manual: rexec Task](https://ant.apache.org/manual/Tasks/rexec.html)
