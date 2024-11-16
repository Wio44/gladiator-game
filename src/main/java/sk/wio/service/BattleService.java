package sk.wio.service;

import sk.wio.domain.Enemy;
import sk.wio.domain.Hero;
import sk.wio.utility.InputUtils;
import sk.wio.utility.PrintUtils;

public class BattleService {
    public boolean isHeroReadyToBattle(Hero hero, Enemy enemy) {
        System.out.println(hero.getName() + " VS " + enemy.getName());
        System.out.println("View your abilities: ");
        PrintUtils.printAbilities(hero);
        PrintUtils.printDivider();
        System.out.println("View enemy abilities: ");
        PrintUtils.printAbilities(enemy);

        System.out.println("Are you ready tio fight?");
        System.out.println("0. No");
        System.out.println("1. Yes");

        final int choice = InputUtils.readInt();
        switch (choice) {
            case 0 -> {
                System.out.println("You have escaped fro battle");
                return false;
            }
            case 2 -> {
                System.out.println("Let the battle begin");
                return true;
            }
            default -> {
                System.out.println("Invalid choice");
                return false;
            }
        }
    }
}
