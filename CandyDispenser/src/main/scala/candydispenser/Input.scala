package candydispenser

/**
  * Created by kirankumar on 03-11-2016.
  */
sealed trait Input
case object Coin extends Input
case object Turn extends Input

object Input {
  def coin: Input = Coin
  def turn: Input = Turn
}