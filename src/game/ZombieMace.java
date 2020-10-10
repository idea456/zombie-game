package game;

import edu.monash.fit2099.engine.WeaponItem;
/**
 * A weapon formed from a zombie leg
 * 
 * @author Adrienne Rio Wongso Atmojo
 *
 */
public class ZombieMace extends WeaponItem {

	/**
	 * The constructor for ZombieMace
	 * 
	 * @param name the name of the weapon
	 * @param displayChar the character that will be displayed on the map
	 * @param damage the amount of damage this weapon will deal
	 * @param verb the verb when performing an attack with this weapon
	 *
	 */
	public ZombieMace(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}

}
