## Sequential Task (Groovy DSL)

### Description

The `sequential` task in Ant is a container that groups nested tasks to be executed in order. This is especially useful within a `parallel` task to ensure a specific sequence of operations. In Groovy AntBuilder, use `ant.sequential { ... }` to define sequential execution of tasks.

### Parameters

The `sequential` task does not support any attributes.

### Nested Elements

The `sequential` task only accepts valid Ant tasks as nested elements. Place any Ant task inside the closure to be executed sequentially.

### Examples

1. **Basic usage:**
   ```groovy
   sequential {
       echo(message: 'First step')
       echo(message: 'Second step')
   }
   ```
2. **Within a parallel block:**
   ```groovy
   parallel {
       wlrun(command: 'startProcess')
       sequential {
           sleep(seconds: 30)
           junit(printsummary: 'yes', haltonerror: 'true') {
               test(name: 'com.example.MyTest')
           }
           wlstop()
       }
   }
   ```

### Important Considerations

* By default, Ant executes tasks sequentially. Use `sequential` primarily within tasks like `parallel` to control the order of nested tasks.
* The `sequential` task itself does not produce output or return values; its purpose is solely to group tasks.

### Navigation

* [Previous Task: Scriptdef Task](scriptdef_task.md)
* [Next Task: ServerDeploy Task](serverdeploy_task.md)
* [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
* [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
