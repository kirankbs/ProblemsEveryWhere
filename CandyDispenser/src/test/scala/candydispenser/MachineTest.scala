package candydispenser

import org.junit.{Assert, Test}
import org.scalatest.Matchers._
/**
  * Created by kirankumar on 03-11-2016.
  */
class MachineTest {


  @Test
  def itShouldCreateMachine: Unit ={
    //Given
    val machine = Machine(true,100,0)
    //When
    //Then
    machine.coins shouldEqual 0
    machine.candies shouldEqual 100
   machine.locked shouldEqual true

  }


  @Test
  def itShouldIncreaseCoinCountInMachineAtInitialState(): Unit ={
    //Given
    val machine = Machine(true,100,0)
    //When
    val newMachineState = Machine.simulateMachine1(List(Coin))
    val (cc,machineActual) = newMachineState.run(machine)
    //Then
    cc._1 shouldEqual 1
  }

  @Test
  def itShouldIncreaseCoinInMachineAnyState: Unit ={
    //Given
    val machine = Machine(true,100,10)
    //When
    val newMachineState = Machine.simulateMachine1(List(Coin))
    val (cc,machineActual) = newMachineState.run(machine)
    //Then
    cc._1 shouldEqual 11
  }

  @Test
  def itShouldNotIncreaseCoinWhenInputIsNotACoin: Unit ={
    //Given
    val machine = Machine(true,100,10)
    //When
    val newMachineState = Machine.simulateMachine1(List(Turn))
    val (cc,machineActual) = newMachineState.run(machine)
    //Then
    cc._1 shouldEqual 10
  }

  @Test
  def itShouldNotIncreaseCoinWhenMachineIsUnlocked: Unit ={
    //Given
    val machine = Machine(false,100,10)
    //When
    val newMachineState = Machine.simulateMachine1(List(Coin))
    val (cc,machineActual) = newMachineState.run(machine)
    //Then
    cc._1 shouldEqual 10
  }

  @Test
  def itShouldNotIncreaseCoinWhenMachineIsLockedAndInputIsNotACoin: Unit ={
    //Given
    val machine = Machine(true,100,10)
    //When
    val newMachineState = Machine.simulateMachine1(List(Turn))
    val (cc,machineActual) = newMachineState.run(machine)
    //Then
    cc._1 shouldEqual 10
  }

  @Test
  def itShouldNotIncreaseCoinWhenMachineIsNotHavingAnyCandy: Unit ={
    //Given
    val machine = Machine(true,0,10)
    //When
    val newMachineState = Machine.simulateMachine1(List(Coin))
    val (cc,machineActual) = newMachineState.run(machine)
    //Then
    cc._1 shouldEqual 10
  }



  @Test
  def itShouldIncreaseCoinAndUnlockMachineWhenCandyIsAvailable: Unit ={
    //Given
    val machine = Machine(true,10,10)
    //When
    val newMachineState = Machine.simulateMachine1(List(Coin))
    val (coins,machineActual) = newMachineState.run(machine)
    //Then
    machineActual.coins shouldEqual 11
    machineActual.locked shouldEqual false
  }

  @Test
  def itShouldNotDispenseCandyIfMachineIsLocked: Unit ={
    //Given
    val machine = Machine(true,100,0)
    //When
    val newMachineState = Machine.simulateMachine1(List(Turn))
    val (candies,machineActual) = newMachineState.run(machine)
    //then
    machineActual.candies shouldEqual 100
  }

  @Test
  def itShouldNotDispenseAntCandyWhenInputIsNotTurn: Unit ={
    //Given
    val machine = Machine(true,100,0)
    //When
    val newMachineState = Machine.simulateMachine1(List(Coin))
    val (coins,machineActual) = newMachineState.run(machine)
    //then
    machineActual.candies shouldEqual 100
  }

  @Test
  def itShouldDispenseCandyWhenInputIsTurn: Unit ={
    //Given
    val machine = Machine(false,100,0)
    //When
    val newMachineState = Machine.simulateMachine1(List(Turn))
    val (candies,machineActual) = newMachineState.run(machine)
    //then
    machineActual.candies shouldEqual 99
  }

  @Test
  def itShouldDispenseCandyWhenInputIsTurnAndLockMachine: Unit = {
    //Given
    val machine = Machine(false,100,0)
    //When
    val newMachineState = Machine.simulateMachine1(List(Turn))
    val (candies,machineActual) = newMachineState.run(machine)
    //then
    machineActual.candies shouldEqual 99
    machineActual.locked shouldEqual true
  }

  @Test
  def itShouldSetCoinsAndCandiesInMachineBasedOnGivenInputs: Unit = {
    //Given
    val machine = Machine(true,100,0)
    //When
    val newMachineState = Machine.simulateMachine1(List(Coin,Turn))
    val (cc,machineActual) = newMachineState.run(machine)
    //then
    cc._1 shouldEqual 1
    cc._2 shouldEqual 99
    machineActual.locked shouldEqual true
  }


}
