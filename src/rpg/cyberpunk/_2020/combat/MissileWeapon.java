package rpg.cyberpunk._2020.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rpg.cyberpunk._2020.combat.Ammunition.AmmoType;
import rpg.general.combat.CombatCalculator;
import rpg.general.combat.Combatant;
import rpg.general.combat.Modifier.ModifierType;
import rpg.general.combat.WeaponModifier;
import rpg.util.Probability;

public class MissileWeapon extends CyberpunkWeapon {
	private CombatCalculator calculator;
	private Magazine magazine;
	private List<String> acceptedModifierTypes;
	private Map<String, WeaponModifier> bonuses;

	private MissileWeapon(String weaponName, String description, String weaponType, String skillName,
			int weaponAccuracy, Concealability concealability, Availability availability, Probability damage,
			AmmoType ammoType, int numberOfShots, int rateOfFire, Reliability reliability, int range, double weight,
			int cost) {
		super(weaponName, description, weaponType, skillName, weaponAccuracy, concealability, availability, damage,
				ammoType, rateOfFire, reliability, range, cost, weight);
		this.magazine = new Magazine(ammoType, numberOfShots);
		initializeAcceptedModifierTypes();
		initializeStatBonuses();
		calculator = new StaticRangeCombatCalculator();
		calculator.setWeaponThenCalculate(this);
	}

	private void initializeAcceptedModifierTypes() {
		acceptedModifierTypes = new ArrayList<String>();
		acceptedModifierTypes.add(CyberpunkWeaponModifier.SIGHT);
		acceptedModifierTypes.add(CyberpunkWeaponModifier.HOLSTER);
		acceptedModifierTypes.add(CyberpunkWeaponModifier.UNDERBARREL);
	}

	private void initializeStatBonuses() {
		bonuses = new HashMap<String, WeaponModifier>();
		Iterator<String> iterator = acceptedModifierTypes.iterator();
		while (iterator.hasNext()) {
			bonuses.put(iterator.next(), NullWeaponModifier.getInstance());
		}
	}

	public static MissileWeapon createTypeBasedWeapon(String weaponName, String description, String weaponType,
			int weaponAccuracy, Concealability concealability, Availability availability, Probability damage,
			AmmoType ammoType, int numberOfShots, int rateOfFire, Reliability reliability, int range, double weight,
			int cost) {
		return new MissileWeapon(weaponName, description, weaponType, CyberpunkWeapon.parseSkillName(weaponType),
				weaponAccuracy, concealability, availability, damage, ammoType, numberOfShots, rateOfFire, reliability,
				range, weight, cost);
	}

	public static MissileWeapon createSkillBasedWeapon(String weaponName, String description, String skillName,
			int weaponAccuracy, Concealability concealability, Availability availability, Probability damage,
			AmmoType ammoType, int numberOfShots, int rateOfFire, Reliability reliability, int range, double weight,
			int cost) {
		return new MissileWeapon(weaponName, description, CyberpunkWeapon.EXOTIC, skillName, weaponAccuracy,
				concealability, availability, damage, ammoType, numberOfShots, rateOfFire, reliability, range, weight,
				cost);
	}

	public boolean fire(int shotsToFire) {
		boolean hasFired = false;
		for (int shotsFired = 0; shotsFired < shotsToFire; shotsFired++) {
			if (magazine.getAmmoCount() <= Magazine.EMPTY) {
				return hasFired;
			} else {
				magazine.spendAmmunition();
				hasFired = true;
			}
		}
		return hasFired;
	}

	public Magazine reload(Magazine newMagazine) {
		while (!newMagazine.isEmpty() && magazine.getAmmoCount() < magazine.getAmmoCapacity()) {
			if (magazine.storeAmmunition(newMagazine.getAmmunition())) {
				newMagazine.spendAmmunition();
			}
		}
		return newMagazine;
	}

	public int getAmmoCapacity() {
		return magazine.getAmmoCapacity();
	}

	public int getAmmoCount() {
		return magazine.getAmmoCount();
	}

	public void setCombatant(Combatant combatant) {
		calculator.setCombatantThenCalculate(combatant);
	}

	public int getHitScore() {
		int bonus = 0;
		Iterator<WeaponModifier> iterator = bonuses.values().iterator();
		while (iterator.hasNext()) {
			WeaponModifier modifier = iterator.next();
			bonus += modifier.getHitModifier();
		}

		return calculator.getHitScore() + bonus;
	}

	public int getDamageScore() {
		int bonus = 0;
		Iterator<WeaponModifier> iterator = bonuses.values().iterator();
		while (iterator.hasNext()) {
			WeaponModifier modifier = iterator.next();
			bonus += modifier.getDamageModifier();
		}

		return calculator.getDamageScore() + bonus;
	}

	public int getRangeScore() {
		int bonus = 0;
		Iterator<WeaponModifier> iterator = bonuses.values().iterator();
		while (iterator.hasNext()) {
			WeaponModifier modifier = iterator.next();
			bonus += modifier.getRangeModifier();
		}

		return calculator.getRangeScore() + bonus;
	}

	public double getSoftArmorMultiplier() {
		return magazine.getAmmunition().getSoftArmorMultiplier();
	}

	public double getHardArmorMultiplier() {
		return magazine.getAmmunition().getHardArmorMultiplier();
	}

	public double getDamageMultiplier() {
		return magazine.getAmmunition().getDamageMultiplier();
	}

	public WeaponModifier setModifier(WeaponModifier modifier) {
		CyberpunkWeaponModifier type = modifier.getType();
		if (acceptedModifierTypes.contains(type)) {
			modifier = bonuses.replace(type, modifier);
		}
		return modifier;
	}

	public String getBonuses() {
		String bonusString = "";
		Iterator<WeaponModifier> iterator = bonuses.values().iterator();
		while (iterator.hasNext()) {
			WeaponModifier modifier = iterator.next();
			bonusString += "[" + modifier.getName() + "]:\n";
			bonusString += modifier.getBonus() + "\n";
		}
		return bonusString;
	}
}
