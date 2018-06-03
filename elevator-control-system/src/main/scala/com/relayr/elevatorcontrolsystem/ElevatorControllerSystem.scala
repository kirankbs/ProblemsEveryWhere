package com.relayr.elevatorcontrolsystem

import com.relayr.elevatorcontrolsystem.models._

import scala.annotation.tailrec
import scala.collection.immutable.TreeSet

sealed trait ElevatorControlSystem[ElevatorController, Elevator, Direction] {

  type Elevators = List[Elevator]
  type Validate[A] = Either[String, A]
  type Result = Validate[ElevatorController]

  def status(): Either[String, Elevators]

  def update(id: ElevatorId, goalFloor: Floor, direction: Option[Direction]): Result

  def pickup(pickUpFloor: Floor, direction: Direction): Result

  def step(): Result
}

class ElevatorController(capacity: Int, elevators: List[Elevator])
  extends ElevatorControlSystem[ElevatorController, Elevator, Direction] {

  override def status(): Validate[Elevators] = Right(elevators)

  override def update(elevatorId: ElevatorId, goalFloor: Floor, direction: Option[Direction] = None): Result = {
    @tailrec
    def loop(acc: Elevators, elevators: Elevators): Result = {
      elevators match {
        case e :: es if (e.id == elevatorId) => ElevatorController(capacity, acc ++ (Elevator.passengerRequest(goalFloor, direction)(e) :: es))
        case e :: es => loop(e :: acc, es)
        case Nil => Left("Requested invalid Elevator")
      }
    }

    loop(List(), elevators)
  }

  def step(): Result = ElevatorController(capacity, elevators.map(e => Elevator.move(e)))

  //TODO throws exception if lift is empty
  override def pickup(pickUpFloor: Floor, direction: Direction): Result =
    ElevatorPickupCriteria(elevators, pickUpFloor, direction).getOrElse(elevators) match {
      case Nil => Left("No elevators are available")
      case e::es => {
        update(e.id, pickUpFloor, Some(direction))
      }
    }

}

object ElevatorController{
  def apply(capacity: Int, elevators: List[Elevator]): Either[String, ElevatorController] =
    if(capacity == elevators.length)
      Right(new ElevatorController(capacity, elevators))
    else Left(s"Please configure $capacity elevators")

  def apply(capacity: Int): Either[String, ElevatorController] = {
    val elevators: List[Elevator] = (1 to capacity).map(n => Elevator(n, 0, IDLE, Map(UP -> TreeSet(), DOWN -> TreeSet()))).toList
    ElevatorController(capacity ,elevators)
  }
}
