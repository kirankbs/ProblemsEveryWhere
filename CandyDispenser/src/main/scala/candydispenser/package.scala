/**
  * Created by kirankumar on 04-11-2016.
  */
package object candydispenser {

  type State[S,A] = S => (A,S)

}
