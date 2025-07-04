# javadoc

The `javadoc` task generates API documentation for Java source files by invoking the standard JDK `javadoc` tool. This task always forks a new process, because the tool calls `System.exit()`.

## Usage Example

```groovy
target('generateDocs') {
  javadoc(
    destdir: 'build/docs',
    sourcepath: 'src/main/java',
    packagenames: 'com.example.*',
    author: true,
    version: true
  )
}
```

This generates Javadoc HTML files for all classes in the `com.example.*` package and writes them to the `build/docs` directory.

## Attributes

| Attribute      | Description                                                      | Required                                               |
|----------------|------------------------------------------------------------------|--------------------------------------------------------|
| `destdir`      | Directory where the generated Javadoc will be placed             | Yes                                                    |
| `sourcepath`   | Path(s) to the Java source files                                 | Yes (unless nested `sourcefiles` or `fileset` is used) |
| `packagenames` | Comma-separated list of packages to include (e.g., `com.myco.*`) | Yes                                                    |
| `author`       | Whether to include `@author` in documentation                    | No                                                     |
| `version`      | Whether to include `@version` in documentation                   | No                                                     |
| `classpathref` | Reference to a classpath path used during Javadoc generation     | No                                                     |

## Notes

- Always forks: the Javadoc tool internally calls `System.exit()`.
- You can add nested elements like `classpath`, `fileset`, `group`, `link`, `doclet`, etc.
- Use `link(href: '...')` to add hyperlinks to external Javadoc (e.g., Java SE API docs).

## Example with Classpath and Link

```groovy
target('docWithLinks') {
  javadoc(destdir: 'build/api', sourcepath: 'src/main/java', packagenames: 'com.example.*') {
    classpath {
      path(refid: 'compilePath')
    }
    link(href: 'https://docs.oracle.com/en/java/javase/17/docs/api/')
  }
}
```

## Reference

- [Ant Manual: javadoc Task](https://ant.apache.org/manual/Tasks/javadoc.html)
