package rpg.general.combat;

import rpg.util.Probability;

public abstract class Combatant {
	public abstract void arm(int slot, Weapon weapon);

	public abstract void disarm(int slot);

	public abstract int getHitModifier(Weapon weapon);

	public abstract Probability getTotalHitChance(int slot);

	public abstract int getDamageModifier(Weapon weapon);

	public abstract Probability getTotalDamageChance(int slot);

	public abstract int getRangeModifier(Weapon weapon);

	public abstract int getRangeScore(int slot);
}
