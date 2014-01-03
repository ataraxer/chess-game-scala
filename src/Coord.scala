package com.ataraxer.apps.chess.scala

/*
 * Coord
 *
 * Coord class represents a two-dimensional coordinate on a chess board
 * where first values determines row and second one — column.
 */
case class Coord(row: Int, col: Int) {
  // constant board size value for both dimensions
  val boardSize = 8

  // boundaries check
  if (row < 0 || row >= boardSize || col < 0 || col >= boardSize) {
    throw new IllegalArgumentException
  }

  // represents coordinate as a string for printing and debugging
  override def toString: String = format("Coord: {%d, %d}", row, col)

  // equals operator for coordinate comparison
  def == (c: Coord): Boolean = {
    c match {
      case c: Coord =>
        c.row == this.row &&
        c.col == this.col
      // dirty hack to prevent crashes when checking for equality to null
      // TODO: check if it's necessary
      case _ => false
    }
  }

  def copy = new Coord(row, col)

  // shift operation/method for generating new coordinate by shifting
  // existing coordinate's dimensions values by given delta
  def << (sh: List[Int]) = shift(sh(0), sh(1))
  def << (tor: Int, toc: Int) = shift(tor, toc)

  def shift(sh: List[Int]): Coord = shift(sh(0), sh(1))
  def shift(tor: Int, toc: Int): Coord = {
    try {
      new Coord(row + tor, col + toc)
    } catch {
      case e: IllegalArgumentException => null
    }
  }


}