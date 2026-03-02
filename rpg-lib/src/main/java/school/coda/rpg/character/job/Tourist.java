package school.coda.rpg.character.job;

public class Tourist extends RpgCharacter {
    public Tourist(String name, int defense, int money, int hp) {
        super(name, defense, money * 10, hp);
    }

    @Override
    public String toString() {
        return "📸"+super.toString();
    }
}
