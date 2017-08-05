package ar.com.pablitar.catchthething.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.libgdx.commons.math.Segment2

class CatcherComponent(val leftSegment: Segment2, val rightSegment: Segment2, val bottomSegment: Segment2, var score:Int = 0) extends Component
class SeedComponent(val radius: Float) extends Component
class NonCaughtSeedComponent extends Component
class CaughtSeedComponent(val catcher: Entity, var lastCollision: Option[Any] = None) extends Component
class CatcherTop(val catcher: Entity) extends Component

case class SeedSpawnerComponent(minNext: Float, maxNext: Float) extends Component {
  var timeUntilNextSpawn = maxNext
}

object Mappers {
  val seedSpawnerComponentMapper = ComponentMapper.getFor(classOf[SeedSpawnerComponent])
  val catcherMapper = ComponentMapper.getFor(classOf[CatcherComponent])
  val catcherTopMapper = ComponentMapper.getFor(classOf[CatcherTop])
  val caughtSeedMapper = ComponentMapper.getFor(classOf[CaughtSeedComponent])
  val seedMapper = ComponentMapper.getFor(classOf[SeedComponent])
}

import Mappers._
import com.badlogic.gdx.math.Vector2

object Extensions {
  implicit class SeedSpawnerEntity(e: Entity) {
    def seedSpawnerComponent = seedSpawnerComponentMapper.get(e)
    def minNext = seedSpawnerComponent.minNext
    def maxNext = seedSpawnerComponent.maxNext
    def timeUntilNextSpawn = seedSpawnerComponent.timeUntilNextSpawn
    def timeUntilNextSpawn_=(time: Float) = seedSpawnerComponent.timeUntilNextSpawn = time
  }
  
  implicit class CatcherEntity(e: Entity) {
    import ar.com.pablitar.libgdx.commons.components.Extensions._
    def catcherComponent = catcherMapper.get(e)
    def score = catcherComponent.score
    def score_=(s: Int) = catcherComponent.score = s
    
    def leftSegmentGlobal = catcherComponent.leftSegment.displaced(e.position)
    def rightSegmentGlobal = catcherComponent.rightSegment.displaced(e.position)
    def bottomSegmentGlobal = catcherComponent.bottomSegment.displaced(e.position)
  }
  
  implicit class CatcherTopEntity(e: Entity) {
    def catcher = catcherTopMapper.get(e).catcher
  }
  
  implicit class SeedEntity(e: Entity) {
    def radius = seedMapper.get(e).radius
  }
  
  implicit class CaughtSeedEntity(e: Entity) {
    def lastCollision = caughtSeedMapper.get(e).lastCollision
    def putLastCollision(collider: Any) = caughtSeedMapper.get(e).lastCollision = Some(collider)
  }
}