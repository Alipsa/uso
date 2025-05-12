## Available Task (Groovy DSL)

### Description

The `available` task sets a property if a specified resource is available at runtime. This resource can be a file, a directory, a class in the classpath, or a JVM system resource. If the resource is found, the property is set (by default to `true`); otherwise, it remains unset.

In Groovy AntBuilder, the `available` task is invoked as `ant.available(...)`. Its attributes are passed as named parameters, and nested elements like `classpath` or `filepath` are defined within a closure.

### Parameters

Common Ant attributes for the `available` task and their Groovy AntBuilder DSL mapping:

*   `property`: The name of the property to set. (Required) Groovy: `property: "myResource.is.present"`
*   `value`: The value to set the property to if the resource is available. Defaults to `"true"`. Groovy: `value: "yes"`
*   `classname`: The fully qualified name of the class to look for in the classpath. (Exactly one of `classname`, `file`, or `resource` is required). Groovy: `classname: "com.example.MyClass"`
*   `file`: The file or directory to look for. Groovy: `file: "path/to/somefile.txt"`
*   `resource`: The name of the resource to look for in the JVM's resource path (e.g., a properties file). Groovy: `resource: "com/example/config.properties"`
*   `type`: When `file` is specified, this can be `"file"` or `"dir"` to check for a specific type. If omitted, it checks for either. Groovy: `type: "dir"`
*   `classpath`: The classpath to use when looking up `classname` or `resource`. Groovy: `classpath: "libs/myjar.jar:otherlibs/*"`
*   `classpathref`: A reference to a classpath defined elsewhere. Groovy: `classpathref: "my.defined.classpath"`
*   `filepath`: The path to use when looking up `file`. Groovy: `filepath: "/usr/local/data"`
*   `ignoresystemclasses`: If `true`, ignore Ant's runtime classes when checking for `classname`, using only the specified classpath. Groovy: `ignoresystemclasses: true`
*   `searchparents`: If `true` (default is `false`), when searching for a `file`, search parent directories as well. Groovy: `searchparents: true`

### Nested Elements

*   **classpath**: Defines the classpath for `classname` or `resource` checks.
    ```groovy
    ant.available(property: "myclass.found", classname: "com.foo.Bar") {
        classpath {
            pathelement(location: "lib/foo.jar")
            fileset(dir: "common_libs", includes: "*.jar")
        }
    }
    ```
*   **filepath**: Defines the search path for the `file` attribute.
    ```groovy
    ant.available(property: "config.exists", file: "settings.ini") {
        filepath {
            pathelement(location: "/etc/myapp")
            pathelement(path: ant.project.getProperty("user.home") + "/.myapp")
        }
    }
    ```

### Examples

1.  **Check if a class is available in the classpath:**

    *   **Ant XML:**
        ```xml
        <available classname="org.apache.commons.logging.LogFactory" property="commons.logging.present"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.available(classname: "org.apache.commons.logging.LogFactory", property: "commons.logging.present")
        // Later, you can check this property:
        // if (ant.project.getProperty("commons.logging.present")) { ... }
        ```

2.  **Check if a specific file exists:**

    *   **Ant XML:**
        ```xml
        <available file="src/main/config/app.properties" property="app.config.exists"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.available(file: "src/main/config/app.properties", property: "app.config.exists")
        ```

3.  **Check if a directory exists and set a custom value:**

    *   **Ant XML:**
        ```xml
        <available file="/opt/tomcat" type="dir" property="tomcat.installed" value="yes"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.available(file: "/opt/tomcat", type: "dir", property: "tomcat.installed", value: "yes")
        ```

4.  **Check for a resource in a specific classpath:**

    *   **Ant XML:**
        ```xml
        <path id="custom.path">
            <pathelement location="lib/custom.jar"/>
        </path>
        <available resource="com/mycorp/licence.key" property="licence.found" classpathref="custom.path"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.path(id: "custom.path") {
            pathelement(location: "lib/custom.jar")
        }
        ant.available(resource: "com/mycorp/licence.key", property: "licence.found", classpathref: "custom.path")
        ```
        Alternatively, with a nested classpath:
        ```groovy
        ant.available(resource: "com/mycorp/licence.key", property: "licence.found") {
            classpath {
                pathelement(location: "lib/custom.jar")
            }
        }
        ```

### Navigation

*   [Previous Task: Augment Task](Augment_Task_Groovy.md)
*   [Next Task: BUnzip2 Task](BUnzip2_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
