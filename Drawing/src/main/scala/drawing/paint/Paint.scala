package drawing.paint

import drawing._
import drawing.shape.Shape

/**
  * Created by kirankumarbs on 9/29/2016.
  */
trait Paint {
  def paint(): Canvas
}

case class PaintCanvas(c:Canvas,s:Shape,colour: Colour) extends Paint {
  override def paint(): Canvas = {
    def loop(cP: Pixels, sP: Pixels, acc: Pixels): Pixels = cP match {
      case Nil      =>  acc.reverse
      case x :: xs  if(sP.exists(sp => sp.coordinate() == x.coordinate())) => loop(xs,sP, Pixel(x.coordinate(),colour) :: acc)
      case x :: xs  => loop(xs,sP,x :: acc)
    }
    Canvas(c.width,c.height,loop(c.pixels(),s.draw(),List()))
  }

}

object Paint{
  def apply(canvas: Canvas, shape:Shape,colour:Colour): Paint = validateShapeBoundariesOnCanvas(canvas,shape,colour)

  private def validateShapeBoundariesOnCanvas(c:Canvas,s:Shape,colour: Colour):Paint = (s.x1y1,s.x2y2) match {
    case (x1y1,x2y2) if(isValid(c,x1y1,x2y2)) => PaintCanvas(c,s,colour)
    case _ => throw new Error("Shape Coordinates are not inside Canvas")
  }

  private def isValid(c: Canvas,x1y1:Coordinate,x2y2: Coordinate): Boolean =
    x1y1._1 <= c.width && x1y1._2 <= c.height && x2y2._1 <= c.width && x2y2._2 <= c.height
}
