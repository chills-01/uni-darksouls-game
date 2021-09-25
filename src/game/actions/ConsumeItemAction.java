package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.ConsumableItem;

public class ConsumeItemAction extends Action {
    protected ConsumableItem item;

    public ConsumeItemAction(ConsumableItem item) {
        this.item = item;
    }

    public String execute(Actor actor, GameMap Map) { //unsure about needing if statement {

            // todo is there a way to not hardcode this?
            int healPoints = (int) (0.4 * 200);
            actor.heal(healPoints);
            item.reduceCharge();

        return menuDescription(actor);
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from the " + item + " (" + item.getCharge() + "/" + item.getMaxCharge() + ")";
    }




}
