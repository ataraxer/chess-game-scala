package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesPredefined

case class Knight(color: Color, position: Coord, hasMoved: Boolean = false)
    extends Piece(color, position, hasMoved)
    with MovesPredefined
{
  override def getTypeAsString = "Kn"
  override def getType = PieceType.Knight
  override def directionShifts = List(
    (1, 2), (1, -2), (-1, 2), (-1, -2),
    (2, 1), (2, -1), (-2, 1), (-2, -1)
  )

  override def addMove(piecesColorMap: Array[Array[Color]], coordShift: (Int, Int))
    = addPredefinedMove(piecesColorMap, coordShift)
}
