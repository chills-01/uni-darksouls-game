package game.weapons;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MeleeWeapon extends WeaponItem {
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */

    protected ArrayList<Abilities> passiveSkills;
    public MeleeWeapon(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
        passiveSkills = new ArrayList<>();
    }


    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public int damage() {
        
        if (passiveSkills.contains(Abilities.CRITICAL_HIT) && !this.hasCapability(Status.DISABLE_CRITICAL_STRIKE)) {
            if (new Random().nextInt(5) < 1) { // 20% chance
                new Display().println("CRITICAL HIT!!");
                return 2 * damage;
            }
        }
        return super.damage();
    }
}
