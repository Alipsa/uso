# echoxml

The `echoxml` task in Ant writes the current state of the project (including targets, properties, and references) in XML format to the console or a specified file. This can be useful for debugging and understanding what has been configured in the build script at a given point in time.

## Usage

```groovy
target('dumpProjectState') {
  echoxml()
}
```

This will output the project structure as XML to the console (standard output).

To write the output to a file:

```groovy
target('dumpProjectToFile') {
  echoxml(file: 'build/projectState.xml')
}
```

## Attributes

| Attribute | Description                                      | Required             |
|----------:|--------------------------------------------------|----------------------|
|    `file` | File to write the XML to (defaults to stdout)    | No                   |
|  `format` | Whether to pretty-print the XML (`true`/`false`) | No (default: `true`) |

## Example Output

```xml
<project name="example" default="dumpProjectState">
  <target name="dumpProjectState"/>
  <property name="src.dir" value="src"/>
  <!-- More properties and targets -->
</project>
```

## Notes

- The XML structure includes all currently defined targets and properties.
- Useful for inspecting how variables and references have been resolved during execution.

## Reference

- [Ant Manual: echoxml Task](https://ant.apache.org/manual/Tasks/echoxml.html)
