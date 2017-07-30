package ar.com.pablitar.catchthething

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx

import ar.com.pablitar.catchthething.systems.CatcherSystem
import ar.com.pablitar.libgdx.commons.engines.Engines
import ar.com.pablitar.catchthething.systems.SeedSpawnerSystem
import ar.com.pablitar.catchthething.systems.SeedsSystem

class CatchTheThingGame extends ApplicationAdapter {
  
  lazy val engine = {
    val commonEngine = Engines.commonEngine()
    commonEngine.addSystem(new CatcherSystem)
    commonEngine.addSystem(new SeedSpawnerSystem)
    commonEngine.addSystem(new SeedsSystem)
    commonEngine
  }
  
  override def create() = {
    engine.addEntity(CTTEntities.catcher)
    engine.addEntity(CTTEntities.catcherShadow)
    engine.addEntity(CTTEntities.seedSpawner)
  }
  
  override def render() = {
    engine.update(Gdx.graphics.getDeltaTime())
  }
}
