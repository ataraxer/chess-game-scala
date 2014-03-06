package com.ataraxer.apps.chess.scala

import com.ataraxer.apps.chess.scala.pieces._


abstract class Move {
  def apply(board: Board): Board
  def unapply(board: Board): Board
}

case class SimpleMove(from: Coord, to: Coord) extends Move {
  override def apply(board: Board): Board =
    board(from).piece match {
      case Some(piece) => piece.move(board, from, to)
      case None => throw new Board.NoPieceAtCellException
    }

  override def unapply(board: Board): Board =
    SimpleMove(to, from).apply(board)
}

case class PromotePawn[A](from: Coord, to: Coord, newType: A) extends Move {
  override def apply(board: Board): Board = {
    val newBoard = SimpleMove(from, to).apply(board)
    newBoard.promotePawn(to, newType)
  }

  override def unapply(board: Board): Board = board
}

