# jarlib-resolve

The `jarlib-resolve` task attempts to locate a JAR file that satisfies an extension (as defined by the Optional Package specification) and sets a property with the location of the matching JAR. This is useful when dynamically resolving dependencies based on extension declarations in manifests.

## Usage Example

```groovy
target('resolveExtensionJar') {
  jarlibResolve(property: 'resolved.path', failOnError: true, checkExtension: true) {
    extension(refid: 'myExt')
    location(location: 'lib/myExt.jar')
  }
}
```

This will try to resolve a JAR that satisfies the `myExt` extension, and assign its path to the `resolved.path` property.

## Attributes

| Attribute        | Description                                                                  | Required |
|------------------|------------------------------------------------------------------------------|----------|
| `property`       | Name of the property to set with the resolved JAR path                       | Yes      |
| `failOnError`    | If true, fails the build if resolution fails (default: true)                 | No       |
| `checkExtension` | Whether to verify the resolved JAR satisfies the required extension metadata | No       |

## Nested Elements (Resolvers)

- `extension` — The extension (must be defined elsewhere using `<extension>` or `<extensionSet>`).
- `location` — File location to check (uses `location: 'path/to/jar'`).
- `url` — Remote URL to download the JAR from.
- `ant` — Runs another Ant build file to produce the required JAR.

## Full Example

```groovy
project.with {
  extension(id: 'myExt', extensionName: 'MyFeatureLib', specificationVersion: '1.0',
            implementationVersion: '1.2.3', implementationVendor: 'YourOrg')

  target('resolveViaMultipleResolvers') {
    jarlibResolve(property: 'feature.lib.path') {
      extension(refid: 'myExt')
      location(location: 'lib/myfeature.jar')
      url(url: 'https://example.com/myfeature.jar', destfile: 'lib/myfeature.jar')
      ant(antfile: 'build-feature.xml', target: 'buildJar', destfile: 'lib/myfeature.jar')
    }
  }
}
```

## Notes

- Resolver elements are checked in order; the first one that satisfies the extension resolves the property.
- If none match and `failOnError=true`, the build fails.
- This task is especially useful in modular applications that rely on manifest-declared extensions.

## Reference

- [Ant Manual: jarlib-resolve Task](https://ant.apache.org/manual/Tasks/jarlib-resolve.html)
