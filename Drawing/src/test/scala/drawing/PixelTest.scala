package drawing

import org.junit.Test
import org.junit.Assert._

/**
  * Created by kirankumar on 27-09-2016.
  */
class PixelTest {

  @Test
  def itShouldCreateEmptyPixel(): Unit ={
    //given
    val p:Pixel  = Pixel(10,5)
    //when
    //then
    assertTrue(p.isEmpty())
  }

  @Test
  def itShouldCreateColouredPixelByAddingColourToEmptyPixel(): Unit ={
    //given
    val emptyPixel: Pixel = Pixel(3,5)
    //when
    val clolouredPixel: Pixel = emptyPixel.fillColour('*')
    //then
    assertFalse(clolouredPixel.isEmpty())
  }

  @Test
  def emptyPixelColourShouldbeEmpty(): Unit ={
    //given
    val pixel: Pixel = Pixel(2,3)
    //when
    val expected = ' '
    val actual = pixel.colour()
    //then
    assertEquals(expected,actual)
  }

  @Test
  def itShouldCreateColouredPixel(): Unit ={
    //given
    val p:Pixel  = Pixel(10,5,'*')
    //when
    //then
    assertFalse(p.isEmpty())
  }

  @Test
  def colouredPixelSHouldGiveColour(): Unit ={
    //given
    val pixel: Pixel = Pixel(5,5,'*')
    //when
    val expected: Char = '*'
    val actual: Char  = pixel.colour()
    //then
    assertEquals(expected,actual)
  }




}
