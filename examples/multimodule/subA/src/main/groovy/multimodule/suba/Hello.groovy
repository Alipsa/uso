package multimodule.suba

import multimodule.common.Greeting

class Hello implements Greeting {

  String greet(String name) {
    return "Hello $name from suba"
  }
}
