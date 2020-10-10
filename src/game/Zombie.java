package game;


import java.util.*;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	private Behaviour[] behaviours = {
			new SpeakBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour(),
	};
	private int noOfArms = 2;
	private int noOfLegs = 2;
	private int turns = 0;
	
	/**
	 * The constructor of the Zombie class
	 *
	 * @param name the name of the Zombie
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}
	
	/**
	 * A getter class to get the number of arms for a Zombie
	 * 
	 * @return the number of arms currently the Zombie has
	 */
	public int getNoOfArms() {
		return this.noOfArms;
	}
	
	/**
	 * A getter class to get the number of legs for a Zombie
	 * 
	 * @return the number of legs currently the Zombie has
	 */
	public int getNoOfLegs() {
		return this.noOfLegs;
	}
	
	/**
	 * A setter class to set the number of arms for a Zombie
	 * 
	 * @param newArms the number of new arms the Zombie will have
	 */
	public void setNoOfArms(int newArms) {
		this.noOfArms = newArms;
	}
	
	/**
	 * A setter class to set the number of legs for a Zombie
	 * 
	 * @param newLegs the number of new legs the Zombie will have
	 */
	public void setNoOfLegs(int newLegs) {
		this.noOfLegs = newLegs;
	}
	
	/**
	 * A getter class to get the number of turns the Zombie currently has if it has one leg
	 * 
	 * @return the number of turns the Zombie currently has
	 */
	public int getTurns() {
		return this.turns;
	}
	
	/**
	 * A setter class to set the number of turns for a Zombie 
	 * 
	 * @param newTurn the new number of turns the Zombie will have
	 */
	public void setTurns(int newTurn) {
		this.turns = newTurn;
	}
		

	/**
	 * A method that creates or returns an IntrinsicWeapon
	 * This method overrides the Actor.getIntrinsicWeapon()
	 * 
	 * @return a new IntrinsicWeapon for the Zombie such as punching or biting
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		if((Math.random() * 100) < 50) {
			// Zombies should be able to bite. Give the Zombie a bite attack as well, with a 50% probability of using this instead of their normal attack.
			// A successful bite attack restores 5 health points to the Zombie
			this.heal(5);
			return new IntrinsicWeapon(20, "bites");
		}
		return new IntrinsicWeapon(10, "punches");
	}
	
	

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// increment the number of turns if the zombie has only one leg
		if(this.noOfLegs == 1) {
			this.turns++;
		}
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			// if action returns null means that the action cannot be performed
			if (action != null) {
				// If there is a weapon at the Zombie’s location when its turn starts, the Zombie should pick it up
				List<Item> items = map.locationOf(this).getItems();
				for (Item item : items) {
					if (item instanceof WeaponItem) {
						// zombie cannot pick up anything since it has no arms
						if (this.getNoOfArms() == 0) {
							break;
						} 
						else {
							// zombie has a probability of picking up a weapon of 50% if it only has 1 arm
							if (this.getNoOfArms() == 1) {
								double pickUpChance = Math.random() * 100;
								if (pickUpChance < 50) {
									display.println(this.name + " picks up a " + item.toString());
									this.addItemToInventory(item);
									// remove the weapon since it is already in the Zombie's inventory
									map.locationOf(this).removeItem(item);
									break;
								} else {
									break;
								}
							} else {
								// zombie has 2 arms and can pick up any weapon
								display.println(this.name + " picks up a " + item.toString());
								this.addItemToInventory(item);
								// remove the weapon since it is already in the Zombie's inventory
								map.locationOf(this).removeItem(item);
								break;
							}
							
						}
					}
				}
				return action;
			}
		}
		return new DoNothingAction();	
	}
	
	/**
	 * This method returns a possible Location where a ZombieArm or ZombieLeg can be dropped adjacently
	 * 
	 * @param actorLocation the current location of the Zombie in the map
	 * @param map the map in which the Zombie is currently in
	 * @return location that drops the zombie limbs
	 */
	public Location dropZombieLimbs(Location actorLocation, GameMap map) {
		int x = actorLocation.x();
		int y = actorLocation.y();
		// naively iterate through every possible directions
		for(int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if(x+i >= 0 && x+i <= map.getXRange().max() && y+j >= 0 && y+j <= map.getXRange().max()) 
				// if there is no item or tree or obstacle at that particular adjacent Location
					if (map.at(x+i, y+j).getItems().size() == 0 && map.at(x+i, y+j).getDisplayChar() != '+' && map.at(x+i, y+j).getDisplayChar() != '#') {
						return new Location(map, x+i, y+j);
				}
			}
		}
		return null;
	}
}
