Implement a simulation of a simple candy dispenser. The machine has two types of input: You
can insert a coin, or you can turn the knob to dispense candy. It can be in one of
two states: locked or unlocked. It also tracks how many candies are left and how
many coins it contains.

The rules of the machine are as follows:
    Inserting a coin into a locked machine will cause it to unlock if there is
    any candy left.
    Turning the knob on an unlocked machine will cause it to dispense candy
    and become locked.
    Turning the knob on a locked machine or inserting a coin into an
    unlocked machine does nothing.
    A machine that is out of candy ignores all inputs.
    
The method simulateMachine should operate the machine based on the list
of inputs and return the number of coins left in the machine at the end. Note that if
the input Machine has 10 coins in it, and a net total of 4 coins are added in the
inputs, the output will be 14.    