# link

The `link` task creates a Java runtime image using the JDK `jlink` tool. It assembles modules into a customized runtime image that contains only the required modules, reducing size and improving security.

## Usage Example

```groovy
target('createImage') {
  link(destDir: 'build/image', modulepath: 'mods', modules: 'com.example.app')
}
```

## Attributes

| Attribute           | Description                                                  | Required                         |
|---------------------|--------------------------------------------------------------|----------------------------------|
| `destDir`           | Target directory where the runtime image will be created     | Yes                              |
| `modulepath`        | Path containing module definitions (typically `.jmod` files) | Yes (or use nested `modulepath`) |
| `modules`           | Comma-separated list of modules to include in the image      | Yes (or use nested `module`)     |
| `observableModules` | Modules visible to the linking process                       | No                               |
| `launchers`         | Launcher commands (e.g. `name=module[/mainclass]`)           | No                               |
| `locales`           | Locales to include (e.g. `en`, `fr`, or wildcard like `fr*`) | No                               |
| `excludeResources`  | Files or patterns of resources to exclude                    | No                               |
| `excludeFiles`      | Files or patterns of files to exclude                        | No                               |

## Nested Elements

- `modulepath` – Define the module path via a nested path structure.
- `module` – Specify individual modules.
- `launcher` – Define launcher executables for the runtime image.
- `locale` – Locales to include in the image.
- `compress` – Set compression level.
- `releaseInfo` – Define additional metadata for the runtime image.

## Advanced Example

```groovy
target('advancedImage') {
  link(destDir: 'build/runtime', modules: 'com.myapp') {
    modulepath {
      pathelement(location: 'mods')
    }
    launchers {
      launcher(name: 'myapp', module: 'com.myapp', mainclass: 'com.myapp.Main')
    }
    locale(name: 'en', name: 'sv')
    compress(level: '2')
  }
}
```

## Notes

- Equivalent to the `jlink` tool in the JDK.
- Useful for building custom, minimized runtime images with only required modules.
- Requires Java 9 or later and Ant 1.10.6+.

## Reference

- [Ant Manual: link Task](https://ant.apache.org/manual/Tasks/link.html)
