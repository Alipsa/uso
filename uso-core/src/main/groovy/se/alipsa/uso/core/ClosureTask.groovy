package se.alipsa.uso.core

import org.apache.tools.ant.Target
import org.apache.tools.ant.Task

/**
 * A task that executes a Groovy closure. This class is used to create Ant tasks that can be
 * executed as part of an Ant build process.
 * The closure is executed with the task as its delegate, allowing for the use of Ant properties
 * and methods within the closure.
 */
class ClosureTask extends Task {
  Target owner
  Closure action

  ClosureTask() {
    super()
  }

  ClosureTask(Target owner, Closure action) {
    this()
    this.owner = owner
    this.project = owner.project
    this.action = action
  }

  @Override
  void execute() {
    action.delegate = this
    action.resolveStrategy = Closure.DELEGATE_FIRST
    action.call()
  }

  void setOwningTarget(Target target) {
    this.owner = target
    setProject(owner.project)
  }

  void setAction(Closure action) {
    this.action = action
  }
}