package drawing.shape

import drawing._

/**
  * Created by kirankumarbs on 9/28/2016.
  */
sealed trait Shape {
  def draw():List[Pixel]

  def drawHelper(co:BoundaryBetweenPixels,f: tracePixel): List[Pixel] = ((co._1 to co._2) map (x => f(x))).toList
  def minMax(co: BoundaryBetweenPixels) = (co._1 min co._2,co._1 max co._2)
}

sealed case class Line(co1:Coordinate,co2:Coordinate) extends Shape {
  override def draw(): List[Pixel] =
    ((co1._1 to co2._1) flatMap (x => (co1._2 to co2._2) map (y => Pixel((x,y))))).toList

}
sealed case class Rectangle(x1y1:Coordinate, x2y2:Coordinate) extends Shape() {

  override def draw(): List[Pixel] = {
    def drawLoop(bs: Boundaries,acc:List[Pixel]):List[Pixel] =bs match {
      case Nil    => acc
      case x::xs  => drawLoop(xs,acc ++ drawHelper(x._1,x._2))
      case _      => acc
    }
    drawLoop(getRectangleBoundaries(),List())

  }

  private def getRectangleBoundaries(): Boundaries ={
    List((minMax(x1y1._1,x2y2._1),(x: Int) => Pixel((x,x1y1._2))),
      (minMax(x1y1._1,x2y2._1),(x: Int) => Pixel((x,x2y2._2))),
      (minMax(x1y1._2,x2y2._2),(y:Int) => Pixel((x1y1._1,y))),
      (minMax(x1y1._2,x2y2._2),(y: Int) => Pixel((x2y2._1,y))))
  }
}
object Shape{
  def line(co1: Coordinate,co2: Coordinate): Shape = Line(co1,co2)
  def rect(co1: Coordinate,co2: Coordinate): Shape = Rectangle(co1,co2)
}








//private def drawX1_X2Y(f: tracePixel): List[Pixel] = ((x1y1._1 to x2y2._1) map (x => f(x))).toList
//private def drawX1_X2Y2(f: tracePixel): List[Pixel] = ((co1._1 to co2._1) map (x => Pixel((x,co2._2),c))).toList
//private def drawY1_Y2X(f: tracePixel): List[Pixel] = ((x1y1._2 to x2y2._2) map (y => f(y))).toList
//private def drawY1_Y2X2(f: tracePixel): List[Pixel] = ((co1._2 to co2._2) map (y => Pixel((co2._1,y),c))).toList



/*    drawHelper(minMax(x1y1._1,x2y2._1), x => Pixel((x,x1y1._2),c)) ++
      drawHelper(minMax(x1y1._1,x2y2._1),x => Pixel((x,x2y2._2),c)) ++
      drawHelper(minMax(x1y1._2,x2y2._2),y => Pixel((x1y1._1,y),c)) ++
      drawHelper(minMax(x1y1._2,x2y2._2),y => Pixel((x2y2._1,y),c))*/