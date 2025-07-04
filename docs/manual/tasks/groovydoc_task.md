# The groovydoc Ant task
GroovyDoc is a tool responsible for generating documentation from your code. It acts like the Javadoc tool in the Java world but is capable of handling both groovy and java files. The groovydoc Ant task allows generating groovydocs from an Ant build.

## Required taskdef
Assuming all the groovy jars you need are in my.classpath (this will be groovy-VERSION.jar, groovy-ant-VERSION.jar, groovy-groovydoc-VERSION.jar plus any modules and transitive dependencies you might be using) you will need to declare this task at some point in the build.xml prior to the groovydoc task being invoked.

taskdef name: "groovydoc" classname: "org.codehaus.groovy.ant.Groovydoc" classpathref: "my.classpath"

## groovydoc Attributes
| Attribute	   | Description                                                                    | 	Required |
|--------------|--------------------------------------------------------------------------------|-----------|
| destdir      | Location to store the class files.                                             | Yes       |
| sourcepath   | The sourcepath to use.                                                         | No        | 
| packagenames | Comma separated list of package files (with terminating wildcard).             | No        | 
| use          | Create class and package usage pages.                                          | No        | 
| windowtitle  | Browser window title for the documentation (text).                             | No        |
| doctitle     | Include title for the package index(first) page (html-code).                   | No        | 
| header       | Include header text for each page (html-code).                                 | No        | 
| footer       | Include footer text for each page (html-code).                                 | No        | 
| overview     | Read overview documentation from HTML file.                                    | No        | 
| private      | Show all classes and members (i.e. including private ones) if set to ``true''. | No        |

## groovydoc Nested Elements
#### link
Create link to groovydoc/javadoc output at the given URL.

| Attribute | Description                              | Required |
|-----------|------------------------------------------|----------|
| packages  | Comma separated list of package prefixes | Yes      |
| href      | Base URL of external site                | Yes      | 

#### Example #1 - <groovydoc> Ant task
```groovy
taskdef name: "groovydoc", classname: "org.codehaus.groovy.ant.Groovydoc", classpathref: "path_to_groovy_all"

groovydoc(destdir: "${docsDirectory}/gapi",
  sourcepath: "${mainSourceDirectory}",
  packagenames: "**.*",
  use: "true",
  windowtitle: '${title}',
  doctitle: '${title}',
  header: '${title}',
  footer: '${docFooter}',
  overview: 'src/main/overview.html',
  private: "false") {

  link packages: "java.,org.xml.,javax.,org.xml.", href: "http://docs.oracle.com/javase/8/docs/api/"
  link packages: "org.apache.tools.ant.", href: "http://docs.groovy-lang.org/docs/ant/api/"
  link packages: "org.junit.,junit.framework.", href: "http://junit.org/junit4/javadoc/latest/"
  link packages: "groovy.,org.codehaus.groovy.", href: "http://docs.groovy-lang.org/latest/html/api/"
  link packages: "org.codehaus.gmaven.", href: "http://groovy.github.io/gmaven/apidocs/"
}
```

#### Example #2 - Executing groovydoc from Groovy
```groovy
def ant = new AntBuilder()
ant.taskdef(name: "groovydoc", classname: "org.codehaus.groovy.ant.Groovydoc")
ant.groovydoc(
  destdir      : "${docsDirectory}/gapi",
  sourcepath   : "${mainSourceDirectory}",
  packagenames : "**.*",
  use          : "true",
  windowtitle  : "${title}",
  doctitle     : "${title}",
  header       : "${title}",
  footer       : "${docFooter}",
  overview     : "src/main/overview.html",
  private      : "false") {
  
  link(packages:"java.,org.xml.,javax.,org.xml.",href:"http://docs.oracle.com/javase/8/docs/api/")
  link(packages:"groovy.,org.codehaus.groovy.",  href:"http://docs.groovy-lang.org/latest/html/api/")
  link(packages:"org.apache.tools.ant.",         href:"http://docs.groovy-lang.org/docs/ant/api/")
  link(packages:"org.junit.,junit.framework.",   href:"http://junit.org/junit4/javadoc/latest/")
  link(packages:"org.codehaus.gmaven.",          href:"http://groovy.github.io/gmaven/apidocs/")
}
```

## Custom templates
The groovydoc Ant task supports custom templates, but it requires two steps:

1. A custom groovydoc class
2. A new groovydoc task definition

### Custom Groovydoc class
The first step requires you to extend the Groovydoc class, like in the following example:
```groovy
package org.codehaus.groovy.tools.groovydoc

import org.codehaus.groovy.ant.Groovydoc

/**
 * Overrides GroovyDoc's default class template - for testing purpose only. 
 */
class CustomGroovyDoc extends Groovydoc {

  @Override
  protected String[] getClassTemplates() {
    ["org/codehaus/groovy/tools/groovydoc/testfiles/classDocName.html"] as String[]
  }
}
```
You can override the following methods:
- getClassTemplates for class-level templates
- getPackageTemplates for package-level templates
- getDocTemplates for top-level templates

You can find the list of default templates in the `org.codehaus.groovy.tools.groovydoc.gstringTemplates.GroovyDocTemplateInfo` class.

#### Using the custom groovydoc task
Once you’ve written the class, using it is just a matter of redefining the groovydoc task:
```groovy
taskdef name: "groovydoc", classname: "org.codehaus.groovy.ant.CustomGroovyDoc", classpathref: "path_to_groovy_all"
```

Please note that template customization is provided as is. APIs are subject to change, so you must consider this as a fragile feature.

