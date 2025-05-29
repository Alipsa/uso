package multimodule.common

interface Greeting {

  String greet(String name)

  default void info() {
    println org.apache.commons.lang3.ClassNodeUtils.getPackageName(this.class)
  }
}