# scriptdef

The `scriptdef` task defines a new Ant task or data type using an embedded script (BSF or JSR-223). It allows you to write custom tasks inline in a scripting language and invoke them like built-in Ant tasks.

## Usage Examples

### Define and use a simple Groovy-based task

```groovy
project.with {
  scriptdef(name: 'helloTask', language: 'groovy') {
    attribute(name: 'greeting', default: 'Hello')
    element(name: 'names', optional: true)
    script {
      def greet = attributes['greeting']
      elements['names']?.each { name ->
        project.log("${greet}, ${name}")
      }
    }
  }

  target('sayHello', depends: 'helloTask') {
    helloTask(greeting: 'Hi') {
      names('Alice', 'Bob', 'Charlie')
    }
  }
}
```

### Define and use a JavaScript-based task

```groovy
taskdef(name: 'scriptdef', classname: 'org.apache.tools.ant.taskdefs.optional.script.ScriptDef')
target {
  scriptdef(name: 'jsTask', language: 'javascript') {
    attribute(name: 'message', default: 'Hello from JS')
    script {
      // In JavaScript, 'attributes' and 'project' are provided by Ant
      var msg = attributes.get('message');
      project.log('JS says: ' + msg);
    }
  }

  // Define a target to invoke our new jsTask
  target('runJsTask', depends: 'jsTask') {
    jsTask(message: 'Custom JS greeting!')
  }
}
```

## Attributes

|      Attribute | Description                                                                             | Required |
|---------------:|-----------------------------------------------------------------------------------------|----------|
|         `name` | Name of the new task or data type to define                                             | Yes      |
|     `language` | Scripting language (e.g., `groovy`, `javascript`, `jython`) supported by BSF or JSR-223 | Yes      |
|      `manager` | Engine manager: `auto`, `bsf`, or `javax` (default: `auto`)                             | No       |
|    `classpath` | Classpath for locating script engine or additional libraries                            | No       |
| `classpathref` | Reference to an existing Ant `<path>` element for classpath                             | No       |
|          `src` | Path to an external script file                                                         | No       |
|     `encoding` | Encoding for reading external script files (default: platform encoding)                 | No       |

## Nested Elements

- `<attribute>` — Declare an attribute for the custom task; use `name` and optional `default`.
- `<element>` — Declare nested element(s) for the custom task (e.g., lists, resource collections).
- `<script>` — Embed inline script code for the task body.

## Notes

- `scriptdef` must be declared on the Ant `project`, not on a raw `AntBuilder`, so that the task context—especially `attributes` and `elements`—is correctly bound.
- Script-based tasks run in the context of the Ant project; you can access `project` (the Ant `Project` instance) and its methods.
- For modern Java, prefer JSR-223 (`manager="javax"`) to avoid BSF dependencies.
- To keep build files clean, consider using `src` to reference external script files.

## Reference

- [Ant Manual: scriptdef Task](https://ant.apache.org/manual/Tasks/scriptdef.html)
