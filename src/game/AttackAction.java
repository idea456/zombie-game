package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		// if the Player is attacking a Zombie
		if (target instanceof Zombie) {
			double knockOffChance = Math.random() * 100;
			Zombie zombieActor = (Zombie) target;
			//Any attack on a Zombie that causes damage has a chance to knock at least one of its limbs off
			if (knockOffChance < 25) {
				// choice variable to determine either an arm or a leg will knock off
				double choice = Math.random() * 3;
				if (Math.abs(choice) <= 1) {
					if (!(zombieActor.getNoOfArms() <= 0)) {
						// if the zombie has no arms then it cannot attack
						zombieActor.setNoOfArms(zombieActor.getNoOfArms() <= 0 ? zombieActor.getNoOfArms() : zombieActor.getNoOfArms() - 1);
						Location location = zombieActor.dropZombieLimbs(map.locationOf(actor), map);
						// add to adjacent location if there is a possible adjacent location
						if (location != null) {
							map.at(location.x(), location.y()).addItem(new ZombieArm("Zombie Arm", 'A'));
							result += System.lineSeparator() + zombieActor.toString() + " loses an arm.";
						}
					}
				} 
				else {
					if (!(zombieActor.getNoOfLegs() <= 0)) {
						zombieActor.setNoOfLegs(zombieActor.getNoOfLegs() <= 0 ? zombieActor.getNoOfLegs() : zombieActor.getNoOfLegs() - 1);
						Location location = zombieActor.dropZombieLimbs(map.locationOf(actor), map);
						// add to adjacent location if there is a possible adjacent location
						if (location != null) {
							map.at(location.x(), location.y()).addItem(new ZombieLeg("Zombie Leg", 'L'));
							result += System.lineSeparator() + zombieActor.toString() + " loses a leg.";
						}
					}
					
				}
				
			}
		}
		// If a Zombie loses one arm, its probability of punching (rather than biting) is halved
		else if (target instanceof Player && actor instanceof Zombie) {
			if (((Zombie) actor).getNoOfArms() == 1) {
				double attackChance = Math.random() * 100;
				if(!(attackChance < 50)) {
					return actor + " misses " + target + ".";
				} 
			}
		}
		target.hurt(damage);
		if (!target.isConscious()) {
			// this should be a new Corpse()
			if(target instanceof Zombie) {
				Item corpse = new PortableItem("dead " + target, '%');
				map.locationOf(target).addItem(corpse);	
			}
			else{
				if(target instanceof VoodooPriestess) {
					((NewMap)map).getWorld().setMMDead(true);
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
			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
