# signjar

The `signjar` task signs JAR files using the `jarsigner` tool, allowing users to verify the publisher of a JAR. It supports signing individual JARs or batches via nested resource collections, in-place or to a specified destination directory.

## Usage Examples

```groovy
project.with {
  // Sign a single JAR in place
  target('signInPlace') {
    signjar(
      jar: 'dist/myapp.jar',
      alias: 'myalias',
      keystore: 'keystore.jks',
      storepass: 'changeit'
    )
  }

  // Sign all JARs under dist/, copying signed jars to 'signed' directory with flatten mapper
  target('signAllDist') {
    signjar(
      alias: 'testonly',
      keystore: 'testkeystore',
      storepass: 'apacheant',
      destDir: 'signed',
      lazy: true
    ) {
      path {
        fileset(dir: 'dist', includes: '**/*.jar')
      }
      flattenmapper()
    }
  }

  // Sign with explicit output file name
  target('signCustomName') {
    signjar(
      jar: 'dist/lib/util.jar',
      alias: 'utilSigner',
      keystore: 'utilKeystore',
      storepass: 'secret',
      signedjar: 'dist/lib/util-signed.jar'
    )
  }
}
```

## Attributes

|              Attribute | Description                                                                                            | Required                               |
|-----------------------:|--------------------------------------------------------------------------------------------------------|----------------------------------------|
|                  `jar` | Path to the JAR file to sign                                                                           | Yes, unless nested resource collection |
|                `alias` | Alias under which to sign                                                                              | Yes                                    |
|            `storepass` | Password for keystore integrity; passed to `jarsigner` via prompt mechanism                            | Yes                                    |
|             `keystore` | Location of the keystore file                                                                          | No                                     |
|            `storetype` | Type of the keystore (e.g., `JKS`)                                                                     | No                                     |
|              `keypass` | Password for the private key if different from `storepass`                                             | No                                     |
|              `sigfile` | Base name for signature files (`.SF`/`.DSA`)                                                           | No                                     |
|            `signedjar` | Path to output signed JAR file (mutually exclusive with `destDir` when signing multiple jars)          | No                                     |
|              `destDir` | Directory into which signed JARs are placed when signing multiple jars                                 | No                                     |
|              `verbose` | Enable verbose signing output (`true`/`false`, default: `false`)                                       | No                                     |
|               `strict` | Enable strict validity checking during signing (`true`/`false`, default: `false`)                      | No                                     |
|           `internalsf` | Include the `.SF` file inside the signature block (`true`/`false`, default: `false`)                   | No                                     |
|         `sectionsonly` | Sign only manifest sections without computing overall manifest hash (`true`/`false`, default: `false`) | No                                     |
|                 `lazy` | Only sign if not already signed when target equals source (`true`/`false`, default: `false`)           | No                                     |
|            `maxmemory` | Maximum memory for the `jarsigner` process (e.g., `128m`)                                              | No                                     |
| `preserveLastModified` | Preserve original JAR file timestamps on signed jars (`true`/`false`, default: `false`)                | No                                     |
|               `tsaurl` | URL of a timestamp authority for timestamped JARs                                                      | No                                     |
|              `tsacert` | Keystore alias of the TSA authority                                                                    | No                                     |
|         `tsaproxyhost` | Proxy host for connecting to TSA                                                                       | No                                     |
|         `tsaproxyport` | Proxy port for connecting to TSA                                                                       | No                                     |
|           `executable` | Path to a custom `jarsigner` executable                                                                | No                                     |
|                `force` | Force signing even if JAR appears up-to-date (`true`/`false`, default: `false`)                        | No                                     |
|               `sigalg` | Signature algorithm (e.g., `MD5withRSA`)                                                               | No                                     |
|            `digestalg` | Digest algorithm (e.g., `SHA1`)                                                                        | No                                     |
|         `tsadigestalg` | Digest algorithm for TSA timestamp (`SHA256`, etc.)                                                    | No                                     |
|         `providername` | Name of cryptographic provider as listed in security properties                                        | No                                     |
|        `providerclass` | Fully qualified class name of a custom cryptographic provider                                          | No                                     |
|          `providerarg` | Argument for the provider class constructor                                                            | No                                     |

## Nested Elements

- `<path>` — Defines a resource collection of JARs to sign.
- `<fileset>` — Specify JAR files via directory patterns (inside `<path>`).
- `<flattenmapper>` or `<mapper>` — Rename or flatten output JAR paths.
- `<sysproperty>` — JVM system properties for the signing process.
- `<arg>` — Pass arbitrary `jarsigner` command-line arguments (since Ant 1.10.6).

## Notes

- When `destDir` is omitted and `signedjar` is not specified, JARs are signed in place.
- `lazy="true"` prevents re-signing jars that already contain the specified alias signature.
- Use `flattenmapper` to avoid subdirectory structures when signing multiple JARs.
- `executable` can override the default `jarsigner` binary; ensure compatibility with standard options.
- Timestamp signing requires `tsaurl` and optionally `tsacert` to embed trusted timestamps.

## Reference

- [Ant Manual: signjar Task](https://ant.apache.org/manual/Tasks/signjar.html)
