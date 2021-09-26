package game.enemies;

import edu.monash.fit2099.engine.*;
import game.AttackBehaviour;
import game.FollowBehaviour;
import game.WanderBehaviour;
import game.actions.AttackAction;
import game.actions.WindSlashAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.weapons.Broadsword;
import game.weapons.YhormGreatMachete;

import java.util.ArrayList;
import java.util.List;

public class YhormTheGiant extends LordOfCinder{
    public YhormTheGiant(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        //creating Yhorm's Great Machete that is stored in Yhorm's inventory
        this.addItemToInventory(new YhormGreatMachete());
        this.addCapability(Abilities.WEAK_TO_STORM_RULER);

        behaviours = new ArrayList<Behaviour>(); // Yhorm should not wander



    }

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
}
