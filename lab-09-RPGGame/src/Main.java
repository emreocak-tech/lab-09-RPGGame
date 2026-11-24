// 5) Main Test Program
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Creating Characters ===");

        // Test: Geçersiz Level (Warning vermeli)
        Warrior gecerliWarrior = new Warrior("Aragorn", 10, 100, 20);
        Mage gecerliMage = new Mage("Gandalf", 12, 80, 100);
        Healer gecerliHealer = new Healer("Elrond", 8, 90, 15);

        // Test: Geçersiz level (Level 150 girildi, 1'e düşürecek)
        Warrior gecersizWarrior = new Warrior("Hatali", 150, 100, 10);

        Team team = new Team();

        System.out.println("\n=== Building Team ===");
        team.addCharacter(gecerliWarrior);
        team.addCharacter(gecerliMage);
        team.addCharacter(gecerliHealer);

        // Test: Aynı isimde karakter ekleme
        team.addCharacter(new Mage("Gandalf", 5, 50, 50));

        // Test: Karakter silme ve arama
        team.removeCharacter("Hatali");
        GameCharacter found = team.findByName("Aragorn");
        if (found != null) System.out.println("Search successful: Found " + found.getName());

        team.displayTeam();

        // Düşman oluşturma
        Warrior golem = new Warrior("Golem", 20, 300, 50);
        System.out.println("\n=== Enemy Appears: " + golem.getName() + " ===");

        // Simülasyon: En az 2 tur
        for (int round = 1; round <= 2; round++) {
            System.out.println("\n======= ROUND " + round + " =======");

            // 1. Takım saldırıyor
            team.attackAll(golem);

            // Eğer düşman öldüyse döngüden çık
            if (!golem.isAlive()) {
                System.out.println(golem.getName() + " is defeated!");
                break;
            }

            // 2. Düşman takıma saldırıyor (Rastgele birine)
            System.out.println("\n--- " + golem.getName() + " attacks back! ---");
            GameCharacter target = team.findByName("Gandalf"); // Örnek olarak Mage'e saldırsın
            if (target != null && target.isAlive()) {
                int dmg = golem.attack(target);
                System.out.println(golem.getName() + " hits " + target.getName() + " for " + dmg + " damage.");
            } else {
                // Hedef öldüyse başka hedef seç (Basitlik için ilk yaşayanı bulalım)
                for (GameCharacter c : new GameCharacter[]{gecerliWarrior, gecerliMage, gecerliHealer}) {
                    if (c.isAlive()) {
                        golem.attack(c);
                        break;
                    }
                }
            }

            // 3. Takım kendini iyileştiriyor
            team.healTeam();
        }

        System.out.println("\n=== Final Status ===");
        team.displayTeam();
        System.out.println(golem.getCharacterInfo());
    }
}
