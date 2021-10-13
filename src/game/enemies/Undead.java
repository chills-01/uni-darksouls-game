package game.enemies;


import edu.monash.fit2099.engine.*;
import game.weapons.NoWeapon;

import java.util.Random;

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

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		int chance = new Random().nextInt(9);
		Action action =  super.playTurn(actions, lastAction, map, display);
		if (chance == 0) {
//			this.hurt(Integer.MAX_VALUE);
			System.out.println("Undead dies randomly at " + map.locationOf(this).x() + ", " + map.locationOf(this).y());
			map.removeActor(this);
			return new DoNothingAction();
		}
		return action;
	}


}
