package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.{Board, Cell, Coord, Shift}


/*
 * Piece is abstract class that represents single
 * chess piece which can be located somewhere on the board
 *
 */
object Piece {
  class ImpossibleMoveException extends Exception
}

abstract class Piece(val color: Color, val hasMoved: Boolean) {
  def shortName: String = getClass.getName.split('.').last.substring(0, 2)

  protected val directionShifts: List[(Int, Int)]
  protected def addMove(position: Coord, board: Board, shift: Shift): List[Coord]

  def moveIsValid(board: Board, to: Coord) =
    board(to).color != Some(color)

  def possibleMoves(position: Coord, board: Board): List[Coord] =
    for (shift <- directionShifts;
         move  <- addMove(position, board, shift))
    yield move

  def movePossible(board: Board, from: Coord, to: Coord): Boolean =
    possibleMoves(from, board) contains to

  def copy(hasMoved: Boolean = false): Piece = this match {
    case Pawn(_, _) => Pawn(color, hasMoved)
    case Rook(_, _) => Rook(color, hasMoved)
    case Knight(_, _) => Knight(color, hasMoved)
    case Bishop(_, _) => Bishop(color, hasMoved)
    case King(_, _) => King(color, hasMoved)
    case Queen(_, _) => Queen(color, hasMoved)
  }

  def move(board: Board, from: Coord, to: Coord): Board = {
    val moveIsPossible = possibleMoves(from, board) contains to
    if (moveIsPossible)
      board.update(List(
        Cell(to, Some(this.copy(hasMoved=true))),
        Cell(from, None)
      ))
    else
      throw new Piece.ImpossibleMoveException
  }

  override def toString = color.shortName + shortName
}

