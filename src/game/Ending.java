package game;

import edu.monash.fit2099.engine.Display;

/**
 * Base class for Endings. 
 * Ending represents the different endings the game can have
 * For example, good ending and bad ending
 * 
 * @author Adrienne Rio Wongso Atmojo
 */
public abstract class Ending {
	protected NewWorld world;
	
	/**
	 * Constructor for Ending
	 *
	 * @param world the current world to monitor the ending
	 */
	public Ending(NewWorld world) {
		this.world = world;
	}
	
	/**
	 * Constructor for defensive copying
	 *
	 * @param copyEnding the Ending to copy
	 */
	public Ending(Ending copyEnding) {
		this(copyEnding.world);
	}
	
	
	/**
	 * Checks if the current world meets the conditions for the ending
	 *
	 *
	 * @return a boolean value indicating if the ending is eligible or not
	 */
	public abstract boolean check();
	
	
	/**
	 * Performs the ending on the world by setting the world's endGame condition to true
	 * Override this method to add more functionality
	 *
	 */
	public void run() {
		this.world.setEndGame(true);
	}

}
