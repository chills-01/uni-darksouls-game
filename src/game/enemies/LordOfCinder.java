package game.enemies;

import edu.monash.fit2099.engine.*;
import game.CinderLordItem;

/**
 * The boss of Design o' Souls
 * FIXME: This boss is Boring. It does nothing. You need to implement features here.
 * TODO: Could it be an abstract class? If so, why and how?
 */
public abstract class LordOfCinder extends Enemy {
    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints );
        this.addItemToInventory(new CinderLordItem());
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
