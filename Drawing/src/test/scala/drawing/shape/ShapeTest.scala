package drawing.shape

import drawing._
import org.junit.Test
import org.scalatest.Matchers._

/**
  * Created by kirankumarbs on 9/28/2016.
  */
class ShapeTest {

  @Test
  def itShouldGIveHorizontalLine1(): Unit ={
    //given

    val x1y1: Coordinate = (1,5)
    val x2y2: Coordinate = (5,5)
    //when
    val line:List[Pixel]  = Shape.line(x1y1,x2y2).draw()
    //then
    println(line)
    (x1y1._1 to x2y2._1) map (x => (line.exists(p => (p.coordinate()==(x,x1y1._2))) shouldBe true))
  }
  @Test
  def itShouldGIveHorizontalLine2(): Unit ={
    //given

    val x1y1: Coordinate = (0,5)
    val x2y2: Coordinate = (5,5)
    //when
    val line:List[Pixel]  = Shape.line(x1y1,x2y2).draw()
    //then
    println(line)
    (x1y1._1 to x2y2._1) map (x => (line.exists(p => (p.coordinate()==(x,x1y1._2))) shouldBe true))
  }
  @Test
  def itShouldGIveVerticalLine1(): Unit ={
    //given

    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (1,5)
    //when
    val line:List[Pixel]  = Shape.line(x1y1,x2y2).draw()
    //then
    println(line)
    (x1y1._2 to x2y2._2) map (y => (line.exists(p => (p.coordinate()==(x1y1._1,y))) shouldBe true))
  }
  @Test
  def itShouldGIveVerticalLine2(): Unit ={
    //given

    val x1y1: Coordinate = (1,0)
    val x2y2: Coordinate = (1,5)
    //when
    val line:List[Pixel]  = Shape.line(x1y1,x2y2).draw()
    //then
    println(line)
    (x1y1._2 to x2y2._2) map (y => (line.exists(p => (p.coordinate()==(x1y1._1,y))) shouldBe true))
  }

  @Test
  def itShouldGIveVerticalLine3(): Unit ={
    //given

    val x1y1: Coordinate = (0,0)
    val x2y2: Coordinate = (0,5)
    //when
    val line:List[Pixel]  = Shape.line(x1y1,x2y2).draw()
    //then
    println(line)
    (x1y1._2 to x2y2._2) map (y => (line.exists(p => (p.coordinate()==(x1y1._1,y))) shouldBe true))
  }

  @Test
  def itShouldDrawRectangle1(): Unit ={
    //given

    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (5,5)
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2).draw()
    //then
    println(line)
    List(Pixel((1,3)), Pixel((2,3)), Pixel((3,3)), Pixel((4,3)),
      Pixel((5,3)), Pixel((1,5)), Pixel((2,5)), Pixel((3,5)),
      Pixel((4,5)), Pixel((5,5)), Pixel((1,3)), Pixel((1,4)),
      Pixel((1,5)), Pixel((5,3)), Pixel((5,4)), Pixel((5,5))) shouldBe line
  }

  @Test
  def itShouldDrawRectangle2(): Unit ={
    //given

    val x1y1: Coordinate = (5,5)
    val x2y2: Coordinate = (1,3)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2).draw()
    //then
    println(line)
    List(Pixel((1,3)), Pixel((2,3)), Pixel((3,3)), Pixel((4,3)),
      Pixel((5,3)), Pixel((1,5)), Pixel((2,5)), Pixel((3,5)),
      Pixel((4,5)), Pixel((5,5)), Pixel((1,3)), Pixel((1,4)),
      Pixel((1,5)), Pixel((5,3)), Pixel((5,4)), Pixel((5,5))) shouldBe line
  }

  @Test
  def itShouldDrawRectangle3(): Unit ={
    //given

    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (4,5)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2).draw()
    //then
    println(line)
    List(Pixel((1,3)), Pixel((2,3)), Pixel((3,3)), Pixel((4,3)),
      Pixel((5,3)), Pixel((1,5)), Pixel((2,5)), Pixel((3,5)),
      Pixel((4,5)), Pixel((5,5)), Pixel((1,3)), Pixel((1,4)),
      Pixel((1,5)), Pixel((5,3)), Pixel((5,4)), Pixel((5,5))) shouldBe line
  }

  @Test
  def itShouldDrawRectangle4(): Unit ={
    //given

    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (0,5)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2).draw()
    //then
    println(line)
    List(Pixel((1,3)), Pixel((2,3)), Pixel((3,3)), Pixel((4,3)),
      Pixel((5,3)), Pixel((1,5)), Pixel((2,5)), Pixel((3,5)),
      Pixel((4,5)), Pixel((5,5)), Pixel((1,3)), Pixel((1,4)),
      Pixel((1,5)), Pixel((5,3)), Pixel((5,4)), Pixel((5,5))) shouldBe line
  }

  @Test
  def itShouldDrawRectangle5(): Unit ={
    //given

    val x1y1: Coordinate = (1,3)
    val x2y2: Coordinate = (0,0)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2).draw()
    //then
    println(line)
    List(Pixel((1,3)), Pixel((2,3)), Pixel((3,3)), Pixel((4,3)),
      Pixel((5,3)), Pixel((1,5)), Pixel((2,5)), Pixel((3,5)),
      Pixel((4,5)), Pixel((5,5)), Pixel((1,3)), Pixel((1,4)),
      Pixel((1,5)), Pixel((5,3)), Pixel((5,4)), Pixel((5,5))) shouldBe line
  }
  @Test
  def itShouldDrawRectangle6(): Unit ={
    //given

    val x1y1: Coordinate = (0,0)
    val x2y2: Coordinate = (5,5)
    val c = '*'
    //when
    val line:List[Pixel]  = Shape.rect(x1y1,x2y2).draw()
    //then
    println(line)
    List(Pixel((1,3)), Pixel((2,3)), Pixel((3,3)), Pixel((4,3)),
      Pixel((5,3)), Pixel((1,5)), Pixel((2,5)), Pixel((3,5)),
      Pixel((4,5)), Pixel((5,5)), Pixel((1,3)), Pixel((1,4)),
      Pixel((1,5)), Pixel((5,3)), Pixel((5,4)), Pixel((5,5))) shouldBe line
  }


}
