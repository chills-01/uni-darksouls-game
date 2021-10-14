package game.weapons;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;
import game.enums.Status;

import java.util.Random;

public class DarkmoonLongbow extends MeleeWeapon{

    public DarkmoonLongbow() {
        super("Darkmoon Longbow", '3', 70, "hits", 80);
        passiveSkills.add(Abilities.CRITICAL_HIT);
        passiveSkills.add(Abilities.RANGED_WEAPON);
    }



}
