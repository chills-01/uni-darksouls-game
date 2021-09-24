package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.Player;
import game.actions.SwapWeaponAction;
import game.enums.Abilities;

public class StormRuler extends MeleeWeapon {
    public StormRuler() {
        super("Storm Ruler", '7', 70, "hits", 60);
        passiveSkills.add(Abilities.CRITICAL_HIT); // critical hit functionality
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (actor instanceof Player) {
            return new SwapWeaponAction(this);
        }
        return null;
    }
}
