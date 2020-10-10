
package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;


/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		NewWorld world = new NewWorld(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		
		List<String> town = Arrays.asList(
		".....................#########..................................................",
		".....................#.......#..................................................",
		".....................#.......#..................................................",
		".....................###...###..................................................",
		".......................#...#....................................................",
		"................................................................................",
		".........................................................................#######",
		"......................................................................###......#",
		"...............................................................................#",
		"............................##################........................###......#",
		"............................#................#...........................#######",
		"............................#................#..................................",
		"............................#................#..................................",
		"............................#######....#######..................................",
		"................................................................................",
		"................................................................................",
		"#######.........................................................................",
		"#.....###.......................................................................",
		"#...............................................................................",
		"#.....###.......................................................................",
		"#######.........................................#...#...........................",
		"..............................................###...###.........................",
		"..............................................#.......#.........................",
		"..............................................#.......#.........................",
		"..............................................#########.........................");
		
		
		//-----------------------------GameMap-----------------------------
		NewMap gameMap = new NewMap(groundFactory, map, world, "compound");
		world.addGameMap(gameMap);
		Player player = new Player("Player", '@', 100);
		
//		//------------Add Player------------
//		world.addPlayer(player, gameMap.at(44, 20));
//		Direction ne = new Direction(gameMap, player);
//		ne.improveGetDirection("S");		
		
		
		
		//------------Add Vehicle------------
		gameMap.at(78, 10).addItem(new Vehicle("Car", 'C', false));

		// //------------Adding Humans------------
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}
//		
//		
		// gameMap.at(78, 8).addItem(new Plank());	// place a simple weapon on map
		gameMap.at(10,  10).addActor(new Human("Jack"));
		
		//------------Adding Zombies------------
		Zombie zombie = new Zombie("Groan");
		gameMap.at(0, 1).addActor(zombie);
		gameMap.at(30,  18).addActor(new Zombie("Boo"));
		gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));	
		
		//------------Adding Farmers------------
		gameMap.at(0, 24).addActor(new Farmer("Farmer 1"));
		gameMap.at(79, 0).addActor(new Farmer("Farmer 2"));		
		gameMap.at(38, 12).addActor(new Farmer("Farmer 3"));	
		gameMap.at(42, 10).addActor(new Farmer("Farmer 4"));	
		
		
		//-----------------------------TownMap-----------------------------
		NewMap townMap = new NewMap(groundFactory, town, world, "town");
		world.addGameMap(townMap);
		
		//------------Add Vehicle------------
		townMap.at(1, 0).addItem(new Vehicle("Car", 'C', false));
		
		//------------Add Player------------
		world.addPlayer(player, townMap.at(39, 20));
				
		//------------Add Humans------------
		String[] humans2 = {"Rhasta", "Kael", "Magina", "Traxis", "Gorgon", "Morph", "Butcher", "Nevermore", "Mercurial"};
		int x1, y1;
		for (String name: humans2) {
			do {
				x1 = (int) Math.floor(Math.random() * 40.0 + 20.0);
				y1 = (int) Math.floor(Math.random() * 10.0 + 5.0);
			}
			while (townMap.at(x1, y1).containsAnActor());
			townMap.at(x1,  y1).addActor(new Human(name));
			
		}
			
		//------------Add Shotgun and Sniper------------
		townMap.at(50, 23).addItem(new Shotgun());
		townMap.at(24, 1).addItem(new Sniper());
		townMap.at(2, 18).addItem(new Shotgun());
		townMap.at(77, 8).addItem(new Sniper());
		
		//------------Add Ammunition------------
		townMap.at(30, 10).addItem(new SniperAmmunition());
		townMap.at(32, 11).addItem(new SniperAmmunition());
		townMap.at(38, 12).addItem(new SniperAmmunition());
		townMap.at(36, 10).addItem(new ShotgunAmmunition());
		townMap.at(41, 11).addItem(new ShotgunAmmunition());
		townMap.at(44, 12).addItem(new ShotgunAmmunition());
		
		//------------Add Zombies------------
		String[] zombieName = {"Charger", "Jockey", "Spitter", "Witch", "Screamer", "Leaker", "Zeke"};
		int x2, y2;
		for (String name: zombieName) {
			do {
				x2 = (int) Math.floor(Math.random() * 40.0 + 20.0);
				y2 = (int) Math.floor(Math.random() * 10.0 + 5.0);
			}
			while (townMap.at(x2, y2).containsAnActor());
			townMap.at(x2,  y2).addActor(new Zombie(name));
		}
		world.run();		
		
	}	
}	
