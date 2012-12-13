package com.ataraxer.apps.chess.scala.pieces

import com.ataraxer.apps.chess.scala.pieces.PieceType._
import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.Coord

/**
 * Created with IntelliJ IDEA.
 * User: Ataraxer
 * Date: 06.12.12
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
object PieceFactory {

  def createPiece(pieceType: PieceType, color: Color, position: Coord, hasMoved: Boolean = false) = {
    pieceType match {
      case PieceType.Pawn => new Pawn(color, position, hasMoved)
      case PieceType.Rook => new Rook(color, position, hasMoved)
      case PieceType.Knight => new Knight(color, position, hasMoved)
      case PieceType.Bishop => new Bishop(color, position, hasMoved)
      case PieceType.Queen => new Queen(color, position, hasMoved)
      case PieceType.King => new King(color, position, hasMoved)
    }
  }

}