package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord

/*
 * Piece is abstract class that represents single
 * chess piece which can be located somewhere on the board
 *
 */
abstract class Piece(color: Color, position: Coord, hasMoved: Boolean) {

  def getTypeAsString: String
  def getType: PieceType.PieceType
  def directionShifts: List[List[Int]]
  def addMove(piecesColorMap: Array[Array[Color]], coordShift: List[Int]): List[Coord]

  override def toString = {{if (color == White) "W" else "B"} + getTypeAsString}

  def getColor = color
  def getPosition = position

  // TODO: replace with copy?
  def setPosition(newPosition: Coord) = {
    PieceFactory.createPiece(getType, color, newPosition, true)
  }

  def moveIsValid(pieceColorMap: Array[Array[Color]], toCoord: Coord) = {
    (toCoord != null && pieceColorMap(toCoord.row)(toCoord.col) != color)
  }

  def possibleMoves(piecesColorMap: Array[Array[Color]]): List[Coord] = {
    for (shift <- directionShifts;
         move  <- addMove(piecesColorMap, shift))
    yield move
  }

  def copy(newPosition: Coord): Piece = PieceFactory.createPiece(getType, color, newPosition)
  def copy: Piece = copy(position)

}
