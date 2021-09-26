package game.enemies;

import edu.monash.fit2099.engine.*;
import game.AttackBehaviour;
import game.actions.AttackAction;
import game.FollowBehaviour;
import game.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;

public abstract class Enemy extends Actor implements Resettable, Soul {
    // Will need to change this to a collection if Enemy gets additional Behaviours.
    protected ArrayList<Behaviour> behaviours = new ArrayList<>(); // so yhormgiant can modify to not wander
    private int souls;

    public Enemy(String name, char displayChar, int hitPoints, int souls) {
        super(name, displayChar, hitPoints);
        this.souls = souls;
        behaviours.add(new WanderBehaviour()); // does by default

        //register as resettable
        registerInstance();
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
            behaviours.add(0, new AttackBehaviour(otherActor));
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Figure out what to do next.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */

    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // todo display hitpoints and weapon
        // loop through all behaviours
        for(game.interfaces.Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public String toString() {
        return name  + " (" + hitPoints + "/" + maxHitPoints + "), holding: " + getWeapon().toString();
    }

    @Override
    public void resetInstance() {
        // heal
        hitPoints = maxHitPoints;
    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
        this.subtractSouls(souls);
    }

    @Override
    public boolean subtractSouls(int souls) {
        return Soul.super.subtractSouls(souls);
    }
}
