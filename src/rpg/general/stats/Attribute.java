package rpg.general.stats;

import rpg.util.Observable;

/**
 * Lays down the groundwork for any Attribute used in an RPG.
 */
public interface Attribute extends Levelable, Observable {
	/**
	 * Returns a String that represents this Attribute's name.
	 * 
	 * @return the name of this Attribute.
	 */
	public String getName();

	/**
	 * Return the description of the Attribute.S
	 * 
	 * @return the description of the Attribute.
	 */
	public String getDescription();

	/**
	 * Return the modifier which can differ from what is returned from
	 * <code>getLevel()</code>.
	 * 
	 * @return the modifier derived from the level of an attribute.
	 */
	public int getModifier();
}
