package rpg.general.combat;

public abstract class CombatCalculator {
	public final void setWeaponThenCalculate(Weapon weapon) {
		setWeapon(weapon);
		calculate();
	}

	public final void setCombatantThenCalculate(Combatant combatant) {
		setCombatant(combatant);
		calculate();
	}

	protected abstract void calculate();

	public abstract int getHitScore();

	public abstract int getDamageScore();

	public abstract int getRangeScore();

	protected abstract void setCombatant(Combatant combatant);

	protected abstract void setWeapon(Weapon weapon);
}
