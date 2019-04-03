package rpg.general.combat;

import rpg.general.commerce.Item;
import rpg.util.Probability;

public interface Ammunition extends Item {
	public static final String NO_AMMUNITION_TYPE = "None";
	public static final double WEIGHT_OF_BOX = 0.5;

	/**
	 * Returns the category that this <code>Ammunition</code> belongs to.
	 * 
	 * @return the category that this <code>Ammunition</code> belongs to
	 */
	public String getAmmunitionType();

	/**
	 * Returns the number of ammunition that is normally held within one container.
	 * This is used to represent a box of ammunition instead of the cartridge,
	 * arrow, etc.
	 * 
	 * @return the amount of ammunition held within a container before purchase.
	 */
	public int getAmmunitionPerBox();

	/**
	 * Returns the damage potential represented by die and a flat modifier.
	 * 
	 * @return the damage potential represented as a die
	 */
	public Probability getDamage();

	/**
	 * Returns the conditional bonuses. This excludes any flat bonuses that are
	 * applied in general to the scores of range, damage, and hit. For example,
	 * modifying values in a specific situation like "when crouched" or "in certain
	 * lighting".
	 * 
	 * @return any bonuses that are not modifiers to range, damage, and hit in a
	 *         general case. Such as, modifying values in a specific situation like
	 *         "when crouched" or "in certain lighting".
	 */
	public String printBonuses();
}
