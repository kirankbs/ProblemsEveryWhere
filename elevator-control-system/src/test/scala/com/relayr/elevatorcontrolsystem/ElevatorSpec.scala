package com.relayr.elevatorcontrolsystem

import com.relayr.elevatorcontrolsystem.models._
import org.scalatest.{FunSpec, Matchers}

import scala.collection.immutable.TreeSet

class ElevatorSpec extends FunSpec with Matchers {
  val upRequests = UP -> TreeSet[Floor]()
  val downRequests = DOWN -> TreeSet[Floor]()
  val requests: PassengerRequests = Map(upRequests, downRequests)

  describe("Testing different parts of LOOK Algorithm") {

    describe("Moving elevator") {

      it("should not move when no requests") {
        val elevatorUp = Elevator(1, 0, UP, requests)
        Elevator.move(
          elevatorUp
        ).currentFloor shouldBe 0
      }

      it("should move upwards") {
        val upRequests = UP -> TreeSet[Floor](5, 6)

        Elevator.move(
          Elevator(1, 0, UP, Map(upRequests, downRequests))
        ) shouldBe Elevator(1, 5, UP, Map(UP -> TreeSet(6), downRequests))
      }

      //TODO write custom ordering to down queue

      it("should move downwards") {
        val downRequests = DOWN -> TreeSet[Floor](1, 2)

        Elevator.move(
          Elevator(2, 5, DOWN, Map(upRequests, downRequests))
        ) shouldBe Elevator(2, 2, DOWN, Map(upRequests, DOWN -> TreeSet[Floor](1)))
      }

      it("should move and change direction to DOWN when UP requests are done") {
        val upRequests = UP -> TreeSet[Floor](5, 6)
        val downRequests = DOWN -> TreeSet[Floor](2)

        Elevator.move(
          Elevator.move(
            Elevator(1, 4, UP, Map(upRequests, downRequests))
          )) shouldBe Elevator(1, 6, DOWN, Map(UP -> TreeSet[Floor](), downRequests))
      }

      it("should move and change direction to UP when DOWN requests are done") {
        val downRequests = DOWN -> TreeSet(1, 2)
        val upRequests = UP -> TreeSet(5)

        Elevator.move(
          Elevator.move(
            Elevator(1, 4, DOWN, Map(upRequests, downRequests))
          )) shouldBe Elevator(1, 1, UP, Map(upRequests, DOWN -> TreeSet()))

      }

    }

    describe("Elevator direction") {
      it("should not change when requests are empty") {
        val elevatorIdle = Elevator(1, 0, IDLE, requests)
        Elevator.move(
          elevatorIdle
        ) shouldBe elevatorIdle
      }
      it("should not change UP direction when UP requests are in progress") {
        val upRequests = UP -> TreeSet[Floor](5, 6)
        val elevatorUP = Elevator(1, 0, UP, Map(upRequests, downRequests))
        Elevator.move(
          elevatorUP
        ) shouldBe Elevator(1, 5, UP, Map(UP -> TreeSet(6), downRequests))
      }
      it("should not change DOWN direction when DOWN requests are in progress") {
        val downRequests = DOWN -> TreeSet[Floor](5, 6)
        val elevatorDOWN = Elevator(1, 8, DOWN, Map(upRequests, downRequests))
        Elevator.move(
          elevatorDOWN
        ) shouldBe Elevator(1, 6, DOWN, Map(upRequests, DOWN -> TreeSet(5)))
      }
      it("should change to IDLE when requests are done") {
        val elevatorUP = Elevator(1, 0, UP, requests)
        Elevator.move(
          elevatorUP
        ) shouldBe elevatorUP.copy(direction = IDLE)
      }
      it("should change UP to IDLE when all requests are done") {
        val elevatorUP = Elevator(1, 0, UP, requests)
        Elevator.move(
          elevatorUP
        ) shouldBe elevatorUP.copy(direction = IDLE)
      }

      it("should change DOWN to IDLE when requests are done") {
        val elevatorDOWN = Elevator(2, 0, DOWN, requests)
        Elevator.move(
          elevatorDOWN
        ) shouldBe elevatorDOWN.copy(direction = IDLE)
      }
    }
  }


  describe("Send Pickup/Update Request elevator") {
    it("should add to UP requests when it is IDLE") {
      Elevator.passengerRequest(5)(
        Elevator(1, 0, IDLE, Map(upRequests, downRequests))
      ) shouldBe Elevator(1, 0, UP, Map(UP -> TreeSet(5), downRequests))
    }

    it("should add to DOWN requests when it is IDLE") {
      Elevator.passengerRequest(3)(
        Elevator(1, 5, IDLE, Map(upRequests, downRequests))
      ) shouldBe Elevator(1, 5, DOWN, Map(upRequests, DOWN -> TreeSet(3)))
    }

    it("should serve UP requests when serving in same direction ") {
      Elevator.passengerRequest(4)(
        Elevator(1, 2, UP, Map(UP -> TreeSet(5), downRequests))
      ) shouldBe Elevator(1, 2, UP, Map(UP -> TreeSet(4, 5), downRequests))

    }

    it("should serve DOWN requests when serving in same direction") {
      Elevator.passengerRequest(2)(
        Elevator(1, 4, DOWN, Map(upRequests, DOWN -> TreeSet(3)))
        ) shouldBe Elevator(1, 4, DOWN, Map(upRequests, DOWN -> TreeSet(2, 3)))

    }

    it("should serve DOWN requests later when serving UP requests") {
      Elevator.passengerRequest(1)(
        Elevator(1, 3, UP, Map(UP -> TreeSet(5), downRequests))
      ) shouldBe Elevator(1, 3, UP, Map(UP -> TreeSet(5), DOWN -> TreeSet(1)))
    }

    it("should serve UP requests later when serving DOWN requests") {
      Elevator.passengerRequest(4)(
        Elevator(1, 3, DOWN, Map(upRequests, DOWN -> TreeSet(1)))
      ) shouldBe Elevator(1, 3, DOWN, Map(UP -> TreeSet(4), DOWN -> TreeSet(1)))
    }
  }


}


//TODO same floor requests