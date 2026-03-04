package school.coda.game;

import school.coda.rpg.character.job.Mage;
import school.coda.rpg.character.job.Paladin;
import school.coda.rpg.character.job.Priest;
import school.coda.rpg.character.job.RpgCharacter;
import school.coda.rpg.character.job.Thief;
import school.coda.rpg.character.job.Tourist;
import school.coda.rpg.character.job.Warrior;
import school.coda.rpg.party.Dungeon;
import school.coda.rpg.party.Party;

public class Main {

    static void main() {

        RpgCharacter m = new Mage("", 1, 1, 1, 1);
        RpgCharacter w = new Warrior("", 1, 1, 1, 1);
        RpgCharacter th = new Thief("", 1, 1, 1, 1);
        RpgCharacter pa = new Paladin("", 1, 1, 1, 1, 1);
        RpgCharacter pr = new Priest("", 1, 1, 1, 1);
        RpgCharacter to = new Tourist("", 1, 1, 1);
        RpgCharacter r = new RpgCharacter("", 1, 1, 1);


        Party party = new Party();
        System.out.println("-------");
        System.out.println("Party");
        System.out.println(party);
        System.out.println("-------");

        Dungeon dungeon = new Dungeon();
        System.out.println("Dungeon");
        System.out.println(dungeon);
        System.out.println("-------");

        int turn = 0;
        while ((!dungeon.hasFallen()) && (!party.hasFallen()) && turn < 1000) {
            party.attack(dungeon);
            dungeon.attack(party);
            party.heal();
            dungeon.heal();
            turn++;
        }

        if (party.hasFallen()) {
            System.out.println("Party has fallen");
            System.out.println("Dungeon damage :");
            System.out.println(dungeon);
        } else if (dungeon.hasFallen()) {
            System.out.println("Dungeon has fallen");
            System.out.println("Party stats :");
            System.out.println(party);
        } else {
            System.out.println("It's a draw:");
            System.out.println("-------");
            System.out.println("Party");
            System.out.println(party);
            System.out.println("-------");
            System.out.println("Dungeon");
            System.out.println(dungeon);
        }
    }


}
