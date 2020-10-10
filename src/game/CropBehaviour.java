package game;

import java.util.ArrayList;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * A class that generates a CropAction if Actor (Human, Player, Farmer) is standing on dirt and on or next to a crop. 
 * If the crop is ripe, generates HarvestAction.
 * If the crop is not ripe, generates FertilizeAction
 * If there is dirt, generates PlantAction based on a probability
 * @author Sean Hii Jun Wei
 *
 */
public class CropBehaviour implements Behaviour {
	/**
	 * random number as chance that the crop will be fertilized by farmer
	 */
	final double fertilizeChance = Math.random()*100;
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Human human = (Human) actor;
		Location actorLocation = map.locationOf(actor);											//the current location of the actor
		ArrayList <Location> adjacentLocations = human.searchAdjacentLocation(actorLocation,map);		//all adjacent locations of the actor
		
		//if the actor is a farmer, it can execute 3 actions (PlantAction, HarvestAction, FertilizeAction)
		if (actor instanceof Farmer) {
			
			//if there is crop on the location of actor, farmer can perform FertilizeAction with 70% chance if the age of crop is less than 20.
			if (actorLocation.getGround() instanceof Crop) {				
				Crop crop1 = (Crop)actorLocation.getGround();
				if(crop1.getAge()<20) {
					if (fertilizeChance >= 70) {
						return new FertilizeAction(crop1);
					}
				}
			}
			
			//plant crops if current location is dirt and harvest if current location contains ripe crop
			for (Location location : adjacentLocations) {					//loop through all the adjacent location of actor
				if (location.getGround() instanceof Dirt) {					//if there is dirt, farmer can perform PlantAction
					return new PlantAction(location);
				}
				
				else if (location.getGround() instanceof Crop) {			//if there is crop and the age of crop is more than 20, farmer can perform HarvestAction
					Crop crop = (Crop) location.getGround();				
					if (crop.getAge()>=20) {
						return new HarvestAction(actor,crop);
					}	
				}
			}
			
			
		}
		//if the actor is player, it can only execute HarvestAction on its own location when the crop is ripe (age>=20)
		else if (actor instanceof Player) {
			if (actorLocation.getGround() instanceof Crop) {
				Crop crop = (Crop) actorLocation.getGround();
				if(crop.getAge()>=20) {
					return new HarvestAction(actor,crop);
				}
			}
		}
		return null; 	
	}

	
	
}
