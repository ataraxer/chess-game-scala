package com.ataraxer.apps.chess.scala.pieces.moveTypes

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.{Board, Coord, Shift}


trait MovesLineary {
  val   linearShifts = List((0, 1), (0, -1), ( 1, 0), (-1,  0))
  val diagonalShifts = List((1, 1), (1, -1), (-1, 1), (-1, -1))

  val color: Color
  val DiffColor = color.opposite

  def addMove(position: Coord, board: Board, shift: Shift): List[Coord] = {
    def nextMove(cShift: Shift): List[Coord] =
      (position << cShift) match {
        case Some(move) =>
          board(move).color match {
            case None => move :: nextMove(cShift + shift)
            case Some(DiffColor) => List(move)
            case _ => Nil
          }
        case None => Nil
      }

    nextMove(shift)
  }
}

