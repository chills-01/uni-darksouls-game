package game.enemies;

import game.interfaces.Behaviour;
import game.items.CinderLordItem;

import java.util.ArrayList;

/**
 * The boss of Design o' Souls
 */
public abstract class LordOfCinder extends Enemy {
    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, 5000 );
        this.addItemToInventory(new CinderLordItem());

        behaviours = new ArrayList<Behaviour>(); // Lord of Cinder's do not wander
    }
}
