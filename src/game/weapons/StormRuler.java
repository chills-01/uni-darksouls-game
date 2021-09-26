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

public class StormRuler extends MeleeWeapon {
    protected int charge;

    public StormRuler() {
        super("Storm Ruler", '7', 70, "hits", 60);
        //weapon must be charged to use.
        this.charge = 0;
        passiveSkills.add(Abilities.CRITICAL_HIT); // critical hit functionality
        this.allowableActions.add(new ChargeAction(this));
    }

    public void fullyCharged(Actor actor, GameMap map) {
        //if fully charged, method gets called to add specific actions to new array list needed to be removed from original array list, then iterate through new list to remove from original.
        List<Action> removeItems = new ArrayList<Action>();
        for(Action action: this.allowableActions) {
            if (action instanceof ChargeAction) {
                removeItems.add(action);
            }
        }
        for(Action action: removeItems) {
            this.allowableActions.remove(action);
        }
       /* if (getTarget(actor, map) != null) {
            this.allowableActions.add(new WindSlashAction(this, getTarget(actor, map)));
        }*/

        actor.addCapability(Status.STORMRULER_FULLY_CHARGED);

    }

    public void preRecharge() {
        this.setCharge(0);
        List<Action> removeItems = new ArrayList<Action>();
        for(Action action: this.allowableActions) {
            if (action instanceof WindSlashAction) {
                removeItems.add(action);
            }
        }
        for(Action action: removeItems) {
            this.allowableActions.remove(action);
        }

        this.allowableActions.add(new ChargeAction(this));
    }

    private Actor getTarget(Actor actor, GameMap map) {
        Actor target = null;
        //check for Yhorm the Giant
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor targetActor = exit.getDestination().getActor();
                if (targetActor.hasCapability(Abilities.WEAK_TO_STORM_RULER) && (targetActor instanceof YhormTheGiant)) {
                    target = targetActor;
                }
            }
        }
        return target;

    }
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (actor instanceof Player) {
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
