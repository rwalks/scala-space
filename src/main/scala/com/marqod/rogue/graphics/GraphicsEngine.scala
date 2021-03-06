package com.marqod.rogue.graphics

import java.awt.geom.{AffineTransform, GeneralPath}

import com.marqod.rogue.art.ArtHolder
import com.marqod.rogue.engine.GameEngine
import com.marqod.rogue.utils.{Colors, Config, Vector2}

import scala.swing.Graphics2D

/**
  * Created by ryan.walker on 10/29/16.
  */
class GraphicsEngine(engine: GameEngine, canvas: Canvas) extends Config with ArtHolder {

  val camera = new Camera()

  def draw(): Unit = {
    camera.update(engine.gameState.player.position)
    canvas.draw()
  }

  def drawScene(g: Graphics2D) = {

    g.scale(1.0, 0.5)
    g.shear(-1, 0.5)
    //
    drawWater(g, camera.offset)
    //waves
    engine.gameState.waves.foreach { w =>
      waveArt.draw(g,w,camera.offset)
    }

    engine.gameState.wakes.foreach { w =>
      wakeArt.draw(g,w,camera.offset)
    }

    engine.gameState.islands.foreach { i =>
      islandArt.draw(g,i,camera.offset)
    }

    playerArt.draw(g,engine.gameState.player, camera.offset)
  }

  private def drawWater(g: Graphics2D, offset: Vector2) = {
    //base
    val gCon: Graphics2D = g.create().asInstanceOf[Graphics2D]
    gCon.translate(-offset.x,-offset.y)
    gCon.setColor(Colors.blue)
    val wZ = 60
    val wX = 0 + wZ
    val wY = 0 + wZ
    gCon.fillRect(wX, wY, WORLD_SIZE.x.toInt, WORLD_SIZE.y.toInt)
    gCon.setColor(Colors.seaBlue)
    gCon.fillRect(0, 0, WORLD_SIZE.x.toInt, WORLD_SIZE.y.toInt)
    gCon.dispose()
    //
    gCon.dispose()
  }
}
