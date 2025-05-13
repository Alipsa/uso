# Ant Task: GenKey

## Original Ant Task Description

The `genkey` Ant task is used to generate a key pair (public and private key) and store it in a keystore. This is often used in scenarios requiring digital signatures or encryption, such as securing web applications or signing code.

## Groovy AntBuilder DSL Equivalent

In Groovy, using `AntBuilder`, the `genkey` task can be invoked by providing the necessary parameters, similar to its XML counterpart. You can specify attributes like alias, storepass, keystore location, and distinguished name (dname) parameters directly in the Groovy script.

```groovy
// Basic structure for using genkey with AntBuilder
ant = new AntBuilder()

// Example: Generate a key with a specific alias and storepass
// (Assuming a keystore already exists or will be created by keytool implicitly if not specified)
ant.genkey(alias: "myKeyAlias", storepass: "mySecurePassword", 
           keystore: "myApplication.keystore", 
           dname: [CN: "My Application", OU: "My Org Unit", O: "My Org", C: "US"])
// Note: The dname structure above is a conceptual representation for Groovy.
// The actual implementation might involve creating a DName object or a map, 
// depending on how AntBuilder handles nested XML structures internally.
// For simplicity in this conceptual example, we'll treat it as a map.

// If dname needs to be more structured as per Ant's <dname><param> structure:
ant.genkey(alias: "myKeyAlias", storepass: "mySecurePassword", keystore: "myApplication.keystore") {
    dname {
        param(name: "CN", value: "My Application")
        param(name: "OU", value: "My Org Unit")
        param(name: "O", value: "My Org")
        param(name: "C", value: "US")
    }
}

```

## Parameter Mapping

The parameters for the `genkey` task in Ant XML can be mapped to arguments when using `AntBuilder` in Groovy.

| Ant Attribute     | Groovy Parameter      | Description                                                                 | Required |
|-------------------|-----------------------|-----------------------------------------------------------------------------|----------|
| `alias`           | `alias`               | The alias for the generated key entry in the keystore.                      | Yes      |
| `storepass`       | `storepass`           | Password for accessing the keystore. Must be at least 6 characters.         | Yes      |
| `keystore`        | `keystore`            | The location of the keystore file.                                          | No       |
| `storetype`       | `storetype`           | The type of the keystore (e.g., JKS, PKCS12).                               | No       |
| `keypass`         | `keypass`             | Password for the private key, if different from `storepass`.                | No       |
| `sigalg`          | `sigalg`              | The signature algorithm (e.g., SHA256withRSA).                              | No       |
| `keyalg`          | `keyalg`              | The key generation algorithm (e.g., RSA).                                   | No       |
| `verbose`         | `verbose`             | If `true`, enables verbose output during key generation.                    | No       |
| `dname`           | `dname`               | The distinguished name for the entity (as a string).                        | Yes, unless nested `dname` is used |
| `saname`          | `saname`              | Subject Alternative Name. Requires Java 7+.                                 | No       |
| `validity`        | `validity`            | Certificate validity period in days.                                        | No       |
| `keysize`         | `keysize`             | The size of the key to be generated.                                        | No       |

### Nested `dname` Element

If you prefer to specify the distinguished name components individually, you can use a nested `dname` element with `param` sub-elements:

**Ant XML:**
```xml
<genkey alias="myalias" storepass="mypassword">
  <dname>
    <param name="CN" value="Common Name"/>
    <param name="OU" value="Organizational Unit"/>
    <param name="O"  value="Organization"/>
    <param name="L"  value="Locality"/>
    <param name="S"  value="StateOrProvince"/>
    <param name="C"  value="Country"/>
  </dname>
</genkey>
```

**Groovy AntBuilder DSL (Conceptual):**
```groovy
ant.genkey(alias: "myalias", storepass: "mypassword") {
    dname {
        param(name: "CN", value: "Common Name")
        param(name: "OU", value: "Organizational Unit")
        param(name: "O",  value: "Organization")
        param(name: "L",  value: "Locality")
        param(name: "S",  value: "StateOrProvince")
        param(name: "C",  value: "Country")
    }
}
```

## Code Examples

### Example 1: Basic Key Generation

**Ant XML:**
```xml
<genkey alias="serverkey" 
        storepass="changeit" 
        keystore="server.keystore" 
        dname="CN=Server, OU=Dept, O=MyCompany, C=US" 
        keyalg="RSA" 
        keysize="2048" 
        validity="365" />
```

**Groovy AntBuilder DSL:**
```groovy
// Ensure the directory for the keystore exists if it's not the current directory
// For this example, we assume it's in the current directory.

new AntBuilder().genkey(
    alias: "serverkey_groovy", 
    storepass: "changeit_groovy", 
    keystore: "server_groovy.keystore", 
    dname: "CN=ServerGroovy, OU=DeptGroovy, O=MyCompanyGroovy, C=US",
    keyalg: "RSA", 
    keysize: "2048", // Ant's GenKey seems to expect string here based on docs
    validity: "365"  // Same as above
)
println "Groovy: Key generated in server_groovy.keystore (if it didn't exist, it might be created)."
```

### Example 2: Key Generation with Nested DName

**Ant XML:**
```xml
<genkey alias="anotherkey" storepass="anotherpass" keystore="another.keystore">
  <dname>
    <param name="CN" value="Another Server"/>
    <param name="OU" value="Another Unit"/>
    <param name="O"  value="Another Corp"/>
    <param name="C"  value="CA"/>
  </dname>
</genkey>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().genkey(alias: "anotherkey_groovy", storepass: "anotherpass_groovy", keystore: "another_groovy.keystore") {
    dname {
        param(name: "CN", value: "Another Server Groovy")
        param(name: "OU", value: "Another Unit Groovy")
        param(name: "O",  value: "Another Corp Groovy")
        param(name: "C",  value: "CA")
    }
}
println "Groovy: Key generated with nested dname in another_groovy.keystore."
```

## Notes:

*   The `genkey` task relies on the `keytool` utility that is part of the Java Development Kit (JDK). Ensure that `keytool` is available in the system's PATH or that the Ant script is executed in an environment where `keytool` can be found.
*   Passwords (`storepass`, `keypass`) are provided in plaintext in the Ant build file. For production environments, consider more secure ways to handle sensitive information, such as using environment variables or external property files that are not checked into version control, and then referencing them within the Ant script.
*   The `destfile` attribute mentioned in the general description of Ant tasks is referred to as `keystore` for the `genkey` task, specifying the path to the keystore file.
*   The `zipfile` attribute mentioned in the general description is not applicable here, as `genkey` deals with key generation, not file compression.
*   When using `dname` as an attribute, ensure the string value is correctly formatted as a single distinguished name string.
*   The nested `dname` with `param` elements offers a more structured way to define the distinguished name components.

