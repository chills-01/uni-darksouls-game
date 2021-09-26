package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.Player;
import game.actions.ConsumeItemAction;

/**
 * Parent class for all consumable items
 */
public class ConsumableItem extends Item {
    protected int charge;
    protected int maxCharge;
    /**
     * Constructor
     *
     * @param name        name of item
     * @param displayChar display character of item
     * @param portable    represents if item is portable or not
     * @param maxCharge   the maximum number of uses
     */
    public ConsumableItem(String name, char displayChar, boolean portable, int maxCharge) {
        super(name, displayChar, portable);
        this.maxCharge = maxCharge;
    }

    /**
     * decrements the item when used
     *
     * @return true if item is consumed, false if depleted
     */
    public boolean reduceCharge() {
        boolean flag = false;
        if (this.charge > 0) {
            this.charge -= 1;
            flag = true;
        }
        return flag;
    }


    public int getCharge() {
        return charge;
    }

    public int getMaxCharge() {
        return maxCharge;
    }


}
