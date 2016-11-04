package candydispenser

/**
  * Created by kirankumarbs on 11/4/2016.
  */
case class State[S,A](run: S => (A,S)) {

  def map[B](f: A=>B):State[S,B] =
    State(s => {
      val (cc,s1) = run(s)
      (f(cc),s1)
    })

  def flatMap[B](f: A => State[S,B]): State[S,B] =
    State(s => {
      val (cc,s1) = run(s)
      f(cc).run(s1)
    })

  def map2[B,C](sb: State[S,B])(f: (A,B) => C): State[S,C] =
    State(s => {
      val (a,s2) = run(s)
      val (b,s3) = sb.run(s2)
      (f(a,b),s3)
    })

}

object State{

  def modify[S](f: S => S): State[S,Unit] = State[S,S](s => (s,s)).flatMap(x => State(_ => ((),f(x)))).map(y =>())

  def sequence[S,A](xs: List[State[S,A]]): State[S,List[A]] =
    xs.reverse.foldLeft(State[S,List[A]](s => (List[A](),s)))((acc,t) => t.map2(acc)(_ :: _))

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))


}
