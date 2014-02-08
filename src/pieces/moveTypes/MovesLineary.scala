package com.ataraxer.apps.chess.scala.pieces.moveTypes

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord

/**
 * Created with IntelliJ IDEA.
 * User: Ataraxer
 * Date: 11.12.12
 * Time: 11:26
 */
trait MovesLineary {
  def getPosition: Coord
  def moveIsValid(pieceColorMap: Array[Array[Color]], toCoord: Coord): Boolean

  def iterateDirection(
    piecesColorMap: Array[Array[Color]],
    coordShift: (Int, Int),
    possibleTurns: List[Coord] = List[Coord](),
    iteration: Int = 1
    ): List[Coord] =
  {
    def colorOf(c: Coord) = if (c != null) piecesColorMap(c.row)(c.col) else null

    val (xShift, yShift) = coordShift
    val currentMove = getPosition << (xShift * iteration, yShift * iteration)

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
