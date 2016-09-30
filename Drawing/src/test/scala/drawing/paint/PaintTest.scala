package drawing.paint

import drawing.{Pixels, Canvas, Colour, Coordinate}
import drawing.shape.Shape
import org.junit.{Before, Test}
import org.scalatest.Matchers._

/**
  * Created by kirankumarbs on 9/29/2016.
  */
class PaintTest {
  val canvas: Canvas = Canvas(5,5)
  val colour: Colour  = '*'
  val expectedTrue    = true
  val expectedFalse   = false

  @Test
  def itshouldDrawHorizontalLineOnCanvas(): Unit ={
    //given
    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (4,3)
    val shape:Shape = Shape.line(x1y1,x2y2)
    //when
    val newCanvas: Canvas = Paint(canvas,shape,colour).paint()
    //then
    isShapeInCanvas(shape,newCanvas,expectedTrue)

  }



  @Test
  def itShouldDrawVerticalLineOnCanvas(): Unit ={
    //given
    val x1y1: Coordinate = (1,0)
    val x2y2: Coordinate = (1,4)
    val shape:Shape = Shape.line(x1y1,x2y2)
    //when
    val newCanvas: Canvas = Paint(canvas,shape,colour).paint()
    //then
    isShapeInCanvas(shape,newCanvas,expectedTrue)
  }

  @Test
  def itShouldDrawRectangleOnCanvas(): Unit ={
    //given
    val x1y1: Coordinate = (1,0)
    val x2y2: Coordinate = (4,4)
    val shape:Shape = Shape.rect(x1y1,x2y2)
    //when
    val newCanvas: Canvas = Paint(canvas,shape,colour).paint()
    //then
    isShapeInCanvas(shape,newCanvas,expectedTrue)
  }

  @Test(expected = classOf[Error])
  def itShouldNotDrawLineOutsideCanvas1(): Unit ={
    //given
    val x1y1: Coordinate = (1,10)
    val x2y2: Coordinate = (4,10)
    val shape:Shape = Shape.line(x1y1,x2y2)
    //when
    val newCanvas: Canvas = Paint(canvas,shape,colour).paint()
    //then
  }
  @Test(expected = classOf[Error])
  def itShouldNotDrawLineOutsideCanvas2(): Unit ={
    //given
    val x1y1: Coordinate = (1,5)
    val x2y2: Coordinate = (10,5)
    val shape:Shape = Shape.line(x1y1,x2y2)
    //when
    val newCanvas: Canvas = Paint(canvas,shape,colour).paint()
    //then
  }

  @Test(expected = classOf[Error])
  def itShouldNotDrawRectangleOutsideCanvas1(): Unit ={
    //given
    val x1y1: Coordinate = (1,10)
    val x2y2: Coordinate = (4,10)
    val shape:Shape = Shape.rect(x1y1,x2y2)
    //when
    val newCanvas: Canvas = Paint(canvas,shape,colour).paint()
    //then
  }

  @Test(expected = classOf[Error])
  def itShouldNotDrawRectangleOutsideCanvas2(): Unit ={
    //given
    val x1y1: Coordinate = (1,5)
    val x2y2: Coordinate = (40,5)
    val shape:Shape = Shape.rect(x1y1,x2y2)
    //when
    val newCanvas: Canvas = Paint(canvas,shape,colour).paint()
    //then
  }


  def isShapeInCanvas(shape:Shape, newCanvas: Canvas,expected: Boolean) = {
    shape.draw().foreach(p => newCanvas.pixels().
      exists(p1 => (p1.coordinate() == p.coordinate() && p1.colour() == colour ) ) shouldBe expected)
  }

}
