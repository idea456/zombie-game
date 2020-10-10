package game;


import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	private int inventorySpace;
	private int inventorySpaceLimit = 10;
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		
	}
	
	/**
	 * setter for inventory space
	 * @param space inventory space
	 */
	public void setInventorySpace(int space) {
		this.inventorySpace = space;
	}
	
	/**
	 * setter for inventory space
	 * @return inventory space
	 */
	public int getInventorySpace1() {
		return inventorySpace;
	}
	
	/**
	 * setter for inventory space limit
	 * @param limit inventory space limit
	 */
	public void setInventorySpaceLimit(int limit) {
		this.inventorySpaceLimit = limit;
	}
	
	/**
	 * getter for inventory space limit
	 * @return  inventory space limit
	 */
	public int getInventorySpaceLimit() {
		return inventorySpaceLimit;
	}
	
	/**
	 * check location for vehicles
	 * @param location current location
	 * @return true if there is a vehicle
	 */
	public boolean containsVehicle(Location location){
		for(Item item : location.getItems()) {
			if(item instanceof Vehicle) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		} else {
			Location location = map.locationOf(this);
			List<Item>groundItems = location.getItems();
			for (Item item: groundItems) {
				if(item instanceof Food) {
					actions.add(new EatAction(this,(Food)item));
				}
			}
			
			// if the player encounters a vehicle
			if(this.containsVehicle(location)) {
				actions.add(new DrivingAction(((NewMap)map).getWorld(), map));
			}
			
			//able to execute harvest action if the crop is ripe
			if(location.getGround() instanceof Crop) {
				Crop crop = (Crop)location.getGround();
				if (crop.getAge()>=20) {
					actions.add(new HarvestAction(this,location.getGround()));
				}
			}
			for(Item item: this.getInventory()) {
				if (item instanceof ZombieArm) {
					actions.add(new WeaponCraftingAction("Zombie Club", item));
				} 
				else if (item instanceof ZombieLeg) {
					actions.add(new WeaponCraftingAction("Zombie Mace", item));
				} 
				else if (item instanceof Food) {
					actions.add(new EatAction(this, (Food)item));
				}
				else if (item instanceof Gun) {
					Gun currentGun = (Gun) item;
					int gunAmmo = currentGun.getCurrentAmmo();
					if(gunAmmo != 0) {
						actions.add(new ShootingAction(currentGun));
					}
				}
			}
			
			// add the Quit Action
			actions.add(new QuitAction());
			return menu.showMenu(this, actions, display);
		}
		
	}
	
	/**
	 * 	check whether inventory is full
	 * @param spaceOccupiedByItems number of space occupied by items
	 * @return true if able to add items
	 */
	public boolean ableToAddItems (int spaceOccupiedByItems) {
		boolean output = false;
		int totalInventorySpace = inventorySpace + spaceOccupiedByItems;
		if (totalInventorySpace <= inventorySpaceLimit) {
			output = true;
		}
		
		return output;
	}
	

}
