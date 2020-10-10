package game;

import edu.monash.fit2099.engine.Location;
/**
 * A class of sniper extending Gun class
 * @author Sean Hii Jun Wei
 *  */
public class Sniper extends Gun{
	/**
	 * damage of shooting the shotgun
	 *  */
	protected int shootDamage;
	/**
	 * age if sniper
	 *  */
	protected int age;
	
	/**
	 * Constructor
	 * */
	public Sniper() {
		// TODO Auto-generated constructor stub
		super("sniper",'R',30,"boom", 10, 0.75);	
		this.shootDamage= 45;
		this.age = 0;
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
	
	/**
	 * getter for age
	 * @return age*/
	public int getAge() {
		return this.age;
	}
	
	@Override
	public void tick (Location location) {
		super.tick(location);
		age++;
	}
}
