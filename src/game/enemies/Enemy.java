package game.enemies;

import edu.monash.fit2099.engine.Actor;

public abstract class Enemy extends Actor {
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        // todo make attack and follow player
    }

}
