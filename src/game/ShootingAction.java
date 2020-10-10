package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
/**
 * A class representing the shooting action
 * @author Sean Hii Jun Wei
 * */
public class ShootingAction extends Action{
	/**
	 * gun of actor
	 */
	protected Gun gun;	
	/**
	 * target of sniper attack
	 */
	protected Actor sniperTarget;
	/**
	 *  round of aiming
	 */
	protected int sniperRounds;
	/**
	 * current round of aiming
	 */
	protected int sniperAge;
	
	
	/**
	 * Constructor
	 * @param newGun gun of actor 
	 */
	public ShootingAction(Gun newGun) {
		// TODO Auto-generated constructor stub
		this.gun = newGun;
		this.sniperRounds = 100;
		this.sniperAge = 0;
	}
	
	/**
	 * setter for sniper rounds
	 * 
	 * @param rounds the number of rounds to set
	 */
	public void setSniperRounds(int rounds) {
		this.sniperRounds = rounds;
	}
	
	/**
	 * getter for sniper rounds
	 * @return number of sniper rounds
	 */
	public int getSniperRounds() {
		return this.sniperRounds;
	}
	
	/**
	 * setter for sniper age
	 * @param age age of sniper
	 */
	public void setSniperAge(int age) {
		this.sniperAge = age;
	}
	
	/**
	 * getter for sniper age
	 * @return age of sniper 
	 */
	public int getSniperAge() {
		return this.sniperAge;
	}
	
	/**
	 * Perform the shooting action. If the gun is a shotgun, player will choose a direction to shoot. The actors that is in the shooting range of the 
	 * shotgun have a chance to get hit and deal certain amount of damage. If the gun is a sniper, player will choose number of rounds to aim. The larger 
	 * the number of rounds of aiming, the higher the accuracy and the damage dealt to the actor that is chosen by player
	 * @param actor Actor performing the action
	 * @param map Map that is played on
	 * @return Describes what happened after the action
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		String output = "";
		boolean hurt = false;
		String inputDirection = showShotgunSubMenu();
		Direction direction = new Direction(map,actor);
		ArrayList<Location> directions = direction.getDirection(inputDirection);
		reloadGun(actor,gun);
		if (gun instanceof Shotgun) {
			Shotgun shotgun = (Shotgun)gun;
			double hittingChance = Math.random();
			int damage = shotgun.getShootDamage();
			double accuracy = shotgun.getAccuracy();
			for(Location location:directions) {
				if(location.containsAnActor() && !(location.getActor() instanceof Player)) {
					if(hittingChance<=accuracy) {
						Actor target = location.getActor();
						target.hurt(damage);
						output+= System.lineSeparator()+"Player hit "+target+" with a shotgun for "+ damage+" damage.";
						output+=checkSurvivors(target,map);
						hurt = true;
					}
				}
			}
			
		}
		
		else if (gun instanceof Sniper) {
			hurt = true;
			Sniper sniper = (Sniper) gun;
			double hittingChance = Math.random();
			int baseDamage = sniper.getShootDamage();
			double accuracy = sniper.getAccuracy();
			Actor target = pickTarget(actor,sniper,map);				
			
			if (this.sniperRounds>=3) {
				System.out.println("Spend round aiming?");
				this.sniperRounds = spendRoundAiming();
			}
		

			if (this.sniperAge == this.sniperRounds) {
				if (this.sniperRounds == 0) {
					if(hittingChance <= accuracy) {
						target.hurt(baseDamage);
						output+= System.lineSeparator()+"Player hit "+target.toString()+" with a sniper for "+ baseDamage+" damage.";
						output+=checkSurvivors(target,map);
					}
					else {
						output+="Player shot but missed!";
					}
				}
				else if (this.sniperAge == 1){
					accuracy = 0.95;
					int newDamage = baseDamage*2;
					if(hittingChance <= accuracy) {
						target.hurt(newDamage);
						output+= System.lineSeparator()+"Player hit "+target.toString()+" with a sniper for "+ newDamage+" damage.";
						output+=checkSurvivors(target,map);
						this.sniperAge=0;
					}
					else {
						output+="Player shot but missed!";
					}
				}
				else if(this.sniperAge == 2) {
					accuracy = 0.95;
					int newDamage1 = 1000;
					if(hittingChance <= accuracy) {
						target.hurt(newDamage1);
						output+= System.lineSeparator()+"Player hit "+target.toString()+" with a sniper with instakill.";
						output+=checkSurvivors(target,map);
						this.sniperAge=0;
					}
					else {
						output+="Player shot but missed!";
					}
				}
				this.sniperRounds = 100;
				
			}
			else {
				this.sniperAge++;
			}
		}
		if (hurt == false) {
			output ="Player shot towards direction of "+inputDirection+" with a"+gun.toString()+". No one is harmed.";
		}
		else {
			gun.setCurrentAmmo(gun.getCurrentAmmo()-1);
		}
		return new String (output);
	}
	
	/**
	 * A method that concatenate the string which describes what happened when action is executed
	 * @param actor actor performing the action
	 * @return return the string of description
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Player fires the "+ gun;
	}
	
	/**
	 * A method that shows the direction for the shotgun to shoot at
	 * @return A list of locations which is in the shotgun shooting range
	 *  */
	public String showShotgunSubMenu(){
		int answer = 0;
		if (gun instanceof Shotgun) {
			String[] directions = {"N","NE","E","SE","S","SW","W","NW"};
			int[] console = {8,9,6,3,2,1,4,7};
			for (int i = 0; i < directions.length;i++) {
				System.out.println(console[i]+": Player shoots towards "+directions[i]);
			}
			InputStreamReader isr = new InputStreamReader (System.in );
	        BufferedReader stdin = new BufferedReader( isr );
			try {
				String check = stdin.readLine();
				answer = Integer.valueOf(check);
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < console.length;i++) {
				if(answer == console[i]) {
					return new String (directions[i]);
				}
			}
		}			
		return null;
	}
	
	/**
	 * A method that shows the list of target actors for the player to shoot at
	 * @param newGun the sniper used by the player
	 * @param actor the player
	 * @param map the playing map
	 * @return The target for player to snipe
	 *  */
	public Actor showSniperSubMenu(Gun newGun, Actor actor, GameMap map) {
		Gun gun = newGun;
		ArrayList<Actor> targetList = new ArrayList<Actor>();
		int count = 0;
		int answer = 0;
		for (int i : map.getXRange()) {
			for(int j : map.getYRange()) {
				Location location = new Location(map,i,j);
				if (map.isAnActorAt(location)) {
					Actor targetActor = map.getActorAt(location);
					if (targetActor != actor) {
						targetList.add(targetActor);
					}
				}	
			}
		}
		
		for (Actor target : targetList) {
			System.out.println(count+1+": "+ target);
			count++;
		}
		InputStreamReader isr = new InputStreamReader (System.in );
        BufferedReader stdin = new BufferedReader( isr );
        try {
        	String check = stdin.readLine();
			answer = Integer.valueOf(check);
	    }catch (IOException e) {
	    	e.printStackTrace();
		}
        
		return targetList.get(answer-1);
	}
	
	/**
	 * Check if the actor is alive after being shot and carry out necessary actions if the actor is killed
	 * @param target Target that is shot
	 * @param map The map that is played on
	 * @return A dtring of description of the result of this method*/
	public String checkSurvivors(Actor target, GameMap map) {
		String output ="";
		if (!target.isConscious()) {
			if(target instanceof Zombie) {
				Item corpse = new PortableItem("dead " + target, '%');
				map.locationOf(target).addItem(corpse);	
			}
			else{
				if(target instanceof VoodooPriestess && map instanceof NewMap) {
					NewMap newMap = (NewMap)map;
					newMap.getWorld().setMMDead(true);
				}
				String corpseName = "Zombie "+target.toString();
				Corpse corpse = new Corpse(corpseName);
				map.locationOf(target).addItem(corpse);
			}
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			output += System.lineSeparator() + target + " is killed.";
		}
		return new String (output);
	}
	
	/**
	 * A method that handles the menu of number of rounds of aiming by player
	 * @return The number of rounds chosen by player*/
	public int spendRoundAiming() {
		int rounds = 0;
		System.out.println("No aim: 75% chance to hit, standard damage");
		System.out.println("One round aiming: 90% chance to hit, double damage");
		System.out.println("Two rounds aiming: 100% chance to hit, instakill");
			
			
		InputStreamReader isr = new InputStreamReader (System.in );
        BufferedReader stdin = new BufferedReader( isr );
        try {
        	String check = stdin.readLine();
			rounds = Integer.valueOf(check);
	    }catch (IOException e) {
	    	e.printStackTrace();
		}
        
		return rounds;
	}
	/**a method that check the number of rounds of sniper with input roudns of aiming
	 * @param current current age of sniper
	 * @param input the number of rounds chosen by player
	 * @return a boolean value indicating if the 2 variables have the same values of not*/
	public boolean checkRounds(int current, int input) {
		boolean go = false;
		if (input == current) {
			go = true;
		}
		return go;
	}
	
	/**
	 * A method for players to pick the target from a list
	 * @param actor player of the game
	 * @param sniper sniper used by player
	 * @param map the map that the player is playing on
	 * @return the target of player*/
	public Actor pickTarget(Actor actor, Sniper sniper, GameMap map) {
		System.out.println("Who is your target?");
		Actor target = showSniperSubMenu(sniper,actor,map);
		return target;
	}
	
	/**
	 * a method that reloads the gun
	 * @param actor player
	 * @param gun gun used by player*/
	 public void reloadGun(Actor actor, Gun gun) {
		 for(Item item: actor.getInventory()) {
				if (item instanceof ShotgunAmmunition) {
					if(item instanceof Shotgun) {
						ShotgunAmmunition ammo = (ShotgunAmmunition) item;
						Shotgun shotgun = (Shotgun) gun;
						shotgun.setCurrentAmmo(shotgun.getCurrentAmmo()+ammo.getRound());
					}
				}
				else if (item instanceof SniperAmmunition) {
					if(item instanceof Sniper) {
						SniperAmmunition ammo = (SniperAmmunition) item;
						Sniper sniper = (Sniper) gun;
						sniper.setCurrentAmmo(sniper.getCurrentAmmo()+ammo.getRound());
					}
				}
			}
	 }
	
}
