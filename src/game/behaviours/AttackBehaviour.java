package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Status;

/**
 * Behaviour that enables an NPC to attack another actor
 */
public class AttackBehaviour extends FollowBehaviour {
    /**
     *
     * @param subject actor to attack
     */
    public AttackBehaviour(Actor subject) {
        super(subject);
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // check if target and actor exist on the map
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);

        // check exits for hostile actor
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().containsAnActor() && target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return new AttackAction(target, exit.getName());
            }

        }
        return null;
    }
}
