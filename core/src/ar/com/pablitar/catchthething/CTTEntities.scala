package ar.com.pablitar.catchthething

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2

import ar.com.pablitar.catchthething.components.CatcherComponent
import ar.com.pablitar.catchthething.components.SeedComponent
import ar.com.pablitar.catchthething.components.SeedSpawnerComponent
import ar.com.pablitar.libgdx.commons.entities.Entities
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions.fromTupleToVector2
import ar.com.pablitar.libgdx.commons.components.Extensions._
import com.badlogic.gdx.math.Rectangle
import com.badlogic.ashley.core.Family
import ar.com.pablitar.catchthething.components.CatcherFamilies
import com.badlogic.gdx.math.Circle
import ar.com.pablitar.libgdx.commons.components.BindingsComponent
import ar.com.pablitar.libgdx.commons.components.TransformComponent
import ar.com.pablitar.libgdx.commons.components.Bindings

object CTTEntities {
  val CATCHER_INITIAL_POSITION: Vector2 = (Configuration.VIEWPORT_WIDTH / 2.0f, Configuration.VIEWPORT_HEIGHT * 0.15f)
  val CATCHER_TOP_WIDTH = 200
  val CATCHER_TOP_HEIGHT = 200
  def catcher: Entity = {
    val catcher = Entities.movingAnimated(Resources.macetaAnimation, CATCHER_INITIAL_POSITION)
    catcher.add(new CatcherComponent())
    catcher
  }

  def catcherTop(catcher: Entity): Entity = {
    val catcherTop = Entities.collider(
      CATCHER_INITIAL_POSITION,
      new Rectangle(0, 0,
        CATCHER_TOP_WIDTH, CATCHER_TOP_HEIGHT),
      CatcherFamilies.seeds)
      catcherTop.add(BindingsComponent(catcher, Bindings.transform))
    catcherTop
  }

  def catcherShadow(catcher: Entity): Entity = {
    val catcherShadow = Entities.animated(Resources.macetaShadowAnimation, CATCHER_INITIAL_POSITION, 2)
    catcherShadow.add(BindingsComponent(catcher, Bindings.transform))
    catcherShadow
  }

  def seed(position: Vector2, initialVelocity: Vector2): Entity = {
    val seed = Entities.movingShapedSprite(Resources.seedSprite, position, new Circle(0, 0, 50), 1, initialVelocity)
    seed.acceleration = new Vector2(0.0f, -800f)
    seed.add(new SeedComponent)
  }

  def seedSpawner = {
    val spawner = new Entity
    spawner.add(SeedSpawnerComponent(0.5f, 3f))
    spawner
  }
}