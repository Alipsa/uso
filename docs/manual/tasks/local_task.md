# local

The `local` task declares one or more properties local to the current scope in Ant. Local properties shadow properties of the same name at higher scopes (including the global scope) and are cleared when the scope ends (e.g., at the end of a target or a `<sequential>`/`<parallel>` block).

## Usage Examples

```groovy
// Temporarily shadow a global property
project.with {
  target('step1') {
    echo(message: "Before local: foo = ${foo}")
    local(name: 'foo')
    property(name: 'foo', value: 'bar')
    echo(message: "After local: foo = ${foo}")
  }

  target('step2', depends: 'step1') {
    echo(message: "In step2: foo = ${foo}")  // Original global foo restored
  }
}
```

```groovy
// Using local inside macrodef to avoid global pollution
project.with {
  // Define a macro for creating a parent directory
  macrodef(name: 'makeParentDir') {
    sequential {
      local(name: 'parent')
      dirname(property: 'parent', file: '@{file}')
      mkdir(dir: '${parent}')
    }
  }

  target('createDirs') {
    makeParentDir(file: 'some-dir/some-file')
    makeParentDir(file: 'other-dir/other-file')
  }
}
```

## Attributes

| Attribute | Description                              | Required |
|----------:|------------------------------------------|----------|
|    `name` | Name of the property to declare as local | Yes      |

## Nested Elements

- `<name>` — Specify one or more property names to declare as local. Useful when declaring multiple in one task.

## Notes

- Local properties exist only within the current scope, such as the body of a target, `<parallel>`, `<sequential>`, or a `<macrodef>` invocation.
- Using `<local>` at the global level makes the property local to the “anonymous target” of top-level operations; it will not persist across targets.
- Especially handy for avoiding property name collisions in `<macrodef>` and multi-threaded builds.

## Reference

- [Ant Manual: local Task](https://ant.apache.org/manual/Tasks/local.html)
