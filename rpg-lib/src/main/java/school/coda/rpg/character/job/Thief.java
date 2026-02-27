package school.coda.rpg.character.job;

import school.coda.rpg.character.abilities.damage.Attacker;
import school.coda.rpg.character.abilities.damage.Damage;
import school.coda.rpg.character.abilities.stealing.Stealing;

public class Thief extends RpgCharacter implements Attacker {

    private final Damage damage;
    private final Stealing stealing;

    public Thief(String name, int attack, int defense, int money, int hp) {
        super(name, defense, money, hp);
        this.damage = new Damage(attack);
        this.stealing = new Stealing(5);
    }

    @Override
    public void attack(RpgCharacter opponent) {
        if (isConscious()) {
            opponent.takes(damage);
            int stolenAmount = opponent.undergo(stealing);
            this.receiveMoney(stolenAmount);
        }
    }

    @Override
    public String toString() {
        return "🥷" + super.toString();
    }

}
