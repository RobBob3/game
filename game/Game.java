package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Game {
	public static void main(String[] args) throws InterruptedException, IOException {
		boolean wantsToPlay = true;
		do {
			runGame();
			if (PromptHelper.askYesOrNo("Do you want to play again? Y/N")) {
				wantsToPlay = true;
			}
			else {
				wantsToPlay = false;
			}
		}
		while (wantsToPlay);
	}

	static void printAttributes (game.GameCharacter gameCharacter) {
		System.out.println("Strength = " + gameCharacter.getStrength());
		System.out.println("HitPoints = " + gameCharacter.getHitpoints());
		System.out.println("Armor = " + gameCharacter.getArmor());
		System.out.println("Level = " + gameCharacter.getLevel());
		System.out.println("Name = " + gameCharacter.getName(false));
		System.out.println("Alive = " + gameCharacter.alive());
	}

	static void runGame() throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		game.GameCharacter player = new game.GameCharacter();
		player.nameProperNoun = true;
		game.GameCharacter enemy = new game.GameCharacter();
		enemy.nameProperNoun = false;
		System.out.println("What is your name?");
		player.setName(reader.readLine());
		System.out.println("These are your attributes");
		printAttributes(player);
		System.out.println("Press enter to continue.");
		reader.readLine();
		System.out.println("These are " + enemy.getName(true) + "'s attributes");
		printAttributes(enemy);
		System.out.println("Press enter to continue.");
		reader.readLine();
		game.CombatRunner fighting = new game.CombatRunner(player, enemy);
		fighting.fightLoop();
		System.out.println("After the fight, your attributes are:");
		printAttributes(player);
		System.out.println("Press enter to continue.");
		reader.readLine();
		System.out.println("After the fight, " + enemy.getName(true) + "'s attributes are:");
		printAttributes(enemy);
		if (player.alive()) {
			System.out.println("You won the battle");
		} else {
			System.out.println(enemy.getName(true) + " won the battle");
		}
	}
}
