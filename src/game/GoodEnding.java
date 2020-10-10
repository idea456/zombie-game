package game;

import java.util.ArrayList;
import java.util.Iterator;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class for good endings
 *
 * @author Adrienne Rio Wongso Atmojo
 */
public class GoodEnding extends Ending {
	
	/**
	 * Constructor for good ending
	 *
	 * @param world the world to check for good endings
	 */
	public GoodEnding(NewWorld world) {
		super(world);
	}

	/**
	 * Checks if the world meets the requirements for a good ending
	 *
	 * 
	 * @return boolean a boolean value indicating if the world has a good ending or not
	 */
	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		if(this.checkAllZombies() && this.world.isMMDead()) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Checks if all zombies in the world is conscious
	 *
	 * 
	 * @return boolean value indicating if all zombies in the world are conscious
	 */
	public boolean checkAllZombies() {
		for(Zombie zombie : this.world.getAllZombies()) {
			if(zombie.isConscious()) {
				return false;
			}
		}
		return true;
	}


}
