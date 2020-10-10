package game;

import java.util.HashMap;
import java.util.Map.Entry;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Allows only a VoodooPriestess to chant for spawning zombies
 * 
 * @author Adrienne Rio Wongso Atmojo
 *
 */
public class ChantBehaviour implements Behaviour {
	private int noOfMMZombies = 0;

	/**
	 * Returns a ChantAction for the voodoo priestess 
	 * If chanting is not possible, returns null.
	 * 
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no ChantAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		if(actor instanceof VoodooPriestess) {
			VoodooPriestess voodooPriestess = (VoodooPriestess) actor;
			// get the number of turns that have transpired for that voodoo priestess
			if(voodooPriestess.getTurns() % 10 == 0) {
				this.noOfMMZombies = this.noOfMMZombies + 5;
				return new ChantAction(this.noOfMMZombies - 5);
			}
		}
		
		return null;
	}

}
