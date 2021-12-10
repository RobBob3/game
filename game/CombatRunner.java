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
        if (attackerDamage < 0 || defender.isInvulnerable()) {
            attackerDamage = 0;
        }
        defender.getHurt(attackerDamage);
        return attackerDamage;
    }

    void doRound() {
        int attackerDamage;
        PromptHelper.printDivider();
        boolean potionDrank = handleHealingPotions() || handleInvulnerabilityPotions();
        if (!potionDrank) {
            attackerDamage = attack(playerChar, enemyChar);
            printHittingAndWait(playerChar, enemyChar, attackerDamage);
        }

        if (enemyChar.alive()) {
            attackerDamage = attack(enemyChar, playerChar);
            printHittingAndWait(enemyChar, playerChar, attackerDamage);
        }
        playerChar.timerTickDown();
        enemyChar.timerTickDown();
        if (playerChar.getTurnsInvulnerable() > 0) {
            System.out.println("Turns invulnerable = " + playerChar.getTurnsInvulnerable());
        }
    }

    void fightLoop() {
        while ((playerChar.alive()) && (enemyChar.alive())) {
            doRound();
        }
    }
    void printHittingAndWait(GameCharacter attacker, GameCharacter defender, int attackerDamage) {
        System.out.println(attacker.getName(true) + " hit " + defender.getName(true) + " for " + attackerDamage);
        PromptHelper.pause(1500);
        System.out.println(defender.getName(true) + " has " + defender.getHitpoints() + " hitpoints left.");
        PromptHelper.pause(1500);
    }

    private boolean handleHealingPotions() {
        int amountHealed = 0;
        boolean answeredYes;
        boolean playerHealed = false;
        
        if (playerChar.getAmountOfHealingPotions() >= 1) {
            answeredYes = PromptHelper.askYesOrNo("Do you want to drink a healing potion? Y/N");
            if (answeredYes) {
                amountHealed = HealingPotion.drink(playerChar);
                playerHealed = true;
                System.out.println("You healed for " + amountHealed + " hitpoints.");
            }
        }
        PromptHelper.printDivider();
        PromptHelper.pause(500);
        return playerHealed;
    }

    private boolean handleInvulnerabilityPotions() {
        boolean answeredYes = false;
        boolean drankPotion = false;
        if (playerChar.getAmountOfInvulnerabilityPotions() >= 1)
        answeredYes = PromptHelper.askYesOrNo("Do you want to drink an invulnerability potion? Y/N");
        if (answeredYes) {
            drankPotion = true;
            InvulnerabilityPotion.drink(playerChar);
        }
        PromptHelper.printDivider();
        PromptHelper.pause(500);
        return drankPotion;
    }
}
