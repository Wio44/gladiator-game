package sk.wio.ability;

import sk.wio.domain.Hero;
import sk.wio.utility.InputUtils;
import sk.wio.utility.PrintUtils;

import java.util.Map;

public class HeroAbilityManager {
    private final Hero hero;

    public HeroAbilityManager(Hero hero) {
        this.hero = hero;
    }

    public void spendHeroAvailablePoints() {
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
                    PrintUtils.printAbilities(hero);
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
            System.out.println("You have upgraded " + ability);

            hero.updateAvailablePoints(-1);
            if (availablePoints > 1) {
                PrintUtils.printAbilities(hero);
            }
            availablePoints--;
        }

        System.out.println("You have spent all your abilities points. Your abilities are: ");
        PrintUtils.printAbilities(hero);
        System.out.println();
    }

    public void removeHeroAvailablePoints() {
        while (true) {
            System.out.println("Which ability do you want to remove?");
            System.out.println("0. I am done");
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
                    return;
                }
                case 1 -> ability = Ability.ATTACK;
                case 2 -> ability = Ability.DEFENCE;
                case 3 -> ability = Ability.DEXTERITY;
                case 4 -> ability = Ability.SKILL;
                case 5 -> ability = Ability.LUCK;
                case 6 -> ability = Ability.HEALTH;
                default -> {
                    System.out.println("Invalid ability index");
                    continue;
                }
            }

            if (this.hero.getAbilities().get(ability) == 1) {
                System.out.println("You have no points to spend!");
            } else {
                this.hero.updateAbility(ability, -1);
                this.hero.updateAvailablePoints(1);
                System.out.println("Ypu have remove 1 point from " + ability);
                PrintUtils.printAbilities(this.hero);
                PrintUtils.printDivider();
            }
        }
    }
}
