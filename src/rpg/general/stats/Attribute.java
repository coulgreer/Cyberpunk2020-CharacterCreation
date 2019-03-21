package rpg.general.stats;

/**
 * Lays down the groundwork for any Attribute used in an RPG. Attributes in this
 * sense are stats filling out a person's traits.
 */
public interface Attribute extends Statistic {

	/**
	 * Return the derived score from the base level. This can differ from what is
	 * returned from <code>getLevel()</code>.
	 * 
	 * @return the modifier derived from the level of an attribute.
	 */
	public int getModifier();

}
