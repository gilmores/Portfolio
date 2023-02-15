import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements GameView {
    private final Scanner in;

    public ConsoleView() {
        // This scanner may actually be defined in the calling method, double check and
        // see if it needs to be passed in instead
        in = new Scanner(System.in);
    }

    @Override
    public boolean checkContinue() {
        System.out.println("Would you like to continue on your quest (y/n)?");
        char decision = in.nextLine().toLowerCase().charAt(0);
        if (decision == 'y') return true;
        return false;
    }

    @Override
    public String displayMainMenu() {
        System.out.println("What would you like to do?");
        return in.nextLine().toLowerCase().trim();
    }

    @Override
    public void endGame() {
        System.out.println("Thanks for playing and goodbye!");
    }

    @Override
    public void knightNotFound() {
        System.out.println("    Knight not found!");
    }

    @Override
    public void listKnights(List<Knight> knights) {
        if (knights.size() > 0 && knights != null) {
            for (Knight kt : knights) {
                System.out.println(kt.getId() + ": " + kt.getName());
            }
        }
        else System.out.println("No knights to list");
    }

    @Override
    public void printBattleText(List<MOB> monsters, List<Knight> activeKnights) {
        System.out.println("Our heroes come across the following monsters. Prepare for battle!");
        System.out.println("Knights                    Foes");
        if (activeKnights.size() == monsters.size()) {
            for (int i = 0; i < activeKnights.size(); i++) {
                System.out.printf("%-27s%s%n", activeKnights.get(i).getName(), monsters.get(i).getName());
            }
        }
        // This entire if statement can be omitted if the monster opposing the knight leaves as soon as
        // the knight is dead.
        if (activeKnights.size() < monsters.size()) {
            int i;
            for (i = 0; i < activeKnights.size(); i++) {
                System.out.printf("%-27s%s%n", activeKnights.get(i).getName(), monsters.get(i).getName());
            }
            for (int j = i; j < monsters.size(); j++) {
                System.out.printf("%31s%n", monsters.get(j).getName());
            }
        }
        if (activeKnights.size() > monsters.size()) {
            int i;
            for (i = 0; i < monsters.size(); i++) {
                System.out.printf("%-27s%s%n", activeKnights.get(i).getName(), monsters.get(i).getName());
            }
            for (int j = i; j < activeKnights.size(); j++) {
                System.out.printf("%s%n", activeKnights.get(j).getName());
            }
        }
    }

    @Override
    public void printBattleText(MOB dead) {
        System.out.println(dead.getName() + " was defeated!");
    }

    @Override
    public void printDefeated() {
        System.out.println("All active knights have been defeated!\n");
    }

    @Override
    public void printFortunes(List<Knight> activeKnights) {
        System.out.println("For this quest, our knights drew the following fortunes!");
        for (Knight kt : activeKnights) {
            System.out.println(kt.getName() + " drew");
            System.out.println(kt.getActiveFortune());
        }
    }

    @Override
    public void printHelp() {
        System.out.println("Unsure what to do, here are some options:");
        System.out.println("    ls or list all  - listing the knights");
        System.out.println("    list active  - list the active knights knights only");
        System.out.println("    show name or id - show the knight details card");
        System.out.println("    set active name or id - set knight as active (note: only 4 knights can be active)");
        System.out.println("    remove active name or id - remove a knight from active status (heals knight)");
        System.out.println("    explore or adventure or quest - find random monsters to fight");
        System.out.println("    save filename - save the game to the file name (default: saveData.csv)");
        System.out.println("    exit or goodbye - to leave the game\n");

        System.out.println("Game rules: You can have four active knights. As long as they are active, they won't heal,");
        System.out.println("but they can gain XP by going on adventures.");
        System.out.println("When you make a knight inactive, they will heal. How many monsters can you defeat ");
        System.out.println("before, you have to heal?");
    }

    @Override
    public void setActiveFailed() {
        System.out.println("    Unable to set active knight. Only four can be active at a time.\n");
    }

    @Override
    public void showKnight(Knight knight) {
        System.out.println(knight.toString() + "\n"); // additional \n may be unnecessary.
    }

    @Override
    public void splashScreen() {
        System.out.println("Welcome to Knight Fight!");
        System.out.println("This iteration is brought to you by Spencer G.");
    }
    
    public static void main(String[] args) {
        ConsoleView cv = new ConsoleView();
        List<Knight> activeKnights = new ArrayList<>();
        activeKnights.add(new Knight(3, "Zach le Fay", 68, 14, 2, DiceType.D8, 0));
        activeKnights.add(new Knight(4, "Sapper", 68, 14, 2, DiceType.D8, 0));
        activeKnights.add(new Knight(5, "Homie", 68, 14, 2, DiceType.D8, 0));
        List<MOB> monsters = new ArrayList<>();
        monsters.add(new MOB("Yo' Mama", 15, 12, 2, DiceType.D6));
        monsters.add(new MOB("Yeti", 16, 12, 2, DiceType.D6));
        cv.printBattleText(monsters, activeKnights);
    }

}
