# Ant Task: FTP

## Original Ant Task Description

The `ftp` task provides functionality for a basic FTP client within Ant. It allows for sending (uploading), receiving (downloading), listing, and deleting files on a remote FTP server, as well as creating and removing remote directories. It can also execute site-specific commands and change file permissions (chmod) on Unix-like servers.

Key features and considerations include:
*   **External Dependencies**: This task relies on external libraries (typically Apache Commons Net) which are not part of the standard Ant distribution and must be available in Ant's classpath.
*   **Server Type Detection**: The task attempts to auto-detect the remote FTP server's operating system (Unix, NT, OS2, VMS, OS400) to handle directory listings and path conventions correctly. If auto-detection fails or is insufficient, a Unix-like structure is assumed. The `separator` attribute can be used to specify a custom file separator for non-standard servers.
*   **Directory-Based Operations**: It supports `fileset` nested elements for specifying which files to act upon, similar to other directory-based Ant tasks.
*   **Proxy and Firewall**: The task does not use proxy settings configured by `<setproxy>` and has limitations with firewalls that require SOCKS.
*   **Passive Mode**: The `passive` attribute can be set to `true` to enable passive FTP mode, which is often necessary for connections through firewalls.
*   **Timestamp Sensitivity**: The `newer` (or `depends`) attribute, along with `timediffauto`, `timediffmillis`, and `timestampGranularity`, helps in transferring files only if they are newer or have changed. Accurate timestamp comparison can be tricky due to differences between local and server time zones or timestamp formats.
*   **Error Handling**: Attributes like `skipFailedTransfers` and `ignoreNoncriticalErrors` offer some control over how the task behaves when encountering issues.

## Groovy AntBuilder DSL Equivalent

The Groovy `AntBuilder` allows for a natural and concise way to use the `ftp` task. Attributes of the Ant XML task become named parameters in the Groovy method call, and nested elements like `<fileset>` are represented as closures.

```groovy
ant = new AntBuilder()

ant.ftp(server: "ftp.example.com", userid: "myuser", password: "mypassword", action: "put", remotedir: "/public_html/uploads") {
    fileset(dir: "build/dist") {
        include(name: "**/*.jar")
        include(name: "**/*.html")
    }
}
```

## Parameter Mapping

| Ant Attribute             | Groovy Parameter          | Description                                                                                                                               | Required                                |
|---------------------------|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------|
| `server`                  | `server`                  | The address of the remote FTP server.                                                                                                     | Yes                                     |
| `port`                    | `port`                    | The port number of the remote FTP server.                                                                                                 | No; defaults to 21                      |
| `userid`                  | `userid`                  | The login ID to use on the FTP server.                                                                                                    | Yes                                     |
| `password`                | `password`                | The login password to use on the FTP server.                                                                                              | Yes                                     |
| `account`                 | `account`                 | The account to use on the FTP server (since Ant 1.7).                                                                                     | No                                      |
| `remotedir`               | `remotedir`               | Remote directory on the FTP server. Usage depends on the action.                                                                          | No (but often essential for actions)    |
| `action`                  | `action`                  | FTP action: `put` (or `send` - default), `get`, `del`, `list`, `chmod`, `mkdir`, `rmdir`, `site`.                                          | No; defaults to `send` (`put`)          |
| `binary`                  | `binary`                  | `true` for binary mode, `false` for text/ASCII mode.                                                                                      | No; defaults to `true`                  |
| `passive`                 | `passive`                 | `true` for passive mode transfers.                                                                                                        | No; defaults to `false`                 |
| `verbose`                 | `verbose`                 | `true` to display information on each file transferred.                                                                                   | No; defaults to `false`                 |
| `depends`                 | `depends`                 | `true` to transfer only new or changed files.                                                                                             | No; defaults to `false`                 |
| `newer`                   | `newer`                   | Synonym for `depends`.                                                                                                                    | No                                      |
| `timediffauto`            | `timediffauto`            | `true` to make Ant calculate time difference between client/server (requires remote write access for temp file). (Since Ant 1.6)          | No                                      |
| `timestampGranularity`    | `timestampGranularity`    | `MINUTE` or `NONE`. Overrides timestamp comparison for `put` (default `MINUTE`) and `get` (default `NONE`). (Since Ant 1.7)                 | No                                      |
| `timediffmillis`          | `timediffmillis`          | *Deprecated*. Milliseconds to add to remote time. Use `timestampGranularity` or `serverTimeZoneConfig` instead. (Since Ant 1.6)           | No                                      |
| `separator`               | `separator`               | File separator used on the FTP server.                                                                                                    | No; defaults to `/`                     |
| `umask`                   | `umask`                   | Default file permissions for new files (Unix only). E.g., "022".                                                                          | No                                      |
| `chmod`                   | `chmod`                   | File permissions for new/existing files (Unix only). E.g., "755". Used with `put` or `chmod` action.                                      | No                                      |
| `listing`                 | `listing`                 | File to write results of the `list` action.                                                                                               | Yes for `list` action                   |
| `ignoreNoncriticalErrors` | `ignoreNoncriticalErrors` | `true` to ignore some non-fatal error codes.                                                                                              | No; defaults to `false`                 |
| `skipFailedTransfers`     | `skipFailedTransfers`     | `true` to skip unsuccessful file operations with a warning and continue.                                                                  | No; defaults to `false`                 |
| `preservelastmodified`    | `preservelastmodified`    | `true` to give copied files (get action) the same last modified time as source.                                                           | No; defaults to `false`                 |
| `retriesAllowed`          | `retriesAllowed`          | Number of retries for a failed file transfer (0, positive number, or -1/`forever`).                                                       | No; defaults to 0                       |
| `siteCommand`             | `siteCommand`             | Server-specific `SITE` command to execute if `action="site"`.                                                                             | No; required if `action="site"`         |
| `initialSiteCommand`      | `initialSiteCommand`      | Server-specific `SITE` command to execute immediately after login.                                                                        | No                                      |
| `enableRemoteVerification`| `enableRemoteVerification`| `true` to verify data connection host matches control connection host (security). (Since Ant 1.8.0)                                     | No; defaults to `true`                  |
| `dataTimeout`             | `dataTimeout`             | Timeout in milliseconds for data connection (0 for infinite). (Since Ant 1.10.7)                                                          | No                                      |
| `wakeUpTransferInterval`  | `wakeUpTransferInterval`  | Interval in seconds to send a LIST command to keep data connection alive. (Since Ant 1.10.7)                                            | No                                      |
| `systemTypeKey`           | `systemTypeKey`           | System type key (e.g., `UNIX`, `WINDOWS_NT`). Overrides auto-detection. Requires Commons Net 1.4.0+.                                      | No                                      |
| `defaultDateFormatStr`    | `defaultDateFormatStr`    | Default date format string for parsing listings. Requires Commons Net 1.4.0+.                                                             | No                                      |
| `recentDateFormatStr`     | `recentDateFormatStr`     | Date format string for recently modified files. Requires Commons Net 1.4.0+.                                                              | No                                      |
| `serverLanguageCode`      | `serverLanguageCode`      | Language code for server (e.g., `en`, `fr`). Affects listing parsing. Requires Commons Net 1.4.0+.                                       | No                                      |
| `serverTimeZoneConfig`    | `serverTimeZoneConfig`    | Server time zone ID (e.g., `America/New_York`). Helps with timestamp accuracy. Requires Commons Net 1.4.0+.                               | No                                      |

### Nested Elements

*   **`<fileset>`**: Specifies a collection of local files (for `put`/`send` action) or remote file patterns (for `get`, `del`, `chmod`, `list` actions). Standard fileset attributes and nested elements (`<include>`, `<exclude>`, `<patternset>`, etc.) apply.
    ```groovy
    // For 'put' action (local fileset)
    ftp(action: "put", ...) {
        fileset(dir: "src/main/webapp") {
            include(name: "**/*.html")
            exclude(name: "**/test/**")
        }
    }

    // For 'get' action (remote fileset pattern)
    ftp(action: "get", ...) {
        fileset {
            include(name: "/docs/*.pdf")
            include(name: "/images/*.png")
        }
    }
    ```

## Code Examples

### Example 1: Sending (Uploading) Files

**Ant XML:**
```xml
<ftp server="ftp.example.com"
     userid="testuser"
     password="testpass"
     remotedir="/htdocs/myfiles"
     binary="true">
    <fileset dir="dist/web">
        <include name="**/*.html"/>
        <include name="images/**/*.jpg"/>
        <exclude name="**/*.tmp"/>
    </fileset>
</ftp>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    ftp(server: "ftp.example.com", 
        userid: "testuser", 
        password: "testpass", 
        remotedir: "/htdocs/myfiles", 
        binary: true,
        verbose: true) {
        fileset(dir: "dist/web") {
            include(name: "**/*.html")
            include(name: "images/**/*.jpg")
            exclude(name: "**/*.tmp")
        }
    }
    println "Files uploaded via FTP."
}
```

### Example 2: Receiving (Downloading) Files

**Ant XML:**
```xml
<ftp server="ftp.example.com"
     userid="testuser"
     password="testpass"
     action="get"
     remotedir="/server/backups"
     localdir="local_backups"
     passive="true"
     newer="true"
     preservelastmodified="true">
    <fileset>
        <include name="daily_*.zip"/>
    </fileset>
</ftp>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    ftp(server: "ftp.example.com", 
        userid: "testuser", 
        password: "testpass", 
        action: "get", 
        remotedir: "/server/backups", 
        localdir: "local_backups", 
        passive: true, 
        newer: true, 
        preservelastmodified: true) {
        fileset {
            include(name: "daily_*.zip")
        }
    }
    println "Files downloaded via FTP."
}
```

### Example 3: Deleting Remote Files

**Ant XML:**
```xml
<ftp server="ftp.example.com"
     userid="testuser"
     password="testpass"
     action="del"
     remotedir="/htdocs/temp_files">
    <fileset>
        <include name="**/*.tmp"/>
        <include name="old_data/**"/>
    </fileset>
</ftp>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    ftp(server: "ftp.example.com", 
        userid: "testuser", 
        password: "testpass", 
        action: "del", 
        remotedir: "/htdocs/temp_files",
        verbose: true) {
        fileset {
            include(name: "**/*.tmp")
            include(name: "old_data/**")
        }
    }
    println "Temporary files deleted from FTP server."
}
```

### Example 4: Listing Remote Files

**Ant XML:**
```xml
<ftp server="ftp.example.com"
     userid="anonymous"
     password="user@example.com"
     action="list"
     remotedir="/pub/somefolder"
     listing="listing_of_pub.txt"/>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    ftp(server: "ftp.example.com", 
        userid: "anonymous", 
        password: "user@example.com", 
        action: "list", 
        remotedir: "/pub/somefolder", 
        listing: "listing_of_pub_groovy.txt")
    println "Remote file listing saved to listing_of_pub_groovy.txt"
}
```

### Example 5: Creating a Remote Directory

**Ant XML:**
```xml
<ftp server="ftp.example.com"
     userid="testuser"
     password="testpass"
     action="mkdir"
     remotedir="/htdocs/new_project/assets"/>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    ftp(server: "ftp.example.com", 
        userid: "testuser", 
        password: "testpass", 
        action: "mkdir", 
        remotedir: "/htdocs/new_project_groovy/assets")
    println "Remote directory created."
}
```

### Example 6: Removing a Remote Directory

**Ant XML:**
```xml
<!-- Note: Directory must typically be empty to be removed by rmdir. -->
<!-- First, delete contents if necessary -->
<ftp server="ftp.example.com" userid="testuser" password="testpass" action="del" remotedir="/htdocs/old_project">
    <fileset><include name="**/*"/></fileset>
</ftp>
<!-- Then, remove the empty directory -->
<ftp server="ftp.example.com"
     userid="testuser"
     password="testpass"
     action="rmdir"
     remotedir="/htdocs/old_project"/>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    // First, delete contents if necessary
    ftp(server: "ftp.example.com", userid: "testuser", password: "testpass", action: "del", remotedir: "/htdocs/old_project_groovy") {
        fileset { include(name: "**/*") }
    }
    // Then, remove the empty directory
    ftp(server: "ftp.example.com", 
        userid: "testuser", 
        password: "testpass", 
        action: "rmdir", 
        remotedir: "/htdocs/old_project_groovy")
    println "Remote directory and its contents (if any) removed."
}
```

### Example 7: Changing File Permissions (chmod)

**Ant XML:**
```xml
<ftp server="ftp.example.com"
     userid="testuser"
     password="testpass"
     action="chmod"
     remotedir="/cgi-bin"
     chmod="755">
    <fileset>
        <include name="*.pl"/>
        <include name="*.cgi"/>
    </fileset>
</ftp>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    ftp(server: "ftp.example.com", 
        userid: "testuser", 
        password: "testpass", 
        action: "chmod", 
        remotedir: "/cgi-bin", 
        chmod: "755") {
        fileset {
            include(name: "*.pl")
            include(name: "*.cgi")
        }
    }
    println "Permissions changed for scripts on FTP server."
}
```

### Example 8: Executing a SITE Command

**Ant XML:**
```xml
<ftp server="ftp.example.com"
     userid="testuser"
     password="testpass"
     action="site"
     siteCommand="HELP"/>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    ftp(server: "ftp.example.com", 
        userid: "testuser", 
        password: "testpass", 
        action: "site", 
        siteCommand: "STAT") // Example: Get server status
    println "SITE command executed."
}
```

## Notes

*   **Dependencies**: Ensure that the necessary FTP library (e.g., Apache Commons Net) is in Ant's classpath. This is often done by placing the JAR file in Ant's `lib` directory or specifying it via the `-lib` command-line option.
*   **Error Handling**: FTP operations can be unreliable due to network issues or server configurations. Use `skipFailedTransfers="true"` if you want the build to continue even if some files fail to transfer. The `retriesAllowed` attribute can also be helpful.
*   **Passive Mode**: `passive="true"` is highly recommended, especially when dealing with firewalls.
*   **Timestamps and `newer`**: When using `newer="true"` (or `depends="true"`), be mindful of potential discrepancies in time zones and timestamp formats between the local machine and the FTP server. Attributes like `timediffauto`, `timestampGranularity`, and `serverTimeZoneConfig` (with Commons Net 1.4.0+) can help mitigate these issues.
*   **Security**: Passwords are specified in plain text in the build file. For sensitive environments, consider using property files that are not checked into version control, or use Ant's `<input>` task to prompt for passwords. Avoid hardcoding credentials directly if possible.
*   **Server-Specific Behavior**: FTP server implementations can vary. Some SITE commands are server-specific. Directory listing formats can also differ, potentially affecting tasks that rely on parsing these listings (like `newer` checks).
*   **`remotedir` and `fileset`**: For actions like `get`, `del`, `list`, and `chmod`, the `remotedir` attribute often acts as a base, and the `<fileset>` specifies patterns *within* that remote directory. For `put`, the `fileset`'s `dir` attribute specifies the local base directory.

This documentation provides a comprehensive overview of using the Ant `ftp` task with Groovy AntBuilder, covering its various actions, parameters, and common usage patterns.

