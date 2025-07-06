## Cab Task (Groovy DSL)

### Description

The `cab` task is used to create Microsoft Cabinet (.cab) archive files. It functions similarly to Ant's `jar` or `zip` tasks but is specific to the CAB format. This task relies on an external tool: `cabarc.exe` (from Microsoft) on Windows, or a compiled `libcabinet` on other platforms.

In Groovy AntBuilder, the `cab` task is invoked as `ant.cab(...)`. Attributes like the destination CAB file and the base directory for files are passed as named parameters. Nested filesets or include/exclude patterns are defined within a closure.

### Parameters

Common Ant attributes for the `cab` task and their Groovy AntBuilder DSL mapping:

*   `cabfile`: The name of the CAB file to be created. (Required)
    *   Groovy: `cabfile: "output/myarchive.cab"`
*   `basedir`: The directory from which to archive files. This is used when not specifying a nested fileset.
    *   Groovy: `basedir: "src/main/resources"`
*   `compress`: (Boolean) Whether to compress files within the CAB archive. Defaults to `true` (yes).
    *   Groovy: `compress: false` (to store files uncompressed)
*   `verbose`: (Boolean) If `true` (yes), displays output from the underlying `cabarc` tool. Defaults to `false` (no).
    *   Groovy: `verbose: true`
*   `options`: (String) Allows passing additional command-line options directly to the `cabarc` tool. Use with caution.
    *   Groovy: `options: "-s 6144"` (Example: set cabinet reserve size)
*   `includes`: Comma- or space-separated list of patterns for files to include (e.g., `"**/*.txt,images/*.jpg"`).
    *   Groovy: `includes: "**/*.dll, **/*.exe"`
*   `includesfile`: Name of a file where each line is an include pattern.
    *   Groovy: `includesfile: "conf/cab_includes.txt"`
*   `excludes`: Comma- or space-separated list of patterns for files to exclude.
    *   Groovy: `excludes: "**/*.tmp, **/.DS_Store"`
*   `excludesfile`: Name of a file where each line is an exclude pattern.
    *   Groovy: `excludesfile: "conf/cab_excludes.txt"`
*   `defaultexcludes`: (Boolean) Whether to use Ant's default excludes. Defaults to `true` (yes).
    *   Groovy: `defaultexcludes: false`

### Nested Elements

*   **fileset**: A standard Ant `fileset` can be nested to specify the files to be included in the CAB archive. If a nested `fileset` is used, the `basedir`, `includes`, `excludes`, etc., attributes on the `cab` task itself are typically ignored in favor of the `fileset`'s configuration.
    ```groovy
    cab(cabfile: "myapp.cab") {
        fileset(dir: "release_files") {
            include(name: "**/*.exe")
            include(name: "**/*.dll")
            exclude(name: "**/debug/*")
        }
    }
    ```
*   **include**, **exclude**, **patternset**: These can be used directly within the `cab` task closure if not using a full nested `fileset` and relying on the `basedir` attribute of the `cab` task.

### Examples

1.  **Create a CAB file from a directory:**

    ```groovy
    cab(cabfile: "${ant.project.getProperty('dist')}/manual.cab", basedir: "htdocs/manual")
    ```

2.  **Create a CAB file with includes and excludes:**

    ```groovy
    cab(cabfile: "${project.getProperty('dist')}/app.cab",
            basedir: "build/app",
            includes: "**/*.exe, **/*.dll",
            excludes: "**/temp/*, **/*.pdb")
    ```

3.  **Create a CAB file using a nested fileset:**

    ```groovy
    cab(cabfile: "${project.getProperty('dist')}/components.cab") {
        fileset(dir: "build/components") {
            include(name: "ui/**")
            include(name: "core/**")
            exclude(name: "**/*.java")
        }
    }
    ```

### Important Considerations

*   **External Tool Dependency**: The `cab` task's functionality depends on `cabarc.exe` (on Windows) or `libcabinet` (on other systems) being correctly installed and accessible in the system's PATH.
*   **Platform Specificity**: While `libcabinet` aims for cross-platform support, CAB files are predominantly a Microsoft Windows archive format.
*   The task forms an implicit fileset, so attributes like `basedir`, `includes`, `excludes` work as they do for filesets when a nested `<fileset>` is not explicitly provided.

### Navigation

*   [Previous Task: BuildNumber Task](BuildNumber_Task_Groovy.md)
*   [Next Task: Checksum Task](Checksum_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
