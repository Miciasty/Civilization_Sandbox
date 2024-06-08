package src.test.java.nsk;

import org.junit.Before;
import src.main.java.nsk.Civilizations.Country;
import src.main.java.nsk.GameOfLife;

public class TestWar {

    private Country V, E;
    private GameOfLife gameOfLife;

    @Before
    public void init() {
        gameOfLife = new GameOfLife();

        V = new Country("Valhalla");
        E = new Country("Empire");

        gameOfLife.addCountry(V);
        gameOfLife.addCountry(E);
    }

}
