# Ant Task: JUnitLauncher

## Original Ant Task Description

This task allows tests to be launched and run using the JUnit 5 framework. JUnit 5 introduced a newer set of APIs to write and launch tests, along with the concept of test engines. It supports running tests written using JUnit 4 or JUnit 5 constructs. The task itself does not interpret test cases but launches the JUnit 5 test launcher to parse and execute selected tests via appropriate test engines.

**Note**: This task depends on external libraries not included in the Apache Ant distribution. Necessary JUnit 5 platform libraries (e.g., `junit-platform-commons.jar`, `junit-platform-engine.jar`, `junit-platform-launcher.jar`, `opentest4j.jar`) and specific test engine JARs (e.g., `junit-vintage-engine.jar` for JUnit 4, or `junit-jupiter-api.jar` and `junit-jupiter-engine.jar` for JUnit 5/Jupiter) must be on the classpath.

Tests are defined by nested elements like `test` or `testclasses`.

## Groovy AntBuilder DSL Equivalent

To use the `junitlauncher` Ant task within a Groovy script via `AntBuilder`, you can call it as a method on your `AntBuilder` instance. Ensure that all required JUnit 5 platform and engine JARs, along with `ant-junitlauncher.jar`, are correctly configured in the classpath.

### Key Parameters (and their Groovy equivalents):

| Ant Attribute     | Groovy Parameter Type | Description                                                                                                | Required |
|-------------------|-----------------------|------------------------------------------------------------------------------------------------------------|----------|
| `haltOnFailure`   | `boolean`             | Stop the build if any test fails (assertion failures or exceptions). Default: `false`.                       | No       |
| `failureProperty` | `String`              | Property to set if a test fails.                                                                           | No       |
| `printSummary`    | `boolean`             | Print a summary of test execution to System.out. Default: `false`.                                         | No       |
| `includeTags`     | `String`              | Comma-separated list of JUnit 5 tags to include. (Since Ant 1.10.7)                                        | No       |
| `excludeTags`     | `String`              | Comma-separated list of JUnit 5 tags to exclude. (Since Ant 1.10.7)                                        | No       |
| `fork`            | `boolean`             | Run tests in a separate JVM. (Since Ant 1.10.6) Default: `false`.                                          | No       |

### Nested Elements in Groovy:

Nested elements like `classpath`, `test`, `testclasses`, `listener`, and `jvmarg` (if forking) are configured using closures or method calls within the `junitlauncher` block.

*   **`classpath`**: `ant.classpath { pathelement(location: 'path/to/libs') }` or `ant.classpath(refid: 'defined.path.id')`
*   **`test`**: `ant.test(name: 'com.example.MyTestClass', methods: 'testMethod1,testMethod2')`
*   **`testclasses`**: `ant.testclasses(outputdir: 'reports/junitlauncher') { fileset(dir: 'build/test-classes') { include(name: '**/*Test.class') } }`
*   **`listener`**: `ant.listener(type: 'legacy-xml', sendSysOut: true, sendSysErr: true)` or `ant.listener(classname: 'com.example.CustomListener', outputDir: 'reports/custom-listener')`
*   **`jvmarg`** (when `fork: true`): `ant.jvmarg(value: '-Xmx1024m')`
*   **`sysproperty`** (when `fork: true`): `ant.sysproperty(key: 'my.prop', value: 'prop.value')`

### Example Groovy DSL Usage:

```groovy
def ant = new AntBuilder()
def reportsDir = "build/reports/junitlauncher"

ant.mkdir(dir: reportsDir)

// Define classpaths for JUnit 5 libraries and test classes
ant.path(id: "junit.platform.libs") {
    fileset(dir: "path/to/junit-platform-libs") { include(name: "*.jar") }
}
ant.path(id: "junit.jupiter.engine.libs") {
    fileset(dir: "path/to/junit-jupiter-libs") { include(name: "*.jar") }
}
ant.path(id: "test.classes.path") {
    pathelement(location: "build/test-classes")
}

// Assuming ant-junitlauncher.jar is in ANT_HOME/lib
// Otherwise, define the task:
// ant.taskdef(name: 'junitlauncher', classname: 'org.apache.tools.ant.taskdefs.optional.junitlauncher.confined.JUnitLauncherTask')

ant.junitlauncher(haltOnFailure: true, printSummary: true, fork: true) {
    classpath(refid: "junit.platform.libs")
    classpath(refid: "junit.jupiter.engine.libs")
    classpath(refid: "test.classes.path")

    testclasses(outputdir: reportsDir) {
        fileset(dir: "build/test-classes") {
            include(name: "**/*Test.class")
            exclude(name: "**/*IntegrationTest.class")
        }
        listener(type: "legacy-xml", sendSysOut: true, sendSysErr: true)
        listener(type: "legacy-brief", sendSysOut: true)
    }

    // Example of running a single test class
    // test(name: "com.example.SpecificUnitTest")

    // Example of filtering by tags
    // includeTags("fast-tests, smoke")
    // excludeTags("slow-tests")

    jvmarg(value: "-Xmx512m")
    sysproperty(key: "environment", value: "test")
}

ant.echo("JUnit 5 tests completed via JUnitLauncher. Reports are in ${reportsDir}")
```

### Important Considerations for JUnitLauncher with Groovy/AntBuilder:

1.  **JUnit 5 Library Dependencies**: Ensure all necessary JUnit 5 platform and engine JARs are on the classpath. This is crucial for the task to find and execute tests correctly.
2.  **Classpath Configuration**: Use the nested `<classpath>` element to manage dependencies for the tests, including the test classes themselves and the JUnit libraries.
3.  **Test Selection**: Tests can be selected using nested `<test>` elements for individual classes/methods or `<testclasses>` with nested `<fileset>` for batch execution.
4.  **Listeners/Formatters**: Use nested `<listener>` elements to configure test reporting. `legacy-xml` and `legacy-brief` are common for generating XML reports and console summaries, respectively.
5.  **Forking**: If tests need to run in a separate JVM (e.g., to manage memory, system properties, or avoid interference), set `fork: true` and use nested `<jvmarg>` and `<sysproperty>` as needed.
6.  **Tagging**: Leverage `includeTags` and `excludeTags` attributes for fine-grained test selection based on JUnit 5 tags.

## Examples (Ant XML)

### Example 1: Basic Test Execution with Jupiter Engine

```xml
<project name="JUnitLauncherExample" default="test" basedir=".">
    <property name="src.test.dir" location="src/test/java"/>
    <property name="build.test.classes.dir" location="build/test-classes"/>
    <property name="reports.junitlauncher.dir" location="build/reports/junitlauncher"/>
    <property name="junit.platform.lib.dir" location="path/to/junit-platform-libs"/>
    <property name="junit.jupiter.lib.dir" location="path/to/junit-jupiter-libs"/>

    <path id="test.compile.classpath">
        <fileset dir="${junit.jupiter.lib.dir}" includes="junit-jupiter-api-*.jar"/>
    </path>

    <path id="test.runtime.classpath">
        <pathelement location="${build.test.classes.dir}"/>
        <fileset dir="${junit.platform.lib.dir}" includes="*.jar"/>
        <fileset dir="${junit.jupiter.lib.dir}" includes="*.jar"/>
        <!-- Add other dependencies for tests if any -->
    </path>

    <target name="init">
        <mkdir dir="${build.test.classes.dir}"/>
        <mkdir dir="${reports.junitlauncher.dir}"/>
    </target>

    <target name="compile-tests" depends="init">
        <javac srcdir="${src.test.dir}"
               destdir="${build.test.classes.dir}"
               classpathref="test.compile.classpath"
               includeantruntime="false"/>
    </target>

    <target name="test" depends="compile-tests">
        <junitlauncher printSummary="true" haltOnFailure="true">
            <classpath refid="test.runtime.classpath"/>
            <testclasses outputdir="${reports.junitlauncher.dir}">
                <fileset dir="${build.test.classes.dir}">
                    <include name="**/*Test.class"/>
                </fileset>
                <listener type="legacy-xml" sendSysOut="true"/>
                <listener type="legacy-brief" sendSysOut="true"/>
            </testclasses>
        </junitlauncher>
    </target>
</project>
```
*Description: This example compiles JUnit 5 tests and then runs them using `junitlauncher`. It configures classpaths for JUnit 5 platform and Jupiter engine libraries. Tests are discovered using a fileset, and results are reported in XML and brief console summary formats. The build halts on any test failure.*

### Example 2: Running a Single Test Class and Method, Forking JVM

```xml
<project name="SingleJUnitLauncherTest" default="run-single">
    <property name="test.class.name" value="com.example.MySpecificJupiterTest"/>
    <property name="test.method.name" value="testFeatureX"/>
    <property name="reports.dir" location="build/reports/single-test"/>
    <path id="test.cp">
        <!-- Define your test classpath including JUnit 5 libs and compiled tests -->
        <pathelement location="path/to/compiled/tests"/>
        <fileset dir="path/to/junit5-libs" includes="*.jar"/>
    </path>

    <target name="init-single">
        <mkdir dir="${reports.dir}"/>
    </target>

    <target name="run-single" depends="init-single">
        <junitlauncher fork="true" printSummary="true">
            <classpath refid="test.cp"/>
            <test name="${test.class.name}" methods="${test.method.name}" outputdir="${reports.dir}">
                <listener type="legacy-xml"/>
            </test>
            <jvmarg value="-Xmx1g"/>
            <sysproperty key="database.url" value="jdbc:h2:mem:testdb"/>
        </junitlauncher>
    </target>
</project>
```
*Description: This example runs a specific method (`testFeatureX`) within a single test class (`com.example.MySpecificJupiterTest`). The tests are forked into a new JVM with 1GB of heap space and a system property `database.url` is set. An XML listener reports results to the specified output directory for the test.*

### Example 3: Using Tags to Include/Exclude Tests

```xml
<project name="TaggedJUnitLauncherTest" default="test-tagged">
    <path id="test.classpath.all">
        <!-- Define your full test classpath -->
    </path>
    <property name="reports.tagged.dir" location="build/reports/tagged-tests"/>

    <target name="test-tagged">
        <mkdir dir="${reports.tagged.dir}"/>
        <junitlauncher includeTags="smoke | regression" excludeTags="slow, flaky" printSummary="true">
            <classpath refid="test.classpath.all"/>
            <testclasses outputdir="${reports.tagged.dir}">
                <fileset dir="path/to/test/classes">
                    <include name="**/*Test.class"/>
                </fileset>
                <listener type="legacy-xml"/>
            </testclasses>
        </junitlauncher>
    </target>
</project>
```
*Description: This example demonstrates using tags to control test execution. It includes tests tagged with either `smoke` OR `regression`, but excludes any tests tagged with `slow` OR `flaky`. This allows for flexible test suite execution based on JUnit 5 tagging conventions.*

