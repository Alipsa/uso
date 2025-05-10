## Ant Tasks: Exec (Groovy DSL)

The `exec` task in Ant is used to execute system commands. This is useful for running external programs or scripts as part of your build process. Groovy AntBuilder allows you to use this task with a similar syntax.

### Basic Command Execution

*   **Ant XML:**
    ```xml
    <exec executable="mycommand">
        <arg line="-option1 value1"/>
        <arg value="-option2"/>
        <arg value="value2"/>
    </exec>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.exec(executable: "mycommand") {
        arg(line: "-option1 value1")
        arg(value: "-option2")
        arg(value: "value2")
    }
    ```

### Specifying the Operating System

You can make the execution OS-specific.

*   **Ant XML:**
    ```xml
    <exec executable="cmd" osfamily="windows">
        <arg value="/c"/>
        <arg value="dir"/>
    </exec>
    <exec executable="ls" osfamily="unix">
        <arg value="-l"/>
    </exec>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    // For Windows
    ant.exec(executable: "cmd", osfamily: "windows") {
        arg(value: "/c")
        arg(value: "dir")
    }
    // For Unix-like systems
    ant.exec(executable: "ls", osfamily: "unix") {
        arg(value: "-l")
    }
    ```

### Setting Environment Variables for the Command

*   **Ant XML:**
    ```xml
    <exec executable="myscript.sh">
        <env key="MY_VARIABLE" value="my_value"/>
    </exec>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.exec(executable: "myscript.sh") {
        env(key: "MY_VARIABLE", value: "my_value")
    }
    ```

### Navigation

*   [Previous: Move Task](10-Ant_Tasks_Move_Groovy.md)
*   [Table of Contents](00-Introduction_Groovy_Ant_Manual.md)

These examples cover the typical uses of the `exec` task in Ant and their translations to Groovy AntBuilder DSL. The Groovy syntax simplifies the build script while retaining the functionality of the Ant task.
