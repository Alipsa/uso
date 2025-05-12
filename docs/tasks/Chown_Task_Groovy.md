## Chown Task (Groovy DSL)

### Description

The `chown` task is used to change the owner of a file or all files within specified directories. This task is primarily effective on Unix-like operating systems where file ownership is a key security and operational feature. It mirrors the functionality of the `chown` command-line utility.

In Groovy AntBuilder, the `chown` task is invoked as `ant.chown(...)`. Attributes for specifying the file/resource, the new owner, and other options are passed as named parameters. Nested resource collections like filesets can be defined within a closure.

### Parameters

Common Ant attributes for the `chown` task and their Groovy AntBuilder DSL mapping:

*   `file`: The specific file or directory whose owner is to be changed. Required if no nested resource collection is used.
    *   Groovy: `file: "/path/to/your/file.ext"`
*   `owner`: The new owner's username or UID (User ID) to assign to the file(s). (Required)
    *   Groovy: `owner: "new_owner_username"`
*   `parallel`: (Boolean) Whether to process all specified files using a single underlying `chown` command. Defaults to `true`. Setting to `false` might execute `chown` for each file individually.
    *   Groovy: `parallel: false` (if you want to process files one by one, though less common for this task)
*   `type`: Specifies what to operate on: "file", "dir", or "both". Defaults to "file".
    *   Groovy: `type: "file"`
*   `maxparallel`: Limits the number of files passed to a single `chown` command invocation. Useful for very long file lists. A negative value means unlimited.
    *   Groovy: `maxparallel: 100` (example)
*   `verbose`: (Boolean) If `true`, prints a summary after execution. Defaults to `false`.
    *   Groovy: `verbose: true`
*   `os`: A comma-separated list of operating systems on which this task should run.
*   `osfamily`: OS family (e.g., "unix"). Defaults to "unix".

### Nested Elements

*   **fileset**: To specify a collection of files.
*   **dirset**: To specify a collection of directories.
*   **filelist**: To specify a list of files.
*   **Resource Collections**: Generic resource collections.

    ```groovy
    ant.chown(owner: "newuser") {
        fileset(dir: "/home/olduser/documents") {
            include(name: "**/*.txt")
        }
        // You can add more filesets or other resource collections here
    }
    ```

### Examples

1.  **Change owner of a single file:**

    *   **Ant XML:**
        ```xml
        <chown file="/data/important.dat" owner="admin"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.chown(file: "/data/important.dat", owner: "admin")
        ```

2.  **Change owner of all `.log` files in a directory:**

    *   **Ant XML:**
        ```xml
        <chown owner="logmanager">
            <fileset dir="/var/log/myapp">
                <include name="**/*.log"/>
            </fileset>
        </chown>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.chown(owner: "logmanager") {
            fileset(dir: "/var/log/myapp") {
                include(name: "**/*.log")
            }
        }
        ```

### Important Considerations

*   **Platform Dependency**: This task is primarily designed for Unix and Unix-like systems. Its behavior on other operating systems might be undefined or unsupported.
*   **Permissions**: The user executing the Ant script must have sufficient privileges (often root or sudo access) to change the ownership of the specified files or directories.
*   **External Command**: Ant typically relies on the system's `chown` command. Ensure this command is available and in the system's PATH where the Ant script is executed.

### Navigation

*   [Previous Task: Chmod Task](Chmod_Task_Groovy.md)
*   [Next Task: Componentdef Task](ComponentDef_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
