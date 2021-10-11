package game.actions;

import edu.monash.fit2099.engine.*;
import game.enemies.Mimic;
import game.items.Chest;
import game.items.TokenOfSouls;

import java.util.ArrayList;
import java.util.List;

public class ChestOpenAction extends Action {
    Chest chest;

    public ChestOpenAction(Chest chest) {
        this.chest = chest;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int chance  = new java.util.Random().nextInt(2);
        System.out.println(chance);
        String rString;

        if (chance == 0) {
            rString = dropTokenOfSouls(map);
        }
        else {
            rString = spawnMimic(actor, map);

        }
        // remove the chest
        map.at(chest.getSpawnLocation().x(), chest.getSpawnLocation().y()).removeItem(chest);

        return rString;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Open the Chest";
    }

    private String dropTokenOfSouls(GameMap map) {
        // Spawn the token of souls
        // Place on exits or current location if player can step on said location

        // anywhere between 1 and 3
        int numTokens = new java.util.Random().nextInt(3);

        for (int i = 0; i <= numTokens; i++) {
            TokenOfSouls tokenOfSouls = new TokenOfSouls();
            tokenOfSouls.addSouls(100);
            map.at(chest.getSpawnLocation().x(), chest.getSpawnLocation().y()).addItem(tokenOfSouls);

        }
        return "Dropped " + numTokens + " Token of Souls";
    }

    private String spawnMimic(Actor actor, GameMap map) {
        List<Exit> exits = map.at(chest.getSpawnLocation().x(), chest.getSpawnLocation().y()).getExits();
        for (Exit e : exits) {
            if (e.getDestination().canActorEnter(actor) && !e.getDestination().containsAnActor()) {
                System.out.println("trying to place");
                map.at(e.getDestination().x(), e.getDestination().y()).addActor(new Mimic());
                break;
                //todo check if successful
            }
        }
        return "Mimic spawned";
    }


}
