package game.items;

import game.actions.ConsumeItemAction;
import game.interfaces.Resettable;

/**
 * Class representing the Estus flask
 */
public class EstusFlask extends ConsumableItem implements Resettable {

    public EstusFlask() {
        super("Estus Flask", 'E', false, 3); //cannot be dropped, hence is not portable
        this.charge = 3;
        this.allowableActions.add(new ConsumeItemAction(this));
        registerInstance();
    }

    @Override
    public void resetInstance() {
        // recharges the item
        this.charge = maxCharge;
    }

    @Override
    public boolean isExist() {
        return true;
    }
}
