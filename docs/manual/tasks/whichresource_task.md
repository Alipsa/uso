# whichresource

The `whichresource` task locates a class or resource on the specified classpath (or system classpath if none is provided) and sets a property with its URL. It is useful for finding configuration files or implementations at runtime.

## Usage Examples

```groovy
// Find a resource on the default (system) classpath
target('findLog4j') {
  whichresource(
    resource: '/log4j.properties',
    property: 'log4j.url'
  )
  echo "Log4j located at ${project.properties['log4j.url']}"
}

// Locate a class in a custom classpath reference
path(id: 'bsf.classpath') {
  fileset(dir: "${user.home}/lang/bsf", includes: '*.jar')
}
target('findBSFManager') {
  whichresource(
    clazz: 'org.apache.bsf.BSFManager',
    classpathref: 'bsf.classpath',
    property: 'bsf.manager.location'
  )
  echo "BSFManager location: ${project.properties['bsf.manager.location']}"
}

// Use nested classpath element to specify lookup jars
target('findAntContribAntlib') {
  whichresource(
    resource: 'net/sf/antcontrib/antlib.xml',
    property: 'antcontrib.location'
  ) {
    classpath {
      pathelement(path: 'f:/testing/ant-contrib/target/ant-contrib.jar')
    }
  }
  echo "Ant-Contrib antlib at ${project.properties['antcontrib.location']}"
}
```

> **Note:** In the Groovy Uso builder, the `class` attribute is named `clazz` to avoid keyword conflicts.

## Attributes

|      Attribute | Description                                                                                                    | Required                     |
|---------------:|----------------------------------------------------------------------------------------------------------------|------------------------------|
|     `property` | Name of the property to set with the URL of the found resource or class.                                       | Yes                          |
|        `clazz` | Fully qualified class name to search for. Use `clazz` instead of `class` in Groovy to avoid the reserved word. | One of `clazz` or `resource` |
|     `resource` | Name of the resource to search for (e.g., `/log4j.properties` or `net/sf/foo/bar.xml`).                        | One of `clazz` or `resource` |
|    `classpath` | Inline classpath for lookup, declared as a nested `<classpath>` element.                                       | No                           |
| `classpathref` | Reference to a `<path>` element defining the classpath (since Ant 1.7.1).                                      | No                           |

## Nested Elements

- `<classpath>` — Define an inline classpath (e.g., via nested `<pathelement>` or `<fileset>`) for searching classes or resources.

## Notes

- If no classpath is provided, the system classpath (including tools.jar and loaded Ant libraries) is used.
- The task sets the property only if the class/resource is found; otherwise, the property remains unset.
- Unlike `<available>`, `whichresource` returns the URL of the resource, not just existence.

## Reference

- [Ant Manual: whichresource Task](https://ant.apache.org/manual/Tasks/whichresource.html)
