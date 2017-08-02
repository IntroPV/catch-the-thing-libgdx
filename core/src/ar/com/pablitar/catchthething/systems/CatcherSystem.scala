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
import ar.com.pablitar.catchthething.Configuration


class CatcherSystem extends IteratingSystem(Family.all(
        classOf[CatcherComponent], classOf[TransformComponent], classOf[VelocityComponent]).get()
        ) {   
  val speed = 600
  def processEntity(catcher: Entity, delta: Float): Unit = {
    processCatcherInput(catcher)
    restrictCatcherMovement(catcher)
    updateOtherEntities(catcher)
  }
  
  def processCatcherInput(catcher: Entity) = {
    val catcherVelocity :Vector2= (0,0)
    
    if(Gdx.input.isKeyPressed(Keys.LEFT)) {
      catcherVelocity.x-=speed
    }
    
    if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
      catcherVelocity.x+=speed
    }
    
    catcher.velocity = catcherVelocity
  }

  def restrictCatcherMovement(catcher: Entity) = {
    if(catcher.position.x <= 0 && catcher.velocity.x < 0) {
      catcher.position.x = 0
      catcher.velocity.x = 0
    }
    
    if(catcher.position.x >= Configuration.VIEWPORT_WIDTH && catcher.velocity.x > 0) {
      catcher.position.x = Configuration.VIEWPORT_WIDTH
      catcher.velocity.x = 0
    }
  }

  def updateOtherEntities(catcher: Entity) = {
    catcher.shadow.position = catcher.position
    catcher.top.position = catcher.position
  }
}
