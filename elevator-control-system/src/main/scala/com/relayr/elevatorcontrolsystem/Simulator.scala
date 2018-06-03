package com.relayr.elevatorcontrolsystem

import com.relayr.elevatorcontrolsystem.models.{DOWN, Direction, UP}

import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object Simulator {

  def prettyPrint(color: String, message: String): Unit = {
    println(color)
    println(message)
  }


  @tailrec
  def readCapacityOfElevators(maxCapacity: Int): Int = {

    prettyPrint(Console.WHITE, s"Please enter elevator control system capacity (limited to $maxCapacity)")
    val readInput = StdIn
    val noOfElevators = Try(readInput.readInt())

    noOfElevators match {
      case Success(capacity) if (capacity > maxCapacity) => {
        prettyPrint(Console.RED, s"enter elevators up to $maxCapacity")
        readCapacityOfElevators(maxCapacity)
      }
      case Success(capacity) => capacity

      case Failure(_) => {
        println(Console.RED, "enter valid number")
        readCapacityOfElevators(maxCapacity)
      }
    }
  }

  def leftResult(msg: String, capacity: Int, ecs: ElevatorController) = {
    prettyPrint(Console.RED, msg)
    respondToClient(capacity, ecs)
  }

  def isNotNumber(x: String) =  ! (x forall Character.isDigit)

  def handlePassengerUpdateRequest(capacity: Int, ecs: ElevatorController) = {
    val readInput = StdIn
    readInput.readLine().split(" ") match {
      case inputs if(inputs forall isNotNumber) => leftResult("enter valid numbers only", capacity, ecs)
      case Array(elevator, pickupFloor) => {
        ecs.update(elevator.toInt, pickupFloor.toInt) match {
          case Left(msg) => leftResult(msg, capacity, ecs)
          case Right(cs) => respondToClient(capacity, cs)
        }

      }
    }
  }

  def handlePickUpRequest(capacity: Int, ecs: ElevatorController) = {
    val readInput = StdIn
    readInput.readLine().split(" ") match {
      case Array(pickupFloor, direction) => {
        ecs.pickup(pickupFloor.toInt, Direction(direction)) match {
          case Left(msg) => leftResult(msg, capacity, ecs)
          case Right(cs) => respondToClient(capacity, cs)
        }
      }
    }
  }

  def handleSimulationStep(capacity: Int, ecs: ElevatorController) = ecs.step() match {
    case Left(msg) => leftResult(msg, capacity, ecs)
    case Right(cs) => respondToClient(capacity, cs)
  }


  def handleStatusRequest(capacity: Int, ecs: ElevatorController) = ecs.status() match {
    case Left(msg) => leftResult(msg, capacity, ecs)
    case Right(es) => {
      val prettyEcs =
        es
          .map(e => s"" +
            s"Elevator ${e.id} - " +
            s"Current Floor ${e.currentFloor} - " +
            s"Current direction ${e.direction} - " +
            s"UP requests [${e.requests.get(UP).get.mkString(",")}] - " +
            s"DOWN requests [${e.requests.get(DOWN).get.mkString(",")}]"
          )
      prettyPrint(Console.GREEN, prettyEcs.mkString("\n"))
      respondToClient(capacity, ecs)
    }
  }


  def respondToClient(capacity: Int, ecs: ElevatorController): ElevatorController = {
    prettyPrint(
      Console.YELLOW,
      "Choose one of the options below: \n " +
        "1. status of elevators\n " +
        "2. Enter the elevator ID and destination floor \n " +
        "3. Passenger Pickup: Pickup floor and Direction(default is UP direction) \n " +
        "4. simulate system")

    val readInput = StdIn
    val input = Try(readInput.readInt())

    input match {
      case Success(1) => handleStatusRequest(capacity, ecs)
      case Success(2) => handlePassengerUpdateRequest(capacity, ecs)
      case Success(3) => handlePickUpRequest(capacity, ecs)
      case Success(4) => handleSimulationStep(capacity, ecs)
      case Success(n) => leftResult(s"option $n is not available", capacity, ecs)
      case Failure(_) => leftResult("command line reading error", capacity, ecs)
    }

  }

  def main(args: Array[String]): Unit = {
    val noOfElevators = readCapacityOfElevators(16)
    val ecs = ElevatorController(noOfElevators)

    println("Elevator Control system has started")

    ecs match {
      case Left(msg) => prettyPrint(Console.RED, msg)
      case Right(ecs) => respondToClient(noOfElevators, ecs)
    }

  }

}
