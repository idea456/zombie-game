package game;
/**
 * A class of Ammunition extending portable item*/
public class Ammunition extends PortableItem{
	/**
	 * number of rounds of one box of ammo*/
	protected int rounds;
	/**
	 * Constructor
	 * @param name type of ammo
	 * @param displayChar the displaying character for ammo
	 * @param rounds number number of rounds of one box of ammo
	 * */
	public Ammunition(String name, char displayChar, int rounds) {
		// TODO Auto-generated constructor stub
		super(name,displayChar);
		this.rounds = rounds;
	}
	
	/**
	 * setter for number of rounds
	 * @param newRounds number of rounds*/
	public void setRounds(int newRounds) {
		this.rounds = newRounds;
	}
	
	/**
	 * getter for number of rounds
	 * @return newRounds number of rounds*/
	public int getRound() {
		return this.rounds;
	}

}
