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
    private static final StringBuilder menu = new StringBuilder();

    public GameOfLife() {
        GameInstance.setInstance(this);

        try {

            this.setMenu();

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

    private void setMenu() {
        menu.append("\nWelcome to the Game Of Life!\n")
                .append("Quick notification! This is simple sandbox simulator.\n\n")

                .append("Please check your options below:\n")
                .append(" - 'create' {country name}\n")
                .append(" - 'add' {country_name} {type_of_knight}\n")
                .append("       | + 'trainee'   | + 'warrior'   | + 'bowman'    |\n")
                .append("       | + 'horseman'  | + 'magician   | + 'mage       |\n")
                .append(" - 'attack' {country_name}")
                .append(" - 'status' {country_name}'\n")
                .append(" - 'help'\n")
                .append(" - 'exit'\n\n")

                .append("Use console to write commands above.\n");
    }
    public void showMenu() {
        this.systemMessage(menu.toString());
    }

    public static void main(String[] args) {
        new GameOfLife();
        GameInstance.getInstance().showMenu();


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

    public Country getCountry(String countryName) {
        try {
            for (Country c : this.countries) {
                if (c.getName().equals(countryName)) {
                    return c;
                }
            }

            throw new IllegalArgumentException("Country not found");

        } catch (Exception e) {
            this.consoleError(e);
            return null;
        }
    }

    private boolean existCountry(String name) {
        for (Country c : this.countries) {
            if (c.getName().equals(name)) {
                return true;
            }
        }
        return false;
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

    public boolean isValidKnightType(String type) {
        try {
            Knight.JobType.valueOf(type.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void consoleError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void systemMessage(String m) {
        System.out.println(m);
    }

    //  --  --  --  --  //  --  --  --  --  // Commands //  --  --  --  --  //  --  --  --  --  //

    private void CommandHandler(String input) {
        String[] args = input.split(" ");
        String command = args[0];

        switch (command) {
            case "create":
                if (args.length == 2) {
                    try {
                        this.addCountry(new Country(args[1]));
                        systemMessage("You have created new country called " + args[1]);
                    } catch (Exception e) {
                        consoleError(e);
                    }
                } else {
                    this.systemMessage("You must enter a country name.");
                }

            case "add":
                if (args.length == 3 && existCountry(args[1])) {
                    if (isValidKnightType(args[2])) {
                        Country c = this.getCountry(args[1]);
                        c.addEntity( new Knight(Knight.JobType.valueOf(args[2])) );

                        systemMessage("You have added new Knight to army of " + c.getName());
                    } else {
                        this.systemMessage("You must enter a valid knight type.");
                    }
                } else {
                    this.systemMessage("You must enter a country name and type of knight.");
                }
        }
    }

}
