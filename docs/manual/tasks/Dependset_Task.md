## DependSet Task (Groovy DSL)

### Description

The `dependset` task in Ant is used to manage arbitrary dependencies between sets of resources (sources) and a set of target files. If any source resource is newer than any target file, or if any source file specified in a `srcfilelist` does not exist, then all target files are removed. This is useful for dependencies that Ant cannot determine algorithmically, such as an XML file depending on its DTD or an XSLT stylesheet.

In Groovy AntBuilder, `dependset` is invoked as `ant.dependset(...)`. It uses nested resource collections to define source and target sets.

### Parameters

*   `verbose`: (Boolean) If `true`, lists all deleted target files and the reason for their deletion. Defaults to `false`.
    *   Groovy: `verbose: true`

### Nested Elements

`dependset` requires at least one set of sources and one set of targets.

*   **`<sources>`**: (Container for source resource collections, _since Ant 1.7_)
    This element acts as a [Union](https://ant.apache.org/manual/Types/union.html) and can contain various resource collections (e.g., `fileset`, `filelist`, `path`, `urlresource`).
    ```groovy
    ant.dependset {
        sources {
            filelist(dir: "dtds", files: "mydoc.dtd,common.dtd")
            url(url: "https://example.com/schema/external.xsd")
        }
        targets {
            fileset(dir: "output/generated-xml", includes: "**/*.xml")
        }
    }
    ```

*   **`<srcfileset>`**: (Defines a FileSet of source files)
    All files in this fileset are compared against all target files.
    ```groovy
    ant.dependset {
        srcfileset(dir: "schemas", includes: "**/*.xsd")
        // ... targets ...
    }
    ```

*   **`<srcfilelist>`**: (Defines a FileList of source files)
    All files in this filelist are compared. If any file in a `srcfilelist` does *not* exist, all targets are considered out of date and will be deleted.
    ```groovy
    ant.dependset {
        srcfilelist(dir: "config", files: "important.properties,another.xml")
        // ... targets ...
    }
    ```

*   **`<targets>`**: (Container for target file resources, _since Ant 1.7_)
    This element is a [Path](https://ant.apache.org/manual/Types/path.html)-like structure and can contain filesystem-based resources that represent the target files.
    ```groovy
    ant.dependset {
        // ... sources ...
        targets {
            fileset(dir: "build/reports", includes: "**/*.html")
            filelist(dir: "docs/generated", files: "api.pdf")
        }
    }
    ```

*   **`<targetfileset>`**: (Defines a FileSet of target files)
    All files in this fileset are targets. If out of date, all are deleted.

*   **`<targetfilelist>`**: (Defines a FileList of target files)
    All files in this filelist are targets. If out of date, all are deleted. If any file in a `targetfilelist` does not exist, it is considered out of date.

### Examples

Remove derived HTML files in `output_html` if they are out-of-date with respect to DTDs, an XSL stylesheet, or the build file itself.

*   **Ant XML:**
    ```xml
    <dependset verbose="true">
      <srcfilelist dir="dtds" files="paper.dtd,common.dtd"/>
      <srcfilelist dir="styles" files="common.xsl"/>
      <srcfilelist dir="." files="build.xml"/>
      <targetfileset dir="output_html" includes="**/*.html"/>
    </dependset>
    ```
*   **Groovy AntBuilder:**
    ```groovy
    ant.dependset(verbose: true) {
        srcfilelist(dir: "dtds", files: "paper.dtd,common.dtd")
        srcfilelist(dir: "styles", files: "common.xsl")
        srcfilelist(dir: ".", files: "build.xml") // Current directory for build.xml
        targetfileset(dir: "output_html", includes: "**/*.html")
    }
    ```
    If any source file in the `srcfilelist`s does not exist, all HTML files in `output_html` will be deleted.

To ignore missing source files (i.e., only trigger deletion if an existing source is newer), use `srcfileset` instead of `srcfilelist` for those sources:

```groovy
ant.dependset(verbose: true) {
    srcfileset(dir: "dtds", includes: "paper.dtd,common.dtd") // Ignores if dtds are missing
    srcfileset(dir: "styles", includes: "common.xsl")
    srcfilelist(dir: ".", files: "build.xml") // build.xml must exist
    targetfileset(dir: "output_html", includes: "**/*.html")
}
```

### Important Considerations

*   **`srcfilelist` vs. `srcfileset`**: A key difference is how missing files are handled. `srcfilelist` treats a missing source file as a trigger to delete targets. `srcfileset` will simply not include missing files in its set of sources, so a missing source file in a `srcfileset` won't by itself cause targets to be deleted.
*   **Target Deletion**: If the dependency condition is met (any source newer than any target, or a required source from `srcfilelist` is missing), *all* specified target files are deleted.
*   **Use Cases**: Ideal for situations where build tools like `javac` or `style` (XSLT) don't capture all dependencies (e.g., an XSLT importing other XSLTs, or XML files validated against schemas/DTDs that might change).

### Navigation

*   [Previous Task: Depend Task](Depend_Task_Groovy.md)
*   [Next Task: Diagnostics Task](Diagnostics_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)

