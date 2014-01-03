package com.ataraxer.apps.chess.scala.pieces

object PieceType extends Enumeration {
  type PieceType = Value
  val Pawn, Rook, Knight, Bishop, Queen, King = Value

  class PieceTypeValue(pieceType: Value) {

  }

  implicit def valueToPieceTypeValue(color: Value) = new PieceTypeValue(color)
}