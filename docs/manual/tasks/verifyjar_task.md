# verifyjar

The `verifyjar` task verifies that JAR files have valid digital signatures and integrity using the `jarsigner` tool. It can verify a single JAR via attributes or multiple JARs via nested resource collections, and supports options for verbose output, strict checking, and custom JVM settings.

## Usage Examples

```groovy
// Verify a single JAR with alias and store password
target('verifySingle') {
  verifyjar(
    jar: 'build/libs/app.jar',
    alias: 'myalias',
    storepass: 'changeit'
  )
}

// Verify all JARs in a directory via fileset
target('verifyAll') {
  verifyjar {
    fileset(dir: 'build/libs', includes: '**/*.jar')
  }
}

// Verify with verbose and strict checking, plus a custom sysproperty
target('verifyVerbose') {
  verifyjar(
    jar: 'dist/util.jar',
    alias: 'utilkey',
    verbose: true,
    strict: true
  ) {
    sysproperty(key: 'java.home', value: '/usr/lib/jvm/java-17-openjdk')
  }
}
```

## Attributes

| Attribute      | Description                                                                                 | Required    |
|----------------|---------------------------------------------------------------------------------------------|-------------|
| `jar`          | Path to the JAR file to verify. Required unless nested `<path>` or `<fileset>` is used.     | Conditional |
| `alias`        | Alias of the certificate to verify within the keystore.                                     | Yes*        |
| `storepass`    | Password for the keystore integrity (may be visible on the command line).                   | No          |
| `keystore`     | Location of the keystore file. Defaults to the JDK’s default `cacerts` keystore if omitted. | No          |
| `storetype`    | Type of the keystore (e.g., `JKS`).                                                         | No          |
| `keypass`      | Password for the private key if it differs from the keystore password.                      | No          |
| `certificates` | Display information about certificates (`true`/`false`, default `false`).                   | No          |
| `verbose`      | Enable verbose `jarsigner` output (`true`/`false`, default `false`).                        | No          |
| `strict`       | Enable strict signature checking beyond basic validity (`true`/`false`, default `false`).   | No          |
| `maxmemory`    | Maximum memory for the `jarsigner` JVM process (e.g., `128m`).                              | No          |
| `executable`   | Path to a custom `jarsigner` executable in place of the default.                            | No          |
| `providername` | Name of a cryptographic provider to use (as listed in security properties).                 | No          |

\* When verifying multiple JARs via nested resources, `alias` must still be specified.

## Nested Elements

- `<path>` / `<fileset>` — Specify a collection of JAR files to verify.
- `<sysproperty>` — Set a JVM system property for the `jarsigner` process (`key`, `value`).
- `<arg>` — Pass arbitrary command-line arguments directly to `jarsigner`, e.g., `-J-Dsome.prop=val`.

## Notes

- Omitting `alias` causes `jarsigner` to verify all signatures in the JAR.
- Strict mode enforces full manifest and block file consistency checks rather than simple signature verification.
- Override the default `jarsigner` via `executable` if using a non-standard JDK or custom signing tool.

## Reference

- [Ant Manual: verifyjar Task](https://ant.apache.org/manual/Tasks/verifyjar.html)
