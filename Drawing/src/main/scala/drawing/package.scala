/**
  * Created by kirankumarbs on 9/28/2016.
  */
package object drawing {

  type Width      = Int
  type Height     = Int
  type Colour     = Char
  type Coordinate = (Int,Int)
  type BoundaryBetweenPixels = (Int,Int)
  type Boundaries = List[((Int,Int),tracePixel)]
  type tracePixel = Int => Pixel
  type Pixels     = List[Pixel]
}
