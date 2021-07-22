package game;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyCharacter extends GameCharacter {
    public EnemyCharacter() {
        MIN_STRENGTH_RANGE = 6;
        MAX_STRENGTH_RANGE = 10;
        MIN_HITPOINTS_RANGE = 10;
        MAX_HITPOINTS_RANGE = 15;
        MIN_ARMOR_RANGE = 3;
        MAX_ARMOR_RANGE = 5;
        level = 1;
        generateStats();
        name = MONSTERS[ThreadLocalRandom.current().nextInt(0, 2 + 1)];
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
