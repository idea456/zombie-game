package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

/**
 * A subclass that extends from World 
 * that further implements additional features and attributes to the world
 * such as spawning Mambo Marie
 * 
 * @author Adrienne Rio Wongso Atmojo
 */
public class NewWorld extends World {
	private NewMap currentMap;
	private boolean mamboMarieExists;
	private boolean endGame = false;
	private boolean mamboMarieDead = false;
	
	private Ending goodEnding = new GoodEnding(this);
	private Ending badEnding = new BadEnding(this);
	
	private ArrayList<Zombie> allZombies = new ArrayList<Zombie>();
	private ArrayList<Human> allHumans = new ArrayList<Human>();

	/**
	 * Constructor for NewWorld
	 * 
	 * @param display the display for the world
	 */
	public NewWorld(Display display) {
		// TODO Auto-generated constructor stub
		super(display);
	}
	
	/**
	 * A getter method to get all maps in the world
	 * 
	 * @return A shallow copy of gameMaps attribute
	 */
	public ArrayList<GameMap> getGameMaps(){
		return new ArrayList<GameMap>(this.gameMaps);
	}
	
	/**
	 * A setter method for Mambo Marie to exist in the world
	 * 
	 * @param exists the boolean value to set for Mambo Marie to exist in the world
	 * 
	 */
	public void setMamboMarieExists(boolean exists) {
		this.mamboMarieExists = exists;
	}
	
	/**
	 * A setter method to set condition for endGame
	 * 
	 * @param newEndGame the new newGame condition to be set
	 */
	public void setEndGame(boolean newEndGame) {
		this.endGame = newEndGame;
	}

	
	
	/**
	 * A getter method to get the current map the player is in
	 * 
	 * @return NewMap the current map the player is in
	 */
	public NewMap getCurrentMap() {
		return (NewMap)actorLocations.locationOf(player).map();
	}
	
	/**
	 * Check if Mambo Marie exists in the World
	 * 
	 * @return A string description of the action for the menu
	 */
	public boolean containsMamboMarie() {
		return this.mamboMarieExists;
	}
	
	/**
	 * A getter method to get an ArrayList of all zombies in the World
	 * 
	 * @return an ArrayList of all zombies in the World
	 */
	public ArrayList<Zombie> getAllZombies() {
		return new ArrayList<Zombie>(allZombies);
	}
	
	/**
	 * A getter method to get an ArrayList of all humans in the World
	 * 
	 * @return an ArrayList of all humans in the World
	 */
	public ArrayList<Human> getAllHumans() {
		return new ArrayList<Human>(allHumans);
	}
	
	/**
	 * This method adds a new zombie to the total zombies in the World
	 * 
	 * @param zombie the new zombie to the total zombies in the World
	 */
	public void addTotalZombies(Zombie zombie) {
		allZombies.add(zombie);
	}
	
	
	/**
	 * This method adds a new human to the total humans in the World
	 * 
	 * @param human the new human to the total humans in the World
	 */
	public void addTotalHumans(Human human) {
		allHumans.add(human);
	}
	
	
	/**
	 * This method checks if Mambo Marie is dead in the World
	 * 
	 * @return a boolean value indicating if Mambo Marie is dead or not
	 */
	public boolean isMMDead() {
		return this.mamboMarieDead;
	}
	
	
	/**
	 * This method sets the condition for Mambo Marie to be dead
	 * 
	 * @param mamboMarieDead the new boolean value for Mambo Marie condition to be dead
	 */
	public void setMMDead(boolean mamboMarieDead) {
		this.mamboMarieDead = mamboMarieDead;
	}
	
	
	/**
	 * The overridden method of run() in World
	 * with additional functionalities such as
	 * to check for different endings
	 * 
	 */
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();
		

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()) {
			if (this.goodEnding.check()) {
				this.goodEnding.run();
				display.println("All zombies and Mambo Marie has been eliminated! You win!");
				continue;
			} else if(this.badEnding.check()) {
				this.badEnding.run();
				display.println("All humans are killed! You lose!");
				continue;
			}
			
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}

		}
		if(!actorLocations.contains(player) && !this.endGame) {
			display.println(endGameMessage());
		} else {
			display.println("Exited game.");
		}
	
	}
	
	/**
	 * This method checks if the game is still running
	 * It checks if the player is still in the world and the endGame condition
	 * 
	 * @return a boolean value to indicate if the game iss till running or not
	 */
	@Override
	protected boolean stillRunning() {
		return actorLocations.contains(player) && !this.endGame;
	}
	
}
