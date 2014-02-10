package com.ataraxer.apps.chess.scala

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.pieces._

case class Cell(val coordinates: Coord, val piece: Option[Piece]) {
  def this(c: Coord) = this(c, None)

  def color = piece map { _.color }
  def isEmpty = (piece == None)
  def copy = Cell(coordinates, piece)

  override def toString: String = {
    piece match {
      case Some(p) => p.toString()
      case None => "---"
    }
  }
}
