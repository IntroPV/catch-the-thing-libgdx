package ar.com.pablitar.catchthething.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.math.Vector2

import ar.com.pablitar.libgdx.commons.components.Extensions.EntityWithTransform
import ar.com.pablitar.libgdx.commons.components.Extensions.SpeedyEntity
import ar.com.pablitar.libgdx.commons.components.TransformComponent
import ar.com.pablitar.libgdx.commons.components.VelocityComponent
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions.fromTupleToVector2
import ar.com.pablitar.catchthething.components.CatcherComponent
import ar.com.pablitar.catchthething.components.Extensions._
import ar.com.pablitar.libgdx.commons.components.Extensions._
import ar.com.pablitar.libgdx.commons.extensions.FamilyImplicits._
import ar.com.pablitar.catchthething.Configuration
import ar.com.pablitar.catchthething.components.NonCaughtSeedComponent
import ar.com.pablitar.catchthething.components.CaughtSeedComponent
import ar.com.pablitar.catchthething.components.CTTFamilies
import com.badlogic.ashley.core.EntitySystem
import ar.com.pablitar.catchthething.components.CatcherTop
import ar.com.pablitar.libgdx.commons.rendering.Renderers
import ar.com.pablitar.catchthething.CatcherStates

class CatcherSystem extends EntitySystem {
  val speed = 600
  lazy val catchers = this.getEngine.getEntitiesFor(CTTFamilies.catcher)
  lazy val catcherTops = this.getEngine.getEntitiesFor(classOf[CatcherTop])
  
  def catcher = catchers.first()
  def catcherTop = catcherTops.first()
  
  override def update(delta: Float) = {
    processCatcherInput(catcher)
    restrictCatcherMovement(catcher)
    processCatcherCollisions(catcherTop)
  }

  def processCatcherInput(catcher: Entity) = {
    val catcherVelocity: Vector2 = (0, 0)

    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      catcherVelocity.x -= speed
    }

    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      catcherVelocity.x += speed
    }

    catcher.velocity = catcherVelocity
  }

  def restrictCatcherMovement(catcher: Entity) = {
    if (catcher.position.x <= 0 && catcher.velocity.x < 0) {
      catcher.position.x = 0
      catcher.velocity.x = 0
    }

    if (catcher.position.x >= Configuration.VIEWPORT_WIDTH && catcher.velocity.x > 0) {
      catcher.position.x = Configuration.VIEWPORT_WIDTH
      catcher.velocity.x = 0
    }
  }

  def processCatcherCollisions(catcherTop: Entity) = {
    catcherTop.collisionEvents.foreach(ev => {
      catcherTop.catcher.score = catcherTop.catcher.score + 1
      ev.collided.remove(classOf[NonCaughtSeedComponent])
      ev.collided.add(new CaughtSeedComponent(catcherTop.catcher))
      catcherTop.catcher.changeStateTo(CatcherStates.Catching)
    })
  }
}
