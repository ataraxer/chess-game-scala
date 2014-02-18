package com.ataraxer.apps.chess.scala

import org.scalatest._


class CoordSpec extends UnitSpec {

  "A Coord" should "define coordinates on a board by row and column" in {
    val coord = Coord(1, 3)
    assert(coord.row == 1)
    assert(coord.col == 3)
  }

  it should "throw IllegalArgumentException if overflows a board size" in {
    intercept[IllegalArgumentException] {
      val coord = Coord(4, 9)
    }
    intercept[IllegalArgumentException] {
      val coord = Coord(9, 4)
    }
    intercept[IllegalArgumentException] {
      val coord = Coord(9, 9)
    }
  }

  it can "be shifted by arbitrary rows and columns number" +
         "to produce new coord if it hasn't overflown a board size" in {
    val coord = Coord(4, 4)
    val shiftedCoord = coord << (1, 3)
    assert(shiftedCoord == Some(Coord(5, 7)))
  }

  it should "result in None otherwise" in {
    val coord = Coord(4, 4)
    val shiftedCoord = coord << (5, 3)
    assert(shiftedCoord == None)
  }

}

// vim: set ts=2 sw=2 et:
