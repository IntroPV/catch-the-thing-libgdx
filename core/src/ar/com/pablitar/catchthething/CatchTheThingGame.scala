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
  }
  
  override def render() = {
    engine.update(Gdx.graphics.getDeltaTime())
  }
}

object Entities {
  def catcher: Entity = {
    val catcher = CommonEntities.movingSprite(Resources.macetaSprite, 
        (Configuration.VIEWPORT_WIDTH/2.0f, Configuration.VIEWPORT_HEIGHT * 0.1f))
    catcher.add(new CatcherComponent)
    catcher
  }
}

class CatcherComponent extends Component

class CatcherSystem extends IteratingSystem(Family.all(
    classOf[CatcherComponent], classOf[TransformComponent], classOf[VelocityComponent]).get()
    ) { 
  val speed = 600
  def processEntity(catcher: Entity, delta: Float): Unit = {
    val catcherVelocity :Vector2= (0,0)
    if(Gdx.input.isKeyPressed(Keys.LEFT)) {
      catcherVelocity.x-=speed
    }
    
    if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
      catcherVelocity.x+=speed
    }
    
    catcher.velocity = catcherVelocity
  }
}

//object Resources {
//  lazy val 
//}