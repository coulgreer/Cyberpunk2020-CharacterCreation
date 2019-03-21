package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeListener;

import rpg.general.stats.Attribute;

public class NullAttribute implements Attribute {
	private static NullAttribute uniqueInstance;

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
