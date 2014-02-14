package com.ataraxer.apps.chess.scala.pieces.moveTypes

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.{Board, Coord, Shift}


/**
 * Created with IntelliJ IDEA.
 * User: Ataraxer
 * Date: 11.12.12
 * Time: 11:26
 */
trait MovesLineary {
  val   linearShifts = List((0, 1), (0, -1), ( 1, 0), (-1,  0))
  val diagonalShifts = List((1, 1), (1, -1), (-1, 1), (-1, -1))

  def moveIsValid(board: Board, toCoord: Coord): Boolean

  def addMove(p: Coord, cm: Board, cs: Shift) =
    nextMove(p, cm, cs)

  def nextMove(position: Coord, board: Board, shift: Shift,
               moves: List[Coord] = List[Coord](),
               step: Int = 1): List[Coord] =
    (position << shift * step) match {
      case Some(move) =>
        if (moveIsValid(board, move))
          if (board(move).isEmpty)
            nextMove(position, board, shift, move :: moves, step + 1)
          else
            move :: moves
        else moves
      case None => moves
    }
}

