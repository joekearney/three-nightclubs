package nightclubs

import scalaz._, Scalaz._

/**
 * Part One : Clubbed to Death
 *
 * Now let's compose some validation checks
 */
object ClubbedToDeath extends Nightclub {
  def costToEnter(p: Person): Validation[String, Double] = {
    /*
	   * The thing to note here is how the Validations can be composed together in a for-comprehension.
	   * Scala's type system is making sure that failures flow through your computation in a safe manner.
	   */
    for {
      a <- checkAge(p)
      b <- checkClothes(a)
      c <- checkSobriety(b)
    } yield (if (c.gender == Gender.Female) 0D else 5D)
  }
}
