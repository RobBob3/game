package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;
class CombatRunner {
    GameCharacter playerChar;
    GameCharacter enemyChar;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    CombatRunner(GameCharacter playerChar, GameCharacter enemyChar) {
        this.playerChar = playerChar;
        this.enemyChar = enemyChar;
    }
    int attack (GameCharacter attacker, GameCharacter defender) {
        int atkDieRoll = ThreadLocalRandom.current().nextInt(1,6 + 1);
        int attackerDamage = atkDieRoll + attacker.getStrength() - defender.getArmor();
        if (attackerDamage < 0 ) {
            attackerDamage = 0;
        }
        defender.getHurt(attackerDamage);
        return attackerDamage;
    }
    void doRound() {
        int attackerDamage;
        String yesOrNo = "Y";
        System.out.println("Do you want to heal? Y/N");
        try {
            yesOrNo = reader.readLine();
            if (yesOrNo.equals("Y")) {
                int amountHealed = doHeal(playerChar);
                System.out.println("You were healed for " + amountHealed + " hitpoints");
                pause(1500);
            }
            else {
                attackerDamage = attack(playerChar, enemyChar);
                printHittingAndWait(playerChar, enemyChar, attackerDamage);
            }
        }
        catch (IOException ignored) {
        }
        if (enemyChar.alive()) {
            attackerDamage = attack(enemyChar, playerChar);
            printHittingAndWait(enemyChar, playerChar, attackerDamage);
        }
    }

    void fightLoop() {
        while ((playerChar.alive()) && (enemyChar.alive())) {
            doRound();
        }
    }
    int doHeal(GameCharacter gameCharacter) {
        int amountHealed = 5;
        gameCharacter.setHitpoints(gameCharacter.getHitpoints() + amountHealed);
        return amountHealed;
    }
    void printHittingAndWait(GameCharacter attacker, GameCharacter defender, int attackerDamage) {
        System.out.println(attacker.getName(true) + " hit " + defender.getName(true) + " for " + attackerDamage);
        pause(1500);
        System.out.println(defender.getName(true) + " has " + defender.getHitpoints() + " hitpoints left.");
        pause(1500);
    }
    void pause(int timePaused) {
        try {
            Thread.sleep(timePaused);
        }
        catch(InterruptedException ignored) {
        }
    }
}
