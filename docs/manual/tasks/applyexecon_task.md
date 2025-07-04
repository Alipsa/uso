## Apply/ExecOn Task (Groovy DSL)

_The name `execon` is deprecated; `apply` should be used._

### Description

The `apply` task executes a system command, passing files and/or directories from one or more Resource Collections (like FileSets, DirSets, or FileLists) as arguments to the command. It is useful for applying a command to a set of files.

In Groovy AntBuilder, the `apply` task is invoked as `ant.apply(...)`. Its attributes are passed as named parameters, and nested elements like resource collections, mappers, arguments, and environment variables are defined within a closure.

### Parameters

Common Ant attributes for the `apply` task and their Groovy AntBuilder DSL mapping:

*   `executable`: The command to execute. (Required) Groovy: `executable: 'mycommand'`
*   `dir`: The directory in which the command should be executed. Groovy: `dir: 'working_dir'`
*   `os`: List of Operating Systems on which the command may be executed. Groovy: `os: 'Linux,Mac OS X'`
*   `output`: File to redirect standard output to. Groovy: `output: 'output.log'`
*   `error`: File to redirect standard error to. Groovy: `error: 'error.log'`
*   `logError`: If `true`, error output is also sent to Ant's log. Groovy: `logError: true`
*   `append`: If `true`, append output/error to existing files. Groovy: `append: true`
*   `input`: File to use as standard input. Groovy: `input: 'input.txt'`
*   `inputstring`: String to use as standard input. Groovy: `inputstring: 'some input'`
*   `outputproperty`: Property to store standard output. Groovy: `outputproperty: 'cmd.output'`
*   `errorproperty`: Property to store standard error. Groovy: `errorproperty: 'cmd.error'`
*   `resultproperty`: Property to store the command's return code. Groovy: `resultproperty: 'cmd.result'`
*   `timeout`: Timeout in milliseconds. Groovy: `timeout: 60000`
*   `failonerror`: If `true` (default is `false` for `apply`, but often `true` for `exec`), stop build if command fails. Groovy: `failonerror: true`
*   `spawn`: If `true`, run command in background (output not logged by Ant). Groovy: `spawn: true`
*   `parallel`: If `true` (default is `false`), run command once with all files as arguments. If `false`, command is run once per file. Groovy: `parallel: true`
*   `skipemptyfilesets`: If `true`, don't run if no source files. Groovy: `skipemptyfilesets: true`
*   `addsourcefile`: If `false` (default is `true`), source file names are not automatically added to the command. Groovy: `addsourcefile: false`
*   `dest`: Destination directory when using a mapper. Groovy: `dest: 'output_dir'`
*   `relative`: If `true`, pass relative paths. Groovy: `relative: true`
*   `forwardslash`: If `true`, use forward slashes in paths. Groovy: `forwardslash: true`
*   `type`: Type of resources to process (`file`, `dir`, `both`). Groovy: `type: 'file'`
*   `maxparallel`: Max number of source files per command when `parallel` is `true`. Groovy: `maxparallel: 10`
*   `newenvironment`: If `true`, don't propagate existing environment variables. Groovy: `newenvironment: true`

### Nested Elements

*   **Resource Collections** (e.g., `fileset`, `filelist`, `dirset`, or generic `resources`):
    ```groovy
    ant.apply(executable: 'process') {
        fileset(dir: 'src_files', includes: '*.txt')
    }
    ```
*   **arg**: Specifies command-line arguments.
    ```groovy
    ant.apply(executable: 'compiler') {
        arg(value: '-o')
        arg(value: 'outputfile')
        fileset(dir: 'src', includes: '*.c') // Files will be appended after these args
    }
    ```
*   **srcfile**: Used within a nested `arg` to represent the source file(s) being processed. This is particularly useful when `parallel` is `false` or when you need to control the position of the filename argument.
    ```groovy
    ant.apply(executable: 'process_file', parallel: false) {
        fileset(dir: 'data', includes: '*.dat')
        arg(value: '--input')
        srcfile() // Represents the current source file
        arg(value: '--output')
        arg(line: "${ant.project.getProperty('build.dir')}/${current.file.name}.out") // Example of constructing output name
    }
    ```
*   **targetfile**: Used within a nested `arg` in conjunction with a `mapper` to represent the target file(s).
    ```groovy
    ant.apply(executable: 'transform', dest: 'output') {
        fileset(dir: 'input', includes: '*.xml')
        mapper(type: 'glob', from: '*.xml', to: '*.xslt.xml')
        arg(value: '-i')
        srcfile()
        arg(value: '-o')
        targetfile()
    }
    ```
*   **env**: Defines environment variables for the command.
    ```groovy
    ant.apply(executable: 'myscript.sh') {
        env(key: 'MY_VAR', value: 'my_value')
        fileset(dir: 'scripts')
    }
    ```
*   **mapper**: Defines a mapping from source files to target files, often used with `dest`.
    ```groovy
    ant.apply(executable: 'copy_and_rename', dest: 'backup') {
        fileset(dir: 'original', includes: '*.txt')
        mapper(type: 'glob', from: '*.txt', to: '*.txt.bak')
        // Command would typically take srcfile and targetfile as args
        arg(value: 'cp') 
        srcfile()
        targetfile() // targetfile() needs a mapper and usually dest
    }
    ```

### Examples

1.  **Apply `chmod` to all `.sh` files (parallel=false, one by one):**

    *   **Ant XML:**
        ```xml
        <apply executable="chmod" parallel="false">
            <arg value="755"/>
            <fileset dir="scripts" includes="*.sh"/>
        </apply>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.apply(executable: 'chmod', parallel: false) {
            arg(value: '755')
            fileset(dir: 'scripts', includes: '*.sh')
        }
        ```

2.  **Compile all `.java` files in parallel, passing them to `javac`:**

    *   **Ant XML:**
        ```xml
        <apply executable="javac" parallel="true" dir="src">
            <arg value="-d"/>
            <arg value="../classes"/>
            <srcfile/> <!-- Placeholder for all source files -->
            <fileset dir="." includes="**/*.java"/>
        </apply>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.apply(executable: 'javac', parallel: true, dir: 'src') {
            arg(value: '-d')
            arg(path: '../classes') // Using arg(path:...) for file paths is good practice
            srcfile() // Indicates where the list of source files should be inserted
            fileset(dir: '.', includes: '**/*.java')
        }
        ```

3.  **Convert files using a mapper:**

    *   **Ant XML:**
        ```xml
        <apply executable="convertImage" dest="output/pngs">
            <arg value="--format"/>
            <arg value="png"/>
            <srcfile/>
            <arg value="--output"/>
            <targetfile/>
            <fileset dir="input/jpegs" includes="*.jpg"/>
            <mapper type="glob" from="*.jpg" to="*.png"/>
        </apply>
        ```
    *   **Groovy AntBuilder:**
        ```groovy
        ant.apply(executable: 'convertImage', dest: 'output/pngs') {
            arg(value: '--format')
            arg(value: 'png')
            srcfile()
            arg(value: '--output')
            targetfile()
            fileset(dir: 'input/jpegs', includes: '*.jpg')
            mapper(type: 'glob', from: '*.jpg', to: '*.png')
        }
        ```

### Navigation

*   [Previous Task: Ant Task](Ant_Task_Groovy.md)
*   [Next Task: Attrib Task](Attrib_Task_Groovy.md)
*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)
