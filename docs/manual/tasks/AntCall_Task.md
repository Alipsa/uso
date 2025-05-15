## AntCall Task (Groovy DSL)

### Description

The `antcall` task is used to call another target within the *same* buildfile. It can optionally pass properties (referred to as `param` in this context) to the called target. This is useful for modularizing build logic and reusing sequences of tasks.

**Important Note from Ant Manual:** This task must not be used outside of a `target`.

In Groovy AntBuilder, the `antcall` task is invoked as `ant.antcall(...)`. Its attributes are passed as named parameters, and nested elements like `param`, `reference`, and `target` are defined within a closure.

### Parameters

Common Ant attributes for the `antcall` task and their Groovy AntBuilder DSL mapping:

*   `target`: The target to execute within the current buildfile. (Required) Groovy: `target: 'otherTarget'`
*   `inheritAll`: If `true` (default), pass all properties of the current project to the called target. Groovy: `inheritAll: false`
*   `inheritRefs`: If `true` (default is `false`), pass all references to the called target. Groovy: `inheritRefs: true`

### Nested Elements

*   **param**: Specifies properties to set before running the specified target. These are like command-line properties for the called target.
    ```groovy
    ant.antcall(target: 'deployStaging') {
        param(name: 'server.ip', value: '192.168.1.100')
        param(name: 'deploy.user', value: 'staging_user')
    }
    ```
*   **reference**: Copies references from the calling project to the new project context for the called target, optionally under a different ID.
    ```groovy
    ant.antcall(target: 'processFiles') {
        reference(refid: 'input.files.path', torefid: 'processing.path')
    }
    ```
*   **propertyset**: (Since Ant 1.6) Specifies a set of properties to be copied into the new project context for the called target.
    ```groovy
    ant.antcall(target: 'configureModule') {
        propertyset {
            propertyref(prefix: 'module.specific.config')
            // other propertyset nested elements
        }
    }
    ```
*   **target**: (Since Ant 1.6.3) Specifies multiple targets to be executed in order, as if they were dependencies of a single invoked target.
    ```groovy
    ant.antcall {
        target(name: 'cleanTemp')
        target(name: 'generateSources')
        // Note: The main target for antcall itself would still be specified via the 'target' attribute
        // This nested <target> is for specifying multiple *called* targets if the main 'target' attribute is omitted
        // and the antcall is meant to orchestrate several targets. More commonly, a single target is specified.
        // The Ant manual implies these nested targets are executed as if Ant had been invoked with a single target
        // whose dependencies are the targets so specified, in the order specified. If a primary target is also
        // specified via the attribute, the behavior might be specific to Ant versions.
        // For clarity, usually one uses the `target` attribute for the main call.
    }
    // More typical usage for a single target call:
    // ant.antcall(target: 'mainTargetToCall') { ... params ... }
    ```

### Examples

1.  **Calling another target with parameters:**

    *   **Ant XML:**
        ```xml
        <target name="main">
            <antcall target="compileModule">
                <param name="module.name" value="core"/>
                <param name="debug.mode" value="true"/>
            </antcall>
        </target>

        <target name="compileModule">
            <echo message="Compiling module: ${module.name}, Debug: ${debug.mode}"/>
            <!-- ... compilation tasks ... -->
        </target>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        // Define targets (conceptual, as Groovy doesn't have direct <target> tags like XML)
        // This would typically be part of a larger script where targets are methods or closures.

        // Invoking antcall within a conceptual target/method:
        // def main = {
        ant.antcall(target: 'compileModule') { // Assuming 'compileModule' is another target/method in scope
            param(name: 'module.name', value: 'core')
            param(name: 'debug.mode', value: 'true')
        }
        //}

        // def compileModule = { ant -> // 'ant' instance would be available
        //    def moduleName = ant.project.getProperty('module.name')
        //    def debugMode = ant.project.getProperty('debug.mode')
        //    ant.echo(message: "Compiling module: $moduleName, Debug: $debugMode")
        //    // ... compilation tasks ...
        // }
        ```
        *Note: In a pure Groovy script using AntBuilder, targets are often represented by methods or closures. The `antcall` task would then invoke these by their name as registered or understood by the Ant project instance manipulated by AntBuilder.* 
        *The properties passed via `<param>` are set in a new Ant project scope for the called target.* 

2.  **Calling a target and inheriting references:**

    *   **Ant XML:**
        ```xml
        <path id="common.libs">
            <fileset dir="lib" includes="*.jar"/>
        </path>

        <target name="runTests">
            <antcall target="executeTests" inheritRefs="true"/>
        </target>

        <target name="executeTests">
            <!-- common.libs path is available here -->
            <echo message="Classpath for tests: ${toString:common.libs}"/>
            <!-- ... junit task using common.libs ... -->
        </target>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        // Define the path reference
        ant.path(id: 'common.libs') {
            fileset(dir: 'lib', includes: '*.jar')
        }

        // Call the target, inheriting references
        // def runTests = {
        ant.antcall(target: 'executeTests', inheritRefs: true)
        // }

        // def executeTests = { ant ->
        //    def classPathString = ant.project.getReference('common.libs').toString()
        //    ant.echo(message: "Classpath for tests: $classPathString")
        //    // ... junit task using the reference ...
        // }
        ```

### Important Considerations

*   The called target runs in a new Ant project context. Properties or references set within the called target do not automatically propagate back to the calling project/target.
*   `inheritRefs` has specific behavior with `antcall` because the build file is the same. It won't override references already defined by ID in the build file. It's mainly for references created dynamically by tasks.

### Navigation

*   [Previous Task: ANTLR Task](ANTLR_Task_Groovy.md)
*   [Next Task: AntStructure Task](AntStructure_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
