package rpg.general.combat;

/**
 * Allows an object to be used as a weapon in the sense of displaying how
 * accurate an object is to hit its intended target, how much damage the object
 * does, and how far the object can attack on its own.
 * 
 * @author Coul Greer
 */
public interface Weaponizable {
	/**
	 * Gets the modifier number for the ability to hit another thing.
	 * 
	 * @return a number used to boost base hit probability
	 */
	public int getHitModifier();

	/**
	 * Gets the modifier number for the ability to deal damage to another thing.
	 * 
	 * @return a number used to boost base damage probability
	 */
	public int getDamageModifier();

	/**
	 * Gets the modifier number that increases overall range.
	 * 
	 * @return a number used to boost base range
	 */
	public int getRangeModifier();
}
