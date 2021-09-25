package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.enums.Abilities;
import game.enums.Status;

public class YhormGreatMachete extends MeleeWeapon{
    public YhormGreatMachete() {
        super("Yhorm's Great Machete", 'M', 95, "hits", 60);
    }

    @Override
    public int chanceToHit() {
        if (this.hasCapability(Status.EMBER_FORM)) {
            return this.hitRate + 30; // increase by 30%
        }
        return super.chanceToHit();
    }
}
