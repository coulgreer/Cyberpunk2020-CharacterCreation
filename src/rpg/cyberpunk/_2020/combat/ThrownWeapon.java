package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.combat.Ammunition.AmmoType;
import rpg.general.combat.CombatCalculator;
import rpg.general.combat.Combatant;
import rpg.general.combat.WeaponModifier;
import rpg.util.Probability;

public class ThrownWeapon extends CyberpunkWeapon {
	public static final int RATE_OF_FIRE = 1;

	private CombatCalculator calculator;

	public ThrownWeapon(String weaponName, String description, String weaponType, int weaponAccuracy,
			Concealability concealability, Availability availability, Probability damage, int rateOfFire,
			Reliability reliability, double weight, int cost) {
		super(weaponName, description, weaponType, CyberpunkWeapon.parseSkillName(weaponType), weaponAccuracy,
				concealability, availability, damage, AmmoType.NONE, RATE_OF_FIRE, reliability,
				(int) Math.round(weight), cost, weight);

		calculator = new DynamicRangeCombatCalculator();
		calculator.setWeaponThenCalculate(this);
	}

	public boolean fire(int shotsToFire) {
		return true;
	}

	public Magazine reload(Magazine newMagazine) {
		return newMagazine;
	}

	public void setCombatant(Combatant combatant) {
		calculator.setCombatantThenCalculate(combatant);
	}

	public int getHitScore() {
		return calculator.getHitScore();
	}

	public int getDamageScore() {
		return calculator.getDamageScore();
	}

	public int getRangeScore() {
		return calculator.getRangeScore();
	}

	public int getAmmoCapacity() {
		return Magazine.EMPTY;
	}

	public int getAmmoCount() {
		return Magazine.EMPTY;
	}

	public WeaponModifier setModifier(WeaponModifier modifier) {
		return modifier;
	}

	public String getBonuses() {
		return "";
	}
}
