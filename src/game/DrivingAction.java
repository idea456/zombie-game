package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class of driving action which extends Action
 * @author Sean Hii Jun Wei
 *
 */
public class DrivingAction extends Action {
	/**
	 * destination map
	 */
	private NewMap destination;
	/**
	 * destination world
	 */
	private NewWorld world;
	
	/**
	 * Cosntructor
	 * @param world destination world
	 * @param map destination map
	 */
	public DrivingAction(NewWorld world, GameMap map) {
		this.world = world;
		this.destination = this.getDestination(map);
	}
	
	/**
	 * moves the player from a map to another map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		this.movePlayerToMap(actor, (NewMap)map, this.getDestination(map));
		return menuDescription(actor);
	}
	
	public NewMap getDestination(GameMap map) {
		ArrayList<GameMap> mapList = this.world.getGameMaps();
		
		// circle through the mapList to find the next map
		// if it reaches the end of the list then go back to the first map
		// this way the player travels around the maps in a circular fashion
		int index = mapList.indexOf(map);
		index++;
		if(index >= mapList.size()) {
			index = 0;
		}
		return (NewMap)mapList.get(index);
	}
	
	/**
	 * a method that moves the player to a new map from an old map
	 * @param actor the player
	 * @param previousMap previous map
	 * @param nextMap destination map
	 */
	public void movePlayerToMap(Actor actor, NewMap previousMap, NewMap nextMap) {
		this.destination = nextMap;
		// remove the player from the current map first
		previousMap.removeActor(actor);
		// then add the player at the next map
		int x = (int)(Math.random() * nextMap.getXRange().max());
		int y = (int)(Math.random() * nextMap.getYRange().max());
		
		
		while(nextMap.at(x, y).containsAnActor()) {
			x = (int)(Math.random() * nextMap.getXRange().max());
			y = (int)(Math.random() * nextMap.getYRange().max());
		}
		
		nextMap.addActor(actor, nextMap.at(1, 0));
		
	}
	
	/**
	 * A method that concatenate the string which describes what happened when action is executed
	 * @param actor actor performing the action
	 * @return return the string of description
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor.toString()+" drives to " + this.destination.getName();
	}

}
