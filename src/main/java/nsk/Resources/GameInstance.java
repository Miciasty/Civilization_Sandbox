package src.main.java.nsk.Resources;

import src.main.java.nsk.CivilizationSandbox;

public class GameInstance {

    private static CivilizationSandbox instance;

    public static CivilizationSandbox getInstance() {
        return instance;
    }

    public static void setInstance(CivilizationSandbox gameInstance) {
        instance = gameInstance;
    }
}
