package game;

import java.util.concurrent.ThreadLocalRandom;
class CombatRunner {
    GameCharacter playerChar;
    GameCharacter enemyChar;
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
        PromptHelper.printDivider();
        boolean answeredYes = PromptHelper.askYesOrNo("Do you want to heal? Y/N");
        PromptHelper.printDivider();
        if (answeredYes) {
            if (((PlayerCharacter)playerChar).getAmountOfHealingPotions() > 0) {
                ((PlayerCharacter)playerChar).setAmountOfHealingPotions(((PlayerCharacter)playerChar).getAmountOfHealingPotions()-1);
                int amountHealed = playerChar.doMaxHeal();
                System.out.println("You were healed for " + amountHealed + " hitpoints");
            }
            else {
                System.out.println("You have no healing potions to drink.");
            }
            pause(1500);
        }
        else {
            attackerDamage = attack(playerChar, enemyChar);
            printHittingAndWait(playerChar, enemyChar, attackerDamage);
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
