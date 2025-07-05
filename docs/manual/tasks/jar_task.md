# Ant Task: Jar

## Original Ant Task Description

Jars a set of files.

The basedir attribute is the reference directory from where to jar.

Note that file permissions will not be stored in the resulting jarfile.

It is possible to refine the set of files that are being jarred. This can be done with the includes, includesfile, excludes, excludesfile and defaultexcludes attributes. With the includes or includesfile attribute you specify the files you want to have included by using patterns. The exclude or excludesfile attribute is used to specify the files you want to have excluded. This is also done with patterns. And finally with the defaultexcludes attribute, you can specify whether you want to use default exclusions or not. See the section on [directory based tasks](https://ant.apache.org/manual/dirtasks.html), on how the inclusion/exclusion of files works, and how to write patterns.

This task forms an implicit FileSet and supports most attributes of `<fileset>` (dir becomes basedir) as well as the nested `<include>`, `<exclude>` and `<patternset>` elements.

You can also use nested file sets for more flexibility, and specify multiple ones to merge together different trees of files into one JAR. The extended `fileset` and `groupfileset` child elements from the `zip` task are also available in the `jar` task. See the Zip task for more details and examples.

The update parameter controls what happens if the JAR file already exists. When set to yes, the JAR file is updated with the files specified. When set to no (the default) the JAR file is overwritten.

If the manifest is omitted, a simple one will be supplied by Apache Ant.

The whenmanifestonly parameter controls what happens when no files, apart from the manifest file, or nested services, match. If skip, the JAR is not created and a warning is issued. If fail, the JAR is not created and the build is halted with an error. If create (default), an empty JAR file (only containing a manifest and services) is created.

Manifests are processed by the Jar task according to the [Jar file specification](https://docs.oracle.com/javase/8/docs/technotes/guides/jar/jar.html#JAR_Manifest).

The Jar task checks whether you specified package information according to the [versioning specification](https://docs.oracle.com/javase/8/docs/technotes/guides/versioning/spec/versioning2.html#wp90779).

**Please note that the ZIP format allows multiple files of the same fully-qualified name to exist within a single archive. This has been documented as causing various problems for unsuspecting users. If you wish to avoid this behavior you must set the duplicate attribute to a value other than its default, add.**

To cryptographically sign your JAR file, use the [SignJar task](signjar.html) on the JAR that you create from this task.

For creating a simple version of a [JEP 238 multi-release jar](https://openjdk.java.net/jeps/238), you don't need any special tools. Just set the required `manifest` entry and place the files where required.

## Parameters

| Attribute        | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | Required                                                          |
|------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------|
| destfile         | the JAR file to create.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | Yes                                                               |
| basedir          | the directory from which to jar the files.                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | No                                                                |
| compress         | Not only store data but also compress them. Unless you set the keepcompression attribute to false, this will apply to the entire archive, not only the files you've added while updating.                                                                                                                                                                                                                                                                                                                        | No; defaults to true                                              |
| keepcompression  | For entries coming from existing archives (like nested `zipfileset`s or while updating the archive), keep the compression as it has been originally instead of using the compress attribute. _Since Ant 1.6_                                                                                                                                                                                                                                                                                                     | No; defaults to false                                             |
| encoding         | The character encoding to use for filenames inside the archive. **It is not recommended to change this value as the created archive will most likely be unreadable for Java otherwise.**                                                                                                                                                                                                                                                                                                                         | No; defaults to UTF8                                              |
| filesonly        | Store only file entries                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | No; defaults to false                                             |
| includes         | comma- or space-separated list of patterns of files that must be included                                                                                                                                                                                                                                                                                                                                                                                                                                        | No; defaults to all (\*\*)                                        |
| includesfile     | name of a file. Each line of this file is taken to be an include pattern                                                                                                                                                                                                                                                                                                                                                                                                                                         | No                                                                |
| excludes         | comma- or space-separated list of patterns of files that must be excluded                                                                                                                                                                                                                                                                                                                                                                                                                                        | No; defaults to default excludes or none if defaultexcludes is no |
| excludesfile     | Name of a file. Each line of this file is taken to be an exclude pattern                                                                                                                                                                                                                                                                                                                                                                                                                                         | No                                                                |
| defaultexcludes  | indicates whether default excludes should be used or not (yes                                                                                                                                                                                                                                                                                                                                                                                                                                                    | no)                                                               | No; defaults to yes |
| manifest         | the manifest file to use. This can be either the location of a manifest, or the name of a jar added through a fileset. If its the name of an added jar, the task expects the manifest to be in the jar at META-INF/MANIFEST.MF                                                                                                                                                                                                                                                                                   | No                                                                |
| filesetmanifest  | behavior when a manifest is found in a `zipfileset` or `zipgroupfileset` file. Valid values are skip, merge, and mergewithoutmain. merge will merge all of the manifests together, and merge this into any other specified manifests. mergewithoutmain merges everything but the Main section of the manifests.                                                                                                                                                                                                  | No; defaults to skip                                              |
| update           | indicates whether to update or overwrite the destination file if it already exists                                                                                                                                                                                                                                                                                                                                                                                                                               | No; defaults to false                                             |
| whenmanifestonly | behavior when no files match. Valid values are fail, skip, and create.                                                                                                                                                                                                                                                                                                                                                                                                                                           | No; defaults to create                                            |
| duplicate        | behavior when a duplicate file is found. Valid values are add, preserve, and fail.                                                                                                                                                                                                                                                                                                                                                                                                                               | No; defaults to add                                               |
| index            | whether to create an [index list](https://docs.oracle.com/javase/8/docs/technotes/guides/jar/jar.html#JAR_Index) to speed up classloading. Unless you specify additional jars with nested `indexjars` elements, only the contents of this jar will be included in the index.                                                                                                                                                                                                                                     | No; defaults to false                                             |
| indexMetaInf     | whether to include META-INF and its children in the index. Doesn't have any effect if index is false. Sun's jar implementation used to skip the META-INF directory and Ant followed that example. The behavior has been changed with Java 5. In order to avoid problems with Ant generated jars on Java 1.4 or earlier Ant will not include META-INF unless explicitly asked to. _Since Ant 1.8.0_                                                                                                               | No; defaults to false                                             |
| manifestencoding | The encoding used to read the JAR manifest, when a manifest file is specified. The task will always use UTF-8 when writing the manifest.                                                                                                                                                                                                                                                                                                                                                                         | No; defaults to default JVM character encoding                    |
| roundup          | Whether to round up dates of files to the next even second. Zip archives store file modification times with a granularity of 2 seconds, so the times will either be rounded up or down. If you round down, the archive will always seem out-of-date when you rerun the task, so the default is to round up. Rounding up may lead to a different type of problems like JSPs inside a web archive that seem to be slightly more recent than precompiled pages, rendering precompilation useless. _Since Ant 1.6.2_ | No; defaults to true                                              |

## Nested Elements

### `metainf`

Specifies files to be placed in the `META-INF` directory of the JAR file. This element takes no attributes. It is a `FileSet` and supports all of its attributes and nested elements.

### `manifest`

Allows inline configuration of the manifest file. See the [Manifest Task documentation](https://ant.apache.org/manual/Tasks/manifest.html) for details.

### `indexjars`

Specifies a list of JAR files to be included in the index. This element is a [Path-like Structure](https://ant.apache.org/manual/Types/pathelement.html).

### `service`

Adds a service provider descriptor to the JAR file. This element has one attribute, `type`, which specifies the service type.

## Examples

### Example 1: Basic JAR creation

```xml
<jar destfile="myarchive.jar" basedir="build/classes"/>
```

This creates a JAR file named `myarchive.jar` containing all files from the `build/classes` directory.

### Example 2: Creating a JAR with a custom manifest

```xml
<jar destfile="myapp.jar">
    <fileset dir="build/classes"/>
    <manifest>
        <attribute name="Main-Class" value="com.example.MyMainClass"/>
        <attribute name="Built-By" value="${user.name}"/>
    </manifest>
</jar>
```

This creates a JAR file named `myapp.jar` with a manifest specifying the main class and the builder's name.

### Example 3: Adding files to an existing JAR

```xml
<jar destfile="myarchive.jar" update="true">
    <fileset dir="additional-files"/>
</jar>
```

This adds files from the `additional-files` directory to the existing `myarchive.jar` file. If the JAR file does not exist, it will be created.

### Example 4: Creating an executable JAR

```xml
<jar destfile="${dist}/lib/myexecutable.jar" basedir="${build.classes}">
    <manifest>
        <attribute name="Main-Class" value="com.example.MyMainClass"/>
        <attribute name="Class-Path" value="."/>
    </manifest>
    <fileset dir="${build.classes}"/>
</jar>
```

This creates an executable JAR file named `myexecutable.jar` in the `${dist}/lib` directory. The `Main-Class` attribute specifies the entry point of the application. The `Class-Path` attribute with a value of `.` indicates that all dependencies are within the JAR itself.

### Example 5: Creating a JAR with a specific encoding

```xml
<jar destfile="myarchive.jar" basedir="classes" encoding="UTF-8"/>
```

This creates a JAR file named `myarchive.jar` and specifies that the filenames within the archive should be encoded using UTF-8. While UTF-8 is generally recommended, this example shows how to specify a different encoding if needed for specific compatibility reasons.

## Groovy AntBuilder DSL Examples

### Example 1: Basic JAR creation (Groovy)

```groovy
ant.jar(destfile: "${dist}/lib/app.jar", basedir: "${build}/classes")
```

### Example 2: JAR with custom manifest (Groovy)

```groovy
ant.jar(destfile: "${dist}/lib/app.jar", basedir: "${build}/classes") {
    manifest {
        attribute(name: "Main-Class", value: "com.example.Main")
        attribute(name: "Built-By", value: System.getProperty("user.name"))
    }
}
```

### Example 3: Updating JAR with fileset (Groovy)

```groovy
ant.jar(destfile: "${dist}/lib/app.jar", update: true) {
    fileset(dir: "${build}/additional-classes")
    manifest(file: "${src}/META-INF/MANIFEST.MF")
}
```

### Example 4: Multi-Release JAR (Groovy)

```groovy
ant.jar(destfile: "mrjar.jar") {
    manifest {
        attribute(name: "Multi-Release", value: "true")
    }
    fileset(dir: "${java.classes}")
    zipfileset(prefix: "META-INF/versions/9/", dir: "${java9.classes}")
    zipfileset(prefix: "META-INF/versions/11/", dir: "${java11.classes}")
}
```

