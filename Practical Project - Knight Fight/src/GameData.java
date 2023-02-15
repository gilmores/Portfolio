import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GameData {
    protected final List<Knight> activeKnights = new ArrayList<>();
    protected final List<Fortune> fortunes = new ArrayList<>();
    protected final List<Knight> knights = new ArrayList<>();
    private static final int MAX_ACTIVE = 4;
    protected final List<MOB> monsters = new ArrayList<>();
    private static final Random random = new Random();

    public List<Knight> getKnights(){ return knights;}
    public List<Knight> getActiveKnights(){ return activeKnights;}
    public Knight getActive(String nameOrId) { return findKnight(nameOrId, activeKnights);}
    public Knight getKnight(String nameOrId) { return findKnight(nameOrId, knights);}
    protected Knight findKnight(String nameOrId, List<Knight> list) {
        for (Knight kt : list) {
            if (nameOrId.equals(kt.getId().toString()) || kt.getName().toLowerCase().contains(nameOrId.toLowerCase())) {
                return kt;
            }
        }
        return null;
    }
    public boolean setActive(Knight kt) {
        if (activeKnights.size() < MAX_ACTIVE) {
            activeKnights.add(kt);
            return true;
        }
        return false;
    }
    public void removeActive(Knight kt) {
        if (activeKnights.remove(kt)) {
            kt.resetDamage();
        }
    }
    public Fortune getRandomFortune() { return fortunes.get(random.nextInt(fortunes.size()));}
    public List<MOB> getRandomMonsters(int number) {
        List<MOB> randMonsters = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            randMonsters.add(monsters.get(random.nextInt(monsters.size())).copy());
        }
        return randMonsters;
    }
    public abstract void save(String filename);
}
