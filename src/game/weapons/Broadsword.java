package game.weapons;

import edu.monash.fit2099.engine.Display;
import game.enemies.Undead;
import game.enums.Abilities;

/**
 * Class representing the Broadsword weapon
 */
public class Broadsword extends MeleeWeapon {
    public Broadsword() {
        super("Broadsword", 'B', 30, "hits", 80);
        // ability to critical hit
        passiveSkills.add(Abilities.CRITICAL_HIT);
    }


}
