import java.util.Random;

public class DiceSet {
    private Random rnd = new Random();

    public int roll(DiceType diceType) {
        int myVal = 0;
        switch(diceType) {
            case D4:
                myVal = rnd.nextInt(4) + 1;
            break;
            case D6:
                myVal = rnd.nextInt(6) + 1;
            break;
            case D8:
                myVal = rnd.nextInt(8) + 1;
            break;
            case D10:
                myVal = rnd.nextInt(10) + 1;
            break;
            case D12:
                myVal = rnd.nextInt(12) + 1;
            break;
            case D20:
                myVal = rnd.nextInt(20) + 1;
            break;
        }

        return myVal;
    }
    public static void main(String[] args) {
        DiceSet dice = new DiceSet();
        System.out.println("Dice Tests:");
        System.out.println("Testing D4: " + dice.roll(DiceType.D4));
        System.out.println("Testing D6: " + dice.roll(DiceType.D6));
        System.out.println("Testing D8: " + dice.roll(DiceType.D8));
        System.out.println("Testing D10: " + dice.roll(DiceType.D10));
        System.out.println("Testing D12: " + dice.roll(DiceType.D12));
        System.out.println("Testing D20: " + dice.roll(DiceType.D20));
    }
}
