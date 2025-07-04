# patch

The `patch` task applies unified diff patch files to files or resource collections. It provides options to reverse patches, strip path components, and control behavior on errors.

## Usage Examples

### Apply a patch to a single file

```groovy
target('applyPatch') {
  patch(
    patchfile: 'fix.patch',
    input: 'src/Main.java',
    output: 'src/Main.java'
  )
}
```

### Apply patches to multiple files

```groovy
target('applyPatches') {
  patch(patchfile: 'changes.patch') {
    fileset(dir: 'src', includes: '**/*.java')
  }
}
```

## Attributes

|     Attribute | Description                                                                     | Required                          |
|--------------:|---------------------------------------------------------------------------------|-----------------------------------|
|   `patchfile` | Path to the patch file (unified diff format)                                    | Yes                               |
|    `reversed` | Apply patch in reverse (undo) (`true`/`false`, default: `false`)                | No                                |
|       `strip` | Number of leading path components to strip from file paths (default: 0)         | No                                |
|       `input` | Single file to patch (use with `output` when not using nested resources)        | One of `input` or nested resource |
|      `output` | Destination for patched output; if omitted, patches are applied in place        | No                                |
| `failOnError` | Fail the build if the patch cannot be applied (`true`/`false`, default: `true`) | No                                |

## Nested Elements

- `<fileset>` — Specifies files to patch via include/exclude patterns.
- `<mapper>` — Maps source files to target paths before patching.
- `<formatter>` — Configures output format or logging for the patch operation.

## Notes

- The patch file must be in the unified diff format (e.g., generated by `diff -u`).
- Use `reversed="true"` to revert patches.
- The `strip` attribute functions like the `-p` option of the GNU `patch` command.
- Nested `<fileset>` is useful for patching multiple files across directories.

## Reference

- [Ant Manual: patch Task](https://ant.apache.org/manual/Tasks/patch.html)
