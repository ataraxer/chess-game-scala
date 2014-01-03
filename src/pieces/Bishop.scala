package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesLineary

class Bishop(color: Color, position: Coord, hasMoved: Boolean = false) extends Piece(color, position, hasMoved) with MovesLineary {
  override def getTypeAsString = "Bi"
  override def getType = PieceType.Bishop
  override def directionShifts = List(
    List(1, 1), List(1, -1), List(-1, 1), List(-1, -1)  // Diagonal moves
  )

  override def addMove(piecesColorMap: Array[Array[Color]], coordShift: List[Int])
    = iterateDirection(piecesColorMap, coordShift)
}
