package tests.unit.fake;

import ru.otus.game.GameWinnerPrinter;
import ru.otus.game.Player;

public class Mock implements GameWinnerPrinter {
    private static int counter = 0;
    private String actual;

    @Override
    public void printWinner(Player winner) {
        actual = winner.getName();
        counter++;
    }

    public String getStatistic() {
        int temp = counter;
        counter = 0;
        return "Mock вызывался " + temp + " раз";
    }
    public String getActual() {
        return actual;
    }
}
