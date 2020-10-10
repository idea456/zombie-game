package game;
/**
 * A class of shotgun extending Gun class
 * @author Sean Hii Jun Wei
 *  */

public class Shotgun extends Gun{
	/**
	 * damage of shooting the shotgun
	 *  */
	protected int shootDamage;
	/**
	 * Constructor
	 * */
	public Shotgun() {
		super("shotgun",'S',30,"barreled", 12, 0.75);
		this.shootDamage = 40;
	}
	/**
	 * setter of shootDamage
	 * @param dmg damage of shooting the shotgun*/
	public void setShootDamage(int dmg) {
		this.shootDamage = dmg;
	}
	
	/**
	 * getter for shootDamage
	 * @return shootDamage*/
	public int getShootDamage() {
		return this.shootDamage;
	}
	
}
