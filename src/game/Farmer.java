package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * A class that represents Farmer which is extended from Human.
 * @author Sean Hii Jun Wei
 *
 */
public class Farmer extends Human {
	/**
	 * wandering behavior of Farmers. Basically let farmers to move around on its own 
	 */
	private WanderBehaviour wandering = new WanderBehaviour();
	
	/**
	 * crop behavior of farmer. Let farmers to perform crop actions
	 */
	private CropBehaviour cropBehaviour = new CropBehaviour();
	
	/**
	 * Constructor
	 * @param name name of farmer
	 */
	public Farmer(String name) {
		// TODO Auto-generated constructor stub
		super(name,'F',100);
	}
	
	/**
	 * setter for the name of farmer
	 * @param name name of farmer
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * getter for the name of farmer
	 * @return name of farmer
	 */
	public String getName() {
		return new String (name);
	}
	
	/**
	 * If the farmer is not at the map boundaries, it has half the chance of wandering around or perform crop action (depending on the ground of the location). 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Farmer is
	 * @param display the Display where the description of farmer's action will show
	 */
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		double chance = 0.5;
		double rand = Math.random();
		Location location = map.locationOf(this);			//location of farmer
		int x = location.x();
		int y = location.y();

		List <Integer>xBoundaries = Arrays.asList(0,79);	//x-coordinate
		List <Integer> yBoundaries = Arrays.asList(0,24);	//y-coordinate
		
		//if the farmer is not at the boundaries of the map it has half the chance of wandering around or perform crop action.
		//if the farmer is at the boundaries of the map, it will only perform crop action
		if(!xBoundaries.contains(x)) {
			if(!yBoundaries.contains(y)) {
				if(rand<= chance) {
					return wandering.getAction(this, map);
				}
				else{
					return cropBehaviour.getAction(this, map);
				}
			}
			else {
				return wandering.getAction(this, map);
			}
		}
		else {
			return wandering.getAction(this, map);
		}
	}
}

