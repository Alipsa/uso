## CvsChangeLog Task (Groovy DSL)

### Description

The `cvschangelog` task in Ant is used to generate an XML-formatted report of commit comments from a CVS (Concurrent Versions System) repository. This can be useful for tracking changes between builds or releases.

When using Groovy with AntBuilder, you would typically execute this as an external Ant process if you need to generate this report, as direct Groovy bindings for every Ant task (especially those less commonly used or relying on specific external tools like CVS) might not be available or practical within a pure Groovy script for build automation. However, if AntBuilder itself provides a direct way to invoke such tasks, that would be the preferred method.

For the purpose of this documentation, we will assume you are invoking it as if it's a standard Ant task available in a Groovy context. If direct invocation isn't supported, you would typically use Groovy's `execute` method to run the `ant` command-line tool with an appropriate `build.xml` file configured for the `cvschangelog` task.

### Parameters

Assuming `cvschangelog` can be called via AntBuilder, here are common parameters based on the Ant task's XML definition:

*   `destfile`: The path and filename for the XML output file where the changelog will be written. (Required)
    *   Groovy: `destfile: 'reports/cvs_changelog.xml'`
*   `dir`: The directory containing the CVS working copy. (Required)
    *   Groovy: `dir: './my-cvs-project'`
*   `start`: A date or tag specifying the start of the period for which to fetch log entries.
    *   Groovy: `start: '2023-01-01'`
*   `end`: A date or tag specifying the end of the period.
    *   Groovy: `end: '2023-12-31'`
*   `users`: A comma-separated list of CVS users to include. If not specified, all users are included.
    *   Groovy: `users: 'johndoe,janedoe'`
*   `tag`: If you want to get changes based on a specific CVS tag.
    *   Groovy: `tag: 'RELEASE_1_0'`
*   `passfile`: The file containing CVSROOT, username, and password information. Defaults to `~/.cvspass`.
    *   Groovy: `passfile: '/path/to/mypassfile'`
*   `failonerror`: (Boolean) Whether the build should fail if CVS encounters an error. Defaults to `true`.
    *   Groovy: `failonerror: false`

### Nested Elements

The `cvschangelog` task can have nested `<user>` elements to map CVS user IDs to display names in the generated report.

*   **user**: Defines a user mapping.
    *   `cvsuser`: The CVS user ID.
    *   `displayname`: The name to display in the report.

### Examples

1.  **Generate a changelog for a specific date range:**

    *   **Ant XML (for context):**
        ```xml
        <cvschangelog destfile="changelog.xml" dir="${basedir}"
                      start="2023-05-01" end="2023-05-10"/>
        ```
    *   **Groovy AntBuilder (Conceptual - direct equivalent might not exist for all Ant tasks):**
        ```groovy
        // Assuming a hypothetical direct mapping or an external execution setup
        ant.cvschangelog(
            destfile: 'changelog.xml',
            dir: '.', // Current directory as the CVS working copy
            start: '2023-05-01',
            end: '2023-05-10'
        )
        ```

2.  **Generate a changelog including user display names:**

    *   **Ant XML (for context):**
        ```xml
        <cvschangelog destfile="changelog_users.xml" dir=".">
            <user cvsuser="brucew" displayname="Bruce Wayne"/>
            <user cvsuser="ckent" displayname="Clark Kent"/>
        </cvschangelog>
        ```
    *   **Groovy AntBuilder (Conceptual):**
        ```groovy
        ant.cvschangelog(destfile: 'changelog_users.xml', dir: '.') {
            user(cvsuser: 'brucew', displayname: 'Bruce Wayne')
            user(cvsuser: 'ckent', displayname: 'Clark Kent')
        }
        ```

### Important Considerations

*   **CVS Availability**: The CVS command-line client must be installed and in the system's PATH for this task to work, as Ant typically shells out to the `cvs` command.
*   **Repository Access**: Ensure that the machine running the Ant script has access to the CVS repository and that any necessary authentication (like via `passfile` or SSH keys if applicable) is set up.
*   **Date/Tag Specificity**: Be precise with dates or tags to ensure you are pulling the correct range of log entries.
*   **Alternative for Non-Direct Groovy Support**: If direct Groovy AntBuilder support for `cvschangelog` is not available or suitable, you would typically use Groovy's `execute` method to run the `ant` command with a small, dedicated `build.xml` file that performs the `cvschangelog` operation. The results (like the generated XML file) can then be processed by the Groovy script.

    ```groovy
    // Example of using GDK's execute for external Ant (if direct binding isn't there)
    // def antCommand = "ant -f build_changelog.xml"
    // def proc = antCommand.execute(null, new File(".")) // Assuming build_changelog.xml is in CWD
    // proc.waitFor()
    // println "Standard Output: ${proc.in.text}"
    // println "Error Output: ${proc.err.text}"
    ```
    You would need to create a `build_changelog.xml` file for this approach.

### Navigation

*   [Previous Task: Copy Task](Copy_Task_Groovy.md)
*   [Next Task: Delete Task](Delete_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
