# Requirements for Design O'Souls

## Enemies (*Carter*)
* [x] Enemy abstract class
* [x] LordOfCinder (abstract) -> YhormTheGiant
* [x] Skeleton
* [x] Refactor this package
* [x] Abstract enemy functionality
* [ ] Make enemies hostile

## Ground (*Carter*)
* [x] Make Cemetary class
* [x] Make Bonfire class
* [x] Refactor into Ground package and `GroundType.java` abstract
* [x] Method for spawning undead in `Cemetary.java`

## Items (*Devshi*)
* [x] Make Estus flask item
* [x] Add to Player constructor
* [x] Implements Consumable (create interface)
* [ ] `ConsumeItemAction` implement

`Items.addItemToInventory(new EstusFlask())` in constructor

* [x] Make the CinderLord item
* [x] Add to LordofCinder constructor

* [x] Make the TokenOfSouls item (implements Souls)
* [x] Add to player constructor
* [x] Override Souls methods

## Weapons (*Devshi*)
* [x] Add all weapons + add to enemies / player
* [ ] Implement attack functionality
* [ ] Refactor actions

## Actions (*Carter*)
* [ ] Add attack behaviour (same as wander)

## Reset (*Carter*)
* [ ] Rest at bonfire feature
* [ ] Walking off valley death
* [ ] Death from zero HP