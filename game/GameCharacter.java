package game;

import java.util.concurrent.ThreadLocalRandom;
class GameCharacter {
	private static final int MIN_STRENGTH = 6;
	private static final int MAX_STRENGTH = 10;
	private static final int MIN_HITPOINTS = 10;
	private static final int MAX_HITPOINTS = 15;
	private static final int MIN_ARMOR = 3;
	private static final int MAX_ARMOR = 5;
	private static final String[] MONSTERS = {"Goblin", "Zombie", "Orc"};

	GameCharacter() {
		level = 1;
		strength = ThreadLocalRandom.current().nextInt(MIN_STRENGTH, MAX_STRENGTH + 1); // Need to add 1 for chance to return MAX_STRENGTH
		hitpoints = ThreadLocalRandom.current().nextInt(MIN_HITPOINTS, MAX_HITPOINTS + 1); // Need to add 1 for chance to return MAX_HITPOINTS
		armor = ThreadLocalRandom.current().nextInt(MIN_ARMOR, MAX_ARMOR + 1); // Need to add 1 for chance to return MAX_ARMOR
		name = MONSTERS[ThreadLocalRandom.current().nextInt(0, 2 + 1)];
	}

	private int strength;
	private int level;
	private int hitpoints;
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
		this.setHitpoints(this.getHitpoints() + amountHealed);
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
