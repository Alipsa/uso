# tstamp

The `tstamp` task sets timestamp properties in the project:  
- `DSTAMP` (date stamp in `yyyyMMdd`),  
- `TSTAMP` (time stamp in `HHmm`), and  
- `TODAY` (full date in `MMMM dd yyyy`).  

Use nested `<format>` elements to create additional custom properties with specific patterns, locales, timezones, or offsets.

## Usage Examples

```groovy
// Set default DSTAMP, TSTAMP, and TODAY properties
target('initTime') {
  tstamp()
}

// Prefix standard properties and add a UK-formatted date property
target('customFormats') {
  tstamp(prefix: 'start') {
    format(property: 'GB', pattern: 'd-MMMM-yyyy', locale: 'en,GB')
  }
}

// Create a timestamp property 5 hours before now for use in touch
target('touchTime') {
  tstamp() {
    format(
      property: 'touch.time',
      pattern: 'MM/dd/yyyy hh:mm aa',
      offset: '-5',
      unit: 'hour'
    )
  }
}
```

## Attributes

| Attribute | Description                                                                                              | Required |
|-----------|----------------------------------------------------------------------------------------------------------|----------|
| `prefix`  | Prefix to apply to `DSTAMP`, `TSTAMP`, and `TODAY` property names (e.g., `prefix="start"` yields `start.DSTAMP`) | No       |

## Nested Elements

### `<format>`

Defines a custom date/time property with a specific pattern:

| Attribute  | Description                                                                                                                             | Required |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------|----------|
| `property` | Name of the property to set                                                                                                             | Yes      |
| `pattern`  | Date/time pattern as per Java `SimpleDateFormat`                                                                                        | Yes      |
| `timezone` | Time zone ID (e.g., `UTC`, `America/New_York`)                                                                                          | No       |
| `locale`   | Locale identifier (`language[,country[,variant]]`, e.g., `en,GB` or `fr`)                                                               | No       |
| `offset`   | Numeric offset to apply to the time before formatting (positive or negative)                                                            | No       |
| `unit`     | Unit for the offset: `millisecond`, `second`, `minute`, `hour`, `day`, `week`, `month`, or `year`                                        | No       |
| `date`     | **(Ant 1.10.2+)** Fixed epoch seconds (integer) or ISO date string to override "now" for reproducible builds (`ant.tstamp.now`/`ant.tstamp.now.iso`) | No       |

## Notes

- Introduced in Ant 1.6, enhanced in Ant 1.10.2 to support reproducible builds via `ant.tstamp.now` or `ant.tstamp.now.iso`, and in Ant 1.10.8 to respect `SOURCE_DATE_EPOCH` environment variable.
- Default `format`s use the JVM’s default time zone and locale.
- Best used in an initialization target to capture build start time.

## Reference

- [Ant Manual: tstamp Task](https://ant.apache.org/manual/Tasks/tstamp.html)
