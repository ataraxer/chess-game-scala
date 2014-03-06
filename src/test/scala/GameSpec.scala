package com.ataraxer.apps.chess.scala

import org.scalatest._

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.pieces._
import com.ataraxer.apps.chess.scala.pieces.Piece.ImpossibleMoveException


class GameSpec extends UnitSpec {

  "A Chess Game" should "conform to the rules of chess" in {
    val board = Board()

    val firstMove = board.movePiece(Coord(1, 3), Coord(3, 3))
    assert(firstMove == Board().update(
      Cell(Coord(1, 3), None),
      Cell(Coord(3, 3), Some(Pawn(White, true)))
    ))

    val secondMove = firstMove.movePiece(Coord(0, 2), Coord(5, 7))
    assert(secondMove == Board().update(
      Cell(Coord(1, 3), None),
      Cell(Coord(3, 3), Some(Pawn(White, true))),
      Cell(Coord(0, 2), None),
      Cell(Coord(5, 7), Some(Bishop(White, true)))
    ))

    val thirdMove = secondMove.movePiece(Coord(6, 6), Coord(4, 6))
    assert(thirdMove == Board().update(
      Cell(Coord(1, 3), None),
      Cell(Coord(3, 3), Some(Pawn(White, true))),
      Cell(Coord(0, 2), None),
      Cell(Coord(5, 7), Some(Bishop(White, true))),
      Cell(Coord(6, 6), None),
      Cell(Coord(4, 6), Some(Pawn(Black, true)))
    ))

    intercept[ImpossibleMoveException] {
      thirdMove.movePiece(Coord(5, 7), Coord(3, 5))
    }
  }

}

// vim: set ts=2 sw=2 et:
