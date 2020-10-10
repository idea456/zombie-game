package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * An action that allows an actor to craft weapons
 * 
 * @author Adrienne Rio Wongso Atmojo
 */
public class WeaponCraftingAction extends Action {
	private String weaponType;
	private Item item;
	
	/**
	 * The constructor of WeaponCraftingAction.
	 * 
	 * @param weaponType the type of weapon to be crafted
	 * @param item the item from which this weapon is crafted
	 */
	public WeaponCraftingAction(String weaponType, Item item) {
		this.weaponType = weaponType;
		this.item = item;
	}

	/**
	 * Allows the crafting weapon action to be executed.
	 * Weapon crafting is based on the weapon type.
	 * 
	 * @param actor the actor performing the action
	 * @param map the map in which the action is being performed
	 * @return the String of the action's description
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		if (this.weaponType == "Zombie Club") {
			// add the new weapon
			actor.addItemToInventory(new ZombieClub("Zombie Club", 'C', 50, "smashes"));
			actor.removeItemFromInventory(this.item);
		} else if (this.weaponType == "Zombie Mace") {
			actor.addItemToInventory(new ZombieMace("Zombie Mace", 'M', 50, "pounds"));
			actor.removeItemFromInventory(this.item);
		}
		return menuDescription(actor);
	}

	
	/**
	 * A method that returns the description for the menu.
	 * Based on what type the weapon is, the appropriate weapon name that will be crafted will be returned.
	 * 
	 * @param actor the actor performing the action
	 */
	@Override
	public String menuDescription(Actor actor) {
		if (this.weaponType == "Zombie Club") 
			return actor.toString() + " crafts Zombie Club";
		else {
			return actor.toString() + " crafts Zombie Mace";
		}
		
	}
	
	
}
