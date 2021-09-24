package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class ResetAction extends Action {
    private Actor actor;
    private Location bonfireLocation;
    private Location playerLocation;

    public ResetAction(Actor player, Location bonfireLocation, Location playerLocation) {
        this.actor = player;
        this.bonfireLocation = bonfireLocation;
        this.playerLocation = playerLocation;
    }


    @Override
    public String execute(Actor actor, GameMap map) {




        // here we should discern if this is a bonfire reset or a death
        if (! actor.isConscious()) { // if dead
            map.moveActor(actor, bonfireLocation);
            ResetManager.getInstance().run(map);


            return "\n" +
                    "                                                                          \n" +
                    "                                                                          \n" +
                    "____     ___  ____   ____     ___      ________  _____________ ________   \n" +
                    "`MM(     )M' 6MMMMb  `MM'     `M'      `MMMMMMMb.`MM`MMMMMMMMM `MMMMMMMb. \n" +
                    " `MM.    d' 8P    Y8  MM       M        MM    `Mb MM MM      \\  MM    `Mb \n" +
                    "  `MM.  d' 6M      Mb MM       M        MM     MM MM MM         MM     MM \n" +
                    "   `MM d'  MM      MM MM       M        MM     MM MM MM    ,    MM     MM \n" +
                    "    `MM'   MM      MM MM       M        MM     MM MM MMMMMMM    MM     MM \n" +
                    "     MM    MM      MM MM       M        MM     MM MM MM    `    MM     MM \n" +
                    "     MM    MM      MM MM       M        MM     MM MM MM         MM     MM \n" +
                    "     MM    YM      M9 YM       M        MM     MM MM MM         MM     MM \n" +
                    "     MM     8b    d8   8b     d8        MM    .M9 MM MM      /  MM    .M9 \n" +
                    "    _MM_     YMMMM9     YMMMMM9        _MMMMMMM9'_MM_MMMMMMMMM _MMMMMMM9' \n" +
                    "                                                                          \n" +
                    "                                                                          \n" +
                    "                                                                          \n";
        } else {
            ResetManager.getInstance().run(map);
            return "Player reset the game";
        }
    }

    @Override
    // only applies to bonfire reset
    public String menuDescription(Actor actor) {
        return "Rest at Firelink Shrine bonfire";
    }

}
