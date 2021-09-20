package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.addons.DesignOSoulsAddOn;
import game.interfaces.Soul;

public class TokenOfSouls extends Item implements Soul, DesignOSoulsAddOn {
    int souls;
    public TokenOfSouls() {
        super("Token Of Souls", '$', true);
    }


    //Used when TokenOfSouls is picked up by Player:
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
    }



}


