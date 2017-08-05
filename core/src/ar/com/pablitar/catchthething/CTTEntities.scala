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
import ar.com.pablitar.catchthething.components.CTTFamilies
import com.badlogic.gdx.math.Circle
import ar.com.pablitar.libgdx.commons.components.BindingsComponent
import ar.com.pablitar.libgdx.commons.components.TransformComponent
import ar.com.pablitar.libgdx.commons.components.Bindings
import ar.com.pablitar.catchthething.components.NonCaughtSeedComponent
import ar.com.pablitar.catchthething.components.CatcherTop
import ar.com.pablitar.libgdx.commons.math.Segment2

object CTTEntities {
  val CATCHER_INITIAL_POSITION: Vector2 = (Configuration.VIEWPORT_WIDTH / 2.0f, Configuration.VIEWPORT_HEIGHT * 0.15f)
  val CATCHER_TOP_WIDTH = 200
  val CATCHER_TOP_HEIGHT = 200
  def catcher: Entity = {
    Entities.movingSprite(Resources.macetaSprite, CATCHER_INITIAL_POSITION)
      .add(new CatcherComponent(Segment2((-90, 88), (-40, -60)), Segment2((40, -60), (90, 88)), Segment2((-40, -60), (40, -60))))
  }

  def catcherTop(catcher: Entity): Entity = {
    Entities.collider(
      CATCHER_INITIAL_POSITION,
      new Rectangle(0, 0,
        CATCHER_TOP_WIDTH - 50, 50),
      CTTFamilies.nonCaughtSeeds)
      .add(BindingsComponent(catcher, Bindings.transform(0, 70)))
      .add(new CatcherTop(catcher))
  }

  def catcherShadow(catcher: Entity): Entity = {
    Entities.simpleSprite(Resources.macetaShadowSprite, CATCHER_INITIAL_POSITION, 2)
      .add(BindingsComponent(catcher, Bindings.transform()))
  }
  
  val SEED_RADIUS = 20

  def seed(position: Vector2, initialVelocity: Vector2): Entity = {
    val seed = Entities.movingShapedSprite(Resources.seedSprite, position, new Circle(0, 0, SEED_RADIUS), 1, initialVelocity)
    seed.acceleration = new Vector2(0.0f, -800f)
    seed.add(new SeedComponent(SEED_RADIUS)).add(new NonCaughtSeedComponent)
  }

  def seedSpawner = {
    new Entity().add(SeedSpawnerComponent(0.5f, 3f))
  }
}