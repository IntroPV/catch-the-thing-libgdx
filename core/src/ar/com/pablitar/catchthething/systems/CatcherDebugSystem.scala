package ar.com.pablitar.catchthething.systems

import com.badlogic.ashley.core.EntitySystem
import ar.com.pablitar.libgdx.commons.rendering.Renderers
import ar.com.pablitar.libgdx.commons.family.Families
import ar.com.pablitar.catchthething.components.CTTFamilies

import ar.com.pablitar.catchthething.components.Extensions._
import ar.com.pablitar.libgdx.commons.components.Extensions._
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

class CatcherDebugSystem extends EntitySystem(21) {
  lazy val debugRenderers = new Renderers
  
  def catcher = this.getEngine.getEntitiesFor(CTTFamilies.catcher).first()

  override def update(delta: Float) = {
    debugRenderers.begin()
    
    debugRenderers.withShapes(ShapeType.Line) { shapesRenderer =>
      catcher.leftSegmentGlobal.drawOn(shapesRenderer)
      catcher.rightSegmentGlobal.drawOn(shapesRenderer)
      catcher.bottomSegmentGlobal.drawOn(shapesRenderer)
    }
   
    debugRenderers.end()
  }
}