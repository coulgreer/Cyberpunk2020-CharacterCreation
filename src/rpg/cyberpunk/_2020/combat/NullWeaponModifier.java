package rpg.cyberpunk._2020.combat;

import rpg.general.combat.Modifier;
import rpg.general.combat.WeaponModifier;
import rpg.util.NullProbability;

public class NullWeaponModifier extends WeaponModifier {
	public static NullWeaponModifier uniqueInstance;

	private NullWeaponModifier() {
		super("", "", Modifier.MISCELLANEOUS, NullProbability.getInstance(), NullProbability.getInstance(), 0, null,
				0.0, 0.0);
	}

	public static NullWeaponModifier getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullWeaponModifier();
		}
		return uniqueInstance;
	}

	public String getBonus() {
		return "";
	}
}
