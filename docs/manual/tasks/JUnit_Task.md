# Ant Task: JUnit

## Original Ant Task Description

This task runs tests from the JUnit testing framework. The latest version of the framework can be found at [https://junit.org](https://junit.org). This task has been tested with JUnit 3.0 up to JUnit 3.8.2; it won't work with versions prior to JUnit 3.0. It also works with JUnit 4.x, including "pure" JUnit 4 tests using only annotations and no `JUnit4TestAdapter`.

**Note**: This task depends on external libraries not included in the Apache Ant distribution. See Library Dependencies for more information.

**Note**: It is necessary to set `fork="true"`, if the test(s) being launched by this task or any libraries being used by the test(s), call APIs like `java.lang.System.exit()` or `java.lang.Runtime.exit()`.

**Note**: You must have `junit.jar` available. You can do one of:

1.  Put both `junit.jar` and `ant-junit.jar` in `ANT_HOME/lib`.
2.  Do not put either in `ANT_HOME/lib`, and instead include their locations in your `CLASSPATH` environment variable.
3.  Add both JARs to your classpath using `-lib`.
4.  Specify the locations of both JARs using a `<classpath>` element in a `<taskdef>` in the build file.
5.  Leave `ant-junit.jar` in its default location in `ANT_HOME/lib` but include `junit.jar` in the `<classpath>` passed to `<junit>`. (Since Ant 1.7)

See the FAQ for details.

Tests are defined by nested `test` or `batchtest` tags.

## Groovy AntBuilder DSL Equivalent

To use the `junit` Ant task within a Groovy script via `AntBuilder`, you can call it as a method on your `AntBuilder` instance. Ensure that `junit.jar` and `ant-junit.jar` (or `ant-junit4.jar` for JUnit4-specific features if needed) are on the classpath, or define the task using `ant.taskdef`.

### Key Parameters (and their Groovy equivalents):

| Ant Attribute             | Groovy Parameter Type | Description                                                                                                                               | Required |
|---------------------------|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------|----------|
| `printsummary`            | `String` or `boolean` | Print one-line statistics for each testcase. Values: `on`, `off`, `yes`, `no`, `withOutAndErr`. `yes`/`on` are equivalent. Default: `off`.   | No       |
| `fork`                    | `boolean`             | Run the tests in a separate JVM. Default: `false`.                                                                                        | No       |
| `forkmode`                | `String`              | Controls how many JVMs get created if `fork` is `true`. Values: `perTest` (default), `perBatch`, `once`.                                   | No       |
| `haltonerror`             | `boolean`             | Stop the build process if an error occurs during the test run. Default: `false`.                                                          | No       |
| `errorproperty`           | `String`              | The name of a property to set in the event of an error.                                                                                   | No       |
| `haltonfailure`           | `boolean`             | Stop the build process if a test fails (errors are considered failures as well). Default: `false`.                                        | No       |
| `failureproperty`         | `String`              | The name of a property to set in the event of a failure.                                                                                  | No       |
| `filtertrace`             | `boolean`             | Filter out JUnit and Ant stack frames from error and failure stack traces. Default: `true`.                                               | No       |
| `timeout`                 | `Long`                | Cancel individual tests if they don't finish in the given time (milliseconds). Ignored if `fork` is `false`.                               | No       |
| `maxmemory`               | `String`              | Maximum amount of memory to allocate to the forked JVM (e.g., "128m", "1g"). Ignored if `fork` is `false`.                               | No       |
| `jvm`                     | `String`              | The command used to invoke JVM. Default: `java`. Ignored if `fork` is `false`.                                                              | No       |
| `dir`                     | `File` or `String`    | The directory in which to invoke JVM. Ignored if `fork` is `false`.                                                                       | No       |
| `newenvironment`          | `boolean`             | Do not propagate the old environment when new environment variables are specified. Default: `false`. Ignored if `fork` is `false`.        | No       |
| `includeantruntime`       | `boolean`             | Implicitly add Ant classes required to run tests and JUnit to classpath in forked mode. Default: `true`.                                  | No       |
| `showoutput`              | `boolean`             | Send any output generated by tests to Ant's logging system as well as to formatters. Default: `false`.                                    | No       |
| `outputtoformatters`      | `boolean`             | Send any output generated by tests to the test formatters. Default: `true`. (Since Ant 1.7.0)                                             | No       |
| `tempdir`                 | `File` or `String`    | Where Ant should place temporary files. Default: project's basedir. (Since Ant 1.6)                                                       | No       |
| `reloading`               | `boolean`             | Whether a new classloader should be instantiated for each test case. Ignored if `fork` is `true`. Default: `true`. (Since Ant 1.6)         | No       |
| `clonevm`                 | `boolean`             | If `true`, system properties and bootclasspath of forked JVM will be same as Ant's JVM. Default: `false`. Ignored if `fork` is `false`. | No       |
| `logfailedtests`          | `boolean`             | Log a "FAILED" message for each failing test. Default: `true`. (Since Ant 1.8.0)                                                          | No       |
| `enableTestListenerEvents`| `boolean`             | Send fine-grained test information to Ant's logging system at verbose level. Default: `false`. (Since Ant 1.8.2)                         | No       |
| `threads`                 | `int`                 | Number of threads to run tests in. Requires `fork` with `perTest`. (Since Ant 1.9.4)                                                      | No       |

### Nested Elements in Groovy:

Nested elements like `classpath`, `formatter`, `test`, `batchtest`, `jvmarg`, `sysproperty`, and `env` are configured using closures or method calls within the `junit` block.

*   **`classpath`**: `ant.classpath { pathelement(location: 'path/to/classes') }` or `ant.classpath(path: 'path/to/classes')`
*   **`formatter`**: `ant.formatter(type: 'xml')` or `ant.formatter(type: 'plain', usefile: 'false')`
*   **`test`**: `ant.test(name: 'com.example.MyTest', todir: 'reports/test-results')`
*   **`batchtest`**: `ant.batchtest(todir: 'reports/test-results') { fileset(dir: 'build/test-classes') { include(name: '**/*Test.class') } }`
*   **`jvmarg`**: `ant.jvmarg(value: '-Xmx512m')`
*   **`sysproperty`**: `ant.sysproperty(key: 'my.property', value: 'my.value')`
*   **`env`**: `ant.env(key: 'MY_ENV_VAR', value: 'some_value')`

### Example Groovy DSL Usage:

```groovy
def ant = new AntBuilder()
def reportsDir = "build/reports/junit"

ant.mkdir(dir: reportsDir)

// Assuming junit.jar and ant-junit.jar are in ANT_HOME/lib or CLASSPATH
// Otherwise, define the task:
// ant.taskdef(name: 'junit', classname: 'org.apache.tools.ant.taskdefs.optional.junit.JUnitTask')

ant.junit(printsummary: "yes", haltonfailure: "true", fork: "true") {
    classpath {
        pathelement(location: "build/classes")      // Compiled application classes
        pathelement(location: "build/test-classes") // Compiled test classes
        // Add JUnit JAR and other dependencies if not in Ant's lib or global CLASSPATH
        // pathelement(location: "path/to/junit.jar")
    }

    formatter(type: "xml") // Generates XML reports
    formatter(type: "plain", usefile: "false") // Prints plain text summary to console

    batchtest(todir: reportsDir) { // todir for XML formatter output files
        fileset(dir: "build/test-classes") {
            include(name: "**/*Test.class")
            exclude(name: "**/*AbstractTest.class")
        }
    }

    // Example of running a single test
    // test(name: "com.example.SpecificTest", todir: reportsDir)

    // Example of forked JVM arguments and system properties
    if (delegate.getProperty("fork").toBoolean()) { // Check if fork is true
        jvmarg(value: "-Xmx256m")
        sysproperty(key: "database.url", value: "jdbc:hsqldb:mem:testdb")
    }
}

ant.echo("JUnit tests completed. Reports are in ${reportsDir}")
```

### Important Considerations for JUnit with Groovy/AntBuilder:

1.  **JAR Dependencies**: Ensure `junit.jar` and `ant-junit.jar` (or `ant-junit4.jar` if using JUnit 4 specific features directly without adapters) are accessible. They can be in `ANT_HOME/lib`, the system `CLASSPATH`, or specified via a `<classpath>` in a `taskdef` or the `junit` task itself.
2.  **Forking**: If tests interact with `System.exit()` or require a specific environment (memory, system properties), set `fork: true`. Forking also allows for `timeout` and `maxmemory` settings.
3.  **Formatters**: Multiple formatters can be used. The `todir` attribute on `test` or `batchtest` is often used by file-based formatters (like XML) to specify the output directory for reports.
4.  **Test Discovery**: `batchtest` with `fileset` is common for discovering multiple test classes. Individual tests can be run using the `test` element.
5.  **Error and Failure Handling**: `haltonerror`, `haltonfailure`, `errorproperty`, and `failureproperty` control the build's behavior upon test issues.

## Examples (Ant XML)

### Example 1: Basic Test Execution

```xml
<project name="JUnitExample" default="test" basedir=".">
    <property name="src.dir" location="src"/>
    <property name="build.dir" location="build"/>
    <property name="build.classes.dir" location="${build.dir}/classes"/>
    <property name="build.test.classes.dir" location="${build.dir}/test-classes"/>
    <property name="reports.junit.xml.dir" location="${build.dir}/reports/junit/xml"/>
    <property name="reports.junit.html.dir" location="${build.dir}/reports/junit/html"/>

    <path id="compile.classpath">
        <!-- Add JUnit JAR and other dependencies -->
        <pathelement location="path/to/junit-4.13.2.jar"/>
        <!-- <pathelement location="path/to/hamcrest-core-1.3.jar"/> -->
    </path>

    <target name="init">
        <mkdir dir="${build.classes.dir}"/>
        <mkdir dir="${build.test.classes.dir}"/>
        <mkdir dir="${reports.junit.xml.dir}"/>
        <mkdir dir="${reports.junit.html.dir}"/>
    </target>

    <target name="compile" depends="init">
        <javac srcdir="${src.dir}/main/java" destdir="${build.classes.dir}" classpathref="compile.classpath" includeantruntime="false"/>
        <javac srcdir="${src.dir}/test/java" destdir="${build.test.classes.dir}" classpathref="compile.classpath" includeantruntime="false">
            <classpath>
                <pathelement location="${build.classes.dir}"/>
            </classpath>
        </javac>
    </target>

    <target name="test" depends="compile">
        <junit printsummary="yes" haltonfailure="yes" fork="yes">
            <classpath>
                <pathelement location="${build.classes.dir}"/>
                <pathelement location="${build.test.classes.dir}"/>
                <path refid="compile.classpath"/>
            </classpath>

            <formatter type="xml"/>
            <formatter type="plain" usefile="false"/>

            <batchtest todir="${reports.junit.xml.dir}">
                <fileset dir="${build.test.classes.dir}">
                    <include name="**/*Test.class"/>
                </fileset>
            </batchtest>
        </junit>
    </target>

    <!-- Optional: Generate HTML reports from XML output -->
    <target name="junitreport" depends="test">
        <junitreport todir="${reports.junit.html.dir}">
            <fileset dir="${reports.junit.xml.dir}">
                <include name="TEST-*.xml"/>
            </fileset>
            <report format="frames" todir="${reports.junit.html.dir}"/>
        </junitreport>
    </target>
</project>
```
*Description: This example compiles source and test files, then runs all tests ending with `Test.class` found in `${build.test.classes.dir}`. It forks a new JVM for the tests, prints a summary, and halts the build on failure. XML reports are generated in `${reports.junit.xml.dir}`, and a plain summary is printed to the console. An optional `junitreport` target shows how to convert these XML reports to HTML.* 

### Example 2: Running a Single Test with Forked JVM and Properties

```xml
<project name="SingleJUnitTest" default="run-single-test">
    <property name="test.class.name" value="com.example.MySpecificTest"/>
    <property name="build.dir" location="build"/>
    <property name="build.classes.dir" location="${build.dir}/classes"/>
    <property name="build.test.classes.dir" location="${build.dir}/test-classes"/>
    <property name="reports.junit.dir" location="${build.dir}/reports/junit"/>

    <path id="test.classpath">
        <pathelement location="${build.classes.dir}"/>
        <pathelement location="${build.test.classes.dir}"/>
        <pathelement location="path/to/junit-4.13.2.jar"/>
    </path>

    <target name="init-single">
        <mkdir dir="${reports.junit.dir}"/>
    </target>

    <target name="run-single-test" depends="init-single">
        <!-- Assume classes are already compiled -->
        <junit fork="yes" haltonfailure="no" failureproperty="test.failed" dir=".">
            <classpath refid="test.classpath"/>
            
            <formatter type="plain" usefile="false"/>
            <formatter type="xml"/>
            
            <test name="${test.class.name}" todir="${reports.junit.dir}"/>
            
            <jvmarg value="-Xmx512m"/>
            <sysproperty key="config.file" value="test-config.properties"/>
            <env key="TEST_MODE" value="integration"/>
        </junit>
        
        <fail if="test.failed" message="JUnit tests failed. See reports in ${reports.junit.dir}"/>
    </target>
</project>
```
*Description: This example runs a single specified test class (`com.example.MySpecificTest`). It forks a JVM with a maximum memory of 512MB, sets a system property `config.file`, and an environment variable `TEST_MODE`. Instead of halting immediately on failure, it sets the `test.failed` property, allowing for potential cleanup actions before explicitly failing the build if tests did not pass.* 

