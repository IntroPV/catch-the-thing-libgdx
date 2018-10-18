package ar.com.pablitar.catchthething

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx

import ar.com.pablitar.catchthething.systems.CatcherSystem
import ar.com.pablitar.catchthething.systems.SeedSpawnerSystem
import ar.com.pablitar.catchthething.systems.SeedsSystem
import ar.com.pablitar.libgdx.commons.engines.Engines
import ar.com.pablitar.catchthething.systems.CatcherDebugSystem

class CatchTheThingGame extends ApplicationAdapter {
  
  val debug = true
  
  lazy val engine = {
    val commonEngine = Engines.commonEngine(debug)
    commonEngine.addSystem(new CatcherSystem)
    commonEngine.addSystem(new SeedSpawnerSystem)
    commonEngine.addSystem(new SeedsSystem)
    if(debug) {
      commonEngine.addSystem(new CatcherDebugSystem)
    }
    commonEngine
  }
  
  override def create() = {
    val catcher = CTTEntities.catcher
    engine.addEntity(catcher)
    engine.addEntity(CTTEntities.catcherShadow(catcher))
    engine.addEntity(CTTEntities.catcherTop(catcher))
    engine.addEntity(CTTEntities.seedSpawner)
  }
  
  override def render() = {
    engine.update(Gdx.graphics.getDeltaTime())
  }
}
