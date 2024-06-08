package src.test.java.nsk;

import org.junit.Before;
import org.junit.Test;
import src.main.java.nsk.Civilizations.Country;
import src.main.java.nsk.GameOfLife;
import src.main.java.nsk.Resources.Entities.Citizen;
import src.main.java.nsk.Resources.Entities.Knight;
import src.main.java.nsk.Resources.Entity;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testWinningWarForValhalla() {
        for (int i = 0; i < 300; i++) {
            V.addEntity(new Citizen());
            V.addEntity(new Knight(25, 20, Knight.JobType.MAGICIAN));
        }

        System.out.println("Valhalla army power: " + V.getArmyPower() + ", power: " + V.getCountryPower());

        for (int i = 0; i < 50; i++) {
            E.addEntity(new Citizen());
            E.addEntity(new Knight(10, 30, Knight.JobType.HORSEMAN));
        }
        for (int i = 0; i < 100; i++) {
            E.addEntity(new Knight(40, 30, Knight.JobType.WARRIOR));
        }

        System.out.println("Empire power: " + E.getArmyPower() + ", power: " + E.getCountryPower());

        V.attack(E);

        System.out.println("\n\nValhalla army power: " + V.getArmyPower() + ", power: " + V.getCountryPower());
        System.out.println("Empire power: " + E.getArmyPower() + ", power: " + E.getCountryPower());

        assertEquals(6000, V.getArmyPower()); assertEquals(6300, V.getCountryPower());
        assertEquals(3000, E.getArmyPower()); assertEquals(3050, E.getCountryPower());

    }

    @Test
    public void testDrawWar() {
        for (int i = 0; i < 300; i++) {
            V.addEntity(new Citizen());
            V.addEntity(new Knight(25, 20, Knight.JobType.MAGICIAN));
        }

        System.out.println("Valhalla army power: " + V.getArmyPower() + ", power: " + V.getCountryPower());

        for (int i = 0; i < 300; i++) {
            E.addEntity(new Citizen());
            E.addEntity(new Knight(45, 20, Knight.JobType.HORSEMAN));
        }

        System.out.println("Empire power: " + E.getArmyPower() + ", power: " + E.getCountryPower());

        V.attack(E);

        System.out.println("Valhalla army power: " + V.getArmyPower() + ", power: " + V.getCountryPower());
        System.out.println("Empire power: " + E.getArmyPower() + ", power: " + E.getCountryPower());

        assertEquals(0, V.getArmyPower()); assertEquals(300, V.getCountryPower());
        assertEquals(0, E.getArmyPower()); assertEquals(300, E.getCountryPower());
    }

}
