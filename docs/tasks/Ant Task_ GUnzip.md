# Ant Task: GUnzip

## Original Ant Task Description

The `gunzip` task in Apache Ant is used to expand (decompress) a file that has been compressed using the GZip algorithm. It is typically used for files with a `.gz` extension. The task can decompress a source file to a specified destination file or directory. If the destination is a directory, the output file will have the same name as the source file but without the `.gz` extension. If no destination is specified, the output file is created in the same directory as the source file.

The task only expands the file if the source is newer than the destination or if the destination file does not exist, preventing unnecessary operations.

## Groovy AntBuilder DSL Equivalent

In Groovy, the `AntBuilder` provides a straightforward way to invoke the `gunzip` task. The attributes of the Ant XML task are passed as named arguments to the `gunzip()` method. Nested resource collections can also be used to specify the source.

```groovy
ant = new AntBuilder()

// Example: Expand a .gz file to the same directory
ant.gunzip(src: "myarchive.tar.gz")

// Example: Expand a .gz file to a specific destination file
ant.gunzip(src: "myarchive.tar.gz", dest: "myarchive.tar")

// Example: Expand a .gz file to a specific directory
ant.gunzip(src: "myarchive.tar.gz", dest: "output_directory/")

// Example: Using a nested resource (e.g., a URL)
ant.gunzip(dest: "output_directory/") {
    url(url: "https://example.com/somefile.gz")
}
```

## Parameter Mapping

| Ant Attribute | Groovy Parameter | Description                                                                 | Required                               |
|---------------|------------------|-----------------------------------------------------------------------------|----------------------------------------|
| `src`         | `src`            | The GZipped file to expand.                                                 | Yes (or a nested resource collection)  |
| `dest`        | `dest`           | The destination file or directory. If a directory, the output filename is derived from `src`. If omitted, defaults to the parent directory of `src`. | No                                     |

### Nested Elements

*   **Resource Collections**: Any single-element resource collection (e.g., `<file>`, `<url>`, `<gzipresource>`) can be nested to specify the source file. The `gunzip` task will attempt to decompress the content of this resource.

## Code Examples

### Example 1: Expand `test.tar.gz` to `test.tar` (in the same directory)

**Ant XML:**
```xml
<gunzip src="test.tar.gz"/>
```

**Groovy AntBuilder DSL:**
```groovy
// For this example to run, create a dummy gzipped file first.
// 1. Create a dummy file: new File("dummy.txt").text = "This is a test file for GZip."
// 2. Gzip it: new AntBuilder().gzip(src: "dummy.txt", destfile: "test.tar.gz")

new AntBuilder().gunzip(src: "test.tar.gz")
println "Groovy: Expanded test.tar.gz to test.tar in the current directory."
// To verify: new File("test.tar").exists() should be true
```

### Example 2: Expand `test.tar.gz` to `test2.tar` (specific destination file)

**Ant XML:**
```xml
<gunzip src="test.tar.gz" dest="test2.tar"/>
```

**Groovy AntBuilder DSL:**
```groovy
// Prerequisite: Assume test.tar.gz from Example 1 exists.
new AntBuilder().gunzip(src: "test.tar.gz", dest: "test2.tar")
println "Groovy: Expanded test.tar.gz to test2.tar."
// To verify: new File("test2.tar").exists() should be true
```

### Example 3: Expand `test.tar.gz` to `subdir/test.tar` (destination is a directory)

**Ant XML:**
```xml
<gunzip src="test.tar.gz" dest="subdir"/>
```

**Groovy AntBuilder DSL:**
```groovy
// Prerequisite: Assume test.tar.gz from Example 1 exists.
new File("subdir").mkdirs() // Ensure the destination directory exists

new AntBuilder().gunzip(src: "test.tar.gz", dest: "subdir")
println "Groovy: Expanded test.tar.gz to subdir/test.tar."
// To verify: new File("subdir/test.tar").exists() should be true
```

### Example 4: Download and expand a GZipped file from a URL

**Ant XML:**
```xml
<gunzip dest=".">
  <url url="https://example.org/archive.tar.gz"/>
</gunzip>
```

**Groovy AntBuilder DSL:**
```groovy
// This example requires internet access and a valid URL to a .gz file.
// For a local test, you might set up a simple HTTP server or use a file resource.
// For this example, we will simulate with a local file resource for testability.

// 1. Create a dummy file: new File("source_for_url_sim.txt").text = "Content for URL simulation."
// 2. Gzip it: new AntBuilder().gzip(src: "source_for_url_sim.txt", destfile: "archive_to_expand.gz")

new AntBuilder().sequential {
    gunzip(dest: ".") {
        // In a real scenario, this would be: url(url: "https://example.org/archive.tar.gz")
        // For local testing, we use a file resource:
        file(file: "archive_to_expand.gz")
    }
    println "Groovy: Expanded archive_to_expand.gz (simulating URL resource) to archive_to_expand in current directory."
    // To verify: new File("archive_to_expand").exists() should be true
}
```

## Notes

*   The `gunzip` task is specific to GZip compressed files. For other archive formats like ZIP or BZip2, use `unzip` or `bunzip2` respectively.
*   The task is idempotent: it will not re-expand the file if the destination is up-to-date with the source.
*   The `gunzip` task can be seen as a specialized form of the `copy` task using a `gzipresource` and a mapper. The `copy` task offers more features like filtering if needed.
*   Ensure that the source file specified by `src` or the nested resource actually exists and is a valid GZip compressed file.
*   If `dest` is a directory, it must exist, or the task might fail. It is good practice to create the destination directory beforehand if it might not exist (e.g., using `mkdir`).

