package com.ataraxer.apps.chess.scala.algorithms

import com.ataraxer.apps.chess.scala.{Coord, Board}
import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.pieces._

/**
 * DRAFT
 */
object Default {
  def solve(board: Board, color: Color) = {
    val myPieces = board.getPieces(color)
    var resultingBoards: List[Board] = List()

    for (piece <- myPieces) {
      // generate moves
      val possibleMoves = piece.possibleMoves(
        board.piecesColorMap
      )
      // generate move results
      val addBoards =
        for (move <- possibleMoves)
          yield board.movePiece(piece.position, move)

      resultingBoards = resultingBoards ++ addBoards
    }

    // analyze result efficiency
    val solutions =
      for (resBoard <- resultingBoards)
      yield (analyze(resBoard, color), resBoard)
    var best = solutions(0)
    for (s <- solutions) {
      //println(s._1)
      best = if (s._1 > best._1) s else best
    }
    best._2
  }

  private def pieceValue(p: Piece): Int = {
    p match {
      case Pawn(_, _, _)   => 1
      case Knight(_, _, _) => 3
      case Bishop(_, _, _) => 3
      case Rook(_, _, _)   => 5
      case Queen(_, _, _)  => 9
      case King(_, _, _)   => 20
    }
  }

  def analyze(board: Board, color: Color): Int = {
    var score = 0;

    val myPieces = board.getPieces(color)
    for (piece <- myPieces) {
      val cellInfluence = piece.possibleMoves(
        board.piecesColorMap
      )
      for (move <- cellInfluence) {
        if (!board.getCell(move).isEmpty) {
          if (board.getPiece(move).color == color) {
            score += 3
          } else {
            score += pieceValue(
              board.getPiece(move)
            )
          }
        } else {
          score += 1
        }
      }
    }
    score
  }

  def main(args: Array[String]) {
    var b = new Board()
    println(b)

    var c = White
    for (i <- 1 to 100) {
      b = solve(b, c)
      c = c.opposite
      println(b)
    }
  }
}
