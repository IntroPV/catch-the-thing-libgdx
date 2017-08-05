package ar.com.pablitar.catchthething.components

import ar.com.pablitar.libgdx.commons.family.Families

object CTTFamilies {
  def seeds = {
    Families.kinematicCollidable(classOf[SeedComponent]).get
  }
  
  def nonCaughtSeeds = Families.kinematicCollidable(classOf[SeedComponent], classOf[NonCaughtSeedComponent]).get
  
  def catcher = Families.kinematic(classOf[CatcherComponent]).get
}