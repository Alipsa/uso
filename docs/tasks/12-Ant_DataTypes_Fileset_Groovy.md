## Ant Data Types: Fileset (Groovy DSL)

A `fileset` is one of the most commonly used data types in Ant. It represents a collection of files, typically defined by a base directory and include/exclude patterns. Filesets are used by many Ant tasks (like `copy`, `delete`, `jar`, `javac`) to specify which files the task should operate on. Groovy AntBuilder allows you to define filesets in a very similar and intuitive way.

### Basic Fileset Definition

In Groovy AntBuilder, a `fileset` is typically defined as a nested closure within a task that accepts it.

*   **Ant XML (within a `copy` task):**
    ```xml
    <copy todir="backup">
        <fileset dir="src">
            <include name="**/*.java"/>
            <exclude name="**/*Test.java"/>
        </fileset>
    </copy>
    ```
*   **Groovy AntBuilder (within a `copy` task):**
    ```groovy
    ant.copy(todir: "backup") {
        fileset(dir: "src") {
            include(name: "**/*.java")
            exclude(name: "**/*Test.java")
        }
    }
    ```

### Common Attributes of Fileset

*   `dir`: The base directory from which to select files. (Required)
*   `includes`: A comma-separated list of patterns for files to include. If not specified, defaults to `**` (all files).
*   `excludes`: A comma-separated list of patterns for files to exclude.
*   `casesensitive`: Whether pattern matching should be case sensitive (defaults to `true`).
*   `defaultexcludes`: Whether to use Ant's default excludes (e.g., `**/*~`, `**/#*#`, etc.). Defaults to `true`.

*   **Ant XML (demonstrating attributes):**
    ```xml
    <fileset dir="config" casesensitive="false" defaultexcludes="no">
        <include name="*.properties"/>
        <exclude name="user.properties"/>
    </fileset>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    // Example of using fileset attributes directly (e.g., if passing to a custom task or method)
    // More commonly, these are nested as shown in the copy task example.
    ant.echo("Processing configuration files...") // Placeholder for a task that would use a fileset
    // Conceptual use:
    // someTask {
    //     fileset(dir: "config", casesensitive: false, defaultexcludes: false) {
    //         include(name: "*.properties")
    //         exclude(name: "user.properties")
    //     }
    // }
    ```
    *Note: The direct invocation of `fileset` outside a task that consumes it is less common. It's usually nested.* 

### Using Multiple Include/Exclude Patterns

You can specify multiple `include` and `exclude` elements, or provide comma-separated lists to their `name` attributes.

*   **Ant XML:**
    ```xml
    <fileset dir="src/main/java">
        <include name="com/example/core/**"/>
        <include name="com/example/utils/**"/>
        <exclude name="**/old/**"/>
        <exclude name="**/*IGNORE*"/>
    </fileset>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    // Within a task like copy or zip:
    // ant.copy(todir: '...') {
    //     fileset(dir: "src/main/java") {
    //         include(name: "com/example/core/**")
    //         include(name: "com/example/utils/**")
    //         exclude(name: "**/old/**")
    //         exclude(name: "**/*IGNORE*")
    //     }
    // }
    // Alternatively, using comma-separated values:
    // ant.copy(todir: '...') {
    //     fileset(dir: "src/main/java", 
    //             includes: "com/example/core/**,com/example/utils/**",
    //             excludes: "**/old/**,**/*IGNORE*")
    // }
    ```

### Fileset by Reference

Ant allows defining a fileset with an `id` and then referencing it. This is less common in Groovy AntBuilder as you can assign the fileset configuration to a Groovy variable if needed, or define it directly where used.

*   **Ant XML:**
    ```xml
    <fileset id="common.sources" dir="src">
        <include name="**/*.java"/>
    </fileset>

    <javac destdir="classes">
        <src>
            <fileset refid="common.sources"/>
        </src>
    </javac>
    ```
*   **Groovy AntBuilder (Conceptual Equivalent):**
    While direct `refid` isn't the primary Groovy way, you can achieve reusability with Groovy closures or methods.

    ```groovy
    // Define a closure that configures a fileset
    def commonSources = {
        fileset(dir: "src") {
            include(name: "**/*.java")
        }
    }

    // Use it in a task
    ant.javac(destdir: "classes") {
        src {
            commonSources.delegate = delegate // Set the delegate for the closure
            commonSources()
        }
    }
    ```
    A more direct approach is often to just inline the fileset definition if it's not overly complex or reused extensively across many disparate tasks.

Filesets are fundamental to Ant's power in file manipulation, and Groovy AntBuilder provides a natural and expressive way to define them within your build scripts.

### Navigation

*   [Previous: Exec Task](11-Ant_Tasks_Exec_Groovy.md)
*   [Next: Appendix A - Mapping](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
*   [Table of Contents](00-Introduction_Groovy_Ant_Manual.md)
