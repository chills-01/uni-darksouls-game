package game.enemies;

import edu.monash.fit2099.engine.*;
import game.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
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

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (this.hitPoints < 0.5 * maxHitPoints) {
            new Display().println("EMBER FORM ACTIVATED");
            ((WeaponItem) this.getWeapon()).addCapability(Status.EMBER_FORM); // todo fix downcast


        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
