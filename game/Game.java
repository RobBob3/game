package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Game {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	GameCharacter player = new game.GameCharacter();
	public static void main(String[] args) throws InterruptedException, IOException {
		boolean wantsToPlay = true;
		Game game = new Game();
		do {
			game.runGame();
			if (PromptHelper.askYesOrNo("Do you want to play again? Y/N")) {
				wantsToPlay = true;
			}
			else {
				wantsToPlay = false;
			}
		}
		while (wantsToPlay);
	}

	void printAttributes (game.GameCharacter gameCharacter) {
		PromptHelper.printDivider();
		System.out.println("Strength = " + gameCharacter.getStrength());
		System.out.println("HitPoints = " + gameCharacter.getHitpoints());
		System.out.println("Armor = " + gameCharacter.getArmor());
		System.out.println("Level = " + gameCharacter.getLevel());
		System.out.println("Name = " + gameCharacter.getName(false));
		System.out.println("Alive = " + gameCharacter.alive());
		System.out.println("Experience = " + gameCharacter.getExperience());
	}

	void runGame() throws IOException {
		int enemiesDefeated = 0;
		PromptHelper.printDivider();
		System.out.println("What is your name?");
		player.setName(reader.readLine());
		do {
			GameCharacter enemy = doBeforeFightStuff();
			if (player.alive()) {
				enemiesDefeated++;
				int addedExperience = player.increaseExperience(enemy.getLevel());

				PromptHelper.printDivider();
				System.out.println("You won the battle");
				PromptHelper.printDivider();
				System.out.println("You gained " + addedExperience + " experience!");
				PromptHelper.printDivider();
				System.out.println("You were healed for " + player.doHeal(5) + " hitpoints.");
				PromptHelper.printDivider();
			} else {
				System.out.println(enemy.getName(true) + " won the battle!");
				PromptHelper.printDivider();
				System.out.println("You died to the " + enemy.getName(false) + "!");
				PromptHelper.printDivider();
				System.out.println("You defeated " + enemiesDefeated + " enemies this game.");
			}
		}
		while (player.alive());
	}
	private GameCharacter doBeforeFightStuff() throws IOException {
		player.nameProperNoun = true;
		game.GameCharacter enemy = new game.GameCharacter();
		enemy.nameProperNoun = false;
		PromptHelper.printDivider();
		System.out.println("These are your attributes");
		printAttributes(player);
		PromptHelper.printDivider();
		PromptHelper.promptForEnter();
		PromptHelper.printDivider();
		System.out.println("These are " + enemy.getName(true) + "'s attributes");
		printAttributes(enemy);
		PromptHelper.printDivider();
		PromptHelper.promptForEnter();
		return enemy;
	}
	private void doFight(GameCharacter enemy) {
		game.CombatRunner fighting = new game.CombatRunner(player, enemy);
		fighting.fightLoop();
		PromptHelper.printDivider();
		System.out.println("After the fight, your attributes are:");
		printAttributes(player);
		PromptHelper.printDivider();
		PromptHelper.promptForEnter();
		PromptHelper.printDivider();
		System.out.println("After the fight, " + enemy.getName(true) + "'s attributes are:");
		printAttributes(enemy);
	}
}
