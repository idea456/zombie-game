package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	private Behaviour[] behaviour = {
			new WanderBehaviour(),
			new FoodBehaviour(),
			new CropBehaviour(),
			new DrivingBehaviour()
	};
	                                 

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 100, ZombieCapability.ALIVE);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}	

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// FIXME humans are pretty dumb, maybe they should at least run away from zombies?
		Action action = null;
		for (Behaviour behaviours: behaviour ) {
			action = behaviours.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
		
	}
	
	public ArrayList<Location> searchAdjacentLocation(Location actorLocation, GameMap map) {
		int x = actorLocation.x();	//x-coordinate
		int y = actorLocation.y();	//y-coordinate
		ArrayList <Location> adjacentLocations = new ArrayList <Location>();
		for (int i=-1; i<2; i++) {
			for (int j=-1; j<2; j++) {
				Location nextLocation = map.at(x+i,y+j);
				adjacentLocations.add(nextLocation);
			}
		}
		return adjacentLocations;
	}
	

}
