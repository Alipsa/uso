# splash

## Defining the Task

The `splash` task is part of Ant's optional tasks. You must define it before use by providing the optional Ant jar
(`org.apache.ant:ant-swing:1.10.15`) on the classpath:

```groovy
// Register the splash task
taskdef(
  name: 'splash',
  classname: 'org.apache.tools.ant.taskdefs.optional.splash.SplashTask'
)

```


The `splash` task displays a graphical splash screen during the build, optionally showing a progress bar, custom image, and display text. It can parse build output to update progress and supports deprecated proxy settings for remote images.

## Usage Examples

### Default splash (using bundled logo)

```groovy
target('defaultSplash') {
  splash()
}
```

### Custom image with proxy and duration

```groovy
target('customSplash') {
  splash(
    imageurl: 'https://jakarta.apache.org/images/jakarta-logo.gif',
    useproxy: true,
    showduration: 5000
  )
}
```

### Controlled progress and custom text

```groovy
target('progressSplash') {
  echo 'Starting feature tests'
  splash(
    progressregexp: 'Progress: (.*)%',
    showduration: 0,
    displaytext: 'Testing features...'
  )
  sleep(seconds: 1)
  echo 'Progress: 10%'
  sleep(seconds: 1)
  echo 'Progress: 50%'
  sleep(seconds: 1)
  echo 'Progress: 100%'
  sleep(seconds: 3)
}
```

## Attributes

| Attribute        | Description                                                                                | Required |
|------------------|--------------------------------------------------------------------------------------------|----------|
| `imageurl`       | URL pointing to the image to display. Defaults to `antlogo.gif` from the classpath.        | No       |
| `showduration`   | Initial display duration in milliseconds before progress updates. Defaults to `5000`.      | No       |
| `progressregexp` | Regular expression to parse progress from build output (must contain one capturing group). | No       |
| `displaytext`    | Text to display in the splash window. Defaults to `"Building ..."`.                        | No       |
| `useproxy`       | Whether to use proxy settings to fetch remote images (deprecated in favor of `setproxy`).  | No       |
| `proxy`          | Proxy host (deprecated).                                                                   | No       |
| `port`           | Proxy port (deprecated).                                                                   | No       |
| `user`           | Proxy username (deprecated).                                                               | No       |
| `password`       | Proxy password (deprecated).                                                               | No       |

> **Note:** The proxy-related attributes (`useproxy`, `proxy`, `port`, `user`, `password`) are deprecated; prefer using the `setproxy` task.

When using this from AntBuilder, the splash screen will "hang" there forever.
The reason your build never “finishes” when you launch the splash via AntBuilder is that the splash task spins up a Swing window on the AWT Event Dispatch Thread (EDT). That thread is non-daemon—so the JVM will stay alive even after Ant’s work is done.

To handle this, you can either:
- Call System.exit(0) at the end of your AntBuilder script. That will kill the JVM (and the EDT) immediately.
- Run the splash in a separate JVM, so killing that JVM doesn’t affect your main build. For example, you could invoke the splash via an <exec> or <java> task with fork="true" and spawn="true", which launches it in a background process you can later kill without touching AntBuilder’s JVM

Here is a complete example of the System.exit() approach:

```groovy
@GrabConfig(systemClassLoader=true)
@Grab('org.apache.ant:ant-swing:1.10.15')
import groovy.ant.AntBuilder
import org.apache.tools.ant.Project

def ant = new AntBuilder()
ant.with {
  taskdef(name: 'splash', classname: 'org.apache.tools.ant.taskdefs.optional.splash.SplashTask')
  splash(
    imageurl: 'https://jakarta.apache.org/images/jakarta-logo.gif',
    showduration: 2000
  )
  buildnumber(file: "custom_build_count.txt")
  def customBuild = project.getProperty("build.number")
  echo(message: "Current Custom Build Number: $customBuild")
}
System.exit(0)
```

## Notes

- Introduced in Ant 1.8.0.
- Requires a GUI environment (Java AWT); headless mode may not support splash.
- The splash screen appears before build output and closes when the build ends or progress reaches 100%.
- Use `showduration: 0` to display the splash until progress updates complete.

## Reference

- [Ant Manual: splash Task](https://ant.apache.org/manual/Tasks/splash.html)