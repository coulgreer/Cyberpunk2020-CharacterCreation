package rpg.general.stats;

import java.beans.PropertyChangeListener;

public interface Statistic {
	public static final String PROPERTY_NAME_LEVEL = "Level";

	/**
	 * Returns a String that represents this statistic's name.
	 * 
	 * @return the name of this statistic.
	 */
	public String getName();

	/**
	 * Returns a String that represents this statistic's description.
	 * 
	 * @return the description of this statistic.
	 */
	public String getDescription();

	/**
	 * Returns the current level.
	 * 
	 * @return the current level.
	 */
	public int getLevel();

	/**
	 * Increments current level.
	 */
	public void increaseLevel();

	/**
	 * Decrements current level.
	 */
	public void decreaseLevel();

	/**
	 * Resets level to starting value.
	 */
	public void resetLevel();

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
