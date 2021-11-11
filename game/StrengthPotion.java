package game;

import java.util.concurrent.ThreadLocalRandom;
public class StrengthPotion extends Potion {
    public int cost() {
        return 10;
    }
    public int drink(PlayerCharacter player) {
        int strengthIncrease = ThreadLocalRandom.current().nextInt(1, 4);
        player.setStrength(player.getStrength() + strengthIncrease);
        return strengthIncrease;
    }
}
