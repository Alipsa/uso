# schemavalidate

The `schemavalidate` task validates XML files against one or more XML Schema (XSD) definitions. It extends the `xmlvalidate` task with XSD-specific features like namespace-aware schema binding and full schema checking.

## Usage Examples

### Validate a single XML file against a no-namespace schema

```groovy
target('validateConfig') {
  schemavalidate(
    noNamespaceFile: 'schema/config.xsd',
    failonerror: true
  ) {
    fileset(dir: 'src/config', includes: 'config.xml')
  }
}
```

### Validate with explicit namespace bindings

```groovy
target('validateWithNamespaces') {
  schemavalidate(fullchecking: true) {
    schema(namespace: 'http://example.com/schema', file: 'schema/example.xsd')
    fileset(dir: 'src/xml', includes: '**/*.xml')
  }
}
```

## Attributes

|         Attribute | Description                                                                         | Required |
|------------------:|-------------------------------------------------------------------------------------|----------|
|       `classname` | Fully qualified class name of the XML parser to use                                 | No       |
|    `classpathref` | Reference to a `<path>` or classpath containing the parser and dependencies         | No       |
|      `disableDTD` | `true` to disable DTD support during schema validation (default: `false`)           | No       |
|     `failonerror` | `true` to fail the build on validation errors (default: `true`)                     | No       |
|    `fullchecking` | `true` to enforce strict XSD validation (slower, default: `true`)                   | No       |
|         `lenient` | `true` to only check well-formedness and basic validation (skips schema validation) | No       |
| `noNamespaceFile` | Path to an XSD file to use for XML documents without a namespace                    | No       |
|  `noNamespaceURL` | URL of an XSD to apply for no-namespace XML documents                               | No       |
|            `warn` | `true` to log parser warnings (default: `false`)                                    | No       |
|            `file` | Single XML file to validate (can use nested resource collections instead)           | No       |

## Nested Elements

- `<schema>` — Bind a namespace to a schema:
  - `namespace` — The target namespace URI (required).
  - `file` or `url` — XSD location; exactly one must be provided.
- `<dtd>` — Resolve DTDs and entities during validation:
  - `publicId` — Public identifier of the DTD (required).
  - `location` — File, resource, or URL for the DTD (required).
- `<xmlcatalog>` — Use an XML Catalog for entity resolution.
- `<attribute>` — Set parser features (e.g., SAX or Xerces features):
  - `name` — Feature name (e.g., `http://xml.org/sax/features/validation`).
  - `value` — Boolean value (`true`/`false`).
- `<property>` — Set parser-specific properties:
  - `name` — Property name as supported by the parser.
  - `value` — Property value (string).

Additionally, you can embed any Ant resource collection (e.g., `<fileset>`, `<path>`) to specify the set of XML files to validate.

## Notes

- Enables JAXP 1.2 or Xerces XSD schema validation features automatically.
- The `noNamespaceFile`/`noNamespaceURL` attributes are required when validating documents without `xsi:schemaLocation`.
- Nested `<schema>` elements allow multi-schema setups with namespace declarations.
- Use `<attribute>` and `<property>` to tune the underlying parser (e.g., to enable Xerces-specific features).

## Reference

- [Ant Manual: schemavalidate Task](https://ant.apache.org/manual/Tasks/schemavalidate.html)
