package game.enemies;

import edu.monash.fit2099.engine.*;
import game.WanderBehaviour;
import game.interfaces.Behaviour;
import game.weapons.Broadsword;

import java.util.ArrayList;

public class Skeleton extends Enemy{
    private Integer[] initialLocation;
    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    public Skeleton(Integer x, Integer y) {
        super("Skeleton", 'S', 100);
        this.initialLocation = new Integer[] {x, y};
        behaviours.add(new WanderBehaviour());
        //creating Broadsword that is stored in Skeleton's inventory
        this.addItemToInventory(new Broadsword());

    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
