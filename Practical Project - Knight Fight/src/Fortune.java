public class Fortune implements Attributes {

    private final int armor;
    private final DiceType dtype;
    private final int hitModifier;
    private final int hpBonus;
    private final String name;

    public Fortune(String name, int hpBonus, int armor, int hitModifier) {
        this(name, hpBonus, armor, hitModifier, null);
    }
    public Fortune(String name, int hpBonus, int armor, int hitModifier, DiceType type) {
        this.name = name;
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.hitModifier = hitModifier;
        dtype = type;
    }

    // Getters
    public int getArmor() {return armor;}
    public int getMaxHP() {return hpBonus;}
    public DiceType getDamageDie() {return dtype;}
    public int getHitModifier() {return hitModifier;}
    public String getName() {return name;}
    // Methods
    public String toString() {
        return "+======================+\n" +
        String.format("|%-22s|%n", getName()) +
        String.format("|HP Bonus: %+12d|%n", getMaxHP()) +
        String.format("|AC Bonus: %+12d|%n", getArmor()) +
        String.format("|Hit Bonus: %+11d|%n", getHitModifier()) +
        String.format("|Damage Adj: %10s|%n", (getDamageDie() == null) ? "-" : getDamageDie()) + // Ternary Operator
        "+======================+";
    }
    public static void main(String[] args) {
        // Tests
        System.out.println(new Fortune("Impostor", 5, 3, 6));
    }
}
