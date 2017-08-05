package ar.com.pablitar.catchthething

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.{ Color, Texture }
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d._
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Array

import scala.collection.mutable.AnyRefMap
import ar.com.pablitar.libgdx.commons.ResourceManager
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode

/**
 * Created by pablitar on 22/12/16.
 */
object Resources extends ResourceManager {

  //  def multiSizeFont(fontName: String, extraParameters: (FreeTypeFontParameter => Unit) = { p => }): Map[Int, BitmapFont] = {
  //    val generator = new FreeTypeFontGenerator(Gdx.files.internal(fontName))
  //    val parameter = new FreeTypeFontParameter
  //
  //    val result = FontUtils.allSizes.map { (aSize: Int) =>
  //      parameter.size = aSize
  //      extraParameters(parameter)
  //      (aSize, generator.generateFont(parameter))
  //    }.toMap
  //
  //    generator.dispose()
  //
  //    result
  //  }

  lazy val atlas = new TextureAtlas("pack.atlas")
  
  def macetaSprite = {
    val sp = atlas.createSprite("maceta",0)
    sp.setOriginCenter()
    sp
  }
  
  def seedSprite: Sprite = {
    val sp = atlasSprite("semilla")
    sp.setOrigin(sp.getWidth /2, sp.getHeight * 0.3f)
    sp.scale(0.2f)
    sp
  }
  
  def macetaAnimation = {
    val sps = new Animation[Sprite](0.07f, sprites("maceta"))
    sps.setPlayMode(PlayMode.LOOP)
    sps
  }
  
  def macetaShadowSprite = {
    val sp = atlas.createSprite("macetaShadow",0)
    sp.setOriginCenter()
    sp
  }
  
  def macetaShadowAnimation = {
    val sps = new Animation[Sprite](0.07f, sprites("macetaShadow"))
    sps.setPlayMode(PlayMode.LOOP)
    sps
  }

}
