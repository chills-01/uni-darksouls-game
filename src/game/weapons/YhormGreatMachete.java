package game.weapons;

import game.enums.Status;

/**
 * Class representing Yhorm's Great Machete
 */
public class YhormGreatMachete extends MeleeWeapon{
    public YhormGreatMachete() {
        super("Yhorm's Great Machete", 'M', 95, "hits", 60);
    }

    @Override
    public int chanceToHit() {
        // if in ember form
        if (this.hasCapability(Status.EMBER_FORM)) {
            return this.hitRate + 30; // increase by 30%
        }
        return super.chanceToHit();
    }
}
