package candydispenser

import org.junit.Test
import org.scalatest.Matchers._
/**
  * Created by kirankumar on 03-11-2016.
  */
class InputTest {

  @Test
  def itShouldCreateCoinInput: Unit ={
    //given
    val c = Input.coin
    //when
    //then
    c shouldEqual Coin
  }

  @Test
  def itShouldCreateTurnInput: Unit ={
    //given
    val c = Input.turn
    //when
    //then
    c shouldEqual Turn
  }

}
