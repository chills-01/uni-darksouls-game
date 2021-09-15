package game.enemies;

import edu.monash.fit2099.engine.*;
import game.WanderBehaviour;
import game.interfaces.Behaviour;

import java.util.ArrayList;

public class Skeleton extends Enemy{
    private Integer[] initialLocation;
    private ArrayList<Behaviour> behaviours = new ArrayList<>();

    public Skeleton(Integer x, Integer y) {
        super("Skeleton", 'S', 100);
        this.initialLocation = new Integer[] {x, y};
        behaviours.add(new WanderBehaviour());

    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
