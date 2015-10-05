package nightclubs

import scalaz._, Scalaz._

/**
 * Part Two : Club Tropicana
 *
 * Part One showed monadic composition, which from the perspective of Validation is *fail-fast*.
 * That is, any failed check short-circuits subsequent checks. This nicely models nightclubs in the
 * real world, as anyone who has dashed home for a pair of smart shoes and returned, only to be
 * told that your tie does not pass muster, will attest.
 *
 * But what about an ideal nightclub? One that tells you *everything* that is wrong with you.
 *
 * Applicative functors to the rescue!
 */
object ClubTropicana extends Nightclub {
  def costToEnter(p: Person) : ValidationNel[String, Double] = {

    //PERFORM THE CHECKS USING applicative functors, accumulating failure via a monoid (a NonEmptyList, or NEL)
    (checkAge(p).toValidationNel |@|
     checkClothes(p).toValidationNel |@|
     checkSobriety(p).toValidationNel
    )  {
      case (_, _, c) => if (c.gender == Gender.Female) 0D else 7.5D
    }
  }
}

/*
 * So, what have we done? Well, with a *tiny change* (and no changes to the individual checks themselves),
 * we have completely changed the behaviour to accumulate all errors, rather than halting at the first sign
 * of trouble. Imagine trying to do this in Java, using exceptions, with ten checks.
 */
