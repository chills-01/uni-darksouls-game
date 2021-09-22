package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class ResetAction extends Action {
    private Actor actor;
    private Location bonfireLocation;
    private Location playerLocation;
    public ResetAction(Player player, Location bonfireLocation, Location playerLocation) {
        this.actor = player;
        this.bonfireLocation = bonfireLocation;
        this.playerLocation = playerLocation;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, bonfireLocation);

        ResetManager.getInstance().run();

        return "Soft reset occured";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    public String playerKilled() {
        return null;
    }
}
