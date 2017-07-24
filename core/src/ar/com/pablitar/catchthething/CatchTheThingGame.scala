package ar.com.pablitar.catchthething

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.libgdx.commons.engines.Engines
import com.badlogic.gdx.Gdx
import ar.com.pablitar.libgdx.commons.entities.{Entities => CommonEntities}
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._

class CatchTheThingGame extends ApplicationAdapter {
  
  lazy val engine = Engines.commonEngine()
  
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
    catcher
  }
}

//object Resources {
//  lazy val 
//}