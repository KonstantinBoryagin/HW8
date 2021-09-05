package tests.unit.fake;

import ru.otus.game.Dice;

public class Stub implements Dice {
    private int counter = 0;
    @Override
    public int roll() {
        if (counter == 0) {
            counter++;
            return 5;
        } else {
            return 3;
        }
    }
}
