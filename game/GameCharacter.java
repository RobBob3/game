package game;

import java.util.concurrent.ThreadLocalRandom;
abstract class GameCharacter {
	protected static int MIN_STRENGTH_RANGE = 6;
	protected static int MAX_STRENGTH_RANGE = 10;
	protected static int MIN_HITPOINTS_RANGE = 10;
	protected static int MAX_HITPOINTS_RANGE = 15;
	protected static int MIN_ARMOR_RANGE = 3;
	protected static int MAX_ARMOR_RANGE = 5;

	protected int strength;
	protected int level;
	protected int hitpoints;
	protected int maxHitpoints;
	protected int armor;
	protected String name;
	boolean nameProperNoun = true;

	protected void generateInitialStats() {
		strength = ThreadLocalRandom.current().nextInt(MIN_STRENGTH_RANGE, MAX_STRENGTH_RANGE + 1); // Need to add 1 for chance to return MAX_STRENGTH_RANGE
		hitpoints = ThreadLocalRandom.current().nextInt(MIN_HITPOINTS_RANGE, MAX_HITPOINTS_RANGE + 1); // Need to add 1 for chance to return MAX_HITPOINTS_RANGE
		maxHitpoints = hitpoints;
		armor = ThreadLocalRandom.current().nextInt(MIN_ARMOR_RANGE, MAX_ARMOR_RANGE + 1); // Need to add 1 for chance to return MAX_ARMOR_RANGE
		// name = MONSTERS[ThreadLocalRandom.current().nextInt(0, 2 + 1)];
	}

	int getHurt(int damageTaken) {
		hitpoints = hitpoints - damageTaken;
		return hitpoints;
	}

	boolean alive() {
		if (hitpoints > 0) {
			return true;
		} else {
			return false;
		}
	}
	/* public String getName(boolean putThe) {
		if (nameProperNoun) {
			return name;
		} else {
			if (putThe) {
				return "The " + name;
			}
			else {
				return name;
			}
		}
	} */

	public abstract String getName(boolean putThe);

	public int doHeal(int amountHealed) {
		int preHealHitpoints = hitpoints;
		this.setHitpoints(this.getHitpoints() + amountHealed);
		if (hitpoints > maxHitpoints) {
			hitpoints = maxHitpoints;
		}
		amountHealed = hitpoints - preHealHitpoints;
		return amountHealed;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getHitpoints() {
		return hitpoints;
	}
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	public int getMaxHitpoints(){
		return maxHitpoints;
	}
	public void setMaxHitpoints(int maxHitpoints){
		this.maxHitpoints = maxHitpoints;
	}
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
}
