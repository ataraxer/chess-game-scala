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

  def moveIsValid(pieceColorMap: ColorMap, toCoord: Coord) =
    toCoord match {
      case Coord(row, col) => pieceColorMap(row)(col) match {
        case Some(c) => c != color
        case None => true
      }
      case _ => false
    }

  def possibleMoves(piecesColorMap: ColorMap): List[Coord] = {
    for (shift <- directionShifts;
         move  <- addMove(piecesColorMap, shift))
    yield move
  }
}
