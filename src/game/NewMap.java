package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

/**
 * A subclass that extends GameMap with additional functionalities
 * such as spawning Mambo Marie
 */
public class NewMap extends GameMap {
	private MamboMarie mamboMarie = new MamboMarie("Mambo Marie", 'V', 100, ZombieCapability.UNDEAD);
	private double chanceOfAppearing = Math.random() * 100;
	private Display display = new Display();
	private int turns = 0;
	private NewWorld world;
	private String name;
	private int[][] edges = {{0,0}, {0, this.getYRange().max()}, {this.getXRange().max(), 0},{this.getXRange().max(), this.getYRange().max()}};
	

	/**
	 * The constructor of NewMap
	 * 
	 * @param groundFactory Factory to create Ground objects
	 * @param lines List of Strings representing rows of the map
	 * @param world the world the map is in
	 * @param name the name of the map
	 */
	public NewMap(GroundFactory groundFactory, List<String> lines, NewWorld world, String name) {
		super(groundFactory, lines);
		this.world = world;
		this.name = name;
		// check if the name given to the map is unique
		for(GameMap oldMap : this.world.getGameMaps()) {
			if(((NewMap)oldMap).getName() == this.name) {
				throw new IllegalArgumentException("Invalid map name! Map name must be unique among all added maps!");
			}
		}
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * A getter method to get the name of the map
	 * 
	 * @return A string containing the name of map
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * A getter method to get the number of turns that transpired in this map
	 * 
	 * @return A integer containing the number of turns in the map
	 */
	public int getTurns() {
		return this.turns;
	}
	
	/**
	 * A getter method to get World the map is currently in
	 * 
	 * @return A copy of the NewWorld object of the world the map is currently in
	 */
	public NewWorld getWorld() {
		return this.world;
	}
	
	
	/**
	 * This method spawns Mambo Marie if the conditions are met in the map
	 * 
	 */
	public void spawnMamboMarie() {
		if(this.mamboMarie.isConscious()) {
			if(this.world.containsMamboMarie()) {
				this.turns++;
				this.chanceOfAppearing = Math.random() * 100;
				//  If she is not killed, she will vanish after 30 turns
				if(mamboMarie.getTurns() > 30) {
					this.removeActor(mamboMarie);
					mamboMarie.setTurns(0);
					this.world.setMamboMarieExists(false);
					display.println("Mambo Marie has vanished!");
					this.turns = 0;
					this.chanceOfAppearing = Math.random() * 100;
				}
			} else {
				this.chanceOfAppearing = Math.random() * 100;
				// If she is not currently on the map, she has a 5% chance per turn of appearing
				if(this.chanceOfAppearing <= 5) {
					int x;
					int y;
					for(int[] edge : edges) {
						x = edge[0];
						y = edge[1];
						if(this.at(x,y).getGround().canActorEnter(mamboMarie) 
								&& !this.isAnActorAt(new Location(this, x, y))) {
							this.at(x, y).addActor(mamboMarie);
							break;
						}
					}
					this.world.setMamboMarieExists(true);
					display.println("Mambo Marie has appeared at " + this.name + "!");
					this.turns++;
				} 
				
			}
		}
	}
	
	/**
	 * An overridden method that is called every turn with additional functionality
	 * Attempts to add Mambo Marie into the map if conditions are met
	 */
	@Override
	public void tick() {	
		// Tick over all the items in inventories.
		for (Actor actor : actorLocations) {
			if (this.contains(actor)) {
				for (Item item : new ArrayList<Item>(actor.getInventory())) { // Copy the list in case the item wants to leave
						item.tick(actorLocations.locationOf(actor), actor);
				}
			}
		}

		for (int y : heights) {
			for (int x : widths) {
				this.at(x, y).tick();
			}
		}
		
		// spawn Mambo Marie if conditions are met
		this.spawnMamboMarie();
	}
	
	
	/**
	 * An overridden method to add a new Actor to the map
	 * It also adds the new actor to the total actors in the world
	 */
	@Override
	public void addActor(Actor actor, Location location) {
		Objects.requireNonNull(actor);
		actorLocations.add(actor, location);
		
		if(actor instanceof Zombie) {
			this.world.addTotalZombies((Zombie) actor);
		} else if(actor instanceof Human) {
			this.world.addTotalHumans((Human) actor);
		}
		
	}
	
	
	
	
}
