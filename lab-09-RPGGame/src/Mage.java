// 2.b Mage Class
class Mage extends GameCharacter implements Attacker, Supporter {
    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        this.mana = mana;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 8 + mana;
    }

    @Override
    public String getCharacterInfo() {
        return "Mage [" + getName() + "] Lvl:" + getLevel() + " HP:" + getHealth() + "/" + getMaxHealth() + " Mana:" + mana + " Power:" + calculatePower();
    }

    @Override
    public int attack(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) return 0;

        if (mana < 20) {
            System.out.println(getName() + " does not have enough mana to attack!");
            return 0;
        }

        // Hasar hesaplanmadan önce güç hesaplanır
        int damage = calculatePower() / 3;
        mana -= 20;

        target.takeDamage(damage);
        return damage;
    }

    @Override
    public int heal(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) return 0;

        if (mana < 15) {
            System.out.println(getName() + " does not have enough mana to heal!");
            return 0;
        }

        mana -= 15;
        int healAmount = getLevel() * 2;
        return target.restoreHealth(healAmount);
    }
}
