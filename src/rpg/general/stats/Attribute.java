package rpg.general.stats;

import java.beans.PropertyChangeListener;

/**
 * Lays down the groundwork for any Attribute used in an RPG. Attributes in this
 * sense are stats filling out a person's traits.
 */
public interface Attribute extends Statistic {
	/**
	 * A constant used to identify when the level state changes.
	 */
	public static final String PROPERTY_NAME_ATTRIBUTE_LEVEL = "Attribute Level";

	/**
	 * A constant used to identify when the modifier state changes.
	 */
	public static final String PROPERTY_NAME_ATTRIBUTE_MODIFIER = "Attribute Modifier";

	/**
	 * Return the derived score from the base level. This can differ from what is
	 * returned from <code>getLevel()</code>.
	 * 
	 * @return the modifier derived from the level of an attribute.
	 */
	public int getModifier();

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
