/**
  * Created by kirankumarbs on 9/28/2016.
  */
package object drawing {

  type Coordinate = (Int,Int)
  type BoundaryBetweenPixels = (Int,Int)
  type Boundaries = List[((Int,Int),tracePixel)]
  type Colour     = Char
  type tracePixel = Int => Pixel
}
