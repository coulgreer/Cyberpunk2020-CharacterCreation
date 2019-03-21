package rpg.general.stats;

import java.beans.PropertyChangeListener;

/**
 * Lays down the groundwork for any Attribute used in an RPG.
 */
public interface Attribute extends Levelable {
	public static final String PROPERTY_NAME_LEVEL = "Level";

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

	/**
	 * Returns a String that represents this Attribute's name.
	 * 
	 * @return the name of this Attribute.
	 */
	public String getName();

	/**
	 * Returns a String that represents this Attribute's description.
	 * 
	 * @return the description of this Attribute.
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
