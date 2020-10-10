package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * Special class that generates action to eat food. Humans who perform will gain 20 hit points until it reach max capacity of hit points
 * @author Sean Hii Jun Wei
 *
 */
public class EatAction extends Action{
	@SuppressWarnings("unused")
	/**
	 * actor that is performing this action
	 */
	private Actor actor;
	/**
	 * food that actor is performing EatAction on 
	 */
	private Food food;
	/**
	 * name of actor
	 */
	private String name;
	
	/**
	 * Constructor
	 * @param actor actor that performs this action
	 * @param food food that this action performs on
	 */
	public EatAction(Actor actor, Food food) {
		// TODO Auto-generated constructor stub
		this.actor = actor;
		this.name = actor.toString();
		this.food = food;
	}
	
	/**
	 * Perform the eat action. Heals the actor with 20 hit points until maximum capacity. It will remove food from 
	 * author's inventory after eaten
	 * @param actor Actor performing the action
	 * @param map Map that is played on
	 * @return Describes what happened after the action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		int healingPointsPerEat = 20;
		actor.heal(healingPointsPerEat);		//heals the actor by 20 hit points
		if(actor instanceof Player) {
			actor.removeItemFromInventory(this.food);		//removes food from actor's inventory
		}
		return this.name+" ate food and restored 20 hitpoints.";
	}
	/**
	 * A method that concatenate the string which describes what happened when action is executed
	 * @param actor actor performing the action
	 * @return return the string of description
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		if (actor instanceof Player) {
			return this.name + " eats food.";
		}
		return null;
	}

}
