package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesPredefined

case class King(color: Color, position: Coord, hasMoved: Boolean = false)
    extends Piece(color, position, hasMoved)
    with MovesPredefined
{
  override def getTypeAsString = "Ki"
  override def getType = PieceType.King
  override def directionShifts = List(
    (0, 1), ( 0, -1), ( 1,  1), (-1,  1),
    (1, 0), (-1,  0), (-1, -1), ( 1, -1)
  )

  override def addMove(piecesColorMap: Array[Array[Color]], coordShift: (Int, Int))
    = addPredefinedMove(piecesColorMap, coordShift)
}
