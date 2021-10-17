package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.BonfireManager;
import game.Player;
import game.actions.LightBonfireAction;
import game.actions.ResetAction;
import game.actions.TransportActorAction;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Class housing the Bonfire functionality
 */
public class Bonfire extends Ground {
    private BonfireManager bonfireManager;
    private String name;
    public Bonfire(String name, BonfireManager bonfireManager) {
        super('B');
        this.bonfireManager = bonfireManager;
        this.name = name;

    }

    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (!hasCapability(Status.BONFIRE_ACTIVE)) {
            actions.add(new LightBonfireAction(this));
            return actions;
        }


        if (actor.hasCapability(Abilities.REST) && actor instanceof Player && hasCapability(Status.BONFIRE_ACTIVE)) {
            // only the player can rest at the bonfire
            actions.add(new ResetAction(actor, location, ((Player) actor).getBonfireLocation(), ((Player) actor).getPreviousLocation(), bonfireManager));

            for (Bonfire b : bonfireManager.getAllActiveBonfires()) {
                if (b != this) {
                    actions.add(new TransportActorAction(bonfireManager.getLocation(b), b.getName() + " bonfire"));
                }
            }
            return actions;


        } else {
            return new Actions();
        }
    }

    public String getName() {
        return name;
    }

    public BonfireManager getBonfireManager() {
        return bonfireManager;
    };
}

