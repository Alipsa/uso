## ANTLR Task (Groovy DSL)

### Description

The `antlr` task invokes the ANTLR (ANother Tool for Language Recognition) translator generator on a grammar file. This is used to generate parser and lexer code from a grammar definition.

In Groovy AntBuilder, the `antlr` task is invoked as `ant.antlr(...)`. Its attributes are passed as named parameters, and nested elements like `classpath` and `jvmarg` are defined within a closure.

**Note:** This task depends on external libraries (typically `antlr.jar` or a version-specific ANTLR JAR like `antlr-VERSION.jar`) that must be available in Ant's classpath or specified via a nested `<classpath>` element.

### Parameters

Common Ant attributes for the `antlr` task and their Groovy AntBuilder DSL mapping:

*   `target`: The grammar file to process (e.g., `mygrammar.g`). (Required) Groovy: `target: 'src/main/antlr/MyGrammar.g'`
*   `outputdirectory`: The directory to write the generated Java files to. Defaults to the directory containing the grammar file. Groovy: `outputdirectory: 'build/generated-sources/antlr'`
*   `glib`: An optional super grammar file that the target grammar overrides. Groovy: `glib: 'src/main/antlr/SuperGrammar.g'`
*   `debug`: If `true`, adds code to the generated parser to launch the ParseView debugger. (Requires ParseView to be installed). Groovy: `debug: true` (or `debug: 'yes'`). Default is `false`.
*   `html`: If `true`, emits an HTML version of the grammar. Groovy: `html: true` (or `html: 'yes'`). Default is `false`.
*   `diagnostic`: If `true`, generates a text file with debugging information. Groovy: `diagnostic: true` (or `diagnostic: 'yes'`). Default is `false`.
*   `trace`: If `true`, forces all rules to call `traceIn`/`traceOut`. Groovy: `trace: true` (or `trace: 'yes'`). Default is `false`.
*   `traceParser`: If `true`, forces only parser rules to call `traceIn`/`traceOut`. Groovy: `traceParser: true`.
*   `traceLexer`: If `true`, forces only lexer rules to call `traceIn`/`traceOut`. Groovy: `traceLexer: true`.
*   `traceTreeWalker`: If `true`, forces only tree walker rules to call `traceIn`/`traceOut`. Groovy: `traceTreeWalker: true`.
*   `dir`: The directory to invoke the JVM in (for forked execution). Groovy: `dir: '.'`

### Nested Elements

*   **classpath**: Specifies the classpath for ANTLR, typically containing the ANTLR JAR itself if not in Ant's main lib directory.
    ```groovy
    ant.antlr(target: 'MyGrammar.g') {
        classpath {
            pathelement(location: 'libs/antlr-2.7.7.jar') // Example for ANTLR 2.x
        }
    }
    ```
*   **jvmarg**: Specifies JVM arguments for the forked ANTLR process.
    ```groovy
    ant.antlr(target: 'MyGrammar.g') {
        jvmarg(value: '-Xmx512m')
        jvmarg(value: '-Dsome.property=true')
    }
    ```

### Examples

1.  **Basic ANTLR invocation:**

    *   **Ant XML:**
        ```xml
        <antlr target="src/main/antlr/Expr.g"
               outputdirectory="build/generated-src/antlr"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.antlr(target: 'src/main/antlr/Expr.g',
                  outputdirectory: 'build/generated-src/antlr')
        ```

2.  **ANTLR with HTML generation and classpath:**

    *   **Ant XML:**
        ```xml
        <path id="antlr.classpath">
            <pathelement location="lib/antlr-2.7.2.jar"/>
        </path>

        <antlr target="grammars/MyLang.g"
               outputdirectory="gen/com/example/parser"
               html="true">
            <classpath refid="antlr.classpath"/>
        </antlr>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        // Define classpath (if needed, or ensure antlr.jar is in Ant's lib)
        // ant.path(id: 'antlr.classpath') {
        //     pathelement(location: 'lib/antlr-2.7.2.jar')
        // }

        ant.antlr(target: 'grammars/MyLang.g',
                  outputdirectory: 'gen/com/example/parser',
                  html: true) {
            classpath(location: 'lib/antlr-2.7.2.jar') // Or use refid: 'antlr.classpath' if path is defined with an id
        }
        ```

3.  **ANTLR with trace options and JVM arguments (conceptual for ANTLR 3/4 which might have different task names or attributes, this example uses ANTLR 2.x style task attributes):**

    *   **Ant XML:**
        ```xml
        <antlr target="com/example/MyParser.g"
               outputdirectory="${gen.dir}"
               traceParser="true"
               traceLexer="true">
            <jvmarg value="-Xmx256M"/>
        </antlr>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.antlr(target: 'com/example/MyParser.g',
                  outputdirectory: ant.project.getProperty('gen.dir'), // Accessing Ant property
                  traceParser: true,
                  traceLexer: true) {
            jvmarg(value: '-Xmx256M')
        }
        ```
        *Note: For ANTLR v3 or v4, the Ant task might be different (e.g., `org.antlr.v4.Tool` directly or a specific taskdef). The examples above are based on the classic `antlr` task, typically for ANTLR 2.x. Always ensure your classpath and task definition match the ANTLR version you are using.* 

### Navigation

*   [Previous Section: Using Apache Ant with Groovy](03-Using_Apache_Ant_Groovy.md)
*   [Next Task: AntCall Task](AntCall_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
