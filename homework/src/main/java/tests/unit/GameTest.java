package tests.unit;

import assertions.Assertions;
import ru.otus.game.*;
import tests.unit.fake.Mock;
import tests.unit.fake.Stub;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameTest {
    private ByteArrayOutputStream baos;
    private final PrintStream old = System.out;
    private final Dice dice = () -> 4;
    private final Player player1 = new Player("Tom");
    private Player player2 = new Player("Jack");
    private Stub stub;

    public void changeOutputStream() {
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    public  void returnOutputStream() {
        System.setOut(old);
    }

    public void testForNullPlayer(){
        String scenario = "Тест на null в Player player";
        Game game = new Game(dice, new GameWinnerConsolePrinter());
        player2 = null;
        changeOutputStream();

        try {
            game.playGame(player1, player2);
            String expected = "Победитель: Jack\n";
            String actual = baos.toString();
            Assertions.assertEquals(expected, actual);
            System.out.printf("\"%s\" прошел %n", scenario);
        } catch (Throwable e){
            System.err.printf("\"%s\" завершился с ошибкой \"%s\"%n",scenario, e);
        } finally {
            returnOutputStream();
        }
    }

    public void testForNullPointerException() {
        String scenario = "Тест на NullPointerException";
        Game game = new Game(null, new GameWinnerConsolePrinter());
        try {
            Assertions.assertThrows(NullPointerException.class, () -> game.playGame(player1, player2));
            System.out.printf("\"%s\" прошел %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" завершился с ошибкой \"%s\"%n",scenario, e);
        }
    }

    public void testForNormalWork() {
        String scenario = "Тест на правильную работу";
        Game game = new Game( dice, new GameWinnerConsolePrinter());
        changeOutputStream();

        try {
            game.playGame(player1, player2);
            String expected = "Победитель: Jack\n";
            String actual = baos.toString();
            returnOutputStream();
            Assertions.assertEquals(expected, actual);
            System.out.printf("\"%s\" прошел %n", scenario);

        } catch (Throwable e) {
            System.err.printf("\"%s\" завершился с ошибкой \"%s\"%n",scenario, e);
        }
    }

    public void testForWrongWork() {
        String scenario = "Тест на неправильную работу";
        Game game = new Game( dice, new GameWinnerConsolePrinter());
        changeOutputStream();

        try {
            game.playGame(player1, player2);
            String expected = "Победитель: Tom\n";
            String actual = baos.toString();
            returnOutputStream();
            Assertions.assertNotEquals(expected, actual);
            System.out.printf("\"%s\" прошел %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" завершился с ошибкой \"%s\"%n",scenario, e);
        }
    }

    public void test() {
        String scenario = "Тест с Mock";
        Mock mock = new Mock();
        stub = new Stub();
        Game game = new Game(stub, mock);
        try {
            game.playGame(player1, player2);
            String expected = "Tom";
            Assertions.assertEquals(expected, mock.getActual());
           // mock.printWinner(new Player("asd"));
            System.out.printf("\"%s\" прошел, %s %n", scenario, mock.getStatistic());
        } catch (Throwable e) {
            System.err.printf("\"%s\" завершился с ошибкой \"%s\"%n",scenario, e);
        }
    }
}
