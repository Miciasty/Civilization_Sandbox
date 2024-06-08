package src.main.java.nsk;

import src.main.java.nsk.Civilizations.Country;
import src.main.java.nsk.Resources.Entities.Citizen;
import src.main.java.nsk.Resources.Entities.Knight;
import src.main.java.nsk.Resources.Entity;
import src.main.java.nsk.Resources.GameInstance;

import java.util.ArrayList;
import java.util.List;

public class GameOfLife {

    private final List<Country> countries = new ArrayList<>();

    public GameOfLife() {
        GameInstance.setInstance(this);

        try {

            List<Entity> entities = new ArrayList<>();

            for (int i = 0; i < 30; i++) {
                entities.add(new Citizen(Citizen.JobType.Basic));
            }
            for (int i = 0; i < 10; i++) {
                entities.add(new Citizen(Citizen.JobType.Aristocrat));
            }
            for (int i = 0; i < 400; i++) {
                entities.add(new Knight(30, 45, Knight.JobType.Magician));
            }

            this.countries.add(new Country("Western Empire", entities));


            GameInstance.getInstance().systemMessage("Starting new session...");
        } catch (Exception e) {
            this.consoleError(e);
        }
    }

    public static void main(String[] args) {
        new GameOfLife();

        StringBuilder menu = new StringBuilder();
        menu.append("Welcome to the Game Of Life!\n")
            .append("Quick notification! This is simple sandbox simulator.\n\n")

            .append("Please check your options below:\n")
            .append(" - 'create' {country name}\n")
            .append(" - 'add' {type_of_soldier}\n")
            .append("         + 'trainee'\n")
            .append("         + 'warrior'\n")
            .append("         + 'bowman'\n")
            .append("         + 'horseman'\n")
            .append("         + 'magician'\n")
            .append("         + 'mage'\n")
            .append(" - 'attack' {country_name}")
            .append(" - 'status'\n")
            .append(" - 'help'\n")
            .append(" - 'exit'\n\n")

            .append("Use console to write commands above.\n");

        System.out.println(menu.toString());

    }

    public void addCountry(Country c) {
        this.countries.add(c);
    }

    public Country getCountry(int i) {
        try {
            if (this.countries.get(i) != null) {
                return this.countries.get(i);
            } else {
                throw new IllegalArgumentException("Country not found");
            }
        } catch (Exception e) {
            this.consoleError(e);
            return null;
        }
    }

    public List<Country> getCountries() {
        return this.countries;
    }

    public void removeCountry(Country c) {
        this.countries.remove(c);
    }
    public void removeCountry(String name) {
        try {
            for (Country c : countries) {
                if (c.getName().equals(name)) {
                    this.countries.remove(c);
                    return;
                }
            }

            throw new IllegalArgumentException("Country with name " + name + " doesn't exists.");

        } catch (Exception e) {
            this.consoleError(e);
        }
    }

    public void consoleError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void systemMessage(String m) {
        System.out.println(m);
    }

}
