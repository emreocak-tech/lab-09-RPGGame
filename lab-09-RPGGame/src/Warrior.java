class Warrior extends GameCharacter implements Attacker {
    private int armor;

    public Warrior(String name, int level, int maxHealth, int armor) {
        super(name, level, maxHealth);
        this.armor = armor;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 10 + armor * 2;
    }

    @Override
    public String getCharacterInfo() {
        return "Warrior [" + getName() + "] Lvl:" + getLevel() + " HP:" + getHealth() + "/" + getMaxHealth() + " Armor:" + armor + " Power:" + calculatePower();
    }

    @Override
    public int takeDamage(int damage) {
        // Gelen hasardan armor/2 düşülür, minimum 1 hasar alınır
        int damageReduction = armor / 2;
        int netDamage = Math.max(1, damage - damageReduction);
        return super.takeDamage(netDamage);
    }

    @Override
    public int attack(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) return 0;

        int damage = calculatePower() / 4;
        target.takeDamage(damage);
        return damage; // Verilen hasar miktarı
    }
}
