package drawing

/**
  * Created by kirankumar on 27-09-2016.
  */
trait Pixel {
  def isColoured():Boolean
  def fillColour(c: Colour):Pixel
  def colour(): Colour
  def coordinate(): Coordinate
}

protected case class EmptyPixel(co:Coordinate) extends Pixel{
  def isColoured():Boolean = true

  override def fillColour(c: Colour): Pixel = Pixel(co,c)

  override def colour(): Colour = ' '

  override def coordinate(): Coordinate = co
}

protected case class ColouredPixel(co:Coordinate,c: Colour) extends Pixel{
  def isColoured():Boolean = false

  override def fillColour(c: Colour): Pixel = Pixel(co,c)

  override def colour(): Colour = c

  override def coordinate(): Coordinate = co
}

object Pixel{
  def apply(co:Coordinate):Pixel = EmptyPixel(co)
  def apply(co:Coordinate,c: Colour) = ColouredPixel(co,c)
}

