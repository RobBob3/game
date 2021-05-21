package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class Game {
	public static void main(String[] args) throws InterruptedException, IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
        GameCharacter player = new GameCharacter();
        player.nameProperNoun = true;
		GameCharacter enemy = new GameCharacter();
		enemy.nameProperNoun = false;
		System.out.println("What is your name?");
        // player.name = reader.readLine();
		player.setName(reader.readLine());
		System.out.println("These are your attributes");
		printAttributes(player);
		System.out.println("Press enter to continue.");
		reader.readLine();
		System.out.println("These are " + enemy.getName(true) + "'s attributes");
		printAttributes(enemy);
		System.out.println("Press enter to continue.");
		reader.readLine();
		CombatRunner fighting = new CombatRunner(player, enemy);
		fighting.fightLoop();
		System.out.println("After the fight, your attributes are:");
		printAttributes(player);
		System.out.println("Press enter to continue.");
		reader.readLine();
		System.out.println("After the fight, " + enemy.getName(true) + "'s attributes are:");
		printAttributes(enemy);
		if (player.alive()) {
			System.out.println("You won the battle");
		}
		else {
			System.out.println(enemy.getName(true) + " won the battle");
		}
	}
	static void printAttributes (GameCharacter gameCharacter) {
        System.out.println("Strength = " + gameCharacter.getStrength());
        System.out.println("HitPoints = " + gameCharacter.getHitpoints());
		System.out.println("Armor = " + gameCharacter.getArmor());
        System.out.println("Level = " + gameCharacter.getLevel());
        System.out.println("Name = " + gameCharacter.getName(false));
        System.out.println("Alive = " + gameCharacter.alive());
	}
}
