package ar.com.pablitar.catchthething

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2

import ar.com.pablitar.catchthething.components.CatcherComponent
import ar.com.pablitar.catchthething.components.SeedComponent
import ar.com.pablitar.catchthething.components.SeedSpawnerComponent
import ar.com.pablitar.libgdx.commons.entities.Entities
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions.fromTupleToVector2
import ar.com.pablitar.libgdx.commons.components.Extensions._

object CTTEntities {
  val CATCHER_INITIAL_POSITION: Vector2 = (Configuration.VIEWPORT_WIDTH / 2.0f, Configuration.VIEWPORT_HEIGHT * 0.15f)
  def catcher: Entity = {
    val catcher = Entities.movingAnimated(Resources.macetaAnimation, CATCHER_INITIAL_POSITION)
    catcher.add(new CatcherComponent)
    catcher
  }

  def catcherShadow: Entity = {
    val catcherShadow = Entities.movingAnimated(Resources.macetaShadowAnimation, CATCHER_INITIAL_POSITION, 2)
    catcherShadow.add(new CatcherComponent)
    catcherShadow
  }

  def seed(position: Vector2, initialVelocity: Vector2): Entity = {
    val seed = Entities.movingSprite(Resources.seedSprite, position, 1, initialVelocity)
    seed.acceleration = new Vector2(0.0f, -800f) 
    seed.add(new SeedComponent)
  }
  
  def seedSpawner = {
    val spawner = new Entity
    spawner.add(SeedSpawnerComponent(0.5f, 3f))
    spawner
  }
}