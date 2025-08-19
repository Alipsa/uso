# xmlproperty

The `xmlproperty` task loads property values from a well-formed XML file into Ant properties. By default it simply maps XML element hierarchy to dot-separated property names without resolving references, but it can also handle semantic attributes for special processing (e.g., `id`, `location`, `refid`, `value`, and `pathid`).

There are no other restrictions than "well-formed". You can choose the layout you want. For example this XML property file:
```xml
<root>
  <properties>
    <foo>bar</foo>
  </properties>
</root>
```
is roughly equivalent to this Java property file:

`root.properties.foo = bar`
By default, this load does no processing of the input. In particular, unlike the Property task, property references (i.e., `${foo}`) are not resolved.

Input processing *can* be enabled by using the semanticAttributes attribute. If this attribute is set to true (its default is false), the following processing occurs as the input XML file is loaded:/p>

- Property references are resolved.
- The following attributes are treated differently:
  - id: The property is associated with the given id value.
  - location: The property is treated as a file location
  - refid: The property is set to the value of the referenced property.
  - value: The property is set to the value indicated.
- path-like structures can be defined by use of the following attributes:
  - pathid: The given id is used to identify a path. The nested XML tag name is ignored. Child elements can be used (XML tag names are ignored) to identify elements of the path.
  
For example, with semantic attribute processing enabled, this XML property file:
```xml
<root>
  <properties>
    <foo location="bar"/>
    <quux>${root.properties.foo}</quux>
  </properties>
</root>
```
is roughly equivalent to the following fragments in a build.xml file:
```xml
<property name="root.properties.foo" location="bar"/>
<property name="root.properties.quux" value="${root.properties.foo}"/>
```

## Usage Examples

```groovy
// Basic loading: root.properties.foo => bar
target('loadBasicXml') {
  xmlproperty(file: 'config/properties.xml')
}

// Load without including root element name in property keys
target('loadNoRoot') {
  xmlproperty(
    file: 'config/properties.xml',
    keepRoot: false
  )
}

// Prefix all properties with 'app.'
target('loadWithPrefix') {
  xmlproperty(
    file: 'config/properties.xml',
    prefix: 'app'
  )
}

// Enable semantic attribute processing for id, location, refid, and pathid
target('loadSemantic') {
  xmlproperty(
    file: 'config/semantic.xml',
    keepRoot: false,
    semanticAttributes: true,
    includeSemanticAttribute: true
  )
}

// Use a nested resource collection (fileset) instead of file attribute
target('batchLoad') {
  xmlproperty {
    fileset(dir: 'config', includes: '**/*.xml')
  }
}
```

## Attributes

| Attribute                  | Description                                                                                                                                   | Required                   |
|----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|----------------------------|
| `file`                     | Path to the XML file to parse. Required unless a nested resource collection is provided.                                                      | Conditional                |
| `prefix`                   | Prefix to prepend to each property name (default: none).                                                                                      | No                         |
| `keepRoot`                 | Keep the XML root element name as the first segment in property names (`true                                                                  | false`, default: `true`).  | No                                  |
| `validate`                 | Validate the XML against a DTD or schema; otherwise only well-formedness is required (`true                                                   | false`, default: `false`). | No                                  |
| `collapseAttributes`       | Treat attributes as nested property names rather than including parentheses (`true                                                            | false`, default: `false`). | No                                  |
| `semanticAttributes`       | Enable special processing of `id`, `location`, `refid`, `value`, and `pathid` attributes when loading (`true                                  | false`, default: `false`). | No                                  |
| `includeSemanticAttribute` | Include the semantic attribute name in the resulting property name when `semanticAttributes` is enabled (`true                                | false`, default: `false`). | No                                  |
| `rootDirectory`            | Base directory for resolving file locations on `location` attributes; ignored if `semanticAttributes` is not enabled (default: `${basedir}`). | No                         |
| `delimiter`                | Delimiter for splitting multi-valued elements or attributes when `semanticAttributes` is enabled (default: `,`). Introduced in Ant 1.7.1.     | No                         |

## Nested Elements

- `<xmlcatalog>` — Define an XML catalog for entity resolution (Catalog resolver).  
- **Resource collections** (`<fileset>`, `<path>`, `<filelist>`, etc.) — Specify one or more XML files as input if `file` attribute is omitted.

## Notes

- By default, property values are not processed for `${...}` references. Enable `semanticAttributes` to resolve them.  
- Semantic attribute processing recognizes `id`, `location` (as file paths), `refid` (to assign existing property values), `value`, and `pathid` (to build Ant `<path>` structures).  
- Use `collapseAttributes="true"` to flatten attributes into property names instead of parentheses notation.  
- The task is part of Ant core (no optional libraries required).

## Reference

- [Ant Manual: xmlproperty Task](https://ant.apache.org/manual/Tasks/xmlproperty.html)
