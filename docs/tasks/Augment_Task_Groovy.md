## Augment Task (Groovy DSL)

### Description

The `augment` task is used to add attributes or nested elements to existing references that were created using `ant` or `antcall` with an `id` attribute, or by tasks like `path` with an `id`. This allows for modification or extension of pre-defined configurations or data types.

In Groovy AntBuilder, the `augment` task is invoked as `ant.augment(...)`. Its primary attribute is `id`, which specifies the reference to be augmented. Nested elements within the `augment` block define what is being added to that reference.

### Parameters

*   `id`: The `id` of the existing reference that will be augmented. (Required)
    *   Groovy: `id: 'my.existing.reference'`

### Nested Elements

The elements nested within an `augment` call depend on the type of reference being augmented. For example, if augmenting a `path` reference, you might add `pathelement` or `fileset` elements.

### Examples

Let's assume you have a predefined path:

**Ant XML (Original Definition):**
```xml
<path id="compile.classpath">
  <pathelement location="lib/main.jar"/>
</path>
```

**Groovy AntBuilder (Original Definition):**
```groovy
ant.path(id: 'compile.classpath', {
    pathelement(location: 'lib/main.jar')
})
```

Now, you want to augment this classpath with another JAR:

1.  **Augmenting a Path:**

    *   **Ant XML:**
        ```xml
        <augment id="compile.classpath">
          <pathelement location="lib/another.jar"/>
        </augment>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.augment(id: 'compile.classpath') {
            pathelement(location: 'lib/another.jar')
        }
        ```
    This would add `lib/another.jar` to the `compile.classpath`.

2.  **Augmenting with a Fileset:**

    *   **Ant XML:**
        ```xml
        <augment id="compile.classpath">
          <fileset dir="ext_lib">
            <include name="*.jar"/>
          </fileset>
        </augment>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.augment(id: 'compile.classpath') {
            fileset(dir: 'ext_lib') {
                include(name: '*.jar')
            }
        }
        ```
    This adds all JAR files from the `ext_lib` directory to the `compile.classpath`.

### Important Considerations

*   The reference specified by the `id` attribute must exist before `augment` is called.
*   The elements nested within `augment` must be compatible with the type of reference being augmented (e.g., you can't add a `<srcfile>` from a `javac` task to a `path` reference).
*   `augment` modifies the existing reference in place.

### Navigation

*   [Previous Task: Attrib Task](Attrib_Task_Groovy.md)
*   [Next Task: Available Task](Available_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
