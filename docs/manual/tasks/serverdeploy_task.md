# serverdeploy

The `serverdeploy` task runs a vendor-specific deployment tool for hot-deploying J2EE components (EAR, WAR, JAR, etc.) to an application server. It requires nested elements that define the attributes of the chosen deployment tool.

## Usage Examples

```groovy
target('deployApp') {
  // Deploy using a generic Java-based deployment tool
  serverdeploy(action: 'deploy', source: 'dist/myapp.ear') {
    generic(classname: 'com.example.deploy.Main') {
      jvmarg(value: '-Xmx512m')
      arg(value: '--deploy')
      arg(value: 'myapp.ear')
    }
    classpath {
      pathelement(location: 'lib/deploy-tool.jar')
    }
  }
}
```

## Attributes

|   Attribute | Description                                                                                  | Required       |
|------------:|----------------------------------------------------------------------------------------------|----------------|
|    `action` | Action to perform (`deploy`, `undeploy`, `list`, `update`, `delete`, etc.)                   | Yes            |
|    `source` | Path to the component to be deployed (EAR, WAR, JAR, or other supported archive)             | Tool dependent |
| `classpath` | JVM classpath for the deployment tool; can also be supplied via nested `<classpath>` element | Tool dependent |

## Nested Elements

- `<classpath>` — Resource collection for the classpath used by the deployment tool.
- **Vendor-specific elements** — e.g., `<glassfish>`, `<weblogic>`, `<jboss>`, etc.; each element defines server-specific attributes like `server`, `username`, `password`, `host`, `port`, etc.
- `<generic>` — Generic Java-based deployment tool element:
  - **Attributes**: 
    - `classname` (fully qualified Java class to execute)
  - **Nested Elements**:
    - `<arg>` — Passes command-line arguments to the tool.
    - `<jvmarg>` — Passes JVM arguments to the tool.

## Notes

- Use the nested vendor-specific element for your application server to leverage built-in behaviors.
- The generic element allows invoking any Java class as a deployment tool, offering maximum flexibility.
- Classpath can be provided via attribute or nested `<classpath>` to ensure the tool’s dependencies are available.

## Reference

- [Ant Manual: serverdeploy Task](https://ant.apache.org/manual/Tasks/serverdeploy.html)
