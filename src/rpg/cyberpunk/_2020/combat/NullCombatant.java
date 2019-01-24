package rpg.cyberpunk._2020.combat;

import java.util.Iterator;

import rpg.general.combat.Combatant;
import rpg.general.combat.Magazine;
import rpg.general.combat.Weapon;
import rpg.util.NullProbability;
import rpg.util.Probability;

public class NullCombatant extends Combatant {
	private static NullCombatant uniqueInstance;

	private NullCombatant() {
	}

	public static NullCombatant getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullCombatant();
		}

		return uniqueInstance;
	}

	public void arm(int slot, Weapon weapon) {
		// Do Nothing
	}

	public void disarm(int slot) {
		// Do Nothing
	}

	public Weapon getWeapon(int slot) {
		return NullCyberpunkWeapon.getInstance();
	}

	public int getHitModifier(Weapon weapon) {
		return 0;
	}

	public Probability getTotalHitChance(int slot) {
		return NullProbability.getInstance();
	}

	public int getDamageModifier(Weapon weapon) {
		return 0;
	}

	public Probability getTotalDamageChance(int slot) {
		return NullProbability.getInstance();
	}

	public int getRangeModifier(Weapon weapon) {
		return 0;
	}

	public int getRangeScore(int slot) {
		return 0;
	}

	public int getAmmoCount(int slot) {
		return 0;
	}

	public Magazine reload(int slot, Magazine magazine) {
		return magazine;
	}

	public Iterator<? extends Weapon> createIterator() {
		return null;
	}
}
