# Ant Task: JJTree

## Original Ant Task Description

The `jjtree` Ant task invokes the JJTree preprocessor for the JavaCC compiler compiler. It inserts parse tree building actions at various places in the JavaCC source that it generates. The output of JJTree is then run through JavaCC to create the parser.

To use the `jjtree` task, you must set the `target` attribute to the name of the JJTree grammar file (typically with a `.jjt` extension) to process. You also need to specify the directory containing the JavaCC installation using the `javacchome` attribute, so that Ant can find the JavaCC classes. Optionally, you can set the `outputdirectory` to write the generated JavaCC grammar file (usually with a `.jj` extension) and node files to a specific directory. If `outputdirectory` is not set, JJTree writes these files to the directory containing the input JJTree grammar file. You can also use the `outputfile` attribute to specify a particular name and location for the generated JavaCC grammar file; otherwise, it defaults to the name of the JJTree grammar file with a `.jj` suffix.

This task is designed to be incremental; it only invokes JJTree if the input grammar file is newer than the generated JavaCC file.

## Groovy AntBuilder DSL Equivalent

To use the `jjtree` Ant task within a Groovy script via `AntBuilder`, you can call it as a method on your `AntBuilder` instance, passing the attributes as parameters. Ensure that the JavaCC JARs are available on the classpath, or define the task using `ant.taskdef`.

### Key Parameters (and their Groovy equivalents):

| Ant Attribute     | Groovy Parameter Type | Description                                                                                                                               | Required |
|-------------------|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------|----------|
| `target`          | `String`              | The JJTree grammar file to process (e.g., `src/Parser.jjt`).                                                                              | Yes      |
| `javacchome`      | `String`              | The directory containing the JavaCC distribution.                                                                                         | Yes      |
| `outputdirectory` | `String`              | The directory to write the generated JavaCC grammar and node files to. If not set, files are written to the directory containing the grammar file. | No       |
| `outputfile`      | `String`              | The file to write the generated JavaCC grammar file to. If not set, defaults to the input file name with a `.jj` suffix. Relative to `outputdirectory` if specified. | No       |
| `buildnodefiles`  | `boolean`             | Sets the `BUILD_NODE_FILES` grammar option.                                                                                                 | No       |
| `multi`           | `boolean`             | Sets the `MULTI` grammar option.                                                                                                            | No       |
| `nodedefaultvoid` | `boolean`             | Sets the `NODE_DEFAULT_VOID` grammar option.                                                                                              | No       |
| `nodefactory`     | `boolean`             | Sets the `NODE_FACTORY` grammar option.                                                                                                   | No       |
| `nodescopehook`   | `boolean`             | Sets the `NODE_SCOPE_HOOK` grammar option.                                                                                                | No       |
| `nodeusesparser`  | `boolean`             | Sets the `NODE_USES_PARSER` grammar option.                                                                                               | No       |
| `static`          | `boolean`             | Sets the `STATIC` grammar option.                                                                                                         | No       |
| `visitor`         | `boolean`             | Sets the `VISITOR` grammar option.                                                                                                        | No       |
| `nodepackage`     | `String`              | Sets the `NODE_PACKAGE` grammar option (package for generated node classes).                                                              | No       |
| `visitorexception`| `String`              | Sets the `VISITOR_EXCEPTION` grammar option (exception thrown by visitor methods).                                                        | No       |
| `nodeprefix`      | `String`              | Sets the `NODE_PREFIX` grammar option (prefix for node class names, e.g., "AST").                                                         | No       |
| `maxmemory`       | `String`              | Max amount of memory to allocate to the forked JVM (e.g., "128m"). _Since Ant 1.8.3._                                                    | No       |

### Example Groovy DSL Usage:

```groovy
def ant = new AntBuilder()
def javaccHomePath = "/opt/javacc" // User-defined path to JavaCC
def grammarInputFile = "src/com/example/MyGrammar.jjt"
def generatedSourcesDir = "build/generated/jjtree"

// Ensure the output directory exists
ant.mkdir(dir: generatedSourcesDir)

// Define the jjtree task if not already available (e.g., if Ant optional JARs are not automatically on classpath)
// ant.taskdef(name: "jjtree", classname: "org.apache.tools.ant.taskdefs.optional.javacc.JJTree")

ant.jjtree(
    target: grammarInputFile,
    outputdirectory: generatedSourcesDir,
    javacchome: javaccHomePath,
    multi: true,
    visitor: true,
    nodepackage: "com.example.mygrammar.nodes"
)

ant.echo("JJTree processing finished. Generated files are in ${generatedSourcesDir}")

// Typically, this would be followed by a javacc task:
// ant.javacc(
//     target: "${generatedSourcesDir}/MyGrammar.jj", // Assuming this is the output from jjtree
//     outputdirectory: generatedSourcesDir,
//     javacchome: javaccHomePath
// )
// ant.echo("JavaCC processing finished.")
```

### Important Considerations for JJTree with Groovy/AntBuilder:

1.  **JavaCC Installation**: A valid JavaCC installation must be present, and `javacchome` must point to it.
2.  **Classpath**: The `javacc.jar` (or the specific Ant-JavaCC task JAR, like `ant-javacc.jar`) needs to be on the classpath for `AntBuilder` to find the `jjtree` task. If not, use `ant.taskdef` to define it explicitly.
3.  **File Paths**: All paths for `target`, `outputdirectory`, `outputfile`, and `javacchome` must be accurate.
4.  **Boolean Options**: In Groovy, boolean Ant attributes are set using `true` or `false`.
5.  **Output**: JJTree generates a `.jj` file (the JavaCC grammar) and potentially multiple `.java` files for the tree nodes. The subsequent `javacc` task then processes this generated `.jj` file.

## Examples

### Ant XML Examples

#### Example 1: Basic Usage
```xml
<project name="JJTreeExample" default="generate-parser">
    <property name="javacc.home" location="/path/to/javacc-7.0.10"/>
    <property name="grammar.file" location="src/org/example/parser/MyParser.jjt"/>
    <property name="gen.dir" location="build/generated-sources/jjtree"/>

    <target name="init">
        <mkdir dir="${gen.dir}"/>
    </target>

    <target name="run-jjtree" depends="init">
        <jjtree
            target="${grammar.file}"
            outputdirectory="${gen.dir}"
            javacchome="${javacc.home}"
        />
        <echo>JJTree processing complete. Output in ${gen.dir}</echo>
    </target>

    <target name="run-javacc" depends="run-jjtree">
        <javacc
            target="${gen.dir}/MyParser.jj"
            outputdirectory="${gen.dir}"
            javacchome="${javacc.home}"
        />
        <echo>JavaCC compilation complete. Parser generated in ${gen.dir}</echo>
    </target>

    <target name="generate-parser" depends="run-javacc"/>
</project>
```
*Description: This example invokes JJTree on `MyParser.jjt`. The generated JavaCC grammar file (e.g., `MyParser.jj`) and node files are placed in the `build/generated-sources/jjtree` directory. A subsequent `javacc` task processes the output of JJTree.* 

#### Example 2: Specifying Output File and Options
```xml
<project name="JJTreeAdvancedExample" default="generate">
    <property name="javacc.home" location="/path/to/javacc-7.0.10"/>
    <property name="grammar.file" location="src/org/example/parser/AdvancedParser.jjt"/>
    <property name="gen.dir" location="build/generated/jjtree"/>

    <target name="init">
        <mkdir dir="${gen.dir}"/>
    </target>

    <target name="generate-jjtree-output" depends="init">
        <jjtree
            target="${grammar.file}"
            outputdirectory="${gen.dir}"
            outputfile="CustomParser.jj"
            javacchome="${javacc.home}"
            multi="true"
            visitor="true"
            nodepackage="org.example.parser.nodes"
            nodeusesparser="false"
        />
        <echo>JJTree processing complete with options. Output in ${gen.dir}, generated .jj file is CustomParser.jj</echo>
    </target>
    <target name="generate" depends="generate-jjtree-output"/>
</project>
```
*Description: This example demonstrates using several JJTree options: `outputfile` to name the generated grammar, `multi` and `visitor` to enable specific tree-building features, `nodepackage` to specify the package for generated node classes, and `nodeusesparser`.* 

### Groovy AntBuilder DSL Examples

#### Example 1: Basic Usage with AntBuilder
```groovy
// build_jjtree.groovy
def ant = new AntBuilder()

def javaccHome = "/path/to/javacc-7.0.10"
// Ensure MyParser.jjt exists in src/org/example/parser/
// Example .jjt content: options { MULTI=true; VISITOR=true; } PARSER_BEGIN(MyParser) package org.example.parser; public class MyParser {} PARSER_END(MyParser) void Start() #Root : {} { "a" }
def grammarFile = "src/org/example/parser/MyParser.jjt"
def genDir = "build/generated-sources/jjtree_groovy"

ant.mkdir(dir: genDir)

// ant.taskdef(name: "jjtree", classname: "org.apache.tools.ant.taskdefs.optional.javacc.JJTree")

ant.jjtree(
    target: grammarFile,
    outputdirectory: genDir,
    javacchome: javaccHome
)

ant.echo("Groovy: JJTree processing complete. Output in ${genDir}")
```

#### Example 2: Specifying Options with AntBuilder
```groovy
// build_jjtree_options.groovy
def ant = new AntBuilder()

def javaccHome = "/path/to/javacc-7.0.10"
// Ensure AdvancedParser.jjt exists in src/org/example/parser/
// Example .jjt content: options { NODE_PACKAGE="org.example.parser.nodes"; } PARSER_BEGIN(AdvancedParser) package org.example.parser; public class AdvancedParser {} PARSER_END(AdvancedParser) void Input() #Input : {} { "x" }
def grammarFile = "src/org/example/parser/AdvancedParser.jjt"
def genDir = "build/generated/jjtree_groovy_options"

ant.mkdir(dir: genDir)

// ant.taskdef(name: "jjtree", classname: "org.apache.tools.ant.taskdefs.optional.javacc.JJTree")

ant.jjtree(
    target: grammarFile,
    outputdirectory: genDir,
    outputfile: "CustomizedOutput.jj",
    javacchome: javaccHome,
    multi: true,
    visitor: true,
    nodepackage: "org.example.parser.customnodes",
    static: false
)

ant.echo("Groovy: JJTree processing with options complete. Output in ${genDir}")
```

