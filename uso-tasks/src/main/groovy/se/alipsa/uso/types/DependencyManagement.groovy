package se.alipsa.uso.types

import org.apache.maven.resolver.internal.ant.types.Dependencies
import org.apache.tools.ant.types.DataType

class DependencyManagement extends DataType {
  Dependencies dependencies

  Dependencies getDependencies() {
    return dependencies
  }

  void addDependencies(Dependencies dependencies) {
    if (this.dependencies != null) {
      throw new IllegalStateException("You must not specify multiple <dependencies> elements");
    }
    this.dependencies = dependencies
  }
}
