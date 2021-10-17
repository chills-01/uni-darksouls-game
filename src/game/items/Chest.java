package game.items;

import edu.monash.fit2099.engine.*;
import game.actions.ChestOpenAction;
import game.interfaces.Resettable;

import java.sql.ResultSet;
import java.util.List;

public class Chest extends Item {

    /***
     * Constructor.
     *
     */
    private Location spawnLocation;
    public Chest(Location location) {
        super("Chest", '?', false);
        allowableActions.add(new ChestOpenAction(this));
        spawnLocation = location;
    }

    @Override
    public List<Action> getAllowableActions() {
        return super.getAllowableActions();
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

}
