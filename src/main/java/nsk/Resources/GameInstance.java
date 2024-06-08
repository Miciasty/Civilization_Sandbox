package src.main.java.nsk.Resources;

import src.main.java.nsk.GameOfLife;

public class GameInstance {

    private static GameOfLife instance;

    public static GameOfLife getInstance() {
        return instance;
    }

    public static void setInstance(GameOfLife gameInstance) {
        instance = gameInstance;
    }
}
