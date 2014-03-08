package com.ataraxer.apps.chess.scala

import com.ataraxer.apps.chess.scala.pieces._


abstract class Move(board: Board) {
  def apply: Board
  def unapply: Board = board
}


case class SimpleMove
  (board: Board)
  (from: Coord, to: Coord)
  extends Move(board)
{
  override def apply: Board =
    board(from).piece match {
      case Some(piece) => piece.move(board, from, to)
      case None => throw new Board.NoPieceAtCellException
    }
}


case class PromotePawnMove[A]
  (board: Board)
  (from: Coord, to: Coord, newType: A)
  extends Move(board)
{
  override def apply: Board = {
    val newBoard = SimpleMove(board)(from, to).apply
    newBoard.promotePawn(to, newType)
  }
}

