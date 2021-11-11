package game;
public class Shop {
    public void goTo(PlayerCharacter player) {
        int userAnswer;
        System.out.println("Welcome to the shop.");
        PromptHelper.printDivider();
        PromptHelper.pause(1000);
        System.out.println("Today we have healing potions, strength potions, and invulnerability potions.");
        PromptHelper.printDivider();
        PromptHelper.pause(1000);
        do {
            System.out.println("Type \"0\" to not buy anything, Type \"1\" for healing potions, \"2\" for strength potions, and \"3\" for invulnerability potions.");
            userAnswer = PromptHelper.promptForChoice(4, "Which item would you like to buy?");
            if (userAnswer == 1) {
                if (player.getGold() >= new HealingPotion().cost()) {
                    System.out.println("You give the shopkeeper " + new HealingPotion().cost() + " gold.");
                    player.setGold(player.getGold() - new HealingPotion().cost());
                    player.setAmountOfHealingPotions(player.getAmountOfHealingPotions() + 1);
                }
                else {
                    System.out.println("You can't afford this!");
                }
            }
            else if (userAnswer == 2) {
                if (player.getGold() >= new StrengthPotion().cost()) {
                    System.out.println("You give the shopkeeper " + new StrengthPotion().cost() + " gold.");
                    player.setGold(player.getGold() - new StrengthPotion().cost());
                    System.out.println ("You gained " + new StrengthPotion().drink(player) + " strength!");
                }
                else {
                    System.out.println("You can't afford this!");
                }
            }
            if (userAnswer == 3) {
                if (player.getGold() >= new InvulnerabilityPotion().cost()) {
                    System.out.println("You give the shopkeeper " + new InvulnerabilityPotion().cost() + " gold.");
                    player.setGold(player.getGold() - new InvulnerabilityPotion().cost());
                    player.setAmountOfInvulnerabilityPotions(player.getAmountOfInvulnerabilityPotions() + 1);
                }
                else {
                    System.out.println("You can't afford this!");
                }
            }

        } while(userAnswer != 0);
    }
}
