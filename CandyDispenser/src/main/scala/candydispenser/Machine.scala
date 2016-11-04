package candydispenser

/**
  * Created by kirankumar on 03-11-2016.
  */
case class Machine(locked: Boolean, candies: Int, coins: Int)

object Machine{
  def simulateMachine1(inputs: List[Input]): State[Machine,(Int,Int)] =
    for {
      _ <- State.sequence(inputs.map(i => State.modify((s: Machine) => (s,i) match {
        case (Machine(_,0,_),_) => s
        case (Machine(false,candies,coins),Coin)  =>s
        case (Machine(false,0,coins),Coin)  => s
        case (Machine(true,0,coins),Coin)  => s
        case (Machine(true,candies,coins),Coin)  => Machine(false,candies,coins+1)
        case (Machine(true,candies,coins),Turn)  => Machine(true,candies,coins)
        case (Machine(false,candies,coins),Turn)  => Machine(true,candies-1,coins)
        case _ => s
      })))
      s <- State.get
    } yield (s.coins, s.candies)
}

//State(s => (List[...],s)).flatMap(_ => State.get)