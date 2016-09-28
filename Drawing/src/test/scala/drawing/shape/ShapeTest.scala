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


}
