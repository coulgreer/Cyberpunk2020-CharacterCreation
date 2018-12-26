package rpg.cyberpunk._2020.combat;

import rpg.general.combat.CombatCalculator;
import rpg.general.combat.Combatant;
import rpg.general.combat.Weapon;

public class DynamicRangeCombatCalculator extends CombatCalculator {
	private Combatant combatant;
	private Weapon weapon;
	private int hitScore;
	private int damageScore;
	private int rangeScore;

	public DynamicRangeCombatCalculator() {
		combatant = NullCombatant.getInstance();
		weapon = NullCyberpunkWeapon.getInstance();
		hitScore = 0;
		damageScore = 0;
		rangeScore = 0;
	}

	protected void calculate() {
		hitScore = combatant.getHitModifier(weapon) + weapon.getHitModifier();
		damageScore = combatant.getDamageModifier(weapon) + weapon.getDamageModifier();
		rangeScore = (int) ((10 * combatant.getRangeModifier(weapon)) - (10 * weapon.getRangeModifier()));
	}

	public int getHitScore() {
		return hitScore;
	}

	public int getDamageScore() {
		return damageScore;
	}

	public int getRangeScore() {
		return rangeScore;
	}

	protected void setCombatant(Combatant combatant) {
		this.combatant = combatant;
	}

	protected void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

}
