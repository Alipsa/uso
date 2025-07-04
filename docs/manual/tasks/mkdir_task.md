# mkdir

The `mkdir` task creates directories. It creates all necessary parent directories as needed and does nothing if the directory already exists.

## Usage Example

```groovy
target('prepareBuild') {
  mkdir(dir: 'build/output')
}
```

You can also specify multiple directories:

```groovy
target('setupDirs') {
  mkdir(dir: 'build/output')
  mkdir(dir: 'build/temp')
}
```

## Attributes

|      Attribute | Description                                                            | Required              |
|---------------:|------------------------------------------------------------------------|-----------------------|
|          `dir` | Path of the directory to create                                        | Yes                   |
|      `basedir` | Base directory to resolve `dir` against (defaults to project baseDir)  | No                    |
|  `failonerror` | Whether to fail the build if directory creation fails (`true`/`false`) | No (default: `true`)  |
| `erroronexist` | Whether to treat existing directory as an error (`true`/`false`)       | No (default: `false`) |

## Notes

- Creates intermediate directories if they do not exist.
- Does not modify permissions of existing directories.
- When `failonerror` is `false`, any errors during creation are ignored.

## Reference

- [Ant Manual: mkdir Task](https://ant.apache.org/manual/Tasks/mkdir.html)
