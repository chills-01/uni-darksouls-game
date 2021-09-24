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
import game.weapons.StormRuler;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
					new Cemetery(), new Bonfire()); // changed this, put in string?

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
					"...........++.....................#___B___#.......................+.............",
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

			Actor player = new Player("Unkindled (Player)", '@', 100, bonfireLocation);
			world.addPlayer(player, gameMap.at(38, 11));

			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new YhormTheGiant("Yhorm the Giant", 'Y', 10));

			//Place Skeletons on map
			//todo add more skeletons
			ArrayList<Integer[]> skeletonCoordinates = new ArrayList<>();
			skeletonCoordinates.add(new Integer[] {10,10});
			skeletonCoordinates.add(new Integer[] {20,20});

			// todo: add more skeletons
			for (Integer[] loc : skeletonCoordinates) {
				Integer x = loc[0];
				Integer y = loc[1];
				gameMap.at(x, y).addActor(new Skeleton(x, y));
		}

			// place storm ruler
			gameMap.at(7, 25).addItem(new StormRuler());

			world.run();


	}
}
