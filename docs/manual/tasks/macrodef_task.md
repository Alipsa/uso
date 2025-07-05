# macrodef

The `macrodef` task defines a reusable build script macro (similar to a function) that you can invoke like any other Ant task. You can declare attributes and nested elements to parameterize the macro’s behavior.

## Usage

```groovy
// Define a macro
target('defineMacros') {
  macrodef(name: 'makeParentDir', attributes: 'file') {
    // Declare the macro attribute
    attribute(name: 'file')
    // Macro body: create parent directory of the given file
    sequential {
      dirname(property: 'parent', file: '@{file}')
      mkdir(dir: '${parent}')
    }
  }
}

// Invoke the macro
target('createDirs', depends: 'defineMacros') {
  makeParentDir(file: 'build/output/data.txt')
  makeParentDir(file: 'logs/app.log')
}
```

## Attributes

| Attribute    | Description                                                | Required |
|-------------:|------------------------------------------------------------|----------|
|       `name` | Name of the macro to define                                | Yes      |
| `attributes` | Comma‑separated list of attribute names for the macro      | No       |

## Nested Elements

- `attribute` — Declares a single attribute available inside the macro (accessible via `@{name}`).
- `element` — Declares a nested element (e.g., a `<fileset>` or `<path>`) for complex parameters.
- `sequential` — Groups tasks to form the macro body.
- Any Ant tasks or Groovy code can be used inside `sequential`.

## Notes

- Macros are typically defined in an initialization target and invoked later.
- Attribute values are referenced inside the macro body with the `@{}` syntax.
- Nested elements allow passing sets of resources or configurations into the macro.

## Reference

- [Ant Manual: macrodef Task](https://ant.apache.org/manual/Tasks/macrodef.html)
