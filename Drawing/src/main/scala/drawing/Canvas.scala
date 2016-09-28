package drawing

/**
  * Created by kirankumar on 28-09-2016.
  */
trait Canvas {
  def isEmpty():Boolean
  def pixels(): List[Pixel]


  def generatePixels(w: Int,h: Int): List[Pixel] =
    ((0 to w ) flatMap  (w =>
      (0 to h) map (h => Pixel((w,h))))).toList
}

protected case class EmptyCanvas(width: Int,height: Int) extends Canvas{
  override def isEmpty(): Boolean = true
  override def pixels():List[Pixel] = generatePixels(width,height)
}

protected case class FilledCanvas(w: Int,h: Int) extends Canvas{
  override def isEmpty(): Boolean = false

  override def pixels(): List[Pixel] = ???
}

object Canvas{
  def apply(w: Int,h: Int): Canvas = EmptyCanvas(w,h)
}