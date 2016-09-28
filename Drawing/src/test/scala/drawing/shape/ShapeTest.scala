package drawing.shape

import drawing._
import org.junit.Test
import org.scalatest.Matchers._

/**
  * Created by kirankumarbs on 9/28/2016.
  */
class ShapeTest {

  @Test
  def itShouldGIveLineOfPixels(): Unit ={
    //given

    val x1y1: Coordinate = (1,5)
    val x2y2: Coordinate = (5,5)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.line(x1y1,x2y2,c).draw()
    //then
    println(line)
    (x1y1._2 to x2y2._2) map (x => (line.exists(p => (p.coordinate()==(x,x1y1._1)) && (p.colour() == c)) shouldBe true))
  }


  @Test
  def itShouldDrawRectangle1(): Unit ={
    //given

    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (5,5)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2,c).draw()
    //then
    println(line)
    List(ColouredPixel((1,3),'*'), ColouredPixel((2,3),'*'), ColouredPixel((3,3),'*'), ColouredPixel((4,3),'*'),
      ColouredPixel((5,3),'*'), ColouredPixel((1,5),'*'), ColouredPixel((2,5),'*'), ColouredPixel((3,5),'*'),
      ColouredPixel((4,5),'*'), ColouredPixel((5,5),'*'), ColouredPixel((1,3),'*'), ColouredPixel((1,4),'*'),
      ColouredPixel((1,5),'*'), ColouredPixel((5,3),'*'), ColouredPixel((5,4),'*'), ColouredPixel((5,5),'*')) shouldBe line
  }

  @Test
  def itShouldDrawRectangle2(): Unit ={
    //given

    val x1y1: Coordinate = (5,5)
    val x2y2: Coordinate = (1,3)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2,c).draw()
    //then
    println(line)
    List(ColouredPixel((1,3),'*'), ColouredPixel((2,3),'*'), ColouredPixel((3,3),'*'), ColouredPixel((4,3),'*'),
      ColouredPixel((5,3),'*'), ColouredPixel((1,5),'*'), ColouredPixel((2,5),'*'), ColouredPixel((3,5),'*'),
      ColouredPixel((4,5),'*'), ColouredPixel((5,5),'*'), ColouredPixel((1,3),'*'), ColouredPixel((1,4),'*'),
      ColouredPixel((1,5),'*'), ColouredPixel((5,3),'*'), ColouredPixel((5,4),'*'), ColouredPixel((5,5),'*')) shouldBe line
  }

  @Test
  def itShouldDrawRectangle3(): Unit ={
    //given

    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (4,5)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2,c).draw()
    //then
    println(line)
    List(ColouredPixel((1,3),'*'), ColouredPixel((2,3),'*'), ColouredPixel((3,3),'*'), ColouredPixel((4,3),'*'),
      ColouredPixel((5,3),'*'), ColouredPixel((1,5),'*'), ColouredPixel((2,5),'*'), ColouredPixel((3,5),'*'),
      ColouredPixel((4,5),'*'), ColouredPixel((5,5),'*'), ColouredPixel((1,3),'*'), ColouredPixel((1,4),'*'),
      ColouredPixel((1,5),'*'), ColouredPixel((5,3),'*'), ColouredPixel((5,4),'*'), ColouredPixel((5,5),'*')) shouldBe line
  }

  @Test
  def itShouldDrawRectangle4(): Unit ={
    //given

    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (0,5)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2,c).draw()
    //then
    println(line)
    List(ColouredPixel((1,3),'*'), ColouredPixel((2,3),'*'), ColouredPixel((3,3),'*'), ColouredPixel((4,3),'*'),
      ColouredPixel((5,3),'*'), ColouredPixel((1,5),'*'), ColouredPixel((2,5),'*'), ColouredPixel((3,5),'*'),
      ColouredPixel((4,5),'*'), ColouredPixel((5,5),'*'), ColouredPixel((1,3),'*'), ColouredPixel((1,4),'*'),
      ColouredPixel((1,5),'*'), ColouredPixel((5,3),'*'), ColouredPixel((5,4),'*'), ColouredPixel((5,5),'*')) shouldBe line
  }

  @Test
  def itShouldDrawRectangle5(): Unit ={
    //given

    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (0,0)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2,c).draw()
    //then
    println(line)
    List(ColouredPixel((1,3),'*'), ColouredPixel((2,3),'*'), ColouredPixel((3,3),'*'), ColouredPixel((4,3),'*'),
      ColouredPixel((5,3),'*'), ColouredPixel((1,5),'*'), ColouredPixel((2,5),'*'), ColouredPixel((3,5),'*'),
      ColouredPixel((4,5),'*'), ColouredPixel((5,5),'*'), ColouredPixel((1,3),'*'), ColouredPixel((1,4),'*'),
      ColouredPixel((1,5),'*'), ColouredPixel((5,3),'*'), ColouredPixel((5,4),'*'), ColouredPixel((5,5),'*')) shouldBe line
  }
  @Test
  def itShouldDrawRectangle6(): Unit ={
    //given

    val x1y1: Coordinate = (0,0)
    val x2y2: Coordinate = (5,5)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2,c).draw()
    //then
    println(line)
    List(ColouredPixel((1,3),'*'), ColouredPixel((2,3),'*'), ColouredPixel((3,3),'*'), ColouredPixel((4,3),'*'),
      ColouredPixel((5,3),'*'), ColouredPixel((1,5),'*'), ColouredPixel((2,5),'*'), ColouredPixel((3,5),'*'),
      ColouredPixel((4,5),'*'), ColouredPixel((5,5),'*'), ColouredPixel((1,3),'*'), ColouredPixel((1,4),'*'),
      ColouredPixel((1,5),'*'), ColouredPixel((5,3),'*'), ColouredPixel((5,4),'*'), ColouredPixel((5,5),'*')) shouldBe line
  }


}
