package game.actions;

import edu.monash.fit2099.engine.*;
import game.enemies.YhormTheGiant;
import game.enums.Abilities;
import game.enums.Status;
import game.weapons.StormRuler;

public class ChargeAction extends WeaponAction {

    public ChargeAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String returnString = null;
        if (weapon instanceof StormRuler ) {
            if (((StormRuler) weapon).getCharge() < 3) {
                actor.addCapability(Status.DISARM);
                actor.removeCapability(Status.HOSTILE_TO_ENEMY);
                Weapon weapon = actor.getWeapon();
                increaseCharge((StormRuler) weapon);
                returnString = menuDescription(actor);
            }

            if (((StormRuler) weapon).getCharge() == 3) {
                actor.removeCapability(Status.DISARM);
                actor.addCapability(Status.HOSTILE_TO_ENEMY);
                returnString = actor.getWeapon() + " is at full charge.";
                ((StormRuler) weapon).fullyCharged(actor, map);
            }
        }
        return returnString;

    }

    @Override
    public String menuDescription(Actor actor) {
        if (actor.getWeapon() instanceof StormRuler ) {
            return actor + " charges " + actor.getWeapon() + " | Charge: (" + ((StormRuler) weapon).getCharge() + "/3)";
        }
        else {
            return null;
        }
    }

    @Override
    public Action getNextAction() {
        return null;
    }

    public boolean increaseCharge(StormRuler weapon) {
        boolean flag = false;
        if ((weapon.getCharge() + 1) <= 3) {
            weapon.setCharge(weapon.getCharge() + 1);
            flag = true;
        }
        return flag;
    }






}
