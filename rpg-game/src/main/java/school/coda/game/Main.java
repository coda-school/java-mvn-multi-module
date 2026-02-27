package school.coda.game;

import school.coda.rpg.party.Dungeon;
import school.coda.rpg.party.Party;

public  class Main {

    static void main() {
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
