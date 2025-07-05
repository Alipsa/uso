# jmod

The `jmod` task creates a `.jmod` file, which is a packaging format introduced in JDK 9 for modular Java applications. It supports inclusion of compiled classes, native libraries, man pages, configuration files, and more. This task requires Java 9 or later and Ant 1.10.6+.

## Usage Example

```groovy
target('createJmod') {
  jmod(
    destFile: 'build/myApp.jmod',
    classpath: 'build/my-app.jar',
    modulepath: 'libs',
    version: '1.0.0',
    mainclass: 'com.mycompany.Main'
  )
}
```

## Attributes

| Attribute            | Description                                                                      | Required                  |
|----------------------|----------------------------------------------------------------------------------|---------------------------|
| `destFile`           | Path to the `.jmod` file to be created                                           | Yes                       |
| `classpath`          | Path(s) to classes or JARs to include in the module                              | Yes (or nested classpath) |
| `modulepath`         | Path(s) to required modules                                                      | No                        |
| `version`            | Module version string                                                            | No                        |
| `mainclass`          | Entry point class                                                                | No                        |
| `platform`           | Target platform for the module                                                   | No                        |
| `resolveByDefault`   | Whether the module is resolved by default                                        | No                        |
| `hashModulesPattern` | Pattern of modules whose dependencies should be hashed                           | No                        |
| `moduleWarnings`     | Comma-separated list of module warnings to include (e.g. deprecated, incubating) | No                        |

## Nested Elements

The following path-based elements can be defined using attributes or nested `<path>` structures:

- `classpath`, `classpathref`
- `modulepath`, `modulepathref`
- `commandpath`, `commandpathref`
- `headerpath`, `headerpathref`
- `configpath`, `configpathref`
- `legalpath`, `legalpathref`
- `nativelibpath`, `nativelibpathref`
- `manpath`, `manpathref`

You can also use `<version>` as a nested element for structured version declarations.

## Notes

- The `jmod` task wraps the JDK `jmod` tool.
- Useful for packaging Java modules with native resources, headers, or configuration files.
- Automatically forks to avoid JVM issues.
- Only available with Java 9+ and Ant 1.10.6 or later.

## Reference

- [Ant Manual: jmod Task](https://ant.apache.org/manual/Tasks/jmod.html)
