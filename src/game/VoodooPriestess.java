package game;

import java.util.Collections;
import java.util.Map.Entry;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A class which extends ZombieActor which implements method for Voodoo Priestess
 * 
 * @author Adrienne Rio Wongso Atmojo
 */
public abstract class VoodooPriestess extends ZombieActor {
	private int turns = 0;
	private int noOfSummonedZombies = 0;
	private Behaviour[] behaviours = {
			new ChantBehaviour(),
			new WanderBehaviour()
	};
	
	/**
	 * The constructor for VoodooPriestess
	 * 
	 * @param name the name of the actor
	 * @param displayChar the display character of the actor in the map
	 * @param hitPoints the health of the actor
	 * @param team the ZombieCapability of the actor
	 */
	public VoodooPriestess(String name, char displayChar, int hitPoints, ZombieCapability team) {
		super(name, displayChar, hitPoints, team);
	}

	/**
	 * Select and return an action to perform on the current turn. 
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		this.turns++;
		Action action = null;
		
		for(Behaviour behaviour : behaviours) {
			action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		
		return new DoNothingAction();
	}
	
	/**
	 * A getter method to get the number of summoned zombie this voodoo priestess has summoned
	 * 
	 * @return the number of summoned zombies
	 */
	public int getNoOfSummonedZombies() {
		return this.noOfSummonedZombies;
	}
	
	/**
	 * Add the number of summoned zombies by 5
	 * 
	 */
	public void addNoOfSummonedZombies() {
		this.noOfSummonedZombies += 5;
	}
	
	/**
	 * A getter method to get the number of turns the voodoo priestess has transpired
	 * 
	 * @return the number of turns
	 */
	public int getTurns() {
		return this.turns;
	};
	
	/**
	 * A setter method to get set the number of turns for voodoo priestess
	 * 
	 * @param newTurn the new number of turns for voodoo priestess
	 */
	public void setTurns(int newTurn) {
		this.turns = newTurn;
	}
	

}
