package com.relayr.elevatorcontrolsystem.models

//simple algebraic sum types to manage directions
sealed trait Direction
case object DOWN extends Direction
case object UP extends Direction
case object IDLE extends Direction

object Direction {
  def apply(direction: String): Direction = direction match {
    case "down" => DOWN
    case _ => UP
  }
  def change(d: Direction): Direction = d match {
    case UP => DOWN
    case DOWN => UP
    case IDLE => IDLE
  }
}

object DirectionCriteria {
  def apply(elevator: Elevator): Elevator = elevator match {
    case _ if (isIdle(elevator)) => elevator.copy(direction = IDLE)
    case _ if(noRequests(elevator)) => elevator.copy(direction = Direction.change(elevator.direction))
    case _ => elevator
  }

  def isIdle(elevator: Elevator): Boolean = elevator.requests.forall(_._2.isEmpty)
  def noRequests(elevator: Elevator): Boolean = elevator.requests.get(elevator.direction) match{
    case None => false
    case Some(requests) => requests.isEmpty
  }

}

