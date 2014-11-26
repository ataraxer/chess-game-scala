package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import com.ataraxer.apps.chess.scala.pieces.moveTypes.MovesPredefined


case class King(color: Color, hasMoved: Boolean = false)
    extends Piece with MovesPredefined
{
  val directionShifts = List(
    (0, 1), ( 0, -1), ( 1,  1), (-1,  1),
    (1, 0), (-1,  0), (-1, -1), ( 1, -1)
  )
}

