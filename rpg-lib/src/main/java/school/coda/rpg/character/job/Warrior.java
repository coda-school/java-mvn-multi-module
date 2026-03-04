package school.coda.rpg.character.job;

import school.coda.rpg.character.abilities.damage.Attacker;
import school.coda.rpg.character.abilities.damage.Damage;

public class Warrior extends RpgCharacter implements Attacker {
    private final Damage damage;

    public Warrior(String name, int defense, int hp, int money, int attack) {
        super(name, defense, hp, money);
        this.damage = new Damage(attack + 1);
    }

    @Override
    public void attack(RpgCharacter opponent) {
        if (isConscious()) {
            opponent.takes(damage);
        }
    }

    @Override
    protected void takes(Damage damage) {
        super.takes(new Damage(damage.amount() - 1));
    }

    @Override
    public String toString() {
        return "💪" + super.toString();
    }
}
