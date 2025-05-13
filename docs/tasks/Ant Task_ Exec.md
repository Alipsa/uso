# Ant Task: Exec

## Original Ant Task Description

The `exec` task executes a system command. It allows for platform-specific execution and provides options for handling input/output streams, error conditions, and environment variables.

When the `os` attribute is specified, the command is only executed if Apache Ant is running on one of the specified operating systems. Similarly, the `osfamily` attribute can be used for OS family-specific execution.

It's important to note that interaction with the forked program is not possible; input can only be sent via the `input` or `inputstring` attributes. Since Ant 1.6, any attempt by the forked program to read input will receive an EOF (-1).

For Windows users, the task delegates to `Runtime.exec`, which calls the Win32 function `CreateProcess`. This means that if no file extension is provided for the executable, only `.EXE` files are typically found. Batch files (`.bat`) usually require `cmd /c` to be executed.

For Cygwin users, paths like `/bin/sh` are not directly understood by the JVM. A workaround might involve a Cygwin-compiled JVM.

For OpenVMS users, commands are executed via a temporary DCL script. Paths should be in VMS style, and DCL scripts should be prefixed with `@`.

## Groovy AntBuilder DSL Equivalent

The Groovy `AntBuilder` provides a direct way to use the `exec` task. The syntax is very similar to the Ant XML structure, with attributes becoming parameters to the method call and nested elements becoming closures.

```groovy
ant = new AntBuilder()

ant.exec(executable: "mycommand") {
    // Nested elements like arg, env, etc.
    arg(value: "--option1")
    arg(line: "argument2 argument3")
    env(key: "MY_VARIABLE", value: "my_value")
}
```

## Parameter Mapping

The Ant XML attributes for the `exec` task map to parameters in the Groovy `AntBuilder` method call. Nested elements like `<arg>`, `<env>`, and `<redirector>` are handled as closures.

| Ant Attribute        | Groovy Parameter     | Description                                                                                                | Required                                  |
|----------------------|----------------------|------------------------------------------------------------------------------------------------------------|-------------------------------------------|
| `executable`         | `executable`         | The command to execute without any command line arguments.                                                 | Yes (or `command`)                        |
| `command`            | `command`            | *Deprecated*. The command to execute with all command line arguments. Use `executable` and nested `<arg>`. | Yes (or `executable`)                     |
| `dir`                | `dir`                | The directory in which the command should be executed.                                                     | No                                        |
| `os`                 | `os`                 | List of Operating Systems on which the command may be executed.                                            | No                                        |
| `osfamily`           | `osfamily`           | OS family as used in the `<os>` condition. (Since Ant 1.7)                                               | No                                        |
| `spawn`              | `spawn`              | If `true`, the command is spawned, and Ant does not wait for it. Output is not logged. (Since Ant 1.6)     | No; defaults to `false`                   |
| `output`             | `output`             | Name of a file to which to write the standard output.                                                      | No                                        |
| `error`              | `error`              | The file to which the standard error of the command should be redirected. (Since Ant 1.6)                  | No                                        |
| `logError`           | `logError`           | If `true`, error output is sent to Ant's log when output is redirected. (Since Ant 1.6)                  | No; defaults to `false`                   |
| `append`             | `append`             | If `true`, output and error files are appended to; otherwise, they are overwritten.                        | No; defaults to `false`                   |
| `outputproperty`     | `outputproperty`     | The name of a property in which the output of the command should be stored.                                | No                                        |
| `errorproperty`      | `errorproperty`      | The name of a property in which the standard error of the command should be stored. (Since Ant 1.6)        | No                                        |
| `input`              | `input`              | A file from which the executed command's standard input is taken. (Mutually exclusive with `inputstring`)    | No                                        |
| `inputstring`        | `inputstring`        | A string which serves as the input stream for the executed command. (Mutually exclusive with `input`)        | No                                        |
| `resultproperty`     | `resultproperty`     | The name of a property in which the return code of the command should be stored.                           | No                                        |
| `timeout`            | `timeout`            | Stop the command if it doesn't finish within the specified time (in milliseconds).                         | No                                        |
| `failonerror`        | `failonerror`        | If `true`, stop the build process if the command exits with a non-zero return code.                        | No; defaults to `false`                   |
| `failifexecutionfails`| `failifexecutionfails`| If `true`, stop the build if the program cannot be started.                                                | No; defaults to `true`                    |
| `newenvironment`     | `newenvironment`     | If `true`, do not propagate the old environment when new environment variables are specified.              | No; defaults to `false`                   |
| `vmlauncher`         | `vmlauncher`         | If `true` (default), use JVM's execution facilities. If `false`, use OS shell.                           | No; defaults to `true`                    |
| `resolveexecutable`  | `resolveexecutable`  | If `true`, resolve the executable path against `basedir`, then `dir`, then system `PATH`.                  | No; defaults to `false`                   |

### Nested Elements

*   **`<arg>`**: Represents a command-line argument. Can have `value`, `line`, `path`, `pathref`, or `file` attributes.
    ```groovy
    ant.exec(executable: "mytool") {
        arg(value: "--verbose")
        arg(line: "-input /path/to/input -output /path/to/output")
        arg(path: "/usr/local/lib:/opt/lib") // for PATH-like arguments
    }
    ```
*   **`<env>`**: Defines an environment variable for the executed command. Requires `key` and `value` (or `path`, `file`).
    ```groovy
    ant.exec(executable: "myprogram") {
        env(key: "MY_VAR", value: "some_value")
        env(key: "ANOTHER_VAR", path: "/some/path:/another/path")
    }
    ```
*   **`<redirector>`**: Allows for advanced I/O redirection, including mapping input/output/error streams and using filter chains. (Since Ant 1.6)
    ```groovy
    ant.exec(executable: "cat") {
        redirector(outputproperty: "cat.output", errorproperty: "cat.error", inputstring: "Hello World") {
            inputfilterchain {
                replacestring(from: "World", to: "Groovy")
            }
            // outputmapper and errormapper can also be used here
        }
    }
    ```

## Code Examples

### Example 1: Simple Execution

**Ant XML:**
```xml
<project name="ExecExample1" default="run">
    <target name="run">
        <exec executable="ls">
            <arg value="-l"/>
            <arg value="/tmp"/>
        </exec>
    </target>
</project>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    exec(executable: "ls", failonerror: true) {
        arg(value: "-l")
        arg(value: "/tmp")
    }
}
println "Executed ls -l /tmp"
```

### Example 2: Executing a Windows Batch File and Capturing Output

**Ant XML:**
```xml
<project name="ExecExample2" default="run-batch">
    <target name="run-batch">
        <exec executable="cmd" outputproperty="batch.output" osfamily="windows">
            <arg value="/c"/>
            <arg value="mybatch.bat"/>
            <arg value="param1"/>
        </exec>
        <echo message="Batch output: ${batch.output}"/>
    </target>
</project>
```
*Assumes `mybatch.bat` is in the path or current directory.*

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    def os = System.getProperty("os.name").toLowerCase()
    if (os.contains("windows")) {
        exec(executable: "cmd", outputproperty: "batch.output", failonerror: true) {
            arg(value: "/c")
            arg(value: "mybatch.bat") // Ensure mybatch.bat is accessible
            arg(value: "param1")
        }
        echo(message: "Batch output: ${ant.project.getProperty('batch.output')}")
    } else {
        echo(message: "Skipping Windows-specific batch execution.")
    }
}
```

### Example 3: Setting Environment Variables and Working Directory

**Ant XML:**
```xml
<project name="ExecExample3" default="run-with-env">
    <target name="run-with-env">
        <exec executable="python" dir="/home/user/scripts">
            <env key="PYTHONPATH" value=".:/opt/custom_libs"/>
            <arg value="myscript.py"/>
        </exec>
    </target>
</project>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    exec(executable: "python", dir: "/home/user/scripts", failonerror: true) {
        env(key: "PYTHONPATH", value: ".:/opt/custom_libs")
        arg(value: "myscript.py")
    }
}
println "Executed python script with custom PYTHONPATH"
```

### Example 4: Using `spawn` and `timeout`

**Ant XML:**
```xml
<project name="ExecExample4" default="spawn-process">
    <target name="spawn-process">
        <!-- Spawn a long-running process but don't wait, timeout if it runs too long (if not spawned) -->
        <exec executable="long_running_server" spawn="true"/>
        
        <!-- Execute a command with a timeout -->
        <exec executable="short_task_with_timeout" timeout="5000" resultproperty="task.result">
            <arg value="--quick"/>
        </exec>
        <echo message="Short task result: ${task.result}"/>
    </target>
</project>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    // Spawn a long-running process
    exec(executable: "long_running_server", spawn: true)
    echo("Spawned long_running_server (Ant won't wait)")

    // Execute a command with a timeout
    exec(executable: "sleep", timeout: "1000", resultproperty: "sleep.result", failonerror: false) { // Using sleep for a testable timeout
        arg(value: "5") // Try to sleep for 5s, but timeout is 1s
    }
    def sleepResult = ant.project.getProperty('sleep.result')
    echo("Sleep task result (should indicate timeout, e.g., -1 or specific OS code): ${sleepResult}")
}
```

### Example 5: Using `redirector` with `inputstring` and `outputproperty`

**Ant XML:**
```xml
<project name="ExecExample5" default="redirect-io">
    <target name="redirect-io">
        <exec executable="cat" outputproperty="cat.output" errorproperty="cat.error">
            <redirector inputstring="Hello from Ant Exec via Redirector">
                <inputfilterchain>
                    <replacestring from="Ant" to="Groovy AntBuilder"/>
                </inputfilterchain>
            </redirector>
        </exec>
        <echo message="Output: ${cat.output}"/>
        <echo message="Error: ${cat.error}"/>
    </target>
</project>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().sequential {
    exec(executable: "cat", failonerror: true) {
        redirector(outputproperty: "cat.output.groovy", errorproperty: "cat.error.groovy", inputstring: "Hello from Ant Exec via Redirector") {
            inputfilterchain {
                replacestring(from: "Ant", to: "Groovy AntBuilder")
            }
        }
    }
    echo(message: "Groovy Output: ${ant.project.getProperty('cat.output.groovy')}")
    echo(message: "Groovy Error: ${ant.project.getProperty('cat.error.groovy')}")
}
```

## Notes

*   When using `failonerror=
