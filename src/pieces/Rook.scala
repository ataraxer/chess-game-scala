package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesLineary

case class Rook(color: Color, position: Coord, hasMoved: Boolean = false)
    extends Piece(color, position, hasMoved)
    with MovesLineary
{
  override def getTypeAsString = "Rk"
  override def getType = PieceType.Rook
  override def directionShifts = List(
    (0, 1), (0, -1), ( 1, 0), (-1,  0) // Vertical and horizontal moves
  )

  override def addMove(piecesColorMap: Array[Array[Color]], coordShift: (Int, Int))
    = iterateDirection(piecesColorMap, coordShift)
}
