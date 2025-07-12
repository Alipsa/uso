# telnet

The `telnet` task automates a remote telnet session over SSH using JSch and Ant’s optional net tasks. It sends commands and waits for specific prompts, enabling scripted interactions with Telnet servers.

## Defining the Task

Since `telnet` is part of Ant’s optional tasks, include the optional JARs and register the task before use:

```groovy
path(id: 'telnetPath') {
  pathelement(location: 'lib/ant-jsch.jar')
  pathelement(location: 'lib/jsch.jar')
  pathelement(location: 'lib/ant-commons-net.jar')
}
taskdef(
  name: 'telnet',
  classname: 'org.apache.tools.ant.taskdefs.optional.net.TelnetTask',
  classpathref: 'telnetPath'
)
```
You can also download the dependency dynamically, e.g.:
```groovy
import groovy.ant.AntBuilder

def ant = new AntBuilder()
ant.with {

  URI[] telnetUris = Grape.instance.resolve(classLoader: this.class.classLoader, [
      [group:'org.apache.ant', module:'ant-jsch', version:'1.10.15'],
      [group:'org.apache.ant', module:'ant-commons-net', version:'1.10.15'],
      [group:'com.github.mwiede', module:'jsch', version:'2.27.2']
  ] as Map[])
  // Create a path to it
  path(id: 'telnetPath') {
    telnetUris.each {
      pathelement(location: new File(it))
    }
  }
  taskdef(
      name: 'telnet',
      classname: 'org.apache.tools.ant.taskdefs.optional.net.TelnetTask',
      classpathref: 'telnetPath'
  )
}
```
## Usage Examples

```groovy
// Login, list home directory, and exit
target('listRemote') {
  telnet(
    userid: 'bob',
    password: 'badpass',
    server: 'localhost'
  ) {
    read '/home/bob'
    write 'ls'
    read string: '/home/bob'
  }
}

// Send HTTP 0.9 request on port 80 with a 10 second timeout
target('fetchHttp') {
  telnet(
    server: 'localhost',
    port: 80,
    timeout: 20,
    initialCR: 'yes'
  ) {
    read()
    write 'GET / HTTP/0.9'
    write ''
    read(timeout: 10) '</HTML>'
  }
}
```

## Attributes

|   Attribute | Description                                                                       | Required                  |
|------------:|-----------------------------------------------------------------------------------|---------------------------|
|    `userid` | Login ID to use on the telnet server. Must be specified together with `password`. | No (if no authentication) |
|  `password` | Password for the telnet server. Must be specified together with `userid`.         | No (if no authentication) |
|    `server` | Address or hostname of the remote Telnet server.                                  | Yes                       |
|      `port` | Port number of the remote server (default: `23`).                                 | No                        |
| `initialCR` | Send a carriage return immediately after connecting (`yes`/`no`, default: `no`).  | No                        |
|   `timeout` | Default timeout in seconds to wait for a response (no timeout by default).        | No                        |

## Nested Elements

- `<read>` — Wait for a specific string from the server.
  - **Attributes**:
    - `timeout` (seconds, overrides task-level `timeout`).
    - `string` (alternative to text content).
- `<write>` — Send text to the server.
  - **Attributes**:
    - `echo` (`true`/`false`, default `true`): whether to echo the sent text to the log.
    - Content or `value` attribute for the text to send.

## Notes

- Requires both Ant’s optional tasks JAR and the JSch library on the classpath.
- The task assumes default Unix prompts for login/password if both `userid` and `password` are specified; specify explicit `<read>` elements to override.
- Use nested `<read>` and `<write>` elements to script the full session interactively.

## Reference

- [Ant Manual: telnet Task](https://ant.apache.org/manual/Tasks/telnet.html)
