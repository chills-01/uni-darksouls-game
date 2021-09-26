package game.actions;

import edu.monash.fit2099.engine.*;
import game.Player;
import game.enums.Status;
import game.weapons.StormRuler;

/**
 * Special action to perform a Wind Slash (storm ruler special ability)
 */
public class WindSlashAction extends WeaponAction {
    protected Actor target;

    public WindSlashAction(WeaponItem weaponItem, Actor target) {
        super(weaponItem);
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon instanceof StormRuler) {
            // disables critical strike ability temporarily
            weapon.addCapability(Status.DISABLE_CRITICAL_STRIKE);
            // double damage all the time
            int damage = weapon.damage() * 2;
            target.hurt(damage);
            // stuns target
            target.addCapability(Status.STUNNED);

            // resets statuses and charge
            ((StormRuler) weapon).preRecharge();
            weapon.removeCapability(Status.DISABLE_CRITICAL_STRIKE);
            target.removeCapability(Status.STUNNED);
            actor.removeCapability(Status.STORMRULER_FULLY_CHARGED);

            if (!target.isConscious()) {
                Actions dropActions = new Actions();
                // drop all items
                for (Item item : target.getInventory())
                    dropActions.add(item.getDropAction(actor));
                for (Action drop : dropActions)
                    drop.execute(target, map);

                // remove actor if not the player
                if (!(target instanceof Player)) {
                    map.removeActor(target);
                }
                // transfer soul to player if actor is player?
                if (actor instanceof Player) {
                    target.asSoul().transferSouls(actor.asSoul());
                }

                return "\n" +
                        "                                                                                                                                                                                                 \n" +
                        "                                                                                                                                                                                                 \n" +
                        "____       ____   ________   ________            ____   ________          ____   ______      ___________  __________ ________          ________   _     ____     ____     __________ ___      ___\n" +
                        "`MM'      6MMMMb  `MMMMMMMb. `MMMMMMMb.         6MMMMb  `MMMMMMM         6MMMMb/ `MM`MM\\     `M'`MMMMMMMb.`MMMMMMMMM `MMMMMMMb.        `MMMMMMM  dM.    `MM'     `MM'     `MMMMMMMMM `MM\\     `M'\n" +
                        " MM      8P    Y8  MM    `Mb  MM    `Mb        8P    Y8  MM    \\        8P    YM  MM MMM\\     M  MM    `Mb MM      \\  MM    `Mb         MM    \\ ,MMb     MM       MM       MM      \\  MMM\\     M \n" +
                        " MM     6M      Mb MM     MM  MM     MM       6M      Mb MM            6M      Y  MM M\\MM\\    M  MM     MM MM         MM     MM         MM      d'YM.    MM       MM       MM         M\\MM\\    M \n" +
                        " MM     MM      MM MM     MM  MM     MM       MM      MM MM   ,        MM         MM M \\MM\\   M  MM     MM MM    ,    MM     MM         MM   , ,P `Mb    MM       MM       MM    ,    M \\MM\\   M \n" +
                        " MM     MM      MM MM    .M9  MM     MM       MM      MM MMMMMM        MM         MM M  \\MM\\  M  MM     MM MMMMMMM    MM    .M9         MMMMMM d'  YM.   MM       MM       MMMMMMM    M  \\MM\\  M \n" +
                        " MM     MM      MM MMMMMMM9'  MM     MM       MM      MM MM   `        MM         MM M   \\MM\\ M  MM     MM MM    `    MMMMMMM9'         MM   `,P   `Mb   MM       MM       MM    `    M   \\MM\\ M \n" +
                        " MM     MM      MM MM  \\M\\    MM     MM       MM      MM MM            MM         MM M    \\MM\\M  MM     MM MM         MM  \\M\\           MM    d'    YM.  MM       MM       MM         M    \\MM\\M \n" +
                        " MM     YM      M9 MM   \\M\\   MM     MM       YM      M9 MM            YM      6  MM M     \\MMM  MM     MM MM         MM   \\M\\          MM   ,MMMMMMMMb  MM       MM       MM         M     \\MMM \n" +
                        " MM    / 8b    d8  MM    \\M\\  MM    .M9        8b    d8  MM             8b    d9  MM M      \\MM  MM    .M9 MM      /  MM    \\M\\         MM   d'      YM. MM    /  MM    /  MM      /  M      \\MM \n" +
                        "_MMMMMMM  YMMMM9  _MM_    \\M\\_MMMMMMM9'         YMMMM9  _MM_             YMMMM9  _MM_M_      \\M _MMMMMMM9'_MMMMMMMMM _MM_    \\M\\_      _MM__dM_     _dMM_MMMMMMM _MMMMMMM _MMMMMMMMM _M_      \\M \n" +
                        "                                                                                                                                                                                                 \n" +
                        "                                                                                                                                                                                                 \n" ;
            }

            return menuDescription(actor);
        } else {
            return "Weapon unable to perform action";
        }

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Wind Slash and stuns " + target;
    }


}
