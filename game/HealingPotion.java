package game;

public class HealingPotion extends Potion {
    public static int cost() {
        return 10;
    }

    @Override
    public int drink(PlayerCharacter player) {
        return player.doMaxHeal();
    }
}
