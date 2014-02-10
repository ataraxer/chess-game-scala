package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesLineary

case class Bishop(_color: Color, _position: Coord, _hasMoved: Boolean = false)
    extends Piece(_color, _position, _hasMoved)
    with MovesLineary
{
  override def getTypeAsString = "Bi"
  override def directionShifts = List(
    // Diagonal moves
    (1, 1), (1, -1), (-1, 1), (-1, -1)
  )

  override def setPosition(position: Coord) =
    Bishop(color, position, hasMoved)

  override def addMove(piecesColorMap: ColorMap, coordShift: (Int, Int)) =
    iterateDirection(piecesColorMap, coordShift)
}
