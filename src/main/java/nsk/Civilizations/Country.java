package src.main.java.nsk.Civilizations;

import src.main.java.nsk.Resources.Entities.Citizen;
import src.main.java.nsk.Resources.Entities.Knight;
import src.main.java.nsk.Resources.Entity;
import src.main.java.nsk.Resources.GameInstance;

import java.util.ArrayList;
import java.util.List;

public class Country {

    private String name;
    private final List<Entity> lives = new ArrayList<>();

    public Country(String name) {
        setName(name);
    }

    public Country(String name, List<Entity> entities) {
        setName(name);
        this.lives.addAll(entities);
    }

    private void setName(String n) {
        this.name = n;
    }
    public String getName() {
        return this.name;
    }

    public void addEntity(Entity e) {
        this.lives.add(e);
    }
    public void addEntity(List<Entity> es) {
        this.lives.addAll(es);
    }

    public Entity getEntityByIndex(int i) {
        return this.lives.get(i);
    }
    public List<Entity> getEntities() {
        try {
            if (!this.lives.isEmpty()) {
                return this.lives;
            } else {
                throw new IllegalArgumentException("This country does not have any lives.");
            }
        } catch (Exception e) {
            GameInstance.getInstance().consoleError(e);
            return null;
        }
    }
    public <T> List<Entity> getAllEntitiesOfClass(T type) {
        List<Entity> LivesOfClass = new ArrayList<>();

        for (Entity e : lives) {
            if (e.getClass().equals(type)) {
                LivesOfClass.add(e);
            }
        }

        return LivesOfClass;
    }

    public void removeEntity(Entity e) {
        this.lives.remove(e);
    }
    public void removeEntity(List<Entity> es) {
        this.lives.removeAll(es);
    }
    public void removeEntityByIndex(int i) {
        this.lives.remove(i);
    }

    //  --  --  --  --  //  --  --  --  --  // Logic //  --  --  --  --  //  --  --  --  --  //

    public void damageEntity(int i) {
        try {
            this.lives.get(i).dropHealth();
        } catch (Exception e) {
            lives.remove(i);
            GameInstance.getInstance().consoleError(e);
        }
    }

    public void damageEntity(int i, int d) {
        try {
            this.lives.get(i).dropHealth(d);
        } catch (Exception e) {
            lives.remove(i);
            GameInstance.getInstance().consoleError(e);
        }
    }

    public int getCountryPower() {
        int power = 0;

        for (Entity e : this.getEntities()) {
            power = power + e.getDamage();
        }

        return power;
    }

    public int getArmyPower() {
        int power = 0;

        for (Entity e : this.getAllEntitiesOfClass(Knight.class)) {
            power = power + e.getDamage();
        }

        return power;
    }

    //  --  --  --  --  //  --  --  --  --  // Mechanics //  --  --  --  --  //  --  --  --  --  //

    public void attack(Country enemy) {

        if (this.getArmyPower() < enemy.getArmyPower()) {

            int difference = enemy.getArmyPower() - this.getArmyPower();

            for (Entity e : this.getAllEntitiesOfClass(Knight.class)) {
                try {
                    e.dropHealth(30); // - All knights will take 5 damage.
                } catch (Exception ex) {
                    GameInstance.getInstance().consoleError(ex);
                    this.lives.remove(e);
                }
            }

            for (Entity e : enemy.getAllEntitiesOfClass(Knight.class)) {
                try {
                    e.dropHealth(12); // - All knights of the winning Country will take 2 damage.
                } catch (Exception ex) {
                    GameInstance.getInstance().consoleError(ex);
                    enemy.lives.remove(e);
                }
            }

            for (int i=0; i<(this.lives.size()/2); i++) {

                List<Entity> entitiesToDie = new ArrayList<>();

                if (this.lives.get(i).equals(Citizen.class)) {
                    try {
                        this.damageEntity(i, (difference/2)); // - All citizens will take damaged based on the difference in power.
                    } catch (Exception ex) {
                        GameInstance.getInstance().consoleError(ex);
                        entitiesToDie.add(this.lives.get(i));
                    }
                }

                this.lives.removeAll(entitiesToDie);
            }

        } else if (this.getArmyPower() == enemy.getArmyPower()) {

            for (Entity e : this.getAllEntitiesOfClass(Knight.class)) {
                this.lives.remove(e); // - If armies are equal... all Knights dies!
            }

            for (Entity e : enemy.getAllEntitiesOfClass(Knight.class)) {
                enemy.lives.remove(e); // - If armies are equal... all Knights dies!
            }

        } else {

            int difference =  this.getArmyPower() - enemy.getArmyPower();

            for (Entity e : enemy.getAllEntitiesOfClass(Knight.class)) {
                try {
                    e.dropHealth(30); // - All knights will take 5 damage.
                } catch (Exception ex) {
                    GameInstance.getInstance().consoleError(ex);
                    enemy.lives.remove(e);
                }
            }

            for (Entity e : this.getAllEntitiesOfClass(Knight.class)) {
                try {
                    e.dropHealth(12); // - All knights of the winning Country will take 2 damage.
                } catch (Exception ex) {
                    GameInstance.getInstance().consoleError(ex);
                    this.lives.remove(e);
                }
            }

            for (int i=0; i<(enemy.lives.size()/2); i++) {

                List<Entity> entitiesToDie = new ArrayList<>();

                if (enemy.lives.get(i).equals(Citizen.class)) {
                    try {
                        enemy.damageEntity(i, (difference/2)); // - All citizens will take damaged based on the difference in power.
                    } catch (Exception ex) {
                        GameInstance.getInstance().consoleError(ex);
                        entitiesToDie.add(enemy.lives.get(i));
                    }
                }

                enemy.lives.removeAll(entitiesToDie);
            }

        }

    }

}
