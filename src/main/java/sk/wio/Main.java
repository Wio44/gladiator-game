package sk.wio;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Gladiator game!");
        System.out.println("Enter your name: ");
        final String name = InputUtils.readString();
        final Hero hero = new Hero(name);
        System.out.println("Hello " + hero.getName() + "! Let's start the game!");

        System.out.println("Your abilities are: ");
        for(Map.Entry<Ability, Integer> entry : hero.getAbilities().entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + ", ");
        }
        System.out.println();

        spendHeroAvailablePoints(hero);
    }

    private static void spendHeroAvailablePoints(Hero hero) {
        int availablePoints = hero.getAvailablePoints();

        if (availablePoints == 0) {
            System.out.println("You have no points to spend");
            return;
        }

        while (availablePoints > 0) {
            System.out.println("You have " + availablePoints + " point to spend. Choose wisely.");
            System.out.println("0. Explain abilities");
            System.out.println("1. Attack");
            System.out.println("2. Defence");
            System.out.println("3. Dexterity");
            System.out.println("4. Skill");
            System.out.println("5. Luck");
            System.out.println("6. Health");

            final int abilityIndex = InputUtils.readInt();
            Ability ability;
            switch (abilityIndex) {
                case 0 -> {
                    for(Ability a : Ability.values()) {
                        System.out.println(a + ": " + a.getDescription());
                    }
                    System.out.println();
                    continue;
                }
                case 1 -> ability = Ability.ATTACK;
                case 2 -> ability = Ability.DEFENCE;
                case 3 -> ability = Ability.DEXTERITY;
                case 4 -> ability = Ability.SKILL;
                case 5 -> ability = Ability.LUCK;
                case 6 -> ability = Ability.HEALTH;
                default -> {
                    System.out.println("Invalid index");
                    continue;
                }
            }

            hero.updateAbility(ability, 1);
            System.out.println("You have upgraded "  + ability);

            hero.updateAvailablePoints(-1);
            if (availablePoints > 1) {
                for(Map.Entry<Ability, Integer> entry : hero.getAbilities().entrySet()) {
                    System.out.print(entry.getKey() + ": " + entry.getValue() + ", ");
                }
            }
            availablePoints--;
        }

        System.out.println("You have spent all your abilities points You ability are: ");
        for(Map.Entry<Ability, Integer> entry : hero.getAbilities().entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + ", ");
        }
        System.out.println();
    }
}
