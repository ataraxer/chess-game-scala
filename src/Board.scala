package com.ataraxer.apps.chess.scala

import com.ataraxer.apps.chess.scala.Color._
import com.ataraxer.apps.chess.scala.pieces._

/*
 * Class board represents a chess playing board which consists of 64 cells
 * aligned in a 8x8 grid. Every cell may be empty or contain only one piece.
 *
 * Cells are represented as two-dimensional array of cells.
 *
 * Board is immutable — any change in a board will in fact result in a new
 * instance of board with given changes applied during instantiation.
 */
class Board(inCells: Array[Array[Cell]] = null) {

  // cells are either initialized with given set
  // or with default layout for a new game of chess
  val cells: Array[Array[Cell]] =
    if (inCells == null) defaultLayout else inCells

  /*
   * Generates default layout of pieces for a new game of chess.
   * Note that white pieces are always located in a top row and black
   * pieces — in a bottom one.
   */
  private def defaultLayout: Array[Array[Cell]] = {
    val defaultLayout
      = List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook)

    def generateLastRow(row: Int, color: Color) =
      for ((pieceType, i) <- defaultLayout.zipWithIndex)
        yield new Cell(Coord(row, i), pieceType(color, Coord(row, i), false))

    def generatePawnRow(row: Int, color: Color) =
      for (i <- 0 to 7)
        yield new Cell(Coord(row, i), Pawn(color, Coord(row, i)))

    def generateRow(row: Int) =
      row match {
        case 0 => generateLastRow(row, White)
        case 1 => generatePawnRow(row, White)
        case 6 => generatePawnRow(row, Black)
        case 7 => generateLastRow(row, Black)
        case _ => for (i <- 0 to 7) yield new Cell(Coord(row, i))
      }

    (for (i <- 0 to 7) yield generateRow(i).toArray).toArray
  }

  /* PUBLIC INTERFACE */
  /*
   * Accessor for a specified cell which takes in's coordinate
   * as an argument of Coord type, rather then two integers
   * representing those coordinates.
   */
  def getCell(c: Coord)  = cells(c.row)(c.col)
  /*
   * Accessor for a piece in a cell specified by it's coordinate.
   */
  def getPiece(c: Coord) = getCell(c).getPiece

  /*
   * Generate a 'color map' of a board — a two-dimensional array which
   * contains information about color of piece in each cell only.
   * If cell doesn't contains a piece it's value in a color map will be
   * equal to null.
   */
  def piecesColorMap =
    for (row <- cells) yield row map { _.color }

  /*
   * Moves piece from one cell to another.
   *
   * Takes coordinates of the cell containing a piece and a cell where
   * it should be moved. Correctness of move is being checked by
   * generating a list of all possible moves for the piece and looking
   * for resulting coordinate there. If it's present — move is valid.
   *
   * Note that due to the fact that board is immutable this method
   * returns a new board with piece in a new position, rather than
   * applying them in a current board itself.
   */
  def movePiece(from: Coord, to: Coord): Board = {
    val piece = getPiece(from)

    // TODO: replaces with exception
    if (piece == null) {
      println("Piece cell is empty!")
      return null
    }

    /* TODO: add exceptional moves processing
     * Pawn promotion;
     * Capture in passing;
     * Castling;
     */
    val moves = piece.possibleMoves(piecesColorMap)

    // TODO: replace with exception
    if (!moves.contains(to)) {
      println("Invalid move!")
      return null
    }

    val newPiece = piece.setPosition(to)
    this.copy(List(
      Cell(to, newPiece),
      Cell(from, null)
    ))
  }

  /*
   * Copies a current board including all containing cells and pieces,
   * thus performing a deep copy.
   *
   * Takes a list of 'updated cells' which contains predefined cells
   * which may or may not contain pieces in them, that will replace
   * cells of a current board with the same coordinates.
   */
  def copy(updatedCells: List[Cell] = null) = {
    def copyCell(c: Cell): Cell = {
      val updated = updatedCells filter { _ == c }
      if (updated.nonEmpty) updated.head else c.copy
    }

    val newCells = for (row <- cells) yield row map { copyCell(_) }

    new Board(newCells)
  }

  /*
   * Returns a list of all pieces of chosen color, provided as an argument.
   */
  def getPieces(c: Color) = {
    for (row <- cells;
         cell <- row
         if !cell.isEmpty && cell.color == c) 
      yield cell.getPiece
  }

  /* Allows to print board in console in a user-readable way. */
  override def toString: String = {
    var buffer = ""

    var rowIndex = 0
    for (row <- cells) {
      buffer += format("%d ", rowIndex)
      rowIndex += 1
      for (cell <- row)
        buffer += cell.toString() + " "
      buffer += "\n"
    }

    buffer += "   0   1   2   3   4   5   6   7\n"
    buffer + "*********"
  }

}
