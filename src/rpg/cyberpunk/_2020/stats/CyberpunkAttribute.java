package rpg.cyberpunk._2020.stats;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import rpg.general.stats.Attribute;

public class CyberpunkAttribute implements Attribute {

	// Attribute names
	public static final String NOT_AVAILABLE = "Not Available";
	public static final String INTELLIGENCE = "Intelligence";
	public static final String REFLEXES = "Reflex";
	public static final String COOL = "Cool";
	public static final String TECHNICAL_ABILITY = "Technical Ability";
	public static final String LUCK = "Luck";
	public static final String ATTRACTIVENESS = "Attractiveness";
	public static final String MOVEMENT_ALLOWANCE = "Movement Allowance";
	public static final String RUN = "Run";
	public static final String LEAP = "Leap";
	public static final String EMPATHY = "Empathy";
	public static final String BODY_TYPE = "Body Type";
	public static final String CARRY = "Carry";

	// TODO Write JavaDoc for entire class
	public static final int MIN_LEVEL = 2;
	public static final int MAX_LEVEL = 10;

	private String name;
	private String description;
	private int level;
	private PropertyChangeSupport changeSupport;

	public CyberpunkAttribute(String name, String description) {
		this.name = name;
		this.description = description;
		level = MIN_LEVEL;
		changeSupport = new PropertyChangeSupport(this);
	}

	@Override
	public void increaseLevel() {
		if (level < MAX_LEVEL) {
			int oldLevel = level;
			level++;
			changeSupport.firePropertyChange(PROPERTY_NAME_ATTRIBUTE_LEVEL, oldLevel, level);
			changeSupport.firePropertyChange(PROPERTY_NAME_ATTRIBUTE_MODIFIER, oldLevel, level);
		}
	}

	@Override
	public void decreaseLevel() {
		if (level > MIN_LEVEL) {
			int oldLevel = level;
			level--;
			changeSupport.firePropertyChange(PROPERTY_NAME_ATTRIBUTE_LEVEL, oldLevel, level);
			changeSupport.firePropertyChange(PROPERTY_NAME_ATTRIBUTE_MODIFIER, oldLevel, level);
		}
	}

	@Override
	public void resetLevel() {
		int oldLevel = level;
		level = MIN_LEVEL;
		changeSupport.firePropertyChange(PROPERTY_NAME_ATTRIBUTE_LEVEL, oldLevel, level);
		changeSupport.firePropertyChange(PROPERTY_NAME_ATTRIBUTE_MODIFIER, oldLevel, level);
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public int getModifier() {
		return getLevel();
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
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}
}
