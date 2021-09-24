package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

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
        item.asSoul().transferSouls(actor.asSoul());
        return super.execute(actor, map);
    }
}
