package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesLineary

case class Rook(_color: Color, _hasMoved: Boolean = false)
    extends Piece(_color, _hasMoved) with MovesLineary
{
  val directionShifts = linearShifts
}
