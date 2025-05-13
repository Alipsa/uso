# Ant Task: JJDoc

## Original Ant Task Description

The `jjdoc` Ant task is designed to invoke the JJDoc preprocessor, which is part of the JavaCC (Java Compiler Compiler) suite. Its primary function is to take a JavaCC parser specification file (typically with a `.jj` extension) and generate documentation for the Backus-Naur Form (BNF) grammar defined within that specification. This documentation can be produced in different formats, commonly HTML or plain text.

To utilize the `jjdoc` task effectively, you need to provide it with the target JavaCC grammar file. Additionally, since JJDoc is part of the JavaCC distribution, the task requires information about the location of the JavaCC installation to access the necessary classes and resources. The output documentation can be directed to a specific file or, by default, will be generated in the same directory as the input grammar file with a `.html` or `.txt` extension.

An important operational detail is that the `jjdoc` task typically only runs if the grammar file has been modified more recently than the existing documentation file, preventing unnecessary regeneration.

## Groovy AntBuilder DSL Equivalent

When working with Groovy and needing to integrate JavaCC documentation generation into your build process, you can leverage `AntBuilder` to execute the `jjdoc` Ant task. This allows you to script the documentation generation as part of a larger Groovy-based build or automation script.

### Key Parameters (and their conceptual Groovy equivalents):

*   **`target`** (Ant XML): Specifies the path to the JavaCC grammar file (e.g., `MyParser.jj`).
    *   *Groovy AntBuilder*: This would be a parameter to the `jjdoc` method call, e.g., `target: 'src/org/example/parser/MyParser.jj'`.
*   **`javacchome`** (Ant XML): Specifies the root directory of the JavaCC installation.
    *   *Groovy AntBuilder*: `javacchome: '/path/to/javacc-7.0.10'`.
*   **`outputfile`** (Ant XML): The path and filename for the generated documentation. If omitted, defaults based on the grammar file name and type (HTML/text).
    *   *Groovy AntBuilder*: `outputfile: 'build/docs/jjdoc/MyParser.html'`.
*   **`text`** (Ant XML): Boolean option to generate plain text output instead of HTML.
    *   *Groovy AntBuilder*: `text: true` (or `false`).
*   **`onetable`** (Ant XML): Boolean option for HTML output, affecting table generation for the BNF.
    *   *Groovy AntBuilder*: `onetable: true` (or `false`).
*   **`maxmemory`** (Ant XML): Memory allocation for the forked JVM (relevant if JJDoc runs in a separate process).
    *   *Groovy AntBuilder*: `maxmemory: '128m'`. This would be passed as a string if the underlying Ant task expects it that way.

### Example Groovy DSL Usage (Conceptual):

Assuming `ant` is an `AntBuilder` instance:

```groovy
// Ensure necessary JARs for JJDoc are on the classpath
// (e.g., ant-javacc.jar or similar if not part of core Ant distribution used by AntBuilder)

// Define parameters
def javaccInstallationPath = "/opt/javacc" // Example path
def grammarSourceFile = "src/myproject/MyGrammar.jj"
def outputDocumentationFile = "build/docs/MyGrammarDoc.html"

// Create output directory if it doesn't exist
ant.mkdir(dir: "build/docs")

// Invoke the jjdoc task
ant.jjdoc(
    target: grammarSourceFile,
    outputfile: outputDocumentationFile,
    javacchome: javaccInstallationPath,
    text: false,      // Generate HTML
    onetable: true    // Use one table for BNF if generating HTML
)

println "JJDoc documentation generated at ${outputDocumentationFile}"
```

### Important Considerations for JJDoc with Groovy/AntBuilder:

1.  **JavaCC Installation**: The machine running the Groovy script must have JavaCC installed, and the `javacchome` parameter must correctly point to its installation directory. The JJDoc tool itself is part of the JavaCC distribution.
2.  **Classpath for Ant Tasks**: If you are using `AntBuilder` within a Groovy script that is, in turn, part of a larger build system (like Gradle), ensure that the necessary JARs for Ant's optional tasks (like `jjdoc`, if it's not a core task) are available on the classpath. You might need to declare dependencies on `org.apache.ant:ant-javacc` or similar artifacts if using a dependency manager. Alternatively, use `ant.taskdef` to define the task if the JAR is present but not automatically loaded.
3.  **Input Grammar File**: The `target` attribute must point to a valid JavaCC grammar file (`.jj`).
4.  **Output Configuration**: Decide whether you want HTML or text output and configure the `text` attribute accordingly. The `outputfile` attribute determines where the generated documentation will be saved.
5.  **Error Handling**: The examples provided are conceptual. In a production script, you would want to add error handling, for example, to check if the `javacchome` path is valid or if the `jjdoc` execution completes successfully.

By using `AntBuilder`, Groovy scripts can effectively automate the process of generating documentation from JavaCC grammar files as part of a larger build or project management workflow.
