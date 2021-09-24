package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.addons.DesignOSoulsAddOn;
import game.interfaces.Soul;

public class TokenOfSouls extends Item implements Soul, DesignOSoulsAddOn {
    int souls;
    public TokenOfSouls() {
        super("Token Of Souls", '$', true);
    }


    //Used when TokenOfSouls is picked up by Player:
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (actor instanceof Player) {
            return new PickUpSoulsAction(this);
        }
        return null;

    }


    @Override
    public boolean addSouls(int souls) {
        this.souls += souls;
        return true;
    }
}


