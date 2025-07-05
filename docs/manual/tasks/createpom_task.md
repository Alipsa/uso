# createPom

The `createPom` task generates a Maven POM file based on the current project’s coordinates, dependencies, and additional POM customization. It registers the resulting POM as an Ant property reference for later use (e.g., publishing).

This task is _automatically_ registered when using Uso. If you use Ant or AntBuilder directly, you must first register the task with
`taskdef(name: 'createPom', classname: 'se.alipsa.uso.tasks.CreatePom')`
This depends on the jar for the dependency `se.alipsa.uso:uso-tasks:0.0.5` to be available on the classpath.


## Usage Example

```groovy
dependencies(id: 'deps') {
  dependency(coords: 'se.alipsa.matrix:matrix-core:3.2.0')
  dependency(coords: 'se.alipsa.matrix:matrix-csv:2.1.0')
  dependency(coords: 'org.junit.jupiter:junit-jupiter-engine:5.12.2:test')
  dependency(coords: 'org.junit.platform:junit-platform-launcher:1.12.2:test')
}

target(name: 'pom') {
  def pomFile = "${buildDir}/${artifactId}-${version}.pom"
  echo "Creating and registering the pom file ${pomFile}"

  createPom(
    pomTarget: pomFile,
    dependenciesRef: 'deps',
    name: 'publish-example',
    description: 'A simple example of a publishable library'
  ) {
    licenses {
      license(
        name: "MIT",
        url: "https://opensource.org/license/mit"
      )
    }
    repositories {
      repository(id: 'jitpack.io', url: 'https://jitpack.io')
    }
    developers {
      developer(
        name: 'Per Nyfelt',
        email: 'per.nyfelt@alipsa.se',
        organization: 'Alipsa HB',
        organizationUrl: 'http://www.alipsa.se'
      )
    }
    scm(
      connection: 'scm:git:https://github.com/Alipsa/publish-example.git',
      developerConnection: 'scm:git:https://github.com/Alipsa/publish-example.git',
      url: 'https://github.com/Alipsa/publish-example/tree/main'
    )
  }
}
```

## Attributes

|         Attribute | Description                                                                 | Required |
|------------------:|-----------------------------------------------------------------------------|----------|
|       `pomTarget` | Path to the output POM file to generate                                     | Yes      |
| `dependenciesRef` | Reference ID of the resolved dependencies (classpath) to include in the POM | Yes      |
|            `name` | Artifact name for the POM (defaults to `artifactId` property)               | No       |
|     `description` | Project description to include in the POM                                   | No       |
|             `url` | Project URL (homepage)                                                      | No       |
|   `inceptionYear` | Inception year of the project                                               | No       |

## Nested Elements

- `licenses` — Container for one or more `<license>` elements:
  - `license(name:, url:)`
- `repositories` — Container for `<repository>` elements:
  - `repository(id:, url:)`
- `developers` — Container for `<developer>` elements:
  - `developer(name:, email:, organization:, organizationUrl:)`
- `scm` — Single element for SCM metadata with attributes `connection`, `developerConnection`, and `url`.
- `organization` — (Optional) Single element for organization metadata: `name`, `url`.

## Notes

- Invoking `createPom` registers a property reference (default ID: `pom`) pointing to the generated POM file.
- Use the POM reference in subsequent `install` or `deploy` tasks to publish artifacts.
- Nested elements align with Maven POM structure for common metadata sections.

## Reference

- [CreatePom Task Source](https://github.com/Alipsa/uso/blob/main/uso-tasks/src/main/groovy/se/alipsa/uso/tasks/CreatePom.groovy)
- [Layout Example](https://github.com/Alipsa/uso/blob/main/examples/layout/build.groovy)
