# Ant Task: Image (Deprecated)

## Original Ant Task Description

The `Image` Ant task was designed to apply a chain of image operations on a set of image files. This task required the Java Advanced Image (JAI) API from Sun.

**Important Note:** This task has been **deprecated** because the Java Advanced Image API depends on internal classes that were removed in Java 9. The `ImageIO` task is the recommended replacement for image manipulation in modern Ant environments.

## Groovy AntBuilder DSL Equivalent (Conceptual for Deprecated Task)

While the `Image` task is deprecated, if you were to conceptualize its usage with Groovy's `AntBuilder` for older systems where it might still be relevant, it would involve invoking methods corresponding to the XML attributes and nested elements.

```groovy
// Conceptual Groovy AntBuilder DSL for the deprecated Image task
// This is for illustrative purposes only, as the task is deprecated.

ant = new AntBuilder()

// Example: Conceptually resizing an image (actual implementation would depend on JAI availability)
// ant.image(src: "input.jpg", dest: "output_resized.jpg") {
//    scale(width: "100", height: "100", proportions: "keep")
// }

// Example: Conceptually rotating an image
// ant.image(src: "input.jpg", dest: "output_rotated.jpg") {
//    rotate(angle: "90")
// }
```

Given the deprecation, direct Groovy examples for current Ant versions would focus on the `ImageIO` task instead.

## Parameter Mapping (Historical / Conceptual)

This mapping is based on the historical functionality of the `Image` task before its deprecation. For current image processing needs, refer to the `ImageIO` task or other image manipulation libraries.

| Ant Attribute | Groovy Parameter (Conceptual) | Description                                                                                                |
|---------------|-------------------------------|------------------------------------------------------------------------------------------------------------|
| `src`         | `src`                         | The source image file or a directory containing image files.                                               |
| `dest`        | `dest`                        | The destination file or directory for the processed images.                                                |
| `overwrite`   | `overwrite`                   | Whether to overwrite existing files in the destination.                                                    |
| `failonerror` | `failonerror`                 | Whether the build should fail if an error occurs during image processing.                                  |

### Nested Elements (Historical / Conceptual)

The `Image` task supported various nested elements to define image operations:

*   **`<scale>`**: To resize an image. Parameters might include `width`, `height`, `factor`, `proportions`.
*   **`<rotate>`**: To rotate an image. Parameters might include `angle`.
*   **`<draw>`**: To draw shapes or text. Parameters would define the shape, position, color, text content, font, etc.
*   **`<filter>`**: To apply image filters (e.g., blur, sharpen). Specific filter types and parameters would be nested.

These would translate into method calls on the `image` object in a hypothetical Groovy DSL, often with nested closures for complex operations.

## Code Examples (Historical / Conceptual)

As this task is deprecated and relies on older Java features, providing runnable modern code examples is not practical. The examples below are conceptual, based on the documented functionality of the original Ant `Image` task.

### Conceptual Example 1: Resizing an image

**Ant XML (Historical):**
```xml
<image src="input.jpg" dest="resized.jpg">
  <scale width="300" height="200" proportions="no"/>
</image>
```

**Conceptual Groovy AntBuilder DSL:**
```groovy
// Assuming 'ant' is an AntBuilder instance
// This is a conceptual representation
/*
ant.image(src: "input.jpg", dest: "resized.jpg") {
    scale(width: "300", height: "200", proportions: "no")
}
*/
```

### Conceptual Example 2: Rotating and adding a watermark

**Ant XML (Historical):**
```xml
<image src="photo.png" dest="processed_photo.png">
  <rotate angle="90"/>
  <draw xloc="10" yloc="10">
    <text string="Watermark Text" font="Arial" size="10" color="gray"/>
  </draw>
</image>
```

**Conceptual Groovy AntBuilder DSL:**
```groovy
// Assuming 'ant' is an AntBuilder instance
// This is a conceptual representation
/*
ant.image(src: "photo.png", dest: "processed_photo.png") {
    rotate(angle: "90")
    draw(xloc: "10", yloc: "10") {
        text(string: "Watermark Text", font: "Arial", size: "10", color: "gray")
    }
}
*/
```

## Notes on Deprecation and Alternatives:

*   The Apache Ant `Image` task is **deprecated** due to its reliance on Java Advanced Imaging (JAI) API components that are no longer standard in modern Java versions (Java 9+).
*   For image manipulation tasks in Ant build scripts, the **`ImageIO` task** is the recommended alternative if a built-in Ant task is desired and suitable for the Java version in use.
*   Alternatively, consider using external command-line tools for image manipulation, invoked via the `exec` task, or using scripting languages like Groovy with appropriate image processing libraries if more complex operations are needed and the build environment supports them.
*   When migrating from the `Image` task, carefully review the capabilities of `ImageIO` or other chosen tools to ensure they meet your specific image processing requirements.

