package drawing

/**
  * Created by kirankumarbs on 9/28/2016.
  */
trait Shape {
  def draw():List[Pixel]

}

case class Line(co1:Coordinate,co2:Coordinate,c:Colour) extends Shape {
  override def draw(): List[Pixel] = ((co1._2 to co2._2) flatMap (x => (co1._1 to co2._1) map (y => Pixel((x,y),c)))).toList

}
case class Rectangle(co1:Coordinate,co2:Coordinate,c:Colour) extends Shape() {
  override def draw(): List[Pixel] = {
    drawHelper(co1,(co1._1,co2._2))
    :: drawHelper(co1,(co2._1,co1._2))
    :: drawHelper((co2._1,co1._1),co2)
    :: drawHelper((co1._1,co2._2),co2)
    :: List(Pixel((0,0))
  }

  private def drawHelper(c1:Coordinate,c2: Coordinate): List[Pixel] ={
    ((co1._2 to co2._2) flatMap (x => (co1._1 to co2._1) map (y => Pixel((x,y),c)))).toList
  }

}
object Shape{
  def line(co1: Coordinate,co2: Coordinate,c: Colour): Shape = Line(co1,co2,c)
  def rect(co1: Coordinate,co2: Coordinate,c:Colour): Shape = Line(co1,co2,c)
}
