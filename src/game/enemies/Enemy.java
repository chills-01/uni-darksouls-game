package game.enemies;

import edu.monash.fit2099.engine.*;
import game.AttackAction;
import game.FollowBehaviour;
import game.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.ArrayList;

public abstract class Enemy extends Actor {
    // Will need to change this to a collection if Enemy gets additional Behaviours.
    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        behaviours.add(new WanderBehaviour());
        // todo make attack and follow player
    }

    /**
     * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
     * You can do something else with this method.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */

    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // better way to do this but we want it as priority
            behaviours.add(0, new FollowBehaviour(otherActor));
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Figure out what to do next.
     * FIXME: An Undead wanders around at random and it cannot attack anyone. Also, figure out how to spawn this creature.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */

    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // loop through all behaviours
        for(game.interfaces.Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

}
