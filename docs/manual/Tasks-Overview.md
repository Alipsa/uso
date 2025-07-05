# Ant tasks
| Task                                                 | Description                                                                                         |
|------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| [Ant](tasks/ant_task.md)                             | Invokes another Ant build file within the current project, allowing reuse of existing builds.       |
| [AntCall](tasks/antcall_task.md)                     | Calls a different target in the same Ant project, respecting dependencies and property inheritance. |
| [ANTLR](tasks/antlr_task.md)                         | Runs the ANTLR tool to generate parser and lexer Java code from grammar files.                      |
| [AntStructure](tasks/antstructure_task.md)           | Outputs the internal structure of the Ant project as XML for debugging.                             |
| [AntVersion](tasks/antversion_task.md)               | Prints the version of the Ant runtime in use.                                                       |
| [Apply/ExecOn](tasks/applyexecon_task.md)            | Applies an external command or script to each file in a fileset for batch processing.               |
| [Attrib](tasks/attrib_task.md)                       | Changes file or directory attributes (e.g., read-only, hidden) on Windows platforms.                |
| [Augment](tasks/augment_task.md)                     | Adds additional entries to an existing archive without rebuilding it.                               |
| [Available](tasks/available_task.md)                 | Checks whether a class, file, or resource exists and sets a property accordingly.                   |
| [Basename](tasks/basename_task.md)                   | Extracts the filename portion of a path, omitting directory and suffix.                             |
| [Bindtargets](tasks/bindtargets_task.md)             | Binds targets to build events (start, finish) so they execute automatically.                        |
| [BuildNumber](tasks/buildnumber_task.md)             | Generates a unique, timestamp-based build number and stores it in a property.                       |
| [BUnzip2](tasks/bunzip2_task.md)                     | Decompresses `.bz2` files using the BZip2 algorithm.                                                |
| [BZip2](tasks/bzip2_task.md)                         | Compresses files into the BZip2 (`.bz2`) format.                                                    |
| [Cab](tasks/cab_task.md)                             | Extracts files from Microsoft CAB archives on Windows.                                              |
| [Checksum](tasks/checksum_task.md)                   | Computes and logs or stores checksums (MD5, SHA1) of specified files.                               |
| [Chgrp](tasks/chgrp_task.md)                         | Changes the group ownership of files on UNIX systems.                                               |
| [Chmod](tasks/chmod_task.md)                         | Modifies file permissions (mode) on UNIX systems.                                                   |
| [Chown](tasks/chown_task.md)                         | Changes file ownership on UNIX systems.                                                             |
| [ComponentDef](tasks/componentdef_task.md)           | Defines a custom task or data type component for reuse in the build.                                |
| [Concat](tasks/concat_task.md)                       | Concatenates multiple files or strings into a single output file.                                   |
| [Condition](tasks/condition_task.md)                 | Evaluates a boolean condition and sets a property if true.                                          |
| [Copy](tasks/copy_task.md)                           | Copies files and directories with support for filtering, flattening, and mapping.                   |
| [CreatePom](tasks/createpom_task.md)                 | Generates a Maven POM file based on project coordinates and dependencies.                           |
| [Delete](tasks/delete_task.md)                       | Removes files or directories, optionally cleaning up empty parents.                                 |
| [Deltree](tasks/deltree_task.md)                     | Recursively deletes directory trees (deprecated in favor of `<delete>`).                            |
| [Depend](tasks/depend_task.md)                       | Declares file dependencies for up-to-date checking between sources and targets.                     |
| [Dependset](tasks/dependset_task.md)                 | Defines a set of files sharing dependency rules.                                                    |
| [Diagnostics](tasks/diagnostics_task.md)             | Prints internal Ant diagnostics including classpath and environment info.                           |
| [Dirname](tasks/dirname_task.md)                     | Extracts the directory portion of a path into a property.                                           |
| [Ear](tasks/ear_task.md)                             | Builds a Java EE EAR archive with modules and descriptors.                                          |
| [Echo](tasks/echo_task.md)                           | Prints messages or property values to the console or a file.                                        |
| [EchoXML](tasks/echoxml_task.md)                     | Dumps the project structure (targets, properties) as XML.                                           |
| [Echoproperties](tasks/echoproperties_task.md)       | Lists all Ant properties to console or file.                                                        |
| [Exec](tasks/exec_task.md)                           | Executes external programs or scripts with argument and environment control.                        |
| [FTP](tasks/ftp_task.md)                             | Transfers files to/from FTP servers.                                                                |
| [Fail](tasks/fail_task.md)                           | Stops the build with a custom message when conditions are met.                                      |
| [Filter](tasks/filter_task.md)                       | Applies one-off filters from files to resources.                                                    |
| [FixCRLF](tasks/fixcrlf_task.md)                     | Normalizes text file line endings between CRLF and LF.                                              |
| [GUnzip](tasks/gunzip_task.md)                       | Decompresses gzip (`.gz`) files.                                                                    |
| [GZip](tasks/gzip_task.md)                           | Compresses files into gzip (`.gz`) format.                                                          |
| [GenKey](tasks/genkey_task.md)                       | Generates key pairs for Java KeyStores.                                                             |
| [Get](tasks/get_task.md)                             | Downloads files via HTTP, FTP, or other URL protocols.                                              |
| [Groovyc](tasks/groovyc_task.md)                     | Compiles Groovy source files to JVM bytecode.                                                       |
| [Hostinfo](tasks/hostinfo_task.md)                   | Retrieves host metadata (name, domain, IP) into properties.                                         |
| [Image](tasks/image_task.md)                         | Reads and writes images via Java ImageIO.                                                           |
| [ImageIO](tasks/imageio_task.md)                     | Advanced control over Java ImageIO operations.                                                      |
| [Import](tasks/import_task.md)                       | Imports targets and properties from another build file.                                             |
| [Include](tasks/include_task.md)                     | Embeds another build file at parse time.                                                            |
| [Input](tasks/input_task.md)                         | Prompts for user input and sets result to a property.                                               |
| [JDepend](tasks/jdepend_task.md)                     | Runs JDepend to analyze Java package dependencies.                                                  |
| [JJDoc](tasks/jjdoc_task.md)                         | Generates documentation from JavaCC grammars.                                                       |
| [JJTree](tasks/jjtree_task.md)                       | Preprocesses JavaCC grammars into `.jj` files.                                                      |
| [JUnit](tasks/junit_task.md)                         | Runs JUnit 3/4 tests with forks and reports.                                                        |
| [JUnitLauncher](tasks/junitlauncher_task.md)         | Executes JUnit 5 tests via the JUnit Platform.                                                      |
| [JUnitReport](tasks/junitreport_task.md)             | Processes JUnit XML results into HTML reports.                                                      |
| [Jar](tasks/jar_task.md)                             | Creates or updates JAR archives from classes and resources.                                         |
| [Jarlib-available](tasks/jarlib-available_task.md)   | Checks Optional Package extension availability in JARs.                                             |
| [Jarlib-display](tasks/jarlib-display_task.md)       | Lists Optional Package extensions in JAR manifests.                                                 |
| [Jarlib-manifest](tasks/jarlib-manifest_task.md)     | Generates Optional Package manifest entries.                                                        |
| [Jarlib-resolve](tasks/jarlib-resolve_task.md)       | Resolves JARs satisfying Optional Package extensions.                                               |
| [Java](tasks/java_task.md)                           | Launches Java classes in a separate JVM with classpath control.                                     |
| [JavaCC](tasks/javacc_task.md)                       | Generates Java parser code using JavaCC.                                                            |
| [Javac](tasks/javac_task.md)                         | Compiles Java source code.                                                                          |
| [Javadoc](tasks/javadoc_task.md)                     | Generates API documentation with Javadoc.                                                           |
| [Jmod](tasks/jmod_task.md)                           | Creates or extracts `.jmod` module files.                                                           |
| [Layout](tasks/layout_task.md)                       | Initializes a standard project directory layout and properties.                                     |
| [Length](tasks/length_task.md)                       | Measures string lengths or resource sizes, setting properties or conditions.                        |
| [Link](tasks/link_task.md)                           | Creates custom Java runtime images via `jlink`.                                                     |
| [LoadFile](tasks/loadfile_task.md)                   | Loads file contents into a property.                                                                |
| [LoadProperties](tasks/loadproperties_task.md)       | Loads properties files into project properties with filtering.                                      |
| [LoadResource](tasks/loadresource_task.md)           | Reads any resource into a property.                                                                 |
| [Local](tasks/local_task.md)                         | Declares properties local to a scope.                                                               |
| [MacroDef](tasks/macrodef_task.md)                   | Defines reusable build macros with parameters.                                                      |
| [Mail](tasks/mail_task.md)                           | Sends SMTP emails with attachments and MIME support.                                                |
| [MakeURL](tasks/makeurl_task.md)                     | Converts file paths to `file:` URLs for Class-Path entries.                                         |
| [Manifest](tasks/manifest_task.md)                   | Creates or updates JAR manifest files.                                                              |
| [ManifestClassPath](tasks/manifestclasspath_task.md) | Computes manifest `Class-Path` entries from a Path.                                                 |
| [Mkdir](tasks/mkdir_task.md)                         | Creates directories, including parents.                                                             |
| [Move](tasks/move_task.md)                           | Relocates files and directories with filtering and mapping.                                         |
| [Native2Ascii](tasks/native2ascii_task.md)           | Converts non-ASCII text to Unicode escapes in files.                                                |
| [NetRexxC](tasks/netrexxc_task.md)                   | Compiles NetRexx sources to bytecode.                                                               |
| [Nice](tasks/nice_task.md)                           | Runs nested tasks with adjusted UNIX niceness.                                                      |
| [Parallel](tasks/parallel_task.md)                   | Executes multiple tasks concurrently in threads.                                                    |
| [Patch](tasks/patch_task.md)                         | Applies unified diff patches to files or collections.                                               |
| [PathConvert](tasks/pathconvert_task.md)             | Converts paths or resources to delimited strings.                                                   |
| [PreSetDef](tasks/presetdef_task.md)                 | Pre-configures tasks/types into new custom macros.                                                  |
| [ProjectHelper](tasks/projecthelper_task.md)         | Imports build files via a specified ProjectHelper.                                                  |
| [Property](tasks/property_task.md)                   | Sets project properties from values, files, or resources.                                           |
| [PropertyFile](tasks/propertyfile_task.md)           | Updates or creates `.properties` files programmatically.                                            |
| [PropertyHelper](tasks/propertyhelper_task.md)       | Installs or extends the Ant PropertyHelper.                                                         |
| [Record](tasks/record_task.md)                       | Captures build output to log files dynamically.                                                     |
| [Replace](tasks/replace_task.md)                     | Performs literal string replacements with optional backups.                                         |
| [ReplaceRegExp](tasks/replaceregexp_task.md)         | Performs regex-based replacements using Java regex.                                                 |
| [ResourceCount](tasks/resourcecount_task.md)         | Counts resources, setting properties or conditions.                                                 |
| [Retry](tasks/retry_task.md)                         | Retries nested tasks until success or limit.                                                        |
| [RExec](tasks/rexec_task.md)                         | Executes commands on remote hosts via the Rexec protocol.                                           |
| [Rpm](tasks/rpm_task.md)                             | Builds RPM packages using `rpmbuild`.                                                               |
| [Scp](tasks/scp_task.md)                             | Transfers files over SSH using SCP.                                                                 |
| [Script](tasks/script_task.md)                       | Runs embedded scripts through the BSF/Java scripting API.                                           |
| [ScriptDef](tasks/scriptdef_task.md)                 | Defines tasks/types via inline scripting.                                                           |
| [Sequential](tasks/sequential_task.md)               | Groups multiple tasks to run sequentially.                                                          |
| [ServerDeploy](tasks/serverdeploy_task.md)           | Deploys Java EE artifacts via JSR-88.                                                               |
| [SetPermissions](tasks/setpermissions_task.md)       | Sets UNIX file permissions (chmod).                                                                 |
| [SetProxy](tasks/setproxy_task.md)                   | Configures HTTP/HTTPS proxies for network tasks.                                                    |
| [SignJar](tasks/signjar_task.md)                     | Signs JARs with `jarsigner`.                                                                        |
| [Sleep](tasks/sleep_task.md)                         | Pauses execution for a specified time.                                                              |
| [Sound](tasks/sound_task.md)                         | Plays system beep or sound files.                                                                   |
| [SourceOffSite](tasks/sourceoffsite_task.md)         | Excludes files from source distributions.                                                           |
| [Splash](tasks/splash_task.md)                       | Displays a GUI splash screen during the build.                                                      |
| [Sql](tasks/sql_task.md)                             | Executes SQL statements via JDBC.                                                                   |
| [Sshexec](tasks/sshexec_task.md)                     | Executes commands on remote hosts via SSH.                                                          |
| [Sshsession](tasks/sshsession_task.md)               | Manages SSH sessions for multiple operations.                                                       |
| [Subant](tasks/subant_task.md)                       | Runs Ant builds in subdirectories for multi-project builds.                                         |
| [Symlink](tasks/symlink_task.md)                     | Creates filesystem symbolic links.                                                                  |
| [Sync](tasks/sync_task.md)                           | Synchronizes directories, copying only changed files.                                               |
| [TStamp](tasks/tstamp_task.md)                       | Generates timestamp properties for builds.                                                          |
| [Tar](tasks/tar_task.md)                             | Creates or extracts TAR archives.                                                                   |
| [Taskdef](tasks/taskdef_task.md)                     | Defines new tasks or types by class name and classpath.                                             |
| [Telnet](tasks/telnet_task.md)                       | Sends commands over Telnet to remote hosts.                                                         |
| [Tempfile](tasks/tempfile_task.md)                   | Creates temporary files or directories.                                                             |
| [Touch](tasks/touch_task.md)                         | Updates file modification timestamps.                                                               |
| [Translate](tasks/translate_task.md)                 | Translates characters in files or resources.                                                        |
| [Truncate](tasks/truncate_task.md)                   | Truncates or extends files to a given length.                                                       |
| [Typedef](tasks/typedef_task.md)                     | Defines new data types by class name and classpath.                                                 |
| [UnXZ](tasks/unxz_task.md)                           | Decompresses `.xz` files.                                                                           |
| [Unjar](tasks/unjar_task.md)                         | Extracts JAR or ZIP archives.                                                                       |
| [Untar](tasks/untar_task.md)                         | Extracts TAR archives.                                                                              |
| [Unwar](tasks/unwar_task.md)                         | Extracts WAR files.                                                                                 |
| [Unzip](tasks/unzip_task.md)                         | Extracts ZIP archives.                                                                              |
| [UpToDate](tasks/uptodate_task.md)                   | Checks whether files are up-to-date based on timestamps.                                            |
| [Uso](tasks/uso_task.md)                             | Invokes another Uso Groovy build script as a sub-build.                                             |
| [VerifyJar](tasks/verifyjar_task.md)                 | Verifies JAR contents against manifest checksums.                                                   |
| [Waitfor](tasks/waitfor_task.md)                     | Pauses until a specified condition is met.                                                          |
| [War](tasks/war_task.md)                             | Builds WAR archives for Java web applications.                                                      |
| [WhichResource](tasks/whichresource_task.md)         | Finds and logs resource locations on the classpath.                                                 |
| [XSLT/Style](tasks/xslt_style_task.md)               | Applies XSLT transformations to XML files.                                                          |
| [XZ](tasks/xz_task.md)                               | Compresses files into the `.xz` format.                                                             |
| [XmlProperty](tasks/xmlproperty_task.md)             | Extracts XML data via XPath into properties.                                                        |
| [XmlValidate](tasks/xmlvalidate_task.md)             | Validates XML well-formedness and DTDs.                                                             |
| [Zip](tasks/zip_task.md)                             | Creates or extracts ZIP archives.                                                                   |