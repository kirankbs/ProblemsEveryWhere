package candydispenser

/**
  * Created by kirankumar on 03-11-2016.
  */
case class Machine(locked: Boolean, candies: Int, coins: Int)

object Machine{
  def simulateMachine1(inputs: List[Input]): State[Machine,(Int,Int)] =
    s => (s,inputs) match {
      case (Machine(_,0,_),_) => ((s.coins,s.candies),s)
      case (Machine(_,0,_),Nil) => ((s.coins,s.candies),s)
      case (Machine(false,candies,coins),Coin::is)  => simulateMachine1(is)(s)
      case (Machine(false,0,coins),Coin::is)  => simulateMachine1(is)(s)
      case (Machine(true,0,coins),Coin::is)  => simulateMachine1(is)(s)
      case (Machine(true,candies,coins),Coin::is)  => simulateMachine1(is)(Machine(false,candies,coins+1))
      case (Machine(true,candies,coins),Turn::is)  => simulateMachine1(is)(Machine(true,candies,coins))
      case (Machine(false,candies,coins),Turn::is)  => simulateMachine1(is)(Machine(true,candies-1,coins))
      case _ => ((s.coins,s.candies),s)
    }

  def simulateMachine(inputs: List[Input]): State[Machine,(Int,Int)] = inputs.map()

}