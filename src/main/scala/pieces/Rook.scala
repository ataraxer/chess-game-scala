package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesLineary


case class Rook(color: Color, hasMoved: Boolean = false)
    extends Piece with MovesLineary
{
  val directionShifts = linearShifts
}

