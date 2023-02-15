public class GameController {
    private final GameData data;
    private final GameView view;
    private final CombatEngine engine;

    public GameController(GameData data, GameView view, CombatEngine engine) {
        this.data = data;
        this.view = view;
        this.engine = engine;
    }

    public void start() {
        view.splashScreen();
        boolean runAgain = true;
        while (runAgain) {
            runAgain = processCommand(view.displayMainMenu());
        }
    }
    protected boolean processCommand(String command) {
        // I originally tried to do this with a switch case, but there is too much data given in the
        // user input to avoid any manipulation: if statements work better.
        if (command.contains("exit") || command.contains("bye")) return false;
        else if (command.equals("ls") || command.equals("list all")) view.listKnights(data.getKnights());
        else if (command.contains("list active")) view.listKnights(data.getActiveKnights());
        else if (command.contains("show")) {
            String nameOrId = command.substring(5);
            view.showKnight(data.findKnight(nameOrId, data.getKnights()));
        }
        else if (command.contains("set active")) {
            String nameOrId = command.substring(11);
            data.setActive(data.findKnight(nameOrId, data.getKnights()));
        }
        else if (command.contains("remove")) {
            String nameOrId = command.substring(7);
            Knight kt = data.getActive(nameOrId);
            if (kt == null) {
                view.knightNotFound();
            }
            else {
                data.getActiveKnights().remove(kt);
            }
        }
        else if (command.contains("save")) {
            if (command.equals("save")) {
                data.save("saveData.csv");
            }
            else {
                String fileName = command.substring(5);
                data.save(fileName);
            }
        }
        else if (command.equals("explore") || command.equals("adventure") || command.equals("quest")) {
            engine.initialize();
            engine.runCombat();
            engine.clear();
        }
        else {
            view.printHelp();
        }
        return true;
    }
}
