package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeListener;

import rpg.general.stats.Attribute;

/**
 * An instance of Attribute that does nothing. Follows the Null Object pattern.
 * 
 * @author Coul Greer
 */
public class NullAttribute implements Attribute {
	private static NullAttribute uniqueInstance;

	/**
	 * Constructs a new NullAttribute if one has not already been created. This is
	 * the only way to get an instance of this class, and this should represent that
	 * this will be the only instance of this class.
	 * 
	 * @return the one and only instance of NullAttribute
	 */
	public static NullAttribute getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NullAttribute();
		}

		return uniqueInstance;
	}

	private NullAttribute() {
	}

	@Override
	public void increaseLevel() {
	}

	@Override
	public void decreaseLevel() {
	}

	@Override
	public void resetLevel() {
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
	}

	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
	}

	@Override
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public int getModifier() {
		return 0;
	}

}
