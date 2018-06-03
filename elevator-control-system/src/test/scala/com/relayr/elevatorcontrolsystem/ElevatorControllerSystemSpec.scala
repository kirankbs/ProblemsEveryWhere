package com.relayr.elevatorcontrolsystem

import com.relayr.elevatorcontrolsystem.models._
import org.scalatest.{FunSpec, Matchers}

import scala.collection.immutable.TreeSet

class ElevatorControllerSystemSpec extends FunSpec with Matchers {

  describe("Update elevator") {
    val upRequests = UP -> TreeSet[Floor]()
    val downRequests = DOWN -> TreeSet[Floor]()
    val elevator1 = Elevator(1, 0, IDLE, Map(upRequests, downRequests))
    val elevator2 = Elevator(2, 5, IDLE, Map(upRequests, downRequests))
    val elevators = List(elevator1, elevator2)
    val elevatorController = ElevatorController(2, elevators)

    it("should validate elevator control system capacity") {
      val capacity = 16
      ElevatorController(capacity, elevators) shouldBe Left(s"Please configure $capacity elevators")

    }
    it("should validate request for unknown elevator") {
      elevatorController.flatMap(
        _.update(10, 5).flatMap(
          _.status())) shouldEqual Left("Requested invalid Elevator")
    }

    it("should update requested elevator") {
      val expected =
        Right(
          List(Elevator(1, 0, UP, Map(UP -> TreeSet(5), downRequests)), elevator2)
        )

      elevatorController.flatMap(
        _.update(1, 5).flatMap(
          _.status())) shouldBe expected
    }

    it("should update and move requested elevator") {
      val expected =
        Right(
          List(Elevator(1, 5, UP, Map(UP -> TreeSet(6), downRequests)), elevator2)
        )
      elevatorController.flatMap(
        _.update(1, 5).flatMap(
          _.update(1, 6).flatMap(
            _.step().flatMap(
              _.status())))) shouldBe expected
    }

    it("should update duplicate requests and move requested elevator") {
      val expected =
        Right(
          List(Elevator(1, 5, UP, Map(UP -> TreeSet(6), downRequests)), elevator2)
        )
      elevatorController.flatMap(
        _.update(1, 5).flatMap(
          _.update(1, 5).flatMap(
            _.update(1, 6).flatMap(
              _.step().flatMap(
                _.status()))))) shouldBe expected
    }

    it("should elevator serve same direction request") {
      val expected =
        Right(
          List(elevator1, Elevator(2, 3, DOWN, Map(upRequests, DOWN -> TreeSet(2))))
        )

      elevatorController.flatMap(
        _.update(2, 2).flatMap(
          _.update(2, 3).flatMap(
            _.step().flatMap(
              _.status()
            )))) shouldBe expected
    }

    it("should elevator serve same direction and opposite direction requests") {
      val expected =
        Right(
          List(elevator1, Elevator(2, 2, UP, Map(UP -> TreeSet(7), DOWN -> TreeSet())))
        )

      elevatorController.flatMap(
        _.update(2, 2).flatMap(
          _.update(2, 3).flatMap(
            _.step().flatMap(
              _.update(2, 7).flatMap(
                _.step().flatMap(
                  _.status()
                )))))) shouldEqual expected
    }

    it("should update multiple elevators and move") {
      val expected =
        Right(
          List(
            Elevator(1, 5, DOWN, Map(UP -> TreeSet(), DOWN -> TreeSet(3))),
            Elevator(2, 3, DOWN, Map(UP -> TreeSet(5), DOWN -> TreeSet(1)))
          )
        )

      elevatorController.flatMap(
        _.update(1, 5).flatMap(
          _.update(1, 4).flatMap(
            _.step().flatMap(
              _.update(2, 1).flatMap(
                _.update(1, 3).flatMap(
                  _.update(2, 3).flatMap(
                    _.step().flatMap(
                      _.update(2, 5).flatMap(
                        _.status()
                      ))))))))) shouldEqual expected
    }
  }

  describe("Elevator pickup algorithm") {
    val upRequests = UP -> TreeSet[Floor]()
    val downRequests = DOWN -> TreeSet[Floor]()
    val e1 = Elevator(1, 0, IDLE, Map(upRequests, downRequests))
    val e2 = Elevator(2, 0, IDLE, Map(upRequests, downRequests))
    val e3 = Elevator(3, 0, IDLE, Map(upRequests, downRequests))
    val e4 = Elevator(4, 0, IDLE, Map(upRequests, downRequests))
    val capacity = 4
    val es = List(e1, e2, e3, e4)
    val ecs = ElevatorController(capacity, es)
    it("should assign request to IDLE elevator ") {
      val expected = Right(List(Elevator(1, 0, UP, Map(UP -> TreeSet(3), downRequests)), e2, e3, e4))

      ecs.flatMap(
        _.pickup(3, UP).flatMap(
          _.status())) shouldBe expected
    }
    it("should assign request to  inwards and nearest elevator") {
      //given
      val elevators =
        ecs.flatMap(
          _.update(2, 5).flatMap(
            _.step().flatMap(
              _.update(3, 1))))

      val expected =
        ecs.flatMap(
          _.update(2, 5).flatMap(
            _.step().flatMap(
              _.update(3, 1).flatMap(
                _.update(3, 4).flatMap(
                  _.status())))))
      //when
      elevators.flatMap(
        _.pickup(4, UP).flatMap(
          _.status())) shouldBe expected
    }

    it("should assign request to Outwards and farthest elevator") {
      //given
      val ecs = ElevatorController(2, List(e2, e3))
      val elevators =
        ecs.flatMap(
          _.update(2, 6).flatMap(
            _.update(2, 7).flatMap(
              _.update(3, 10).flatMap(
                _.update(3, 9).flatMap(
                  _.step())))))

      val expected =
        ecs.flatMap(
          _.update(2, 6).flatMap(
            _.update(2, 7).flatMap(
              _.update(3, 10).flatMap(
                _.update(3, 9).flatMap(
                  _.step().flatMap(
                    _.update(3, 5, Some(UP)).flatMap(
                      _.status().map(_.sortBy(_.id)))))))))
      //when
      elevators.flatMap(
        _.pickup(5, UP).flatMap(
          _.status().map(_.sortBy(_.id)))) shouldBe expected
    }

    //TODO remaining IDLE elevators coverage
    it("should assign request to IDLE and closest elevator") {
      val elevators =
        ecs.flatMap(
          _.update(2, 10).flatMap(
            _.update(3, 4).flatMap(
              _.step().flatMap(
                _.update(2, 7).flatMap(
                  _.update(3, 3))))))

      val expected =
        ecs.flatMap(
          _.update(2, 10).flatMap(
            _.update(3, 4).flatMap(
              _.step().flatMap(
                _.update(2, 7).flatMap(
                  _.update(3, 3).flatMap(
                    _.update(1, 7).flatMap(
                      _.status().map(_.sortBy(_.id)))))))))

      elevators.flatMap(
        _.pickup(7, UP).flatMap(
          _.status().map(_.sortBy(_.id)))) shouldBe expected

    }
  }


}

//elevator capacity testing
//validation tests
