package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesLineary

case class Bishop(color: Color, position: Coord, hasMoved: Boolean = false)
    extends Piece(color, position, hasMoved)
    with MovesLineary
{
  override def getTypeAsString = "Bi"
  override def getType = PieceType.Bishop
  override def directionShifts = List(
    // Diagonal moves
    (1, 1), (1, -1), (-1, 1), (-1, -1)
  )

  override def addMove(piecesColorMap: Array[Array[Color]], coordShift: (Int, Int))
    = iterateDirection(piecesColorMap, coordShift)
}
