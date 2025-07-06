# scp

The `scp` task securely copies files to or from remote machines over SSH using the JSch library. It supports both single-file and resource-collection transfers with key-based or password authentication.

## Usage Examples

### Upload local files to a remote directory

```groovy
target('uploadDist') {
  scp(
    todir: 'user@remote.example.com:/var/www/html',
    keyfile: "${System.getProperty('user.home')}/.ssh/id_rsa",
    passphrase: 'secret',
    trust: true
  ) {
    fileset(dir: 'dist', includes: '**/*.*')
  }
}
```

### Download a single remote file to a local directory

```groovy
target('downloadConfig') {
  scp(
    file: 'user@remote.example.com:/etc/myapp/config.yml',
    todir: 'config',
    port: 2222,
    trust: true
  )
}
```

## Attributes

| Attribute                    | Description                                                                            | Required                                         |
|------------------------------|----------------------------------------------------------------------------------------|--------------------------------------------------|
| `file`                       | Single local or remote file to copy (`user@host:/path` or local path)                  | One of `file` or nested resource                 |
| `localFile`                  | Local file path when `file` ambiguity arises                                           | No                                               |
| `remoteFile`                 | Remote file path when `file` ambiguity arises                                          | No                                               |
| `todir`                      | Destination directory (local or in `user@host:/path` form)                             | Yes, unless nested resource                      |
| `localTodir`                 | Local-only destination directory                                                       | No                                               |
| `localTofile`                | Local filename for downloaded file                                                     | No                                               |
| `remoteTodir`                | Remote-only destination directory                                                      | No                                               |
| `remoteTofile`               | Remote filename when uploading a single file                                           | No                                               |
| `port`                       | SSH port (default: `22`)                                                               | No                                               |
| `trust`                      | Trust unknown hosts without known_hosts check (`true`/`false`, default `false`)        | No                                               |
| `knownhosts`                 | Path to `known_hosts` file for host key verification (default: `~/.ssh/known_hosts`)   | No                                               |
| `failonerror`                | Halt the build if the transfer fails (`true`/`false`, default `true`)                  | No                                               |
| `password`                   | Password for authentication (not required if using key-based auth)                     | Yes, if no `keyfile` specified and not in `file` |
| `keyfile`                    | Path to private key file for key-based authentication                                  | Yes, if no password                              |
| `passphrase`                 | Passphrase for the private key file (default: empty)                                   | No                                               |
| `sshConfig`                  | Path to OpenSSH–style config file (e.g., `${user.home}/.ssh/config`)                   | No                                               |
| `verbose`                    | Show transfer progress (dots/stars) (`true`/`false`, default `false`)                  | No                                               |
| `sftp`                       | Use the SFTP protocol instead of SCP1 (`true`/`false`, default `false`)                | No                                               |
| `preserveLastModified`       | Preserve last modified timestamp on downloaded files (`true`/`false`, default `false`) | No                                               |
| `filemode`                   | Octal permission mode for uploaded files (e.g., `644`, default `644`)                  | No                                               |
| `dirmode`                    | Octal permission mode for uploaded directories (e.g., `755`, default `755`)            | No                                               |
| `serverAliveIntervalSeconds` | Seconds of inactivity before sending a keep-alive message (`0` = none, default `0`)    | No                                               |
| `serverAliveCountMax`        | Number of keep-alive messages without response before terminating (`3` default)        | No                                               |
| `compressed`                 | Enable SSH channel compression (`true`/`false`, default `false`)                       | No                                               |

## Nested Elements

- `<fileset>` — Select multiple local files for upload (only for uploading to remote).
- Other Ant resource collections (e.g., `<path>`, `<zipfileset>`) can also be used for upload.
- `<additionalConfig>` — Add arbitrary JSch session settings (with `key` and `value` attributes) since Ant 1.10.10.

## Notes

- Requires the JSch library on the classpath (not bundled with Ant).
- `trust="true"` bypasses host key verification—use cautiously.
- Hardcoding credentials can be insecure; consider using `<input>` or secured property files.
- For advanced options like preserving permissions on download, consider using `<exec executable="scp">`.

## Reference

- [Ant Manual: scp Task](https://ant.apache.org/manual/Tasks/scp.html)
