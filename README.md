# A Tale of Three Nightclubs

An example of use of scalaz Validation, by Chris Marshall (@oxbow_lakes)

---

## Script

```scala
import nightclubs._, TestClubbers._
ClubbedToDeath costToEnter Ken
ClubbedToDeath costToEnter Dave
ClubbedToDeath costToEnter Ruby
ClubbedToDeath costToEnter Ruby.copy(age = 17)
ClubbedToDeath costToEnter Ken.copy(sobriety = Sobriety.Unconscious)

import ClubTropicana._
ClubTropicana costToEnter Dave.copy(sobriety = Sobriety.Paralytic)
ClubTropicana costToEnter Ruby

import GayBar._
GayBar costToEnter Ken
GayBar costToEnter Dave
GayBar costToEnter Dave.copy(age=39)
```
