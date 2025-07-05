# Ant Task: Jarlib-available

## Original Ant Task Description

Checks whether an extension is present in a `fileset` or an `extensionSet`. If the extension is present then a property is set.

Note that this task works with extensions as defined by the "Optional Package" specification. For more information about optional packages, see the document _Optional Package Versioning_ in the documentation bundle for your Java Standard Edition package, in file guide/extensions/versioning.html or the online [Extension and ExtensionSet documentation](https://ant.apache.org/manual/Types/extension.html) for further details.

## Parameters

| Attribute  | Description                                            | Required                                                          |
|------------|--------------------------------------------------------|-------------------------------------------------------------------|
| `property` | The name of property to set if extensions is available | Yes                                                               |
| `file`     | The file to check for extension                        | Yes, unless a nested `<extensionSet>` or `<fileset>` is specified |

### Parameters specified as nested elements

#### `extension`

[Extension](https://ant.apache.org/manual/Types/extension.html) the extension to search for.

#### `fileset`

[FileSet](https://ant.apache.org/manual/Types/fileset.html)s are used to select sets of files to check for extension.

#### `extensionSet`

[ExtensionSet](https://ant.apache.org/manual/Types/extensionset.html)s is the set of extensions to search for extension in.

## Examples

### Example 1: Search for extension in single file

```xml
<jarlib-available property="myext.present" file="myfile.jar">
  <extension extensionName="org.apache.tools.ant"
             specificationVersion="1.4.9"
             specificationVendor="Apache Software Foundation"/>
</jarlib-available>
```

### Example 2: Search for extension in single file referencing external Extension

```xml
<extension id="myext"
           extensionName="org.apache.tools.ant"
           specificationVersion="1.4.9"
           specificationVendor="Apache Software Foundation"/>

<jarlib-available property="myext.present" file="myfile.jar">
  <extension refid="myext"/>
</jarlib-available>
```

### Example 3: Search for extension in fileset

```xml
<extension id="myext"
           extensionName="org.apache.tools.ant"
           specificationVersion="1.4.9"
           specificationVendor="Apache Software Foundation"/>

<jarlib-available property="myext.present">
  <extension refid="myext"/>
  <fileset dir="lib">
    <include name="*.jar"/>
  </fileset>
</jarlib-available>
```

### Example 4: Search for extension in extensionSet

```xml
<extension id="myext"
           extensionName="org.apache.tools.ant"
           specificationVersion="1.4.9"
           specificationVendor="Apache Software Foundation"/>

<jarlib-available property="myext.present">
  <extension refid="myext"/>
  <extensionSet id="exts3">
    <libfileset includeUrl="false"
                includeImpl="true"
                dir="lib">
      <include name="*.jar"/>
    </libfileset>
  </extensionSet>
</jarlib-available>
```

## Groovy AntBuilder DSL Equivalent

To use the `jarlib-available` Ant task within a Groovy script via `AntBuilder`, you can invoke it as a method on your `AntBuilder` instance. The parameters and nested elements translate directly to method arguments and closures.

### Key Parameters (and their Groovy equivalents):

| Ant Attribute | Groovy Parameter Type | Description                                                                                                | Required |
|---------------|-----------------------|------------------------------------------------------------------------------------------------------------|----------|
| `property`    | `String`              | The name of property to set if extensions is available                                                     | Yes      |
| `file`        | `String`              | The file to check for extension                                                                            | Yes, unless a nested `<extensionSet>` or `<fileset>` is specified |

### Nested Elements in Groovy:

*   **`extension`**: Corresponds to the `<extension>` Ant element. It defines the extension to search for.
    *   `extensionName`: Name of the extension.
    *   `specificationVersion`: Specification version of the extension.
    *   `specificationVendor`: Specification vendor of the extension.
*   **`fileset`**: Corresponds to the `<fileset>` Ant element. Specifies a set of files to check for the extension.
    *   `dir`: The directory to scan for files.
    *   `includes` / `include`: Patterns for files to include.
    *   `excludes` / `exclude`: Patterns for files to exclude.
*   **`extensionSet`**: Corresponds to the `<extensionSet>` Ant element. Defines a set of extensions to search within.
    *   `id`: An identifier for the extension set.
    *   Nested `libfileset`: Similar to `fileset`, but specifically for library files within an extension set context.

### Example Groovy DSL Usage:

```groovy
def ant = new AntBuilder()

// Example 1: Check for an extension in a single JAR file
ant.jarlibAvailable(property: 'myext.present', file: 'myfile.jar') {
    extension(extensionName: 'org.apache.tools.ant',
              specificationVersion: '1.4.9',
              specificationVendor: 'Apache Software Foundation')
}
if (ant.project.properties['myext.present']) {
    ant.echo(message: 'Extension org.apache.tools.ant is present in myfile.jar')
} else {
    ant.echo(message: 'Extension org.apache.tools.ant is NOT present in myfile.jar')
}

// Example 2: Check for an extension within a fileset
ant.jarlibAvailable(property: 'another.ext.present') {
    extension(extensionName: 'com.example.anotherExt') // Assuming this extension exists
    fileset(dir: 'libs') {
        include(name: '*.jar')
    }
}
if (ant.project.properties['another.ext.present']) {
    ant.echo(message: 'Extension com.example.anotherExt is present in libs directory.')
} else {
    ant.echo(message: 'Extension com.example.anotherExt is NOT present in libs directory.')
}

// Example 3: Referencing a predefined extension and using an extensionSet
ant.extension(id: 'predefined.ext', extensionName: 'org.example.predefined')

ant.jarlibAvailable(property: 'predefined.found') {
    extension(refid: 'predefined.ext')
    extensionSet(id: 'myLibSet') {
        libfileset(dir: 'commonlibs', includeImpl: true) {
            include(name: '*.jar')
        }
    }
}
if (ant.project.properties['predefined.found']) {
    ant.echo(message: 'Predefined extension found in commonlibs.')
} else {
    ant.echo(message: 'Predefined extension NOT found in commonlibs.')
}
```

### Important Considerations for `jarlib-available` with Groovy/AntBuilder:

1.  **Property Setting**: The primary purpose of `jarlib-available` is to set a property. You'll typically check this property's value in subsequent Groovy code to make decisions.
2.  **File Paths**: Ensure that file and directory paths provided to `file`, `dir` (within `fileset` or `libfileset`) are correct relative to the execution context of your Ant script.
3.  **Extension Definition**: When using the nested `extension` element, ensure the attributes like `extensionName`, `specificationVersion`, etc., accurately match what's expected in the JAR manifest.
4.  **Error Handling**: If the specified file or conditions are not met, the property will not be set (or will be false). Your script logic should account for this, rather than assuming the property will always be true.

