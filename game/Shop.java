package game;

public class Shop {
    public void goToShop(PlayerCharacter playerCharacter) {
        System.out.println("Welcome to the shop.");
        PromptHelper.printDivider();
        PromptHelper.pause(1000);
        System.out.println("Today we have healing potions, strength potions, and invulnerability potions.");
        System.out.println("Type \"0\" to not buy anything, Type \"1\" for healing potions," +
                " \"2\" for strength potions, and \"3\" for invulnerability potions.");
        int userAnswer = PromptHelper.promptForChoice(4,
                "Which item would you like to buy?");
        switch (userAnswer) {
            case 0:
                System.out.println("Come back soon!");
                break;
            case 1:
                if (HealingPotion.cost() <= playerCharacter.gold) {
                    playerCharacter.setAmountOfHealingPotions(playerCharacter.getAmountOfHealingPotions() + 1);
                    System.out.println("Come back soon!");
                } else System.out.println("You don't have enough money to buy this.");
                break;
            case 2:
                if (StrengthPotion.cost() <= playerCharacter.gold) {
                    playerCharacter.setAmountOfStrengthPotions(playerCharacter.getAmountOfStrengthPotions() + 1);
                }
                else System.out.println("You don't have enough money to buy this.");
            case 3:
        }
    }
}
