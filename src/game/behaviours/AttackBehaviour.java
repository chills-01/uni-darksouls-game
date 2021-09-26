package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Status;

public class AttackBehaviour extends FollowBehaviour {
    public AttackBehaviour(Actor subject) {
        super(subject);
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            if (exit.getDestination().containsAnActor() && target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return new AttackAction(target, exit.getName());
            }

        }
        return null;
    }
}
