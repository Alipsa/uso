## Using Apache Ant with Groovy DSL

This section explains how to use Apache Ant with Groovy DSL. Instead of XML build files, you can now leverage the power and conciseness of Groovy scripts.

For example, a simple task execution would look like this in your Groovy script:

```groovy
// build.groovy

ant = new AntBuilder() // Creates an AntBuilder instance

// Example: Echoing a message
ant.echo(message: "Compiling the project...")

// Example: Compiling Java source files
ant.javac(srcdir: 'src', destdir: 'build/classes')

// Example: Creating a JAR file
ant.echo(message: "Creating the JAR...")
ant.jar(destfile: 'build/myproject.jar', basedir: 'build/classes')

ant.echo(message: "Build finished.")
```

To run this, you would typically use Groovy to execute the script.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [Next: Ant Tasks - Property](05-Ant_Tasks_Property_Groovy.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
