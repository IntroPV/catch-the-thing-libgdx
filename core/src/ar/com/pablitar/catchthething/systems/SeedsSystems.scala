package ar.com.pablitar.catchthething.systems

import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.ashley.core.Family
import ar.com.pablitar.catchthething.components.SeedSpawnerComponent
import ar.com.pablitar.libgdx.commons.extensions.FamilyImplicits._
import ar.com.pablitar.catchthething.components.Extensions._
import ar.com.pablitar.libgdx.commons.components.Extensions._
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.catchthething.CTTEntities
import com.badlogic.gdx.math.MathUtils
import ar.com.pablitar.catchthething.Configuration
import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.family.Families
import ar.com.pablitar.catchthething.components.SeedComponent

class SeedSpawnerSystem extends IteratingSystem(classOf[SeedSpawnerComponent]) {
  def processEntity(seedSpawner: Entity, delta: Float): Unit = {
    seedSpawner.timeUntilNextSpawn = seedSpawner.timeUntilNextSpawn - delta
    
    if(seedSpawner.timeUntilNextSpawn <= 0) {
      println("spawning seed")
      spawnSeed()
      seedSpawner.timeUntilNextSpawn = MathUtils.random(seedSpawner.minNext, seedSpawner.maxNext)
    }
  }
  
  val minSeedSpeed = 400f
  val maxSeedSpeed = 900f
  
  def newSeedParams() = {
    val leftSpawn = MathUtils.randomBoolean()
    val speed = MathUtils.random(minSeedSpeed, maxSeedSpeed)
    val xPosition = if(leftSpawn) 0 else Configuration.VIEWPORT_WIDTH
    val xVelocity = if(leftSpawn) speed else -speed
    (new Vector2(xPosition, Configuration.VIEWPORT_HEIGHT), new Vector2(xVelocity, 0)) 
  }

  def spawnSeed() = {
    val seedParameters = newSeedParams()
    this.getEngine.addEntity(CTTEntities.seed(seedParameters._1, seedParameters._2))
  }
}

class SeedsSystem extends IteratingSystem(Families.kinematic.all(classOf[SeedComponent])) {
  def processEntity(seedSpawner: Entity, delta: Float): Unit = {
    seedSpawner.rotation = seedSpawner.velocity.angle() + 90
  }
}