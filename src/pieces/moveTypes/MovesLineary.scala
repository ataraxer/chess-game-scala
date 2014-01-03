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
trait MovesLineary {
  def getPosition: Coord
  def moveIsValid(pieceColorMap: Array[Array[Color]], toCoord: Coord): Boolean

  def iterateDirection(
    piecesColorMap: Array[Array[Color]],
    coordShift: List[Int],
    possibleTurns: List[Coord] = List[Coord](),
    iteration: Int = 1
    ): List[Coord] =
  {
    def colorOf(c: Coord) = if (c != null) piecesColorMap(c.row)(c.col) else null

    val currentMove = getPosition << coordShift.map(_ * iteration)

    if (moveIsValid(piecesColorMap, currentMove))
      if (colorOf(currentMove) == null)
        iterateDirection(
          piecesColorMap, coordShift, possibleTurns :+ currentMove, iteration + 1
        )
      else possibleTurns :+ currentMove
    else
      possibleTurns
  }
}
