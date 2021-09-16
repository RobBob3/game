package game;

public class Shop {
    public void goToShop() {
        System.out.println("Welcome to the shop.");
        PromptHelper.printDivider();
        PromptHelper.pause(1000);
        System.out.println("Today we have healing potions, strength potions, and invulnerability potions.");
        System.out.println("Type \"0\" to not buy anything, Type \"1\" for healing potions, \"2\" for strength potions, and \"3\" for invulnerability potions.");
        int userAnswer = PromptHelper.promptForChoice(4, "Which item would you like to buy?");

    }
}
