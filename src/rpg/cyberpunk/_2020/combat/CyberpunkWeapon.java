package rpg.cyberpunk._2020.combat;

import java.util.Objects;

import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Weapon;
import rpg.util.Die;
import rpg.util.Probability;

public abstract class CyberpunkWeapon extends Weapon implements CyberpunkWeaponizable {
	public static final String NONE = "None";
	public static final String LIGHT_PISTOL = "Light Pistol";
	public static final String MEDIUM_PISTOL = "Medium Pistol";
	public static final String HEAVY_PISTOL = "Heavy Pistol";
	public static final String VERY_HEAVY_PISTOL = "Very Heavy Pistol";
	public static final String LIGHT_SUBMACHINEGUN = "Light Submachinegun";
	public static final String MEDIUM_SUBMACHINEGUN = "Medium Submachinegun";
	public static final String HEAVY_SUBMACHINEGUN = "Heavy Submachinegun";
	public static final String RIFLE = "Rifle";
	public static final String SHOTGUN = "Shotgun";
	public static final String HEAVY_WEAPON = "Heavy Weapon";
	public static final String EDGED_WEAPON = "Edged Weapon";
	public static final String BLUNT_WEAPON = "Blunt Weapon";
	public static final String EXOTIC = "Exotic Weapon";
	public static final String UNARMED = "Unarmed";

	private Die hitChance;
	private Concealability concealability;
	private Availability availability;
	private Die damageChance;
	private String ammoType;
	private Reliability reliability;

	protected CyberpunkWeapon(String weaponName, String description, String weaponType, String skillName,
			int weaponAccuracy, Concealability concealability, Availability availability, Probability damage,
			String ammoType, int rateOfFire, Reliability reliability, int rangeModifier, double cost, double weight) {
		super(weaponName, description, weaponType, skillName, weaponAccuracy, damage.getModifier(), rangeModifier,
				rateOfFire, cost, weight);

		this.hitChance = new Die(1, 10);
		this.concealability = concealability;
		this.availability = availability;
		this.damageChance = damage.getDice();
		this.ammoType = ammoType;
		this.reliability = reliability;
	}

	protected static String parseSkillName(String weaponType) {
		switch (weaponType) {
		case LIGHT_PISTOL:
		case MEDIUM_PISTOL:
		case HEAVY_PISTOL:
		case VERY_HEAVY_PISTOL:
			return CyberpunkSkill.HANDGUN;
		case LIGHT_SUBMACHINEGUN:
		case MEDIUM_SUBMACHINEGUN:
		case HEAVY_SUBMACHINEGUN:
			return CyberpunkSkill.SUBMACHINEGUN;
		case RIFLE:
		case SHOTGUN:
			return CyberpunkSkill.RIFLE;
		case HEAVY_WEAPON:
			return CyberpunkSkill.HEAVY_WEAPONS;
		case EDGED_WEAPON:
		case BLUNT_WEAPON:
			return CyberpunkSkill.MELEE;
		default:
			return CyberpunkSkill.NONE;
		}
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof CyberpunkWeapon)) {
			return false;
		}

		CyberpunkWeapon weapon = (CyberpunkWeapon) o;
		return ((weapon.getName()).equals(getName())) && (weapon.getWeaponType() == getWeaponType())
				&& (weapon.getAmmoCount() == getAmmoCount()) && (weapon.getAmmoCapacity() == getAmmoCapacity());
	}

	public int hashCode() {
		return Objects.hash(getName(), getWeaponType(), getAmmoCount(), getAmmoCapacity());
	}

	public String toString() {
		return getName() + ": << <" + hitChance + "+" + getHitScore() + "> <" + getDamageDice() + "+"
				+ getDamageModifier() + "> >>";
	}

	public Die getHitDice() {
		return hitChance;
	}

	public Concealability getConcealability() {
		return concealability;
	}

	public Availability getAvailability() {
		return availability;
	}

	public Die getDamageDice() {
		return damageChance;
	}

	public String getAmmoType() {
		return ammoType;
	}

	public Reliability getReliability() {
		return reliability;
	}

	public double getSoftArmorMultiplier() {
		switch (getWeaponType()) {
		case "Edged Weapon":
			return 0.5;
		default:
			return 1.0;
		}
	}

	public double getHardArmorMultiplier() {
		switch (getWeaponType()) {
		default:
			return 1.0;
		}
	}

	public double getDamageMultiplier() {
		switch (getWeaponType()) {
		default:
			return 1.0;
		}
	}
}
