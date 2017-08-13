package ar.com.pablitar.catchthething

import ar.com.pablitar.libgdx.commons.state.State
import ar.com.pablitar.libgdx.commons.state.TimedStateTransition

object CatcherStates {
  case object Idle extends State
  case object Catching extends State {
    override val timedTransition = Some(TimedStateTransition(Resources.macetaAnimation.getAnimationDuration, () => Idle))
  }
}