# JavaCC Ant Task

## Description

Invokes the JavaCC compiler-compiler on a grammar file. To use the `javacc` task, set the `target` attribute to the name of the grammar file to process. You also need to specify the directory containing the JavaCC installation using the `javacchome` attribute, so that Apache Ant can find the JavaCC classes. Optionally, you can also set the `outputdirectory` to write the generated file to a specific directory. Otherwise, JavaCC writes the generated files to the directory containing the grammar file.

This task only invokes JavaCC if the grammar file is newer than the generated `.java` files. JavaCC assumes that the Java class name of the generated parser is the same as the name of the grammar file, ignoring the `.jj` extension. If this is not the case, the `javacc` task will still work, but it will always generate the output files.

## Parameters

| Attribute             | Description                                                                                                | Required |
|-----------------------|------------------------------------------------------------------------------------------------------------|----------|
| `target`              | The grammar file to process.                                                                               | Yes      |
| `javacchome`          | The directory containing the JavaCC distribution.                                                          | Yes      |
| `outputdirectory`     | The directory to write the generated files to. If not set, the files are written to the directory containing the grammar file. | No       |
| `buildparser`         | Sets the `BUILD_PARSER` grammar option. This is a boolean option.                                            | No       |
| `buildtokenmanager`   | Sets the `BUILD_TOKEN_MANAGER` grammar option. This is a boolean option.                                     | No       |
| `cachetokens`         | Sets the `CACHE_TOKENS` grammar option. This is a boolean option.                                            | No       |
| `choiceambiguitycheck`| Sets the `CHOICE_AMBIGUITY_CHECK` grammar option. This is an integer option.                               | No       |
| `commontokenaction`   | Sets the `COMMON_TOKEN_ACTION` grammar option. This is a boolean option.                                     | No       |
| `debuglookahead`      | Sets the `DEBUG_LOOKAHEAD` grammar option. This is a boolean option.                                         | No       |
| `debugparser`         | Sets the `DEBUG_PARSER` grammar option. This is a boolean option.                                            | No       |
| `debugtokenmanager`   | Sets the `DEBUG_TOKEN_MANAGER` grammar option. This is a boolean option.                                     | No       |
| `errorreporting`      | Sets the `ERROR_REPORTING` grammar option. This is a boolean option.                                         | No       |
| `forcelacheck`        | Sets the `FORCE_LA_CHECK` grammar option. This is a boolean option.                                          | No       |
| `ignorecase`          | Sets the `IGNORE_CASE` grammar option. This is a boolean option.                                             | No       |
| `javaunicodeescape`   | Sets the `JAVA_UNICODE_ESCAPE` grammar option. This is a boolean option.                                     | No       |
| `jdkversion`          | Sets the `JDK_VERSION` option. This is a string option.                                                      | No       |
| `keeplinecolumn`      | Sets the `KEEP_LINE_COLUMN` grammar option. This is a boolean option.                                        | No       |
| `lookahead`           | Sets the `LOOKAHEAD` grammar option. This is an integer option.                                              | No       |
| `optimizetokenmanager`| Sets the `OPTIMIZE_TOKEN_MANAGER` grammar option. This is a boolean option.                                  | No       |
| `otherambiguitycheck` | Sets the `OTHER_AMBIGUITY_CHECK` grammar option. This is an integer option.                                | No       |
| `sanitycheck`         | Sets the `SANITY_CHECK` grammar option. This is a boolean option.                                            | No       |
| `static`              | Sets the `STATIC` grammar option. This is a boolean option.                                                  | No       |
| `unicodeinput`        | Sets the `UNICODE_INPUT` grammar option. This is a boolean option.                                           | No       |
| `usercharstream`      | Sets the `USER_CHAR_STREAM` grammar option. This is a boolean option.                                        | No       |
| `usertokenmanager`    | Sets the `USER_TOKEN_MANAGER` grammar option. This is a boolean option.                                      | No       |
| `maxmemory`           | Max amount of memory to allocate to the forked JVM. *Since Ant 1.8.3*                                      | No       |



## Nested Elements

The `javacc` Ant task does not have any dedicated nested elements. All configurations are done through its attributes.



## Groovy AntBuilder DSL Equivalent

To use the `javacc` Ant task within a Groovy script via `AntBuilder`, you invoke it as a method on your `AntBuilder` instance. The XML attributes translate directly to named method arguments in Groovy.

### Key Parameters (and their Groovy equivalents):

All attributes listed in the Parameters section above can be used as named arguments in the Groovy DSL. For example, `target="src/Parser.jj"` becomes `target: "src/Parser.jj"` in Groovy.

| Ant Attribute        | Groovy Parameter Type | Description                                                                                                |
|----------------------|-----------------------|------------------------------------------------------------------------------------------------------------|
| `target`             | `String` or `File`    | The grammar file to process.                                                                               |
| `javacchome`         | `String` or `File`    | The directory containing the JavaCC distribution.                                                          |
| `outputdirectory`    | `String` or `File`    | The directory to write the generated files to.                                                             |
| `buildparser`        | `boolean`             | Sets the `BUILD_PARSER` grammar option.                                                                    |
| `buildtokenmanager`  | `boolean`             | Sets the `BUILD_TOKEN_MANAGER` grammar option.                                                             |
| `cachetokens`        | `boolean`             | Sets the `CACHE_TOKENS` grammar option.                                                                    |
| `choiceambiguitycheck`| `int`                 | Sets the `CHOICE_AMBIGUITY_CHECK` grammar option.                                                          |
| `commontokenaction`  | `boolean`             | Sets the `COMMON_TOKEN_ACTION` grammar option.                                                               |
| `debuglookahead`     | `boolean`             | Sets the `DEBUG_LOOKAHEAD` grammar option.                                                                 |
| `debugparser`        | `boolean`             | Sets the `DEBUG_PARSER` grammar option.                                                                    |
| `debugtokenmanager`  | `boolean`             | Sets the `DEBUG_TOKEN_MANAGER` grammar option.                                                             |
| `errorreporting`     | `boolean`             | Sets the `ERROR_REPORTING` grammar option.                                                                 |
| `forcelacheck`       | `boolean`             | Sets the `FORCE_LA_CHECK` grammar option.                                                                  |
| `ignorecase`         | `boolean`             | Sets the `IGNORE_CASE` grammar option.                                                                     |
| `javaunicodeescape`  | `boolean`             | Sets the `JAVA_UNICODE_ESCAPE` grammar option.                                                             |
| `jdkversion`         | `String`              | Sets the `JDK_VERSION` option.                                                                             |
| `keeplinecolumn`     | `boolean`             | Sets the `KEEP_LINE_COLUMN` grammar option.                                                                |
| `lookahead`          | `int`                 | Sets the `LOOKAHEAD` grammar option.                                                                       |
| `optimizetokenmanager`| `boolean`             | Sets the `OPTIMIZE_TOKEN_MANAGER` grammar option.                                                          |
| `otherambiguitycheck`| `int`                 | Sets the `OTHER_AMBIGUITY_CHECK` grammar option.                                                           |
| `sanitycheck`        | `boolean`             | Sets the `SANITY_CHECK` grammar option.                                                                    |
| `static`             | `boolean`             | Sets the `STATIC` grammar option.                                                                          |
| `unicodeinput`       | `boolean`             | Sets the `UNICODE_INPUT` grammar option.                                                                   |
| `usercharstream`     | `boolean`             | Sets the `USER_CHAR_STREAM` grammar option.                                                                |
| `usertokenmanager`   | `boolean`             | Sets the `USER_TOKEN_MANAGER` grammar option.                                                              |
| `maxmemory`          | `String`              | Max amount of memory to allocate to the forked JVM.                                                        |

Since there are no nested elements for the `javacc` task, the Groovy DSL usage is straightforward, primarily involving setting these parameters as arguments to the `javacc` method call.




## Examples

### Basic JavaCC Invocation

This example invokes JavaCC on the grammar file `src/Parser.jj`, writing the generated files to the `build/src` directory. The JavaCC distribution is assumed to be in `c:/program files/JavaCC`, and the `STATIC` grammar option is set to `true`.

*Ant XML:*
```xml
<project name="JavaCCExample" default="compile">
    <target name="compile">
        <javacc target="src/Parser.jj"
                outputdirectory="build/src"
                javacchome="c:/program files/JavaCC"
                static="true"/>
    </target>
</project>
```

*Groovy DSL:*
```groovy
ant.javacc(target: "src/Parser.jj",
           outputdirectory: "build/src",
           javacchome: "c:/program files/JavaCC",
           static: true)
```

### Invoking JavaCC with more options

This example demonstrates setting a few more boolean and integer options.

*Ant XML:*
```xml
<project name="JavaCCExampleAdvanced" default="compile">
    <target name="compile">
        <javacc target="grammars/MyParser.jj"
                outputdirectory="generated_sources/java"
                javacchome="/opt/javacc-7.0.10"
                jdkversion="1.8"
                lookahead="2"
                debugparser="false"
                ignorecase="true"
                maxmemory="256m"/>
    </target>
</project>
```

*Groovy DSL:*
```groovy
ant.javacc(target: "grammars/MyParser.jj",
           outputdirectory: "generated_sources/java",
           javacchome: "/opt/javacc-7.0.10",
           jdkversion: "1.8",
           lookahead: 2,
           debugparser: false,
           ignorecase: true,
           maxmemory: "256m")
```
These examples show how the attributes of the `javacc` task are directly translated into named arguments in the Groovy DSL, making the build scripts concise and readable.

