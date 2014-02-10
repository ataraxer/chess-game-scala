package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesLineary

case class Queen(_color: Color, _position: Coord, _hasMoved: Boolean = false)
    extends Piece(_color, _position, _hasMoved)
    with MovesLineary
{
  override def getTypeAsString = "Qu"
  override def directionShifts = List(
    (0, 1), (0, -1), ( 1, 0), (-1,  0), // Vertical and horizontal moves
    (1, 1), (1, -1), (-1, 1), (-1, -1)  // Diagonal moves
  )

  override def setPosition(position: Coord) =
    Queen(color, position, hasMoved)

  override def addMove(piecesColorMap: ColorMap, coordShift: (Int, Int))
    = iterateDirection(piecesColorMap, coordShift)
}
