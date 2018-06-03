package com.relayr.elevatorcontrolsystem

import com.relayr.elevatorcontrolsystem.models._

import scala.annotation.tailrec

case class CriteriaFacts(elevators: List[Elevator], pickupFloor: Floor, direction: Direction)

sealed trait ElevatorPickupCriteria[Elevator, Direction] {
  type Result = CriteriaFacts => Either[String, CriteriaFacts]

  def allIdle: Result

  def requestedDirectionElevators: Result

  def closest: Result

  def farthest: Result

  def idleElevators: Result

  def inwardsClosestElevators: Result = criteria => for {
    inElevators <- requestedDirectionElevators(criteria)
    result <- closest(inElevators)
  } yield result

  def outwardsFarthestElevators: Result = criteria => for {
    outElevators <- requestedDirectionElevators(criteria)
    result <- farthest(outElevators)
  } yield result

  def idleClosestElevators: Result = criteria => for {
    idleElevators <- idleElevators(criteria)
    result <- closest(idleElevators)
  } yield result

}


case object ElevatorPickupCriteria extends ElevatorPickupCriteria[Elevator, Direction] {
  def apply(elevators: List[Elevator], pickupFloor: Floor, direction: Direction): Option[List[Elevator]] = {
    val criteriaFacts = CriteriaFacts(elevators, pickupFloor, direction)

    @tailrec
    def loop(criteriaList: List[Result]): Option[List[Elevator]] = {
      criteriaList match {
        case Nil => None
        case criteria :: Nil =>
          {
            criteria(criteriaFacts) match {
              case Left(_) => None
              case Right(cf) => {
                Some(cf.elevators)
              }
            }
          }
        case criteria :: cs => {
          criteria(criteriaFacts) match {
            case Left(_) => loop(cs)
            case Right(cf) => Some(cf.elevators)
          }
        }
      }
    }

    loop(allCriteria)

  }

  private def allCriteria: List[Result] = List(
    allIdle,
    inwardsClosestElevators,
    idleElevators,
    outwardsFarthestElevators,
  )

  override def allIdle = criteriaFacts =>
    if (criteriaFacts.elevators.forall(_.direction == IDLE))
      Right(criteriaFacts)
    else
      Left("No elevators are in IDLE")

  override def requestedDirectionElevators: Result = criteriaFacts =>
    criteriaFacts.elevators.filter(_.direction == criteriaFacts.direction) match {
      case Nil => Left(s"No elevators are in ${criteriaFacts.direction}")
      case es => Right(criteriaFacts.copy(elevators = es))
    }


  override def closest = criteria =>
    if (criteria.elevators.forall(_.currentFloor < criteria.pickupFloor))
      Right(criteria.copy(elevators = List(criteria.elevators.minBy(e => math.abs(e.currentFloor - criteria.pickupFloor)))))
    else
      Left(s"No elevators are inwards for pickup floor ${criteria.direction}")

  override def farthest = criteria =>
    if (criteria.elevators.nonEmpty)
      Right(criteria.copy(elevators = List(criteria.elevators.maxBy(e => math.abs(e.currentFloor - criteria.pickupFloor)))))
    else
      Left(s"No elevators are outwards from pickup floor ${criteria.direction}")

  override def idleElevators = criteria =>
    criteria.elevators.filter(_.direction == IDLE) match {
      case Nil => Left("No elevators are IDLE")
      case es => Right(criteria.copy(elevators = es))
    }
}
