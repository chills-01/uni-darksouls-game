package game.enemies;

import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;
import game.items.Chest;
import game.items.TokenOfSouls;
import game.weapons.NoWeapon;

public class Mimic extends Enemy {
//todo reset features
    /**
     * Class for Mimic enemy
     */

    private Location chestLocation; // location of initial chest
    public Mimic(Location chestLocation) {
        super("Mimic", 'M', 100, 200);
        this.chestLocation = chestLocation;


        addTokensToInventory();
        addCapability(Abilities.CAN_DROP_TOKEN_OF_SOULS);
    }
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new NoWeapon(55, "kicks");
    }

    /**
     * Adds 1 to 3 tokens of souls to inventory for Mimic to drop on death
     */
    private void addTokensToInventory() {
        int numTokens = new java.util.Random().nextInt(3);

        for (int i = 0; i <= numTokens; i++) {
            TokenOfSouls tokenOfSouls = new TokenOfSouls();
            tokenOfSouls.addSouls(100);
            addItemToInventory(tokenOfSouls);
        }
    }

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public void resetInstance() {
        Chest chest = new Chest(chestLocation);
        if (!chestLocation.getItems().contains(chest)) {
            chestLocation.addItem(chest);
        }

    }
}
