package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Transports an actor to any location across the Game World.
 */
public class TransportActorAction extends Action {
    Location targetLocation;
    String targetLocationName;
    public TransportActorAction(Location location, String name) {
        targetLocation = location;
        targetLocationName = name;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!targetLocation.containsAnActor() || targetLocation.getGround().canActorEnter(actor)) {
            map.moveActor(actor, targetLocation);
            return menuDescription(actor);
        }
        return actor + " cannot enter target location";

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves to " + targetLocationName;
    }
}
