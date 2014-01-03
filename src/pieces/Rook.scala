package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesLineary

class Rook(color: Color, position: Coord, hasMoved: Boolean = false) extends Piece(color, position, hasMoved) with MovesLineary {
  override def getTypeAsString = "Rk"
  override def getType = PieceType.Rook
  override def directionShifts = List(
    List(0, 1), List(0, -1), List( 1, 0), List(-1,  0) // Vertical and horizontal moves
  )

  override def addMove(piecesColorMap: Array[Array[Color]], coordShift: List[Int])
    = iterateDirection(piecesColorMap, coordShift)
}
