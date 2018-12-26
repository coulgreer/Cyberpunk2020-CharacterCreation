package rpg.cyberpunk._2020.combat;

import rpg.cyberpunk._2020.combat.Ammunition.AmmoType;
import rpg.cyberpunk._2020.stats.CyberpunkSkill;
import rpg.general.combat.Combatant;
import rpg.general.combat.WeaponModifier;
import rpg.util.Die;
import rpg.util.Probability;

public class NullCyberpunkWeapon extends CyberpunkWeapon {
	private static NullCyberpunkWeapon uniqueInstance;

	private NullCyberpunkWeapon() {
		super("", "", CyberpunkWeapon.NONE, CyberpunkSkill.NONE, 0, Concealability.CANNOT_HIDE,
				Availability.UNAVAILABLE, new Probability(new Die(0, 0), 0), AmmoType.NONE, 0, Reliability.UNRELIABLE,
				0, 0, 0.0);
	}

	public static NullCyberpunkWeapon getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullCyberpunkWeapon();
		}

		return uniqueInstance;
	}

	public void setCombatant(Combatant fighter) {
		// Do Nothing
	}

	public int getHitScore() {
		return 0;
	}

	public int getDamageScore() {
		return 0;
	}

	public int getRangeScore() {
		return 0;
	}

	public boolean fire(int shotsFired) {
		return false;
	}

	public Magazine reload(Magazine magazine) {
		return magazine;
	}

	public int getAmmoCapacity() {
		return 0;
	}

	public int getAmmoCount() {
		return 0;
	}

	public WeaponModifier setModifier(WeaponModifier modifier) {
		return modifier;
	}

	public String getBonuses() {
		return "";
	}
}
