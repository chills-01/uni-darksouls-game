package game.enemies;

import game.items.CinderLordItem;

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
    }
}
