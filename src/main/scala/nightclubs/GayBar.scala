package nightclubs

import scalaz._, Scalaz._

/**
 * Part Three : Gay Bar
 *
 * And for those wondering how to do this with a *very long list* of checks. Use sequence:
 *   List[ValidationNel[E, A]] ~> (via sequence) ~> ValidationNel[E, List[A]]
 *
 * Here we go:
 */
object GayBar extends Nightclub {

  def checkGender(p: Person): Validation[String, Person] = {
    if (p.gender != Gender.Male)
      "Men Only".failure
    else
      p.success
  }

  // checks as a list of functions
  val checks = List(checkAge _, checkClothes _, checkSobriety _, checkGender _)

  def costToEnter(p: Person): ValidationNel[String, Double] = {
    (checks map { _(p) toValidationNel }).sequenceU map {
       case c :: _ => c.age + 1.5D // this is case (c :: restOfList)
    }
  }

  //Interestingly, as traverse is basically map + sequence, we can reduce this even further
  def costToEnter2(p : Person): ValidationNel[String, Double] = {
    checks.traverseU(_ andThen (_.toValidationNel) apply p) map {
      case c :: _ => c.age + 1.5D
    }
  }
}

/*
 * As always; the point is that our validation functions are "static";
 * we do not need to change the way they have been coded because we want to combine them in different ways
 */
