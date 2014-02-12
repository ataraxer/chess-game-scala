package com.ataraxer.apps.chess.scala.pieces.moveTypes

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord

/**
 * Created with IntelliJ IDEA.
 * User: Ataraxer
 * Date: 11.12.12
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
trait MovesPredefined {
  val position: Coord
  def moveIsValid(pieceColorMap: ColorMap, toCoord: Coord): Boolean

  def addMove(cm: ColorMap, cs: (Int, Int)) = addPredefinedMove(cm, cs)

  def addPredefinedMove(piecesColorMap: ColorMap, shift: (Int, Int)): List[Coord] = {
    val currentMove = position << shift
    currentMove match {
      case Some(move) =>
        if (moveIsValid(piecesColorMap, move))
          List(move)
        else Nil
      case None => Nil
    }
  }
}
