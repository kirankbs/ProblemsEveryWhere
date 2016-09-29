package drawing

/**
  * Created by kirankumar on 28-09-2016.
  */
sealed trait Canvas {
  def isEmpty():Boolean
  def pixels(): List[Pixel]
  def width: Width
  def height: Height


  def generatePixels(w: Int,h: Int): List[Pixel] =
    ((0 to w ) flatMap  (w =>
      (0 to h) map (h => Pixel((w,h))))).toList
}

sealed protected case class EmptyCanvas(w: Width,h: Height) extends Canvas{
  override def isEmpty(): Boolean = true
  override def width: Width = w
  override def height: Height = h
  override def pixels():List[Pixel] = generatePixels(w,h)
}

sealed protected case class FilledCanvas(w: Width,h: Height,cP: Pixels) extends Canvas{
  override def isEmpty(): Boolean = false
  override def width: Width = w
  override def height: Height = h
  override def pixels(): List[Pixel] = cP
}

object Canvas{
  def apply(w: Width,h: Height): Canvas = EmptyCanvas(w,h)
  def apply(w: Width,h: Height,cP: Pixels): Canvas = FilledCanvas(w,h,cP)
}