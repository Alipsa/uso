# rpm

The `rpm` task creates a Red Hat RPM package by invoking the system's `rpmbuild` tool. It allows you to define package metadata and include files from your build output.

## Usage Example

```groovy
target('makeRpm') {
  rpm(
    name: 'myapp',
    group: 'Applications/Productivity',
    version: '1.0.0',
    release: '1',
    arch: 'noarch',
    os: 'linux',
    summary: 'My Application',
    description: 'A sample application packaged as an RPM',
    packager: 'Per Nyfelt <per@alipsa.se>',
    vendor: 'Alipsa HB',
    url: 'https://example.com',
    buildroot: 'build/rpmroot',
    specfile: 'src/rpm/myapp.spec',
    outputdir: 'build/rpm'
  ) {
    fileset(dir: 'dist') {
      include(name: '*')
    }
  }
}
```

## Attributes

|     Attribute | Description                                                                       | Required                                     |
|--------------:|-----------------------------------------------------------------------------------|----------------------------------------------|
|        `name` | Package name                                                                      | Yes                                          |
|       `group` | Package group (e.g., `Applications/Productivity`)                                 | Yes                                          |
|     `version` | Version string (e.g., `1.0.0`)                                                    | Yes                                          |
|     `release` | Release number (e.g., `1`)                                                        | Yes                                          |
|        `arch` | Architecture (`noarch`, `x86_64`, etc.)                                           | Yes                                          |
|          `os` | Operating system (`linux` by default)                                             | No                                           |
|     `summary` | Brief summary of the package                                                      | Yes                                          |
| `description` | Full description of the package                                                   | No                                           |
|    `packager` | Packager name and email                                                           | No                                           |
|      `vendor` | Vendor name                                                                       | No                                           |
|         `url` | Project or download URL                                                           | No                                           |
|   `buildroot` | Build root directory containing files for packaging                               | No (defaults to `<project basedir>/rpmroot`) |
|    `specfile` | Path to RPM spec file                                                             | No (task generates a spec if omitted)        |
|   `outputdir` | Directory where generated RPMs will be placed                                     | No (defaults to `.`)                         |
| `failonerror` | Whether to fail the build if RPM creation fails (`true`/`false`, default: `true`) | No                                           |

## Nested Elements

- `<fileset>` — FileSet of files to include in the RPM buildroot.
- `<directory>` — Create an empty directory entry in the RPM.
- `<link>` — Create a symlink entry.
- `<changelog>` — Add changelog entries.
- `<rpmtag>` — Define custom RPM tags for advanced metadata.

## Notes

- Requires `rpmbuild` and related RPM tooling installed on the system.
- The task will generate or use the provided spec file to build the RPM.
- Make sure the `buildroot` directory contains only the files you want packaged.
- For custom spec file generation, omit `specfile` and provide nested metadata via `<rpmtag>`.

## Reference

- [Ant Manual: rpm Task](https://ant.apache.org/manual/Tasks/rpm.html)
