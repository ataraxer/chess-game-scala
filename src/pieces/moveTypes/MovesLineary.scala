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
  val   linearShifts = List((0, 1), (0, -1), ( 1, 0), (-1,  0))
  val diagonalShifts = List((1, 1), (1, -1), (-1, 1), (-1, -1))

  val position: Coord
  def moveIsValid(pieceColorMap: ColorMap, toCoord: Coord): Boolean

  def addMove(cm: ColorMap, cs: (Int, Int)) = iterateDirection(cm, cs)

  def iterateDirection(
    piecesColorMap: ColorMap,
    coordShift: (Int, Int),
    possibleTurns: List[Coord] = List[Coord](),
    iteration: Int = 1
    ): List[Coord] =
  {
    def colorOf(c: Coord) = piecesColorMap(c.row)(c.col)

    val (xShift, yShift) = coordShift
    val currentMove = position << (xShift * iteration, yShift * iteration)
    currentMove match {
      case Some(move) =>
        if (moveIsValid(piecesColorMap, move))
          if (colorOf(move).isEmpty)
            iterateDirection(
              piecesColorMap, coordShift, possibleTurns :+ move, iteration + 1
            )
          else possibleTurns :+ move
        else possibleTurns
      case None => possibleTurns
    }

  }
}
