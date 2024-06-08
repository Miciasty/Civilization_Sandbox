package src.main.java.nsk.Civilizations;

import src.main.java.nsk.Resources.Entity;

import java.util.ArrayList;
import java.util.List;

public class Country {

    private String name;
    private List<Entity> lives = new ArrayList<>();

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

}