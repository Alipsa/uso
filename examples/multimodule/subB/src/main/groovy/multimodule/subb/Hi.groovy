package multimodule.subb

import multimodule.common.Greeting

class Hi implements Greeting {

  String greet(String name) {
    return "Hi $name from subb"
  }
}
