package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.enemies.AldrichTheDevourer;
import game.enemies.Skeleton;
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
			GameMap profaneCapital = new GameMap(groundFactory, map);
			world.addGameMap(profaneCapital);

			map = Arrays.asList(
				".+++++++++++........................................................++++++++++++",
				"+++++.....................++....................################################",
				"+++++..X........................................#..............#................",
				"+++...........+.....++++++......................#.......#...........#...........",
				"+++++++..............................X..++++++++#.#___..._...................#..",
				"+++..........X.....+...............++++++++++...#...._..._.......#..............",
				"++...............++++............++++++.........#....#...#...........#..........",
				"+...................................++++++......#...._..._.................#....",
				".........................+++............++++++..########################__######",
				"+++++.....X..............................................++++..............+++++",
				"++++..........++++...............X............................+++++++...........",
				"++.........................+++++...............+++.........................+++++");

			GameMap anorLondo = new GameMap(groundFactory, map);
			world.addGameMap(anorLondo);


			Location bonfireLocation = profaneCapital.at(38, 11); // to pass into player constructor
			profaneCapital.at(bonfireLocation.x(), bonfireLocation.y()).setGround(new Bonfire());

			// placing fog doors
			profaneCapital.at(38,25).setGround(new FogDoor(anorLondo.at(50, 0), "Anor Londo"));
			anorLondo.at(50, 0).setGround(new FogDoor(profaneCapital.at(38,25), "Profane Capital"));

			// place player at bonfire
			Actor player = new Player("Unkindled (Player)", '@', 200, bonfireLocation);
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



			// place storm ruler
			profaneCapital.at(7, 25).addItem(new StormRuler());

			anorLondo.at(59,5 ).addActor(new AldrichTheDevourer("Aldrich the Devourer", 'A', 350));

			world.run();


	}
}
