package com.ataraxer.apps.chess.scala.pieces.moveTypes

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.{Board, Coord, Shift}


trait MovesLineary {
  val   linearShifts = List((0, 1), (0, -1), ( 1, 0), (-1,  0))
  val diagonalShifts = List((1, 1), (1, -1), (-1, 1), (-1, -1))

  val color: Color

  def addMove(position: Coord, board: Board, shift: Shift): List[Coord] = {
    // other color that that of a piece
    val Diff = Some(color.opposite)

    def nextMove(currentShift: Shift): List[Coord] =
      (position << currentShift) match {
        case Some(move) =>
          board(move).color match {
            case None => move :: nextMove(currentShift + shift)
            case Diff => List(move)
            case _    => Nil
          }
        case None => Nil
      }

    nextMove(shift)
  }
}

