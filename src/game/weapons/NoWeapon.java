package game.weapons;

import edu.monash.fit2099.engine.IntrinsicWeapon;

/**
 * Default weapon for undead
 */

public class NoWeapon extends IntrinsicWeapon {
    public NoWeapon(int damage, String verb) {
        super(damage, verb);
    }

    @Override
    public String toString() {
        return "No Weapon";
    }
}
