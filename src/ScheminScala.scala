package com.scheminscala

object ScheminScala {
  def main(args: Array[String]): Unit = {
    println(Parser.readExpr(args(0)))
  }
}