package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import moveTypes.MovesLineary

class Queen(color: Color, position: Coord, hasMoved: Boolean = false) extends Piece(color, position, hasMoved) with MovesLineary {
  override def getTypeAsString = "Qu"
  override def getType = PieceType.Queen
  override def directionShifts = List(
    List(0, 1), List(0, -1), List( 1, 0), List(-1,  0), // Vertical and horizontal moves
    List(1, 1), List(1, -1), List(-1, 1), List(-1, -1)  // Diagonal moves
  )

  override def addMove(piecesColorMap: Array[Array[Color]], coordShift: List[Int])
    = iterateDirection(piecesColorMap, coordShift)
}