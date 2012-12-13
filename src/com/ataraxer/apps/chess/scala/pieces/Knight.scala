package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord
import java.awt.image.AreaAveragingScaleFilter
import moveTypes.MovesPredefined

class Knight(color: Color, position: Coord, hasMoved: Boolean = false) extends Piece(color, position, hasMoved) with MovesPredefined {
  override def getTypeAsString = "Kn"
  override def getType = PieceType.Knight
  override def directionShifts = List(
    List(1, 2), List(1, -2), List(-1, 2), List(-1, -2),
    List(2, 1), List(2, -1), List(-2, 1), List(-2, -1)
  )

  override def addMove(piecesColorMap: Array[Array[Color]], coordShift: List[Int])
    = addPredefinedMove(piecesColorMap, coordShift)
}