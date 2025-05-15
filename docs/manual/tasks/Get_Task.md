# Ant Task: Get

## Original Ant Task Description

The `get` Ant task is designed to retrieve files from URLs. It supports various URL schemes like `http:`, `https:`, `ftp:`, and `jar:`. The task offers several features, including verbose progress reporting, conditional downloads based on timestamps (for HTTP), and basic authentication. It can also utilize operating system proxy settings or be configured with a specific proxy.

Key functionalities include:
*   **Source and Destination**: Specifies a source URL (`src`) and a destination file or directory (`dest`).
*   **Timestamp Checking**: The `usetimestamp` attribute allows downloading a file only if the remote version is newer than the local copy (HTTP only).
*   **Authentication**: Supports `username` and `password` attributes for basic HTTP authentication.
*   **Error Handling**: Provides options like `ignoreerrors` to log errors without failing the build.
*   **Resource Collections**: Can download multiple files specified within a nested resource collection.
*   **Mappers**: Allows renaming or restructuring downloaded files using nested mapper elements.
*   **Custom Headers**: _Since Ant 1.9.3_, supports adding custom HTTP headers via nested `<header>` elements.
*   **Gzip Encoding**: _Since Ant 1.9.5_, supports requesting and handling gzip encoded content from the server for reduced transfer size.

## Groovy AntBuilder DSL Equivalent

The Groovy `AntBuilder` provides a convenient way to use the `get` task. Attributes from the Ant XML task are passed as named parameters to the `get` method. Nested elements like resource collections or mappers can be defined within closures.

```groovy
ant = new AntBuilder()

// Example: Get a single file
ant.get(src: "https://ant.apache.org/", dest: "ant_index.html")

// Example: Get a file with timestamp checking and verbose output
ant.get(src: "https://www.apache.org/dist/ant/KEYS", 
        dest: "ANT_KEYS", 
        verbose: true, 
        usetimestamp: true)

// Example: Get multiple files using a nested resource collection (e.g., list of URLs)
ant.get(dest: "downloaded_files/") {
    url(url: "https://ant.apache.org/index.html")
    url(url: "https://ant.apache.org/faq.html")
}

// Example: Get a file with custom HTTP headers
ant.get(src: "https://ant.apache.org/index.html", dest: "ant_index_with_headers.html") {
    header(name: "X-Custom-Header", value: "MyValue")
    header(name: "User-Agent", value: "MyAntScript/1.0")
}
```

## Parameter Mapping

| Ant Attribute          | Groovy Parameter       | Description                                                                                                | Required                               |
|------------------------|------------------------|------------------------------------------------------------------------------------------------------------|----------------------------------------|
| `src`                  | `src`                  | The URL from which to retrieve a file.                                                                     | Yes (or a nested resource collection)  |
| `dest`                 | `dest`                 | The file or directory where to store the retrieved file(s).                                                | Yes                                    |
| `verbose`              | `verbose`              | Show verbose progress information (`true`\|`false`).                                                               | No; defaults to `false`           |
| `quiet`                | `quiet`                | Log errors only (`true`\|`false`).                                                                                 | No; defaults to `false`           |
| `ignoreerrors`         | `ignoreerrors`         | Log errors but don't treat as fatal (`true`\|`false`).                                                              | No; defaults to `false`           |
| `usetimestamp`         | `usetimestamp`         | Conditionally download a file based on the timestamp of the local copy (HTTP only).                        | No; defaults to `false`           |
| `username`             | `username`             | Username for basic HTTP authentication.                                                                    | No                                     |
| `password`             | `password`             | Password for basic HTTP authentication.                                                                    | No                                     |
| `authenticateOnRedirect` | `authenticateOnRedirect` | Whether the credentials should also be sent to the new location when a redirect is followed. _Since Ant 1.10.13_ | No; defaults to `false`           |
| `maxtime`              | `maxtime`              | Maximum time in seconds a single download may take. 0 means unlimited. _Since Ant 1.8.0_                   | No; defaults to `0`               |
| `retries`              | `retries`              | The number of attempts to make for opening the URI. 1 means no retry. _Since Ant 1.8.0_                    | No; defaults to `3`               |
| `skipexisting`         | `skipexisting`         | Skip files that already exist on the local filesystem. _Since Ant 1.8.0_                                   | No; defaults to `false`           |
| `httpusecaches`        | `httpusecaches`        | HTTP only—if `true`, allow caching at the `HttpUrlConnection` level.                                       | No; defaults to `true`            |
| `useragent`            | `useragent`            | `User-Agent` HTTP header to send. _Since Ant 1.9.3_                                                        | No; defaults to Apache Ant version    |
| `tryGzipEncoding`      | `tryGzipEncoding`      | When `true`, Ant will tell the server it is willing to accept gzip encoding. _Since Ant 1.9.5_              | No; defaults to `false`           |

### Nested Elements

*   **Resource Collections**: Any [resource collection]() can be nested to specify multiple URLs to download. If `dest` is a directory, files are downloaded into it, using the last part of their URL as the filename.
*   **`<mapper>`**: A [mapper]() can be nested to transform the names of the downloaded files.
*   **`<header>`**: _Since Ant 1.9.3_, multiple `<header>` elements can be nested to specify custom HTTP headers. Each `<header>` element requires `name` and `value` attributes.

## Code Examples

### Example 1: Basic File Download

**Ant XML:**
```xml
<get src="https://ant.apache.org/" dest="ant_homepage.html"/>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().get(src: "https://ant.apache.org/", dest: "ant_homepage_groovy.html")
println "Groovy Get: Downloaded ant.apache.org homepage to ant_homepage_groovy.html"
```

### Example 2: Conditional Download with Verbose Output

**Ant XML:**
```xml
<get src="https://www.apache.org/dist/ant/KEYS"
     dest="ANT_KEYS_xml"
     verbose="true"
     usetimestamp="true"/>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().get(src: "https://www.apache.org/dist/ant/KEYS", 
                     dest: "ANT_KEYS_groovy", 
                     verbose: true, 
                     usetimestamp: true)
println "Groovy Get: Attempted to download KEYS with verbose and usetimestamp to ANT_KEYS_groovy"
```

### Example 3: Downloading Multiple Files to a Directory

**Ant XML:**
```xml
<mkdir dir="ant_docs_xml"/>
<get dest="ant_docs_xml">
  <url url="https://ant.apache.org/index.html"/>
  <url url="https://ant.apache.org/faq.html"/>
  <url url="https://ant.apache.org/manual/index.html"/>
</get>
```

**Groovy AntBuilder DSL:**
```groovy
new File("ant_docs_groovy").mkdirs()
new AntBuilder().get(dest: "ant_docs_groovy") {
    url(url: "https://ant.apache.org/index.html")
    url(url: "https://ant.apache.org/faq.html")
    url(url: "https://ant.apache.org/manual/index.html")
}
println "Groovy Get: Downloaded multiple Ant documentation pages to ant_docs_groovy/"
```

### Example 4: Downloading with Custom HTTP Headers

**Ant XML:**
```xml
<get src="https://ant.apache.org/" dest="ant_homepage_headers.html">
  <header name="X-Custom-Header" value="Ant-Build-Process"/>
  <header name="User-Agent" value="MyAntScript/1.0"/>
</get>
```

**Groovy AntBuilder DSL:**
```groovy
new AntBuilder().get(src: "https://ant.apache.org/", dest: "ant_homepage_headers_groovy.html") {
    header(name: "X-Custom-Header-Groovy", value: "Ant-Build-Process-Groovy")
    header(name: "User-Agent", value: "MyGroovyAntScript/1.0")
}
println "Groovy Get: Downloaded ant.apache.org homepage with custom headers to ant_homepage_headers_groovy.html"
```

### Example 5: Using a Mapper to Rename Downloaded Files

**Ant XML:**
```xml
<mkdir dir="mapped_downloads_xml"/>
<get dest="mapped_downloads_xml">
  <url url="https://ant.apache.org/index.html"/>
  <url url="https://ant.apache.org/faq.html"/>
  <mapper type="glob" from="*.html" to="*.txt"/>
</get>
```

**Groovy AntBuilder DSL:**
```groovy
new File("mapped_downloads_groovy").mkdirs()
new AntBuilder().get(dest: "mapped_downloads_groovy") {
    url(url: "https://ant.apache.org/index.html")
    url(url: "https://ant.apache.org/faq.html")
    mapper(type: "glob", from: "*.html", to: "*.txt")
}
println "Groovy Get: Downloaded and mapped .html files to .txt in mapped_downloads_groovy/"
```

## Notes

*   The `get` task is very versatile for fetching resources from various URLs.
*   When downloading multiple files, `dest` should be a directory. The task will create it if it doesn't exist.
*   For secure transmission of credentials, always use HTTPS.
*   The `usetimestamp` feature is particularly useful for avoiding redundant downloads in continuous integration or build scripts.
*   The `tryGzipEncoding` attribute can significantly reduce download times for compressible content by allowing the server to send gzipped data.
*   Error handling attributes like `ignoreerrors` and `retries` provide control over how download failures are managed within a build process.

