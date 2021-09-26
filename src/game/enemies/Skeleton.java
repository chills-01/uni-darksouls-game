package game.enemies;

import game.behaviours.WanderBehaviour;
import game.interfaces.Behaviour;
import game.weapons.Broadsword;

import java.util.ArrayList;

/**
 * Class housing Skeleton information (in addition to enemy)
 */

public class Skeleton extends Enemy{
    private Integer[] initialLocation; // for reset
    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    public Skeleton(Integer x, Integer y) {
        super("Skeleton", 'S', 100, 250);
        this.initialLocation = new Integer[] {x, y};
        behaviours.add(new WanderBehaviour());

        //creating Broadsword that is stored in Skeleton's inventory
        this.addItemToInventory(new Broadsword());

    }


}
