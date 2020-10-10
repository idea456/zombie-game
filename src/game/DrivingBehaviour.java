package game;


import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class DrivingBehaviour implements Behaviour{

	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (actor instanceof Player) {
			Location location = map.locationOf(actor);	
			List<Item>items = location.getItems();
			for (Item item : items) {
				if(item instanceof Vehicle) {
					return new DrivingAction(((NewMap)map).getWorld(), map);
				}
			}
		}
		
		
		return null;
		
	}

}
