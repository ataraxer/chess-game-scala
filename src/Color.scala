package com.ataraxer.apps.chess.scala

object Color extends Enumeration {
  type Color = Value

  val White, Black = Value

  class ColorValue(color: Value) {
    def unary_! () = color match {
      case White => Black
      case Black => White
    }

    def shortName = color match {
      case White => "W"
      case Black => "B"
    }

    def opposite = unary_!()
  }

  implicit def valueToColorValue(color: Value) =
    new ColorValue(color)
}

