package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * A special class that generates the action to fertilize
 * @author Sean Hii Jun Wei
 *
 */
public class FertilizeAction extends Action{
	/**
	 * Crop that will be fertilized
	 */
	private Crop crop;
	
	/**
	 * Constructor
	 * @param crop crop that will be fertilized
	 */
	public FertilizeAction(Crop crop) {
		this.crop = crop;
	}
	
	/**
	 * setter for Crop
	 * @param crop crop to be fertilized
	 */
	public void setCrop (Crop crop) {
		this.crop = crop;
	}
	
	/**
	 * getter for crop
	 * @return crop to be fertilized
	 */
	public Crop getCrop() {
		return this.crop;
	}
	/**
	 * Perform the fertilize action. Increase the age of crop by 10
	 * @param actor Actor performing the action
	 * @param map Map that is played on
	 * @return Describes what happened after the action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		crop.setAge(crop.getAge()+10);		
		return menuDescription(actor);
	}

	/**
	 * A method that concatenate the string which describes what happened when action is executed
	 * @param actor actor performing the action
	 * @return return the string of description
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor+" fertilized crop";
	}
}
