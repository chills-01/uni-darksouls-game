package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Special PickupAction for picking up a Souls item
 */

public class PickUpSoulsAction extends PickUpItemAction {
    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public PickUpSoulsAction(Item item) {
        super(item);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // transfer souls to actor and removes souls item
        try {
            item.asSoul().transferSouls(actor.asSoul());
            map.at(map.locationOf(actor).x(), map.locationOf(actor).y()).removeItem(item);
            return super.execute(actor, map);
        }catch (ClassCastException e) {
            return e.getMessage();
        }
    }
}
