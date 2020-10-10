package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * An Action that allows an actor to speak 
 */
public class SpeakAction extends Action{
	private String name;
	private String message;
	
	/**
	 * The constructor of SpeakAction
	 * 
	 * @param actor the actor enacting the speak action
	 * @param message the message to be spoken
	 */
	public SpeakAction(Actor actor, String message) {
		this.name = actor.toString();
		this.message = message;
	}

	/**
	 * Allows the Actor to speak the message
	 * Overrides Action.execute()
	 * 
	 * @param actor the actor enacting the speak action
	 * @param map the map in which the action is performed
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return this.name + " says : " + this.message;
	}

	/**
	 * Returns a description of the speak action into the menu
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Zombie says : Braiiins"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return this.name + " says : " + this.message;
	}
	

}
