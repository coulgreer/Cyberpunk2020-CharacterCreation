package rpg.general.stats;

import java.beans.PropertyChangeListener;

/**
 * Lays down the groundwork for any Skill used in an RPG. Skill has a dependency
 * upon <code>Attribute</code>.
 */
public interface Skill extends Levelable, PropertyChangeListener {
	public static final String PROPERTY_NAME_LEVEL = "Level";

	/**
	 * Returns a String that represents this Skill's name.
	 * 
	 * @return the name of this Skill.
	 */
	public String getName();

	/**
	 * Returns the description of this Skill.
	 * 
	 * @return the description of this Skill.
	 */
	public String getDescription();

	/**
	 * Returns the total derived value of this skill. Based on if this Skill is
	 * dependent upon a <code>Attribute</code>
	 * 
	 * @return the total value between the Skills base score plus whatever its
	 *         associated modifier is.
	 */
	public int getTotalValue();

	/**
	 * Adds a PropertyChangeListener to the listener list.
	 * 
	 * @param listener the PropertyChangeListener to be added
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Removes a PropertyChangeListener from the listener list.
	 * 
	 * @param listener the PropertyChangeListener to be removed
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Adds a PropertyChangeListener for a specific property.
	 * 
	 * @param propertyName the name of the property to listen on
	 * @param listener     the PropertyChangeListener to be added
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

	/**
	 * Removes a PropertyChangeListener for a specific property.
	 * 
	 * @param propertyName the name of the property that was listened on
	 * @param listener     the PropertyChangeListener to be removed
	 */
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
}
