package game;

import java.util.concurrent.ThreadLocalRandom;

public class PlayerCharacter extends GameCharacter {
    protected static int MIN_EXPERIENCE_ADDED_RANGE;
    protected static int MAX_EXPERIENCE_ADDED_RANGE;
    protected static int MAX_GOLD_ADDED_RANGE;
    protected static int MIN_GOLD_ADDED_RANGE;
    protected int amountOfHealingPotions = 3;
    protected int amountOfInvulnerabilityPotions = 0;
    protected int experience = 0;
    protected int gold = 10;
    public PlayerCharacter() {
        MIN_STRENGTH_RANGE = 8;
        MAX_STRENGTH_RANGE = 12;
        MIN_HITPOINTS_RANGE = 10;
        MAX_HITPOINTS_RANGE = 15;
        MIN_ARMOR_RANGE = 3;
        MAX_ARMOR_RANGE = 5;
        MIN_EXPERIENCE_ADDED_RANGE = 5;
        MAX_EXPERIENCE_ADDED_RANGE = 10;
        MIN_GOLD_ADDED_RANGE = 5;
        MAX_GOLD_ADDED_RANGE = 10;
        level = 1;
        turnsInvulnerable = 0;

        generateInitialStats();
    }
    public String getName(boolean putThe) {
        return name;
    }
    public int increaseExperience(int enemyLevel) {
        int experienceIncreased;
        int randomExperienceIncrease = ThreadLocalRandom.current().nextInt(MIN_EXPERIENCE_ADDED_RANGE, MAX_EXPERIENCE_ADDED_RANGE + 1); // Need to add 1 for chance to return MAX_EXPERIENCE_ADDED_RANGE
        experienceIncreased = randomExperienceIncrease * enemyLevel;
        setExperience(getExperience() + experienceIncreased);
        return experienceIncreased;
        // Xp = level * random # between 5 and 10
    }
    public int increaseGold(int enemyLevel) {
        int goldIncreased;
        int randomGoldIncrease = ThreadLocalRandom.current().nextInt(MIN_GOLD_ADDED_RANGE, MAX_GOLD_ADDED_RANGE + 1); // Need to add 1 for chance to return MAX_EXPERIENCE_ADDED_RANGE
        goldIncreased = randomGoldIncrease * enemyLevel;
        setGold(getGold() + goldIncreased);
        return goldIncreased;
        // Gold = level * random # between 5 and 10
    }
    public int gainLevelsIfCan() {
        int levelBeforeLevelUp = level;
        int requiredExperience = 15 * level;
        int levelAfterLevelUp = level;
        while (requiredExperience <= getExperience()) {
            setExperience(experience - requiredExperience);
            level++;
            this.setStrength(strength + level);
            this.setMaxHitpoints(maxHitpoints + level);
            this.setHitpoints(hitpoints + level);
            levelAfterLevelUp++;
        }
        return levelAfterLevelUp - levelBeforeLevelUp;
    }
    public int addInvulnerabilityTurns(int invulnerabilityTurnsAdded) {
        setTurnsInvulnerable(getTurnsInvulnerable() + invulnerabilityTurnsAdded);
        return turnsInvulnerable;
    }
    public boolean isInvulnerable() {
        return turnsInvulnerable > 0;
    }
    public void countDownInvulnerability() {
        if (isInvulnerable()) {
            setTurnsInvulnerable(getTurnsInvulnerable() - 1);
        }
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public int getAmountOfHealingPotions(){
        return amountOfHealingPotions;
    }
    public void setAmountOfHealingPotions(int amountOfHealingPotions) {
        this.amountOfHealingPotions = amountOfHealingPotions;
    }
        public int getAmountOfInvulnerabilityPotions() {
            return amountOfInvulnerabilityPotions;
        }
        public void setAmountOfInvulnerabilityPotions(int amountOfInvulnerabilityPotions) {
            this.amountOfInvulnerabilityPotions = amountOfInvulnerabilityPotions;
    }
    public int getTurnsInvulnerable() {
        return turnsInvulnerable;
    }
    public void setTurnsInvulnerable(int turnsInvulnerable) {
        this.turnsInvulnerable = turnsInvulnerable;
    }
}
