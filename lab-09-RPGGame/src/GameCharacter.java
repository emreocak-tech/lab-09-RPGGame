// 1) Abstract Class GameCharacter
abstract class GameCharacter {
    private String name;
    private int level;
    private int health;
    private int maxHealth;

    public GameCharacter(String name, int level, int maxHealth) {
        this.name = name;

        // Level validasyonu (1-100)
        if (level < 1 || level > 100) {
            System.out.println("Warning: Invalid level for " + name + ". Setting to 1.");
            this.level = 1;
        } else {
            this.level = level;
        }

        // MaxHealth validasyonu (0'dan büyük olmalı)
        if (maxHealth <= 0) {
            System.out.println("Warning: Invalid maxHealth for " + name + ". Setting to 100.");
            this.maxHealth = 100;
        } else {
            this.maxHealth = maxHealth;
        }

        // Yeni karakter tam sağlıkla başlar
        this.health = this.maxHealth;
    }

    // Getter ve Setter metotları
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    protected void setHealth(int health) { this.health = health; }

    // a. isAlive
    public boolean isAlive() {
        return health > 0;
    }

    // b. takeDamage
    public int takeDamage(int damage) {
        if (!isAlive()) return 0;

        int actualDamage = Math.min(health, damage);
        health -= actualDamage;
        return actualDamage;
    }

    // c. restoreHealth
    public int restoreHealth(int amount) {
        if (!isAlive()) return 0; // Yenilmiş karakter iyileştirilemez

        int space = maxHealth - health;
        int actualRestored = Math.min(space, amount);
        health += actualRestored;
        return actualRestored;
    }

    // Abstract metotlar
    public abstract String getCharacterInfo();
    public abstract int calculatePower();
}
