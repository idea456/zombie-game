package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * A special class that generates the action to plant
 * @author Sean Hii Jun Wei
 *
 */
public class PlantAction extends Action{
	/**
	 * location of the actor/dirt
	 */
	private Location location;
	
	/**
	 * Constructor
	 * @param location location of actor
	 */
	public PlantAction(Location location) {
		// TODO Auto-generated constructor stub
		this.location = location;
	}
	
	/**
	 * setter for location to perform this action
	 * @param location location to perform this action
	 */
	public void setLocation (Location location) {
		this.location = location;
	}
	
	/**
	 * getter for location to perform this action
	 * @return location to perform this action
	 */
	public Location getLocation () {
		return this.location;
	}
	
	/**
	 * Perform the plant action. There is a 33% to plant a crop
	 * @param actor Actor performing the action
	 * @param map Map that is played on
	 * @return Describes what happened after the action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		double chance = Math.random()*100;
		
		if (chance <= 33) {
			Crop crop = new Crop();
			map.at(location.x(),location.y()).setGround(crop);
		}	
		
		return menuDescription(actor);
	}

	/**
	 * A method that concatenate the string which describes what happened when action is executed
	 * @param actor actor performing the action
	 * @return return the string of description
	 */
	@Override
	public String menuDescription(Actor actor) {
		// only performed by Farmers, so no need menuDesc
		return actor+" planted crops.";
	}

}
