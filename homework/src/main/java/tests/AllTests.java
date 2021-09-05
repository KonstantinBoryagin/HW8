package tests;

import tests.unit.DiceImplTest;
import tests.unit.GameTest;

public class AllTests {
    public static void main(String[] args) {

        new GameTest().testForNormalWork();
        new DiceImplTest().testRolledDieValue();
        new GameTest().testForNullPointerException();
        new GameTest().testForNullPlayer();
        new GameTest().testForWrongWork();
        new GameTest().test();
    }
}
