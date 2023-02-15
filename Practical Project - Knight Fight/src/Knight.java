public class Knight extends MOB {

    private Fortune activeFortune = null;
    protected final int id;
    protected int xp;

    public Knight(int id, String name, int hp, int armor, int hitModifier, DiceType damageDie, int xp) {
        super(name, hp, armor, hitModifier, damageDie);
        this.id = id;
        this.xp = xp;
    }
    // Getters (With tons of ternary operators for simplicity)
    public int getArmor() {
        return (activeFortune != null) ? super.getArmor() + activeFortune.getArmor() : super.getArmor();
    }
    public int getMaxHP() {
        return (activeFortune != null) ? super.getMaxHP() + activeFortune.getMaxHP() : super.getMaxHP();
    }
    public DiceType getDamageDie() {
        return (activeFortune != null) ? activeFortune.getDamageDie() : super.getDamageDie();
    }
    public int getHitModifier() {
        return (activeFortune != null) ? super.getHitModifier() + activeFortune.getHitModifier() : super.getHitModifier();
    }
    public int getXP() {return xp;}
    public Fortune getActiveFortune() {return activeFortune;}
    public Integer getId() {return id;}
    // Setters
    public void setActiveFortune(Fortune activeFortune) {
        this.activeFortune = activeFortune;
    }
    // Methods
    public void addXP(int xp) {
        this.xp += xp;
    }
    public String toString() {
        return "+============================+\n" +
        String.format("| %-27s|%n", getName()) +
        String.format("| id: %-23d|%n", getId()) +
        "|                            |\n" +
        String.format("| Health: %-6d XP: %-7d |%n", getHP(), getXP()) +
        String.format("|  Power: %-6s Armor: %-4d |%n", getDamageDie(), getArmor()) +
        "|                            |\n" +
        "+============================+\n";
    }
    public String toCSV() {
        return getName() + "," + getMaxHP() + "," + getArmor() + "," + getHitModifier() + "," + getDamageDie() + "," + getXP();
    }
    public static void main(String[] args) {
        // Tests
        System.out.println(new Knight(3, "Zach le Fay", 68, 14, 2, DiceType.D8, 0));
    }
}
