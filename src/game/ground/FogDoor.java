package game.ground;

import edu.monash.fit2099.engine.*;
import game.actions.TransportActorAction;

public class FogDoor extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    private Location targetLocation;
    private String targetLocationName;
    public FogDoor(Location location, String name) {
        super('=');
        this.targetLocation = location;
        targetLocationName = name;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (location.getActor() == actor) {
            Action action = new TransportActorAction(targetLocation, targetLocationName);
            Actions actions = new Actions();
            actions.add(action);
            return actions;
        }
        return new Actions();
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return super.canActorEnter(actor);
    }
}
