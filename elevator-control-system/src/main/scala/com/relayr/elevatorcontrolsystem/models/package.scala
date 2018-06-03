package com.relayr.elevatorcontrolsystem

import scala.collection.immutable.TreeSet

package object models {

  type ElevatorId = Int
  type Floor = Int
  type PassengerRequests = Map[Direction, TreeSet[Floor]]


}
