package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enemies.AldrichTheDevourer;
import game.enums.Status;
import game.weapons.DarkmoonLongbow;

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

        if (actor.getWeapon() instanceof DarkmoonLongbow) { //can abstract this to instance of ranged weapon for future design
            Location here = map.locationOf(actor);
            Location there = map.locationOf(target);

            NumberRange xs, ys;

            if ((here.x() == there.x() && (Math.abs(here.x() - there.x())) <= 3) || (here.y() == there.y() && (Math.abs(here.y() - there.y())) <= 3)) {
                xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
                ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

                for (int x : xs) {
                    for (int y : ys) {
                        if (map.at(x, y).getGround().blocksThrownObjects()) {
                            new Display().println(actor + " attempted to attack " + target + "but was blocked.");
                            return null;
                        }
                    }
                }
                return new AttackAction(target, "");
            }
            return null;
        }

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
