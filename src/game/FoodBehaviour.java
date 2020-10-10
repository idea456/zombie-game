package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * A class that generates food action if actor is on the ground that has food, or there is food in player's inventory. 
 * Implements Behaviour class
 * @author Sean Hii Jun Wei
 *
 */

public class FoodBehaviour implements Behaviour{
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		// if the actor is a human, if there is food on the ground, human will eat the food
		if(actor instanceof Human) {
			Location location = map.locationOf(actor);
			for (Item items:location.getItems()) {
				if (items instanceof Food) {
					return new EatAction(actor,(Food)items);
				}
			}
		}
		//if the actor is player, if there is food in player's inventory, player can play a round to eat the food
		else if (actor instanceof Player) {
			List<Item>inventory = actor.getInventory();
			for (Item items : inventory) {
				if (items instanceof Food) {
					return new EatAction(actor,(Food)items);
					
				}
			}
		}
		return null;
	}

}
