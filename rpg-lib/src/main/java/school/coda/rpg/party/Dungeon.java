package school.coda.rpg.party;

import school.coda.rpg.character.job.Priest;
import school.coda.rpg.character.job.Warrior;

import java.util.Random;

public class Dungeon extends Party {

    public Dungeon() {
        super();
    }

    public Dungeon(Random randomGenerator) {
        super(randomGenerator);
    }

    protected void hire() {
        hireBoss("ZOOOG");
        hireMinionPriest("Zig");
        hireMinionPriest("Ziig");
        hireMinionWarrior("Zaaag");
        hireMinionWarrior("Zaaaaag");
        hireMinionWarrior("Zaaaaaaag");
        hireMinionWarrior("Zaaaaaaaaag");
    }

    protected void hireMinionWarrior(String name) {
        int attack = randomGenerator.nextInt(1, 10);
        int defense = randomGenerator.nextInt(0, 4);
        int money = randomGenerator.nextInt(0, 50);
        int hp = randomGenerator.nextInt(10, 20);
        Warrior warrior = new Warrior(name,
                attack,
                defense,
                money,
                hp);
        this.rpgCharacters.add( warrior);
        this.attackers.add(warrior);

    }

    protected void hireMinionPriest(String name) {
        int healing = randomGenerator.nextInt(1, 3);
        int defense = randomGenerator.nextInt(0, 2);
        int money = randomGenerator.nextInt(0, 75);
        int hp = randomGenerator.nextInt(5, 10);
        Priest priest = new Priest(name,
                defense,
                money,
                hp,
                healing
                );
        this.rpgCharacters.add( priest);
        this.healers.add(priest);

    }

    protected void hireBoss(String name) {
        int attack = randomGenerator.nextInt(5, 7);
        int defense = randomGenerator.nextInt(2, 5);
        int money = randomGenerator.nextInt(0, 500);
        int hp = randomGenerator.nextInt(50, 100);
        Warrior warrior = new Warrior(name,
                attack,
                defense,
                money,
                hp);
        this.rpgCharacters.add( warrior);
        this.attackers.add(warrior);

    }
}
