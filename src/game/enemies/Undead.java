package game.enemies;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.AttackAction;
import game.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;

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
		super(name, 'u', 50);
	}

	@Override
	public boolean isExist() {
		return false;
	}
}
