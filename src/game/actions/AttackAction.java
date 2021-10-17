package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.Player;
import game.enemies.Enemy;
import game.enemies.LordOfCinder;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			Actions dropActions = new Actions();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(target)); //fixme changed argument from actor to target
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

			result += System.lineSeparator() + target + " is killed.";

			// special message for lord of cinder
			if (target instanceof LordOfCinder) {
				result = "\n" +
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
						"                                                                                                                                                                                                 \n" +
						"                                                                                                                                                                                                 \n";
			}
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
