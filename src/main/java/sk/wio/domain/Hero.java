package sk.wio.domain;

import sk.wio.ability.Ability;
import sk.wio.constant.Constants;

import java.util.HashMap;
import java.util.Map;

public class Hero extends GameCharacter {
    private String name;
    private Map<Ability, Integer> abilities;
    private int availablePoints;

    public Hero(String name) {
        super(name, new HashMap<>());
        this.abilities = getInitialAbilities();
        this.availablePoints = Constants.INITIAL_ABILITY_POINTS;
    }

    public Hero(String name, Map<Ability, Integer> abilities, int availablePoints) {
        super(name, abilities);
        this.availablePoints = availablePoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void updateAbility(Ability ability, int delta) {
        if (ability.equals(Ability.HEALTH)) {
            abilities.put(ability, this.abilities.get(ability) + delta * Constants.HEALTH_OF_ONE_POINT);
        } else {
            abilities.put(ability, this.abilities.get(ability) + delta);
        }
    }

    public void updateAvailablePoints(int delta) {
        this.availablePoints += delta;
    }

    private Map<Ability, Integer> getInitialAbilities() {
        return new HashMap<>(Map.of(
                Ability.ATTACK, 1,
                Ability.DEFENCE, 1,
                Ability.DEXTERITY, 1,
                Ability.SKILL, 1,
                Ability.LUCK, 1,
                Ability.HEALTH, 50
        ));
    }
}
