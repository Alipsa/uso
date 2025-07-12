# xslt

The `xslt` task (formerly `style`) processes XML documents via XSLT, supporting implicit filesets or nested resource collections for input, plus extensive options for parameters, output properties, factory configuration, and error handling.

## Usage Examples

```groovy
// Basic transform: all files under 'doc' → 'build/doc' with .html extension
target('transformDocs') {
  xslt(
    basedir: 'doc',
    destdir: 'build/doc',
    extension: '.html',
    style: 'style/apache.xsl'
  )
}

// Inline stylesheet resource with parameters
target('transformWithParams') {
  xslt(
    in: 'data.xml',
    out: 'out/output.xml',
    style: 'template.xsl'
  ) {
    param(name: 'date', expression: '2025-07-12')
    param(name: 'author', expression: project.properties['user.name'])
  }
}

// Custom factory and output properties
target('customTransform') {
  xslt(
    basedir: 'src',
    destdir: 'out',
    style: 'xsl/main.xsl',
    factory: 'org.apache.xalan.processor.TransformerFactoryImpl'
  ) {
    factory {
      attribute(name: 'http://xml.apache.org/xalan/features/optimize', value: 'true')
    }
    outputproperty(name: 'indent', value: 'yes')
    outputproperty(name: 'encoding', value: 'UTF-8')
    xmlcatalog {
      public(publicId: '-//DTD Ex//EN', uri: 'dtds/example.dtd')
    }
  }
}
```

## Attributes

| Attribute                                | Description                                                                                                                    | Required                                                                                   |
|------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| `basedir`                                | Directory to scan for source XML files (implicit fileset). Default: project base directory.                                    | No                                                                                         |
| `destdir`                                | Directory to write transformed files. Required unless using `in`/`out` attributes.                                             | Conditional                                                                                |
| `in`                                     | Single source XML file (use with `out`).                                                                                       | Conditional                                                                                |
| `out`                                    | Single output file for `in`.                                                                                                   | Conditional                                                                                |
| `style`                                  | XSLT stylesheet location, relative or absolute path. Use `style` attribute or nested `<style>` resource.                       | Yes                                                                                        |
| `extension`                              | File extension for output files when using `basedir`/`destdir` (default: `.html`). Ignored if `in`/`out` or nested `<mapper>`. | No                                                                                         |
| `includes`                               | Comma‑ or space‑separated patterns of files to include for implicit fileset. Default: all (`**`).                              | No                                                                                         |
| `includesfile`                           | File with include patterns, one per line.                                                                                      | No                                                                                         |
| `excludes`                               | Patterns of files to exclude.                                                                                                  | No                                                                                         |
| `excludesfile`                           | File with exclude patterns, one per line.                                                                                      | No                                                                                         |
| `defaultexcludes`                        | Whether to apply Ant's default excludes (`yes                                                                                  | no`; default: `yes`).                                                                      | No         |
| `classpath`                              | Inline classpath for loading custom XSLT processors.                                                                           | No                                                                                         |
| `classpathref`                           | Reference to an existing `<path>` element defining classpath.                                                                  | No                                                                                         |
| `force`                                  | Recreate target files even if they are newer than source or stylesheet (`true                                                  | false`; default: `false`).                                                                 | No         |
| `scanincludedirectories`                 | If directories matched by patterns should be scanned for files (`true                                                          | false`; default: `true`).                                                                  | No         |
| `reloadstylesheet`                       | Reload stylesheet for each transform (`true                                                                                    | false`; default: `false`). Useful for dynamic stylesheet updates.                          | No         |
| `useImplicitFileset`                     | Whether to use the implicit fileset formed by this task (`true                                                                 | false`; default: `true`). If `false`, requires nested resource collections or `in`/`out`.  | No         |
| `filenameparameter`                      | Name of an XSLT parameter to receive the current filename (`String`).                                                          | No                                                                                         |
| `filedirparameter`                       | Name of an XSLT parameter to receive the current file's directory (`String`).                                                  | No                                                                                         |
| `suppressWarnings`                       | Suppress processor warnings (`true                                                                                             | false`; default: `false`). Requires processor support (e.g., bundled TrAX implementation). | No         |
| `failOnError`                            | Fail the build on any error (`true                                                                                             | false`; default: `true`).                                                                  | No         |
| `failOnTransformationError`              | Fail only on transformation errors (`true                                                                                      | false`; default: `true`).                                                                  | No         |
| `failOnNoResources`                      | Fail if no resources match implicit fileset or nested collections (`true                                                       | false`; default: `true`).                                                                  | No         |
| `processor`                              | Name of XSLT processor: `trax` or other (`default: `trax`).                                                                    | No                                                                                         |
| `jdk.xml.transform.extensionClassLoader` | Classloader reference for extension functions. Use nested `<factory>` and `<attribute>`.                                       | No                                                                                         |

## Nested Elements

- `<param>` — Pass values to `<xsl:param>` in the stylesheet (`name`, `expression`, `type`, `if`, `unless`).  
- `<style>` — Nested resource to specify stylesheet via `<url>`, `<file>`, `<resource>` or `<fileset>`.  
- `<mapper>` — Customize output filenames; default removes extension and appends `extension`.  
- `<xmlcatalog>` — Define XML Catalog for entity resolution (`<public>`, `<system>`, etc.).  
- `<factory>` — Configure JAXP `TransformerFactory` features via nested `<attribute>` elements.  
- `<outputproperty>` — Set XSLT output parameters such as method, indent, encoding (`name`, `value`).  
- `<sysproperty>` / `<syspropertyset>` — Set JVM system properties for processor (since Ant 1.8.0).

## Notes

- Task name `style` is deprecated; use `xslt` instead.  
- Supports nested resource collections since Ant 1.7.  
- Behavior for directories matching differs: set `scanincludedirectories="false"` to avoid processing entire directory.  
- Use custom `TransformerFactory` via `factory` if needing Saxon or other implementations.  
- Ensure proper classpath for processor libraries (Xalan, Saxon, etc.) when not using default JAXP.

## Reference

- [Ant Manual: XSLT (`style`) Task](https://ant.apache.org/manual/Tasks/style.html")
