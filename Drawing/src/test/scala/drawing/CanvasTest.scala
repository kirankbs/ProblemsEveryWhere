package drawing

import org.junit.Test
import org.junit.Assert._
import org.scalatest.Matchers._
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
  def emptyCanvasShouldGiveCanvasWithEmptyPixels(): Unit ={
    //given
    val emptyCanvas: Canvas = Canvas(3,2)
    //when
    val actual = emptyCanvas.pixels()
    //then
    ((0 to 3) map (x => (0 to 2) map (y => actual.exists(p => (x,y) == p.coordinate()) shouldBe true)))
    actual.foreach(e => e.isColoured() shouldBe true)
  }

  @Test
  def filledCanvasShouldGiveCanvasWithColouredPixels(){}




}
