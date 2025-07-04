The <groovyc> Ant Task
======================

version 4.0.26

Table of Contents

*   [1\. `groovyc`](#groovyc-ant-task-using)
    *   [1.1. Description](#groovyc-ant-task-description)
    *   [1.2. Required taskdef](#groovyc-ant-task-taskdef)
    *   [1.3. <groovyc> Attributes](#groovyc-ant-task-attributes)
    *   [1.4. <groovyc> Nested Elements](#groovyc-ant-task-nested-elements)
    *   [1.5. Joint Compilation](#groovyc-ant-task-joint-compilation)

[](#groovyc-ant-task-using)[1\. `groovyc`](#groovyc-ant-task-using)
---------------------------------------------------------------------

### [](#groovyc-ant-task-description)[1.1. Description](#groovyc-ant-task-description)

Compiles Groovy source files and, if joint compilation option is used, Java source files from [Apache Ant](http://ant.apache.org/).

### [](#groovyc-ant-task-taskdef)[1.2. Required taskdef](#groovyc-ant-task-taskdef)

Assuming the groovy jars are in _groovy.libs_, you will need to declare this task at some point in the `build.xml` prior to the `groovyc` task being invoked. Consider also adding any additional Groovy module jars, libraries and potentially transitive dependencies you might be using.
```groovy
taskdef name:"groovyc", classname:"org.codehaus.groovy.ant.Groovyc" {
  classpath {
    fileset file: '${groovy.libs}/groovy-ant-VERSION.jar'
    fileset file: '${groovy.libs}/groovy-VERSION.jar'
  }
}
```
### [](#groovyc-ant-task-attributes)[1.3. <groovyc> Attributes](#groovyc-ant-task-attributes)


| Attribute               | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | Required |
|-------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|
| srcdir                  | Location of the Groovy (and possibly Java) source files.                                                                                                                                                                                                                                                                                                                                                                                                                                  | Yes      |
| destdir                 | Location to store the class files.                                                                                                                                                                                                                                                                                                                                                                                                                                                        | Yes      |
| classpath               | The classpath to use.                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | No       |
| classpathref            | The classpath to use given as a path references.                                                                                                                                                                                                                                                                                                                                                                                                                                          | No       |
| sourcepath              | The sourcepath to use.                                                                                                                                                                                                                                                                                                                                                                                                                                                                    | No       |
| sourcepathref           | The sourcepath to use given as a path reference.                                                                                                                                                                                                                                                                                                                                                                                                                                          | No       |
| encoding                | Encoding of source files.                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | No       |
| verbose                 | Asks the compiler for verbose output; defaults to no.                                                                                                                                                                                                                                                                                                                                                                                                                                     | No       |
| includeAntRuntime       | Whether to include the Ant run-time libraries in the classpath; defaults to yes.                                                                                                                                                                                                                                                                                                                                                                                                          | No       |
| includeJavaRuntime      | Whether to include the default run-time libraries from the executing VM in the classpath; defaults to no.                                                                                                                                                                                                                                                                                                                                                                                 | No       |
| includeDestClasses      | This property controls whether to include the destination classes directory in the classpath given to the compiler. The default value is "true".                                                                                                                                                                                                                                                                                                                                          | No       |
| fork                    | Whether to execute groovyc using a spawned instance of the JVM; defaults to no.                                                                                                                                                                                                                                                                                                                                                                                                           | No       |
| memoryInitialSize       | The initial size of the memory for the underlying VM, if using fork mode; ignored otherwise. Defaults to the standard VM memory setting. (Examples: 83886080, 81920k, or 80m)                                                                                                                                                                                                                                                                                                             | No       |
| memoryMaximumSize       | The maximum size of the memory for the underlying VM, if using fork mode; ignored otherwise. Defaults to the standard VM memory setting. (Examples: 83886080, 81920k, or 80m)                                                                                                                                                                                                                                                                                                             | No       |
| failonerror             | Indicates whether compilation errors will fail the build; defaults to true.                                                                                                                                                                                                                                                                                                                                                                                                               | No       |
| proceed                 | Inverse alias for _failonerror_.                                                                                                                                                                                                                                                                                                                                                                                                                                                          | No       |
| listfiles               | Indicates whether the source files to be compiled will be listed; defaults to no.                                                                                                                                                                                                                                                                                                                                                                                                         | No       |
| stacktrace              | if true each compile error message will contain a stacktrace                                                                                                                                                                                                                                                                                                                                                                                                                              | No       |
| indy                    | Enable compilation with the \`\`invoke dynamic'' support when using Groovy 2.0 and beyond and running on JDK 7                                                                                                                                                                                                                                                                                                                                                                            | No       |
| scriptBaseClass         | Sets the base class for Groovy scripts                                                                                                                                                                                                                                                                                                                                                                                                                                                    | No       |
| stubdir                 | Set the stub directory into which the Java source stub files should be generated. The directory need not exist and will not be deleted automatically - though its contents will be cleared unless 'keepStubs' is true. Ignored when forked.                                                                                                                                                                                                                                               | No       |
| keepStubs               | Set the keepStubs flag. Defaults to false. Set to true for debugging. Ignored when forked.                                                                                                                                                                                                                                                                                                                                                                                                | No       |
| forceLookupUnnamedFiles | The Groovyc Ant task is frequently used in the context of a build system that knows the complete list of source files to be compiled. In such a context, it is wasteful for the Groovy compiler to go searching the classpath when looking for source files and hence by default the Groovyc Ant task calls the compiler in a special mode with such searching turned off. If you wish the compiler to search for source files then you need to set this flag to true. Defaults to false. | No       |
| configscript            | Set the configuration file used to customize the compilation configuration.                                                                                                                                                                                                                                                                                                                                                                                                               | No       |
| parameters              | Generates metadata for reflection on method parameter names on JDK 8 and above. Defaults to false.                                                                                                                                                                                                                                                                                                                                                                                        | No       |
| previewFeatures         | Enables the JEP preview features on JDK 12 and above. Defaults to false.                                                                                                                                                                                                                                                                                                                                                                                                                  | No       |
| targetBytecode          | Sets the bytecode compatibility level.                                                                                                                                                                                                                                                                                                                                                                                                                                                    | No       |
| javahome                | Sets the `java.home` value to use, default is the current JDK’s home.                                                                                                                                                                                                                                                                                                                                                                                                                     | No       |
| executable              | Sets the name of the java executable to use when invoking the compiler in forked mode, ignored otherwise.                                                                                                                                                                                                                                                                                                                                                                                 | No       |
| scriptExtension         | Set the extension to use when searching for Groovy source files. Accepts extensions in the form \*.groovy, .groovy or groovy.                                                                                                                                                                                                                                                                                                                                                             | No       |
| updatedProperty         | The property to set on compilation success. This property will not be set if the compilation fails, or if there are no files to compile.                                                                                                                                                                                                                                                                                                                                                  | No       |
| errorProperty           | The property to set on compilation failure. This property will be set if the compilation fails.                                                                                                                                                                                                                                                                                                                                                                                           | No       |

**Example:**
```groovy
path id:"classpath.main" {
  fileset dir:'${groovy.libs}', includes='*.jar', excludes='groovy-ant-*.jar'
  // add any other jars you need
}
groovyc srcdir:'${dir.sources}', destdir:'${dir.classes}', classpathref:'classpath.main',
         fork:'true', includeantruntime:'false', configscript:'config.groovy', targetBytecode:'17'
```
### [](#groovyc-ant-task-nested-elements)[1.4. <groovyc> Nested Elements](#groovyc-ant-task-nested-elements)



element

kind

Required

Replaces Attribute

src

a path structure

Yes (unless srcdir is used)

srcdir

classpath

a path structure

No

classpath or classpathref

javac

javac task

No

N/A

**Notes:**

*   For path structures see for example [https://ant.apache.org/manual/using.html#path](https://ant.apache.org/manual/using.html#path)

*   For usages of the `javac` task see [https://ant.apache.org/manual/Tasks/javac.html](https://ant.apache.org/manual/Tasks/javac.html)

*   The nested `javac` task behaves more or less as documented for the top-level `javac` task. `srcdir`, `destdir`, `classpath`, `encoding` and `parameters` for the nested `javac` task are taken from the enclosing `groovyc` task. If these attributes are specified then they are added, they do not replace. In fact, you should not attempt to overwrite the destination. Other attributes and nested elements are unaffected, for example `fork`, `memoryMaximumSize`, etc. may be used freely.


### [](#groovyc-ant-task-joint-compilation)[1.5. Joint Compilation](#groovyc-ant-task-joint-compilation)

Joint compilation is enabled by using an embedded `javac` element, as shown in the following example:
```groovy
groovyc srcdir:'${testSourceDirectory}', destdir:'${testClassesDirectory}', targetBytecode:'1.8' {
  classpath {
    pathelement path:'${mainClassesDirectory}'
    pathelement path:'${testClassesDirectory}'
    path refid:'testPath'
  }
  javac debug:"true" source:"1.8" target:"1.8"
}
```
More details about joint compilation can be found in the [joint compilation](tools-groovyc.html#section-jointcompilation) section.

Version 4.0.26  
Last updated 2025-02-25 08:23:11 +1000