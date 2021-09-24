package game.enemies;


import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;

import java.util.ArrayList;

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
		return new IntrinsicWeapon(20, "thwacks");
	}

	@Override
	public boolean isExist(){
		return false;
	}
}
