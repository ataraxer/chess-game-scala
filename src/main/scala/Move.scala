package com.ataraxer.apps.chess.scala

import com.ataraxer.apps.chess.scala.pieces._
import com.ataraxer.apps.chess.scala.Color._


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


case class CasltingMove(board: Board)(color: Color, short: Boolean = true)
  extends Move(board)
{
  override def apply: Board = {
    val row = color match {
      case White => 0
      case Black => 7
    }

    val bishopColumn = short match {
      case true  => 7
      case false => 0
    }

    val kingMove = SimpleMove(board)(
      Coord(row, 4), Coord(row, bishopColumn)
    ).apply

    kingMove.update(
      Cell(Coord(row, 7), Some(Bishop(color, true)))
    )
  }
}

