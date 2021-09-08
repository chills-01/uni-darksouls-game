package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

public class Skeleton extends Enemy{
    public Skeleton() {
        super("Skeleton", 'S', 100);

    }

    // todo move playturn (Undead) into Enemy to implement same method
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
