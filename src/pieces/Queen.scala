package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesLineary

case class Queen(_color: Color, _position: Coord, _hasMoved: Boolean = false)
    extends Piece(_color, _position, _hasMoved)
    with MovesLineary
{
  val directionShifts = linearShifts ++ diagonalShifts

  override def setPosition(position: Coord) =
    Queen(color, position, hasMoved)
}
