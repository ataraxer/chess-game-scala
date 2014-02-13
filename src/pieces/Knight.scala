package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesPredefined

case class Knight(_color: Color, _hasMoved: Boolean = false)
    extends Piece(_color, _hasMoved) with MovesPredefined
{
  val directionShifts = List(
    (1, 2), (1, -2), (-1, 2), (-1, -2),
    (2, 1), (2, -1), (-2, 1), (-2, -1)
  )
}
