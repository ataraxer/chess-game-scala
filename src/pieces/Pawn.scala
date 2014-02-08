package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord

case class Pawn(color: Color, position: Coord, hasMoved: Boolean = false)
    extends Piece(color, position, hasMoved)
{
  override def getTypeAsString = "Pw"
  override def getType = PieceType.Pawn
  override def directionShifts = List(
    (1, 1), (1, -1),
    (1, 0), (2,  0)
  )

  val rowShift = if (color == White) 1 else -1

  def addMove(piecesColorMap: Array[Array[Color]], coordShift: (Int, Int)) = {
    val (xShift, yShift) = coordShift

    def colorOf(c: Coord) = piecesColorMap(c.row)(c.col)

    val currentMove = position << (xShift * rowShift, yShift)

    def condition: Boolean = {
      val mc = colorOf(currentMove)
      val attacking   = (yShift.abs == 1 && mc == getColor.opposite)
      val movingFirst = (!hasMoved && xShift == 2)
      val moving      = yShift == 0
      (mc == null && (movingFirst || moving)) || attacking
    }

    if (currentMove != null && condition)
      List(currentMove)
    else Nil
  }
}
