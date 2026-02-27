package school.coda.rpg.character.job;

import school.coda.rpg.character.abilities.healing.Healer;
import school.coda.rpg.character.abilities.healing.Healing;

public class Priest extends RpgCharacter implements Healer {

    private final Healing healing;

    public Priest(String name, int defense, int money, int hp, int healing) {
        super(name, defense, money, hp);
        this.healing = new Healing(healing + 2);
    }

    @Override
    public void heal(RpgCharacter ally) {
        if(isConscious()) {
            ally.receives(healing);
        }
    }

    @Override
    public String toString() {
        return "👩‍⚕️"+super.toString();
    }
}
