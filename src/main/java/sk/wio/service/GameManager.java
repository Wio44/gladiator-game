package sk.wio.service;

import sk.wio.ability.HeroAbilityManager;
import sk.wio.constant.Constants;
import sk.wio.domain.Hero;
import sk.wio.utility.InputUtils;
import sk.wio.utility.PrintUtils;

public class GameManager {
    private final Hero hero;
    private final HeroAbilityManager heroAbilityManager;
    private int currentLevel;

    public GameManager() {
        this.hero = new Hero("");
        this.heroAbilityManager = new HeroAbilityManager(hero);
        this.currentLevel = Constants.INITIAL_LEVEL;
    }

    public void startGame() {
       this.initGame();

        while (this.currentLevel <= 5) {
            System.out.println("0. Fight " + "Level " + this.currentLevel);
            System.out.println("1. Upgrade abilities (" + hero.getAvailablePoints() + " points to spend)");
            System.out.println("2. Save game");
            System.out.println("3. Exit game");

            final int choice = InputUtils.readInt();
            switch (choice) {
                case 0 -> {
                   // TODO fight
                    this.currentLevel++;
                }
                case 1 -> {
                    // TODO upgrade abilities
                }
                case 2 -> {
                    // TODO save game
                }
                case 3 -> {
                    System.out.println("Are you sure?");
                    System.out.println("0. N0");
                    System.out.println("1. Yes");
                    final int exitChoice = InputUtils.readInt();
                    if (exitChoice == 1) {
                        System.out.println("Bye!");
                        return;
                    }
                    System.out.println("Continuing...");
                    PrintUtils.printDivider();
                }
                default -> System.out.println("Invalid choice");
            }
        }

        System.out.println("You have won the game! Congratulations!");
    }

    private void initGame() {
        System.out.println("Welcome to the Gladiator game!");
        System.out.println("Enter your name: ");
        final String name = InputUtils.readString();
        this.hero.setName(name);
        System.out.println("Hello " + hero.getName() + "! Let's start the game!");
        PrintUtils.printDivider();
        System.out.println("Your abilities are: ");
        PrintUtils.printAbilities(hero);
        PrintUtils.printDivider();
        this.heroAbilityManager.spendHeroAvailablePoints();
    }
}

