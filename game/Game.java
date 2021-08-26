package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Game {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	PlayerCharacter player;
	int enemyLevel;
	int enemiesDefeated;
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

	void printAttributes (GameCharacter gameCharacter) {
		PromptHelper.printDivider();
		System.out.println("Strength = " + gameCharacter.getStrength());
		System.out.println("HitPoints = " + gameCharacter.getHitpoints() + "/" + gameCharacter.getMaxHitpoints());
		System.out.println("Armor = " + gameCharacter.getArmor());
		System.out.println("Level = " + gameCharacter.getLevel());
		System.out.println("Name = " + gameCharacter.getName(false));
		System.out.println("Alive = " + gameCharacter.alive());
		if (gameCharacter instanceof PlayerCharacter) {
			PlayerCharacter player = (PlayerCharacter) gameCharacter;
			System.out.println("Healing potions = " + player.getAmountOfHealingPotions());
			System.out.println("Experience = " + player.getExperience());
			System.out.println("Gold = " + player.getGold());
		}
	}

	void runGame() throws IOException {
		player = new PlayerCharacter();
		enemiesDefeated = 0;
		enemyLevel = 0; // enemyLevel set to 0 because it is required for increasing enemy levels in doBeforeFightStuff
		PromptHelper.printDivider();
		System.out.println("What is your name?");
		player.setName(reader.readLine());
		do {
			EnemyCharacter enemy = doBeforeFightStuff();
			doFight(enemy);
			if (player.alive()) {
				enemiesDefeated++;
				int addedExperience = player.increaseExperience(enemy.getLevel());
				int levelsGained = player.gainLevelsIfCan();
				int addedGold = player.increaseGold(enemy.getLevel());
				PromptHelper.printDivider();
				System.out.println("You won the battle");
				PromptHelper.printDivider();
				System.out.println("You gained " + addedExperience + " experience!");
				PromptHelper.printDivider();
				System.out.println("You gained " + addedGold + " gold!");
				PromptHelper.printDivider();
				if (levelsGained == 1) {
					System.out.println("You gained 1 level.");
				}
				else System.out.println("You gained " + levelsGained + " levels.");
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
	private EnemyCharacter doBeforeFightStuff() throws IOException {
		if (enemiesDefeated % 3 == 0) {
			enemyLevel++;
		}
		player.nameProperNoun = true;
		EnemyCharacter enemy = new EnemyCharacter(enemyLevel);
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
	private void doFight(EnemyCharacter enemy) {
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
