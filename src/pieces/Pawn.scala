package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord

case class Pawn(_color: Color, _hasMoved: Boolean = false)
    extends Piece(_color, _hasMoved)
{
  val rowShift = if (color == White) 1 else -1
  val directionShifts = List((1, 1), (1, -1), (1, 0), (2,  0))

  def addMove(position: Coord, piecesColorMap: ColorMap, coordShift: (Int, Int)) = {
    val (xShift, yShift) = coordShift

    def colorOf(c: Coord) = piecesColorMap(c.row)(c.col).getOrElse(null)

    val currentMove = position << (xShift * rowShift, yShift)

    def condition(move: Coord): Boolean = {
      val mc = colorOf(move)
      val attacking   = (yShift.abs == 1 && mc == color.opposite)
      val movingFirst = (!hasMoved && xShift == 2)
      val moving      = yShift == 0
      (mc == null && (movingFirst || moving)) || attacking
    }

    currentMove match {
      case Some(move) => if (condition(move)) List(move) else Nil
      case None => Nil
    }
  }
}
