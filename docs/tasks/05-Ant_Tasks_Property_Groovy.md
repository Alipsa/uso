## Ant Tasks: Property (Groovy DSL)

The `property` task in Ant is used to set properties. These properties can then be used throughout your build script. In Groovy AntBuilder, you can set properties in several ways, often directly using Groovy variables or the `ant.property` task for more complex scenarios like loading from a file.

### Setting a Simple Property

*   **Ant XML:**
    ```xml
    <project name="MyProject" default="main" basedir=".">
        <property name="project.name" value="MyAwesomeProject"/>
        <target name="main">
            <echo message="The project name is ${project.name}"/>
        </target>
    </project>
    ```
*   **Groovy AntBuilder:**
    In Groovy, you can often just define a Groovy variable. If you need it to be an Ant property accessible via `${...}` syntax within other Ant tasks called by AntBuilder (like `echo`), you would use `ant.property` to set it. **Crucially, when you want Ant to expand the property (e.g., `${project.name}`), the string containing this expression must be passed to the Ant task using single quotes in Groovy. This prevents Groovy from trying to interpolate it as a GString.**

    ```groovy
    import groovy.ant.AntBuilder
    // build.groovy
    ant = new AntBuilder()

    // Setting as an Ant property
    ant.property(name: "project.name", value: "MyAwesomeProject")

    // Using the property in an Ant task: Ant's echo task expands ${...} syntax.
    // Note the use of SINGLE QUOTES for the message string to ensure Ant handles the expansion.
    ant.echo(message: 'The project name is ${project.name}')

    // If you need to access the property value within Groovy code itself (e.g., for logic, or to build a GString):
    def groovyFetchedProjectName = ant.project.getProperty("project.name")
    ant.echo(message: "Fetched in Groovy: $groovyFetchedProjectName") // Double quotes for GString interpolation

    // Alternatively, using Groovy variables directly if not strictly needing Ant's property expansion for all tasks
    def myGroovyProjectName = "MyGroovyProject"
    ant.echo(message: "The Groovy variable project name is $myGroovyProjectName") // Double quotes for GString interpolation
    ```
    *Note: When passing a string to an Ant task (like `echo`) that supports property expansion, and you want Ant to perform the expansion, use single quotes (`'...'`) for the string in Groovy. If you use double quotes (`"..."`), Groovy will attempt GString interpolation first. If you need to retrieve the property's value for use in Groovy logic *before* passing it to an Ant task, or for tasks that don't do their own expansion, use `ant.project.getProperty("propertyName")`.* 

### Loading Properties from a File

*   **Ant XML:**
    ```xml
    <property file="build.properties"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.property(file: "build.properties")
    // Properties from build.properties are now available
    // e.g., if build.properties contains 'version=1.0'
    // The echo task will expand this property. Use single quotes for Ant expansion:
    ant.echo(message: 'Version: ${version}')

    // To access it in Groovy code:
    def fileVersion = ant.project.getProperty("version")
    ant.echo(message: "Version (fetched by Groovy): $fileVersion") // Double quotes for GString interpolation
    ```

### Setting Environment Variables as Properties

*   **Ant XML:**
    ```xml
    <property environment="env"/>
    <echo message="Your home directory is ${env.HOME}"/>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.property(environment: "env")
    // The echo task will expand this property (e.g., env.HOME). Use single quotes for Ant expansion:
    ant.echo(message: 'Your home directory is ${env.HOME}')

    // To access it in Groovy code:
    def envHome = ant.project.getProperty("env.HOME")
    ant.echo(message: "Home directory (fetched by Groovy): $envHome") // Double quotes for GString interpolation

    // Groovy can also access environment variables directly
    def directHomeDir = System.getenv("HOME")
    ant.echo(message: "Groovy direct access to HOME: $directHomeDir") // Double quotes for GString interpolation
    ```

This demonstrates how the `property` task and its common uses can be translated to Groovy AntBuilder. Remember the distinction between Ant's automatic property expansion in tasks (requiring single-quoted strings in Groovy for the `${...}` syntax to pass through to Ant) and explicitly fetching property values in your Groovy code using `ant.project.getProperty()` (which can then be used in GStrings with double quotes).

### Navigation

*   [Previous: Using Apache Ant with Groovy](03-Using_Apache_Ant_Groovy.md)
*   [Next: Javac Task](06-Ant_Tasks_Javac_Groovy.md)
*   [Table of Contents](00-Introduction_Groovy_Ant_Manual.md)
