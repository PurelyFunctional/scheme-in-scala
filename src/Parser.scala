package com.scheminscala

import scala.util.parsing.combinator.RegexParsers

object Parser extends RegexParsers {
  override val skipWhitespace = false

  private val symbol: Parser[String] = regex("[!#$%&|*+/:<=>?@^_~-]".r)
  private val spaces = rep1(" ")

  abstract class LispVal
  case class Atom(name: String) extends LispVal
  case class LispList(elems: List[LispVal]) extends LispVal
  case class DottedList(elems: List[LispVal], last: LispVal) extends LispVal
  case class Number(i: Int) extends LispVal
  case class LispString(s: String) extends LispVal
  case class Bool(b: Boolean) extends LispVal

  private val parseString = "\"" ~> rep("[^\"]".r) <~ "\"" map {
    ss: List[String] => LispString(ss.mkString)
  }

  private val letter = regex("[a-zA-Z]".r)
  
  private val digit = regex("[0-9]".r)

  private val parseAtom = (letter | symbol) ~ rep(letter | symbol | digit) map {
    case first ~ rest =>
      val atom = first + rest.mkString
      atom match {
        case "#t" => Bool(true)
        case "#f" => Bool(false)
        case _ => Atom(atom)
      }
  }

  private val parseNumber: Parser[LispVal] = rep1(digit) map {
    n => Number(n.mkString.toInt)
  }

  def parseExpr: Parser[LispVal] = parseString | parseNumber | parseAtom

  def readExpr(input: String): String = {
    parse(parseExpr, input) match {
      case NoSuccess(err, _) => "No match: \"lisp\" " + err
      case Success(_, _) => "Found value"
    }
  }
}