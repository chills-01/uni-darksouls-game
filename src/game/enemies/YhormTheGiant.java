package game.enemies;

import game.weapons.Broadsword;
import game.weapons.YhormGreatMachete;

public class YhormTheGiant extends LordOfCinder{
    public YhormTheGiant(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        //creating Yhorm's Great Machete that is stored in Yhorm's inventory
        this.addItemToInventory(new YhormGreatMachete());
    }
}
