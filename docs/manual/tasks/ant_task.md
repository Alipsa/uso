## Ant Task (Groovy DSL)

### Description

The `ant` task is used to run Apache Ant on a supplied buildfile, often to build subprojects. It allows you to invoke another Ant build process from your current build.

In Groovy AntBuilder, the `ant` task is invoked as `ant.ant(...)`, with its attributes passed as named parameters and nested elements like `property`, `reference`, and `target` defined within a closure.

**Important Note from Ant Manual:** This task must not be used outside of a `target` if it invokes the same build file it is part of.

Note: in an uso build you use the [`uso`](uso_task.md) task to execute another build (as the equivalent to the `ant` task)

### Parameters

Common Ant attributes for the `ant` task and their Groovy AntBuilder DSL mapping:

*   `antfile`: The buildfile to use (relative to `dir`). Groovy: `antfile: 'subbuild.xml'`
*   `dir`: The base directory for the new Ant project. Groovy: `dir: 'subproject'`
*   `target`: The target to execute in the new project. Groovy: `target: 'compile'`
*   `output`: Filename to write the Ant output to. Groovy: `output: 'subbuild.log'`
*   `inheritAll`: If `true` (default), pass all properties to the new project. Groovy: `inheritAll: false`
*   `inheritRefs`: If `true` (default is `false`), pass all references to the new project. Groovy: `inheritRefs: true`
*   `useNativeBasedir`: If `true`, the child build uses its own defined basedir. Groovy: `useNativeBasedir: true`

### Nested Elements

*   **property**: Sets properties in the new project. Can be defined with `name` and `value`, or `file`, etc.
    ```groovy
    ant.ant(antfile: 'other.xml') {
        property(name: 'newProp', value: 'newValue')
        property(file: 'common.properties')
    }
    ```
*   **reference**: Copies references from the calling project to the new project.
    ```groovy
    ant.ant(antfile: 'other.xml') {
        reference(refid: 'original.path', torefid: 'copied.path')
    }
    ```
*   **propertyset**: Specifies a set of properties to be copied.
    ```groovy
    ant.ant(antfile: 'other.xml') {
        propertyset {
            propertyref(prefix: 'original.prefix')
            // other propertyset nested elements
        }
    }
    ```
*   **target**: Specifies one or more targets to be executed in order.
    ```groovy
    ant.ant(dir: 'subproject') {
        target(name: 'clean')
        target(name: 'compile')
    }
    ```

### Examples

1.  **Running a target in a different build file with properties:**

    *   **Ant XML:**
        ```xml
        <ant antfile="subproject/subbuild.xml" dir="subproject" target="deploy">
            <property name="deploy.env" value="production"/>
            <property name="version" value="1.5"/>
        </ant>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ant(antfile: 'subproject/subbuild.xml', dir: 'subproject', target: 'deploy') {
            property(name: 'deploy.env', value: 'production')
            property(name: 'version', value: '1.5')
        }
        ```

2.  **Running multiple targets in a build file in the same directory:**

    *   **Ant XML:**
        ```xml
        <ant antfile="common-build.xml">
            <target name="prepare-resources"/>
            <target name="compile-module-a"/>
        </ant>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ant(antfile: 'common-build.xml') {
            target(name: 'prepare-resources')
            target(name: 'compile-module-a')
        }
        ```

3.  **Inheriting references:**

    *   **Ant XML:**
        ```xml
        <path id="global.classpath">
            <pathelement location="lib/common.jar"/>
        </path>

        <ant antfile="module.xml" inheritRefs="true"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        // Assuming global.classpath is already defined in the Ant project instance
        // ant.path(id: 'global.classpath') {
        //     pathelement(location: 'lib/common.jar')
        // }

        ant.ant(antfile: 'module.xml', inheritRefs: true)
        ```

### Navigation

*   [Previous Task: AntVersion Task](AntVersion_Task_Groovy.md)
*   [Next Task: Apply/ExecOn Task](ApplyExecOn_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
