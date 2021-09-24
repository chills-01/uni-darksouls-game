package game.enemies;

import game.WanderBehaviour;
import game.interfaces.Behaviour;
import game.weapons.Broadsword;
import game.weapons.YhormGreatMachete;

import java.util.ArrayList;

public class YhormTheGiant extends LordOfCinder{
    public YhormTheGiant(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        //creating Yhorm's Great Machete that is stored in Yhorm's inventory
        this.addItemToInventory(new YhormGreatMachete());

        behaviours = new ArrayList<Behaviour>(); // Yhorm should not wander
    }
}
