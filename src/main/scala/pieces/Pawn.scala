package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.{Board, Coord, Shift}


case class Pawn(_color: Color, _hasMoved: Boolean = false)
    extends Piece(_color, _hasMoved)
{
  val directionShifts = List((1, 1), (1, -1), (1, 0), (2,  0))
  val direction = if (color == White) (1, 0) else (-1, 0)

  def addMove(position: Coord, board: Board, shift: Shift) = {
    def moveIsValid(move: Coord) = {
      val moveColor = board(move).color
      shift match {
        case Shift(1, 1 | -1) => moveColor == Some(color.opposite)
        case Shift(1, 0)      => moveColor == None
        case Shift(2, 0)      => moveColor == None && !hasMoved
      }
    }

    (position << shift * direction) match {
      case Some(move) =>
        if (moveIsValid(move)) List(move) else Nil
      case None => Nil
    }
  }
}

