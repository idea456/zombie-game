package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates an SpeakAction for an Actor
 * for Zombies with the probability of 10% for generating a SpeakAction
 * 
 * @author Adrienne Rio Wongso Atmojo
 *
 */
public class SpeakBehaviour implements Behaviour {
	double chance = Math.random() * 100;
	
	/**
	 * Returns a SpeakAction that allows Actors to say something in the display.
	 * 
	 * @param actor the actor enacting the action
	 * @param map the GameMap in which the action is taking place
	 */
	public Action getAction(Actor actor, GameMap map) {
		if (actor instanceof Zombie) {
			Zombie zombieActor = (Zombie) actor;
			if (zombieActor.getNoOfLegs() == 1) {
				if (zombieActor.getTurns() > 2) {
					// reset the number of turns
					zombieActor.setTurns(0);
				}
			}
		}
		if(this.chance < 10) {
			// generate a new chance
			this.chance = Math.random() * 100;
			return new SpeakAction(actor, "Braiiins!");
		} else {
			// generate a new chance
			this.chance = Math.random() * 100;
			return null;
		}
			
	}

}
