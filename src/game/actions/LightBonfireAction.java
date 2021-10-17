package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;
import game.ground.Bonfire;

/**
 * Class outlining the action for lighting a bonfire, only called if bonfire is not active.
 */

public class LightBonfireAction extends Action {
    private Bonfire bonfire;
    public LightBonfireAction(Bonfire bonfire) {
        this.bonfire = bonfire;

    }
    @Override
    public String execute(Actor actor, GameMap map) {
        this.bonfire.addCapability(Status.BONFIRE_ACTIVE);
        bonfire.getBonfireManager().setCurrentBonfire(this.bonfire);
        return menuDescription(actor);
    }


    @Override
    public String menuDescription(Actor actor) {
        return "Light the " + bonfire.getName() + " bonfire";
    }
}
