package game;

import java.util.concurrent.ThreadLocalRandom;
public class StrengthPotion {
    public static int cost() {
        return 10;
    }
    public static int drink(PlayerCharacter player) {
        int strengthIncrease = ThreadLocalRandom.current().nextInt(1, 4);
        player.setStrength(player.getStrength() + strengthIncrease);
        return strengthIncrease;
    }
}
