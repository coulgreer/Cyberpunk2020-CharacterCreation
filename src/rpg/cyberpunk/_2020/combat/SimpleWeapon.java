package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.combat.Ammunition.AmmoType;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.CombatCalculator;
import rpg.general.combat.Combatant;
import rpg.general.combat.Magazine;
import rpg.general.combat.WeaponModifier;
import rpg.util.Probability;

public class SimpleWeapon extends CyberpunkWeapon {
	public static final AmmoType DEFAULT_AMMO_TYPE = AmmoType.NONE;
	public static final int RATE_OF_FIRE = 1;

	private CombatCalculator calculator;

	private SimpleWeapon(String weaponName, String description, String weaponType, String skillName,
			int weaponAccuracy, Concealability concealability, Availability availability, Probability damage,
			Reliability reliability, int range, int cost, double weight) {
		super(weaponName, description, weaponType, skillName, weaponAccuracy, concealability, availability, damage,
				DEFAULT_AMMO_TYPE, RATE_OF_FIRE, reliability, range, cost, weight);

		calculator = new StaticRangeCombatCalculator();
		calculator.setWeaponThenCalculate(this);
	}

	public static SimpleWeapon createTypeBasedWeapon(String weaponName, String description, String weaponType,
			int weaponAccuracy, Concealability concealability, Availability availability, Probability damage,
			Reliability reliability, int range, double weight, int cost) {
		return new SimpleWeapon(weaponName, description, weaponType, CyberpunkSkill.MELEE, weaponAccuracy,
				concealability, availability, damage, reliability, range, cost, weight);
	}

	public static SimpleWeapon createSkillBasedWeapon(String weaponName, String description, String skillName,
			int weaponAccuracy, Concealability concealability, Availability availability, Probability damage,
			Reliability reliability, int range, double weight, int cost) {
		return new SimpleWeapon(weaponName, description, CyberpunkWeapon.BLUNT_WEAPON, skillName,
				weaponAccuracy, concealability, availability, damage, reliability, range, cost, weight);
	}

	public int getHitScore() {
		return calculator.getHitScore();
	}

	public int getDamageScore() {
		return calculator.getDamageScore();
	}

	public void setCombatant(Combatant fighter) {
		calculator.setCombatantThenCalculate(fighter);
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
