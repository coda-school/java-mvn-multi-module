package school.coda.rpg.character.job;

public class Tourist extends RpgCharacter {
    protected Tourist(String name, int defense, int money, int hp) {
        super(name, defense, money * 10, hp);
    }

    @Override
    public String toString() {
        return "📸"+super.toString();
    }
}
