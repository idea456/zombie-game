package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;


/**
 * Special Action for quitting game
 * 
 * @author Adrienne Rio Wongso Atmojo
 */
public class QuitAction extends Action {
	
	/**
	 * The execute method excutes the quit action
	 * 
	 * @return A string description of the action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actor);
		((NewMap)map).getWorld().setEndGame(true);
		return "Quitting game...";
	}

	
	/**
	 * Shows the description of the action to the menu
	 * 
	 * @return A string description of the action for the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Quit Game";
	}
	
	/**
	 * Shows the hotkey of the action for the menu
	 * 
	 * @return A string hotkey for the action on the menu
	 */
	@Override
	public String hotkey() {
		return "1";
	}
	

}
