# Uso tasks overview
The following table lists all ant tasks available in uso out of the box. It is assumed that they are called from within a project.with{...} closure. These tasks are also valid for AntBuilder assuming you call them similarly e.g. `new AntBuilder().with {...}`. If using AntBuilder directly, you will need to set up the appropriate classpath (e.g. using Grab) for the taskdef to work.

The availability column indicates whether:
- You can use the task directly (core tasks)
- Need to add a taskdef (optional tasks)
- Need to add a taskdef if executing the task from AntBuilder (uso tasks)

| Task                                                 | Description                                                                                         | Availability |
|------------------------------------------------------|-----------------------------------------------------------------------------------------------------|--------------|
| [Ant](tasks/ant_task.md)                             | Invokes another Ant build file within the current project, allowing reuse of existing builds.       | core         |
| [AntCall](tasks/antcall_task.md)                     | Calls a different target in the same Ant project, respecting dependencies and property inheritance. | core         |
| [ANTLR](tasks/antlr_task.md)                         | Runs the ANTLR tool to generate parser and lexer Java code from grammar files.                      | core         |
| [AntStructure](tasks/antstructure_task.md)           | Outputs the internal structure of the Ant project as XML for debugging.                             | core         |
| [AntVersion](tasks/antversion_task.md)               | Prints the version of the Ant runtime in use.                                                       | core         |
| [Apply/ExecOn](tasks/apply_execon_task.md)           | Applies an external command or script to each file in a fileset for batch processing.               | core         |
| [Attrib](tasks/attrib_task.md)                       | Changes file or directory attributes (e.g., read-only, hidden) on Windows platforms.                | core         |
| [Augment](tasks/augment_task.md)                     | Adds additional entries to an existing archive without rebuilding it.                               | core         |
| [Available](tasks/available_task.md)                 | Checks whether a class, file, or resource exists and sets a property accordingly.                   | core         |
| [Basename](tasks/basename_task.md)                   | Extracts the filename portion of a path, omitting directory and suffix.                             | core         |
| [Bindtargets](tasks/bindtargets_task.md)             | Binds targets to build events (start, finish) so they execute automatically.                        | core         |
| [BuildNumber](tasks/buildnumber_task.md)             | Generates a unique, timestamp-based build number and stores it in a property.                       | core         |
| [BUnzip2](tasks/bunzip2_task.md)                     | Decompresses `.bz2` files using the BZip2 algorithm.                                                | optional     |
| [BZip2](tasks/bzip2_task.md)                         | Compresses files into the BZip2 (`.bz2`) format.                                                    | optional     |
| [Cab](tasks/cab_task.md)                             | Extracts files from Microsoft CAB archives on Windows.                                              | optional     |
| [Checksum](tasks/checksum_task.md)                   | Computes and logs or stores checksums (MD5, SHA1) of specified files.                               | core         |
| [Chgrp](tasks/chgrp_task.md)                         | Changes the group ownership of files on UNIX systems.                                               | core         |
| [Chmod](tasks/chmod_task.md)                         | Modifies file permissions (mode) on UNIX systems.                                                   | core         |
| [Chown](tasks/chown_task.md)                         | Changes file ownership on UNIX systems.                                                             | core         |
| [ComponentDef](tasks/componentdef_task.md)           | Defines a custom task or data type component for reuse in the build.                                | core         |
| [Concat](tasks/concat_task.md)                       | Concatenates multiple files or strings into a single output file.                                   | core         |
| [Condition](tasks/condition_task.md)                 | Evaluates a boolean condition and sets a property if true.                                          | core         |
| [Copy](tasks/copy_task.md)                           | Copies files and directories with support for filtering, flattening, and mapping.                   | core         |
| [CreatePom](tasks/createpom_task.md)                 | Generates a Maven POM file based on project coordinates and dependencies.                           | uso          |
| [Delete](tasks/delete_task.md)                       | Removes files or directories, optionally cleaning up empty parents.                                 | core         |
| [Deltree](tasks/deltree_task.md)                     | Recursively deletes directory trees (deprecated in favor of `<delete>`).                            | core         |
| [Depend](tasks/depend_task.md)                       | Declares file dependencies for up-to-date checking between sources and targets.                     | core         |
| [Dependset](tasks/dependset_task.md)                 | Defines a set of files sharing dependency rules.                                                    | core         |
| [Diagnostics](tasks/diagnostics_task.md)             | Prints internal Ant diagnostics including classpath and environment info.                           | core         |
| [Dirname](tasks/dirname_task.md)                     | Extracts the directory portion of a path into a property.                                           | core         |
| [Ear](tasks/ear_task.md)                             | Builds a Java EE EAR archive with modules and descriptors.                                          | core         |
| [Echo](tasks/echo_task.md)                           | Prints messages or property values to the console or a file.                                        | core         |
| [EchoXML](tasks/echoxml_task.md)                     | Dumps the project structure (targets, properties) as XML.                                           | core         |
| [Echoproperties](tasks/echoproperties_task.md)       | Lists all Ant properties to console or file.                                                        | core         |
| [Exec](tasks/exec_task.md)                           | Executes external programs or scripts with argument and environment control.                        | core         |
| [FTP](tasks/ftp_task.md)                             | Transfers files to/from FTP servers.                                                                | optional     |
| [Fail](tasks/fail_task.md)                           | Stops the build with a custom message when conditions are met.                                      | core         |
| [Filter](tasks/filter_task.md)                       | Applies one-off filters from files to resources.                                                    | core         |
| [FixCRLF](tasks/fixcrlf_task.md)                     | Normalizes text file line endings between CRLF and LF.                                              | core         |
| [GUnzip](tasks/gunzip_task.md)                       | Decompresses gzip (`.gz`) files.                                                                    | core         |
| [GZip](tasks/gzip_task.md)                           | Compresses files into gzip (`.gz`) format.                                                          | core         |
| [GenKey](tasks/genkey_task.md)                       | Generates key pairs for Java KeyStores.                                                             | core         |
| [Get](tasks/get_task.md)                             | Downloads files via HTTP, FTP, or other URL protocols.                                              | optional     |
| [Groovyc](tasks/groovyc_task.md)                     | Compiles Groovy source files to JVM bytecode.                                                       | core         |
| [Hostinfo](tasks/hostinfo_task.md)                   | Retrieves host metadata (name, domain, IP) into properties.                                         | core         |
| [ImageIO](tasks/imageio_task.md)                     | Advanced control over Java ImageIO operations.                                                      | core         |
| [Import](tasks/import_task.md)                       | Imports targets and properties from another build file.                                             | core         |
| [Include](tasks/include_task.md)                     | Embeds another build file at parse time.                                                            | core         |
| [Input](tasks/input_task.md)                         | Prompts for user input and sets result to a property.                                               | core         |
| [JDepend](tasks/jdepend_task.md)                     | Runs JDepend to analyze Java package dependencies.                                                  | optional     |
| [JJDoc](tasks/jjdoc_task.md)                         | Generates documentation from JavaCC grammars.                                                       | optional     |
| [JJTree](tasks/jjtree_task.md)                       | Preprocesses JavaCC grammars into `.jj` files.                                                      | optional     |
| [JUnit](tasks/junit_task.md)                         | Runs JUnit 3/4 tests with forks and reports.                                                        | core         |
| [JUnitLauncher](tasks/junitlauncher_task.md)         | Executes JUnit 5 tests via the JUnit Platform.                                                      | core         |
| [JUnitReport](tasks/junitreport_task.md)             | Processes JUnit XML results into HTML reports.                                                      | core         |
| [Jar](tasks/jar_task.md)                             | Creates or updates JAR archives from classes and resources.                                         | core         |
| [Jarlib-available](tasks/jarlib-available_task.md)   | Checks Optional Package extension availability in JARs.                                             | core         |
| [Jarlib-display](tasks/jarlib-display_task.md)       | Lists Optional Package extensions in JAR manifests.                                                 | core         |
| [Jarlib-manifest](tasks/jarlib-manifest_task.md)     | Generates Optional Package manifest entries.                                                        | core         |
| [Jarlib-resolve](tasks/jarlib-resolve_task.md)       | Resolves JARs satisfying Optional Package extensions.                                               | core         |
| [Java](tasks/java_task.md)                           | Launches Java classes in a separate JVM with classpath control.                                     | core         |
| [JavaCC](tasks/javacc_task.md)                       | Generates Java parser code using JavaCC.                                                            | optional     |
| [Javac](tasks/javac_task.md)                         | Compiles Java source code.                                                                          | core         |
| [Javadoc/Javadoc2](tasks/javadoc_task.md)            | Generates API documentation with Javadoc.                                                           | core         |
| [Jmod](tasks/jmod_task.md)                           | Creates or extracts `.jmod` module files.                                                           | core         |
| [Layout](tasks/layout_task.md)                       | Initializes a standard project directory layout and properties.                                     | uso          |
| [Length](tasks/length_task.md)                       | Measures string lengths or resource sizes, setting properties or conditions.                        | core         |
| [Link](tasks/link_task.md)                           | Creates custom Java runtime images via `jlink`.                                                     | core         |
| [LoadFile](tasks/loadfile_task.md)                   | Loads file contents into a property.                                                                | core         |
| [LoadProperties](tasks/loadproperties_task.md)       | Loads properties files into project properties with filtering.                                      | core         |
| [LoadResource](tasks/loadresource_task.md)           | Reads any resource into a property.                                                                 | core         |
| [Local](tasks/local_task.md)                         | Declares properties local to a scope.                                                               | core         |
| [MacroDef](tasks/macrodef_task.md)                   | Defines reusable build macros with parameters.                                                      | core         |
| [Mail](tasks/mail_task.md)                           | Sends SMTP emails with attachments and MIME support.                                                | core         |
| [MakeURL](tasks/makeurl_task.md)                     | Converts file paths to `file:` URLs for Class-Path entries.                                         | core         |
| [Manifest](tasks/manifest_task.md)                   | Creates or updates JAR manifest files.                                                              | core         |
| [ManifestClassPath](tasks/manifestclasspath_task.md) | Computes manifest `Class-Path` entries from a Path.                                                 | core         |
| [Mkdir](tasks/mkdir_task.md)                         | Creates directories, including parents.                                                             | core         |
| [Move](tasks/move_task.md)                           | Relocates files and directories with filtering and mapping.                                         | core         |
| [Native2Ascii](tasks/native2ascii_task.md)           | Converts non-ASCII text to Unicode escapes in files.                                                | core         |
| [NetRexxC](tasks/netrexxc_task.md)                   | Compiles NetRexx sources to bytecode.                                                               | core         |
| [Nice](tasks/nice_task.md)                           | Runs nested tasks with adjusted UNIX niceness.                                                      | core         |
| [Parallel](tasks/parallel_task.md)                   | Executes multiple tasks concurrently in threads.                                                    | core         |
| [Patch](tasks/patch_task.md)                         | Applies unified diff patches to files or collections.                                               | core         |
| [PathConvert](tasks/pathconvert_task.md)             | Converts paths or resources to delimited strings.                                                   | core         |
| [PreSetDef](tasks/presetdef_task.md)                 | Pre-configures tasks/types into new custom macros.                                                  | core         |
| [ProjectHelper](tasks/projecthelper_task.md)         | Imports build files via a specified ProjectHelper.                                                  | core         |
| [Property](tasks/property_task.md)                   | Sets project properties from values, files, or resources.                                           | core         |
| [PropertyFile](tasks/propertyfile_task.md)           | Updates or creates `.properties` files programmatically.                                            | core         |
| [PropertyHelper](tasks/propertyhelper_task.md)       | Installs or extends the Ant PropertyHelper.                                                         | core         |
| [Record](tasks/record_task.md)                       | Captures build output to log files dynamically.                                                     | core         |
| [Replace](tasks/replace_task.md)                     | Performs literal string replacements with optional backups.                                         | core         |
| [ReplaceRegExp](tasks/replaceregexp_task.md)         | Performs regex-based replacements using Java regex.                                                 | core         |
| [ResourceCount](tasks/resourcecount_task.md)         | Counts resources, setting properties or conditions.                                                 | core         |
| [Retry](tasks/retry_task.md)                         | Retries nested tasks until success or limit.                                                        | core         |
| [RExec](tasks/rexec_task.md)                         | Executes commands on remote hosts via the Rexec protocol.                                           | optional     |
| [Rpm](tasks/rpm_task.md)                             | Builds RPM packages using `rpmbuild`.                                                               | optional     |
| [SchemaValidate](tasks/schemavalidate_task.md)       | Validates XML files against XSD schemas with namespace and full-checking support.                   | core         |
| [Scp](tasks/scp_task.md)                             | Transfers files over SSH using SCP.                                                                 | optional     |
| [Script](tasks/script_task.md)                       | Runs embedded scripts through the BSF/Java scripting API.                                           | core         |
| [Scriptdef](tasks/scriptdef_task.md)                 | Defines new tasks/types via inline scripting.                                                       | optional     |
| [Sequential](tasks/sequential_task.md)               | Groups multiple tasks to run sequentially.                                                          | core         |
| [ServerDeploy](tasks/serverdeploy_task.md)           | Deploys Java EE artifacts via JSR-88.                                                               | core         |
| [SetPermissions](tasks/setpermissions_task.md)       | Sets UNIX file permissions (chmod).                                                                 | core         |
| [SetProxy](tasks/setproxy_task.md)                   | Configures HTTP/HTTPS proxies for network tasks.                                                    | core         |
| [SignJar](tasks/signjar_task.md)                     | Signs JARs with `jarsigner`.                                                                        | core         |
| [Sleep](tasks/sleep_task.md)                         | Pauses build execution for a specified duration.                                                    | core         |
| [Sound](tasks/sound_task.md)                         | Plays system beep or sound files.                                                                   | core         |
| [Splash](tasks/splash_task.md)                       | Displays a GUI splash screen during the build.                                                      | core         |
| [Sql](tasks/sql_task.md)                             | Executes SQL statements via JDBC.                                                                   | core         |
| [Sshexec](tasks/sshexec_task.md)                     | Executes commands on remote hosts via SSH.                                                          | optional     |
| [Sshsession](tasks/sshsession_task.md)               | Manages SSH sessions for multiple operations.                                                       | optional     |
| [Subant](tasks/subant_task.md)                       | Runs Ant builds in subdirectories for multi-project builds.                                         | core         |
| [Symlink](tasks/symlink_task.md)                     | Creates filesystem symbolic links.                                                                  | core         |
| [Sync](tasks/sync_task.md)                           | Synchronizes directories, copying only changed files.                                               | core         |
| [TStamp](tasks/tstamp_task.md)                       | Generates timestamp properties for builds.                                                          | core         |
| [Tar](tasks/tar_task.md)                             | Creates or extracts TAR archives.                                                                   | core         |
| [Taskdef](tasks/taskdef_task.md)                     | Defines new tasks or types by class name and classpath.                                             | core         |
| [Telnet](tasks/telnet_task.md)                       | Sends commands over Telnet to remote hosts.                                                         | optional     |
| [Tempfile](tasks/tempfile_task.md)                   | Creates temporary files or directories.                                                             | core         |
| [Touch](tasks/touch_task.md)                         | Updates file modification timestamps.                                                               | core         |
| [Translate](tasks/translate_task.md)                 | Translates characters in files or resources.                                                        | core         |
| [Truncate](tasks/truncate_task.md)                   | Truncates or extends files to a given length.                                                       | core         |
| [Typedef](tasks/typedef_task.md)                     | Defines new data types by class name and classpath.                                                 | core         |
| [UnXZ](tasks/unxz_task.md)                           | Decompresses `.xz` files.                                                                           | core         |
| [Unjar](tasks/unjar_task.md)                         | Extracts JAR or ZIP archives.                                                                       | core         |
| [Untar](tasks/untar_task.md)                         | Extracts TAR archives.                                                                              | core         |
| [Unwar](tasks/unwar_task.md)                         | Extracts WAR files.                                                                                 | core         |
| [Unzip](tasks/unzip_task.md)                         | Extracts ZIP archives.                                                                              | core         |
| [Uptodate](tasks/uptodate_task.md)                   | Checks whether files are up-to-date based on timestamps.                                            | core         |
| [Uso](tasks/uso_task.md)                             | Invokes another Uso Groovy build script as a sub-build.                                             | uso          |
| [VerifyJar](tasks/verifyjar_task.md)                 | Verifies JAR contents against manifest checksums.                                                   | core         |
| [Waitfor](tasks/waitfor_task.md)                     | Pauses until a specified condition is met.                                                          | core         |
| [War](tasks/war_task.md)                             | Builds WAR archives for Java web applications.                                                      | core         |
| [WhichResource](tasks/whichresource_task.md)         | Finds and logs resource locations on the classpath.                                                 | core         |
| [XSLT/Style](tasks/xslt_style_task.md)               | Applies XSLT transformations to XML files.                                                          | core         |
| [XZ](tasks/xz_task.md)                               | Compresses files into the `.xz` format.                                                             | core         |
| [XmlProperty](tasks/xmlproperty_task.md)             | Extracts XML data via XPath into properties.                                                        | core         |
| [XmlValidate](tasks/xmlvalidate_task.md)             | Validates XML well-formedness and DTDs.                                                             | core         |
| [Zip](tasks/zip_task.md)                             | Creates or extracts ZIP archives.                                                                   | core         |