package rpg.general.combat;

import java.util.Iterator;

import rpg.cyberpunk._2020.combat.Magazine;
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

	public abstract int getAmmoCount(int slot);

	public abstract Magazine reload(int slot, Magazine magazine);

	public abstract Iterator<? extends Weapon> createIterator();
}
