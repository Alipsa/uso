# layout

The `layout` task initializes a standard project directory structure and sets Ant properties pointing to each directory based on the chosen layout type and programming language. It also creates directories on disk if they do not exist.

This task is _automatically_ registered when using Uso. If you use Ant or AntBuilder directly you must first register the task with
`taskdef(name: 'layout', classname: 'se.alipsa.uso.tasks.Layout')`
This depends on the jar for the dependency `se.alipsa.uso:uso-tasks:0.0.5` to be available on the classpath.

## Usage Example

```groovy
project.with {
  groupId = 'se.alipsa.uso.layout'
  artifactId = 'layout-example'
  version = '0.0.1'

  target('init') {
    // Set up Maven-style layout with Groovy sources and verbose logging
    layout(
      type: 'maven',
      language: 'groovy',
      logLevel: org.apache.tools.ant.Project.MSG_VERBOSE
    )
  }

  target(name: 'clean', depends: 'init') {
    delete(dir: '${buildDir}')
    mkdir(dir: '${mainClassesDir}')
  }

  target(name: 'compile', depends: 'init') {
    echo "Compiling main Groovy classes"
    groovyc(
      srcdir: '${mainSrcDir}',
      destdir: '${mainClassesDir}'
    )
  }
}
```

## Attributes

| Attribute   | Description                                                                                   | Default                         |
|------------:|-----------------------------------------------------------------------------------------------|---------------------------------|
|      `type` | Layout style: `maven`, `gradle`, or `simple`                                                  | `maven`                         |
|  `language` | Source language directory name (e.g., `groovy`, `java`, `scala`)                              | `groovy`                        |
|       `dir` | Base directory for the layout; defaults to project `basedir` property                         | Project base directory          |
|  `logLevel` | Ant log level for printed property names and values (e.g., `Project.MSG_INFO`, `MSG_VERBOSE`) | `Project.MSG_VERBOSE`           |

## Properties Set

After execution, the following Ant properties are defined (and directories created):

- `mainSrcDir` – Main source directory (`${dir}/src/main/${language}` for Maven/Gradle; `${dir}/src/code` for Simple)
- `testSrcDir` – Test source directory
- `mainResourcesDir` – Main resources directory
- `testResourcesDir` – Test resources directory
- `buildDir` – Output build directory (`target`, `build`, or `out`)
- `mainGeneratedDir` – Generated sources for main
- `testGeneratedDir` – Generated sources for tests
- `mainClassesDir` – Compiled main classes directory
- `testClassesDir` – Compiled test classes directory
- `testResultDir` – Test results (e.g., surefire reports) directory
- `testReportDir` – Test reports (e.g., site) directory
- `mainDocDir` – Documentation directory for main code
- `testDocDir` – Documentation directory for tests
- `distDir` – Distribution directory

## Notes

- The task creates each directory if it does not already exist.
- Choose `type` for popular conventions: Maven (src/main, target), Gradle (src/main, build), or Simple (src/code, out).
- Use `logLevel` to control verbosity: at `MSG_VERBOSE`, property assignments are printed.

## Reference

- [Layout Task Source](https://github.com/Alipsa/uso/blob/main/uso-tasks/src/main/groovy/se/alipsa/uso/tasks/Layout.groovy)
- [Example Build](https://github.com/Alipsa/uso/blob/main/examples/layout/build.groovy)
