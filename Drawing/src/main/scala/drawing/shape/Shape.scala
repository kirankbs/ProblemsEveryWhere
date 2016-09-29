package drawing.shape

import drawing._

/**
  * Created by kirankumarbs on 9/28/2016.
  */
trait Shape {
  def draw():List[Pixel]

  def drawHelper(co:BoundaryBetweenPixels,f: tracePixel): List[Pixel] = ((co._1 to co._2) map (x => f(x))).toList
  def minMax(co: BoundaryBetweenPixels) = (co._1 min co._2,co._1 max co._2)
}

case class Line(co1:Coordinate,co2:Coordinate,c:Colour) extends Shape {
  override def draw(): List[Pixel] =
    //drawHelper(minMax(co1._2,co2._2),(y:Int) => Pixel((co1._1,y),c))
    ((co1._1 to co2._1) flatMap (x => (co1._2 to co2._2) map (y => Pixel((x,y),c)))).toList

}
case class Rectangle(x1y1:Coordinate, x2y2:Coordinate, c:Colour) extends Shape() {

  override def draw(): List[Pixel] = {
    def loop(bs: Boundaries,acc:List[Pixel]):List[Pixel] =bs match {
      case Nil    => acc
      case x::xs  => loop(xs,acc ++ drawHelper(x._1,x._2))
      case _      => acc

    }
    val b: Boundaries =
      List((minMax(x1y1._1,x2y2._1),(x: Int) => Pixel((x,x1y1._2),c)),
        (minMax(x1y1._1,x2y2._1),(x: Int) => Pixel((x,x2y2._2),c)),
        (minMax(x1y1._2,x2y2._2),(y:Int) => Pixel((x1y1._1,y),c)),
        (minMax(x1y1._2,x2y2._2),(y: Int) => Pixel((x2y2._1,y),c)))

    loop(b,List())

  }
}
object Shape{
  def line(co1: Coordinate,co2: Coordinate,c: Colour): Shape = Line(co1,co2,c)
  def rect(co1: Coordinate,co2: Coordinate,c:Colour): Shape = Rectangle(co1,co2,c)
}








//private def drawX1_X2Y(f: tracePixel): List[Pixel] = ((x1y1._1 to x2y2._1) map (x => f(x))).toList
//private def drawX1_X2Y2(f: tracePixel): List[Pixel] = ((co1._1 to co2._1) map (x => Pixel((x,co2._2),c))).toList
//private def drawY1_Y2X(f: tracePixel): List[Pixel] = ((x1y1._2 to x2y2._2) map (y => f(y))).toList
//private def drawY1_Y2X2(f: tracePixel): List[Pixel] = ((co1._2 to co2._2) map (y => Pixel((co2._1,y),c))).toList



/*    drawHelper(minMax(x1y1._1,x2y2._1), x => Pixel((x,x1y1._2),c)) ++
      drawHelper(minMax(x1y1._1,x2y2._1),x => Pixel((x,x2y2._2),c)) ++
      drawHelper(minMax(x1y1._2,x2y2._2),y => Pixel((x1y1._1,y),c)) ++
      drawHelper(minMax(x1y1._2,x2y2._2),y => Pixel((x2y2._1,y),c))*/