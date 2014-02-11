package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord

/*
 * Piece is abstract class that represents single
 * chess piece which can be located somewhere on the board
 *
 */
abstract class Piece(val color: Color, val position: Coord, val hasMoved: Boolean) {

  def getTypeAsString: String
  def directionShifts: List[(Int, Int)]
  def addMove(piecesColorMap: ColorMap, coordShift: (Int, Int)): List[Coord]

  override def toString = {if (color == White) "W" else "B"} + getTypeAsString

  def setPosition(newPosition: Coord): Piece

  def moveIsValid(pieceColorMap: ColorMap, toCoord: Coord) = {
    val Coord(row, col) = toCoord
    pieceColorMap(row)(col) match {
      case Some(c) => c != color
      case None => true
    }
  }

  def possibleMoves(piecesColorMap: ColorMap): List[Coord] = {
    for (shift <- directionShifts;
         move  <- addMove(piecesColorMap, shift))
    yield move
  }
}
