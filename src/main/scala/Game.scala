package com.ataraxer.apps.chess.scala

import com.ataraxer.apps.chess.scala.Color._


object Game {
  private var moves: List[Board] = List(Board())
  private var turn: Color = White

  private def lastMove = moves.head
  private def addMove(move: Board) { moves ::= move }
  private def switchTurn() { turn = turn.opposite }

  def newGame() {
    moves = List(Board())
    turn = White
  }


  def move(from: Coord, to: Coord) {
    addMove(lastMove.movePiece(from, to))
    switchTurn()
  }


  def main(args: Array[String]) {
    println("Ataraxer's Chess Game (Scala) v0.1.0")
    newGame()
  }
}
