package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
/**
 * A special class that generates harvest actions
 * @author User
 *
 */
public class HarvestAction extends Action{
	/**
	 * name of actor
	 */
	private String name;
	
	/**
	 * Constructor
	 * @param actor actor that performs the action
	 * @param ground the ground that action is performed on
	 */
	public HarvestAction(Actor actor, Ground ground) {
		this.name = actor.toString();
	}
	
	/**
	 * setter for the name of actor
	 * @param name name of actor
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getter for the name of actor
	 * @return name of actor
	 */
	public String getName () {
		return this.name;
	}
	
	/**
	 * Perform the harvest action. If the actor is farmer, he will harvest food and food will drop on the ground. If the actor is player, if the player's inventory is full,
	 * food will drop on the ground. If the player's inventory is not full, it will be placed in it.
	 * @param actor Actor performing the action
	 * @param map Map that is played on
	 * @return Describes what happened after the action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String output = "";
		Food food = new Food();
		
		//check if the actor is player, it will harvest the crop as food. Food will be placed in player's inventory if its not full, else food will drop on ground
		if (actor instanceof Player) {
			if (((Player)actor).ableToAddItems(food.getSpaceOccupied())) {
				actor.addItemToInventory(food);
				Location location = map.locationOf(actor);
				location.setGround(new Dirt());
			}
			else {
				Location location = map.locationOf(actor);
				location.addItem(food);
				location.setGround(new Dirt());
				output = "Inventory full. Crop dropped on the ground";
			}
		}
		//if the actor is farmer, crop will be harvested as food and drop on ground
		else if (actor instanceof Farmer) {
			Location location = map.locationOf(actor);
			location.addItem(food);
			location.setGround(new Dirt());
		}
		return menuDescription(actor)+output;
	}
	
	/**
	 * A method that concatenate the string which describes what happened when action is executed
	 * @param actor actor performing the action
	 * @return return the string of description
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor+" harvest crop.";
	}

}
