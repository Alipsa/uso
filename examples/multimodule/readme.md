# Multi-module example

This project shows an example of a multi module project. It has three sub projects, common, subA and subB where
subA and subB depend on common. The common module is a library that is used by both subA and subB.

- you reuse properties by defining a properties file in the root project and call that from each sub project
- The main build script is in the root directory. It executes the sub projects in the correct order
  - The main build script is not published to maven central. It is only used for local builds.
- The common project is a library that is used by both subA and subB. It has its own build script that is executed by the main build script. 
- The subA and subB projects are applications that depend on the common library. They have their own build scripts that are executed by the main build script but can be executed directly as well.

- There is a difference between project execution and artifact dependencies. Hence, common needs to be locally published so that subA and subB can use it as a dependency.