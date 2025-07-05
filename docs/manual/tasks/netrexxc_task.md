# netrexxc

The `netrexxc` task compiles [NetRexx](https://netrexx.org/) source files into JVM bytecode using the NetRexx compiler. It supports compiling individual files or entire directories and integrates with AntBuilder for Groovy-based builds.

## Usage Examples

### Compile a single NetRexx source file

```groovy
target('compileNetRexx') {
  netrexxc(
    src: 'src/netrexx/Hello.nrx',
    destdir: 'build/classes',
    classpathref: 'compilePath',
    debug: true
  )
}
```

### Compile all NetRexx files in a directory

```groovy
target('compileAllNetRexx') {
  netrexxc(
    srcdir: 'src/netrexx',
    destdir: 'build/classes',
    classpath: 'lib/netrexx.jar:lib/other-deps/*',
    optimize: true
  )
}
```

## Attributes

| Attribute     | Description                                                                                   | Required                                |
|--------------:|-----------------------------------------------------------------------------------------------|-----------------------------------------|
| `src`         | Path to a single NetRexx source file (e.g., `.nrx`)                                          | One of `src` or `srcdir`                |
| `srcdir`      | Directory containing NetRexx source files                                                    | One of `src` or `srcdir`                |
| `destdir`     | Directory where compiled class files will be placed                                           | Yes                                     |
| `classpath`   | Classpath for finding required libraries (colon- or semicolon-separated)                     | No (if needed for external .jar support)|
| `classpathref`| Reference to an Ant `<path>` element for classpath                                            | No                                      |
| `debug`       | Include debugging information (`true`/`false`, default: `false`)                              | No                                      |
| `optimize`    | Enable compiler optimizations (`true`/`false`, default: `false`)                              | No                                      |
| `failonerror` | Fail the build if compilation errors occur (`true`/`false`, default: `true`)                 | No                                      |
| `verbose`     | Show verbose compiler output (`true`/`false`, default: `false`)                               | No                                      |

## Nested Elements

- `fileset` — Specify a set of NetRexx source files to compile.
- `classpath` — Define classpath elements via nested `<path>` if more control is needed.
- `compilerarg` — Pass custom command-line arguments to the NetRexx compiler.

## Notes

- Requires NetRexx compiler JAR on the classpath (e.g., `netrexx.jar`).
- Ensure `destdir` exists or use a preceding `mkdir` task.
- Combining `srcdir` with nested `fileset` allows fine-grained inclusion/exclusion of files.
- The task forks a separate process for compilation.

## Reference

- [Ant Manual: netrexxc Task](https://ant.apache.org/manual/Tasks/netrexxc.html)
