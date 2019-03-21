package rpg.general.combat;

import java.util.List;

import rpg.util.Probability;

public interface Combatant {
	/**
	 * Equips a weapon to a combatants slot while returning the old weapon.
	 * 
	 * @param slot   the index of where the weapon will be stored
	 * @param weapon the weapon to be stored at the given index
	 */
	public void arm(int slot, Weapon weapon);

	/**
	 * Unequips a weapon from a combatants slot.
	 * 
	 * @param slot the index of the weapon to remove from storage
	 */
	public void disarm(int slot);

	/**
	 * Returns a modifier value based on information provided by
	 * <code>Weapon</code>. The value represents the ability to hit a target.
	 * 
	 * @param weapon the <code>Weapon</code> used to derive the hit modifier
	 * @return the modifier value to be used in calculation of the hit score for
	 *         <code>Weapon</code>
	 */
	public int getHitModifier(Weapon weapon);

	/**
	 * Returns a modifier value based on information provided by
	 * <code>Weapon</code>. The value represents the amount of additional damage
	 * dealt.
	 * 
	 * @param weapon the <code>Weapon</code> used to derive the damage modifier
	 * @return the modifier value to be used in the calculation of the damage score
	 *         for <code>Weapon</code>
	 */
	public int getDamageModifier(Weapon weapon);

	/**
	 * Returns a modifier value based on information provided by
	 * <code>Weapon</code>. The value represents the additional range.
	 * 
	 * @param isThrown determines if an item is being thrown or not. Usually, the
	 *                 returned modifier is based on a characters strength.
	 * @return the modifier value to be used in the calculation of the range score
	 *         for a <code>Weapon</code>
	 */
	public int getRangeModifier(boolean isThrown);

	/**
	 * Returns the total probability of hitting a target given an index. This is
	 * based off the <code>Weapon</code> at the index, and this includes the die and
	 * the guaranteed score to hit.
	 * 
	 * @param slot the index used to get the hit chance of a <code>Weapon</code>
	 *             held by this <code>Combatant</code>
	 * @return the hit chance of the <code>Weapon</code> held at the given index of
	 *         this <code>Combatant</code>
	 */
	public Probability getTotalHitChance(int slot);

	/**
	 * Returns the total probability of damaging a target given an index. This
	 * includes the die and the guaranteed score to deal damage.
	 * 
	 * @param slot the index used to get the damage chance of a <code>Weapon</code>
	 *             held by this <code>Combatant</code>
	 * @return the damage chance of the <code>Weapon</code> held at the given index
	 *         of this <code>Combatant</code>
	 */
	public Probability getTotalDamageChance(int slot);

	/**
	 * Returns the max range of a <code>Weapon</code> at a given index.
	 * 
	 * @param slot the index used to get the max range of a <code>Weapon</code> held
	 *             by this <code>Combatant</code>
	 * @return the range of the <code>Weapon</code> at the index of this
	 *         <code>Combatant</code>
	 */
	public int getRangeScore(int slot);

	/**
	 * Decrements the ammunition of a <code>Weapon</code> based on its slot.
	 * 
	 * @param slot       the index of an equipped weapon
	 * @param shotsFired the desired amount of attacks wanted to be made
	 */
	public void attack(int slot, int shotsFired);

	/**
	 * Returns the current amount of ammunition stored
	 * 
	 * @param slot the index of an equipped weapon
	 * @return the current amount of ammunition stored
	 */
	public int getAmmoCount(int slot);

	/**
	 * Transfers ammunition to the storage unit of the indexed weapon, returning the
	 * remaining ammunition from the transfer.
	 * 
	 * @param slot        the index of an equipped weapon
	 * @param storageUnit the storage unit used to get ammunition from and transfer
	 *                    its contents to the indexed weapon
	 * @return the remaining ammunition from the transfer
	 */
	public List<Ammunition> reload(int slot, AmmunitionContainer storageUnit);

	/**
	 * Returns a <code>Weapon</code> based on the given index.
	 * 
	 * @param slot the index of an equipped weapon
	 * @return the equipped weapon in the given index
	 */
	public Weapon getWeapon(int slot);
}
