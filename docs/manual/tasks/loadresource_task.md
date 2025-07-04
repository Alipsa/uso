# loadresource

The `loadresource` task reads the content of a resource (file, URL, or classpath resource) and assigns it to a property. It can load text or binary data and works from a variety of sources.

## Usage Examples

```groovy
// Load from file
target('loadFromFile') {
  loadresource(property: 'license.text', srcFile: 'LICENSE.txt')
}

// Load from classpath
target('loadFromClasspath') {
  loadresource(property: 'template', resource: 'templates/email.txt')
}

// Load via URL
target('loadFromUrl') {
  loadresource(property: 'remoteData', url: 'https://example.com/data.txt')
}
```

## Attributes

| Attribute     | Description                                                                    | Required |
|---------------|--------------------------------------------------------------------------------|----------|
| `property`    | Name of the property to set with the resource contents                         | Yes      |
| `srcFile`     | File to read from                                                              | One of `srcFile`, `resource`, `url`, or nested resource |
| `resource`    | Classpath resource name                                                        | One of `srcFile`, `resource`, `url`, or nested resource |
| `url`         | URL to fetch content from                                                      | One of `srcFile`, `resource`, `url`, or nested resource |
| `encoding`    | Character encoding to use when reading text                                    | No       |
| `charset`     | Alias for `encoding`                                                           | No       |
| `failonerror` | Whether to fail the build if loading fails (default: `true`)                   | No       |

## Nested Elements

- `filterchain` — apply Ant-style filters (e.g., `<linecontains>`, `<tokenfilter>`) before setting the property.

## Notes

- Supports reading from multiple sources interchangeably.
- All content is stored verbatim in the property unless filtered.
- Useful for embedding small external content (e.g., templates, remote configuration) into your build logic.

## Reference

- [Ant Manual: loadresource Task](https://ant.apache.org/manual/Tasks/loadresource.html)
