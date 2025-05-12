## AntStructure Task (Groovy DSL)

### Description

The `AntStructure` task is used to generate a DTD (Document Type Definition) for Ant build files. This DTD describes the structure of Ant build files, including tasks, attributes, and their relationships, based on the tasks currently known to Ant.

In Groovy AntBuilder, the `antstructure` task is invoked as `ant.antstructure(...)`. Its attributes are passed as named parameters.

### Parameters

*   `output`: The name of the DTD file to generate. (Required) Groovy: `output: "my_ant_structure.dtd"`

### Nested Elements

The `AntStructure` task does not typically have nested elements for its core functionality of DTD generation.

### Examples

1.  **Generating a DTD for the current Ant environment:**

    *   **Ant XML:**
        ```xml
        <antstructure output="project_structure.dtd"/>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.antstructure(output: "project_structure.dtd")
        ```
    This will create a file named `project_structure.dtd` in the current directory, containing the DTD for all tasks known to the Ant runtime.

### Navigation

*   [Previous Task: AntCall Task](AntCall_Task_Groovy.md)
*   [Next Task: AntVersion Task](AntVersion_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
