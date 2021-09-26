package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Status;
import game.weapons.StormRuler;

public class WindSlashAction extends WeaponAction {
    protected Actor target;

    public WindSlashAction(WeaponItem weaponItem, Actor target) {
        super(weaponItem);
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        weapon.addCapability(Status.DISABLE_CRITICAL_STRIKE);
        int damage = weapon.damage() * 2;
        target.hurt(damage);
        target.addCapability(Status.STUNNED);


        ((StormRuler) weapon).preRecharge();
        weapon.removeCapability(Status.DISABLE_CRITICAL_STRIKE);
        target.removeCapability(Status.STUNNED);
        actor.removeCapability(Status.STORMRULER_FULLY_CHARGED);
        return menuDescription(actor);

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Wind Slash and stuns " + target;
    }


}
