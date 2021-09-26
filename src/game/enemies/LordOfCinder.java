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

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
}
