# replaceregexp

The `replaceregexp` task performs regular expression-based search-and-replace operations on files or resource collections using Java's `java.util.regex` engine. It supports global or scoped replacements, multiline processing, case sensitivity control, and output customization.

## Usage Examples

### Replace a regex in a single file (multiline)
```groovy
target('regexReplace') {
  replaceregexp(
    file: 'src/main/java/App.java',
    match: 'TODO\(.*?\)',
    replace: 'DONE',
    byline: false,
    flags: 'g'
  )
}
```

### Batch replace across multiple files
```groovy
target('batchRegexReplace') {
  replaceregexp(match: 'foo(\d+)', replace: 'bar\1', flags: 'g;mc') {
    fileset(dir: 'src', includes: '**/*.txt')
  }
}
```

## Attributes

| Attribute               | Description                                                                                                                  | Required                                         |
|------------------------:|------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------|
| `file`                  | Single file to process                                                                                                       | One of `file` or nested resource                 |
| `match`                 | Regular expression pattern to search for                                                                                     | Yes                                              |
| `replace`               | Replacement text (supports backreferences)                                                                                   | Yes                                              |
| `flags`                 | Semicolon-separated regex flags: `g` (global), `m` (multiline), `c` (case-sensitive default), `i` (case-insensitive)         | No (default: `g` for global replacements)        |
| `byline`                | Process text one line at a time (`true`) or whole file as one string (`false`)                                              | No (default: `true`)                             |
| `casesensitive`         | Whether pattern matching is case-sensitive (`true`) or not (`false`)                                                        | No (default: `true`)                             |
| `encoding`              | Character encoding for reading and writing files                                                                             | No                                               |
| `failonnoreplacements`  | Whether to fail the build if no replacements are made (`true`) or continue quietly (`false`)                                 | No (default: `false`)                            |

## Nested Elements

- `fileset` — Include files via directory patterns (include/exclude).
- `path` / `zipfileset` — Additional resource collections for batch processing.
- `filterchain` — Apply Ant-style filters (e.g., `<tokenfilter>`) before or after regex replacement.
- `mapper` — Rename files during output (used with `toDir` or `toFile` attributes).

## Notes

- Use `flags` to combine options: e.g., `g;mi` for global, multiline, case-insensitive replacements.
- When `byline="true"`, the replacement is applied to each line independently.
- Backreferences in replacement use Java syntax (`\1`, `\2`); escape backslashes in Groovy strings.
- If you need to write output to a different location, use `toDir` or `toFile` attributes.
- Suitable for large-scale code refactoring or templated text generation.

## Reference

- [Ant Manual: replaceregexp Task](https://ant.apache.org/manual/Tasks/replaceregexp.html)
