package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord

class Pawn(color: Color, position: Coord, hasMoved: Boolean = false) extends Piece(color, position, hasMoved) {
  override def getTypeAsString = "Pw"
  override def getType = PieceType.Pawn
  override def directionShifts = List(
    List(1, 1), List(1, -1),
    List(1, 0), List(2,  0)
  )

  val rowShift = if (color == White) 1 else -1

  def addMove(piecesColorMap: Array[Array[Color]], coordShift: List[Int]) = {
    def colorOf(c: Coord) = piecesColorMap(c.row)(c.col)

    val currentMove = position << List(coordShift(0) * rowShift, coordShift(1))

    def condition: Boolean = {
      val mc = colorOf(currentMove)
      val cs = coordShift
      ((mc == null
        && ((cs(0) == 2 && !hasMoved) || cs(1) == 0))
        || (cs(1).abs == 1 && mc == getColor.opposite))
    }

    if (currentMove != null && condition)
      List(currentMove)
    else List()
  }
}
