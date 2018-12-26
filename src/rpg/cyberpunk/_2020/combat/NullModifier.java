package rpg.cyberpunk._2020.combat;

import rpg.general.combat.Modifier;

public class NullModifier implements Modifier {
	private static NullModifier uniqueInstance;

	private NullModifier() {
		uniqueInstance = null;
	}

	public static NullModifier getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullModifier();
		}

		return uniqueInstance;
	}

	public String getType() {
		return Modifier.MISCELLANEOUS;
	}

	public String getBonus() {
		return "";
	}

}
