package com.ataraxer.apps.chess.scala.pieces.moveTypes

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.{Board, Coord, Shift}


trait MovesPredefined {
  def moveIsValid(board: Board, toCoord: Coord): Boolean

  def addMove(position: Coord, board: Board, shift: Shift): List[Coord] =
    (position << shift) match {
      case Some(move) =>
        if (moveIsValid(board, move)) List(move) else Nil
      case None => Nil
    }
}

