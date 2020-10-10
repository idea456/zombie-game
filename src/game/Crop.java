package game;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents Crop. Crop can be planted and fertilized by Farmer, and harvested by Player, Human or Farmer when it is ripe.
 * @author Sean Hii Jun Wei
 *
 */
public class Crop extends Ground{
	/**
	 * age is the represented as number of turns, which will be used to determined when it will be ripen
	 */
	private int age;

	/**
	 * Constructor.
	 * Does not receive any parameter, but initializes age as 0.
	 */
	public Crop() {
		// TODO Auto-generated constructor stub
		super('*');
		this.age = 0;
	}
	
	/**
	 * setter for age of crop
	 * @param newAge an integer to be set as age
	 */
	public void setAge(int newAge) {
		this.age = newAge;
	}
	
	/**
	 * getter for age of crop
	 * @return age of crop
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Inform an Item on the ground of the passage of time.This method is called once per turn, if the item rests upon the ground. 
	 * When the crop is ripe (set when age is 20), it will change its display character to /
	 * @param location location of crop
	 */
	public void tick (Location location) {
		super.tick(location);
		age++;
		if (age >= 20) {
			displayChar = '/';
		}
	}
	
	
	
	
}
