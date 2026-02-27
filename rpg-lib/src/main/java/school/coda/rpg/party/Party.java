package school.coda.rpg.party;

import school.coda.rpg.character.abilities.damage.Attacker;
import school.coda.rpg.character.abilities.healing.Healer;
import school.coda.rpg.character.job.RpgCharacter;
import school.coda.rpg.character.job.Mage;
import school.coda.rpg.character.job.Paladin;
import school.coda.rpg.character.job.Priest;
import school.coda.rpg.character.job.Thief;
import school.coda.rpg.character.job.Warrior;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.random.RandomGenerator;

///  A group of hired adventurers, ready to fight monsters in dungeon
public class Party {
    protected final RandomGenerator randomGenerator;

    protected List<RpgCharacter> rpgCharacters = new ArrayList<>();
    protected List<Healer> healers = new ArrayList<Healer>();
    protected List<Attacker> attackers = new ArrayList<>();

    public Party() {
        this(new Random());
    }

    public Party(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;

        hire();

    }

    /// Each attacker of the party attack one random target from the opponent party
    public void attack(Party party) {

        List<RpgCharacter> opponents = party.characters();
        int numberOfOpponents = opponents.size();
        for (Attacker attacker : this.attackers()) {
            int i = randomGenerator.nextInt(0, numberOfOpponents);
            RpgCharacter opponent = opponents.get(i);
            attacker.attack(opponent);
        }
    }

    /// Each healer heals the conscious ally with lower hp first
    public void heal() {


        for (Healer healer : this.healers()) {
            ArrayList<RpgCharacter> characters1 = new ArrayList<>(rpgCharacters);
            characters1.sort(RpgCharacter::comparingToByHp);
            Iterator<RpgCharacter> iterator = characters1.iterator();
            RpgCharacter consciousAlly = null;
            while (consciousAlly == null && iterator.hasNext()) {
                RpgCharacter next = iterator.next();
                if (next.isConscious()) {
                    consciousAlly = next;
                }
            }
            if (consciousAlly != null) {
                // Someone to heal
                healer.heal(consciousAlly);
            }
        }

//        for (Healer healer : this.healers()) {
//            characters.stream()
//                    .filter(Character::isConscious)
//                    .min(Comparator.comparing(Character::hp))
//                    .ifPresent(healer::heal);
//        }
    }

    /// All characters of the party are unconscious
    public boolean hasFallen() {
        for (RpgCharacter rpgCharacter : this.rpgCharacters) {
            if (rpgCharacter.isConscious()) {
                return false;
            }
        }
        return true;
    }

    protected void hire() {
        hireHealer("Ginette");
        hireHealer("Kévin");
        hireAttacker("Leeroy");
        hireAttacker("Boggis");
        hireTank("Gimly");
    }

    protected List<RpgCharacter> characters() {
        return rpgCharacters;
    }

    protected Iterable<Healer> healers() {
        return healers;
    }

    protected Iterable<Attacker> attackers() {
        return attackers;
    }

    protected void hireAttacker(String name) {
        switch (randomGenerator.nextInt(0, 4)) {
            case 0 -> hireMage(name);
            case 1 -> hirePaladin(name);
            case 2 -> hireThief(name);
            default -> hireWarrior(name);
        }
        ;
    }

    protected void hireHealer(String name) {
        if (randomGenerator.nextInt(0, 2) == 0) {
            hirePaladin(name);
        } else {
            hirePriest(name);
        }
        ;
    }

    protected void hireMage(String name) {
        int attack = randomGenerator.nextInt(1, 5);
        int defense = randomGenerator.nextInt(0, 3);
        int money = randomGenerator.nextInt(0, 50);
        int hp = randomGenerator.nextInt(5, 10);
        Mage mage = new Mage(name,
                attack,
                defense,
                money,
                hp);
        this.rpgCharacters.add(mage);
        this.attackers.add(mage);
    }

    protected void hireThief(String name) {
        int attack = randomGenerator.nextInt(1, 7);
        int defense = randomGenerator.nextInt(1, 3);
        int money = randomGenerator.nextInt(0, 70);
        int hp = randomGenerator.nextInt(5, 15);
        Thief thief = new Thief(name,
                attack,
                defense,
                money,
                hp);
        this.rpgCharacters.add(thief);
        this.attackers.add(thief);
    }

    protected void hirePaladin(String name) {
        int defense = randomGenerator.nextInt(1, 5);
        int money = randomGenerator.nextInt(1, 5);
        int hp = randomGenerator.nextInt(0, 10);
        int healing = randomGenerator.nextInt(15, 20);
        int attack = randomGenerator.nextInt(3, 8);
        Paladin paladin = new Paladin(name,
                defense,
                money,
                hp,
                healing,
                attack);

        this.rpgCharacters.add(paladin);
        this.attackers.add(paladin);
        this.healers.add(paladin);
    }

    protected void hireWarrior(String name) {
        int attack = randomGenerator.nextInt(1, 13);
        int defense = randomGenerator.nextInt(1, 5);
        int money = randomGenerator.nextInt(0, 10);
        int hp = randomGenerator.nextInt(15, 20);
        Warrior warrior = new Warrior(name,
                attack,
                defense,
                money,
                hp);
        this.rpgCharacters.add(warrior);
        this.attackers.add(warrior);
    }

    protected void hirePriest(String name) {
        int healing = randomGenerator.nextInt(1, 8);
        int defense = randomGenerator.nextInt(1, 5);
        int money = randomGenerator.nextInt(0, 10);
        int hp = randomGenerator.nextInt(15, 20);
        Priest priest = new Priest(name,
                defense,
                money,
                hp,
                healing);
        this.rpgCharacters.add(priest);
        this.healers.add(priest);
    }

    protected void hireTank(String name) {
        if (randomGenerator.nextInt(0, 2) == 1) {
            hirePaladin(name);
        } else {
            hireWarrior(name);
        }

    }


    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (RpgCharacter rpgCharacter : rpgCharacters) {
            if (rpgCharacter.isConscious()) {
                String string = rpgCharacter.toString();
                joiner.add(string);
            }
        }
        return joiner.toString();
    }
}
