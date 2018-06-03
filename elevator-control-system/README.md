# Programming Task

At a high level task is solved using --- algorithm. The text below explains the different parts of the solution and the algorithm used.

## More about the solution

**Domain Objects**
* Floor
* Elevator
* Direction
* ElevatorController
     
`Elevator` apart from containing elevator information, it also has companion object to have behaviours.

`ElevatorController` apart from containing elevators information, it also deals with few more things as per below: 
   * validations.
   * Creation. 
   * Companion object has behaviours. 
   * `ElevatorSelectionCriteria` to manage all Elevator selection criteria
 
`Direction` is `SUM Type` to manage elevator directions. `DirectionCriteria` to handle Elevator direction criteria.

Validations are managed using simple `algebraic types`

#Algorithm details
**Elevator Motion** 
* LOOK Algorithm -- identical to SSTF, avoid starvation and look ahead also

* Bravely, algorithm is divided into two code blocks which may compromise performance to some extent.
* On the other hand, it increases readability and gives opportunity to test and validate these parts in isolation and easily scalable
* This would have been a simple FCFS, SSTF or SCAN but LOOK are handling following conditions
* The two functions are as follows
    * `move` is again divided into two code blocks
        * `nextFloor` calculating next floor help to change elevator current floor.
            * Elevator will reach only till requested floors instead of travelling till end of Disks.
        * `nextDirection` Direction criteria is separated from elevator behaviour so that we can upgrade algorithm independently. 
            * Elevator change it's direction to serve opposite direction requests.
            * Elevator goes to IDLE state after serving all requests.
    * `request`
        * Elevator handles requests if it is in same direction.

**Elevator Selection**
* Elevator selection criteria is separated from control system so that optimization can be done easily.
* Elevators selection interface contains pure functions and composed functions.
* Different selection criteria as follows:
    * Pick closest elevator from all IDLE elevators.
    * Pick the elevator which is coming inwards and closest.
    * Pick any idle elevator before checking for elevators who are going outwards direction 
    * Pick the elevator which is going outwards and farthest.
    note: Many requirements can be covered independently in selection criteria interface without touching 
          core behaviours of elevator control system.

       
## Tests
* `ElevatorSpec`: the spec is divided into 3 parts. Contains all tests related to elevator behaviours
    * first one is for testing elevator movement.
    * second is for elevator direction change
    * third is for testing prioritize elevator requests 
* `ElevatorControllerSystemSpec`: the spec is divided into 2 parts
    * Update requests to specific elevator
    * Pickup requests to control system
      
       
## Things which can be considered     
* Elevator requests are stored in TreeSet as it helps to eliminate duplicate requests and ordering same direction requests.
* Elevator updates can be done through Lenses.
* Elevator selection algorithm performance can be improved by following strategies:
    * reducing the number of iterations or combining criteria but it costs you clean and comprehensive code.
    * through parallel computation.
* validation done through Either. Left and Right `SUM Types` helps to differentiate messages and model separately and composition.
* Simple string split is used, as parser combinator would be overkill.  

    
## Other Highlights
* One of the major reason for using `Scala 2.12` because it gave flatMap on Either one of the sweet things.
* package object contains the type alias such as `PassengerRequests` which in turn is a simple 
  `Map` with key as direction and TreeSet to store requests for that direction.
* function doing recursion are mostly tailRec  


## How to run the application
**$> ./sbt run**
Once the application is running, it'll behave as stated in the programming task. few examples below:
   * Please enter elevator control system capacity (limited to 16)
        * `4`
   * Choose one of the options below: 
   1. status of elevators
        * `1`
   2. Enter the elevator ID and destination floor
        * `2`
        * `1 10` 
   3. Passenger Pickup: Pickup floor and Direction(default is UP direction)
        * `3`
        * `7 down`
   4. simulate system
        * `4`

## How to run tests
**$> ./sbt test**

### Hope the design is self explanatory :-)


