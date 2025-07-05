# propertyhelper

The `propertyhelper` task allows you to install or extend the Ant `PropertyHelper` at runtime. This is an advanced task (since Ant 1.8.0) for customizing how properties are evaluated in your build.

## Description

- **Install a custom PropertyHelper**: Replace the active `PropertyHelper` with a new implementation.
- **Add PropertyHelper.Delegate**: Insert one or more delegates (e.g., property evaluators) into the current `PropertyHelper`, which are consulted in LIFO order.

## Usage Examples

### Replace the PropertyHelper implementation

```groovy
project.with {
  componentdef(classname: 'org.example.MyPropertyHelper', name: 'mypropertyhelper')
  propertyhelper {
    mypropertyhelper()
  }
}
```

### Add a custom Delegate

```groovy
project.with {
  componentdef(classname: 'org.example.MyPropertyEvaluator', name: 'mypropertyevaluator')
  propertyhelper {
    mypropertyevaluator()
  }
}
```

### Add a Delegate by reference

```groovy
project.with {
  typedef(classname: 'org.example.MyPropertyEvaluator', name: 'mypropertyevaluator')
  mypropertyevaluator(id: 'evaluator')
  propertyhelper {
    delegate(refid: 'evaluator')
  }
}
```

## Nested Elements

| Element                  | Description                                                                    | Required |
|--------------------------|--------------------------------------------------------------------------------|----------|
| `<delegate refid="...">` | Installs an existing `PropertyHelper.Delegate` by reference                    | No       |
| `<YourHelperTask/>`      | Any task or type that implements `PropertyHelper` or `PropertyHelper.Delegate` | No       |

> **Note:** Delegates must implement a `PropertyHelper.Delegate` subinterface (e.g., `PropertyEvaluator`) to have an effect.

## Notes

- Delegates are consulted before built-in ones (LIFO order).
- Use this task sparingly, only when you need custom property evaluation logic.
- Requires familiarity with Ant’s PropertyHelper API.

## Reference

- [Ant Manual: propertyhelper Task](https://ant.apache.org/manual/Tasks/propertyhelper.html)
