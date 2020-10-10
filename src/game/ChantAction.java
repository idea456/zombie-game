package game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Special Action for chanting
 * Only reserved for voodoo priestess
 * 
 * @author Adrienne Rio Wongso Atmojo
 */
public class ChantAction extends Action {
	private HashMap<Zombie, Location> summonedZombies = new HashMap<Zombie, Location>();
	private int noOfSummonedZombies = 0;
	
	/**
	 * The constructor for ChantAction
	 * 
	 * @param noOfSummonedZombies the number of currently summoned zombies in the map
	 */
	public ChantAction(int noOfSummonedZombies) {
		this.noOfSummonedZombies = noOfSummonedZombies;
	}

	/**
	 * Perform the chanting action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// place 5 zombies on the map
		this.placeZombies(actor, 5, map);
		return actor + " is chanting...";
	}
	
	/**
	 * A method to place the number of zombies on the map
	 * 
	 * @param zombieCount the number of zombies to place on the map
	 * @param map the map to place the zombies in
	 * @param actor the actor enacting the zombie placement
	 */
	public void placeZombies(Actor actor, int zombieCount, GameMap map) {
		for(int i = 0; i < zombieCount; i++) {
			int x = (int)Math.floor(Math.random() * map.getXRange().max());
			int y = (int)Math.floor(Math.random() * map.getYRange().max());
			// if there is a zombie at the generated location, then generate a different location
			while(map.isAnActorAt(new Location(map, x, y))) {
				x = (int)Math.floor(Math.random() * map.getXRange().max());
				y = (int)Math.floor(Math.random() * map.getYRange().max());
			}
			int noOfSummonedZombies = ((VoodooPriestess)actor).getNoOfSummonedZombies() + i + 1;
			summonedZombies.put(new Zombie("Summoned " + ((NewMap)map).getName() + " Zombie " + noOfSummonedZombies), new Location(map, x, y));
		}
		
		for(Entry<Zombie, Location> entry : summonedZombies.entrySet()) {
			Zombie zombie = entry.getKey();
			Location location = entry.getValue();
			if(!(map.at(location.x(), location.y()).containsAnActor()))
				map.at(location.x(), location.y()).addActor(zombie);
		}
		
		((VoodooPriestess)actor).addNoOfSummonedZombies();
		System.out.println(((VoodooPriestess)actor).getNoOfSummonedZombies());
		
	}

	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " chants";
	}
	
	/**
	 * A getter method to get the total summoned zombies in the map
	 * 
	 * @return the HashMap for total summoned zombies
	 */
	public HashMap<Zombie, Location> getSummonedZombies() {
		// return a shallow copy of summonedZombies
		return new HashMap<Zombie, Location>(summonedZombies);
	}

}
