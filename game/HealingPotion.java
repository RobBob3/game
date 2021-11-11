package game;

public class HealingPotion extends Potion {
    @Override
    public int cost() {
        return 10;
    }

    @Override
    public int drink(PlayerCharacter player) {
        player.setAmountOfHealingPotions(player.getAmountOfHealingPotions() - 1);
        return player.doMaxHeal();
    }
}
