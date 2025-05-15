## ClearCase Tasks (Groovy DSL) - Overview

Apache Ant provides a suite of optional tasks for interacting with IBM Rational ClearCase, a software configuration management system. These tasks typically wrap the `cleartool` command-line interface. Therefore, `cleartool` must be installed and accessible in the system's PATH where the Ant script is executed.

When using Groovy with AntBuilder, these ClearCase tasks are invoked using the `ant.cc<command_name>(...)` syntax, where `<command_name>` corresponds to the specific ClearCase operation (e.g., `ant.cccheckin(...)`, `ant.cccheckout(...)`).

This section of the manual will cover the following individual ClearCase tasks, each detailed in its own document:

*   [CCCheckin](CCCheckin_Task_Groovy.md)
*   [CCCheckout](CCCheckout_Task_Groovy.md)
*   [CCUnCheckout](CCUnCheckout_Task_Groovy.md)
*   [CCUpdate](CCUpdate_Task_Groovy.md)
*   [CCMklbtype](CCMklbtype_Task_Groovy.md)
*   [CCMklabel](CCMklabel_Task_Groovy.md)
*   [CCRmtype](CCRmtype_Task_Groovy.md)
*   [CCLock](CCLock_Task_Groovy.md)
*   [CCUnlock](CCUnlock_Task_Grovvy.md)
*   [CCMkbl](CCMkbl_Task_Groovy.md)
*   [CCMkattr](CCMkattr_Task_Groovy.md)
*   [CCMkdir](CCMkdir_Task_Groovy.md)
*   [CCMkelem](CCMkelem_Task_Groovy.md)

Each specific task page will detail its parameters, provide Groovy AntBuilder examples, and highlight important considerations.

### General Considerations for ClearCase Tasks with AntBuilder:

*   **`cleartool` Dependency**: All ClearCase tasks require `cleartool` to be available on the command line.
*   **View Context**: Operations are typically performed within the context of a ClearCase view. The `viewpath` attribute is common for specifying the file or directory within a view.
*   **Error Handling**: The `failonerr` attribute (usually defaulting to `true`) controls whether an Ant build fails if the `cleartool` command returns an error.
*   **Comments**: Many tasks support `comment` or `commentfile` attributes for providing descriptions for ClearCase operations.

### Navigation

*   [Return to Introduction](00-Introduction_Groovy_Ant_Manual.md)
*   [View Full Task List (Appendix)](Appendix_A_Ant_XML_to_Groovy_Mapping.md)

