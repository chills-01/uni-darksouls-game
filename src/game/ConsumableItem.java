package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.actions.ConsumeItemAction;

public class ConsumableItem extends Item {
    protected int charge;
    protected int maxCharge;
    protected Player player;

    public ConsumableItem(String name, char displayChar, boolean portable, int maxCharge, Actor actor) {
        super(name, displayChar, portable);
        this.maxCharge = maxCharge;
        this.player = player;
    }


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