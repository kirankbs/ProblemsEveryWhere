case class State1[S,A](run: S => (A,S)) {

  def map[B](f: A=>B):State1[S,B] =
    State1(s => {
      val (cc,s1) = run(s)
      (f(cc),s1)
    })

  def flatMap[B](f: A => State1[S,B]): State1[S,B] =
    State1(s => {
      val (cc,s1) = run(s)
      f(cc).run(s1)
    })

  def map2[B,C](sb: State1[S,B])(f: (A,B) => C): State1[S,C] =
    State1(s => {
      val (a,s2) = run(s)
      val (b,s3) = sb.run(s2)
      (f(a,b),s3)
    })

  def sequence[A](xs: List[State1[S,A]]): State1[S,List[A]] =
    xs.reverse.foldLeft(State1[S,List[A]](s => (List[A](),s)))((acc,t) => t.map2(acc)(_ :: _))

}

case class Machine1(locked: Boolean, candies: Int, coins: Int)

def modify[S](f: S => S): State1[S, Unit] = for {
  s <- get
  _ <- set(f(s))
} yield ()

State1(s => (s, s)).flatMap(x => State1(_ => ((), x)).map(y =>()))

def get[S]: State1[S, S] = State1(s => (s, s))
def set[S](s: S): State1[S, Unit] = State1(_ => ((), s))


modify((s:Machine1) => Machine1(s.locked,20,30)).run(Machine1(true,10,10))




