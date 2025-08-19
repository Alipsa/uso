# sshsession

The `sshsession` task establishes an SSH connection with a remote machine running the SSH daemon, optionally defines local and/or remote tunnels, executes nested tasks, and then closes the connection. It relies on the JSch library, which must be provided via Ant’s optional tasks.

## Defining the Task

Since `sshsession` is part of Ant’s optional tasks, you need to include the JSch-enabled Ant jar and register the task:

```groovy
path(id: 'antOptional') {
  pathelement(location: 'lib/ant-jsch.jar')
}
taskdef(
  name: 'sshsession',
  classname: 'org.apache.tools.ant.taskdefs.optional.ssh.SSHSession',
  classpathref: 'antOptional'
)
```

## Usage Examples

```groovy
// Connect using password authentication, forward local port 2401 to remote CVS port, then run CVS update
target('updateCvsViaTunnel') {
  sshsession(
    host: 'somehost',
    username: 'dude',
    password: 'yo',
    localtunnels: '2401:localhost:2401',
    failonerror: true
  ) {
    sequential {
      cvs(
        command: "update ${cvsParms} ${module}",
        cvsRoot: cvsRoot,
        dest: localRoot,
        failonerror: true
      )
    }
  }
}

// Equivalent example using nested localtunnel element
target('updateCvsViaNestedTunnel') {
  sshsession(
    host: 'somehost',
    username: 'dude',
    password: 'yo'
  ) {
    localtunnel(lport: 2401, rhost: 'localhost', rport: 2401)
    sequential {
      cvs(
        command: "update ${cvsParms} ${module}",
        cvsRoot: cvsRoot,
        dest: localRoot,
        failonerror: true
      )
    }
  }
}

// Use remote port forwarding and then download via GET task
target('fetchViaRemoteTunnel') {
  sshsession(
    host: 'somehost',
    username: 'dude',
    keyfile: "${user.home}/.ssh/id_dsa",
    passphrase: 'secret',
    remotetunnels: '8080:intranet.server:80'
  ) {
    sequential {
      get(src: 'http://localhost:8080/somefile', dest: 'temp/somefile')
    }
  }
}
```

## Attributes

| Attribute       | Description                                                                                     | Required                        |
|-----------------|-------------------------------------------------------------------------------------------------|---------------------------------|
| `host`          | Remote host name or IP address                                                                  | Yes                             |
| `username`      | SSH user name                                                                                   | Yes                             |
| `password`      | Password for authentication (unless using key-based auth)                                       | Conditionally, if no `keyfile`  |
| `keyfile`       | Path to private key file for key-based authentication                                           | Conditionally, if no `password` |
| `passphrase`    | Passphrase for the private key (if encrypted)                                                   | No                              |
| `port`          | SSH port (default: `22`)                                                                        | No                              |
| `localtunnels`  | Comma-delimited list of `lport:rhost:rport` triplets for local port forwarding                  | No                              |
| `remotetunnels` | Comma-delimited list of `rport:lhost:lport` triplets for remote port forwarding                 | No                              |
| `trust`         | Trust unknown hosts without checking `knownhosts` (default: `false`)                            | No                              |
| `knownhosts`    | Path to `known_hosts` file for host key verification (default: `${user.home}/.ssh/known_hosts`) | No                              |
| `sshConfig`     | Path to OpenSSH–style config file for default user/key settings (since Ant 1.10.8)              | No                              |
| `timeout`       | Time in milliseconds to wait for connection (default: `0` = never timeout)                      | No                              |
| `failonerror`   | Abort build if any nested task fails (default: `true`)                                          | No                              |

## Nested Elements

| Element              | Description                                                                                     | Required |
|----------------------|-------------------------------------------------------------------------------------------------|----------|
| `<localtunnel>`      | Define local port forwarding: attributes `lport`, `rhost`, `rport`                              | No       |
| `<remotetunnel>`     | Define remote port forwarding: attributes `rport`, `lhost`, `lport`                             | No       |
| `<additionalConfig>` | Add arbitrary JSch session settings (`key`, `value`) (since Ant 1.10.10)                        | No       |
| `<sequential>`       | Container for tasks to run once the session/tunnels are established (must include at least one) | Yes      |

## Notes

- Ensure the JSch-enabled Ant jar (`ant-jsch.jar` or similar) is on the classpath.
- Hardcoding credentials can be insecure; consider using `<input>` or external property files for passwords.
- Combined use of `localtunnels` and nested `<localtunnel>` elements is supported; same for remote tunnels.
- The `<sequential>` element is required to group nested tasks executed over the SSH connection.

## Reference

- [Ant Manual: sshsession Task](https://ant.apache.org/manual/Tasks/sshsession.html)
