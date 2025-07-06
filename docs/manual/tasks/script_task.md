# script

The `script` task executes code written in a BSF or JSR-223 supported scripting language within the build. It can run inline scripts or load scripts from files, with optional classpath configuration and project variable injection.

## Usage Examples

### Inline JavaScript with project beans

```groovy
target('runJS') {
  script(language: 'javascript', setbeans: true) {
    // Access Ant project properties via the project bean
    project.log("Hello from JavaScript, project version is " + project.getProperty("basedir"));
  }
}
```

### Execute script from file with custom classpath

```groovy
target('runScriptFile') {
  path(id: 'scriptPath') {
    pathelement(location: 'lib/nashorn.jar')
  }
  script(
    language: 'javascript',
    src: 'scripts/hello.js',
    classpathref: 'scriptPath',
    encoding: 'UTF-8'
  )
}
```

## Attributes

|      Attribute | Description                                                                                                     | Required |
|---------------:|-----------------------------------------------------------------------------------------------------------------|----------|
|     `language` | Scripting language name (e.g., `groovy`, `javascript`, `jython`) supported by BSF or JSR-223                    | Yes      |
|      `manager` | Engine manager: `auto`, `bsf`, or `javax` (default: `auto`)                                                     | No       |
|          `src` | File path of the script to execute (inline if omitted)                                                          | No       |
|     `encoding` | Character encoding for reading `src` file (default: platform encoding)                                          | No       |
|     `setbeans` | Whether to expose Ant properties, references, and targets as script variables (`true`/`false`, default: `true`) | No       |
|    `classpath` | Classpath for locating the script engine or resources                                                           | No       |
| `classpathref` | Reference to an existing Ant `<path>` element for classpath                                                     | No       |
|      `basedir` | Base directory for resolving relative script file paths                                                         | No       |
|       `tmpdir` | Directory for temporary script files generated at runtime                                                       | No       |

## Nested Elements

- `<classpath>` — Define classpath entries for the script engine (since Ant 1.7).
- `<importpath>` — Specify additional paths for JSR-223 imports (since Ant 1.10.6).

## Notes

- The task requires BSF (Java 6 and earlier) or JSR-223 libraries (Java 6+ with JDK built-in) on the classpath.
- Use `manager="bsf"` to force BSF, or `manager="javax"` to use JSR-223.
- Inline scripts run in the context of the current project; you can access the `project` and `self` task instance.
- For complex scripting, prefer external script files via `src` to keep build files readable.

## Reference

- [Ant Manual: script Task](https://ant.apache.org/manual/Tasks/script.html)
