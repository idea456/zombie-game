package game;
/**
 * A class that represents food which extends PortableItem class.
 * @author Sean Hii Jun Wei
 *
 */
public class Food extends PortableItem{
	/**
	 * name of the food
	 */
	private String name;
	
	/**
	 * display character of food
	 */
	private char displayChar;
	
	/**
	 * space that food will occupy in player's inventory
	 */
	private int spaceOccupied;
	
	/**
	 * Constructor. 
	 * Set the space food will occupy in inventory to 2
	 */
	public Food() {
		// TODO Auto-generated constructor stub
		super("Food",'R');
		this.spaceOccupied = 2;
	}
	
	/**
	 * setter for the name of food
	 * @param name name of food
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * getter for the name of food
	 * @return name of food
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * setter for the display character of food
	 * @param displayChar display character of food
	 */
	public void setDisplayChar (char displayChar) {
		this.displayChar = displayChar;
	}
	
	/**
	 * getter for the display character of food
	 * @return display character of food
	 */
	public char getDisplayChar() {
		return displayChar;
	}
	
	/**
	 * setter for the space that food will occupy
	 * @param spaceOccupied space that food will occupy
	 */
	public void setSpaceOccupied (int spaceOccupied) {
		this.spaceOccupied = spaceOccupied;
	}
	
	/**
	 * getter for space that food will occupy
	 * @return space that food will occupy
	 */
	public int getSpaceOccupied() {
		return spaceOccupied;
	}

}
