package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.addons.DesignOSoulsAddOn;
import game.actions.PickUpSoulsAction;
import game.Player;
import game.enums.Abilities;
import game.interfaces.Soul;

/**
 * Class containing token of souls functionality
 */
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

    /**
     * only able to be picked up if a player interacts
     * @param actor an actor that will interact with this item
     * @return PickUpSouls actions if actor is player, null otherwise
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (actor instanceof Player) {
            return new PickUpSoulsAction(this);
        }
        return null;

    }

    /**
     * item cannot be dropped
     * @param actor an actor that will interact with this item
     * @return no action
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if (actor.hasCapability(Abilities.CAN_DROP_TOKEN_OF_SOULS) && !actor.isConscious()) {
            return new DropItemAction(this);
        } else {
            return null;
        }
    }


    @Override
    public boolean addSouls(int souls) {
        this.souls += souls;
        return true;
    }
}


