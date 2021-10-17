package game.enemies;


import edu.monash.fit2099.engine.*;
import game.weapons.NoWeapon;

/**
 * An undead minion.
 */
public class Undead extends Enemy {
	/** 
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50, 50);
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new NoWeapon(20, "thwacks");
	}

	@Override
	public boolean isExist(){
		return false;
	}
}
