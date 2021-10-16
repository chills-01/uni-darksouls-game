package game;

import edu.monash.fit2099.engine.Location;
import game.enums.Status;
import game.ground.Bonfire;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class BonfireManager {
    private HashMap<Location, Bonfire> locationToBonfire;
    private HashMap<Bonfire, Location> bonfireToLocation;
    private Bonfire currentBonfire;

    public BonfireManager() {
        locationToBonfire = new HashMap<>();
        bonfireToLocation = new HashMap<>();
    }

    public void addBonfire(Location location, Bonfire bonfire) {
        //todo return true or false here?
        locationToBonfire.put(location, bonfire);
        bonfireToLocation.put(bonfire, location);

    }

    public void setCurrentBonfire(Bonfire bonfire) {
        currentBonfire = bonfire;
    }

    public Bonfire getCurrentBonfire() {
        return currentBonfire;
    }

    public Location getCurrentBonfireLocation() {
        return bonfireToLocation.get(currentBonfire);
    }

    public Bonfire getBonfire(Location location) {
        return locationToBonfire.get(location);
    }

    public Collection<Bonfire> getAllActiveBonfires() {
        Collection<Bonfire> activeBonfires = new ArrayList<>();
        for (Bonfire b : locationToBonfire.values() ) {
            if (b.hasCapability(Status.BONFIRE_ACTIVE)) {
                activeBonfires.add(b);
            }
        }
        return activeBonfires;
    }

    public Location getLocation(Bonfire bonfire) {
        return bonfireToLocation.get(bonfire);
    }
}
