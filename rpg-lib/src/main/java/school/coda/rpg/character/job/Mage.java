package school.coda.rpg.character.job;

import school.coda.rpg.character.abilities.damage.Attacker;
import school.coda.rpg.character.abilities.damage.Damage;
import school.coda.rpg.character.abilities.healing.Healing;


public class Mage extends RpgCharacter implements Attacker {
    private final Damage damage;
    private final Healing healing;

    public Mage(String name, int attack, int defense, int money, int hp) {
        super(name, defense, money, hp);
        this.damage = new Damage(attack);
        this.healing = new Healing(1);
    }

    @Override
    public void attack(RpgCharacter opponent) {
        if(isConscious()){
            opponent.takes(damage);
            this.receives(healing);
        }
    }

    @Override
    public String toString() {
        return "🧙"+super.toString();
    }
}
