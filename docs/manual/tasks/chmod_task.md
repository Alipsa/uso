## Chmod Task (Groovy DSL)

### Description

The `chmod` task is used to change the permissions (mode) of a file or a collection of files and directories. This task is primarily effective on Unix-like operating systems and NonStop Kernel. The permission format is similar to the Unix `chmod` command (e.g., "755", "ugo+rx").

In Groovy AntBuilder, the `chmod` task is invoked as `ant.chmod(...)`. Attributes for specifying the file/resource, the permissions, and other options are passed as named parameters. Nested resource collections like filesets are defined within a closure.

### Parameters

Common Ant attributes for the `chmod` task and their Groovy AntBuilder DSL mapping:

*   `file`: The specific file or single directory whose permissions are to be changed. Required if `dir` or nested resource collections are not used.
    *   Groovy: `file: "/path/to/your/script.sh"`
*   `dir`: The directory containing files/subdirectories whose permissions are to be changed. If `includes` is not specified, it might only affect the directory itself (see Ant manual for specifics on recursion).
    *   Groovy: `dir: "/opt/app/config"`
*   `perm`: The new permissions string (e.g., "755", "u+x,go-w"). (Required)
    *   Groovy: `perm: "755"` or `perm: "u+x,go=r"`
*   `includes`: Comma- or space-separated list of patterns for files to include when `dir` is specified.
    *   Groovy: `includes: "**/*.sh, **/*.py"`
*   `excludes`: Comma- or space-separated list of patterns for files to exclude.
    *   Groovy: `excludes: "**/*.bak, **/.git/**"`
*   `defaultexcludes`: (Boolean) Whether to use Ant's default excludes. Defaults to `true`.
    *   Groovy: `defaultexcludes: false`
*   `parallel`: (Boolean) Whether to process all files using a single underlying `chmod` command. Defaults to `true`.
    *   Groovy: `parallel: false`
*   `type`: Specifies what to operate on: "file", "dir", or "both". Defaults to "file".
    *   Groovy: `type: "both"`
*   `maxparallel`: Limits the number of files passed to a single `chmod` command invocation. Useful for very long file lists.
    *   Groovy: `maxparallel: 200`
*   `verbose`: (Boolean) If `true`, prints a summary. Defaults to `false`.
    *   Groovy: `verbose: true`
*   `os`: Comma-separated list of OSes on which this task should run.
*   `osfamily`: OS family (e.g., "unix"). Defaults to "unix".

### Nested Elements

*   **fileset**: To specify a collection of files.
*   **dirset**: To specify a collection of directories.
*   **filelist**: To specify a list of files.
*   **Resource Collections**: Generic resource collections.

    ```groovy
    ant.chmod(perm: "644") {
        fileset(dir: "/var/www/html/assets") {
            include(name: "**/*.css")
            include(name: "**/*.js")
        }
        dirset(dir: "/var/www/html/cache") {
            include(name: "**")
        }
    }
    ```

### Examples

1.  **Make a script executable for the owner, readable for group/others:**

    *   **Ant XML:**
        ```xml
        <chmod file="${dist}/start.sh" perm="744"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.chmod(file: "${ant.project.getProperty("dist")}/start.sh", perm: "744")
        ```

2.  **Make all `.sh` files in a directory executable for everyone:**

    *   **Ant XML:**
        ```xml
        <chmod dir="${dist}/bin" perm="a+x" includes="**/*.sh"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.chmod(dir: "${ant.project.getProperty("dist")}/bin", perm: "a+x", includes: "**/*.sh")
        ```

3.  **Using a fileset to change permissions:**

    *   **Ant XML:**
        ```xml
        <chmod perm="g+w">
            <fileset dir="shared/sources1">
                <exclude name="**/*.readonly"/>
            </fileset>
        </chmod>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.chmod(perm: "g+w") {
            fileset(dir: "shared/sources1") {
                exclude(name: "**/*.readonly")
            }
        }
        ```

4.  **Setting permissions for files and directories differently using `type`:**

    *   **Ant XML:**
        ```xml
        <chmod perm="755" type="dir">
            <fileset dir="deploy">
                <include name="**/*"/>
            </fileset>
        </chmod>
        <chmod perm="644" type="file">
            <fileset dir="deploy">
                <include name="**/*"/>
            </fileset>
        </chmod>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        // Set directories to 755
        ant.chmod(perm: "755", type: "dir") {
            fileset(dir: "deploy") {
                include(name: "**/*")
            }
        }
        // Set files to 644
        ant.chmod(perm: "644", type: "file") {
            fileset(dir: "deploy") {
                include(name: "**/*")
            }
        }
        ```

### Important Considerations

*   **Platform Dependency**: This task is primarily effective on Unix and Unix-like systems. For a platform-independent alternative, Ant provides the `setpermissions` task (though with a more limited feature set).
*   **Permissions**: The user running the Ant script must have the necessary permissions to change the mode of the specified files/directories.
*   **External Command**: Ant typically executes the system `chmod` command. Ensure it is in the PATH.
*   **Symbolic vs. Octal**: The `perm` attribute accepts both symbolic (e.g., `u+x,go-w`) and octal (e.g., `755`) permission notations.

### Navigation

*   [Previous Task: Chgrp Task](Chgrp_Task_Groovy.md)
*   [Next Task: Chown Task](Chown_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
