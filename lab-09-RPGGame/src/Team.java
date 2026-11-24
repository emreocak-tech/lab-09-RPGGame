// 4) Team Class
import java.util.ArrayList;
class Team {
    private ArrayList<GameCharacter> characters;

    public Team() {
        characters = new ArrayList<>();
    }

    // a. addCharacter
    public void addCharacter(GameCharacter character) {
        for (GameCharacter c : characters) {
            if (c.getName().equalsIgnoreCase(character.getName())) {
                System.out.println("Cannot add " + character.getName() + ": A character with this name already exists in the team.");
                return;
            }
        }
        characters.add(character);
        System.out.println(character.getName() + " added to the team.");
    }

    // b. removeCharacter and findByName
    public void removeCharacter(String name) {
        GameCharacter toRemove = findByName(name);
        if (toRemove != null) {
            characters.remove(toRemove);
            System.out.println(name + " removed from the team.");
        } else {
            System.out.println("Character " + name + " not found.");
        }
    }

    public GameCharacter findByName(String name) {
        for (GameCharacter c : characters) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    // c. attackAll
    public void attackAll(GameCharacter target) {
        if (target == null || !target.isAlive()) return;

        System.out.println("\n--- Team attacks " + target.getName() + " ---");
        for (GameCharacter c : characters) {
            if (c.isAlive() && c instanceof Attacker) {
                int dmg = ((Attacker) c).attack(target);
                System.out.println(c.getName() + " attacks for " + dmg + " damage. Target HP: " + target.getHealth());
            }
        }
    }

    // d. healTeam
    public void healTeam() {
        System.out.println("\n--- Team Healing Phase ---");
        // En düşük can oranına sahip hayatta olan üyeyi bul
        GameCharacter lowestHealthMember = null;
        double lowestRatio = 2.0;

        for (GameCharacter c : characters) {
            if (c.isAlive()) {
                double ratio = (double) c.getHealth() / c.getMaxHealth();
                if (ratio < lowestRatio) {
                    lowestRatio = ratio;
                    lowestHealthMember = c;
                }
            }
        }

        if (lowestHealthMember != null) {
            System.out.println("Lowest health member is " + lowestHealthMember.getName());
            for (GameCharacter c : characters) {
                if (c.isAlive() && c instanceof Supporter) {
                    int healed = ((Supporter) c).heal(lowestHealthMember);
                    System.out.println(c.getName() + " heals " + lowestHealthMember.getName() + " for " + healed + " HP.");
                }
            }
        } else {
            System.out.println("No alive member to heal.");
        }
    }

    // e. getTotalPower and displayTeam
    public int getTotalPower() {
        int total = 0;
        for (GameCharacter c : characters) {
            if (c.isAlive()) {
                total += c.calculatePower();
            }
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("\n--- Team Roster ---");
        for (GameCharacter c : characters) {
            System.out.println(c.getCharacterInfo());
        }
        System.out.println("Total Team Power: " + getTotalPower());
    }
}
