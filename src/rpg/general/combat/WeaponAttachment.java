package rpg.general.combat;

import java.util.Iterator;

/**
 * An interface that maps out the behavior for WeaponAttachments which extend
 * <code>Weaponizable</code>. This allows WeaponAttachment to modify the scores
 * that a weapon uses.
 * 
 * @author Coul Greer
 */
public interface WeaponAttachment extends Weaponizable {
	/**
	 * The constant used as a default attachment point.
	 */
	public static final String MISCELLANEOUS = "Miscellaneous";

	/**
	 * Returns the slot that this <code>WeaponModifier</code> occupies on a
	 * <code>Weapon</code>.
	 * 
	 * @return the slot that this <code>WeaponModifier</code> occupies on a
	 *         <code>Weapon</code>.
	 */
	public String getAttachmentPoint();

	/**
	 * Returns any bonuses that require a certain circumstance in order for a
	 * modifier to apply itself to the score of a <code>Weapon</code>. For example,
	 * "while crouching" or "in dimly lit areas".
	 * 
	 * @return any bonuses that require a circumstance for a modifier to apply
	 */
	public Iterator<String> getConditionalBonus();
}
