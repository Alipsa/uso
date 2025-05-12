## Chgrp Task (Groovy DSL)

### Description

The `chgrp` task is used to change the group ownership of a file or a set of files and directories. This task is primarily effective on Unix-like operating systems. It mirrors the functionality of the `chgrp` command-line utility.

In Groovy AntBuilder, the `chgrp` task is invoked as `ant.chgrp(...)`. Attributes for specifying the file/resource and the new group, along with other options, are passed as named parameters. Nested resource collections like filesets are defined within a closure.

### Parameters

Common Ant attributes for the `chgrp` task and their Groovy AntBuilder DSL mapping:

*   `file`: The specific file or directory whose group is to be changed. Required if no nested resource collection is used.
    *   Groovy: `file: "/path/to/your/file.sh"`
*   `group`: The new group name or GID (Group ID) to assign to the file(s). (Required)
    *   Groovy: `group: "developers"`
*   `parallel`: (Boolean) Whether to process all specified files using a single underlying `chgrp` command. Defaults to `true`. Setting to `false` might execute `chgrp` for each file individually, which can be slower but might avoid command-line length limits.
    *   Groovy: `parallel: false`
*   `type`: Specifies what to operate on. Can be "file", "dir", or "both". Defaults to "file".
    *   Groovy: `type: "both"`
*   `maxparallel`: Limits the number of files passed to a single `chgrp` command invocation if `parallel` is true. Useful for very long file lists that might exceed OS command-line length limits. A negative value means unlimited.
    *   Groovy: `maxparallel: 500`
*   `verbose`: (Boolean) If `true`, prints a summary after execution. Defaults to `false`.
    *   Groovy: `verbose: true`
*   `os`: A comma-separated list of operating systems on which this task should run. Useful if you have a `chgrp`-compatible command on a non-Unix system.
    *   Groovy: `os: "Linux,Mac OS X"`
*   `osfamily`: The OS family to target (e.g., "unix", "windows"). Defaults to "unix".
    *   Groovy: `osfamily: "unix"`

### Nested Elements

*   **fileset**: To specify a collection of files.
*   **dirset**: To specify a collection of directories.
*   **filelist**: To specify a list of files.
*   **Resource Collections**: Generic resource collections can be used.

    ```groovy
    ant.chgrp(group: "webadmin") {
        fileset(dir: "/var/www/html") {
            include(name: "**/*.php")
            exclude(name: "config/**")
        }
        dirset(dir: "/var/www/html/uploads") {
            include(name: "**") // All subdirectories in uploads
        }
    }
    ```

### Examples

1.  **Change the group of a single script:**

    *   **Ant XML:**
        ```xml
        <chgrp file="${dist}/start.sh" group="coders"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.chgrp(file: "${ant.project.getProperty("dist")}/start.sh", group: "coders")
        ```

2.  **Change the group of all `.sh` files in a directory:**

    *   **Ant XML:**
        ```xml
        <chgrp group="sysops">
            <fileset dir="${scripts.dir}" includes="**/*.sh"/>
        </chgrp>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.chgrp(group: "sysops") {
            fileset(dir: ant.project.getProperty("scripts.dir"), includes: "**/*.sh")
        }
        ```

3.  **Change group for files and specific directories, verbose output:**

    *   **Ant XML:**
        ```xml
        <chgrp group="appgroup" type="both" verbose="true">
            <fileset dir="/opt/app/bin" includes="*.jar"/>
            <dirset dir="/opt/app/logs"/>
        </chgrp>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.chgrp(group: "appgroup", type: "both", verbose: true) {
            fileset(dir: "/opt/app/bin", includes: "*.jar")
            dirset(dir: "/opt/app/logs")
        }
        ```

### Important Considerations

*   **Platform Dependency**: This task is primarily effective on Unix and Unix-like systems where the `chgrp` command is available and meaningful.
*   **Permissions**: The user running the Ant script must have the necessary permissions to change the group of the specified files/directories.
*   **External Command**: Ant typically executes the system `chgrp` command. Ensure it is in the PATH.
*   **`osfamily` and `os`**: Use these attributes if you need to control execution on specific operating systems, especially if you have a `chgrp`-compatible tool on a non-default OS family.

### Navigation

*   [Previous Task: Checksum Task](Checksum_Task_Groovy.md)
*   [Next Task: Chmod Task](Chmod_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
