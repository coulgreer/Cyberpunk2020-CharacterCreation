package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import rpg.general.stats.Attribute;

public class RunAttribute implements Attribute, PropertyChangeListener {
	private String name;
	private String description;
	private Attribute parentAttribute;
	private int value;
	private PropertyChangeSupport changeSupport;

	public RunAttribute(String name, String description, Attribute parentStatistic) {
		setName(name);
		setDescription(description);
		this.parentAttribute = parentStatistic;
		parentStatistic.addPropertyChangeListener(Attribute.PROPERTY_NAME_LEVEL, this);
		value = calculateLevel();
		changeSupport = new PropertyChangeSupport(this);
	}

	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("The field 'name' cannot be null.");
		} else {
			this.name = name;
		}
	}

	private void setDescription(String description) {
		if (name == null) {
			throw new IllegalArgumentException("The field 'description' cannot be null.");
		} else {
			this.description = description;
		}
	}

	private int calculateLevel() {
		return (int) (3.0 * parentAttribute.getModifier());
	}

	@Override
	public void increaseLevel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void decreaseLevel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void resetLevel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getLevel() {
		return value;
	}

	@Override
	public int getModifier() {
		return value;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (parentAttribute == evt.getSource()) {
			value = calculateLevel();
		}
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}
}
