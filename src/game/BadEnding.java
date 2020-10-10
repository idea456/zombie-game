package game;

import java.util.Iterator;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class for bad endings
 *
 * @author Adrienne Rio Wongso Atmojo
 */
public class BadEnding extends Ending {
	
	/**
	 * Constructor for bad ending
	 *
	 * @param world the world to check for bad endings
	 */
	public BadEnding(NewWorld world) {
		super(world);
	}

	/**
	 * Checks if the world meets the requirements for a bad ending
	 *
	 * 
	 * @return boolean a boolean value indicating if the world has a bad ending or not
	 */
	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		if(this.checkAllHumans()) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Checks if all humans in the world is conscious
	 *
	 * 
	 * @return boolean value indicating if all humans in the world are consious
	 */
	public boolean checkAllHumans() {
		for(Human human : this.world.getAllHumans()) {
			if(human.isConscious() && !(human instanceof Player)) {
				return false;
			}
		}
		return true;
	}

}
