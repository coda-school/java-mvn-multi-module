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

        var attacker = new Mage("Dédé", 0, 10, 20, attack);

        int hp = 12;
        var defender = new RpgCharacter("Robert", 0, hp, 5);

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

        var attacker = new Mage("Dédé", 0, 10, 20, 2);

        int hp = 12;
        var defender = new RpgCharacter("Robert", defense, hp, 20);

        attacker.attack(defender);

        assertThat(defender.toString()).contains(expectedOpponentHp + " ♥️");
    }


    @Test
    void warrior_deals_plus_1_damage() {
        var warrior = new Warrior("Dédé", 0, 10, 20, 1);

        int hp = 12;
        var defender = new RpgCharacter("Robert", 0, hp, 0);

        warrior.attack(defender);

        assertThat(defender.toString()).contains("10 ♥️");
    }

    @Test
    void thief_steals_on_attack() {
        var thief = new Thief("Thomas", 0, 10, 20, 1);
        var defender = new RpgCharacter("Robert", 1, 12, 5);

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
        var thief = new Thief("Thomas", 0, 10, 20, 1);
        var defender = new RpgCharacter("Robert", 1, 12, opponentsMoney);

        thief.attack(defender);

        assertThat(defender.toString()).describedAs("Opponent money").contains(+expectedOpponentMoney + " 💰");
        assertThat(thief.toString()).describedAs("Thief money").contains(expectedThiefMoney + " 💰");
    }

    @Test
    void mage_recovers_1_health() {
        int hp = 10;
        var mage = new Mage("Mireille", 0, hp, 20, 1);
        int attack = 2;
        var thief = new Thief("Robert", 0, 10, 12, attack);

        // mage must have lost HP to heal
        thief.attack(mage);
        assertThat(mage.toString()).contains("8 ♥️");

        mage.attack(thief);

        assertThat(mage.toString()).contains("9 ♥️");
    }

}
