package game;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A class of Gun extending WeaponItem class
 * @author Sean Hii Jun Wei
 *
 */
public class Gun extends WeaponItem{
	/**
	 * accuracy of the gun
	 */
	protected double accuracy;
	/**
	 * number of ammo in the gun
	 */
	protected int currentAmmo;
	
	/**
	 * Constructor
	 * @param name name of gun 
	 * @param displayChar display character of gun
	 * @param damage damage of barrel attack
	 * @param attackName name of attack
	 * @param capacity number of ammo in gun
	 * @param accuracy accuracy of the gun
	 */
	public Gun(String name, char displayChar, int damage, String attackName, int capacity, double accuracy) {
		// TODO Auto-generated constructor stub
		super(name,displayChar, damage, attackName);
		this.accuracy = accuracy;
		this.currentAmmo = capacity;
	}
	
	/**
	 * setter for accuracy
	 * @param acc accuracy of gun
	 */
	public void setAccuracy(int acc) {
		this.accuracy = acc;
	}
	
	/**
	 * getter for accuracy
	 * @return acc accuracy of gun
	 */
	public double getAccuracy() {
		return this.accuracy;
	}
	
	/**
	 * setter for current ammo
	 * @param number current ammo of gun
	 */
	public void setCurrentAmmo(int number) {
		this.currentAmmo = number;
	}
	
	/**
	 * getter for current ammo
	 * @return current ammo of gun
	 */
	public int getCurrentAmmo() {
		return this.currentAmmo;
	}
}
