package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
	}

	public boolean canActorEnter(Actor actor) {
		if (actor.hasCapability(Status.ENTER_FLOOR)) {
			return true;
		}
		else {
			return false;
		}
	}

}
