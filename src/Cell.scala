package com.ataraxer.apps.chess.scala

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.pieces.{PieceFactory, Piece}
import com.ataraxer.apps.chess.scala.pieces.PieceType._

case class Cell(coordinates: Coord, piece: Piece) {
  def this(c: Coord) = this(c, null)
  def this(c: Coord, pieceType: PieceType, color: Color)
    = this(c, PieceFactory.createPiece(pieceType, color, c))

  def color = if (!isEmpty) piece.getColor else null
  def getCoordinates = coordinates
  def getPiece = piece

  override def toString: String = {
    if (piece != null) piece.toString() else "---"
  }

  def isEmpty = (piece == null)

  def copy = Cell(coordinates, piece)

  def == (c: Cell): Boolean = {
    c match {
      case c: Cell => c.getCoordinates == coordinates
      // dirty hack to prevent crashes when checking for equality to null
      case _ => false
    }
  }
}
