# setpermissions

The `setpermissions` task changes file and directory permissions in a platform-independent way using Java NIO. It offers a subset of Unix `chmod` and Windows `attrib` behaviors, with options for POSIX and non-POSIX filesystems.

## Usage Examples

```groovy
project.with {
  // Set executable permissions on shell scripts using octal mode
  target('fixPermsMode') {
    setpermissions(mode: '755') {
      fileset(dir: 'bin', includes: '**/*.sh')
    }
  }

  // Set specific POSIX permissions via names, with fallback on non-POSIX
  target('readOnlyConfig') {
    setpermissions(
      permissions: 'OWNER_READ,GROUP_READ,OTHERS_READ',
      nonPosixMode: 'tryDosOrPass'
    ) {
      fileset(dir: 'config', includes: '**/*.properties')
    }
  }

  // Adjust a single file's permissions
  target('singleFilePerms') {
    setpermissions(mode: '755', failonerror: false) {
      file(file: "${dist}/start.sh")
    }
  }
}
```

## Attributes

|      Attribute | Description                                                                                                                                           | Required             |
|---------------:|-------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------|
|  `permissions` | Comma-separated list of POSIX permission names (e.g., `OWNER_READ,OWNER_WRITE,OWNER_EXECUTE,OTHERS_READ`)                                             | No                   |
|         `mode` | Traditional Unix three-digit octal permission (e.g., `755`). Overrides `permissions` if both specified                                                | No                   |
| `nonPosixMode` | Behavior when POSIX permissions aren't supported: `fail` (error), `pass` (log only), `tryDosOrFail`, `tryDosOrPass` (attempt DOS readonly on Windows) | No (default: `fail`) |
|  `failonerror` | Whether to fail the build if setting permissions fails (default: `true`)                                                                              | No                   |

## Nested Elements

- **Resource collections**: Any Ant resource collection (e.g., `<fileset>`, `<filelist>`, `<path>`, `<file>`) to select files and directories.

## Notes

- If neither `mode` nor `permissions` is provided, existing permissions will be removed from all matched resources.
- Use `nonPosixMode` to handle non-POSIX filesystems (like Windows) gracefully.
- The task only affects resources that support changing permissions; unsupported resources (e.g., URLs) are ignored.

## Reference

- [Ant Manual: setpermissions Task](https://ant.apache.org/manual/Tasks/setpermissions.html)
