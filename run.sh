#!/usr/bin/env bash

scalac -d out src/*.scala src/*/*.scala src/*/*/*.scala
scala -cp out com.ataraxer.apps.chess.scala.Chess
