# tempfile

The `tempfile` task sets a property to the name of a unique temporary file. Unlike `java.io.File.createTempFile`, this task does _not_ create the file by default (unless `createfile="true"`), but it guarantees that the returned filename did not exist when the task ran.

## Usage Examples

```groovy
// Generate a temporary filename stored in 'temp.file'
target('makeTemp') {
  tempfile(property: 'temp.file')
  echo "Temporary file: ${project.properties['temp.file']}"
}

// Generate a temporary XML file with a suffix
target('makeXmlTemp') {
  tempfile(
    property: 'temp.xml',
    suffix: '.xml'
  )
}

// Generate and create the file on disk
target('createTempFile') {
  tempfile(
    property: 'temp.created',
    createfile: true
  )
  echo "Created file exists: " + new File(project.properties['temp.created']).exists()
}

// Generate a temp file in the build directory and delete on JVM exit
target('tempInBuild') {
  tempfile(
    property: 'temp.build',
    destdir: 'build',
    deleteonexit: true
  )
}
```

## Attributes

|      Attribute | Description                                                                   | Required |
|---------------:|-------------------------------------------------------------------------------|----------|
|     `property` | Name of the property to set with the generated temporary filename             | Yes      |
|      `destdir` | Directory in which to locate the temp file (defaults to Ant’s `basedir`)      | No       |
|       `prefix` | Prefix for the filename (defaults to a unique random string)                  | No       |
|       `suffix` | Suffix for the filename (defaults to none)                                    | No       |
| `deleteonexit` | If `true`, the JVM will delete the file on normal exit (default: `false`)     | No       |
|   `createfile` | Whether to actually create the file on disk (since Ant 1.8; default: `false`) | No       |

## Notes

- By default, `tempfile` only generates a unique filename; to create the file, set `createfile="true"`.  
- Use `deleteonexit="true"` to ensure cleanup of actual files if created.  
- The task is part of Ant core (no additional libraries required).  
- Temporary filenames are chosen to avoid collisions with existing files at execution time.

## Reference

- [Ant Manual: tempfile Task](https://ant.apache.org/manual/Tasks/tempfile.html)
