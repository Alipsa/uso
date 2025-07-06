# sshexec

The `sshexec` task executes commands on a remote host over SSH using the JSch library. It supports password and key-based authentication, command resource files, input/output redirection, and connection tuning options.

## Usage Examples

```groovy
// Run a single command with password authentication
target('touchRemote') {
  sshexec(
    host: 'remote.example.com',
    username: 'deploy',
    password: 'secret',
    command: 'touch /tmp/deployed'
  )
}

// Run multiple commands from a resource file using key-based authentication
target('runRemoteBatch') {
  sshexec(
    host: 'remote.example.com',
    username: 'deploy',
    keyfile: "${System.getProperty('user.home')}/.ssh/id_rsa",
    passphrase: 'keyPassphrase',
    commandResource: 'scripts/commands.txt',
    trust: true,
    port: 2222
  )
}

// Capture output into a property
target('getUptime') {
  sshexec(
    host: 'remote.example.com',
    username: 'deploy',
    password: 'secret',
    command: 'uptime',
    outputproperty: 'remote.uptime'
  )
  echo(message: "Remote uptime: ${project.properties['remote.uptime']}")
}
```

## Attributes

| Attribute                    | Description                                                                             | Required                             |
|------------------------------|-----------------------------------------------------------------------------------------|--------------------------------------|
| `host`                       | Remote host name or IP address                                                          | Yes                                  |
| `username`                   | SSH user name                                                                           | Yes                                  |
| `password`                   | Password for authentication (unless using key-based auth)                               | Conditionally, if no `keyfile`       |
| `keyfile`                    | Path to private key file for key-based authentication                                   | Conditionally, if no `password`      |
| `passphrase`                 | Passphrase for the private key (required if key is encrypted)                           | No                                   |
| `command`                    | Command string to execute remotely                                                      | Yes, unless `commandResource` is set |
| `commandResource`            | Resource file containing commands to run (one per line)                                 | Yes, if `command` is omitted         |
| `port`                       | SSH port (default: `22`)                                                                | No                                   |
| `trust`                      | Trust all hosts without known_hosts check (`true`/`false`, default: `false`)            | No                                   |
| `knownhosts`                 | Path to known_hosts file for host key verification (SSH2 format)                        | No                                   |
| `failonerror`                | Whether to fail build on non-zero exit code (default: `true`)                           | No                                   |
| `output`                     | File path to redirect standard output                                                   | No                                   |
| `outputproperty`             | Property name to store the standard output                                              | No                                   |
| `errorOutput`                | File path to redirect standard error                                                    | No                                   |
| `errorproperty`              | Property name to store the standard error                                               | No                                   |
| `append`                     | Append to output file instead of overwriting (`true`/`false`, default: `false`)         | No                                   |
| `errAppend`                  | Append to errorOutput file instead of overwriting (`true`/`false`, default: `false`)    | No                                   |
| `timeout`                    | Timeout in milliseconds for command execution (default: no timeout)                     | No                                   |
| `suppressSystemOut`          | Suppress sending the remote process’s stdout to Ant’s log (default: `false`)            | No                                   |
| `suppressSystemErr`          | Suppress sending the remote process’s stderr to Ant’s log (default: `false`)            | No                                   |
| `input`                      | File path to provide as standard input to the remote command                            | No                                   |
| `inputproperty`              | Property name whose value is used as standard input                                     | No                                   |
| `inputstring`                | String value used as standard input                                                     | No                                   |
| `verbose`                    | Enable verbose SSH messaging (like `ssh -v`, default: `false`)                          | No                                   |
| `usePty`                     | Allocate a pseudo-terminal for the SSH session (default: `false`)                       | No                                   |
| `useSystemIn`                | Pass Ant’s System.in to the remote command (default: `false`)                           | No                                   |
| `serverAliveIntervalSeconds` | Seconds of inactivity before sending SSH keepalive (default: `0`)                       | No                                   |
| `serverAliveCountMax`        | Maximum unanswered keepalive messages before termination (default: `3`)                 | No                                   |
| `sshConfig`                  | Path to OpenSSH-style SSH config file to read host/user/key defaults (since Ant 1.10.8) | No                                   |
| `classpath`                  | Classpath for JSch library if not on Ant’s classpath                                    | No                                   |

## Nested Elements

- `<additionalConfig>` — Add arbitrary JSch session settings (`key`, `value` attributes). Since Ant 1.10.10.

## Notes

- Requires JSch on the classpath (not bundled with Ant); include `ant-jsch.jar` or similar.
- `trust="true"` bypasses host key verification—use only in trusted networks.
- Hardcoding passwords can be insecure; prefer Ant `<input>` or external property files.
- Combining `commandResource` with input properties allows complex batch operations.

## Example groovy AntBuilder script
```groovy
@GrabConfig(systemClassLoader=true)
@Grab('org.apache.ant:ant-jsch:1.10.15')
@Grab('com.github.mwiede:jsch:2.27.2')
import groovy.ant.AntBuilder

def project = new AntBuilder()

project.with {
  taskdef(name: 'sshexec', classname: 'org.apache.tools.ant.taskdefs.optional.ssh.SSHExec')
  sshexec(
    host: 'myserver',
    username: 'myUserName',
    keyfile: "${System.getProperty('user.home')}/.ssh/id_rsa",
    port: "2222",
    trust:      true,
    command: 'uptime',
    outputproperty: 'remote.uptime'
  )
  echo(message: "Remote uptime: ${project.properties['remote.uptime']}")
}
```
## Reference

- [Ant Manual: sshexec Task](https://ant.apache.org/manual/Tasks/sshexec.html)
