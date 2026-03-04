package school.coda.rpg.character.job;

public class Tourist extends RpgCharacter {
    public Tourist(String name, int defense, int hp, int money) {
        super(name, defense, hp, money * 10);
    }

    @Override
    public String toString() {
        return "📸"+super.toString();
    }
}
