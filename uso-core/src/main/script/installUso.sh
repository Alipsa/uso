wget https://raw.githubusercontent.com/Alipsa/uso/refs/heads/main/uso-core/src/main/script/uso
chmod +x uso
wget https://raw.githubusercontent.com/Alipsa/uso/refs/heads/main/uso-core/src/main/script/usas
chmod +x usas
if [[ ! -f build.groovy ]]; then
  echo "project.with {
  groupId = ''
  artifactId = ''
  version = ''
  defaultTarget = ''
  // target definitions goes here
}" > build.groovy
fi