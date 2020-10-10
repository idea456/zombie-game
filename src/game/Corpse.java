package game;
import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.GameMap;
/**
 * A class extended from Item which represents a corpse of Human when they die.
 * Corpse will rise from the dead as Zombie after 5-10 turns of the game. Number of turns to revive is determined by having random number between 5 to 10 inclusive.
 * The corpse will rise from the dead from its own location. If there is an Actor at that location at that time, it will search for the next suitable adjacent location to rise.
 * @author Sean Hii Jun Wei
 *
 */

public class Corpse extends Item{
	/**
	 * age is used as number of turns after Human died, so that they can rise as Zombie after set turns.
	 */
	private int age;
	
	
	/**Constructor
	 * @param name the name it will have after rised from the dead
	 */
	public Corpse(String name) {
		super(name, 'D',true);
		this.age = 0;
	}
	/**
	 * setter for name
	 * @param name name of the corpse
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getter for name
	 * @return the name of the corpse
	 */
	public String getName() {
		return new String (name);
	}
	
	/**
	 * setter for the display character of corpse
	 * @param displayChar character that represents Corpse
	 */
	public void setDisplayChar(char displayChar) {
		this.displayChar = displayChar;
	}
	
	/**
	 * getter for display character
	 * @return the character that represents Corpse
	 */
	public char getDisplayChar() {
		return displayChar;
	}
	
	/**
	 * setter for portability of corpse
	 * @param portable portability of corpse
	 */
	public void setPortable(boolean portable) {
		this.portable = portable;
	}
	
	/**
	 * getter for portability of corpse
	 * @return portability of corpse
	 */
	public boolean getPortable() {
		return portable;
	}
	
	/**
	 * Inform an Item on the ground of the passage of time.This method is called once per turn, if the item rests upon the ground.
	 * Corpse will rise from death as Zombie after 5-10 turns. If there is an Actor at the location that Corpse is on upon rising, Corpse will rise as Zombie at next 
	 * possible location without Actor.
	 * @param location location of corpse
	 */
	public void tick (Location location) {
		super.tick(location);					
		ArrayList<Location> suitableLocation = searchAdjacentLocation(location);	//a list of locations that is adjacent to corpse
		suitableLocation.remove(location);											//remove corpse current location from suitableLocation
		Random rand = new Random();
		int max = 10;
		int min = 5;
		int targetAge = rand.nextInt(max-min+1)+min;								//randomly generates an integer to determine after how many rounds will the corpse rise as zombie
		age++;
		if (age >= targetAge) {
			Zombie zombie = new Zombie(this.name);
			
			//check if the current location contains an Actor or not. If no, corpse will rise as zombie. If yes, corpse will rise as zombie at next suitable location
			while(!location.canActorEnter(zombie)) {								
				for (Location locations: suitableLocation) {
					location = locations;
					suitableLocation.remove(locations);
					break;
				}
			}
			location.addActor(zombie);				//add zombie to the map
			location.removeItem(this);				//remove corpse as item from the location
		}
	}
	
	/**
	 * A method that checks the adjacent locations of current location
	 * @param actorLocation current location of corpse
	 * @return a list of locations that are adjacent to location of corpse
	 */
	public ArrayList<Location> searchAdjacentLocation(Location actorLocation) {
		GameMap map = actorLocation.map();
		int x = actorLocation.x();	//x-coordinate
		int y = actorLocation.y();	//y-coordinate
		ArrayList <Location> adjacentLocations = new ArrayList <Location>();
		for (int i=-1; i<2; i++) {
			for (int j=-1; j<2; j++) {
				if(x+i >= 0 && x+i<=map.getXRange().max() && y+j>=0 && y<=map.getYRange().max()) {
					Location nextLocation = map.at(x+i,y+j);		//generates all adjacent location of current location
					adjacentLocations.add(nextLocation);
				}
			}
		}
		return adjacentLocations;
	}
}
