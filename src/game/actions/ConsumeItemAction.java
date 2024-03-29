package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.items.ConsumableItem;
import game.interfaces.ConsumeAbility;

/**
 * Special action for consuming an item that heals the actor
 */

public class ConsumeItemAction extends Action {
    protected ConsumableItem item;

    public ConsumeItemAction(ConsumableItem item) {
        this.item = item;
    }

    public String execute(Actor actor, GameMap Map) {
        // check that actor is able to consume items
        if (actor instanceof ConsumeAbility) {
            // 40 % of max hitpoints
            int healPoints = (int) (0.4 * ((ConsumeAbility) actor).getMaxHitPoints());
            actor.heal(healPoints);
            item.reduceCharge();

            return menuDescription(actor);
        }
        return "Actor cannot consume items";
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from the " + item + " (" + item.getCharge() + "/" + item.getMaxCharge() + ")";
    }


}
