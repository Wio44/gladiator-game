package sk.wio.service;

import sk.wio.ability.Ability;
import sk.wio.ability.HeroAbilityManager;
import sk.wio.domain.Hero;
import sk.wio.utility.InputUtils;
import sk.wio.utility.PrintUtils;

import java.util.Map;

public class GameManager {
    private final Hero hero;
    private final HeroAbilityManager heroAbilityManager;

    public GameManager() {
        this.hero = new Hero("");
        this.heroAbilityManager = new HeroAbilityManager(hero);
    }

    public void startGame() {
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

