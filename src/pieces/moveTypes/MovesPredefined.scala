package com.ataraxer.apps.chess.scala.pieces.moveTypes

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.{Board, Coord, Shift}


/**
 * Created with IntelliJ IDEA.
 * User: Ataraxer
 * Date: 11.12.12
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
trait MovesPredefined {
  def moveIsValid(board: Board, toCoord: Coord): Boolean

  def addMove(position: Coord, board: Board, shift: Shift): List[Coord] =
    (position << shift) match {
      case Some(move) =>
        if (moveIsValid(board, move)) List(move) else Nil
      case None => Nil
    }
}

