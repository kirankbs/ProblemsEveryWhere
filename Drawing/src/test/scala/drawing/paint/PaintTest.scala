package drawing.paint

import drawing.{Canvas, Colour, Coordinate}
import drawing.shape.Shape
import org.junit.{Before, Test}
import org.scalatest.Matchers._

/**
  * Created by kirankumarbs on 9/29/2016.
  */
class PaintTest {
  val canvas: Canvas = Canvas(5,5)
  val colour: Colour  = '*'

  @Test
  def itshouldDrawHorizontalLineOnCanvas(): Unit ={
    //given
    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (4,3)
    val line:Shape = Shape.line(x1y1,x2y2)
    //when
    val newCanvas: Canvas = Paint(canvas,line,colour).paint()
    //then
    println(newCanvas)
    line.draw().foreach(p => newCanvas.pixels().
      exists(p1 => (p1.coordinate() == p.coordinate() && p1.colour() == colour ) ) shouldBe true)
  }


  @Test
  def itShouldDrawVerticalLineOnCanvas(): Unit ={
    //given
    val x1y1: Coordinate = (1,0)
    val x2y2: Coordinate = (1,4)
    val line:Shape = Shape.line(x1y1,x2y2)
    //when
    val newCanvas: Canvas = Paint(canvas,line,colour).paint()
    //then
    line.draw().foreach(p => newCanvas.pixels().
      exists(p1 => p.coordinate() == p.coordinate() && p1.colour() == p.colour()) shouldBe true)
  }

  @Test
  def itShouldDrawRectangleOnCanvas(){}

  @Test
  def itShouldNotDrawLineOnCanvasForInvalidPixels(){}

  @Test
  def itShouldNotDrawRectangleOnCanvasForInvalidPixels(){}



}
