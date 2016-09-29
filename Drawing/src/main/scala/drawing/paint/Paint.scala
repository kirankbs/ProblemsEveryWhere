package drawing.paint

import drawing.{Canvas, Colour, Pixel, Pixels}
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
  def apply(canvas: Canvas, shape:Shape,colour:Colour): Paint = PaintCanvas(canvas,shape,colour)
}
