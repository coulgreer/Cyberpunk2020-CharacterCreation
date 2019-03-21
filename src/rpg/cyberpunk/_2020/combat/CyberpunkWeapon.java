package rpg.cyberpunk._2020.combat;

import java.io.Serializable;
import java.util.Objects;

import rpg.general.combat.Weapon;
import rpg.util.Die;

public abstract class CyberpunkWeapon implements Weapon, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6029022101698122763L;

	public enum Availability {
		UNAVAILABLE("N/A"), //
		EXCELLENT("E"), //
		COMMON("C"), //
		POOR("P"), //
		RARE("R");

		private String abbreviation;

		Availability(String abbreviation) {
			this.abbreviation = abbreviation;
		}

		public String getAbbreviation() {
			return abbreviation;
		}
	}

	public enum Reliability {
		VERY_RELIABLE("VR"), STANDARD("ST"), UNRELIABLE("UR");

		private String abbreviation;

		Reliability(String abbreviation) {
			this.abbreviation = abbreviation;
		}

		public String getAbbreviation() {
			return abbreviation;
		}
	}

	public enum Concealability {
		POCKET("P"), //
		JACKET("J"), //
		LONG_COAT("L"), //
		CANNOT_HIDE("N");

		private String abbreviation;

		Concealability(String abbreviation) {
			this.abbreviation = abbreviation;
		}

		public String getAbbreviation() {
			return abbreviation;
		}
	}

	public static final String DEFAULT_WEAPON_TYPE = "None";
	public static final String WEAPON_TYPE_LIGHT_PISTOL = "Light Pistol";
	public static final String WEAPON_TYPE_MEDIUM_PISTOL = "Medium Pistol";
	public static final String WEAPON_TYPE_HEAVY_PISTOL = "Heavy Pistol";
	public static final String WEAPON_TYPE_VERY_HEAVY_PISTOL = "Very Heavy Pistol";
	public static final String WEAPON_TYPE_LIGHT_SUBMACHINEGUN = "Light Submachinegun";
	public static final String WEAPON_TYPE_MEDIUM_SUBMACHINEGUN = "Medium Submachinegun";
	public static final String WEAPON_TYPE_HEAVY_SUBMACHINEGUN = "Heavy Submachinegun";
	public static final String WEAPON_TYPE_RIFLE = "Rifle";
	public static final String WEAPON_TYPE_SHOTGUN = "Shotgun";
	public static final String WEAPON_TYPE_HEAVY_WEAPON = "Heavy Weapon";
	public static final String WEAPON_TYPE_MELEE_WEAPON = "Melee Weapon";
	public static final String WEAPON_TYPE_EXOTIC = "Exotic Weapon";
	public static final String WEAPON_TYPE_UNARMED = "Unarmed";

	private final Die hitChance;

	protected CyberpunkWeapon() {
		this.hitChance = new Die(1, 10);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof CyberpunkWeapon)) {
			return false;
		}

		CyberpunkWeapon weapon = (CyberpunkWeapon) o;
		return ((weapon.getName()).equals(getName())) && (weapon.getWeaponType() == getWeaponType())
				&& (weapon.getAmmunitionCount() == getAmmunitionCount())
				&& (weapon.getAmmunitionCapacity() == getAmmunitionCapacity());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getWeaponType(), getAmmunitionCount(), getAmmunitionCapacity());
	}

	@Override
	public String toString() {
		return getName() + ": << <" + hitChance + "+" + getHitScore() + "> <" + getDamageDice() + "+"
				+ getDamageModifier() + "> >>";
	}

	@Override
	public Die getHitDice() {
		return hitChance;
	}

	public abstract Concealability getConcealability();

	public abstract Availability getAvailability();

	public abstract Reliability getReliability();
}
