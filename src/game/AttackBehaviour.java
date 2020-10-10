package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;

/**
 * A class that generates an AttackAction if the current Actor is standing
 * next to an Actor that they can attack.
 * 
 * @author ram
 *
 */
public class AttackBehaviour implements Behaviour {
	private ZombieCapability attackableTeam;
	
	/**
	 * Constructor.
	 * 
	 * Sets the team (i.e. ZombieCapability) that the owner of this
	 * Behaviour is allowed to attack.
	 * 
	 * @param attackableTeam Team descriptor for ZombieActors that can be attacked
	 */
	public AttackBehaviour(ZombieCapability attackableTeam) {
		this.attackableTeam = attackableTeam;
	}

	/**
	 * Returns an AttackAction that attacks an adjacent attackable Actor.
	 * 
	 * Actors are attackable if their ZombieCapability matches the 
	 * "undeadness status" set 
	 * 
	 * If the actor is a Zombie and the zombie has no arms then it cannot attack and definitely drops any weapon it is holding.
	 * Else if it has only 1 arm then it has a 50% probability of dropping any weapon its holding when attacking a target.
	 * 
	 * @param actor the actor enacting the action
	 * @param map the map in which the actor is performing the action
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there an attackable Actor next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(attackableTeam)) {
				if (actor instanceof Zombie) {
					Zombie zombieActor = (Zombie) actor;
					if (zombieActor.getNoOfLegs() == 1) {
						if (zombieActor.getTurns() == 2) {
							// reset the number of turns
							zombieActor.setTurns(0);
						}
					}
					// if the zombie has no arms then it cannot do anything
					// and  definitely drops any weapon it was holding.
					if (zombieActor.getNoOfArms() == 0) {
						if (!(zombieActor.getWeapon() instanceof IntrinsicWeapon)) {
							System.out.println(zombieActor.toString() + " drops " + zombieActor.getWeapon().toString() + ".");
							map.locationOf(zombieActor).addItem((Item) zombieActor.getWeapon());
							zombieActor.removeItemFromInventory((Item) zombieActor.getWeapon());
						}
						return null;
					} 
					else {
						// If a Zombie loses one arm, it has a 50% chance of dropping any weapon it is holding.
						if (zombieActor.getNoOfArms() == 1) {
							double weaponDropChance = Math.random() * 100;
							if (weaponDropChance < 50 && !(zombieActor.getWeapon() instanceof IntrinsicWeapon)) {
								System.out.println(zombieActor.toString() + " drops " + zombieActor.getWeapon().toString() + ".");
								map.locationOf(zombieActor).addItem((Item) zombieActor.getWeapon());
								zombieActor.removeItemFromInventory((Item) zombieActor.getWeapon());
							} 
						}	
					
					}
				}

				return new AttackAction(e.getDestination().getActor());
			}
		}
		return null;
	}

}
