package game;

import edu.monash.fit2099.engine.Actor;
import game.actions.ConsumeItemAction;
import game.enums.Abilities;
import game.interfaces.Consumable;
import game.interfaces.Resettable;

public class EstusFlask extends ConsumableItem implements Resettable {

    public EstusFlask() {
        super("Estus Flask", 'E', false, 3); //cannot be dropped, hence is not portable
        this.charge = 3;
        this.allowableActions.add(new ConsumeItemAction(this));
        registerInstance();
    }

    @Override
    public void resetInstance() {
        this.charge = maxCharge;
//        this.player.setHitPoints(this.player.getMaxHitPoints());
    }

    @Override
    public boolean isExist() {
        return true;
    }
}
