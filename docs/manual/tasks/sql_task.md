# sql

The `sql` task executes a series of SQL statements via JDBC to a database. Statements can either be read from a text file using the `src` attribute or provided via nested `<transaction>` elements, with support for statement delimiters, transaction control, and result output. citeturn1view0

## Usage Examples

```groovy
target('executeSqlFile') {
  sql(
    driver: 'org.database.jdbcDriver',
    url: 'jdbc:database-url',
    userid: 'sa',
    password: 'pass',
    src: 'data.sql'
  )
}

target('multiTransaction') {
  sql(
    driver: 'org.database.jdbcDriver',
    url: 'jdbc:database-url',
    userid: 'sa',
    password: 'pass'
  ) {
    // Execute multiple SQL files in separate transactions
    transaction(src: 'data1.sql')
    transaction(src: 'data2.sql')
    // Inline SQL in its own transaction
    transaction {
      // Note: consider wrapping in CDATA for special characters
      echo 'truncate table some_other_table;'
    }
  }
}

target('withConnectionProps') {
  sql(
    driver: 'org.database.jdbcDriver',
    url: 'jdbc:database-url',
    userid: 'sa',
    password: 'pass'
  ) {
    connectionProperty(name: 'internal_logon', value: 'SYSDBA')
    transaction(src: 'data.sql')
  }
}
```

## Attributes

|                 Attribute | Description                                                                                              | Required    |
|--------------------------:|----------------------------------------------------------------------------------------------------------|-------------|
|                  `driver` | Fully qualified class name of the JDBC driver                                                            | Yes         |
|                     `url` | JDBC connection URL for the database                                                                     | Yes         |
|                  `userid` | Database user name                                                                                       | Yes         |
|                `password` | Database password                                                                                        | Yes         |
|                     `src` | File containing SQL statements; required if no nested `<transaction>` elements are provided              | Conditional |
|                `encoding` | Character encoding of the SQL files (defaults to JVM default)                                            | No          |
|          `outputencoding` | Character encoding for result output files (since Ant 1.9.4; defaults to JVM default)                    | No          |
|               `delimiter` | String separating SQL statements (defaults to `;`)                                                       | No          |
|              `autocommit` | Whether to enable auto-commit (defaults to `false`)                                                      | No          |
|                   `print` | Print result sets to output (defaults to `false`)                                                        | No          |
|             `showheaders` | Print column headers in result sets (defaults to `true`)                                                 | No          |
|            `showtrailers` | Print trailers indicating number of rows affected (defaults to `true`)                                   | No          |
|                  `output` | Output file or resource for result sets (defaults to `System.out`)                                       | No          |
|                  `append` | Append to `output` if it exists (defaults to `false`)                                                    | No          |
|               `classpath` | Classpath for loading the JDBC driver                                                                    | No          |
|            `classpathref` | Reference to a `<path>` element defining the classpath                                                   | No          |
|                 `onerror` | Action on SQL error: `continue`, `stop`, or `abort` (defaults to `abort`)                                | No          |
|                   `rdbms` | Only execute if the RDBMS name matches this string (defaults to unrestricted)                            | No          |
|                 `version` | Only execute if RDBMS version matches this prefix (defaults to unrestricted)                             | No          |
|                 `caching` | Cache JDBC drivers and loaders (defaults to `true`)                                                      | No          |
|           `delimitertype` | Statement delimiter recognition mode: `normal` or `row` (defaults to `normal`)                           | No          |
|              `keepformat` | Preserve SQL formatting (useful for stored procedures; defaults to `false`)                              | No          |
|        `escapeprocessing` | Enable JDBC escape substitution (defaults to `true`)                                                     | No          |
|        `expandproperties` | Enable property expansion in SQL (since Ant 1.7; defaults to `true`)                                     | No          |
|                `rawblobs` | Write BLOB results as raw streams instead of hex (since Ant 1.7.1; defaults to `false`)                  | No          |
|   `failOnConnectionError` | Fail task if connection cannot be established (since Ant 1.8.0; defaults to `true`)                      | No          |
| `strictDelimiterMatching` | Case-sensitive delimiter matching and strict whitespace (since Ant 1.8.0; defaults to `true`)            | No          |
|            `showWarnings` | Log SQL warnings at warning level (since Ant 1.8.0; defaults to `false`)                                 | No          |
|   `treatWarningsAsErrors` | Treat SQL warnings as errors (since Ant 1.8.0; defaults to `false`)                                      | No          |
|      `csvColumnSeparator` | Column separator for CSV output (since Ant 1.8.0; defaults to `,`)                                       | No          |
|       `csvQuoteCharacter` | Character for quoting CSV values; doubled inside values (since Ant 1.8.0; no default)                    | No          |
|       `forceCsvQuoteChar` | Always quote CSV values (since Ant 1.8.0; no default)                                                    | No          |
|           `errorproperty` | Property name to set on an error occurring (since Ant 1.8.0)                                             | No          |
|         `warningproperty` | Property name to set when a warning occurs (since Ant 1.8.0)                                             | No          |
|        `rowcountproperty` | Property name to set with number of rows affected by first statement returning a count (since Ant 1.8.0) | No          |

## Nested Elements

- `<transaction>` — Execute SQL statements in a separate transaction (attribute: `src`) or inline SQL.
- `<connectionProperty>` — Set additional JDBC connection properties (`name`, `value`).
- Any Ant resource collection (e.g., `<fileset>`, `<path>`) may be used to specify SQL sources.

## Notes

- Wrap inline SQL containing `<`, `>`, or `&` in CDATA sections to avoid XML parsing issues.
- The `sql` task honors JVM proxy settings unless disabled via `-noproxy`.
- Use `autocommit: "false"` to execute multiple statements in a single transaction.
- `onerror: "continue"` allows processing to continue despite SQL errors.

## Complete example:
```groovy
@GrabConfig(systemClassLoader=true)
@Grab('com.h2database:h2:2.3.232')
import groovy.ant.AntBuilder
def project = new AntBuilder()
project.with {
  sql(
    driver:     "org.h2.Driver",
    url:        "jdbc:h2:mem:AZ",
    userid:     "sa",
    password:   "",
    // direct printed output into a text file:
    output:     "query.out",
    print:      "yes",      // enable printing of result sets
    showheaders:"false",    // suppress column names
    showtrailers:"false"    // suppress "N rows returned" line
  ) {
    transaction("""
      CREATE TABLE some_table (
        id   INT,
        name VARCHAR(200)
      );
    """)
    transaction("""
      INSERT INTO some_table (id, name)
      VALUES (1, 'hello');
    """)
    transaction("""
      SELECT name
      FROM some_table
      WHERE id = 1;
    """)
  }
  // now the file query.out contains exactly "hello"
  loadfile(property: "row1col1", srcFile: "query.out")
  echo(message: 'row1col1 = ${row1col1}')
}
```
This will output
```shell
      [sql] Executing commands
      [sql] Executing commands
      [sql] Executing commands
      [sql] 3 of 3 SQL statements executed successfully
     [echo] row1col1 = hello
```
## Reference

- [Ant Manual: sql Task](https://ant.apache.org/manual/Tasks/sql.html) citeturn1view0
