package com.ataraxer.apps.chess.scala

import com.ataraxer.apps.chess.scala.pieces.Piece.ImpossibleMoveException


object Chess {
  def main(args: Array[String]) {
    var b = new Board()
    println(b)

    def move(from: Coord, to: Coord) =
      try {
        b = b movePiece(from, to)
        println(b)
      } catch {
        case e: ImpossibleMoveException =>
          println("Impossible move!\n")
      }

    // pawn two steps
    move(Coord(1, 3), Coord(3, 3))

    // move bishop
    move(Coord(0, 2), Coord(5, 7))

    // move black pawn to obstruct bishop
    move(Coord(6, 6), Coord(4, 6))

    // try to move white bishop past black pawn
    move(Coord(5, 7), Coord(3, 5))
  }
}
