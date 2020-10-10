package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Allows an Actor to wander around at random.
 * 
 * @author ram
 *
 */
public class WanderBehaviour implements Behaviour {
	
	private Random random = new Random();


	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * If the actor is a Zombie, if the Zombie has no legs then it cannot move and therefore cannot wander.
	 * If the Zombie has only 1 leg, then it can only wander every second turn.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }
		// If it loses both legs, it cannot move at all, although it can still bite and punch
		if (actor instanceof Zombie) {
			Zombie zombieActor = (Zombie) actor;
			if (zombieActor.getNoOfLegs() == 0) {
				return null;
			// If it loses one leg, its movement speed is halved
			} else if (zombieActor.getNoOfLegs() == 1) {
				if (zombieActor.getTurns() == 2) {
					// reset the number of turns
					zombieActor.setTurns(0);
					if (!actions.isEmpty()) {
						return actions.get(random.nextInt(actions.size()));
					} else {
						return null;
					}
					
				} else {
					return null;
				}
			} else {
				if (!actions.isEmpty()) {
					return actions.get(random.nextInt(actions.size()));
				} else {
					return null;
				}
			}
			
		}
		else if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}

}
