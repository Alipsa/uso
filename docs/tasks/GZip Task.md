# GZip Task

## Description

Compresses a file using the GZip algorithm. This task is useful for reducing the size of files for storage or transmission.

## Parameters

| Attribute | Description | Required |
|---|---|---|
| `src` | The source file to be compressed. | Yes |
| `destfile` | The name of the GZip archive to be created. | Yes |
| `overwrite` | Whether to overwrite the destination file if it already exists. Defaults to `true`. | No |
| `level` | The compression level (0-9). 0 is no compression, 9 is best compression. Defaults to 6. | No |

## Examples

### Example 1: Compress a single file

```xml
<gzip src="my_file.txt" destfile="my_file.txt.gz" />
```

This example compresses the file `my_file.txt` into `my_file.txt.gz`.

### Example 2: Compress a file and set the compression level

```xml
<gzip src="large_file.log" destfile="large_file.log.gz" level="9" />
```

This example compresses `large_file.log` into `large_file.log.gz` using the highest compression level (9).

### Example 3: Compress multiple files using a fileset

```xml
<gzip destdir="backup/">
  <fileset dir="logs">
    <include name="*.log"/>
  </fileset>
</gzip>
```

This example compresses all `.log` files in the `logs` directory and places the compressed files in the `backup` directory.

## Notes

*   The `gzip` task only works on single files. To compress multiple files into a single archive, use the `tar` task with the `gzip` compression attribute.
*   The `destfile` attribute should specify the full path to the output file, including the `.gz` extension.
*   If the `destfile` already exists, it will be overwritten unless the `overwrite` attribute is set to `false`.
*   The `level` attribute allows you to control the trade-off between compression speed and file size. Higher levels result in smaller files but take longer to compress.
