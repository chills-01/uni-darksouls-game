package game.weapons;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.actions.ChargeAction;
import game.actions.SwapWeaponAction;
import game.actions.WindSlashAction;
import game.enemies.YhormTheGiant;
import game.enums.Abilities;
import game.enums.Status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Class containing specific functionality for storm ruler weapon
 */
public class StormRuler extends MeleeWeapon {
    protected int charge;

    public StormRuler() {
        super("Storm Ruler", '7', 70, "hits", 60);
        //weapon must be charged to use.
        this.charge = 0;
        passiveSkills.add(Abilities.CRITICAL_HIT); // critical hit functionality
    }

    /**
     * Functionality that occurs when weapon is fully charged
     *
     * @param actor actor holding weapon
     */
    public void fullyCharged(Actor actor) {

        //if fully charged, method gets called to add specific actions to new array list needed to be removed from original array list, then iterate through new list to remove from original.
        List<Action> removeItems = new ArrayList<Action>();
        for (Action action : this.allowableActions) {
            if (action instanceof ChargeAction) {
                removeItems.add(action);
            }
        }
        for (Action action : removeItems) {
            this.allowableActions.remove(action);
        }

        actor.addCapability(Status.STORMRULER_FULLY_CHARGED);

    }

    /**
     *  Resets the weapon back to pre-charged state
     */
    public void preRecharge() {
        this.setCharge(0);
        List<Action> removeItems = new ArrayList<Action>();
        for (Action action : this.allowableActions) {
            if (action instanceof WindSlashAction) {
                removeItems.add(action);
            }
        }
        for (Action action : removeItems) {
            this.allowableActions.remove(action);
        }

        this.allowableActions.add(new ChargeAction(this));
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (actor instanceof Player) {
            // charge only available when player picks up
            this.allowableActions.add(new ChargeAction(this));
            return new SwapWeaponAction(this);
        }
        return null;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int newCharge) {
        this.charge = newCharge;
    }
}
