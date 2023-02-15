import java.util.Random;

public class CombatEngine {
    private final GameData data;
    private final DiceSet dice;
    private final Random rnd;
    private final GameView view;

    public CombatEngine(GameData data, GameView view) {
        this.data = data;
        this.view = view;
        rnd = new Random();
        dice = new DiceSet();
    }

    public void initialize() {
        for (Knight kt : data.getActiveKnights()) {
            kt.setActiveFortune(data.getRandomFortune());
        }
        view.printFortunes(data.getActiveKnights());
    }
    public void runCombat() {
        
    }


    public void clear() {
        for (Knight kt : data.getKnights()) {
            kt.setActiveFortune(null);
        }
    }
}
