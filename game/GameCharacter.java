package game;

import java.util.concurrent.ThreadLocalRandom;
class GameCharacter {
	private static final int MIN_STRENGTH_RANGE = 6;
	private static final int MAX_STRENGTH_RANGE = 10;
	private static final int MIN_HITPOINTS_RANGE = 10;
	private static final int MAX_HITPOINTS_RANGE = 15;
	private static final int MIN_ARMOR_RANGE = 3;
	private static final int MAX_ARMOR_RANGE = 5;
	private static final String[] MONSTERS = {"Goblin", "Zombie", "Orc"};

	GameCharacter() {
		level = 1;
		strength = ThreadLocalRandom.current().nextInt(MIN_STRENGTH_RANGE, MAX_STRENGTH_RANGE + 1); // Need to add 1 for chance to return MAX_STRENGTH_RANGE
		hitpoints = ThreadLocalRandom.current().nextInt(MIN_HITPOINTS_RANGE, MAX_HITPOINTS_RANGE + 1); // Need to add 1 for chance to return MAX_HITPOINTS_RANGE
		maxHitpoints = hitpoints;
		armor = ThreadLocalRandom.current().nextInt(MIN_ARMOR_RANGE, MAX_ARMOR_RANGE + 1); // Need to add 1 for chance to return MAX_ARMOR_RANGE
		name = MONSTERS[ThreadLocalRandom.current().nextInt(0, 2 + 1)];
	}

	private int strength;
	private int level;
	private int hitpoints;
	private int maxHitpoints;
	private int armor;
	private String name;
	boolean nameProperNoun = true;
	
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
	public String getName(boolean putThe) {
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
	}
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
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
}
