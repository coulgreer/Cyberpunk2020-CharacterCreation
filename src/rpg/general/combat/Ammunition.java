package rpg.general.combat;

import rpg.general.commerce.Item;
import rpg.util.Probability;

/**
 * An object representing ammunition that is put inside of a ranged weapon in
 * order for it to be effectively used.
 * 
 * @author Coul Greer
 */
public interface Ammunition extends Item {
	/**
	 * The constant value representing that an ammunition has no type.
	 */
	public static final String NO_AMMUNITION_TYPE = "None";

	/**
	 * Returns the category that this <code>Ammunition</code> belongs to.
	 * 
	 * @return the category that this <code>Ammunition</code> belongs to
	 */
	public String getAmmunitionType();

	/**
	 * Returns the damage potential represented by die and a flat modifier.
	 * 
	 * @return the damage potential represented as a die
	 */
	public Probability getDamage();
}
