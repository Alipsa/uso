# Ant Task: ImageIO

## Original Ant Task Description

The `ImageIO` task in Ant is used for performing various image manipulation operations. It leverages Java's ImageIO API, providing a more modern and robust solution compared to the deprecated `Image` task (which relied on JAI - Java Advanced Imaging API). This task allows for reading, writing, and modifying image files in various formats.

## Groovy AntBuilder DSL Equivalent

Groovy's `AntBuilder` can be used to interact with the `ImageIO` task. The syntax is similar to the XML version, with attributes being passed as named parameters to the `imageIo` method (or a custom method name if you've defined one for this task).

### Key Parameters (and their Groovy equivalents):

*   **`src`**: Specifies the source image file to be processed. (e.g., `src: 'input.jpg'`) 
*   **`dest`**: Specifies the destination file for the processed image. (e.g., `dest: 'output.png'`) 
*   **`overwrite`**: Boolean, if `true`, overwrites the destination file if it already exists. Defaults to `false`. (e.g., `overwrite: true`)
*   **`format`**: Specifies the output image format (e.g., "png", "jpg", "gif"). If not specified, it often defaults to the input format or a common format like PNG. (e.g., `format: 'png'`)
*   **Nested Operations**: `ImageIO` often works with nested elements to define specific operations like `scale`, `rotate`, `crop`, `drawtext`, etc. These would be represented as closures or method calls within the `imageIo` block in Groovy.

### Example Groovy DSL Usage:

```groovy
// Ensure you have AntBuilder initialized, e.g., ant = new AntBuilder()

// Example 1: Convert an image to PNG
// <imageio src="input.jpg" dest="output.png" format="png"/>
ant.imageIo(src: 'input.jpg', dest: 'output.png', format: 'png')

// Example 2: Scale an image to a specific width, maintaining aspect ratio
// <imageio src="source.jpg" dest="scaled.jpg">
//   <scale width="300"/>
// </imageio>
ant.imageIo(src: 'source.jpg', dest: 'scaled.jpg') {
    scale(width: 300) // Assuming scale is a nested element/method
}

// Example 3: Rotate an image by 90 degrees
// <imageio src="original.png" dest="rotated.png">
//   <rotate angle="90"/>
// </imageio>
ant.imageIo(src: 'original.png', dest: 'rotated.png') {
    rotate(angle: 90)
}

// Example 4: Add a text watermark
// <imageio src="photo.jpeg" dest="watermarked.jpeg">
//   <drawtext text="Copyright 2025" font="Arial" size="24" color="gray" x="10" y="20"/>
// </imageio>
ant.imageIo(src: 'photo.jpeg', dest: 'watermarked.jpeg') {
    // Assuming 'drawtext' is a supported nested operation or a custom method
    // For simplicity, let's assume a 'text' element similar to other image libraries
    // This might need to be adapted based on actual ImageIO task capabilities
    // For Ant's built-in Image task, it would be <text ... />
    // For ImageIO, it might be more complex or require external libraries called via Ant
    // Let's assume a hypothetical 'drawtext' element for this example
    // drawtext(text: 'Copyright 2025', font: 'Arial', size: 24, color: 'gray', x: 10, y: 20)
    // If direct text drawing isn't supported, this might involve creating an image with text
    // and then overlaying it. For this example, we'll assume a direct way.
}

// Example 5: More complex operation with multiple steps
// <imageio src="raw_image.tiff" dest="final_image.png" overwrite="true">
//   <scale width="800" height="600"/>
//   <rotate angle="-45"/>
//   <drawtext text="Draft" font="Impact" size="72" color="rgba(255,0,0,0.5)" x="50%" y="50%"/>
// </imageio>
ant.imageIo(src: 'raw_image.tiff', dest: 'final_image.png', overwrite: true) {
    scale(width: 800, height: 600) // Assuming scale is a nested element/method
    rotate(angle: -45) // Assuming rotate is a nested element/method
    // For text with specific styling, it might involve more detailed parameters
    // or a separate 'text' element with its own attributes.
    // text(string: "Draft", font: "Impact", size: 72, color: "rgba(255,0,0,0.5)", x: "50%", y: "50%")
}
```

**Note on Nested Operations in Groovy AntBuilder:**

When dealing with nested elements like `<scale>`, `<rotate>`, or `<drawtext>` within the `imageIo` task in Groovy, you would typically define them as method calls within the `imageIo` closure. The method names would correspond to the Ant task element names (e.g., `scale(...)`, `rotate(...)`).

If `ImageIO` task in Ant uses a different mechanism for these operations (e.g., a generic `operation` element with a `type` attribute), the Groovy syntax would need to be adjusted accordingly. The examples above assume direct method calls for clarity, which is a common pattern in Groovy builders.

**Important Considerations for `ImageIO` Task:**

*   **Availability**: Ensure the `ImageIO` task is available in your Ant distribution. It's a standard task, but it's good to be aware of its dependencies (like Java's ImageIO API).
*   **Image Formats**: While ImageIO supports many formats, the specific formats supported can depend on the Java runtime environment and any additionally installed ImageIO plugins.
*   **Performance**: For very complex image manipulations or batch processing of a large number of images, performance might be a consideration. External image processing libraries or tools might be more efficient for heavy-duty tasks.
*   **Error Handling**: Implement appropriate error handling (e.g., `try-catch` blocks) when using `AntBuilder` to deal with potential issues during image processing, such as file not found, unsupported formats, or processing errors.

This documentation provides a general overview. For specific parameters and advanced usage, always refer to the official Apache Ant documentation for the `ImageIO` task and any related image manipulation tasks or types you intend to use.

