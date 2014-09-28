package com.ataraxer.apps.chess.scala

import com.ataraxer.apps.chess.scala.Color._


class Game {
  private var moves: List[Board] = List(Board())
  private var turn: Color = White

  private def lastMove = moves.head
  private def addMove(move: Board) { moves ::= move }
  private def switchTurn() { turn = turn.opposite }

  def move(from: Coord, to: Coord) {
    addMove(lastMove.movePiece(from, to))
    switchTurn()
  }
}
