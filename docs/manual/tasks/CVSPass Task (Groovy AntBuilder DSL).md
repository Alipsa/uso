## CVSPass Task (Groovy AntBuilder DSL)

### Original Ant Task Description

The `cvspass` Ant task is used to add entries to a `.cvspass` file. This file stores CVS login information, and adding entries to it has the same effect as executing a `cvs login` command. This allows subsequent CVS tasks to authenticate without requiring interactive password input.

**CVSNT Note**: It's important to be aware that CVSNT (a CVS server and client for Windows) prefers to store passwords in the Windows registry. In such cases, CVSNT might ignore the `.cvspass` file entirely. If the `cvspass` task doesn't seem to work with CVSNT, this is the most likely reason. Refer to [bugzilla report 21657](https://bz.apache.org/bugzilla/show_bug.cgi?id=21657) for potential workarounds.

### Groovy AntBuilder DSL Syntax

In Groovy, the `cvspass` task is invoked as a method on the `ant` builder instance. The attributes of the Ant task are passed as named arguments to the method.

```groovy
ant.cvspass(cvsroot: 'value', password: 'value', passfile: 'value')
```

### Parameters

The Groovy AntBuilder DSL uses the same parameters as the original Ant task:

| Attribute | Description                                     | Type   | Required | Groovy DSL Notes                                  |
|-----------|-------------------------------------------------|--------|----------|---------------------------------------------------|
| `cvsroot` | The CVS repository string to add an entry for.  | String | Yes      | e.g., `:pserver:user@host:/path/to/repository`    |
| `password`| The password to be added to the password file.  | String | Yes      |                                                   |
| `passfile`| The password file to add the entry to.          | File   | No       | Defaults to `~/.cvspass` (user's home directory). |

### Examples

Here's how you can use the `cvspass` task in both traditional Ant XML and Groovy AntBuilder DSL.

**Ant XML Example:**

This example adds an entry for an anonymous CVS server to the default `~/.cvspass` file.

```xml
<project name="CVSPassExample" default="run-cvspass">
    <target name="run-cvspass">
        <cvspass cvsroot=":pserver:anoncvs@cvs.apache.org:/home/cvspublic"
                 password="anoncvs"/>
        <echo message="CVSPass entry added for anoncvs@cvs.apache.org"/>
    </target>
</project>
```

**Groovy AntBuilder DSL Example:**

This Groovy script achieves the same as the XML example above.

```groovy
// build.gradle or any Groovy script

ant.project(name: 'CVSPassExampleGroovy', default: 'run-cvspass') {
    target(name: 'run-cvspass') {
        ant.cvspass(cvsroot: ':pserver:anoncvs@cvs.apache.org:/home/cvspublic',
                    password: 'anoncvs')
        ant.echo(message: 'CVSPass entry added for anoncvs@cvs.apache.org via Groovy')
    }
}
```

**Groovy Example with a specific passfile:**

This example demonstrates specifying a custom path for the `.cvspass` file.

```groovy
// build.gradle or any Groovy script

ant.project(name: 'CVSPassCustomFileExampleGroovy', default: 'run-custom-cvspass') {
    target(name: 'run-custom-cvspass') {
        def userHome = System.getProperty("user.home")
        ant.cvspass(cvsroot: ':pserver:myuser@mycvs.example.com:/my/repo',
                    password: 'securepassword123',
                    passfile: "${userHome}/.my_custom_cvspass_file")
        ant.echo(message: "CVSPass entry added to custom file for myuser@mycvs.example.com")
    }
}
```

---

**Next:** [Task Name_Task_Groovy.md](Task Name_Task_Groovy.md) (To be updated)

**Previous:** [Task Name_Task_Groovy.md](Task Name_Task_Groovy.md) (To be updated)

**Parent:** [00-Introduction_Groovy_Ant_Manual.md](00-Introduction_Groovy_Ant_Manual.md)

