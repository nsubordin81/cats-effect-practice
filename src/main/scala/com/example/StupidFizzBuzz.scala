package com.example

import cats.effect.{IO, IOApp}
import scala.concurrent.duration._

// object StupidFizzBuzz extends IOApp.Simple
//   val run =
//     for
//         ctr <- IO.ref(0)
        
//         wait = IO.sleep(1.second)
//         poll = wait *> ctr.get


//         _ <- poll.flatMap(IO.println(_).foreverM.start)
//         _ <- poll.map(_ % 3 == 0).ifM(IO.println("fizz"), IO.unit).foreverM.start
//         _ <- poll.map(_ % 5 == 0).ifM(IO.println("buzz"), IO.unit).foreverM.start

//         _ <- (wait *> ctr.update(_ + 1)).foreverM.void
//     yield ()


object StupidFizzBuzz extends IOApp.Simple:
  val run =
    // I understand this, it is a for-yield
    // one way of looking at this is syntactic sugar over a flatmap??
    // I forget how 
    for
      // no idea what IO.ref(0) returns, but looks like a counter
      ctr <- IO.ref(0)

      // the IO sleep looks straightforward
      wait = IO.sleep(1.second)
      // don't know the *> thing. but intuitively look slike 
      // "do wait and then get the next  value for assignment and continue"
      // kind of like forcing streaming to look imperative?
      poll = wait *> ctr.get

      // ok so this part looks like it is doing 3 things in the for yield
      // first starting the counter?? not sure there
      _ <- poll.flatMap(IO.println(_)).foreverM.start
      _ <- poll.map(_ % 3 == 0).ifM(IO.println("fizz"), IO.unit).foreverM.start
      _ <- poll.map(_ % 5 == 0).ifM(IO.println("buzz"), IO.unit).foreverM.start

      _ <- (wait *> ctr.update(_ + 1)).foreverM.void
    yield ()




