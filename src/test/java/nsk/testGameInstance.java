package src.test.java.nsk;

import org.junit.Test;
import src.main.java.nsk.Civilizations.Country;
import src.main.java.nsk.GameOfLife;
import src.main.java.nsk.Resources.Entity;

public class testGameInstance {

    @Test
    public void testCountryCreation() {
        GameOfLife gol = new GameOfLife();

        Country V = new Country("Valhalla");

        gol.addCountry(V);

        Country c = gol.getCountry(0);
        for (Entity e : c.getEntities()) {
            e.toString();
        }
    }

}
