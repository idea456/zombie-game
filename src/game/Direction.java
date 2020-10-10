package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * A class of direction
 * */
public class Direction {
	/**
	 *current location of actor */
	protected Location currentLocation;
	
	/**
	 * a list of location of available locations*/
	protected ArrayList<Location> coords = new ArrayList <Location>();
	
	/**
	 * String of shortform if intercardinal directions*/
	String[] intercardinalName = {"NE","SE","SW","NW",};
	/**
	 * String of shortform if cardinal directions*/

	String[] cardinalName = {"N","S","E","W"};
	protected ArrayList<Location> locations;
	
	/**
	 * current playing map*/
	protected GameMap maps;
	
	/**
	 * Constructor
	 * @param map the map on the play
	 * @param actor current actor*/
	public Direction(GameMap map, Actor actor) {
		// TODO Auto-generated constructor stub
		 this.maps = map;
		 this.currentLocation = map.locationOf(actor);
	}
	
	/**
	 *a method that gets the locations that is in range of that direction based on intercardinal or cardinal direction
	 *@param name the direction
	 *@return a list of locations
	 **/

	public ArrayList<Location> getDirection(String name) {
		String check = checkList(name);
		
		if (check == "I") {
			intercardinalDirection(this.maps, this.currentLocation, name);
		}
		else if (check == "C") {
			cardinalDirection(this.maps, this.currentLocation, name);
		}
		return this.locations;
	}
	
	/**
	 *a method that gets the locations that is in range of that direction based on intercardinal direction
	 *@param map map of playing
	 *@param location player's location
	 *@param string the direction name
	 **/
	public void intercardinalDirection (GameMap map, Location location, String string) {
		int initX = location.x();
		int initY = location.y();
		ArrayList<Location> new1 = new ArrayList <Location>();
		if (string == "NE") {
			for (int i = 0;i<=3;i++) {
				for (int j = -3; j<=0; j++) {
					Location newLocation = new Location (map,initX+i,initY+j);
					new1.add(newLocation);
				}
			}
			this.locations = new1;
		}
		
		else if (string == "SE") {
			for (int i = 0;i<=3;i++) {
				for (int j = 0; j<=3; j++) {
					Location newLocation = new Location (map,initX+i,initY+j);
					new1.add(newLocation);
				}
			}
			this.locations = new1;
		}
			
		else if (string == "SW") {
			for (int i = -3;i<=0;i++) {
				for (int j = 0; j<=3; j++) {
					Location newLocation = new Location (map,initX+i,initY+j);
					new1.add(newLocation);
				}
			}		
			this.locations = new1;
		}
			
		else if (string == "NW") {
			for (int i = -3;i<=0;i++) {
				for (int j = -3; j<=0; j++) {
					Location newLocation = new Location (map,initX+i,initY+j);
					new1.add(newLocation);
				}
			}
			this.locations = new1;
		}	
	}
		
	/**
	 *a method that gets the locations that is in range of that direction based on cardinal direction
	 *@param map map of playing
	 *@param location player's location
	 *@param string the direction name
	 **/
	public void cardinalDirection(GameMap map, Location location, String string) {
		int initX = location.x();
		int initY = location.y();
		ArrayList<Location> new1 = new ArrayList <Location>();
		if (string == "N") {
			for (int i = -3;i<=3;i++) {
				for (int j = -3; j<=0; j++) {
					Location newLocation = new Location (map,initX+i,initY+j);
					if(availablePt(location, newLocation,string)|newLocation.x()==location.x()) {
						new1.add(newLocation);
					}
				}
			}
			this.locations = new1;
		}
		else if (string == "S") {
			for (int i = -3;i<=3;i++) {
				for (int j = 0; j<=3; j++) {
					Location newLocation = new Location (map,initX+i,initY+j);
					if(availablePt(location, newLocation,string)|newLocation.x()==location.x()) {
						new1.add(newLocation);
					}
				}
			}
			this.locations = new1;
		}
		else if (string == "E") {
			for (int i = 0;i<=3;i++) {
				for (int j = -3; j<=3; j++) {
					Location newLocation = new Location (map,initX+i,initY+j);
					if(availablePt(location, newLocation,string)) {
						new1.add(newLocation);
					}
					
				}
			}
			this.locations = new1;
		}
		else if (string == "W") {
			for (int i = -3;i<=0;i++) {
				for (int j = -3; j<=3; j++) {
					Location newLocation = new Location (map,initX+i,initY+j);
					if(availablePt(location, newLocation,string)) {
						new1.add(newLocation);
					}
				}
			}
			this.locations = new1;
		}
	}
	
	/**
	 * a method that checks which finding location method to implement
	 * @param string type of direction
	 * @return the type of direction
	 */
	public String checkList(String string) {
		String output = "";
		for(String check : intercardinalName) {
			if (check == string) {
				output = "I";
			}
		}
		for(String check : cardinalName) {
			if (check == string) {
				output = "C";
			}
		}
		return new String (output);
	}
	
	/**
	 * A method that checks the locations that are in range of the direction
	 * @param currentLocation actor's location
	 * @param targetLocation location that is being checked
	 * @param name the name of direction
	 * @return boolean value whether or not the location is available
	 */
	public boolean availablePt(Location currentLocation, Location targetLocation, String name) {
		double currentX=currentLocation.x();
		double currentY=currentLocation.y();
		double targetX =targetLocation.x() ;
		double targetY=targetLocation.y();
		
		double top = targetX - currentX;
		double bottom = targetY-currentY;
		if (name == "N"|name =="S") {
			if (top!=0) {
				double res = bottom/top;
				res = Math.abs(res);
				if (res>=1) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else if(name == "E"|name =="W"){
			if (top!=0) {
				double res = bottom/top;
				res = Math.abs(res);
				if (res<=1) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	

}
