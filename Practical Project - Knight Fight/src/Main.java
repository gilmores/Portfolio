public class Main {
    private static String gamedata = "gamedata.csv";
    private static String saveData = "knights.csv";

    public static void main(String[] args) {
        parseArgs(args);
        GameData data = new CSVGameData(gamedata, saveData);
        GameView view = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);
        controller.start();
    }
    private static void parseArgs(String[] args) {
        for (String arg : args) {
            if(arg.startsWith("--data")) {
                gamedata = arg.substring(arg.indexOf("=") + 1);
            }
            else {
                saveData = arg;
            }
        }
    }
}
