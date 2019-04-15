package rpg.cyberpunk._2020.combat;

import java.util.ArrayList;
import java.util.List;

import rpg.general.combat.Ammunition;
import rpg.general.combat.AmmunitionContainer;
import rpg.general.combat.Combatant;
import rpg.general.combat.Weapon;
import rpg.util.Die;
import rpg.util.Probability;

public class NullCombatant implements Combatant {
	private static final Probability PROBABILITY = new Probability(
			new Die(Die.MIN_NUMBER_OF_DICE, Die.MIN_NUMBER_OF_FACES), Probability.DEFAULT_MODIFIER);

	private static NullCombatant uniqueInstance;

	private NullCombatant() {
	}

	public static NullCombatant getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullCombatant();
		}

		return uniqueInstance;
	}

	@Override
	public void arm(int slot, Weapon weapon) {
		// Do Nothing
	}

	@Override
	public void disarm(int slot) {
		// Do Nothing
	}

	@Override
	public void attack(int slot, int shotsFired) {
		// Do Nothing
	}

	@Override
	public Weapon getWeapon(int slot) {
		return NullCyberpunkWeapon.getInstance();
	}

	@Override
	public int getHitModifier(Weapon weapon) {
		return 0;
	}

	@Override
	public Probability getTotalHitChance(int slot) {
		return PROBABILITY;
	}

	@Override
	public int getDamageModifier(Weapon weapon) {
		return 0;
	}

	@Override
	public Probability getTotalDamageChance(int slot) {
		return PROBABILITY;
	}

	@Override
	public int getRangeModifier(boolean isThrown) {
		return 0;
	}

	@Override
	public int getRangeScore(int slot) {
		return 0;
	}

	@Override
	public int getAmmoCount(int slot) {
		return 0;
	}

	@Override
	public List<Ammunition> reload(int slot, AmmunitionContainer newStorageDevice) {
		ArrayList<Ammunition> remainingAmmunition = new ArrayList<>();

		while (!newStorageDevice.isEmpty()) {
			remainingAmmunition.add(newStorageDevice.withdrawAmmunition());
		}

		return remainingAmmunition;
	}
}
