package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.Undead;

//todo add to gamemap in Application.java?
public class Cemetery extends Ground {
    public Cemetery() {
        super('X');
    }

    @Override
    public void tick(Location location) {
        // 25% chance of generating undead
        int upperBound = 4;
        int chance = new java.util.Random().nextInt(upperBound);

        if (chance < 1) {
            location.addActor(new Undead("Undead"));

        }

    }

}
