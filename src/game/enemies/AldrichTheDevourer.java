package game.enemies;

import edu.monash.fit2099.engine.*;
import game.weapons.DarkmoonLongbow;

public class AldrichTheDevourer extends LordOfCinder{


    /**
     * Constructor.
     *
     * @param name
     * @param displayChar
     * @param hitPoints
     */
    public AldrichTheDevourer(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addItemToInventory(new DarkmoonLongbow());
        
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }
}
