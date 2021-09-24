package game.ground;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	public Valley() {
		super('+');
	}

	/**
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		// use already existing ENTER_FLOOR enum?
		return actor.hasCapability(Abilities.FALL_FROM_VALLEY);
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		if (location.getActor() == actor && actor.hasCapability(Abilities.FALL_FROM_VALLEY)) {
			actor.hurt(Integer.MAX_VALUE);
		}
		return new Actions();
	}
}
