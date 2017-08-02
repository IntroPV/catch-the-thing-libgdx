package ar.com.pablitar.catchthething.components

import ar.com.pablitar.libgdx.commons.family.Families

object CatcherFamilies {
  def seeds = {
    Families.kinematicCollidable(classOf[SeedComponent]).get
  }
}