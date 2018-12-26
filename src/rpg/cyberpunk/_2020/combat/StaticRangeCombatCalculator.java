package rpg.cyberpunk._2020.combat;

import rpg.general.combat.CombatCalculator;
import rpg.general.combat.Combatant;
import rpg.general.combat.Weapon;

public class StaticRangeCombatCalculator extends CombatCalculator {
	private Combatant combatant;
	private Weapon weapon;
	private int hitScore;
	private int damageScore;

	public StaticRangeCombatCalculator() {
		combatant = NullCombatant.getInstance();
		weapon = NullCyberpunkWeapon.getInstance();
		hitScore = 0;
		damageScore = 0;
	}

	protected void calculate() {
		hitScore = combatant.getHitModifier(weapon) + weapon.getHitModifier();
		damageScore = combatant.getDamageModifier(weapon) + weapon.getDamageModifier();
	}

	public int getHitScore() {
		return hitScore;
	}

	public int getDamageScore() {
		return damageScore;
	}

	public int getRangeScore() {
		return weapon.getRangeModifier();
	}

	public void setCombatant(Combatant combatant) {
		this.combatant = combatant;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}
