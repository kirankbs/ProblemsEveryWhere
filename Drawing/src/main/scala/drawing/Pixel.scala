package drawing

/**
  * Created by kirankumar on 27-09-2016.
  */
trait Pixel {
  def isEmpty():Boolean
  def fillColour(c: Char):Pixel
  def colour(): Char
}

protected case class EmptyPixel(x:Int,y:Int) extends Pixel{
  def isEmpty():Boolean = true

  override def fillColour(c: Char): Pixel = Pixel(x,y,c)

  override def colour(): Char = ' '
}

protected case class ColouredPixel(x: Int,y:Int,c:Char) extends Pixel{
  def isEmpty():Boolean = false

  override def fillColour(c: Char): Pixel = Pixel(x,y,c)

  override def colour(): Char = c
}

object Pixel{
  def apply(x:Int,y:Int):Pixel = EmptyPixel(x,y)
  def apply(x:Int,y:Int,c:Char) = ColouredPixel(x,y,c)
}

