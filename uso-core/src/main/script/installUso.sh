#!/usr/bin/env bash
wget -q https://raw.githubusercontent.com/Alipsa/uso/refs/heads/main/uso-core/src/main/script/uso
chmod +x uso
if [[ ! -f build.groovy ]]; then
  echo "project.with {
  groupId = ''
  artifactId = ''
  version = ''
  defaultTarget = ''
  // target definitions goes here
}" > build.groovy
fi
echo "Installation of uso script completed! Edit build.groovy to add your project building details."