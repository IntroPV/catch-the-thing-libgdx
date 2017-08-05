package ar.com.pablitar.catchthething.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity

class CatcherComponent(var score:Int = 0) extends Component
class SeedComponent extends Component
class NonCaughtSeedComponent extends Component
class CaughtSeedComponent(val catcher: Entity) extends Component
class CatcherTop(val catcher: Entity) extends Component

case class SeedSpawnerComponent(minNext: Float, maxNext: Float) extends Component {
  var timeUntilNextSpawn = maxNext
}

object Mappers {
  val seedSpawnerComponentMapper = ComponentMapper.getFor(classOf[SeedSpawnerComponent])
  val catcherMapper = ComponentMapper.getFor(classOf[CatcherComponent])
  val catcherTopMapper = ComponentMapper.getFor(classOf[CatcherTop])
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
    def score = catcherMapper.get(e).score
    def score_=(s: Int) = catcherMapper.get(e).score = s
  }
  
  implicit class CatcherTopEntity(e: Entity) {
    def catcher = catcherTopMapper.get(e).catcher
  }
}