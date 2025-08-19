#!/usr/bin/env groovy
import groovy.xml.XmlSlurper
import java.net.URL

if (args.length != 1) {
  println "Usage: checkGrabs.groovy <groovy-script-file>"
  System.exit(1)
}

// shorthand: @Grab('group:artifact:version')
def grabShorthand = /@Grab\(['"]([^:]+):([^:]+):([^'"]+)['"]\)/

// list form: @Grab(group='x', module='y', version='z')
def grabListForm = /@Grab\s*\(\s*group\s*=\s*['"]([^'"]+)['"]\s*,\s*module\s*=\s*['"]([^'"]+)['"]\s*,\s*version\s*=\s*['"]([^'"]+)['"]\s*\)/

def file = new File(args[0])
if (!file.exists()) {
  println "File not found: ${args[0]}"
  System.exit(1)
}

// --- helpers ---
def isStable = { v ->
  !(v =~ /(?i).*(alpha|beta|rc|cr|m\d+|milestone|preview|ea).*/)
}

def findStableVersion = { xml ->
  def allVersions = xml.versioning.versions.version*.text()
  if (!allVersions) return null
  def stable = allVersions.findAll { v -> isStable(v) }
  return stable ? stable.last() : allVersions.last()
}

// --- results collectors ---
def upToDate = []
def updates = []
def notAvailable = []
def errors = []

def checkAndRecord = { group, artifact, version ->
  def groupPath = group.replace('.', '/')
  def metadataUrl = "https://repo1.maven.org/maven2/${groupPath}/${artifact}/maven-metadata.xml"

  try (def stream = new URL(metadataUrl).openStream()) {
    def xml = new XmlSlurper().parse(stream)
    def latest = xml.versioning.latest?.text()
    def release = xml.versioning.release?.text()

    def suggested = latest ?: release
    if (!suggested || !isStable(suggested)) {
      suggested = findStableVersion(xml) ?: suggested
    }

    if (suggested == null) {
      notAvailable << "@Grab('${group}:${artifact}:${version}') -> No version info found"
    } else if (suggested == version) {
      upToDate << "@Grab('${group}:${artifact}:${version}')"
    } else {
      updates << "@Grab('${group}:${artifact}:${version}') -> ${suggested}"
    }
  } catch (FileNotFoundException fnfe) {
    notAvailable << "@Grab('${group}:${artifact}:${version}') -> Not available in central"
  } catch (Exception e) {
    errors << "@Grab('${group}:${artifact}:${version}') -> ERROR (${e.message})"
  }
}

// --- parse file ---
boolean inBlockComment = false
file.eachLine { line ->
  def trimmed = line.trim()

  // handle block comments
  if (trimmed.startsWith("/*")) {
    inBlockComment = true
  }
  if (inBlockComment) {
    if (trimmed.endsWith("*/")) {
      inBlockComment = false
    }
    return // skip this line
  }

  // skip single-line comments
  if (trimmed.startsWith("//")) return

  // skip inline-commented @Grab
  def commentIndex = trimmed.indexOf("//")
  def effective = (commentIndex >= 0) ? trimmed[0..<commentIndex] : trimmed

  (effective =~ grabShorthand).each { m ->
    checkAndRecord(m[1], m[2], m[3])
  }
  (effective =~ grabListForm).each { m ->
    checkAndRecord(m[1], m[2], m[3])
  }
}

// --- print results ---
if (upToDate) {
  println "=== Already up to date ==="
  upToDate.each { println it }
  println()
}
if (updates) {
  println "=== Updates available ==="
  updates.each { println it }
  println()
}
if (notAvailable) {
  println "=== Not available in central ==="
  notAvailable.each { println it }
  println()
}
if (errors) {
  println "=== Errors ==="
  errors.each { println it }
  println()
}

if (updates) {
  println "⚠️ There are updates available!"
} else {
  println "✅ All @Grab dependencies are up to date!"
}
if (errors) {
  println "❌ The were errors trying to get the latest dependency versions"
}