project.with {
  name = 'common'
  artifactId = 'multimodule-common'
  version = '1.0.0'
  defaultTarget = 'init'
  property(file: '../multimodule.properties')
  echo 'Multimodule common, before calling base, groupId: ${groupId}'

  dependencies(id: 'compile') {
    dependency(coords: 'org.apache.commons:commons-lang3:3.17.0')

    dependency(coords: 'org.junit.jupiter:junit-jupiter-engine:5.12.2:test')
    dependency(coords: 'org.junit.platform:junit-platform-launcher:1.12.2:test')
  }

  target('init') {
    echo "Initializing project common..."
    layout(type: 'maven', language: 'groovy', logLevel: 3)
    def pomFile = new File($('distDir'), "${artifactId}-${version}.pom")
    echo "Creating and registering the pom file ${pomFile.canonicalPath}"
    createPom(pomTarget: pomFile, dependenciesRef: 'compile',
        name: '${artifactId}', description: "common library for multimodule example") {
      licenses {
        license (
            name:"MIT",
            url:"https://opensource.org/license/mit"
        )
      }
    }
    resolve {
      path(refId: 'compilePath', classpath: 'compile')
      path(refId: 'testPath', classpath: 'test')
    }
  }

  target(name: 'clean') {
    delete dir: '${buildDir}', quiet: true
  }

  target(name: 'compile', depends: 'init') {
    echo "Compiling main groovy classes"
    int level = setOutputLevel(1)
    groovyc(
        srcdir: '${mainSrcDir}',
        destdir: '${mainClassesDir}',
        classpathref: 'compilePath',
        targetBytecode: '${classVersion}'
    )
    setOutputLevel(level)
  }

  target(name: 'test', depends: 'compile') {
    echo "Compiling test groovy classes"

    int level = setOutputLevel(1)
    groovyc(
        srcdir: '${testSrcDir}',
        destdir: '${testClassesDir}',
        classpathref: 'testClassPath',
        targetBytecode: '${classVersion}'
    )
    setOutputLevel(level)

    junitlauncher(printSummary: true, haltOnFailure: true) {
      classpath {
        pathelement location: '${mainCLassesDir}'
        pathelement location: '${testClassesDir}'
        path refid: "testPath"
      }
      testclasses(outputdir: '${testResultDir}') {
        fileset dir: '${testClassesDir}'
        listener type: "legacy-xml", sendSysOut: true, sendSysErr: true
        //listener type: "legacy-plain", sendSysOut: true, sendSysErr: true
      }
    }
    junitreport(todir: '${testReportDir}') {
      fileset dir: '${testResultDir}'
      report format: 'frames', todir: '${testReportDir}'
    }
  }

  target(name: 'jar', depends: 'compile') {
    echo "Creating jar file"
    jarFile = new File($('distDir'), "${artifactId}-${version}.jar")
    jar(destfile: jarFile) {
      fileset dir: '${mainClassesDir}'
    }
    artifact(file:jarFile, type:"jar", id:"jar")
  }

  target(name: 'sourceJar') {
    echo "Creating sources jar file"
    srcJarFile = new File($('distDir'), "${artifactId}-${version}-sources.jar")
    jar(destfile: srcJarFile) {
      fileset dir: '${mainSrcDir}'
    }
    artifact(file: srcJarFile, type: "jar", classifier: 'sources', id: "sourceJar")
  }

  target(name: 'groovydoc', depends: 'init') {
    echo "Creating groovydoc"
    taskdef(name: "groovydoc", classname: "org.codehaus.groovy.ant.Groovydoc", classpathref: 'compilePath')
    groovydoc(destdir: '${mainDocDir}', sourcepath: '${mainSrcDir}') {
      link(packages:"java.,org.xml.,javax.,org.xml.", href: "https://docs.oracle.com/en/java/javase/21/docs/api/")
      link(packages:"groovy.,org.codehaus.groovy.", href:"http://docs.groovy-lang.org/latest/html/api/")
      link(packages:"org.apache.tools.ant.", href:"http://docs.groovy-lang.org/docs/ant/api/")
      link(packages:"org.junit.,junit.framework.", href:"https://junit.org/junit5/docs/current/api/")
    }
  }

  target(name: 'groovydocJar', depends: 'groovydoc') {
    echo "Creating groovydoc jar file"
    groovydocJarFile = new File($('distDir'), "${artifactId}-${version}-javadoc.jar")
    jar(destfile: groovydocJarFile) {
      fileset dir: '${mainDocDir}'
    }
    artifact(file: groovydocJarFile, type: "jar", classifier: 'javadocs', id: "javadocJar")
  }

  target(name: 'publishLocal', depends: 'jar, sourceJar, groovydocJar') {
    echo "Publishing to local maven repository"
    artifacts(id: 'localArtifacts') {
      artifact refid: 'jar'
      artifact refid: 'sourceJar'
      artifact refid: 'javadocJar'
    }
    install(artifactsref:"localArtifacts")
  }

}
