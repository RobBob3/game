package game;

import java.util.concurrent.ThreadLocalRandom;
class CombatRunner {
    PlayerCharacter playerChar;
    EnemyCharacter enemyChar;
    CombatRunner(PlayerCharacter playerChar, EnemyCharacter enemyChar) {
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

        boolean playerHealed = doEntireHeal();
        if (!playerHealed) {
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
    private boolean doEntireHeal() {
        int amountHealed = 0;
        boolean answeredYes;
        boolean playerHealed = false;
        if (playerChar.getAmountOfHealingPotions() < 1) {
            System.out.println("You have no healing potions left.");
            return playerHealed;
        }
        answeredYes = PromptHelper.askYesOrNo("Do you want to heal? Y/N");
        if (answeredYes == true) {
            amountHealed = playerChar.doMaxHeal();
            playerChar.setAmountOfHealingPotions(playerChar.getAmountOfHealingPotions() - 1);
            playerHealed = true;
            System.out.println("You healed for " + amountHealed + " hitpoints.");
        }
        PromptHelper.printDivider();
        pause(1500);
        return playerHealed;
    }
}
