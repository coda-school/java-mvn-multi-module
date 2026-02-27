import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import school.coda.rpg.character.job.Mage;
import school.coda.rpg.character.job.RpgCharacter;
import school.coda.rpg.character.job.Thief;
import school.coda.rpg.character.job.Warrior;

import static org.assertj.core.api.Assertions.assertThat;

public class RpgTest {


    @ParameterizedTest
    @CsvSource({
            "0 , 12",
            "1 , 11",
            "12, 0 ",
            "20, 0 ",
    })
    void dealing_damage_against_unarmored(int attack, int expectedOpponentHp) {

        var attacker = new Mage("Dédé", attack, 0, 20, 10);

        int hp = 12;
        var defender = new RpgCharacter("Robert", 0, 5, hp);

        attacker.attack(defender);

        assertThat(defender.toString()).contains(expectedOpponentHp + " ♥️");
    }

    @ParameterizedTest
    @CsvSource({
            "0 , 10",
            "1 , 11",
            "2,  12",
            "3,  12",
    })
    void dealing_damage_against_armored(int defense, int expectedOpponentHp) {

        var attacker = new Mage("Dédé", 2, 0, 20, 10);

        int hp = 12;
        var defender = new RpgCharacter("Robert", defense, 20, hp);

        attacker.attack(defender);

        assertThat(defender.toString()).contains(expectedOpponentHp + " ♥️");
    }


    @Test
    void warrior_deals_plus_1_damage() {
        var warrior = new Warrior("Dédé", 1, 0, 20, 10);

        int hp = 12;
        var defender = new RpgCharacter("Robert", 0, 0, hp);

        warrior.attack(defender);

        assertThat(defender.toString()).contains("10 ♥️");
    }

    @Test
    void thief_steals_on_attack() {
        var thief = new Thief("Thomas", 1, 0, 20, 10);
        var defender = new RpgCharacter("Robert", 1, 5, 12);

        thief.attack(defender);

        assertThat(defender.toString()).contains("0 💰");
        assertThat(thief.toString()).contains("25 💰");
    }

    @ParameterizedTest
    @CsvSource({
            "10, 5, 25",
            "5, 0, 25",
            "4, 0, 24",
            "0, 0, 20",
    })
    void thief_steals(int opponentsMoney, int expectedOpponentMoney, int expectedThiefMoney) {
        var thief = new Thief("Thomas", 1, 0, 20, 10);
        var defender = new RpgCharacter("Robert", 1, opponentsMoney, 12);

        thief.attack(defender);

        assertThat(defender.toString()).describedAs("Opponent money").contains(+expectedOpponentMoney + " 💰");
        assertThat(thief.toString()).describedAs("Thief money").contains(expectedThiefMoney + " 💰");
    }

    @Test
    void mage_recovers_1_health() {
        int hp = 10;
        var mage = new Mage("Mireille", 1, 0, 20, hp);
        int attack = 2;
        var thief = new Thief("Robert", attack, 0, 12, 10);

        // mage must have lost HP to heal
        thief.attack(mage);
        assertThat(mage.toString()).contains("8 ♥️");

        mage.attack(thief);

        assertThat(mage.toString()).contains("9 ♥️");
    }

}
