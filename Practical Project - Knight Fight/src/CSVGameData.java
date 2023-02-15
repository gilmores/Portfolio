import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVGameData extends GameData {

    public CSVGameData(String gamedata, String saveData) {
        loadGameData(gamedata);
        loadSaveData(saveData);
    }
    // This method is straight from the javadoc
    void loadSaveData(String saveData) {
        int counter = 0;
        Scanner file = readFile(saveData);
        if (file == null) return;
        while (file.hasNextLine()) {
            Scanner line = new Scanner(file.nextLine());
            line.useDelimiter(",");
            Knight kt = new Knight(
                ++counter,
                line.next().trim(),
                line.nextInt(), 
                line.nextInt(), 
                line.nextInt(), 
                DiceType.valueOf(line.next()), 
                line.nextInt());
            knights.add(kt);
        }
    }

    void loadGameData(String gamedata) {
        Scanner file = readFile(gamedata);
        if (file == null) return;
        while (file.hasNextLine()) {
            Scanner line = new Scanner(file.nextLine());
            line.useDelimiter(",");
            String type = line.next();
            String name = line.next().trim();
            int hp = line.nextInt();
            int armor = line.nextInt();
            int hitModifier = line.nextInt();
            String damageDieString = line.next();
            DiceType damageDie;
            if (damageDieString.equals("-")) {
                damageDie = null;
            }
            else {
                damageDie = DiceType.valueOf(damageDieString);
            }
            if (type.equals("MOB")) {
                monsters.add(new MOB(name, hp, armor, hitModifier, damageDie));
            }
            if (type.equals("FORTUNE")) {
                fortunes.add(new Fortune(name, hp, armor, hitModifier, damageDie));
            }
        }
    }

    private Scanner readFile(String fileName) {
        try {
            Scanner data = new Scanner(new File(fileName));
            return data;
        }
        catch(Exception e) {
            System.out.println("File not found! " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(String filename) {
        PrintWriter printer;
        try {
            File saveFile = new File(filename);
            printer = new PrintWriter(saveFile);
        }
        catch(Exception e) {
            System.out.println("File not found! " + e.getMessage());
            return;
        }
        for(Knight kt : knights) {
            printer.println(kt.toCSV());
        }
        printer.close();
    }
}
