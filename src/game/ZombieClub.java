package game;

import edu.monash.fit2099.engine.WeaponItem;
/**
 * A weapon formed from a zombie arm.
 * 
 * @author Adrienne Rio Wongso Atmojo
 *
 */
public class ZombieClub extends WeaponItem {

	/**
	 * The constructor for ZombieClub
	 * 
	 * @param name the name of the weapon
	 * @param displayChar the character that will be displayed on the map
	 * @param damage the amount of damage this weapon will deal
	 * @param verb the verb when performing an attack with this weapon
	 *
	 */
	public ZombieClub(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}

}
