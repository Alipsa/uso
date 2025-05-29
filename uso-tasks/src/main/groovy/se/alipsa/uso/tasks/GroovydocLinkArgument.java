package se.alipsa.uso.tasks;

import org.codehaus.groovy.tools.groovydoc.LinkArgument;

public class GroovydocLinkArgument extends LinkArgument {
  private String module;

  /**
   * Optional name of the module the linked package belongs to.
   * Used to support modular Javadoc links, e.g. java.base/java/lang/Object.html
   */
  public void setModule(String module) {
    this.module = module;
  }

  public String getModule() {
    return module;
  }

  @Override
  public String getHref() {
    String base = super.getHref();
    if (base == null || module == null || module.isEmpty()) return base;
    return base.endsWith("/") ? base + module : base + "/" + module;
  }
}
