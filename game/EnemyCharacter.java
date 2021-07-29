package game;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyCharacter extends GameCharacter {
    public EnemyCharacter(int enemyLevel) {
        MIN_STRENGTH_RANGE = 6;
        MAX_STRENGTH_RANGE = 10;
        MIN_HITPOINTS_RANGE = 10;
        MAX_HITPOINTS_RANGE = 15;
        MIN_ARMOR_RANGE = 3;
        MAX_ARMOR_RANGE = 5;
        level = 0;
        generateInitialStats();
        name = MONSTERS[ThreadLocalRandom.current().nextInt(0, 2 + 1)];
        while (enemyLevel > 0) {
            enemyLevel--;
            level++;
            strength = strength + level;
            hitpoints = hitpoints + level;
        }
    }
    private static final String[] MONSTERS = {"Goblin", "Zombie", "Orc"};
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
}
