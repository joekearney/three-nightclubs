/**
 * Part Zero : 10:15 Saturday Night
 *
 * (In which we will see how to let the type system help you handle failure)...
 *
 * First let's define a domain. (All the following requires scala 2.11.x and scalaz 7.1)
 */
package nightclubs {
  import scalaz._
  import Scalaz._
  import TestClubbers._

  object Sobriety extends Enumeration { val Sober, Tipsy, Drunk, Paralytic, Unconscious = Value }

  object Gender extends Enumeration { val Male, Female = Value }

  case class Person(gender: Gender.Value, age: Int, clothes: Set[String], sobriety: Sobriety.Value)

  /**
   * Let's define a trait which will contain the checks that *all* nightclubs make!
   */
  trait Nightclub {
    // first check
    def checkAge(p: Person): Validation[String, Person] = {
      if (p.age < 18)
        "Too Young".failure
      else if (p.age > 40)
        "Too Old".failure
      else
        p.success
    }

    // second check
    def checkClothes(p: Person): Validation[String, Person] = {
      if (p.gender == Gender.Male && !p.clothes("Tie"))
        "Smarten up!".failure
      else if (p.gender == Gender.Female && p.clothes("Trainers"))
        "Wear high heels".failure
      else
        p.success
    }

    // third check
    def checkSobriety(p: Person): Validation[String, Person] =
      if (Set(Sobriety.Drunk, Sobriety.Paralytic, Sobriety.Unconscious) contains p.sobriety)
        "Sober up!".failure
      else
        p.success
  }
  
  // Let's see these in action
  object TestClubbers {
    val Ken = Person(Gender.Male, 28, Set("Tie", "Shirt"), Sobriety.Tipsy)

    val Dave = Person(Gender.Male, 41, Set("Tie", "Jeans"), Sobriety.Sober)

    val Ruby = Person(Gender.Female, 25, Set("High Heels"), Sobriety.Tipsy)
  }
}