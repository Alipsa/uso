#!/usr/bin/env bash
##############################################################
# Utility to download all dependencies to local maven cache.
# useful if Grape has problems fetching artifacts from central but maven works.
# Grape will then use the local maven cache instead.
##############################################################

set -e

# fetchArtifact groupId:artifactId:version[:packaging[:classifier]]
fetchArtifact() {
  echo "Downloading $1 to local maven cache..."
  mvn -q dependency:get -Dartifact=$1
}
fetchArtifact 'org.apache.maven.resolver:maven-resolver-ant-tasks:1.6.0'
fetchArtifact 'org.codehaus.plexus:plexus-xml:4.1.0'
fetchArtifact 'org.slf4j:slf4j-simple:2.0.17'
fetchArtifact 'se.alipsa.matrix:matrix-csv:2.1.0'
fetchArtifact 'se.alipsa.matrix:matrix-core:3.2.0'
fetchArtifact 'se.alipsa.matrix:matrix-bom:2.2.0:pom'
fetchArtifact 'com.squareup.okhttp3:mockwebserver:4.12.0'
