# echoproperties

The `echoproperties` task in Ant displays all project properties (user-defined and automatic) to the console or to a file. This is useful for debugging property values and assessing what variables are available in your build script.

## Usage

```groovy
target('showProperties') {
  echoproperties()
}
```

This will output all current properties to the console (standard output).

To write the properties to a file:

```groovy
target('writeProperties') {
  echoproperties(file: 'build/project.properties')
}
```

## Attributes

| Attribute   | Description                                                     | Required |
|-------------|-----------------------------------------------------------------|----------|
| `file`      | Path to a file where output will be written                     | No       |
| `dest`      | Alias for `file`                                                | No       |
| `prefix`    | Adds a prefix to each property name in the output               | No       |
| `separator` | String between property name and value (default is `=`)         | No       |

## Example Output

```
project.name = example
src.dir = src
version = 1.0.0
```

Each property is printed as `name<separator>value`.

## Notes

- Prints all Ant properties, including built-in, system, and user-defined ones.
- Useful for debugging large or modular builds to confirm values.
- Defaults to pretty-printed alphabetical output.

## Reference

- [Ant Manual: echoproperties Task](https://ant.apache.org/manual/Tasks/echoproperties.html)
