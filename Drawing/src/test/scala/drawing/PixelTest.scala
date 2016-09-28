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
    val co:Coordinate = (10,5)
    val p:Pixel  = Pixel((co))
    //when
    //then
    assertTrue(p.isColoured())
  }

  @Test
  def itShouldCreateColouredPixelByAddingColourToEmptyPixel(): Unit ={
    //given
    val co: Coordinate = (3,5)
    val emptyPixel: Pixel = Pixel(co)
    //when
    val clolouredPixel: Pixel = emptyPixel.fillColour('*')
    //then
    assertFalse(clolouredPixel.isColoured())
  }

  @Test
  def emptyPixelColourShouldbeEmpty(): Unit ={
    //given
    val co: Coordinate = (2,3)
    val pixel: Pixel = Pixel(co)
    //when
    val expected: Colour = ' '
    val actual: Colour = pixel.colour()
    //then
    assertEquals(expected,actual)
  }

  @Test
  def itShouldCreateColouredPixel(): Unit ={
    //given
    val co: Coordinate  = (10,5)
    val p:Pixel  = Pixel(co,'*')
    //when
    //then
    assertFalse(p.isColoured())
  }

  @Test
  def colouredPixelSHouldGiveColour(): Unit ={
    //given
    val co: Coordinate  = (5,5)
    val pixel: Pixel = Pixel(co,'*')
    //when
    val expected: Colour = '*'
    val actual: Colour  = pixel.colour()
    //then
    assertEquals(expected,actual)
  }




}
