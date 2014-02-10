package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesPredefined

case class King(_color: Color, _position: Coord, _hasMoved: Boolean = false)
    extends Piece(_color, _position, _hasMoved)
    with MovesPredefined
{
  override def getTypeAsString = "Ki"
  override def directionShifts = List(
    (0, 1), ( 0, -1), ( 1,  1), (-1,  1),
    (1, 0), (-1,  0), (-1, -1), ( 1, -1)
  )

  override def setPosition(position: Coord) =
    King(color, position, hasMoved)

  override def addMove(piecesColorMap: ColorMap, coordShift: (Int, Int))
    = addPredefinedMove(piecesColorMap, coordShift)
}
