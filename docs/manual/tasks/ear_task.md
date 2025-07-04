## Ear Task (Groovy DSL)

### Description

The `ear` task in Ant is an extension of the `jar` task, specifically designed to create Enterprise Application aRchive (EAR) files. It provides special treatment for files and directory structures that are standard in EAR archives, such as the `META-INF/application.xml` deployment descriptor.

Like the `jar` and `zip` tasks, it packages files and directories into an archive. The `ear` task offers a convenient shortcut for the specific layout of EAR files, though the same result can be achieved using `zip` or `jar` with appropriate `zipfileset` configurations (using `prefix` and `fullpath` attributes).

In Groovy AntBuilder, the task is invoked as `ant.ear(...)`.

### Parameters

Many parameters are inherited from the `jar` (and by extension, `zip`) task. Key parameters specific to or commonly used with `ear` include:

*   `destfile`: (Required) The name and path of the EAR file to be created.
    *   Groovy: `destfile: "dist/myApplication.ear"`
*   `appxml`: (Required, unless `update` is `true` and `application.xml` already exists in the archive) The path to the deployment descriptor file (e.g., `META-INF/application.xml` or a custom location). This file will be placed at `META-INF/application.xml` in the EAR archive.
    *   Groovy: `appxml: "src/main/application/META-INF/application.xml"`
*   `basedir`: The directory from which to archive files. Files found in this directory will be placed in the root of the EAR archive unless nested filesets specify otherwise.
    *   Groovy: `basedir: "build/staging"`
*   `compress`: (Boolean) Whether to compress the files. Defaults to `true`.
    *   Groovy: `compress: false` (to store uncompressed)
*   `update`: (Boolean) If `true`, updates an existing EAR file. If `false` (default), overwrites it.
    *   Groovy: `update: true`
*   `duplicate`: Defines behavior for duplicate file entries. Options: `add` (default), `preserve`, `fail`.
    *   Groovy: `duplicate: "preserve"`
*   `manifest`: Path to an external manifest file to be included.
    *   Groovy: `manifest: "src/main/application/META-INF/MANIFEST.MF"`
*   `filesetmanifest`: Behavior when a manifest is found in a nested `zipfileset`. Options: `skip` (default), `merge`, `mergewithoutmain`.
    *   Groovy: `filesetmanifest: "merge"`
*   `includes`, `excludes`, `includesfile`, `excludesfile`, `defaultexcludes`: Standard patterns for file inclusion/exclusion from `basedir`.
*   Many other attributes related to compression level, encoding, timestamps, and ZIP64 format are available, similar to the `zip` and `jar` tasks.

### Nested Elements

The `ear` task supports various nested elements, many inherited from `jar` and `zip`:

*   **`<fileset>`** (and other resource collections like `<zipfileset>`, `<filelist>`):
    Used to include files and directories. `zipfileset` is particularly useful for placing files into specific locations within the EAR archive using its `prefix` attribute.
    ```groovy
    ant.ear(destfile: "myApp.ear", appxml: "src/META-INF/application.xml") {
        fileset(dir: "build/main-classes")
        zipfileset(dir: "libs", prefix: "APP-INF/lib", includes: "*.jar")
        zipfileset(dir: "webapps", prefix: "", includes: "*.war") // WARs at root
    }
    ```

*   **`<metainf>`**:
    A special `zipfileset` that collects files to be placed in the `META-INF` directory of the EAR. The `application.xml` specified by the `appxml` attribute is automatically placed here, but `metainf` can add other files like `MANIFEST.MF` (if not using the `manifest` attribute) or vendor-specific descriptors.
    ```groovy
    ant.ear(destfile: "myApp.ear", appxml: "src/app/application.xml") {
        metainf(dir: "src/app/meta-inf-extras") {
            include(name: "*.xml")
            exclude(name: "application.xml") // Already handled by appxml attribute
        }
        // other filesets for modules
    }
    ```

*   **`<manifest>`**:
    Allows inline manifest creation or merging attributes from an existing manifest file.
    ```groovy
    ant.ear(destfile: "myApp.ear", appxml: "app.xml") {
        manifest {
            attribute(name: "Built-By", value: "Groovy AntBuilder")
            attribute(name: "Main-Class", value: "com.example.MyMain") // If applicable
        }
    }
    ```

*   **`<service>`**:
    For adding service provider configuration files (used with `java.util.ServiceLoader`).

*   **`<indexjars>`**:
    If the `index` attribute is true, this nested element can specify additional JARs to include in the generated `META-INF/INDEX.LIST`.

### Examples

1.  **Create a simple EAR file:**

    *   **Ant XML:**
        ```xml
        <ear destfile="myapplication.ear"
             appxml="src/descriptors/application.xml">
          <fileset dir="build/ejbclasses" includes="**/*.jar"/>
          <fileset dir="build/webapps" includes="**/*.war"/>
          <zipfileset dir="commonlibs" prefix="APP-INF/lib" includes="**/*.jar"/>
        </ear>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.ear(destfile: "myapplication.ear",
                  appxml: "src/descriptors/application.xml") {
            fileset(dir: "build/ejbclasses", includes: "**/*.jar")
            fileset(dir: "build/webapps", includes: "**/*.war")
            zipfileset(dir: "commonlibs", prefix: "APP-INF/lib", includes: "**/*.jar")
        }
        ```

2.  **EAR with a custom manifest and placing `application.xml` from a non-standard location:**

    *   **Ant XML:**
        ```xml
        <ear destfile="${dist.dir}/app.ear" 
             appxml="${conf.dir}/application.xml" 
             manifest="${conf.dir}/MANIFEST.MF">
          <fileset dir="${build.dir}/ejb.jar"/>
          <fileset dir="${build.dir}/webapp.war"/>
        </ear>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        // Assuming distDir, confDir, buildDir are Groovy variables or Ant properties
        def distDir = ant.project.getProperty("dist.dir") ?: "dist"
        def confDir = ant.project.getProperty("conf.dir") ?: "config"
        def buildDir = ant.project.getProperty("build.dir") ?: "build"

        ant.ear(destfile: "${distDir}/app.ear", 
                  appxml: "${confDir}/application.xml", 
                  manifest: "${confDir}/MANIFEST.MF") {
            fileset(file: "${buildDir}/ejb.jar") // Assuming ejb.jar is a single file
            fileset(file: "${buildDir}/webapp.war") // Assuming webapp.war is a single file
        }
        ```

### Important Considerations

*   **`application.xml`**: This is the deployment descriptor for the EAR and is crucial. The `appxml` attribute specifies its source location, and it will always be placed as `META-INF/application.xml` in the archive.
*   **Standard Directory Layout**: While `ear` helps, understanding the standard EAR structure (WARs, EJB JARs at the root, library JARs in `APP-INF/lib/` or a shared library directory) is important.
*   **Duplicate Entries**: Be mindful of the `duplicate` attribute, especially when merging content from multiple sources or updating archives, to avoid unexpected behavior.

### Navigation

*   [Previous Task: Dirname Task](Dirname_Task_Groovy.md)
*   [Next Task: Echo Task](Echo_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)

