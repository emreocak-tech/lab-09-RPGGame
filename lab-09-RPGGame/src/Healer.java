// 2.c Healer Class
class Healer extends GameCharacter implements Supporter {
    private int healAmount; // Ek özellik (attribute)

    public Healer(String name, int level, int maxHealth, int healAmount) {
        super(name, level, maxHealth);
        this.healAmount = healAmount;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 5 + healAmount;
    }

    @Override
    public String getCharacterInfo() {
        return "Healer [" + getName() + "] Lvl:" + getLevel() + " HP:" + getHealth() + "/" + getMaxHealth() + " HealPower:" + healAmount + " Power:" + calculatePower();
    }

    @Override
    public int heal(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) return 0;

        int totalHeal = healAmount + getLevel();
        return target.restoreHealth(totalHeal);
    }
}
