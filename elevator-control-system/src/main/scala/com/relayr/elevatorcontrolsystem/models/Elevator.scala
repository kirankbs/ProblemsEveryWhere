package com.relayr.elevatorcontrolsystem.models

case class Elevator(id: Int, currentFloor: Floor, direction: Direction, requests: PassengerRequests)

object Elevator {
  type Result = Elevator => Elevator

  def move: Result = elevator => (nextFloor andThen nextDirection) (elevator)

  def passengerRequest(goalFloor: Floor, direction: Option[Direction] = None): Result =
    elevator => (addRequestedFloor(goalFloor, direction) andThen nextDirection) (elevator)

  private def nextDirection: Result = elevator => DirectionCriteria(elevator)

  private def nextFloor: Result = elevator => {
    elevator.direction match {
      case UP => for {
        upRequests <- elevator.requests.get(elevator.direction)
        nextFloor <- upRequests.headOption
      } yield elevator.copy(currentFloor = nextFloor, requests = elevator.requests.updated(elevator.direction, upRequests.tail))
      case DOWN => for {
        downRequests <- elevator.requests.get(elevator.direction)
        nextFloor <- downRequests.lastOption
      } yield elevator.copy(currentFloor = nextFloor, requests = elevator.requests.updated(elevator.direction, downRequests.init))
      case IDLE => Some(elevator)
    }
  }.getOrElse(elevator)

  
  private def addRequestedFloor(gf: Floor, direction: Option[Direction]): Result = elevator => elevator match {
    case _ if(direction.isDefined) => updateRequests(direction.get, gf)(elevator).copy(direction = direction.get)
    case Elevator(_, cf, UP, _) if (gf > cf) => updateRequests(UP, gf)(elevator)
    case Elevator(_, cf, UP, _) if (gf < cf) => updateRequests(DOWN, gf)(elevator)
    case Elevator(_, cf, DOWN, _) if (gf < cf) => updateRequests(DOWN, gf)(elevator)
    case Elevator(_, cf, DOWN, _) if (gf > cf) => updateRequests(UP, gf)(elevator)
    case Elevator(_, cf, IDLE, _) if (gf > cf) => updateRequests(UP, gf)(elevator).copy(direction = UP)
    case Elevator(_, cf, IDLE, _) if (gf < cf) => updateRequests(DOWN, gf)(elevator).copy(direction = DOWN)
    case _ => elevator
  }

  private def updateRequests(direction: Direction, goalFloor: Floor): Result = elevator => (
    for {
      requests <- elevator.requests.get(direction)
    } yield elevator.copy(requests = elevator.requests.updated(direction, requests + goalFloor)))
    .getOrElse(elevator)
}