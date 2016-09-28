package drawing

import org.junit.Test
import org.junit.Assert._

/**
  * Created by kirankumar on 27-09-2016.
  */
class CanvasTest {

  @Test
  def itShouldCreateEmptyCanvas(): Unit ={
    //given
    val canvas: Canvas = Canvas(4,4)
    //when
    //then
    assertTrue(canvas.isEmpty())
  }

  @Test
  def emptyCanvasShouldGiveCanvasWithEmptyPoints(): Unit ={
    //given
    val emptyCanvas: Canvas = Canvas(5,8)
    //when
    //then
    println(emptyCanvas.pixels())
  }

  @Test
  def itShouldCreateFilledCanvas(): Unit ={
    //given
    val canvas: Canvas = Canvas(10,20)
    //when
    //then
    assertFalse(canvas.isEmpty())
  }

  @Test
  def filledCanvasShouldGiveCanvasWithColouredPixels(){}



}
