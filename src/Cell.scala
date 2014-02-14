package com.ataraxer.apps.chess.scala

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.pieces.Piece


case class Cell(val coordinates: Coord, val piece: Option[Piece] = None) {
  def color = piece map { _.color }
  def isEmpty = (piece == None)

  override def toString: String =
    piece match {
      case Some(p) => p.toString
      case None => "---"
    }

  def == (that: Cell) = that.coordinates == this.coordinates
}

