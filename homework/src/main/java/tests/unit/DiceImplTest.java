package tests.unit;

import ru.otus.game.Dice;
import ru.otus.game.DiceImpl;

public class DiceImplTest {

    private Dice dice = new DiceImpl();

    public void testRolledDieValue() {
        String scenario = "Тест на бросок кубика";
        try {
            int x = dice.roll();
            if (x >= 1 && x <= 6) {
                System.out.printf("\"%s\" выполнен %n", scenario);
            } else {
                throw new AssertionError(String.format("Ожидалось число (1-6), получено %d", x));
            }
        } catch (Throwable e) {
            System.err.printf("\"%s\" завершился с ошибкой \"%s\"%n",scenario, e.getMessage());
        }
    }
}
