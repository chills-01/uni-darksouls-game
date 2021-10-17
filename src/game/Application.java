package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.enemies.AldrichTheDevourer;
import game.enemies.Skeleton;
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
					new Cemetery());

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
			GameMap profaneCapital = new GameMap(groundFactory, map);
			world.addGameMap(profaneCapital);

			map = Arrays.asList(
				".+++++++++++........................................................++++++++++++",
				"+++++.....................++....................################################",
				"+++++..X........................................#..............#................",
				"+++...........+.....++++++......................#.......#...........#...........",
				"+++++++..............................X..++++++++#.#___..._...................#..",
				"+++................+...............++++++++++...#...._..._.......#..............",
				"++...............++++............++++++.........#....#...#...........#..........",
				"+...................................++++++......#...._..._.................#....",
				".........................+++............++++++..########################__######",
				"+++++.....X..............................................++++..............+++++",
				"++++..........++++............................................+++++++...........",
				"++.........................+++++...............+++.........................+++++");

			GameMap anorLondo = new GameMap(groundFactory, map);
			world.addGameMap(anorLondo);

			// bonfire manager
			BonfireManager bonfireManager = new BonfireManager();


			// firelink shrine bonfire
			Location flBonfireLocation = profaneCapital.at(38, 11); // to pass into player constructor
			Bonfire flBonfire = new Bonfire("Firelink Shrine", bonfireManager);
			bonfireManager.addBonfire(flBonfireLocation, flBonfire);

			//anor londo bonfire
			Location alBonfireLocation = anorLondo.at(55, 0);
			Bonfire alBonfire = new Bonfire("Anor Londo", bonfireManager);
			bonfireManager.addBonfire(alBonfireLocation, alBonfire);

			// placing in map
			profaneCapital.at(flBonfireLocation.x(), flBonfireLocation.y()).setGround(flBonfire);
			anorLondo.at(alBonfireLocation.x(), alBonfireLocation.y()).setGround(alBonfire);

			// set initial bonfire
			bonfireManager.setCurrentBonfire(flBonfire);

			// placing fog doors
			profaneCapital.at(38,25).setGround(new FogDoor(anorLondo.at(50, 0), "Anor Londo"));
			anorLondo.at(50, 0).setGround(new FogDoor(profaneCapital.at(38,25), "Profane Capital"));

			// place player at bonfire
			Actor player = new Player("Unkindled (Player)", '@', 200, bonfireManager.getCurrentBonfireLocation(), bonfireManager);
			world.addPlayer(player, profaneCapital.at(38, 11));

			// Place Yhorm the Giant/boss in the map
			profaneCapital.at(6, 25).addActor(new YhormTheGiant("Yhorm the Giant", 'Y', 500));

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
				profaneCapital.at(x, y).addActor(new Skeleton(x, y));
			}

			anorLondo.at(20, 5).addActor(new Skeleton(20, 5));
			anorLondo.at(15, 10).addActor(new Skeleton(15, 10));



			// place storm ruler
			profaneCapital.at(7, 25).addItem(new StormRuler());

			// place Aldrich the Devourer
			anorLondo.at(59,5 ).addActor(new AldrichTheDevourer("Aldrich the Devourer", 'A', 350));

			// place a chest
			Location chestLocation = profaneCapital.at(43, 11);
			chestLocation.addItem(new Chest(chestLocation));
			chestLocation = profaneCapital.at(23, 5);
			chestLocation.addItem(new Chest(chestLocation));
			chestLocation = anorLondo.at(25, 1);
			chestLocation.addItem(new Chest(chestLocation));


			world.run();


	}
}
