package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.Undead;

public class Cemetery extends Ground {
    public Cemetery() {
        super('X');
    }

    /**
     * Spawns new Undead 25% of the time
     * @param location The location of the Ground
     */
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
