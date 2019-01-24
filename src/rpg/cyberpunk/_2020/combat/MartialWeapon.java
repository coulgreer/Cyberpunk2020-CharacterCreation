package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.combat.Ammunition.AmmoType;
import rpg.general.combat.CombatCalculator;
import rpg.general.combat.Combatant;
import rpg.general.combat.Magazine;
import rpg.general.combat.WeaponModifier;
import rpg.util.Probability;

public class MartialWeapon extends CyberpunkWeapon {
	public static final AmmoType DEFAULT_AMMO_TYPE = AmmoType.NONE;
	public static final int RATE_OF_FIRE = 1;
	public static final int DEFAULT_RANGE = 1;
	public static final double DEFAULT_WEIGHT = 0.0;
	public static final int DEFAULT_COST = 0;

	private CombatCalculator calculator;

	public MartialWeapon(String weaponName, String description, String skillName, int weaponAccuracy,
			Concealability concealability, Availability availability, Probability damage, Reliability reliability) {
		super(weaponName, description, CyberpunkWeapon.UNARMED, skillName, weaponAccuracy, concealability, availability,
				damage, DEFAULT_AMMO_TYPE, RATE_OF_FIRE, reliability, DEFAULT_RANGE, DEFAULT_COST, DEFAULT_WEIGHT);

		calculator = new StaticRangeCombatCalculator();
		calculator.setWeaponThenCalculate(this);
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

	public boolean fire(int shotsFired) {
		return true;
	}

	public Magazine reload(Magazine magazine) {
		return magazine;
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
