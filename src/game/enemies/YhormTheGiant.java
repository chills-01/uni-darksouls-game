package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.actions.AttackAction;
import game.actions.WindSlashAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.weapons.YhormGreatMachete;

import java.util.ArrayList;

/**
 * Class for Yhorm the giant, additional functionality to enemy and LordOfCinder
 */

public class YhormTheGiant extends LordOfCinder{
    public YhormTheGiant(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

        //creating Yhorm's Great Machete that is stored in Yhorm's inventory
        this.addItemToInventory(new YhormGreatMachete());
        this.addCapability(Abilities.WEAK_TO_STORM_RULER);

    }

    /**
     * Gets allowable actions unique to Yhorm the Giant
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions available to player
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if (this.hasCapability(Status.STUNNED)) {
            Actions skipTurnActions = new Actions();
            skipTurnActions.add(new DoNothingAction());
            actions = skipTurnActions;
        }
        else {
            // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
            if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                // better way to do this but we want it as priority
                behaviours.add(0, new FollowBehaviour(otherActor));
                behaviours.add(0, new AttackBehaviour(otherActor));
                actions.add(new AttackAction(this,direction));
            }

            if (otherActor.hasCapability(Status.STORMRULER_FULLY_CHARGED)) {
                actions.add(new WindSlashAction((WeaponItem) otherActor.getWeapon(), this));
            }
        }
        return actions;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (this.hitPoints < 0.5 * maxHitPoints) {
            new Display().println("EMBER FORM ACTIVATED");
            ((WeaponItem) this.getWeapon()).addCapability(Status.EMBER_FORM); // todo fix downcast

        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
