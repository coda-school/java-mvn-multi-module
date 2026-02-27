package school.coda.rpg.character.job;

import school.coda.rpg.character.abilities.damage.Attacker;
import school.coda.rpg.character.abilities.damage.Damage;
import school.coda.rpg.character.abilities.healing.Healer;
import school.coda.rpg.character.abilities.healing.Healing;


public class Paladin extends RpgCharacter implements Healer, Attacker {

    private final Healing healing;
    private final Damage damage;

    public Paladin(String name, int defense, int money, int hp, int healing, int attack) {
        super(name, defense, money, hp);
        this.healing = new Healing(healing);
        this.damage = new Damage(attack);
    }

    @Override
    public void heal(RpgCharacter ally) {
        if(isConscious()){{
        ally.receives(healing);
        }}
    }

    @Override
    public void attack(RpgCharacter opponent) {
        if(isConscious()) {
            opponent.takes(damage);
        }
    }

    @Override
    public String toString() {
        return "🫅"+super.toString();
    }
}
