package candydispenser

/**
  * Created by kirankumar on 03-11-2016.
  */
case class Machine(locked: Boolean, candies: Int, coins: Int)

object Machine{
  def simulateMachine(inputs: List[Input]): State[Machine,Int] =
    s => (s,inputs) match {
      case (Machine(_,0,_),_) => (s.coins,s)
      case (Machine(_,0,_),Nil) => (s.coins,s)
      case (Machine(false,candies,coins),Coin::is)  => (coins,s)
      case (Machine(false,0,coins),Coin::is)  => (coins,s)
      case (Machine(true,0,coins),Coin::is)  => (coins,s)
      case (Machine(true,candies,coins),Coin::is)  => (coins+1,Machine(false,candies,coins+1))
      case (Machine(true,candies,coins),Turn::is)  => (coins,Machine(true,candies,coins))
      case (Machine(false,candies,coins),Turn::is)  => (coins,Machine(true,candies-1,coins))
      case _ => (s.coins,s)
    }

}