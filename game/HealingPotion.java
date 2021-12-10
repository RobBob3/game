package game;

public class HealingPotion {
    public static int cost() {
        return 10;
    }

    public static int drink(PlayerCharacter player) {
        player.setAmountOfHealingPotions(player.getAmountOfHealingPotions() - 1);
        return player.doMaxHeal();
    }
}
