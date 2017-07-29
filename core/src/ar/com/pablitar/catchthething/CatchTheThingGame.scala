package ar.com.pablitar.catchthething

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.libgdx.commons.engines.Engines
import com.badlogic.gdx.Gdx
import ar.com.pablitar.libgdx.commons.entities.{Entities => CommonEntities}
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.core.Component
import ar.com.pablitar.libgdx.commons.components.VelocityComponent
import ar.com.pablitar.libgdx.commons.components.TransformComponent
import ar.com.pablitar.libgdx.commons.components.Extensions._
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.math.Vector2

class CatchTheThingGame extends ApplicationAdapter {
  
  lazy val engine = {
    val commonEngine = Engines.commonEngine()
    commonEngine.addSystem(new CatcherSystem)
    commonEngine
  }
  
  override def create() = {
    engine.addEntity(Entities.catcher)
    engine.addEntity(Entities.catcherShadow)
  }
  
  override def render() = {
    engine.update(Gdx.graphics.getDeltaTime())
  }
}

object Entities {
  val CATCHER_INITIAL_POSITION: Vector2 = (Configuration.VIEWPORT_WIDTH/2.0f, Configuration.VIEWPORT_HEIGHT * 0.15f)
  def catcher: Entity = {
    val catcher = CommonEntities.movingAnimated(Resources.macetaAnimation, CATCHER_INITIAL_POSITION)
    catcher.add(new CatcherComponent)
    catcher
  }
  
  def catcherShadow: Entity = {
    val catcherShadow = CommonEntities.movingAnimated(Resources.macetaShadowAnimation, CATCHER_INITIAL_POSITION, 2) 
    catcherShadow.add(new CatcherComponent)
    catcherShadow
  }
}

class CatcherComponent extends Component

class CatcherSystem extends IteratingSystem(Family.all(
      classOf[CatcherComponent], classOf[TransformComponent], classOf[VelocityComponent]).get()
      ) {  
  val speed = 600
  def processEntity(catcher: Entity, delta: Float): Unit = {
    processCatcherInput(catcher)
    restrictCatcherMovement(catcher)
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
}

//object Resources {
//  lazy val 
//}