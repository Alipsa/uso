# jarlib‑manifest

The `jarlib-manifest` task generates a JAR manifest listing dependencies based on the “Extension” or "Optional Package" declarations found in JAR files within a given path. This is useful for specifying extension relationships via the manifest rather than manually listing Class‑Path entries.

## Usage

```groovy
target('generateManifest') {
  jarlibManifest(destfile: 'build/myManifest.txt') {
    extension(refid: 'myExtension')
    depends(refid: 'requiredExtensions')
    options(refid: 'optionalExtensions')
  }
}
```

This writes `build/myManifest.txt` with entries derived from the referenced extensions.

## Attributes

|  Attribute | Description                             | Required |
|-----------:|-----------------------------------------|----------|
| `destfile` | File to write the generated manifest to | Yes      |

## Nested Elements

- `extension` — specifies one extension your library implements. References an extension defined elsewhere.
- `depends` — an `ExtensionSet` representing required dependencies.
- `options` — an `ExtensionSet` representing optional dependencies (ignored if absent).

## Example

```groovy
project.with {
  // define an extension first
  extension(id: 'e1', extensionName: 'MyExtensions', specificationVersion: '1.0',
            specificationVendor: 'YourOrg', implementationVersion: '2.0',
            implementationVendor: 'YourOrg', implementationURL: 'https://example.com')

  target('generateManifest') {
    jarlibManifest(destfile: 'build/myManifest.txt') {
      extension(refid: 'e1')
    }
  }
}
```

This will create a manifest file containing header lines describing `MyExtensions` and its version.

## Notes

- The task scans the JARs specified in extension/depends/options sets to resolve dependencies automatically.
- It follows the Optional Package specification (“Java Extensions”) for manifest Class‑Path declarations.
- Ideal for building extension‑aware JARs or where explicit dependency metadata is required in the manifest.
- If only a single `extension` is present, the manifest will list that extension but not dependencies.

## Reference

- [Ant Manual: jarlib‑manifest Task](https://ant.apache.org/manual/Tasks/jarlib-manifest.html)
