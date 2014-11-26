package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.{Board, Cell, Coord, Shift}


object Pawn {
  class WrongPromotionTypeException extends Exception
  class PawnCantBePromotedException extends Exception
}


case class Pawn(color: Color, hasMoved: Boolean = false)
    extends Piece
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


  def isPromotable(at: Coord): Boolean =
    at match {
      case Coord(_, 7) if color == White => true
      case Coord(_, 0) if color == Black => true
      case _ => false
    }


    def promote(board: Board, at: Coord, to: (Color, Boolean) => Piece): Board =
    if (isPromotable(at))
      board.update(
        Cell(at, Some(to(color, true)))
      )
    else
      throw new Pawn.PawnCantBePromotedException
}

