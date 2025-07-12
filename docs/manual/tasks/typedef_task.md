# typedef

The `typedef` task defines and registers a new data type for use within the project. It maps a name to a Java class that implements the data type, enabling the type to be used in Ant build files via the Groovy Uso builder.

Data types are things like paths or filesets that can be defined at the project level and referenced via their id attribute. Custom data types usually need custom tasks to put them to good use.

Two attributes are needed to make a definition: the name that identifies this data type uniquely, and the full name of the class (including its package name) that implements this type.

You can also define a group of definitions at once using the file or resource attributes. These attributes point to files in the format of Java property files or an xml format.

## Usage Examples

```groovy
target('defineMyDataType') {
  typedef(
    name: 'mytype',
    classname: 'com.example.MyDataType'
  )
}

target('loadTypesFromFile') {
  typedef(file: 'build/types.properties')
}

target('defineViaResource') {
  path(id: 'antlib') {
    pathelement(location: 'lib/my-antlib.jar')
  }
  typedef(
    resource: 'META-INF/antlib.xml',
    classpathref: 'antlib',
    uri: 'antlib:com.example'
  )
}
```

## Attributes

| Attribute      | Description                                                                                               | Required                                      |
|----------------|-----------------------------------------------------------------------------------------------------------|-----------------------------------------------|
| `name`         | Name of the new data type to register                                                                     | Yes, unless `file` or `resource` is specified |
| `classname`    | Fully qualified Java class implementing the data type (must extend `org.apache.tools.ant.types.DataType`) | Yes, unless `file` or `resource` is specified |
| `classpath`    | Inline classpath to locate the implementation class                                                       | No                                            |
| `classpathref` | Reference to an existing `<path>` element for the classpath                                               | No                                            |
| `loaderref`    | Reference to a classloader to use for loading the data type class                                         | No                                            |
| `file`         | Path to a properties file with `typename=classname` entries to load multiple type definitions             | No                                            |
| `resource`     | Classpath resource to load type definitions, typically in Antlib XML format                               | No                                            |
| `uri`          | Namespace URI for resource definitions (when using `resource`)                                            | No                                            |
| `onerror`      | Action if a definition fails: `fail`, `report`, `ignore`, `failall` (default: `fail`)                     | No                                            |
| `adapter`      | Adapter class to wrap the defined class, defaults to `org.apache.tools.ant.types.DataTypeAdapter`         | No                                            |
| `adaptto`      | Interface/class the data type must implement, wrapper used if not                                         | No                                            |

## Nested Elements

- `<classpath>` — Define an inline classpath for loading the data type implementation.

## Notes

- Use `resource` and `uri` to load types from Antlib XML descriptors.
- Group multiple type definitions in a single properties or resource file when possible.
- The `loaderref` attribute allows sharing ClassLoaders between multiple definitions.

## Reference

- [Ant Manual: typedef Task](https://ant.apache.org/manual/Tasks/typedef.html)
