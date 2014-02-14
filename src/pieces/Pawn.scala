package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.{Board, Coord, Shift}


case class Pawn(_color: Color, _hasMoved: Boolean = false)
    extends Piece(_color, _hasMoved)
{
  val rowShift = if (color == White) 1 else -1
  val directionShifts = List((1, 1), (1, -1), (1, 0), (2,  0))

  def addMove(position: Coord, board: Board, shift: Shift) = {
    val Shift(xShift, yShift) = shift

    def colorOf(c: Coord) = board(c.row)(c.col).color

    val currentMove = position << (xShift * rowShift, yShift)

    def condition(move: Coord): Boolean = {
      val mc = colorOf(move)
      val attacking   = (yShift.abs == 1 && mc == Some(color.opposite))
      val movingFirst = (!hasMoved && xShift == 2)
      val moving      = yShift == 0
      (mc == None && (movingFirst || moving)) || attacking
    }

    currentMove match {
      case Some(move) =>
        if (condition(move)) List(move) else Nil
      case None => Nil
    }
  }
}

