package game.weapons;

import edu.monash.fit2099.engine.Display;
import game.enemies.Undead;
import game.enums.Abilities;

public class Broadsword extends MeleeWeapon {
    public Broadsword() {
        super("Broadsword", 'B', 30, "hits", 80);
        passiveSkills.add(Abilities.CRITICAL_HIT);
    }


}
