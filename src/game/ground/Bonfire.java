package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player;
import game.actions.ResetAction;
import game.enums.Abilities;

/**
 *
 */
public class Bonfire extends Ground {
    public Bonfire() {
        super('B');
    }

    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (location.getActor() == actor && actor.hasCapability(Abilities.REST) && actor instanceof Player) {
            // only the player can rest at the bonfire
            Actions actions = new Actions();
            actions.add(new ResetAction(actor, location, ((Player) actor).getBonfireLocation(), ((Player) actor).getPreviousLocation()));
            return actions;
        } else {
            return new Actions();
        }
    }
}

