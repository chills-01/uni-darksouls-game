package game;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.enemies.Skeleton;
import game.enemies.Undead;
import game.enemies.YhormTheGiant;
import game.ground.*;
import game.items.Chest;
import game.weapons.StormRuler;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
					new Cemetery()); // changed this, put in string?

			List<String> map = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++......",
					"..............................X.........................................++......",
					".........................................................................+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					".............................++.......+......................++++...............",
					"................X............................................+++++++............",
					"..................................###___###.........X.........+++...............",
					"..................................#_______#......................+++............",
					"...........++.....................#_______#.......................+.............",
					".........+++......................#_______#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................................++++..........",
					"+....................X.............................................++...........",
					"++...+++.........................................................++++...........",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++..............X..........+....++......",
					"#####___#####++++......................+...............................+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++...........................................................+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Location bonfireLocation = gameMap.at(38, 11); // to pass into player constructor
			gameMap.at(bonfireLocation.x(), bonfireLocation.y()).setGround(new Bonfire());

			// place player at bonfire
			Actor player = new Player("Unkindled (Player)", '@', 200, bonfireLocation);
			world.addPlayer(player, gameMap.at(43, 10));

			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new YhormTheGiant("Yhorm the Giant", 'Y', 500));

			//Place Skeletons on map
			ArrayList<Integer[]> skeletonCoordinates = new ArrayList<>();
			// coords
			skeletonCoordinates.add(new Integer[] {10,10});
			skeletonCoordinates.add(new Integer[] {20,20});
			skeletonCoordinates.add(new Integer[] {35,7});
			skeletonCoordinates.add(new Integer[] {46,23});
			skeletonCoordinates.add(new Integer[] {25,18});
			skeletonCoordinates.add(new Integer[] {13,17});
			// placing them
			for (Integer[] loc : skeletonCoordinates) {
				Integer x = loc[0];
				Integer y = loc[1];
				gameMap.at(x, y).addActor(new Skeleton(x, y));
		}

			// place storm ruler
			gameMap.at(7, 25).addItem(new StormRuler());

			// place a chest
			gameMap.at(43, 11).addItem(new Chest(new Location(gameMap, 43, 11)));

			world.run();


	}
}
