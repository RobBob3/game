package game;

import java.util.concurrent.ThreadLocalRandom;

public class PlayerCharacter extends GameCharacter {
    protected static int MIN_EXPERIENCE_ADDED_RANGE = 5;
    protected static int MAX_EXPERIENCE_ADDED_RANGE = 10;
    protected static int amountOfHealingPotions = 3;
    protected int experience = 0;
    public PlayerCharacter() {
        MIN_STRENGTH_RANGE = 8;
        MAX_STRENGTH_RANGE = 12;
        MIN_HITPOINTS_RANGE = 10;
        MAX_HITPOINTS_RANGE = 15;
        MIN_ARMOR_RANGE = 3;
        MAX_ARMOR_RANGE = 5;
        MIN_EXPERIENCE_ADDED_RANGE = 5;
        MAX_EXPERIENCE_ADDED_RANGE = 10;
        level = 1;

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
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public int getAmountOfHealingPotions(){
        return amountOfHealingPotions;
    }
    public void setAmountOfHealingPotions(int amountOfHealingPotions) {
        this.amountOfHealingPotions = amountOfHealingPotions;
    }
}
