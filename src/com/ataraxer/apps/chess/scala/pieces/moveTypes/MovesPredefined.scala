package com.ataraxer.apps.chess.scala.pieces.moveTypes

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord

/**
 * Created with IntelliJ IDEA.
 * User: Ataraxer
 * Date: 11.12.12
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
trait MovesPredefined {
  def getPosition: Coord
  def moveIsValid(pieceColorMap: Array[Array[Color]], toCoord: Coord): Boolean

  def addPredefinedMove(piecesColorMap: Array[Array[Color]], shift: List[Int]): List[Coord] = {
    val currentMove = getPosition << shift

    if (moveIsValid(piecesColorMap, currentMove))
      List(currentMove)
    else List()
  }
}
