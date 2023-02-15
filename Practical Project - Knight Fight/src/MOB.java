public class MOB implements Attributes {

    protected int armor;
    protected int damage;
    protected DiceType damageDie;
    protected int hitModifier;
    protected int maxHP;
    private final String name;

    public MOB(String name, int hp, int armor, int hitModifier, DiceType damageDie) {
        this.name = name;
        maxHP = hp;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.damageDie = damageDie;
    }
    // Getters
    public int getArmor() {return armor;}
    public int getMaxHP() {return maxHP;}
    public DiceType getDamageDie() {return damageDie;}
    public int getHitModifier() {return hitModifier;}
    public int getDamage() {return damage;}
    public String getName() {return name;}
    public int getHP() {
        return maxHP - damage;
    }
    // Methods
    public void addDamage(int damage) {
        this.damage += damage;
    }
    public void resetDamage() {
        damage = 0;
    }
    public String toString() {
        return "+============================+\n" +
                String.format("| %-27s|%n", getName()) +
                "|                            |\n" +
                String.format("|         Health: %-10d |%n", getHP())  +
                String.format("|  Power: %-6s  Armor: %-4d|%n", getDamageDie().toString(), getArmor()) +
                "|                            |\n" +
                "+============================+";
    }
    public MOB copy() {
        return new MOB(name, maxHP, armor, hitModifier, damageDie);
    }
}
