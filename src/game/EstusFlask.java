package game;

import edu.monash.fit2099.engine.Item;
import game.interfaces.Consummable;

public class EstusFlask extends Item implements Consummable {
    public EstusFlask() {
        super("Estus Flask", 'E', false); //cannot be dropped, hence is not portable
    }
}
