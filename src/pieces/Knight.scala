package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesPredefined

case class Knight(_color: Color, _position: Coord, _hasMoved: Boolean = false)
    extends Piece(_color, _position, _hasMoved)
    with MovesPredefined
{
  override def getTypeAsString = "Kn"
  override def directionShifts = List(
    (1, 2), (1, -2), (-1, 2), (-1, -2),
    (2, 1), (2, -1), (-2, 1), (-2, -1)
  )

  override def setPosition(position: Coord) =
    Knight(color, position, hasMoved)

  override def addMove(piecesColorMap: Array[Array[Color]], coordShift: (Int, Int))
    = addPredefinedMove(piecesColorMap, coordShift)
}
