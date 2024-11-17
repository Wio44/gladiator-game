package sk.wio.service;

import sk.wio.ability.Ability;
import sk.wio.ability.HeroAbilityManager;
import sk.wio.constant.Constants;
import sk.wio.domain.Enemy;
import sk.wio.domain.Hero;
import sk.wio.domain.LoadedGame;
import sk.wio.utility.EnemyGenerator;
import sk.wio.utility.InputUtils;
import sk.wio.utility.PrintUtils;

import java.util.Map;

public class GameManager {
    private Hero hero;
    private final HeroAbilityManager heroAbilityManager;
    private int currentLevel;
    private final FileService fileService;

    private final BattleService battleService;

    private final Map<Integer, Enemy> enemiesByLevel;

    public GameManager() {
        this.hero = new Hero("");
        this.fileService = new FileService();
        this.battleService = new BattleService();
        this.heroAbilityManager = new HeroAbilityManager(this.hero);
        this.currentLevel = Constants.INITIAL_LEVEL;
        this.enemiesByLevel = EnemyGenerator.createEnemies();
    }

    public void startGame() throws InterruptedException {
        this.initGame();

        while (this.currentLevel <= this.enemiesByLevel.size()) {
            final Enemy enemy = this.enemiesByLevel.get(this.currentLevel);
            System.out.println("0. Fight " + enemy.getName() + " (Level " + this.currentLevel + ")");
            System.out.println("1. Upgrade abilities (" + hero.getAvailablePoints() + " points left)");
            System.out.println("2. Save game");
            System.out.println("3. Exit game");

            final int choice = InputUtils.readInt();
            switch (choice) {
                case 0 -> {
                    if (battleService.isHeroReadyToBattle(this.hero, enemy)) {
                        final int heroHealthBeforeBattle = this.hero.getAbilities().get(Ability.HEALTH);

                        // battle
                        final boolean hasHeroWon = battleService.battle(this.hero, enemy);
                        if (hasHeroWon) {
                            PrintUtils.printDivider();
                            System.out.println("You have won this battle! You have gained " + this.currentLevel + " ability points.");
                            this.hero.updateAvailablePoints(this.currentLevel);
                            this.currentLevel++;
                        } else {
                            System.out.println("You have lost!");
                        }

                        // restore hero health
                        this.hero.setAbility(Ability.HEALTH, heroHealthBeforeBattle);
                        System.out.println("You have full health now");
                        PrintUtils.printDivider();
                    }
                }
                case 1 -> this.upgradeAbilities();
                case 2 -> fileService.saveGame(this.hero, this.currentLevel);
                case 3 -> {
                    System.out.println("Are you sure?");
                    System.out.println("0. N0");
                    System.out.println("1. Yes");
                    final int exitChoice = InputUtils.readInt();
                    if (exitChoice == 1) {
                        System.out.println("Bye!");
                        return;
                    }
                    System.out.println("Continuing game...");
                    PrintUtils.printDivider();
                }
                default -> System.out.println("Invalid choice!");
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
            case 0 -> System.out.println("Let's go then.");
            case 1 -> {
                final LoadedGame loadedGame = fileService.loadGame();
                if (loadedGame != null) {
                    this.hero = loadedGame.getHero();
                    this.currentLevel = loadedGame.getLevel();
                    return;
                }
            }
            default -> System.out.println("Invalid choice!");
        }

        System.out.println("Enter your name: ");
        final String name = InputUtils.readString();
        this.hero.setName(name);
        System.out.println("Hello " + hero.getName() + "! Let's start the game!");
        PrintUtils.printDivider();
        System.out.println("Your abilities are:");
        PrintUtils.printAbilities(this.hero);
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
            default -> System.out.println("Invalid choice!");
        }
    }
}

