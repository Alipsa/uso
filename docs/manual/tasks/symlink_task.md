# symlink

The `symlink` task manages symbolic links on platforms where Java supports NIO-based symlinks (Java 7+). It can create a single link, delete a link, record links to property files, or recreate links from property files. Existing files are not overwritten by default.

## Usage Examples

```groovy
// Create a single symbolic link named foo pointing to subdir/bar.foo
target('makeLink') {
  symlink(
    link: "${dir.top}/foo",
    resource: "${dir.top}/subdir/bar.foo"
  )
}

// Record all links under subdir into a properties file named dir.links
target('recordLinks') {
  symlink(
    action: 'record',
    linkfilename: 'dir.links'
  ) {
    fileset(dir: dir.top, includes: 'subdir/**')
  }
}

// Recreate links recorded in dir.links property files
target('recreateLinks') {
  symlink(action: 'recreate') {
    fileset(dir: dir.top, includes: 'subdir/**/dir.links')
  }
}

// Delete a single symbolic link named foo
target('deleteLink') {
  symlink(
    action: 'delete',
    link: "${dir.top}/foo"
  )
}
```

## Attributes

|      Attribute | Description                                                                                                                                                        | Required    |
|---------------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------|
|       `action` | Type of action: `single`, `record`, `recreate`, or `delete`. Defaults to `single`.                                                                                 | No          |
|         `link` | Name of the link to create or delete. Resolved against the working directory (use absolute paths or `${basedir}/...`). Required for `single` and `delete` actions. | Conditional |
|     `resource` | Resource the link should point to (file or directory path). Required for `single` action.                                                                          | Conditional |
| `linkfilename` | Name of the properties file in each directory to record link names. Required for `record` action.                                                                  | Conditional |
|    `overwrite` | Whether to overwrite existing files (even non-symlinks). Defaults to `false`.                                                                                      | No          |
|  `failonerror` | Whether to stop the build on error (`true`) or log a warning and continue (`false`). Defaults to `true`.                                                           | No          |

## Nested Elements

- `<fileset>` — Select directories or link-record property files (`action="record"` or `action="recreate"`). At least one fileset is required for those actions.

## Notes

- **Ant and Java requirements**:  
  - Introduced in Ant 1.7.0 (optional tasks), moved to core in Ant 1.8.0.  
  - Uses Java 7+ NIO (`Files.createSymbolicLink`) since Ant 1.10.2.

- **File resolution**:  
  - The `link` attribute is resolved against the current working directory, not `${basedir}`, for historical reasons.

## Reference

- [Ant Manual: symlink Task](https://ant.apache.org/manual/Tasks/symlink.html)
