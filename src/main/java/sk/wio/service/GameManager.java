package sk.wio.service;

import sk.wio.ability.HeroAbilityManager;
import sk.wio.constant.Constants;
import sk.wio.domain.Hero;
import sk.wio.domain.LoadedGame;
import sk.wio.utility.InputUtils;
import sk.wio.utility.PrintUtils;

public class GameManager {
    private Hero hero;
    private final HeroAbilityManager heroAbilityManager;
    private int currentLevel;
    private final FileService fileService;

    public GameManager() {
        this.hero = new Hero("");
        this.heroAbilityManager = new HeroAbilityManager(hero);
        this.currentLevel = Constants.INITIAL_LEVEL;
        this.fileService = new FileService();
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
                case 1 -> this.upgradeAbilities();
                case 2 -> {
                    this.fileService.saveGame(this.hero, this.currentLevel);
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
        System.out.println("0. Start new game");
        System.out.println("1. Load game");
        final int choice = InputUtils.readInt();
        switch (choice) {
            case 0 -> System.out.println("Let's go then");
            case 1 -> {
                final LoadedGame loadedGame = fileService.loadGame();
                if (loadedGame != null) {
                    this.hero = loadedGame.getHero();
                    this.currentLevel = loadedGame.getLevel();
                    return;
                }
            }
            default -> System.out.println("Invalid choice");
        }

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

    private void upgradeAbilities() {
        System.out.println("Your abilities are: ");
        PrintUtils.printAbilities(this.hero);

        System.out.println("0. Go back");
        System.out.println("1. Spend points(" + this.hero.getAvailablePoints() + " points left)");
        System.out.println("2. Remove points");

        final int choice = InputUtils.readInt();
        switch (choice) {
            case 0 -> {
            }
            case 1 -> this.heroAbilityManager.spendHeroAvailablePoints();
            case 2 -> this.heroAbilityManager.removeHeroAvailablePoints();
            case 3 -> System.out.println("Invalid choice");
        }
    }
}

