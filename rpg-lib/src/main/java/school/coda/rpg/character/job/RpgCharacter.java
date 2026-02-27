package school.coda.rpg.character.job;

import school.coda.rpg.character.abilities.damage.Damage;
import school.coda.rpg.character.abilities.healing.Healing;
import school.coda.rpg.character.abilities.stealing.Stealing;

public class RpgCharacter {
    private final String name;
    private final int defense;
    private final int maxHp;

    private int money;
    private int hp;

    public RpgCharacter(String name, int defense, int money, int hp) {
        this.name = name;
        this.defense = defense;
        this.money = money;
        this.hp = hp;
        this.maxHp = hp;
    }

    protected void takes(Damage damage) {
        int effectiveDamage = damage.amount() - this.defense;
        if (effectiveDamage >= this.hp) {
            this.hp = 0;
        } else if (effectiveDamage >= 0) {
            this.hp -= effectiveDamage;
        }

    }

    protected void receiveMoney(int amount) {
        this.money += amount;
    }

    /**
     * @return amount stolen
     */
    protected int undergo(Stealing stealing) {
        int amount = stealing.amount();
        if (amount > this.money) {
            int stolenMoney = this.money;
            this.money = 0;
            return stolenMoney;
        }
        this.money -= amount;
        return amount;
    }

    protected void receives(Healing healing) {
        int amount = healing.amount();
        if (this.hp + amount > maxHp) {
            this.hp = maxHp;
        } else
            this.hp += amount;

    }

    @Override
    public String toString() {
        return "[" + name + " (" + hp + " ♥️, " + money + " 💰)]";
    }

    public boolean isConscious() {
        return hp > 0;
    }

    public int comparingToByHp(RpgCharacter other) {
        return Integer.compare(this.hp, other.hp);
    }
}
