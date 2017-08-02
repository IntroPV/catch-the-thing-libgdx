package ar.com.pablitar.catchthething.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity

class CatcherComponent(val shadow: Entity, val top: Entity, var score:Int = 0) extends Component
class SeedComponent extends Component

case class SeedSpawnerComponent(minNext: Float, maxNext: Float) extends Component {
  var timeUntilNextSpawn = maxNext
}

object Mappers {
  val seedSpawnerComponentMapper = ComponentMapper.getFor(classOf[SeedSpawnerComponent])
  val catcherMapper = ComponentMapper.getFor(classOf[CatcherComponent])
}

import Mappers._

object Extensions {
  implicit class SeedSpawnerEntity(e: Entity) {
    def seedSpawnerComponent = seedSpawnerComponentMapper.get(e)
    def minNext = seedSpawnerComponent.minNext
    def maxNext = seedSpawnerComponent.maxNext
    def timeUntilNextSpawn = seedSpawnerComponent.timeUntilNextSpawn
    def timeUntilNextSpawn_=(time: Float) = seedSpawnerComponent.timeUntilNextSpawn = time
  }
  
  implicit class CatcherEntity(e: Entity) {
    def shadow = catcherMapper.get(e).shadow
    def top = catcherMapper.get(e).top
  }
}