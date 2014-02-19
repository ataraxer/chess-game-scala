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
object Board {
  type Layout = Seq[Seq[Cell]]

  class NoPieceAtCell extends Exception

  val sideSize = 8
  val defaultLayout = makeDefaultLayout

  /*
   * Generates default layout of pieces for a new game of chess.
   * Note that white pieces are always located in a top row and black
   * pieces — in a bottom one.
   */
  private def makeDefaultLayout: Layout = {
    val defaultLayout
      = List(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook)

    def generateLastRow(row: Int, color: Color) =
      for ((pieceType, i) <- defaultLayout.zipWithIndex)
        yield Cell(Coord(row, i), Some(pieceType(color, false)))

    def generatePawnRow(row: Int, color: Color) =
      for (i <- 0 to 7)
        yield Cell(Coord(row, i), Some(Pawn(color)))

    def generateRow(row: Int) =
      row match {
        case 0 => generateLastRow(row, White)
        case 1 => generatePawnRow(row, White)
        case 6 => generatePawnRow(row, Black)
        case 7 => generateLastRow(row, Black)
        case _ => for (i <- 0 to 7)
          yield Cell(Coord(row, i))
      }

    (0 to 7) map generateRow
  }
}


case class Board(cells: Board.Layout = Board.defaultLayout) {
  /*
   * Method apply allowes to access a boards cell similary to Scala
   * collections (like multi-dimensional array)
   */
  def apply(row: Int)(column: Int): Cell = cells(row)(column)
  def apply(cell: Coord): Cell = apply(cell.row)(cell.col)


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
  def movePiece(from: Coord, to: Coord): Board =
    this(from).piece match {
      case Some(piece) => piece.move(this, from, to)
      case None => throw new Board.NoPieceAtCell
    }


  /*
   * Copies a current board including all containing cells and pieces,
   * thus performing a deep copy.
   *
   * Takes a list of 'updated cells' which contains predefined cells
   * which may or may not contain pieces in them, that will replace
   * cells of a current board with the same coordinates.
   */
  def update(updatedCells: List[Cell]) = {
    def copyCell(c: Cell) = updatedCells.find(_ == c).getOrElse(c)

    val newCells = for (row <- cells)
      yield row map copyCell

    Board(newCells)
  }


  /*
   * Returns all cells sequentially.
   */
  def allCells = for (row <- cells; cell <- row) yield cell


  /*
   * Returns a list of all pieces of chosen color, provided as an argument.
   */
  def piecesOfColor(color: Color) =
    allCells filter { _.color == Some(color) }


  /*
   * Allows to print board to console in a user-readable way.
   */
  override def toString: String = {
    var lines =
      for ((row, i) <- cells.zipWithIndex)
        yield (i.toString ++: row) mkString " "

    var numbers = "\n   0   1   2   3   4   5   6   7\n"

    "\n" + (lines mkString "\n") + numbers
  }
}

