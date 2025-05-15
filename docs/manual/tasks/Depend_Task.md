## Depend Task (Groovy DSL)

### Description

The `depend` task in Ant is used to manage Java class file dependencies. It identifies which compiled class files are out of date with respect to their source files and then removes the class files of any other classes that depend on these out-of-date classes. This triggers their recompilation in a subsequent `javac` task. It analyzes class file bytecode to determine dependencies, not source code.

In Groovy AntBuilder, the `depend` task is invoked as `ant.depend(...)`. It helps in optimizing build times by ensuring only necessary classes are recompiled.

### Parameters

Common Ant attributes for the `depend` task and their Groovy AntBuilder DSL mapping:

*   `srcdir`: (Required) The directory containing the Java source files. This is used to determine if class files are out of date. Can be a path-like structure if multiple source directories are used.
    *   Groovy: `srcdir: "path/to/source_java_files"` or `srcdir: "src/main/java:src/generated/java"`
*   `destdir`: The root directory of the compiled class files that will be analyzed and potentially deleted. Defaults to `srcdir` if not specified.
    *   Groovy: `destdir: "build/classes"`
*   `cache`: A directory where `depend` can store and retrieve dependency information to speed up subsequent runs. If not specified, no caching is performed.
    *   Groovy: `cache: "build/depcache"`
*   `closure`: (Boolean) If `true`, `depend` will traverse the entire class dependency graph, removing all classes transitively affected by an out-of-date class. If `false` (the default), only directly dependent classes are removed.
    *   Groovy: `closure: true`
*   `dump`: (Boolean) If `true`, dependency information is written to the debug log. Defaults to `false`.
    *   Groovy: `dump: true`
*   `classpath`: An optional classpath containing JARs and classes against which dependencies should also be checked. Classes depending on an older element from this classpath will be deleted.
    *   Groovy: `classpath: "libs/some_utility.jar"` (or use nested classpath)
*   `warnOnRmiStubs`: (Boolean) If `true` (the default), warnings are issued for files that appear to be RMI stubs/skeletons without corresponding `.java` source. Set to `false` to suppress these warnings during RMI development.
    *   Groovy: `warnOnRmiStubs: false`

This task also implicitly supports nested `<include>`, `<exclude>`, and `<patternset>` elements like a fileset, operating on the `srcdir`.

### Nested Elements

*   **`classpath`**: A standard Ant path structure can be used to specify the classpath.
    ```groovy
    ant.depend(srcdir: "src", destdir: "classes", cache: "depcache") {
        classpath {
            pathelement(location: "libs/core.jar")
            fileset(dir: "common_libs", includes: "**/*.jar")
        }
        include(name: "com/mypackage/**/*.java")
    }
    ```
*   **`include` / `exclude` / `patternset`**: To refine the set of source files considered for dependency checking (relative to `srcdir`).

### Examples

1.  **Basic dependency check and removal:**
    Remove any classes in `build/classes` that depend on out-of-date classes from `src/java`, caching dependency info in `build/depcache`, and using closure.

    *   **Ant XML:**
        ```xml
        <depend srcdir="src/java"
                destdir="build/classes"
                cache="build/depcache"
                closure="yes"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.depend(srcdir: "src/java",
                   destdir: "build/classes",
                   cache: "build/depcache",
                   closure: true)
        ```

2.  **Dependency check with include/exclude patterns:**
    Same as above, but explicitly include all `.java` files and exclude files listed in `src/java/build_excludes`.

    *   **Ant XML:**
        ```xml
        <depend srcdir="src/java" destdir="build/classes"
                cache="build/depcache" closure="yes">
          <include name="**/*.java"/>
          <excludesfile name="src/java/build_excludes"/>
        </depend>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.depend(srcdir: "src/java", destdir: "build/classes",
                   cache: "build/depcache", closure: true) {
            include(name: "**/*.java")
            excludesfile(name: "src/java/build_excludes")
        }
        ```

### Important Considerations

*   **Performance**: For very large projects or highly interconnected classes, `depend` can be slow. It might be faster to simply recompile all classes. Its effectiveness depends on the project structure.
*   **Limitations**: `depend` relies on class file bytecode. It won't detect dependencies if the compiler optimizes them away, or dependencies related to changes in constant primitive data types (e.g., `public final static boolean DEBUG=false;`). Non-public classes can also pose challenges for accurate dependency tracking.
*   **Usage with `javac`**: The `depend` task is typically used *before* a `javac` task in a build sequence. `depend` removes outdated dependent class files, and then `javac` recompiles the missing classes (both the directly outdated ones and those whose class files were removed by `depend`).

### Navigation

*   [Previous Task: Deltree Task](Deltree_Task_Groovy.md)
*   [Next Task: Dependset Task](Dependset_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)

